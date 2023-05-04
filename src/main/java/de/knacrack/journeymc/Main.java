package de.knacrack.journeymc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.knacrack.journeymc.command.Command;
import de.knacrack.journeymc.command.CommandSingleton;
import de.knacrack.journeymc.command.list.*;
import de.knacrack.journeymc.item.ItemSingleton;
import de.knacrack.journeymc.item.list.*;
import de.knacrack.journeymc.item.recipe.Recipe;
import de.knacrack.journeymc.item.recipe.RecipeSingleton;
import de.knacrack.journeymc.item.recipe.craftingtable.*;
import de.knacrack.journeymc.item.recipe.furnace.RawCopperBlockToCopperBlock;
import de.knacrack.journeymc.item.recipe.furnace.RawGoldBlockToGoldBlock;
import de.knacrack.journeymc.item.recipe.furnace.RawIronBlockToIronBlock;
import de.knacrack.journeymc.item.recipe.furnace.RottenFleshToLeather;
import de.knacrack.journeymc.item.recipe.stonecutter.CobbleToGravel;
import de.knacrack.journeymc.item.recipe.stonecutter.DirtToGravel;
import de.knacrack.journeymc.item.recipe.stonecutter.GravelToSand;
import de.knacrack.journeymc.listener.Listener;
import de.knacrack.journeymc.listener.ListenerSingleton;
import de.knacrack.journeymc.listener.list.*;
import de.knacrack.journeymc.utils.playerprofile.PlayerProfile;
import de.knacrack.journeymc.utils.runnable.PlayerProfileRunnable;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class Main extends JavaPlugin {

    private static Main instance;

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private String packageName = getClass().getPackage().getName();

    @Override
    public void onEnable() {
        instance = this;
        CommandSingleton.getInstance().registerCommands(new Item(), new CombineEnchantments(), new Enderchest(), new Fly(), new GameMode(), new Heal(), new Invsee(), new Ip());
        ListenerSingleton.getInstance().registerListeners(new PlayerJoin(), new DropBeehive(), new DropSpawner(), new EntityDamage(), new EntityDestroyFarmland(), new PlaceSpawner(), new PlayerDeath(), new PlayerQuit(), new SnowmanTurret(), new SpawnFranz(), new SpawnKlaus(), new SpawnOlaf(), new CreatePortal());
        ItemSingleton.getInstance().registerItems(new PortableAnvil(), new PortableEnderchest(), new PortableWorkbench(), new Graam(), new AppleOfGaia(), new Borealis(), new Sol(), new Grenade(), new MagicHeart(), new Musket(), new Homecoming(), new MaraiasGreatsword(), new Excavator(), new Lumberjack());
        RecipeSingleton.getInstance().registerRecipes(new AppleOfGaiaRecipe(), new HomecomingRecipe(), new PortableAnvilRecipe(), new PortableEnderchestRecipe(), new PortableWorkbenchRecipe(), new SolRecipe(), new RawCopperBlockToCopperBlock(), new RawGoldBlockToGoldBlock(), new RawIronBlockToIronBlock(), new RottenFleshToLeather(), new CobbleToGravel(), new GravelToSand(), new DirtToGravel());

        /*registerCustomCommands("de.knacrack.journeymc.command.list");
        registerCustomListerners("de.knacrack.journeymc" + ".listener.list");
        registerCustomItems("de.knacrack.journeymc" + ".item.list");
        registerCustomRecipes("de.knacrack.journeymc" + ".item.recipe.craftingtable");
        registerCustomRecipes("de.knacrack.journeymc" + ".item.recipe.furnace");
        registerCustomRecipes("de.knacrack.journeymc" + ".item.recipe.stonecutter");*/

        saveDefaultConfig();

        Bukkit.getScheduler().scheduleAsyncRepeatingTask(Main.instance, new PlayerProfileRunnable(), 0, 20 * 60 * 5); // auto save every 5min
    }

    @Override
    public void onDisable() {
        Bukkit.getOnlinePlayers().forEach(Player::closeInventory);
        PlayerProfile.getAllStats().forEach(PlayerProfile::save);
    }

    private void registerCustomCommands(String packageName) {
        Set<Object> objects = findAllClassesUsingClassLoader(packageName, Command.class);
        for (Object obj : objects) {
            if (obj instanceof LinkedHashSet<?> hashSet) {
                hashSet.forEach(clazz -> {
                    try {
                        CommandSingleton.getInstance().registerCommand(((Class<? extends Command>) clazz).newInstance());
                    } catch (InstantiationException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
    }

    private void registerCustomListerners(String packageName) {
        Set<Object> objects = findAllClassesUsingClassLoader(packageName, Listener.class);
        for (Object obj : objects) {
            if (obj instanceof Listener) {
                ListenerSingleton.getInstance().registerListener(((Listener) obj));
            }
        }
    }

    private void registerCustomItems(String packageName) {
        Set<Object> objects = findAllClassesUsingClassLoader(packageName, de.knacrack.journeymc.item.Item.class);
        for (Object obj : objects) {
            if (obj instanceof de.knacrack.journeymc.item.Item) {
                ItemSingleton.getInstance().registerItem((de.knacrack.journeymc.item.Item) obj);
            }
        }
    }

    private void registerCustomRecipes(String packageName) {
        Set<Object> objects = findAllClassesUsingClassLoader(packageName, Recipe.class);
        for (Object obj : objects) {
            if (obj instanceof Recipe) {
                RecipeSingleton.getInstance().registerRecipe((Recipe) obj);
            }
        }
    }

    private Set<Object> findAllClassesUsingClassLoader(String packageName, Class<?> type) {
        Reflections reflections = new Reflections(packageName);
        return Collections.singleton(reflections.getSubTypesOf(type));
    }

    private Object getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "." + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }

    /*private Set<Object> findAllClassesUsingClassLoader(String packageName) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        String path = packageName.replaceAll("\\.", "/");
        InputStream stream = classLoader.getResourceAsStream(path);

        if (stream == null) {
            throw new IllegalArgumentException("Package not found: " + packageName);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            return reader.lines()
                    .filter(line -> line.endsWith(".class"))
                    .map(line -> getClass(line, packageName))
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException("Error reading package: " + packageName, e);
        }
    }*/

    /*private Class<?> getClass(String className, String packageName) {
        String fullClassName = packageName + "." + className.substring(0, className.length() - 6);
        try {
            return Class.forName(fullClassName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Class not found: " + fullClassName, e);
        }
    }*/

    public static Main getInstance() {
        return instance;
    }

    public static Gson getGson() {
        return gson;
    }
}

package de.knacrack.journeymc;

import de.knacrack.journeymc.command.CommandSingleton;
import de.knacrack.journeymc.command.list.*;
import de.knacrack.journeymc.item.ItemSingleton;
import de.knacrack.journeymc.item.list.*;
import de.knacrack.journeymc.item.recipe.RecipeSingleton;
import de.knacrack.journeymc.item.recipe.craftingtable.*;
import de.knacrack.journeymc.item.recipe.furnace.RawCopperBlockToCopperBlock;
import de.knacrack.journeymc.item.recipe.furnace.RawGoldBlockToGoldBlock;
import de.knacrack.journeymc.item.recipe.furnace.RawIronBlockToIronBlock;
import de.knacrack.journeymc.item.recipe.furnace.RottenFleshToLeather;
import de.knacrack.journeymc.item.recipe.stonecutter.CobbleToGravel;
import de.knacrack.journeymc.item.recipe.stonecutter.DirtToGravel;
import de.knacrack.journeymc.item.recipe.stonecutter.GravelToSand;
import de.knacrack.journeymc.listener.ListenerSingleton;
import de.knacrack.journeymc.listener.list.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        CommandSingleton.getInstance().registerCommands(new Item(), new CombineEnchantments(), new Enderchest(), new Fly(), new GameMode(), new Heal(), new Invsee(), new Ip());
        ListenerSingleton.getInstance().registerListeners(new PlayerJoin(), new DropBeehive(), new DropSpawner(), new EntityDamage(), new EntityDestroyFarmland(), new PlaceSpawner(), new PlayerDeath(), new PlayerQuit(), new SnowmanTurret(), new SpawnFranz(), new SpawnKlaus(), new SpawnOlaf(), new CreatePortal());
        ItemSingleton.getInstance().registerItems(new PortableAnvil(), new PortableEnderchest(), new PortableWorkbench(), new Graam(), new AppleOfGaia(), new Borealis(), new Sol(), new Grenade(), new MagicHeart(), new Musket(), new Homecoming());
        RecipeSingleton.getInstance().registerRecipes(new AppleOfGaiaRecipe(), new HomecomingRecipe(), new PortableAnvilRecipe(), new PortableEnderchestRecipe(), new PortableWorkbenchRecipe(), new SolRecipe(), new RawCopperBlockToCopperBlock(), new RawGoldBlockToGoldBlock(), new RawIronBlockToIronBlock(), new RottenFleshToLeather(), new CobbleToGravel(), new GravelToSand(), new DirtToGravel());

        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        Bukkit.getOnlinePlayers().forEach(Player::closeInventory);
    }


    public static Main getInstance() {
        return instance;
    }
}

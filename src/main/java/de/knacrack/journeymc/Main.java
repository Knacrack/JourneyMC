package de.knacrack.journeymc;

import de.knacrack.journeymc.command.CommandSingleton;
import de.knacrack.journeymc.command.list.Item;
import de.knacrack.journeymc.item.ItemSingleton;
import de.knacrack.journeymc.item.list.Graam;
import de.knacrack.journeymc.item.list.PortableAnvil;
import de.knacrack.journeymc.item.list.PortableEnderchest;
import de.knacrack.journeymc.item.list.PortableWorkbench;
import de.knacrack.journeymc.listener.ListenerSingleton;
import de.knacrack.journeymc.listener.list.PlayerJoin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        CommandSingleton.getInstance().registerCommands(new Item());
        ItemSingleton.getInstance().registerItems(new PortableAnvil(), new PortableEnderchest(), new PortableWorkbench(), new Graam());
        ListenerSingleton.getInstance().registerListener(new PlayerJoin());
    }

    @Override
    public void onDisable() {
        Bukkit.getOnlinePlayers().stream().forEach(Player::closeInventory);
    }


    public static Main getInstance() {
        return instance;
    }
}

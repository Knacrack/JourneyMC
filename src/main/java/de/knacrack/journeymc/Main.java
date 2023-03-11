package de.knacrack.journeymc;

import de.knacrack.journeymc.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;

public class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        Utils.closePlayersInventory((Collection<Player>) players);
    }


    public static Main getInstance() {
        return instance;
    }
}

package de.knacrack.journeymc.listener.list;

import de.knacrack.journeymc.Main;
import de.knacrack.journeymc.listener.Listener;
import de.knacrack.journeymc.utils.ServerScoreboard;
import de.knacrack.journeymc.utils.playerprofile.PlayerProfile;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerQuit implements Listener {

    @EventHandler
    public void onPlayerQuit(@NotNull PlayerQuitEvent event) {
        event.quitMessage(Component.text("§8[§c-§8]§6§l " + event.getPlayer().getName()));
        PlayerProfile.dispose(event.getPlayer().getUniqueId().toString());
        ServerScoreboard.getServerScoreboard().removePlayer(event.getPlayer());
    }



    @Override
    public String getLabel() {
        return "PlayerQuit";
    }

    @Override
    public boolean register() {
        return true;
    }

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(Main.getInstance(), getClass().getSimpleName());
    }
}

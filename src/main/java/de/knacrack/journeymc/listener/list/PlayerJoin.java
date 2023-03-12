package de.knacrack.journeymc.listener.list;

import de.knacrack.journeymc.Main;
import de.knacrack.journeymc.listener.Listener;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(@NotNull PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.joinMessage(Component.text("§8[§a+§8]§6§l " + player.getName()));
    }

    @Override
    public String getLabel() {
        return "PlayerJoin";
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

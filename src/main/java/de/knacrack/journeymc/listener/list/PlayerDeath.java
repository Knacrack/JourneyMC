package de.knacrack.journeymc.listener.list;

import de.knacrack.journeymc.Main;
import de.knacrack.journeymc.listener.Listener;
import de.knacrack.journeymc.utils.PlayerHead;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerDeath implements Listener {

    @EventHandler
    public void onPlayerDeath(@NotNull PlayerDeathEvent event) {
        String message = event.getDeathMessage();
        event.deathMessage(Component.text("§8[§r☠§8] §c" + message));
        if (event.getPlayer().getKiller() != null) {
            event.getPlayer().getWorld().dropItem(event.getPlayer().getKiller().getLocation(), PlayerHead.getPlayerHead(event.getPlayer().getUniqueId()));
        }
    }

    @Override
    public String getLabel() {
        return "PlayerDeath";
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

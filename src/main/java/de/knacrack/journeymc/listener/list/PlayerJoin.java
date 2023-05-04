package de.knacrack.journeymc.listener.list;

import de.knacrack.journeymc.Main;
import de.knacrack.journeymc.listener.Listener;
import de.knacrack.journeymc.utils.ServerScoreboard;
import de.knacrack.journeymc.utils.Utils;
import de.knacrack.journeymc.utils.playerprofile.PlayerProfile;
import net.kyori.adventure.text.Component;
import org.bukkit.Color;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(@NotNull PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerProfile playerStats = PlayerProfile.getCharacter(player);
        event.joinMessage(Component.text("§8[§a+§8]§6§l " + player.getName()));
        player.setPlayerListHeaderFooter("§8\n§r" + Main.getInstance().getDescription().getName() + "\n§7Version§8: §e" + Main.getInstance().getDescription().getVersion() + "\n§8", "");
        player.setPlayerListName(playerStats.getCustomName());

        ServerScoreboard.getServerScoreboard().addPlayer(event.getPlayer());
        ServerScoreboard.getServerScoreboard().setScoreboard(event.getPlayer());
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

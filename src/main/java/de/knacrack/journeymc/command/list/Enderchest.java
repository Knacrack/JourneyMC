package de.knacrack.journeymc.command.list;

import com.google.common.collect.Lists;
import de.knacrack.journeymc.Main;
import de.knacrack.journeymc.command.Command;
import de.knacrack.journeymc.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Enderchest implements Command {
    @Override
    public @Nullable String getPermission() {
        return "journeymc.enderchest";
    }

    @Override
    public List<String> getAliases() {
        return Lists.newArrayList("ec");
    }

    @Override
    public String getDescription() {
        return "Opens the Enderchest of the Player.";
    }

    @Override
    public void command(CommandSender sender, String[] args) {
        if(sender instanceof Player player && player.hasPermission(getPermission())) {
            if(args.length == 0) {
                player.openInventory(player.getEnderChest());
                player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 1f, 1f);
            }

            if(args.length == 1) {
                Player target;
                if((target = Bukkit.getPlayer(args[0])) == null) {
                    sender.sendMessage(Messages.PLAYER_NOT_ONLINE.getMessage());
                } else {
                    player.openInventory(target.getEnderChest());
                    player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 1f, 1f);
                }
            }

        } else {
            sender.sendMessage(Messages.ERROR.getMessage());
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }

    @Override
    public String getLabel() {
        return "Enderchest";
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

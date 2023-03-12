package de.knacrack.journeymc.command.list;

import com.google.common.collect.Lists;
import de.knacrack.journeymc.Main;
import de.knacrack.journeymc.command.Command;
import de.knacrack.journeymc.utils.Messages;
import de.knacrack.journeymc.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class Fly implements Command {
    @Override
    public @Nullable String getPermission() {
        return "journeymc.fly";
    }

    @Override
    public List<String> getAliases() {
        return Lists.newArrayList();
    }

    @Override
    public String getDescription() {
        return "Make the Player fly.";
    }

    /**
     * FIXME: needs to be refactored
     * @param sender
     * @param args
     */
    @Override
    public void command(CommandSender sender, String[] args) {
        if(!sender.hasPermission(getPermission())) return;

        if (args.length == 0 && sender instanceof Player player) {
            enableFlying(player);
            return;
        } else if(args.length == 1) {
            Player other = Bukkit.getPlayer(args[0]);
            if (other == null) {
                sender.sendMessage(Messages.PLAYER_NOT_ONLINE.getMessage());
                return;
            }

            enableFlying(other);

            if (other.getAllowFlight()) {
                sender.sendMessage("Flying is now enabled for " + other.getName() + "!");
            }
            else {
                sender.sendMessage("Flying is now disabled for " + other.getName() + "!");
            }

            return;
        }

        sender.sendMessage(Messages.SYNTAX_ERROR.getMessage());
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        List<String> list = Collections.emptyList();

        if (sender.hasPermission(getPermission()) && args.length == 1) {
            list = Utils.getOnlinePlayers(args[0]);
        }

        return list;
    }

    private void enableFlying(Player player) {
        boolean allowFlight = player.getAllowFlight();
        if (allowFlight) {
            player.sendMessage("Flying is now " + "disabled" + "!");
        } else {
            player.sendMessage("Flying is now " + "enabled" + "!");
        }
        player.setAllowFlight(!allowFlight);
    }

    @Override
    public String getLabel() {
        return "Fly";
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

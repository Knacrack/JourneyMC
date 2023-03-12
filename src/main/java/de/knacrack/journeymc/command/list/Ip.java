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

public class Ip implements Command {
    @Override
    public @Nullable String getPermission() {
        return "journeymc.ip";
    }

    @Override
    public List<String> getAliases() {
        return Lists.newArrayList();
    }

    @Override
    public String getDescription() {
        return "Outputs the IP of a Player.";
    }

    @Override
    public void command(CommandSender sender, String[] args) {
        if(sender.hasPermission(getPermission())){
            if(args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if(target != null) {
                    sender.sendMessage(target.getName() + "'s IP: " + target.getAddress().getAddress().getHostAddress());
                }
            }
        } else {
            sender.sendMessage(Messages.NO_PERMISSION.getMessage());
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        List<String> list = Collections.emptyList();
        if (args.length == 1 && sender.hasPermission(getPermission())) {
            list = Utils.getOnlinePlayers(args[0]);
        }
        return list;
    }

    @Override
    public String getLabel() {
        return "Ip";
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

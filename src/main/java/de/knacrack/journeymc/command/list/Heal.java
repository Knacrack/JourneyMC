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

public class Heal implements Command {
    @Override
    public @Nullable String getPermission() {
        return "journeymc.heal";
    }

    @Override
    public List<String> getAliases() {
        return Lists.newArrayList();
    }

    @Override
    public String getDescription() {
        return "Heals the Player";
    }

    /**
     * FIXME: needs to be refactored
     * @param sender
     * @param args
     */
    @Override
    public void command(CommandSender sender, String[] args) {
        if (!sender.hasPermission(getPermission())) return;
        switch (args.length) {
            case 0 -> {
                if (!(sender instanceof Player player))
                    return;
                player.setHealth(player.getMaxHealth());
                player.setFoodLevel(20);
                player.setSaturation(20f);
                player.sendMessage(Messages.SELF_HEALING.getMessage());
                return;
            }

            case 1 -> {
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    sender.sendMessage(Messages.PLAYER_NOT_ONLINE.getMessage());
                    return;
                }

                target.setHealth(target.getMaxHealth());
                target.setFoodLevel(20);
                target.setSaturation(20f);
                sender.sendMessage(Messages.OTHER_HEALING.getMessage().replace("%p%", target.getName()));
                target.sendMessage(Messages.SELF_HEALING.getMessage());

                return;
            }
        }

        sender.sendMessage("/heal <player>");
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
        return "Heal";
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

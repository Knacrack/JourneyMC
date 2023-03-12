package de.knacrack.journeymc.command.list;

import com.google.common.collect.Lists;
import de.knacrack.journeymc.Main;
import de.knacrack.journeymc.command.Command;
import de.knacrack.journeymc.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class GameMode implements Command {
    @Override
    public @Nullable String getPermission() {
        return "journeymc.gamemode";
    }

    @Override
    public List<String> getAliases() {
        return Lists.newArrayList("gm");
    }

    @Override
    public String getDescription() {
        return "Changes the GameMode of Player";
    }

    @Override
    public void command(CommandSender sender, String[] args) {
        if (!sender.hasPermission(getPermission())) {
            sender.sendMessage("§cYou don't have Permission to perform this action!");
            return;
        }

        if (sender instanceof Player player) {
            org.bukkit.GameMode mode;
            if (args.length == 1) {
                mode = validate(args[0]);

                if(changeGameMode(player, mode)) {
                    sender.sendMessage("Du bist jetzt im Spielmodus " + mode + ".");
                } else {
                    sender.sendMessage("Du bist bereits im Spielmodus " + mode + ".");
                }
            } else {
                sender.sendMessage("§cA problem occured!");
            }
        } else {
            org.bukkit.GameMode mode;
            Player player;

            if(args.length == 2) {
                mode = validate(args[0]);
                if((player = Bukkit.getPlayer(args[1])) == null){
                    sender.sendMessage("§cA problem occured!");
                } else {
                    if (changeGameMode(player, mode)) {
                        sender.sendMessage(player.getName() + " ist jetzt im Spielmodus " + mode + ".");
                        player.sendMessage("Du bist jetzt im Spielmodus " + mode + ".");
                    } else {
                        sender.sendMessage(player.getName() + " ist bereits im Spielmodus " + mode + ".");
                    }
                }
            } else {
                sender.sendMessage("§cA problem occured!");
            }
        }
    }

    private org.bukkit.GameMode validate(String pPossibility) {
        org.bukkit.GameMode mode = org.bukkit.GameMode.SURVIVAL;
        switch (pPossibility.toLowerCase()) {
            case "su", "0", "s", "survival" -> mode = org.bukkit.GameMode.SURVIVAL;

            case  "cr", "1", "c", "creative" -> mode = org.bukkit.GameMode.CREATIVE;

            case "ad", "2", "a", "adventure" -> mode = org.bukkit.GameMode.ADVENTURE;

            case "sp", "3", "spec", "spectator" -> mode = org.bukkit.GameMode.SPECTATOR;
        }
        return mode;
    }

    private boolean changeGameMode(Player pPlayer, org.bukkit.GameMode pGameMode) {
        boolean outVar = false;
        if (!pPlayer.getGameMode().equals(pGameMode)) {
            pPlayer.setGameMode(pGameMode);
            outVar = true;
        }
        return outVar;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        List<String> list = Collections.emptyList();
        if (sender.hasPermission(getPermission())) {
            if (args.length == 1) {
                list = Lists.newArrayList("0", "1", "2", "3");
            } else if (args.length == 2) {
                list = Utils.getOnlinePlayers(args[1]);
            }
        }
        return list;
    }

    @Override
    public String getLabel() {
        return "GameMode";
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

package de.knacrack.journeymc.command.list;

import com.google.common.collect.Lists;
import de.knacrack.journeymc.Main;
import de.knacrack.journeymc.command.Command;
import de.knacrack.journeymc.item.ItemSingleton;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class Item implements Command {
    @Override
    public @Nullable String getPermission() {
        return "journeymc.item";
    }

    @Override
    public List<String> getAliases() {
        return Lists.newArrayList("i");
    }

    @Override
    public String getDescription() {
        return "Gives the Player a custom Item.";
    }

    @Override
    public void command(CommandSender sender, String[] args) {
        if (sender.hasPermission(getPermission())) {
            if (args.length == 1) {
                if (ItemSingleton.getInstance().getRegisteredItemNames().contains(args[0]) && sender instanceof Player player) {
                    ItemStack item = ItemSingleton.getInstance().getRegisteredItemsByName().get(args[0]).getItem();
                    player.getInventory().addItem(item);
                }
            }
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        List<String> list = Collections.emptyList();
        if (args.length == 1 && sender.hasPermission(getPermission())) {
            list = ItemSingleton.getInstance().getRegisteredItemNames();
        }
        return list;
    }

    @Override
    public String getLabel() {
        return "item";
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

package de.knacrack.journeymc.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Collection;

public class Utils {
    public static void closePlayersInventory(Collection<? extends Player> players) {
        players.stream().forEach(Player::closeInventory);
    }

    public static boolean hasContainerTag(ItemStack itemStack, NamespacedKey key) {
        boolean out = itemStack.getItemMeta() != null;
        out = out && itemStack.getItemMeta().getPersistentDataContainer().has(key);
        return out;
    }

    public static ItemStack addContainerTag(ItemStack itemStack, NamespacedKey key, String value) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (!itemMeta.getPersistentDataContainer().has(key)) {
            itemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, value);
            itemStack.setItemMeta(itemMeta);
        }
        return itemStack;
    }
}

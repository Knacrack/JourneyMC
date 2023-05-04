package de.knacrack.journeymc.utils;

import de.knacrack.journeymc.utils.uuid.UUIDFetcher;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public class PlayerHead {

    public static ItemStack getPlayerHead(String playerName) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        UUID uuid = UUIDFetcher.getUUID(playerName);
        meta.setOwningPlayer(Bukkit.getOfflinePlayer(uuid));
        head.setItemMeta(meta);
        return head;
    }

    public static ItemStack getPlayerHead(UUID uuid) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        meta.setOwningPlayer(Bukkit.getOfflinePlayer(uuid));
        head.setItemMeta(meta);
        return head;
    }

}

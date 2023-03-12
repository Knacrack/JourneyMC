package de.knacrack.journeymc.item.list;

import de.knacrack.journeymc.item.Item;
import de.knacrack.journeymc.item.ItemBuilder;
import de.knacrack.journeymc.listener.Listener;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public class MagicHeart implements Item, Listener {
    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.STICK).setName("§dMagic Heart").setCustomModelId(getCustomModelId()).addStringTag(getKey(), getLabel()).getItem();
    }

    @Override
    public int getCustomModelId() {
        return 0;
    }

    @Override
    public String getLabel() {
        return "MagicHeart";
    }

    @Override
    public boolean register() {
        return false;
    }

    @Override
    public NamespacedKey getKey() {
        return null;
    }
}

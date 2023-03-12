package de.knacrack.journeymc.item.list;

import de.knacrack.journeymc.Main;
import de.knacrack.journeymc.item.Item;
import de.knacrack.journeymc.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class Graam implements Item {
    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.NETHERITE_SWORD).setName("Graam").setUnbreakable(true).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).setCustomModelId(getCustomModelId()).getItem();
    }

    @Override
    public int getCustomModelId() {
        return 0;
    }

    @Override
    public String getLabel() {
        return "Graam";
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

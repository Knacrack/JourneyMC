package de.knacrack.journeymc.item.list;

import de.knacrack.journeymc.Main;
import de.knacrack.journeymc.item.Item;
import de.knacrack.journeymc.item.ItemBuilder;
import de.knacrack.journeymc.utils.ItemAttribute;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class HammerOfGravity implements Item {
    @Override
    public ItemStack getItem() {
        ItemStack hammer_of_gravity = new ItemBuilder(Material.NETHERITE_SWORD).setCustomModelId(getCustomModelId()).setName("§6§l" + getLabel()).addItemAttribute(new ItemAttribute(Attribute.GENERIC_ATTACK_DAMAGE, EquipmentSlot.HAND, 15d)).addStringTag(getKey(), "hammer_of_gravity").getItem();
        return hammer_of_gravity;
    }

    @Override
    public int getCustomModelId() {
        return 1;
    }

    @Override
    public String getLabel() {
        return "Hammer of Gravity";
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

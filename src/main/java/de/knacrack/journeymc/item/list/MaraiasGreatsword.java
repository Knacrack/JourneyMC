package de.knacrack.journeymc.item.list;

import de.knacrack.journeymc.Main;
import de.knacrack.journeymc.item.Item;
import de.knacrack.journeymc.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class MaraiasGreatsword implements Item {
    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.NETHERITE_SWORD).setName("§6§l"+ getLabel() + "§r").addEnchantment(Enchantment.DAMAGE_ALL, 10).addEnchantment(Enchantment.DAMAGE_UNDEAD, 10).setCustomModelId(getCustomModelId()).addEnchantment(Enchantment.SWEEPING_EDGE, 5).addStringTag(getKey(), "Maraias").getItem();
    }

    @Override
    public int getCustomModelId() {
        return 2;
    }

    @Override
    public String getLabel() {
        return "Maraia's";
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

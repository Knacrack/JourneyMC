package de.knacrack.journeymc.item.list;

import de.knacrack.journeymc.Main;
import de.knacrack.journeymc.item.Item;
import de.knacrack.journeymc.item.ItemBuilder;
import de.knacrack.journeymc.listener.Listener;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class Sol implements Item, Listener {
    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.NETHERITE_SWORD).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).setName("§c§lSol").addEnchantment(Enchantment.FIRE_ASPECT, 3).addEnchantment(Enchantment.DAMAGE_UNDEAD, 6).addEnchantment(Enchantment.DAMAGE_ALL, 7).addEnchantment(Enchantment.SWEEPING_EDGE, 5).setCustomModelId(getCustomModelId()).addStringTag(getKey(), getLabel()).getItem();
    }

    @Override
    public int getCustomModelId() {
        return 0;
    }

    @Override
    public String getLabel() {
        return "Sol";
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

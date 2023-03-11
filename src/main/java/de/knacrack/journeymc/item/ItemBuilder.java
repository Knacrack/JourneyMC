package de.knacrack.journeymc.item;

import de.knacrack.journeymc.utils.ItemAttribute;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemBuilder {

    private ItemMeta meta;

    private ItemStack item;

    ItemBuilder(ItemStack item) {
        this.item = item;
        this.meta = item.getItemMeta();
    }

    ItemBuilder(Material material) {
        this.item = new ItemStack(material);
        this.meta = this.item.getItemMeta();
    }

    ItemBuilder addStringTag(NamespacedKey key, String value) {
        PersistentDataContainer container = this.meta.getPersistentDataContainer();
        if (!this.meta.getPersistentDataContainer().has(key)) {
            this.meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, value);
        }
        return this;
    }

    ItemBuilder addIntegerTag(NamespacedKey key, Integer value) {
        PersistentDataContainer container = this.meta.getPersistentDataContainer();
        if (!this.meta.getPersistentDataContainer().has(key)) {
            this.meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, value);
        }
        return this;
    }

    ItemBuilder addDoubleTag(NamespacedKey key, Double value) {
        PersistentDataContainer container = this.meta.getPersistentDataContainer();
        if (!this.meta.getPersistentDataContainer().has(key)) {
            this.meta.getPersistentDataContainer().set(key, PersistentDataType.DOUBLE, value);
        }
        return this;
    }

    ItemBuilder addContainerTag(NamespacedKey key, Long value) {
        PersistentDataContainer container = this.meta.getPersistentDataContainer();
        if (!this.meta.getPersistentDataContainer().has(key)) {
            this.meta.getPersistentDataContainer().set(key, PersistentDataType.LONG, value);
        }
        return this;
    }

    ItemBuilder addContainerTag(NamespacedKey key, PersistentDataContainer value) {
        PersistentDataContainer container = this.meta.getPersistentDataContainer();
        if (!this.meta.getPersistentDataContainer().has(key)) {
            this.meta.getPersistentDataContainer().set(key, PersistentDataType.TAG_CONTAINER, value);
        }
        return this;
    }

    ItemBuilder addContainerTag(NamespacedKey key, Byte value) {
        PersistentDataContainer container = this.meta.getPersistentDataContainer();
        if (!this.meta.getPersistentDataContainer().has(key)) {
            this.meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, value);
        }
        return this;
    }

    ItemBuilder setLore(String... lore) {
        List<Component> componentList = new ArrayList<>();
        Arrays.stream(lore).forEach(l -> componentList.add(Component.text(l)));
        this.meta.lore(componentList);
        return this;
    }

    ItemBuilder addLore(String lore) {
        this.meta.lore().add(Component.text(lore));
        return this;
    }

    ItemBuilder setUnbreakable(boolean isUnbreakable) {
        this.meta.setUnbreakable(isUnbreakable);
        return this;
    }

    ItemBuilder addItemAttribute(ItemAttribute itemAttribute) {
        this.meta.addAttributeModifier(itemAttribute.getAttribute(), itemAttribute.get());
        return this;
    }

    ItemBuilder addItemAttributes(ItemAttribute... itemAttribute) {
        for (ItemAttribute attribute : itemAttribute) {
            this.addItemAttribute(attribute);
        }
        return this;
    }

    ItemBuilder addEnchantment(Enchantment enchantment, int lvl) {
        this.item.setItemMeta(this.meta);
        this.item.addUnsafeEnchantment(enchantment, lvl);
        this.meta = this.item.getItemMeta();
        return this;
    }

    ItemBuilder setName(String name) {
        this.meta.displayName(Component.text(name));
        return this;
    }

    ItemBuilder setCustomModelId(int id) {
        this.meta.setCustomModelData(id);
        return this;
    }

    ItemBuilder addItemFlags(ItemFlag... itemFlags) {
        this.meta.addItemFlags(itemFlags);
        return this;
    }

    ItemStack getItem() {
        this.item.setItemMeta(this.meta);
        return this.item;
    }
}

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

    public ItemBuilder(Material material) {
        this.item = new ItemStack(material);
        this.meta = this.item.getItemMeta();
    }

    public ItemBuilder addStringTag(NamespacedKey key, String value) {
        PersistentDataContainer container = this.meta.getPersistentDataContainer();
        if (!this.meta.getPersistentDataContainer().has(key)) {
            this.meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, value);
        }
        return this;
    }

    public ItemBuilder addIntegerTag(NamespacedKey key, Integer value) {
        PersistentDataContainer container = this.meta.getPersistentDataContainer();
        if (!this.meta.getPersistentDataContainer().has(key)) {
            this.meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, value);
        }
        return this;
    }

    public ItemBuilder addDoubleTag(NamespacedKey key, Double value) {
        PersistentDataContainer container = this.meta.getPersistentDataContainer();
        if (!this.meta.getPersistentDataContainer().has(key)) {
            this.meta.getPersistentDataContainer().set(key, PersistentDataType.DOUBLE, value);
        }
        return this;
    }

    public ItemBuilder addLongTag(NamespacedKey key, Long value) {
        PersistentDataContainer container = this.meta.getPersistentDataContainer();
        if (!this.meta.getPersistentDataContainer().has(key)) {
            this.meta.getPersistentDataContainer().set(key, PersistentDataType.LONG, value);
        }
        return this;
    }

    public ItemBuilder addContainerTag(NamespacedKey key, PersistentDataContainer value) {
        PersistentDataContainer container = this.meta.getPersistentDataContainer();
        if (!this.meta.getPersistentDataContainer().has(key)) {
            this.meta.getPersistentDataContainer().set(key, PersistentDataType.TAG_CONTAINER, value);
        }
        return this;
    }

    public ItemBuilder addByteTag(NamespacedKey key, Byte value) {
        PersistentDataContainer container = this.meta.getPersistentDataContainer();
        if (!this.meta.getPersistentDataContainer().has(key)) {
            this.meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, value);
        }
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        List<Component> componentList = new ArrayList<>();
        Arrays.stream(lore).forEach(l -> componentList.add(Component.text(l)));
        this.meta.lore(componentList);
        return this;
    }

    public ItemBuilder addLore(String lore) {
        this.meta.lore().add(Component.text(lore));
        return this;
    }

    public ItemBuilder setUnbreakable(boolean isUnbreakable) {
        this.meta.setUnbreakable(isUnbreakable);
        return this;
    }

    public ItemBuilder addItemAttribute(ItemAttribute itemAttribute) {
        this.meta.addAttributeModifier(itemAttribute.getAttribute(), itemAttribute.get());
        return this;
    }

    public ItemBuilder addItemAttributes(ItemAttribute... itemAttribute) {
        for (ItemAttribute attribute : itemAttribute) {
            this.addItemAttribute(attribute);
        }
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int lvl) {
        this.item.setItemMeta(this.meta);
        this.item.addUnsafeEnchantment(enchantment, lvl);
        this.meta = this.item.getItemMeta();
        return this;
    }

    public ItemBuilder setName(String name) {
        this.meta.displayName(Component.text(name));
        return this;
    }

    public ItemBuilder setCustomModelId(int id) {
        this.meta.setCustomModelData(id);
        return this;
    }

    public ItemBuilder addItemFlags(ItemFlag... itemFlags) {
        this.meta.addItemFlags(itemFlags);
        return this;
    }

    public ItemStack getItem() {
        this.item.setItemMeta(this.meta);
        return this.item;
    }
}

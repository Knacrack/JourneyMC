package de.knacrack.journeymc.command.list;

import com.google.common.collect.Lists;
import de.knacrack.journeymc.Main;
import de.knacrack.journeymc.command.Command;
import de.knacrack.journeymc.utils.Messages;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class CombineEnchantments implements Command {
    @Override
    public @Nullable String getPermission() {
        return "journeymc.combineenchantments";
    }

    @Override
    public List<String> getAliases() {
        return Lists.newArrayList("ce");
    }

    @Override
    public String getDescription() {
        return "Combine enchantments.";
    }

    /**
     * FIXME: needs to be refactored
     * @param sender
     * @param args
     */
    @Override
    public void command(CommandSender sender, String[] args) {
        if (sender instanceof Player player && player.hasPermission(getPermission())) {
            ItemStack leftHand = player.getInventory().getItem(EquipmentSlot.OFF_HAND);
            ItemStack rightHand = player.getInventory().getItem(EquipmentSlot.HAND);
            if (Material.AIR.equals(leftHand.getType()) || Material.AIR.equals(rightHand.getType())) {
                sender.sendMessage("Du muss in beiden Händen Items Tragen");
            } else if (Material.ENCHANTED_BOOK.equals(leftHand.getType()) && Material.ENCHANTED_BOOK.equals(rightHand.getType())) {
                EnchantmentStorageMeta leftMeta = (EnchantmentStorageMeta) leftHand.getItemMeta();
                EnchantmentStorageMeta rightMeta = (EnchantmentStorageMeta) rightHand.getItemMeta();

                for (Map.Entry<Enchantment, Integer> entry : leftMeta.getStoredEnchants().entrySet()) {
                    rightMeta.addStoredEnchant(entry.getKey(), entry.getValue(), true);
                }
                rightHand.setItemMeta(rightMeta);
                player.getInventory().setItem(EquipmentSlot.OFF_HAND, new ItemStack(Material.AIR));
                player.getInventory().setItem(EquipmentSlot.HAND, rightHand);
            } else if (Material.ENCHANTED_BOOK.equals(leftHand.getType()) && !Material.AIR.equals(rightHand.getType())) {
                EnchantmentStorageMeta leftMeta = (EnchantmentStorageMeta) leftHand.getItemMeta();
                rightHand.addUnsafeEnchantments(leftMeta.getStoredEnchants());
                player.getInventory().setItem(EquipmentSlot.OFF_HAND, new ItemStack(Material.AIR));
                player.getInventory().setItem(EquipmentSlot.HAND, rightHand);
            } else if (!Material.AIR.equals(leftHand.getType()) && Material.ENCHANTED_BOOK.equals(rightHand.getType())) {
                EnchantmentStorageMeta rightMeta = (EnchantmentStorageMeta) rightHand.getItemMeta();
                for (Map.Entry<Enchantment, Integer> entry : leftHand.getEnchantments().entrySet()) {
                    rightMeta.addStoredEnchant(entry.getKey(), entry.getValue(), true);
                }
                rightHand.setItemMeta(rightMeta);
                player.getInventory().setItem(EquipmentSlot.OFF_HAND, removeAllEnchantments(leftHand));
                player.getInventory().setItem(EquipmentSlot.HAND, rightHand);

            } else if (!Material.AIR.equals(leftHand.getType()) && !Material.AIR.equals(rightHand.getType())) {
                rightHand.addUnsafeEnchantments(leftHand.getEnchantments());
                player.getInventory().setItem(EquipmentSlot.OFF_HAND, removeAllEnchantments(leftHand));
                player.getInventory().setItem(EquipmentSlot.HAND, rightHand);
            } else {
                sender.sendMessage("something went wrong! ¯\\_(ツ)_/¯");
            }


        } else {
            sender.sendMessage(Messages.ERROR.getMessage());
        }
    }

    private ItemStack removeAllEnchantments(ItemStack item) {
        for (Map.Entry<Enchantment, Integer> entry : item.getEnchantments().entrySet()) {
            item.removeEnchantment(entry.getKey());
        }
        return item;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }

    @Override
    public String getLabel() {
        return "CombineEnchantments";
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

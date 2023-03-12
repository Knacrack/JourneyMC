package de.knacrack.journeymc.listener.list;

import de.knacrack.journeymc.Main;
import de.knacrack.journeymc.listener.Listener;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Beehive;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collection;

public class DropBeehive implements Listener {

    @EventHandler
    public void dropBeeHive(BlockBreakEvent event) {
        if ((Material.BEEHIVE.equals(event.getBlock().getType()) || Material.BEE_NEST.equals(event.getBlock().getType())) && event.getPlayer().getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(Enchantment.SILK_TOUCH)) {
            event.setDropItems(false);

            String text = Material.BEEHIVE.equals(event.getBlock().getType()) ? "§fBeehive" : "§fBee Nest";

            Collection<ItemStack> drops = event.getBlock().getDrops(event.getPlayer().getInventory().getItem(EquipmentSlot.HAND));
            ItemStack item = new ArrayList<>(drops).get(0);
            Beehive bees = (Beehive) event.getBlock().getState();
            ItemMeta meta = item.getItemMeta();

            meta.displayName(Component.text(text + " (" + bees.getEntityCount() + ")"));
            item.setItemMeta(meta);
            event.getPlayer().getWorld().dropItem(event.getBlock().getLocation().add(0, 1, 0), item);
        }
    }

    @Override
    public String getLabel() {
        return "DropBeehive";
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

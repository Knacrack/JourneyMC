package de.knacrack.journeymc.item.list;

import de.knacrack.journeymc.Main;
import de.knacrack.journeymc.item.Item;
import de.knacrack.journeymc.item.ItemBuilder;
import de.knacrack.journeymc.listener.Listener;
import de.knacrack.journeymc.utils.Utils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class PortableAnvil implements Item, Listener {

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.PAPER).setName("§dPortable Anvil").addStringTag(getKey(), getLabel()).setCustomModelId(getCustomModelId()).getItem();
    }

    @Override
    public int getCustomModelId() {
        return 0;
    }

    @Override
    public String getLabel() {
        return "PortableAnvil";
    }

    @Override
    public boolean register() {
        return true;
    }

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(Main.getInstance(), getClass().getSimpleName());
    }

    @EventHandler
    public void portableAnvil(PlayerInteractEvent event) {
        if (Utils.hasContainerTag(event.getPlayer().getInventory().getItem(EquipmentSlot.HAND), getKey())) {
            if (event.getAction().isRightClick()) {
                event.getPlayer().openAnvil(null, true);
                event.setCancelled(true);
            }
        }
    }
}

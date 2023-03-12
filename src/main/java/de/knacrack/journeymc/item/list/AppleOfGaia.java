package de.knacrack.journeymc.item.list;

import de.knacrack.journeymc.Main;
import de.knacrack.journeymc.item.Item;
import de.knacrack.journeymc.item.ItemBuilder;
import de.knacrack.journeymc.listener.Listener;
import de.knacrack.journeymc.utils.Utils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class AppleOfGaia implements Item, Listener {
    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.GOLDEN_APPLE).setName("§dApple of Gaia").setCustomModelId(getCustomModelId()).addStringTag(getKey(), getLabel()).getItem();
    }

    @EventHandler
    public void appleOfGaia(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        if (Utils.hasContainerTag(event.getPlayer().getInventory().getItem(EquipmentSlot.HAND), getKey())) {
            player.setFoodLevel(20);
            player.setSaturation(20);
            event.setCancelled(true);
        }
    }

    @Override
    public int getCustomModelId() {
        return 0;
    }

    @Override
    public String getLabel() {
        return "AppleOfGaia";
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

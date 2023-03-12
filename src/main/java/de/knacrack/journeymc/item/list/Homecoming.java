package de.knacrack.journeymc.item.list;

import de.knacrack.journeymc.Main;
import de.knacrack.journeymc.item.Item;
import de.knacrack.journeymc.item.ItemBuilder;
import de.knacrack.journeymc.listener.Listener;
import de.knacrack.journeymc.utils.Utils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class Homecoming implements Item, Listener {

    @EventHandler
    public void homecoming(PlayerInteractEvent event) {
        if(Utils.hasContainerTag(event.getPlayer().getInventory().getItem(EquipmentSlot.HAND), getKey())) {
            if (event.getAction().isRightClick()) {
                if (event.getPlayer().getName().equals("Knacrack") || event.getPlayer().getLevel() >= 3) {
                    Location location = getPlayerBedLocation(event.getPlayer());
                    if(!event.getPlayer().getName().equals("Knacrack")){
                        event.getPlayer().setLevel(event.getPlayer().getLevel() - 3);
                    }
                    event.getPlayer().teleportAsync(location);
                    event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ENDER_PEARL_THROW, 1f, 1f);
                    event.setCancelled(true);
                }
            }
        }
    }

    private static Location getPlayerBedLocation(Player player) {
        Location loc;
        loc = player.getBedSpawnLocation();
        if(loc == null) {
            player.getWorld().getSpawnLocation();
        }
        return loc;
    }

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.PAPER).setCustomModelId(getCustomModelId()).setName("§dHomecoming").addStringTag(getKey(), getLabel()).getItem();
    }

    @Override
    public int getCustomModelId() {
        return 0;
    }

    @Override
    public String getLabel() {
        return "Homecoming";
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

package de.knacrack.journeymc.item.list;

import de.knacrack.journeymc.Main;
import de.knacrack.journeymc.item.Item;
import de.knacrack.journeymc.item.ItemBuilder;
import de.knacrack.journeymc.listener.Listener;
import de.knacrack.journeymc.utils.Utils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class MagicHeart implements Item, Listener {

    @EventHandler
    public void magicHeart(EntityDeathEvent event) {
        if (EntityType.ZOMBIE.equals(event.getEntity().getType()) && CreatureSpawnEvent.SpawnReason.CUSTOM.equals(event.getEntity().getEntitySpawnReason())) {
            if (Utils.RANDOM.nextInt(500) == 0) { // 0,2% Drop-chance
                event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), getItem());
            }
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        for (ItemStack itemStack : event.getDrops()) {
            if (Utils.hasContainerTag(itemStack, getKey())) {
                if (itemStack.getAmount() > 1) {
                    itemStack.setAmount(itemStack.getAmount() - 1);
                } else {
                    event.getEntity().getInventory().remove(itemStack);
                }
                event.setKeepInventory(true);
                event.getDrops().clear();
                break;
            }
        }
    }

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.STICK).setName("§dMagic Heart").setCustomModelId(getCustomModelId()).addStringTag(getKey(), getLabel()).getItem();
    }

    @Override
    public int getCustomModelId() {
        return 1;
    }

    @Override
    public String getLabel() {
        return "MagicHeart";
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

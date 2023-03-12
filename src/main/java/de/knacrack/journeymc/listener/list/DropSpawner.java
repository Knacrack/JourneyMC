package de.knacrack.journeymc.listener.list;

import de.knacrack.journeymc.Main;
import de.knacrack.journeymc.listener.Listener;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

public class DropSpawner implements Listener {

    @EventHandler
    public void dropSpawner(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if(Material.SPAWNER.equals(event.getBlock().getType()) && player.getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(Enchantment.SILK_TOUCH)) {
            if(!event.getPlayer().isOp()) {
                player.getInventory().setItem(EquipmentSlot.HAND, new ItemStack(Material.AIR));
            }
            event.setExpToDrop(0);
            ItemStack item = new ItemStack(Material.SPAWNER, 1);
            BlockStateMeta meta = (BlockStateMeta) item.getItemMeta();
            CreatureSpawner cs = (CreatureSpawner) meta.getBlockState();
            EntityType type = ((CreatureSpawner) event.getBlock().getState()).getSpawnedType();

            cs.setSpawnedType(type);
            meta.setBlockState(cs);
            meta.displayName(Component.text( "§c§l" + " Spawner (" + type.name().toLowerCase() + ")"));
            item.setItemMeta(meta);
            player.getWorld().dropItem(event.getBlock().getLocation().add(0,1,0), item);
        }
    }

    @Override
    public String getLabel() {
        return "DropSpawner";
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

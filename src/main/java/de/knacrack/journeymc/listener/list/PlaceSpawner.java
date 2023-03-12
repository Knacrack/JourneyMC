package de.knacrack.journeymc.listener.list;

import de.knacrack.journeymc.Main;
import de.knacrack.journeymc.listener.Listener;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

public class PlaceSpawner implements Listener {

    @EventHandler
    public void onPlaceSpawner(BlockPlaceEvent event) {
        if (Material.SPAWNER.equals(event.getBlock().getType())) {
            BlockState blockState = event.getBlockPlaced().getState();
            CreatureSpawner spawner = ((CreatureSpawner) blockState);
            spawner.setSpawnedType(getEntityType(event.getItemInHand()));
            blockState.update();
        }
    }

    private EntityType getEntityType(ItemStack item) {
        BlockStateMeta meta = (BlockStateMeta) item.getItemMeta();
        CreatureSpawner spawner = (CreatureSpawner) meta.getBlockState();
        return spawner.getSpawnedType();
    }

    @Override
    public String getLabel() {
        return "PlaceSpawner";
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

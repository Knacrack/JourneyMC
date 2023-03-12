package de.knacrack.journeymc.listener.list;

import de.knacrack.journeymc.Main;
import de.knacrack.journeymc.listener.Listener;
import de.knacrack.journeymc.mob.list.FranzSkeleton;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_19_R2.CraftWorld;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.Random;

public class SpawnFranz implements Listener {

    @EventHandler
    public void spawnFranz(CreatureSpawnEvent event) {
        if(EntityType.SKELETON.equals(event.getEntityType()) && CreatureSpawnEvent.SpawnReason.NATURAL == event.getSpawnReason()) {
            if (new Random().nextDouble() <= 0.2) {
                CraftWorld world = ((CraftWorld) event.getLocation().getWorld());
                world.addEntity(new FranzSkeleton(event.getLocation()), CreatureSpawnEvent.SpawnReason.CUSTOM);
                event.setCancelled(true);
            }
        }
    }

    @Override
    public String getLabel() {
        return "SpawnFranz";
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

package de.knacrack.journeymc.listener.list;

import de.knacrack.journeymc.Main;
import de.knacrack.journeymc.listener.Listener;
import de.knacrack.journeymc.mob.list.OlafZombie;
import de.knacrack.journeymc.utils.Utils;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class SpawnOlaf implements Listener {

    @EventHandler
    public void spawnOlaf(CreatureSpawnEvent event) {
        if (EntityType.ZOMBIE.equals(event.getEntityType()) && CreatureSpawnEvent.SpawnReason.NATURAL == event.getSpawnReason()) {
            if (Utils.RANDOM.nextDouble() <= 0.1) {
                new OlafZombie(event.getLocation()).spawn();
                event.setCancelled(true);
            }
        }
    }

    @Override
    public String getLabel() {
        return "SpawnOlaf";
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

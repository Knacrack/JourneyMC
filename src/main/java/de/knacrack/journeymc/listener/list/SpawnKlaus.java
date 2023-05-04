package de.knacrack.journeymc.listener.list;

import de.knacrack.journeymc.Main;
import de.knacrack.journeymc.listener.Listener;
import de.knacrack.journeymc.mob.list.KlausZombie;
import de.knacrack.journeymc.utils.Utils;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.Random;

public class SpawnKlaus implements Listener {

    @EventHandler
    public void spawnKlaus(CreatureSpawnEvent event) {
        if (EntityType.ZOMBIE.equals(event.getEntityType()) && CreatureSpawnEvent.SpawnReason.NATURAL == event.getSpawnReason()) {
            if (Utils.RANDOM.nextDouble() <= 0.1) {
                new KlausZombie(event.getLocation()).spawn();
                event.setCancelled(true);
            }
        }
    }

    @Override
    public String getLabel() {
        return "SpawnKlaus";
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

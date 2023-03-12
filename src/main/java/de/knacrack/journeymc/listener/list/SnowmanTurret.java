package de.knacrack.journeymc.listener.list;

import de.knacrack.journeymc.Main;
import de.knacrack.journeymc.listener.Listener;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Snowman;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;

public class SnowmanTurret implements Listener {

    @EventHandler
    public void SnowmanTurretDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() == EntityType.SNOWBALL) {
            ProjectileSource shooter = ((Snowball) event.getDamager()).getShooter();
            if (shooter instanceof Snowman snowman && snowman.getCustomName() != null && snowman.getCustomName().contains("Turret")
                    && !EntityType.SNOWMAN.equals(event.getEntity().getType())) {
                event.setDamage(10.00);
            }
        }

    }

    @Override
    public String getLabel() {
        return "SnowmanTurret";
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

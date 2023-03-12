package de.knacrack.journeymc.listener.list;

import de.knacrack.journeymc.Main;
import de.knacrack.journeymc.listener.Listener;
import de.knacrack.journeymc.utils.Utils;
import org.bukkit.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamage implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if (event.getFinalDamage() != 0 && event instanceof LivingEntity) {
            Location location = entity.getLocation().add(0, 0.5, 0);
            double speed = 0.025;

            if (Utils.isSkeleton(entity.getType())) {
                spawnParticle(location, 14, 0.2, 1, 0.2, speed, Material.BONE_BLOCK);
            } else if (EntityType.SLIME.equals(entity.getType())) {
                spawnParticle(location, 14 * ((Slime) entity).getSize(), 0.2 * ((Slime) entity).getSize(), 0.2 * ((Slime) entity).getSize(), 0.2 * ((Slime) entity).getSize(), speed, Material.SLIME_BLOCK);
            } else if (EntityType.CREEPER.equals(entity.getType())) {
                spawnParticle(location, 14, 0.2, 1, 0.2, speed, Material.TNT);
            } else if (EntityType.ENDERMAN.equals(entity.getType())) {
                spawnParticle(location, 14, 0.2, 1, 0.2, speed, Material.PURPLE_SHULKER_BOX);
            } else {
                spawnParticle(location, 14, 0.2, 1, 0.2, speed, Material.REDSTONE_BLOCK);
            }
        }
    }

    private void spawnParticle(Location location, int amount, double offsetX, double offsetY, double offsetZ, double speed, Material material) {
        String blockDataString = "minecraft:" + material.name().toLowerCase();
        BlockData blockData = Bukkit.getServer().createBlockData(blockDataString);
        location.getWorld().spawnParticle(Particle.BLOCK_CRACK, location, amount, offsetX, offsetY, offsetZ, speed, blockData);
    }

    @Override
    public String getLabel() {
        return "EntityDamage";
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

package de.knacrack.journeymc.item.list;

import de.knacrack.journeymc.Main;
import de.knacrack.journeymc.item.Item;
import de.knacrack.journeymc.item.ItemBuilder;
import de.knacrack.journeymc.listener.Listener;
import de.knacrack.journeymc.utils.Utils;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Musket implements Item, Listener {

    @EventHandler
    public void onCrossbowCharge(EntityShootBowEvent event) {
        LivingEntity entity = event.getEntity();
        ItemStack crossbow = event.getBow();

        if (crossbow != null && Utils.hasContainerTag(crossbow, getKey())) {

            event.setCancelled(true);

            Location location = entity.getEyeLocation().clone();
            location.add(location.getDirection().normalize().multiply(1.5));
            location.getWorld().spawnParticle(Particle.SMOKE_NORMAL, location, 15, 0.01, 0.0, 0.01, 0.02, null);

            if (entity instanceof Player player) {
                player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 0.2f, 1f);

                List<Entity> nearbyEntities = player.getNearbyEntities(35, 35, 35).stream().toList();
                Entity entity1 = Utils.getTarget(player, 35f, 0.5f, 1.5f, true, nearbyEntities);
                if (entity1 instanceof LivingEntity livingEntity) {
                    double damage = 10D;

                    livingEntity.setLastDamageCause(new EntityDamageByEntityEvent(entity1, entity, EntityDamageEvent.DamageCause.ENTITY_ATTACK, damage));
                    livingEntity.damage(damage);
                }
            }
        }
    }

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.CROSSBOW).setCustomModelId(getCustomModelId()).setName("§fMusket").addStringTag(getKey(), getLabel()).getItem();
    }

    @Override
    public int getCustomModelId() {
        return 0;
    }

    @Override
    public String getLabel() {
        return "Musket";
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

package de.knacrack.journeymc.mob.list;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Skeleton;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_19_R2.CraftServer;
import org.bukkit.craftbukkit.v1_19_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_19_R2.entity.CraftSkeleton;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FranzSkeleton extends CraftSkeleton {

    private Location l;

    public FranzSkeleton(Location loc) {
        super((CraftServer) Bukkit.getServer(), new Skeleton(EntityType.SKELETON, ((CraftWorld) loc.getWorld()).getHandle()));
        l = loc;
        setMaxHealth(50f);
        getLocation(loc);
        setHealth(50f);
        getEquipment().setHelmet(new ItemStack(Material.IRON_HELMET));
        getEquipment().setDropChance(EquipmentSlot.HEAD, 0f);
        addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2, false, false));
        addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 1, false, false));
    }

    public void spawn() {
        spawnAt(l, CreatureSpawnEvent.SpawnReason.CUSTOM);
    }

}
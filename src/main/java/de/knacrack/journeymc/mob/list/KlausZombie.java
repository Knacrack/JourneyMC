package de.knacrack.journeymc.mob.list;

import net.minecraft.world.entity.monster.Zombie;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_19_R2.CraftServer;
import org.bukkit.craftbukkit.v1_19_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_19_R2.entity.CraftZombie;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class KlausZombie extends CraftZombie {

    private Location l;

    public KlausZombie(Location loc) {
        super((CraftServer) Bukkit.getServer(), new Zombie(((CraftWorld) loc.getWorld()).getHandle()));
        l = loc;
        setCanBreakDoors(true);
        setMaxHealth(50d);
        setHealth(50d);
        getEquipment().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
        getEquipment().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
        getEquipment().setBoots(new ItemStack(Material.IRON_BOOTS));
        getEquipment().setItemInMainHand(new ItemStack(Material.IRON_SWORD));
        getEquipment().setItemInOffHand(new ItemStack(Material.SHIELD));
        getEquipment().setDropChance(EquipmentSlot.CHEST, 0f);
        getEquipment().setDropChance(EquipmentSlot.LEGS, 0f);
        getEquipment().setDropChance(EquipmentSlot.FEET, 0f);
        getEquipment().setDropChance(EquipmentSlot.HAND, 0f);
        getEquipment().setDropChance(EquipmentSlot.OFF_HAND, 0f);
    }

    public void spawn() {
        spawnAt(l, CreatureSpawnEvent.SpawnReason.CUSTOM);
    }
}
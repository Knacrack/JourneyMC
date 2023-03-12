package de.knacrack.journeymc.mob.list;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Zombie;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_19_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_19_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class KlausZombie extends Zombie {
    public KlausZombie(Location loc) {
        super(EntityType.ZOMBIE, ((CraftWorld) loc.getWorld()).getHandle());
        this.setPos(loc.getX(), loc.getY(), loc.getZ());
        this.setCanBreakDoors(true);
        this.setItemSlot(EquipmentSlot.CHEST, CraftItemStack.asNMSCopy(new ItemStack(Material.IRON_CHESTPLATE)));
        this.setItemSlot(EquipmentSlot.LEGS, CraftItemStack.asNMSCopy(new ItemStack(Material.IRON_LEGGINGS)));
        this.setItemSlot(EquipmentSlot.FEET, CraftItemStack.asNMSCopy(new ItemStack(Material.IRON_BOOTS)));
        this.setItemSlot(EquipmentSlot.MAINHAND, CraftItemStack.asNMSCopy(new ItemStack(Material.IRON_SWORD)));
        this.setItemSlot(EquipmentSlot.OFFHAND, CraftItemStack.asNMSCopy(new ItemStack(Material.SHIELD)));
        this.setUUID(UUID.randomUUID());
    }
}

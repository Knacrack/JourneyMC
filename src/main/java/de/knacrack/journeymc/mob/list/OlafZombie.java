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

public class OlafZombie extends Zombie {
    public OlafZombie(Location loc) {
        super(EntityType.ZOMBIE, ((CraftWorld) loc.getWorld()).getHandle());
        this.setPos(loc.getX(), loc.getY(), loc.getZ());
        this.setCanBreakDoors(true);
        this.setItemSlot(EquipmentSlot.HEAD, CraftItemStack.asNMSCopy(new ItemStack(Material.PUMPKIN)));
        this.setItemSlot(EquipmentSlot.MAINHAND, CraftItemStack.asNMSCopy(new ItemStack(Material.IRON_AXE)));
        this.setItemSlot(EquipmentSlot.OFFHAND, CraftItemStack.asNMSCopy(new ItemStack(Material.IRON_AXE)));
        this.setUUID(UUID.randomUUID());
    }

}
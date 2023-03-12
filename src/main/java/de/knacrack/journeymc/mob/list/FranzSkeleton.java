package de.knacrack.journeymc.mob.list;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Skeleton;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_19_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_19_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.UUID;

public class FranzSkeleton extends Skeleton {
    public FranzSkeleton(Location loc) {
        super(EntityType.SKELETON, ((CraftWorld) loc.getWorld()).getHandle());
        this.setPos(loc.getX(), loc.getY(), loc.getZ());
        this.setAggressive(true);
        this.setItemSlot(EquipmentSlot.HEAD, CraftItemStack.asNMSCopy(new ItemStack(Material.IRON_HELMET)));
        this.drops = new ArrayList<>();
        this.forceDrops = false;
        this.addEffect(new MobEffectInstance(net.minecraft.world.effect.MobEffect.byId(1), Integer.MAX_VALUE, 2));
        this.addEffect(new MobEffectInstance(net.minecraft.world.effect.MobEffect.byId(11), Integer.MAX_VALUE, 2));
        this.addEffect(new MobEffectInstance(net.minecraft.world.effect.MobEffect.byId(8), Integer.MAX_VALUE, 1));
        this.setUUID(UUID.randomUUID());
    }
}
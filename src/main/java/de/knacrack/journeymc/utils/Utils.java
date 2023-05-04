package de.knacrack.journeymc.utils;

import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

public class Utils {

    public static Random RANDOM = new Random();

    public static void closePlayersInventory(Collection<? extends Player> players) {
        players.forEach(Player::closeInventory);
    }

    public static boolean hasContainerTag(ItemStack itemStack, NamespacedKey key) {
        boolean out = itemStack.getItemMeta() != null;
        out = out && itemStack.getItemMeta().getPersistentDataContainer().has(key);
        return out;
    }

    public static ItemStack addContainerTag(ItemStack itemStack, NamespacedKey key, String value) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (!itemMeta.getPersistentDataContainer().has(key)) {
            itemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, value);
            itemStack.setItemMeta(itemMeta);
        }
        return itemStack;
    }

    public static boolean isTool(Material material) {
        if (material == null) return false;

        String materialName = material.name();
        if (materialName.contains("_AXE") || materialName.contains("_PICKAXE") || materialName.contains("_SWORD") || materialName.contains("_HOE") || materialName.contains("_SHOVEL"))
            return true;
        return false;
    }

    public static boolean isArmor(Material material) {
        if (material == null) return false;

        String materialName = material.name();
        return materialName.contains("_CHESTPLATE") || materialName.contains("_LEGGINGS") || materialName.contains("_BOOTS") || materialName.contains("_HELMET");
    }

    public static boolean isZombie(EntityType entityType) {
        return entityType == EntityType.ZOMBIE || entityType == EntityType.HUSK || entityType == EntityType.DROWNED || entityType == EntityType.ZOGLIN || entityType == EntityType.ZOMBIE_HORSE || entityType == EntityType.ZOMBIE_VILLAGER;
    }

    public static boolean isUndead(EntityType entityType) {
        return entityType == EntityType.ZOMBIE || entityType == EntityType.HUSK || entityType == EntityType.DROWNED || entityType == EntityType.ZOGLIN || entityType == EntityType.ZOMBIE_HORSE || entityType == EntityType.ZOMBIE_VILLAGER || entityType == EntityType.SKELETON || entityType == EntityType.WITHER_SKELETON || entityType == EntityType.WITHER || entityType == EntityType.SKELETON_HORSE;
    }

    public static boolean isSkeleton(EntityType entityType) {
        return entityType == EntityType.SKELETON || entityType == EntityType.WITHER_SKELETON || entityType == EntityType.WITHER || entityType == EntityType.SKELETON_HORSE;
    }

    public static double round(final double value, final int digits) {
        return Math.round(Math.pow(10.0, digits) * value) / Math.pow(10.0, digits);
    }

    public static boolean containsUnicodes(@Nonnull String message) {
        final String a = message;
        message = message.replaceAll("[^a-zA-Z.,#;+|/&%!?=(): 1234567890הצ\"*-@€$]", "");
        return !a.equals(message);
    }

    public static boolean isInventoryFull(@Nonnull Inventory inventory) {
        return inventory.firstEmpty() == -1;
    }

    public static boolean canPickup(@Nonnull Inventory inventory, @Nonnull ItemStack itemStack) {
        return inventory.first(itemStack) == -1;
    }

    public static final List<String> getOnlinePlayers(String argument) {
        List<Player> players = Bukkit.getOnlinePlayers().stream().filter(player -> player.getName().startsWith(argument)).collect(Collectors.toList());
        List<String> names = Lists.newArrayList();

        if (!players.isEmpty()) {
            players.forEach(player -> names.add(player.getName()));
        }
        return names;
    }

    /**
     * FIXME: Dunno what this is dooooing
     *
     * @param player
     * @return
     */
    public static ItemStack getSkullOfPlayer(Player player) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
        String s = player.getPlayerProfile().getTextures().getSkin().toString();
        if (!s.isEmpty()) {
            SkullMeta headMeta = (SkullMeta) head.getItemMeta();
            GameProfile profile = new GameProfile(UUID.fromString("f6f28918-af6d-4995-8fa4-8055c8c4dd40"), null);
            byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", s).getBytes());
            profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
            Field profileField = null;
            try {
                profileField = headMeta.getClass().getDeclaredField("profile");
                profileField.setAccessible(true);
                profileField.set(headMeta, profile);
            } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
                e1.printStackTrace();
            }
            headMeta.displayName(Component.text("§e" + player.getName()));
            head.setItemMeta(headMeta);
        }
        return head;
    }

    /**
     * Try to find an entity on player's eyesight.
     *
     * @param player:           target player (who should is performing this action?)
     * @param range:            range to search for entities | [range ∈ �? | 0.0 < range <= 1.0]
     * @param offset:           This variable controls the fine-tuning of searching for entities. Lower values means higher accuracy and more performance cost. [offset ∈ �? | 0.0 < offset <= 1.0]
     * @param tolerance:        How large should be the tolerance spectrum of failing to successfully hit the target? The tolerance should be higher than 0, but 0 is a valid input. [tolerance ∈ �? | tolerance >= 0.0]
     * @param firstInTolerance: Should the first entity hit within tolerance be returned or the closest?
     * @param nearbyEntities:   list of entities to check
     * @return
     */
    public static Entity getTarget(Player player, float range, float offset, float tolerance, boolean firstInTolerance, List<Entity> nearbyEntities) {
        // handle invalid inputs
        if (range <= 0f) {
            throw new IllegalArgumentException("range must be larger than 0");
        }

        if (offset == 0f) {
            throw new IllegalArgumentException("offset can not be 0");
        }

        if (tolerance < 0.0f) {
            throw new IllegalArgumentException("tolerance can not be lower than 0");
        }

        if (offset > 1f) {
            throw new IllegalArgumentException("offset can not be larger than 1");
        }

        // check if no entities are given
        if (nearbyEntities == null) {
            return null;
        }

        if (nearbyEntities.isEmpty()) {
            return null;
        }

        // handle negative offset-input
        offset = Math.abs(offset);
        double minDistance = Double.MAX_VALUE;

        Entity target = null;
        Vector vector = player.getEyeLocation().getDirection().clone(), additiveVector = player.getEyeLocation().getDirection().multiply(offset).clone();

        while (range > 0) {
            range -= offset;
            vector.add(additiveVector);

            player.getWorld().spawnParticle(Particle.FLAME, vector.getX(), vector.getY(), vector.getZ(), 1, 0.0, 0.0, 0.0, 0.0, null);

            for (Entity entity : nearbyEntities) {
                Location location = vector.toLocation(player.getWorld());
                location.add(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());

                double distance = entity.getLocation().distance(location);
                if (distance < minDistance) {
                    minDistance = distance;
                    target = entity;

                    if (firstInTolerance) {
                        if (minDistance <= tolerance) {
                            return target;
                        }
                    }
                }
            }
        }
        if (minDistance > tolerance) {
            target = null;
        }
        return target;
    }

    public static Component getGradient(Color start, Color end, String text) {
        int value = start.asRGB();
        int step = (value - end.asRGB()) / text.trim().length();

        char[] chars = text.trim().toCharArray();
        Component output = Component.text(chars[0]).color(TextColor.color(start.asRGB()));

        for (int i = 1; i <= chars.length; i++) {
            value += step;
            output.append(Component.text(chars[i]).color(TextColor.color(value)));
        }

        return output;
    }
}

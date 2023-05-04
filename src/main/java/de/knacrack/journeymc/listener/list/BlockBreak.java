package de.knacrack.journeymc.listener.list;

import com.google.common.collect.Lists;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import java.util.*;

public class BlockBreak {

    public static final List<Material> wood = Lists.newArrayList(Material.OAK_LOG, Material.SPRUCE_LOG, Material.MANGROVE_LOG, Material.BIRCH_LOG, Material.DARK_OAK_LOG, Material.JUNGLE_LOG, Material.ACACIA_LOG);

    public static final List<Material> ore = Lists.newArrayList(Material.COAL_ORE, Material.IRON_ORE, Material.COPPER_ORE, Material.GOLD_ORE, Material.DIAMOND_ORE, Material.DEEPSLATE_COAL_ORE, Material.DEEPSLATE_COPPER_ORE, Material.DEEPSLATE_IRON_ORE, Material.DEEPSLATE_DIAMOND_ORE, Material.EMERALD_ORE, Material.DEEPSLATE_EMERALD_ORE, Material.DEEPSLATE_GOLD_ORE);


    public static void breakBlocks(Player player, List<Material> materials, Location location) {
        Material type = location.getBlock().getType();
        if (materials.contains(type)) {

            Set<Block> blocksToBreak = new HashSet<>();
            int count = 0;
            int x = location.getBlockX();
            int y = location.getBlockY();
            int z = location.getBlockZ();

            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    for (int dz = -1; dz <= 1; dz++) {
                        Block block = location.getWorld().getBlockAt(x + dx, y + dy, z + dz);
                        if (type == block.getType()) {
                            if (count < 16) {
                                blocksToBreak.add(block);
                                count++;
                            } else {
                                break;
                            }
                        }
                    }
                    if (count >= 16) {
                        break;
                    }
                }
                if (count >= 16) {
                    break;
                }
            }

            boolean soundPlayed = false;
            for (Block block : blocksToBreak) {
                block.breakNaturally();
                if (!soundPlayed) {
                    player.playSound(block.getLocation(), Sound.BLOCK_STONE_BREAK, 1, 1);
                    soundPlayed = true;
                }
            }
        }
    }

    public static void breakBlocks(Player player, List<Material> materials, Block block) {
        Material type = block.getType();
        if (materials.contains(type)) {

            Set<Block> blocksToBreak = new HashSet<>();
            int count = 0;
            int x = block.getX();
            int y = block.getY();
            int z = block.getZ();

            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    for (int dz = -1; dz <= 1; dz++) {
                        Block blockInLoop = block.getWorld().getBlockAt(x + dx, y + dy, z + dz);
                        if (type == blockInLoop.getType()) {
                            if (count < 16) {
                                blocksToBreak.add(blockInLoop);
                                count++;
                            } else {
                                break;
                            }
                        }
                    }
                    if (count >= 16) {
                        break;
                    }
                }
                if (count >= 16) {
                    break;
                }
            }

            boolean soundPlayed = false;
            for (Block b : blocksToBreak) {
                if (b.getType() == block.getType()) {
                    count = breaking(player, materials, b, count);
                }
                if (b.breakNaturally()) {
                    if (!soundPlayed) {
                        player.playSound(b.getLocation(), Sound.BLOCK_STONE_BREAK, 1, 1);
                        soundPlayed = true;
                    }
                }
            }
        }
    }

    private static int breaking(Player player, List<Material> materials, Block block, int count) {
        Material type = block.getType();
        Set<Block> blocksToBreak = new HashSet<>();
        int x = block.getX();
        int y = block.getY();
        int z = block.getZ();

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dz = -1; dz <= 1; dz++) {
                    Block blockInLoop = block.getWorld().getBlockAt(x + dx, y + dy, z + dz);
                    if (type == blockInLoop.getType()) {
                        if (count < 16) {
                            blocksToBreak.add(blockInLoop);
                            count++;
                        } else {
                            return count;
                        }
                    }
                }
            }
        }

        boolean soundPlayed = false;
        for (Block b : blocksToBreak) {
            b.breakNaturally();
            if (!soundPlayed) {
                player.playSound(b.getLocation(), Sound.BLOCK_STONE_BREAK, 1, 1);
                soundPlayed = true;
            }
        }

        for (Block b : blocksToBreak) {
            count = breaking(player, materials, b, count);
        }
        return count;
    }


    public static void chopTree(Player player, List<Material> list, Block block) {
        Material material = block.getType();
        if (list.contains(material)) {
            Set<Block> blocksToBreak = new HashSet<>();
            int count = 0;
            chopTreeRecursive(player, block.getType(), block, blocksToBreak, count);
            boolean soundPlayed = false;
            for (Block b : blocksToBreak) {
                b.breakNaturally();
                if (!soundPlayed) {
                    player.playSound(b.getLocation(), Sound.BLOCK_WOOD_BREAK, 1, 1);
                    soundPlayed = true;
                }
            }
        }
    }

    private static void chopTreeRecursive(Player player, Material material, Block block, Set<Block> blocksToBreak, int count) {
        if (count >= 16) {
            return;
        }
        if (block.getType() != material) {
            return;
        }
        blocksToBreak.add(block);
        count++;
        if (count >= 16) {
            return;
        }
        chopTreeRecursive(player, material, block.getRelative(BlockFace.NORTH), blocksToBreak, count);
        chopTreeRecursive(player, material, block.getRelative(BlockFace.SOUTH), blocksToBreak, count);
        chopTreeRecursive(player, material, block.getRelative(BlockFace.EAST), blocksToBreak, count);
        chopTreeRecursive(player, material, block.getRelative(BlockFace.WEST), blocksToBreak, count);
        chopTreeRecursive(player, material, block.getRelative(BlockFace.UP), blocksToBreak, count);
        chopTreeRecursive(player, material, block.getRelative(BlockFace.DOWN), blocksToBreak, count);
    }

    public static Set<Location> breakTree(Player player, Set<Integer> materials, Block block) {
        Set<Location> brokenBlocks = new HashSet<>();
        if (!materials.contains(block.getType().getId())) {
            return brokenBlocks;
        }
        Queue<Block> queue = new LinkedList<>();
        queue.add(block);
        int count = 0;
        while (!queue.isEmpty() && count < 16) {
            Block currentBlock = queue.poll();
            if (!brokenBlocks.contains(currentBlock.getLocation())) {
                brokenBlocks.add(currentBlock.getLocation());
                currentBlock.breakNaturally();
                player.playSound(currentBlock.getLocation(), Sound.BLOCK_STONE_BREAK, 1, 1);
                count++;
                if (count >= 16) {
                    break;
                }
            }
            if (currentBlock.getType().name().endsWith("_LOG") && currentBlock.getY() > block.getY() && count < 16) {
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        for (int dz = -1; dz <= 1; dz++) {
                            Block blockInLoop = currentBlock.getWorld().getBlockAt(currentBlock.getX() + dx, currentBlock.getY() + dy, currentBlock.getZ() + dz);
                            if (!brokenBlocks.contains(blockInLoop.getLocation()) && materials.contains(blockInLoop.getType().getId())) {
                                queue.add(blockInLoop);
                            }
                        }
                    }
                }
            }
        }
        return brokenBlocks;
    }
}

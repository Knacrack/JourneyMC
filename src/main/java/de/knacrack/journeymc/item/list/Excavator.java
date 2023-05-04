package de.knacrack.journeymc.item.list;

import com.google.common.collect.Lists;
import de.knacrack.journeymc.Main;
import de.knacrack.journeymc.item.Item;
import de.knacrack.journeymc.item.ItemBuilder;
import de.knacrack.journeymc.listener.Listener;
import de.knacrack.journeymc.utils.Utils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Excavator implements Item, Listener {

    private static final List<Material> oreMaterials = Lists.newArrayList(Material.COAL_ORE, Material.COPPER_ORE, Material.IRON_ORE, Material.GOLD_ORE, Material.DIAMOND_ORE, Material.EMERALD_ORE, Material.REDSTONE_ORE, Material.LAPIS_ORE);

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.DIAMOND_PICKAXE).setName("§6" + getLabel()).addStringTag(getKey(), "ore_excavator").setCustomModelId(getCustomModelId()).getItem();
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        ItemStack mainHand = player.getInventory().getItemInMainHand();


        // Check if the broken block is an ore block
        if (!(oreMaterials.contains(block.getType()) && Utils.hasContainerTag(player.getInventory().getItemInMainHand(), getKey()))) {
            return;
        }

        // Create a list to store the blocks that need to be broken
        List<Block> blocksToBreak = new ArrayList<>();
        blocksToBreak.add(block);

        // Recursively add adjacent ore blocks to the list, up to a maximum of 16
        findAdjacentOreBlocks(block, blocksToBreak, 16);

        // Break all the ore blocks in the list
        for (Block oreBlock : blocksToBreak) {
            oreBlock.breakNaturally(player.getInventory().getItemInMainHand());
        }

        // Add experience to the player for breaking the blocks
        player.giveExp(event.getExpToDrop());

        // Cancel the original block break event
        event.setCancelled(true);
    }

    private void findAdjacentOreBlocks(Block block, List<Block> blocksToBreak, int maxBlocks) {
        if (blocksToBreak.size() >= maxBlocks) {
            return;
        }

        // Check each adjacent block to see if it's an ore block
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    Block adjacentBlock = block.getRelative(x, y, z);
                    if (oreMaterials.contains(adjacentBlock.getType()) && !blocksToBreak.contains(adjacentBlock)) {
                        blocksToBreak.add(adjacentBlock);
                        findAdjacentOreBlocks(adjacentBlock, blocksToBreak, maxBlocks);
                    }
                }
            }
        }
    }

    @Override
    public int getCustomModelId() {
        return 0;
    }

    @Override
    public String getLabel() {
        return "Excavator";
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

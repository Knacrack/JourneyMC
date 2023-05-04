package de.knacrack.journeymc.item.list;

import com.google.common.collect.Lists;
import de.knacrack.journeymc.Main;
import de.knacrack.journeymc.item.Item;
import de.knacrack.journeymc.item.ItemBuilder;
import de.knacrack.journeymc.listener.Listener;
import de.knacrack.journeymc.listener.list.BlockBreak;
import de.knacrack.journeymc.utils.Utils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.TreeSpecies;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Lumberjack implements Item, Listener {

    private static final int MAX_BLOCKS = 16;
    private static final long DELAY_TICKS = 1L;

    private static final List<Material> oreMaterials = Lists.newArrayList(Material.OAK_LOG, Material.ACACIA_LOG, Material.JUNGLE_LOG, Material.DARK_OAK_LOG, Material.BIRCH_LOG, Material.MANGROVE_LOG, Material.SPRUCE_LOG);


    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.IRON_AXE).addStringTag(getKey(), "Lumberjack").setName("§6§l" + getLabel()).setCustomModelId(getCustomModelId()).getItem();
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        ItemStack mainHand = player.getInventory().getItemInMainHand();
        if (Utils.hasContainerTag(mainHand, getKey())) {
            BlockBreak.chopTree(player, BlockBreak.wood, event.getBlock());
            //BlockBreak.breakBlocks(player, BlockBreak.wood, event.getBlock());
            event.setCancelled(true);
        }


        /*Player player = event.getPlayer();
        Block block = event.getBlock();

        if (block.getType() != Material.LEGACY_LOG && block.getType() != Material.LEGACY_LOG_2) {
            return;
        }

        // Prüfen, ob der Spieler eine Axt in der Hand hat
        ItemStack mainHand = player.getInventory().getItemInMainHand();
        if (!Utils.hasContainerTag(mainHand, getKey())) {
            return;
        }

        event.setCancelled(true); // Event abbrechen, damit der Block nicht direkt zerstört wird

        // Baum fällen asynchron ausführen, um Lag zu vermeiden
        new BukkitRunnable() {
            int count = 0;
            TreeSpecies species = TreeSpecies.getByData(block.getData());

            @Override
            public void run() {
                count += cutTree(block, species);
                for (Block neighbor : getAdjacentLogs(block)) {
                    count += cutTree(neighbor, species);
                    if (count >= MAX_BLOCKS) {
                        break;
                    }
                }
                if (count > 0) {
                    player.sendMessage("Du hast " + count + " Stämme abgebaut!");
                }
                player.getWorld().playEffect(block.getLocation(), Effect.LAVA_INTERACT, 0); // Feedback-Effekt
                player.getWorld().playSound(block.getLocation(), mainHand.getType().name().startsWith("GOLD") ? "random.levelup" : "random.break", 1.0F, 1.0F); // Feedback-Sound
            }
        }.runTaskLater(Main.getInstance(), DELAY_TICKS);*/
    }

    // rekursive Methode zum Fällen des Baums
    private int cutTree(Block block, TreeSpecies species) {
        if (!oreMaterials.contains(block.getType())) {
            return 0;
        }
        block.breakNaturally();
        int count = 1;
        for (Block neighbor : getAdjacentLogs(block)) {
            count += cutTree(neighbor, species);
            if (count >= MAX_BLOCKS) {
                break;
            }
        }
        return count;
    }

    // Hilfsmethode, um benachbarte Baumstämme zu finden
    private Block[] getAdjacentLogs(Block block) {
        Block[] neighbors = new Block[6];
        neighbors[0] = block.getRelative(1, 0, 0);
        neighbors[1] = block.getRelative(-1, 0, 0);
        neighbors[2] = block.getRelative(0, 1, 0);
        neighbors[3] = block.getRelative(0, -1, 0);
        neighbors[4] = block.getRelative(0, 0, 1);
        neighbors[5] = block.getRelative(0, 0, -1);
        int count = 0;
        for (Block neighbor : neighbors) {
            if (oreMaterials.contains(neighbor.getType())) {
                count++;
            }
        }
        Block[] result = new Block[count];
        count = 0;
        for (Block neighbor : neighbors) {
            if (oreMaterials.contains(neighbor.getType())) {
                result[count++] = neighbor;
            }
        }
        return result;
    }

    @Override
    public int getCustomModelId() {
        return 0;
    }

    @Override
    public String getLabel() {
        return "Lumberjack";
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

package de.knacrack.journeymc.listener.list;

import de.knacrack.journeymc.Main;
import de.knacrack.journeymc.listener.Listener;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.world.PortalCreateEvent;

import java.util.List;

public class CreatePortal implements Listener {

    @EventHandler
    public void onCreatePortal(PortalCreateEvent event) {
        List<BlockState> blocks = event.getBlocks();
        Material blockType = event.getBlocks().get(0).getType();
        if (Material.OBSIDIAN.equals(blockType)) {
            /*if (!Main.getInstance().getConfig().getBoolean("features.world.nether_portal")) {
                for (BlockState blockState : blocks) {
                    Location location = blockState.getLocation().add(0.5, 0, 0.5);
                    location.getWorld().spawnParticle(Particle.END_ROD, location, 5, 0.15, 0.5, 0.15, 0.005, null);
                }
                event.setCancelled(true);
            }*/
        } else if (Material.END_PORTAL_FRAME.equals(blockType)) {
            /*if (!Main.getInstance().getConfig().getBoolean("features.world.end_portal")) {
                for (BlockState blockState : blocks) {
                    Location location = blockState.getLocation().add(0.5, 0, 0.5);
                    location.getWorld().spawnParticle(Particle.END_ROD, location, 5, 0.15, 0.5, 0.15, 0.005, null);
                }
                event.setCancelled(true);
            }*/
        }
    }

    @Override
    public String getLabel() {
        return "CreatePortal";
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

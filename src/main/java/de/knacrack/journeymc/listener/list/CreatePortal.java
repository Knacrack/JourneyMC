package de.knacrack.journeymc.listener.list;

import de.knacrack.journeymc.Main;
import de.knacrack.journeymc.listener.Listener;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.world.PortalCreateEvent;

public class CreatePortal implements Listener {

    @EventHandler
    public void onCreatePortal(PortalCreateEvent event) {
        Material blockType = event.getBlocks().get(0).getType();
        if(Material.OBSIDIAN.equals(blockType)){
            if(!Main.getInstance().getConfig().getBoolean("features.world.nether_portal")) {
                event.setCancelled(true);
            }
        } else if (Material.END_PORTAL_FRAME.equals(blockType)) {
            if(!Main.getInstance().getConfig().getBoolean("features.world.end_portal")) {
                event.setCancelled(true);
            }
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

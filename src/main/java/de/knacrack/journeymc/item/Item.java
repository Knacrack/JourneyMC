package de.knacrack.journeymc.item;

import de.knacrack.journeymc.utils.Register;
import org.bukkit.inventory.ItemStack;

public interface Item extends Register {
    ItemStack getItem();

    int getCustomModelId();

}

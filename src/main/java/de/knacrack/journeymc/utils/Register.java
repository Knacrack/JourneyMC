package de.knacrack.journeymc.utils;

import org.bukkit.NamespacedKey;

public interface Register {

    String getLabel();

    public boolean register();

    public NamespacedKey getKey();

}

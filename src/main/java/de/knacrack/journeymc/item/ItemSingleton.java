package de.knacrack.journeymc.item;

import de.knacrack.journeymc.listener.Listener;
import de.knacrack.journeymc.listener.ListenerSingleton;
import de.knacrack.journeymc.utils.logging.Logger;

import java.util.HashSet;
import java.util.Set;

public class ItemSingleton {
    private static final ItemSingleton instance = new ItemSingleton();

    private final Set<Item> registeredItem = new HashSet<>();

    private final Set<Item> unRegisteredItem = new HashSet<>();

    ItemSingleton() {
        Logger.info("Initiate Items.");
    }

    /**
     * fixme: Not sure if it works ¯\_(ツ)_/¯
     *
     * @param item
     */
    public void registerItem(Object item) {
        if (item instanceof Item item1) {
            if (item1.register()) {
                if (!registeredItem.contains(item1)) {
                    Logger.info("Register Listener: " + item1.getClass().getSimpleName());
                    registeredItem.add(item1);
                    if (item instanceof Listener listener) {
                        ListenerSingleton.getInstance().registerListener(listener);
                    }
                } else {
                    Logger.error(item1.getLabel() + " is already registered!");
                }
            } else {
                unRegisteredItem.add(item1);
            }
        }
    }

    public ItemSingleton getInstance() {
        return instance;
    }


}

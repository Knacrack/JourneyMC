package de.knacrack.journeymc.item;

import de.knacrack.journeymc.listener.Listener;
import de.knacrack.journeymc.listener.ListenerSingleton;
import de.knacrack.journeymc.utils.logging.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class ItemSingleton {
    private static final ItemSingleton instance = new ItemSingleton();

    private final List<Item> registeredItem = new ArrayList<>();

    private final List<Item> unregisteredItem = new ArrayList<>();

    ItemSingleton() {
        Logger.info("Initiate Items.");
    }

    public void registerItem(@NotNull Item item) {
        if (item.register()) {
            if (!registeredItem.contains(item)) {
                Logger.info("Register Item: " + item.getClass().getSimpleName());
                registeredItem.add(item);
                if (item instanceof Listener listener) {
                    ListenerSingleton.getInstance().registerListener(listener);
                }
            } else {
                Logger.error(item.getLabel() + " is already registered!");
            }
        } else {
            unregisteredItem.add(item);
        }
    }

    public void registerItems(Item... items) {
        Arrays.stream(items).forEach(this::registerItem);
    }

    public void registerItems(Set<Item> items) {
        items.stream().forEach(this::registerItem);
    }

    public void registerItems(List<Item> items) {
        items.stream().forEach(this::registerItem);
    }

    public List<Item> getRegisteredItems() {
        return registeredItem;
    }

    public List<Item> getUnregisteredItems() {
        return unregisteredItem;
    }

    public List<String> getRegisteredItemNames() {
        return registeredItem.stream().map(Item::getLabel).collect(Collectors.toList());
    }
    
    public Map<String, Item> getRegisteredItemsByName() {
        return registeredItem.stream().collect(Collectors.toMap(Item::getLabel, item -> item));
    }

    public Map<String, Item> getRegisteredItemsByClassName() {
        //return registeredItem.stream().map(Item::).collect(Collectors.toList());
        return registeredItem.stream().collect(Collectors.toMap(name -> name.getClass().getSimpleName(), item -> item));
    }

    public List<String> getUnregisteredItemNames() {
        return unregisteredItem.stream().map(Item::getLabel).collect(Collectors.toList());
    }

    public static ItemSingleton getInstance() {
        return instance;
    }


}

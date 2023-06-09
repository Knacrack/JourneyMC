package de.knacrack.journeymc.listener;

import de.knacrack.journeymc.Main;
import de.knacrack.journeymc.utils.logging.Logger;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ListenerSingleton {

    private static final ListenerSingleton listener = new ListenerSingleton();

    private final List<Listener> registeredListeners = new ArrayList<>();

    private final List<Listener> unregisteredListeners = new ArrayList<>();

    ListenerSingleton() {
        Logger.info("Initiate Listeners.");
    }

    public void registerListener(@NotNull Listener listener) {
        if (listener.register()) {
            if (!registeredListeners.contains(listener)) {
                Logger.info("Register Listener: " + listener.getClass().getSimpleName());
                Bukkit.getPluginManager().registerEvents(listener, Main.getInstance());
                registeredListeners.add(listener);
            } else {
                Logger.error(listener.getLabel() + " is already registered!");
            }
        } else {
            unregisteredListeners.add(listener);
        }
    }

    public void registerListeners(Listener... listeners) {
        Arrays.stream(listeners).forEach(this::registerListener);
    }

    public void registerListeners(Set<Listener> listeners) {
        listeners.stream().forEach(this::registerListener);
    }

    public void registerListeners(List<Listener> listeners) {
        listeners.stream().forEach(this::registerListener);
    }

    public List<Listener> getRegisteredListeners() {
        return registeredListeners;
    }

    public List<Listener> getUnregisteredListeners() {
        return unregisteredListeners;
    }

    public static ListenerSingleton getInstance() {
        return listener;
    }
}

package de.knacrack.journeymc.command;

import de.knacrack.journeymc.utils.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class CommandSingleton {

    private static final CommandSingleton command = new CommandSingleton();

    private final List<Command> registeredCommands = new ArrayList<>();

    private final List<Command> unregisteredCommands = new ArrayList<>();

    CommandSingleton() {
        Logger.info("Initiate Commands.");
    }

    public static CommandSingleton getInstance() {
        return command;
    }

    public void registerCommand(@NotNull Command command) {
        if (command.register()) {
            if (!registeredCommands.contains(command)) {
                Logger.info("Register Command: " + command.getClass().getSimpleName());
                org.bukkit.command.Command cmd = new org.bukkit.command.Command(command.getLabel()) {
                    @Override
                    public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String[] strings) {
                        command.command(commandSender, strings);
                        return false;
                    }

                    @Override
                    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
                        return command.tabComplete(sender, args);
                    }
                };

                cmd.setAliases(command.getAliases());
                cmd.setPermission(command.getPermission());

                Bukkit.getCommandMap().register("journeymc", cmd);
                registeredCommands.add(command);
            } else {
                Logger.error(command.getLabel() + " is already registered!");
            }
        } else {
            unregisteredCommands.add(command);
        }
    }

    public void registerCommands(Command... commands) {
        Arrays.stream(commands).forEach(this::registerCommand);
    }

    public void registerCommands(Set<Command> commands) {
        commands.stream().forEach(this::registerCommand);
    }

    public void registerCommands(List<Command> commands) {
        commands.stream().forEach(this::registerCommand);
    }

    public List<Command> getRegisteredListeners() {
        return registeredCommands;
    }

    public List<Command> getUnregisteredListeners() {
        return unregisteredCommands;
    }

}

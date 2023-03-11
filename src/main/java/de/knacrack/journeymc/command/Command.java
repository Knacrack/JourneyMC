package de.knacrack.journeymc.command;

import de.knacrack.journeymc.utils.Register;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface Command extends Register {

    @Nullable String getPermission();

    List<String> getAliases();

    String getDescription();

    void command(CommandSender sender, String[] args);

    List<String> tabComplete(CommandSender sender, String[] args);


}

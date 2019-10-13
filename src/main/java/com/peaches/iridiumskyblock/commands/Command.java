package com.peaches.iridiumskyblock.commands;

import com.peaches.iridiumskyblock.IridiumSkyblock;
import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class Command {
    private final boolean player;
    private final List<String> aliases;
    private final String permission;
    private final String description;

    public Command(List<String> aliases, String description, String permission, boolean player) {
        this.aliases = aliases;
        this.permission = "iridiumskyblock." + permission;
        this.player = player;
        this.description = description;
        IridiumSkyblock.getCommandManager().registerCommand(this);
    }

    public boolean isPlayer() {
        return player;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public String getPermission() {
        return permission;
    }

    public abstract void execute(CommandSender sender, String[] args);

    public abstract List<String> TabComplete(CommandSender cs, org.bukkit.command.Command cmd, String s, String[] args);

    public String getDescription() {
        return description;
    }
}

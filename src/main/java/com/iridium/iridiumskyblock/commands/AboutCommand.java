package com.iridium.iridiumskyblock.commands;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class AboutCommand extends Command {

    public AboutCommand() {
        super(Arrays.asList("about", "version"), "Displays plugin info", "", false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8Plugin Name: &7IridiumSkyblock"));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8Plugin Version: &7" + IridiumSkyblock.getInstance().getDescription().getVersion()));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8Coded by IridiumLLC"));
    }

    @Override
    public List<String> TabComplete(CommandSender cs, org.bukkit.command.Command cmd, String s, String[] args) {
        return null;
    }
}

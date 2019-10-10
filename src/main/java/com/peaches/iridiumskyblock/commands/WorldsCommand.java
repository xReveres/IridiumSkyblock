package com.peaches.iridiumskyblock.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.World;
import org.bukkit.command.CommandSender;

import com.peaches.iridiumskyblock.IridiumSkyblock;
import com.peaches.iridiumskyblock.Utils;

public class WorldsCommand extends Command {

    public WorldsCommand() {
        super(Arrays.asList("worlds"), "shows a list of valid Skyblock Worlds.","", true);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        List<String> worlds = new ArrayList<String>();
        for (World w : IridiumSkyblock.getConfiguration().enabledWorlds)
        	worlds.add(w.getName());
        
        sender.sendMessage(Utils.color(IridiumSkyblock.getMessages().validWorlds.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)
        																		.replace("%worlds%", String.join(", ", worlds))));
    }

    @Override
    public List<String> TabComplete(CommandSender cs, org.bukkit.command.Command cmd, String s, String[] args) {
        return null;
    }
}

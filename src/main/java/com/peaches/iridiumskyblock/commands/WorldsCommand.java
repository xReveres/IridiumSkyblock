package com.peaches.iridiumskyblock.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;

import com.peaches.iridiumskyblock.IridiumSkyblock;
import com.peaches.iridiumskyblock.Utils;

public class WorldsCommand extends Command {

    public WorldsCommand() {
        super(Arrays.asList("worlds"), "shows a list of valid Skyblock Worlds.","", true);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
    	if (IridiumSkyblock.getConfiguration().EnabledWorldsIsBlacklist) {
	        sender.sendMessage(Utils.color(IridiumSkyblock.getMessages().invalidWorlds.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)
					.replace("%worlds%", String.join(", ", IridiumSkyblock.getConfiguration().EnabledWorlds))));
    	} else {
	        sender.sendMessage(Utils.color(IridiumSkyblock.getMessages().validWorlds.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)
	        																		.replace("%worlds%", String.join(", ", IridiumSkyblock.getConfiguration().EnabledWorlds))));
    	}
    }

    @Override
    public List<String> TabComplete(CommandSender cs, org.bukkit.command.Command cmd, String s, String[] args) {
        return null;
    }
}

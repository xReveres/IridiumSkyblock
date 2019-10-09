package com.peaches.iridiumskyblock.commands;

import com.peaches.iridiumskyblock.IridiumSkyblock;
import com.peaches.iridiumskyblock.Utils;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class ReloadCommand extends Command {

    public ReloadCommand() {
        super(Arrays.asList("reload"), "Reload your configurations","EpicSkyblock.reload", false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        IridiumSkyblock.getInstance().loadConfigs();
        sender.sendMessage(Utils.color(IridiumSkyblock.getMessages().reloaded.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
    }

    @Override
    public List<String> TabComplete(CommandSender cs, org.bukkit.command.Command cmd, String s, String[] args) {
        return null;
    }
}

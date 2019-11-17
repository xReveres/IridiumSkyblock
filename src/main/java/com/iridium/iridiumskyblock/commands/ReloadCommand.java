package com.iridium.iridiumskyblock.commands;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Utils;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class ReloadCommand extends Command {

    public ReloadCommand() {
        super(Collections.singletonList("reload"), "Reload your configurations","reload", false);
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

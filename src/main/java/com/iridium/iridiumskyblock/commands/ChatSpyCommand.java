package com.iridium.iridiumskyblock.commands;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class ChatSpyCommand extends Command {

    public ChatSpyCommand() {
        super(Collections.singletonList("chatspy"), "Spy all islands chat", "iridiumskyblock.ischatspy", true);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        final IridiumSkyblock plugin = IridiumSkyblock.getInstance();
        if (plugin.spyingIslandsChat.contains(p.getName())) {
            plugin.spyingIslandsChat.remove(p.getName());
            p.sendMessage(Utils.color(IridiumSkyblock.getMessages().chatSpyDisabled.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
        } else {
            plugin.spyingIslandsChat.add(p.getName());
            p.sendMessage(Utils.color(IridiumSkyblock.getMessages().chatSpyEnabled.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
        }
    }

    @Override
    public void admin(CommandSender sender, String[] args, Island island) {
        execute(sender, args);
    }

    @Override
    public List<String> TabComplete(CommandSender cs, org.bukkit.command.Command cmd, String s, String[] args) {
        return null;
    }
}

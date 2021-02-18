package com.iridium.iridiumskyblock.commands;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.User;
import com.iridium.iridiumskyblock.utils.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class ChatSpyCommand extends Command {

    public ChatSpyCommand() {
        super(Arrays.asList("chatspy", "spy"), "Spy on island chats", "iridiumskyblock.spy", true);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        User u = User.getUser(p);
        u.spyingIslandsChat = !u.spyingIslandsChat;
        if (u.spyingIslandsChat) {
            p.sendMessage(StringUtils.color(IridiumSkyblock.getInstance().getMessages().chatSpyEnabled.replace("%prefix%", IridiumSkyblock.getInstance().getConfiguration().prefix)));
        } else {
            p.sendMessage(StringUtils.color(IridiumSkyblock.getInstance().getMessages().chatSpyDisabled.replace("%prefix%", IridiumSkyblock.getInstance().getConfiguration().prefix)));
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

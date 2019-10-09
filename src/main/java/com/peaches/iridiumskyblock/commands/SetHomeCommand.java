package com.peaches.iridiumskyblock.commands;

import com.peaches.iridiumskyblock.IridiumSkyblock;
import com.peaches.iridiumskyblock.User;
import com.peaches.iridiumskyblock.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class SetHomeCommand extends Command {

    public SetHomeCommand() {
        super(Arrays.asList("sethome"), "Set your island home", "", true);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        User user = User.getUser(p);
        if (user.getIsland() != null) {
            if (Utils.isSafe(p.getLocation(), user.getIsland())) {
                user.getIsland().setHome(p.getLocation());
                p.sendMessage(Utils.color(IridiumSkyblock.getMessages().setHome.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
            } else {
                p.sendMessage(Utils.color(IridiumSkyblock.getMessages().isNotSafe.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
            }
        } else {
            p.sendMessage(Utils.color(IridiumSkyblock.getMessages().noIsland.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
        }
    }

    @Override
    public List<String> TabComplete(CommandSender cs, org.bukkit.command.Command cmd, String s, String[] args) {
        return null;
    }
}

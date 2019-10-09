package com.peaches.iridiumskyblock.commands;

import com.peaches.iridiumskyblock.IridiumSkyblock;
import com.peaches.iridiumskyblock.Island;
import com.peaches.iridiumskyblock.User;
import com.peaches.iridiumskyblock.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class JoinCommand extends Command {

    public JoinCommand() {
        super(Arrays.asList("join"),"Join another players island", "", true);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length != 2) {
            sender.sendMessage("/is join <player>");
            return;
        }
        Player p = (Player) sender;
        User user = User.getUser(p);
        OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);
        if (player != null) {
            User u = User.getUser(player);
            if (u.getIsland() != null) {
                if (user.getIsland() == null) {
                    Island island = u.getIsland();
                    if (user.invites.contains(island.getId())) {
                        island.addUser(user);
                    } else {
                        sender.sendMessage(Utils.color(IridiumSkyblock.getMessages().noActiveInvites.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                    }
                } else {
                    sender.sendMessage(Utils.color(IridiumSkyblock.getMessages().playerAlreadyHaveIsland.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                }
            } else {
                sender.sendMessage(Utils.color(IridiumSkyblock.getMessages().noIsland.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
            }
        } else {
            sender.sendMessage(Utils.color(IridiumSkyblock.getMessages().playerOffline.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
        }
    }

    @Override
    public List<String> TabComplete(CommandSender cs, org.bukkit.command.Command cmd, String s, String[] args) {
        return null;
    }
}

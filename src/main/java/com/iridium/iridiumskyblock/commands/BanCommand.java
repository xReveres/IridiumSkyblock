package com.iridium.iridiumskyblock.commands;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.User;
import com.iridium.iridiumskyblock.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class BanCommand extends Command {

    public BanCommand() {
        super(Arrays.asList("ban"), "Ban a player from visiting your island", "", true);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length != 2) {
            sender.sendMessage("/is ban <player>");
            return;
        }
        Player p = (Player) sender;
        User user = User.getUser(p);
        if (user.getIsland() != null) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);
            if (player != null) {
                if (!User.getUser(player).getIsland().equals(user.getIsland())) {
                    user.getIsland().addBan(User.getUser(player));
                    sender.sendMessage(Utils.color(IridiumSkyblock.getMessages().playerBanned.replace("%player%", player.getName()).replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                    if (player.getPlayer() != null) {
                        if (user.getIsland().isInIsland(player.getPlayer().getLocation())) {
                            user.getIsland().spawnPlayer(player.getPlayer());
                        }
                    }
                }
            } else {
                sender.sendMessage(Utils.color(IridiumSkyblock.getMessages().playerOffline.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
            }
        } else {
            sender.sendMessage(Utils.color(IridiumSkyblock.getMessages().noIsland.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
        }
    }

    @Override
    public List<String> TabComplete(CommandSender cs, org.bukkit.command.Command cmd, String s, String[] args) {
        return null;
    }
}

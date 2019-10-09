package com.peaches.iridiumskyblock.commands;

import com.peaches.iridiumskyblock.IridiumSkyblock;
import com.peaches.iridiumskyblock.Roles;
import com.peaches.iridiumskyblock.User;
import com.peaches.iridiumskyblock.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class KickCommand extends Command {

    public KickCommand() {
        super(Arrays.asList("kick"), "Kick a player from your island", "", true);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length != 2) {
            sender.sendMessage("/is kick <player>");
            return;
        }
        Player p = (Player) sender;
        User user = User.getUser(p); // User kicking the player
        OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);
        if (player != null) {
            User u = User.getUser(player); // Player we want to kick
            if (user.getIsland() != null) {
                if (user.getIsland().equals(u.getIsland())) {
                    if (user.role.equals(Roles.Owner)) {
                        sender.sendMessage(Utils.color(IridiumSkyblock.getMessages().cantKickOwner.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                    } else {
                        if (user.bypassing || user.getIsland().getPermissions(u.role).kickMembers) {
                            user.getIsland().removeUser(u);
                            if (player.getPlayer() != null)
                                player.getPlayer().sendMessage(Utils.color(IridiumSkyblock.getMessages().youHaveBeenKicked.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                        } else {
                            sender.sendMessage(Utils.color(IridiumSkyblock.getMessages().noPermission.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                        }
                    }
                } else {
                    sender.sendMessage(Utils.color(IridiumSkyblock.getMessages().notInYourIsland.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
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

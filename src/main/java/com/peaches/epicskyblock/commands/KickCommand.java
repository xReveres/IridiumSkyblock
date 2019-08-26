package com.peaches.epicskyblock.commands;

import com.peaches.epicskyblock.EpicSkyblock;
import com.peaches.epicskyblock.Roles;
import com.peaches.epicskyblock.User;
import com.peaches.epicskyblock.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
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
        OfflinePlayer player = Bukkit.getPlayer(args[1]);
        if (player != null) {
            User u = User.getUser(player); // Player we want to kick
            if (user.getIsland() != null) {
                if (user.getIsland().equals(u.getIsland())) {
                    if (user.role.equals(Roles.Owner)) {
                        sender.sendMessage(Utils.color(EpicSkyblock.getMessages().cantKickOwner.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
                    } else {
                        if (user.bypassing || user.getIsland().getPermissions(u.role).kickMembers) {
                            user.getIsland().removeUser(u);
                            if (player.getPlayer() != null)
                                player.getPlayer().sendMessage(Utils.color(EpicSkyblock.getMessages().youHaveBeenKicked.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
                        } else {
                            sender.sendMessage(Utils.color(EpicSkyblock.getMessages().noPermission.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
                        }
                    }
                } else {
                    sender.sendMessage(Utils.color(EpicSkyblock.getMessages().notInYourIsland.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
                }
            } else {
                sender.sendMessage(Utils.color(EpicSkyblock.getMessages().noIsland.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
            }
        } else {
            sender.sendMessage(Utils.color(EpicSkyblock.getMessages().playerOffline.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
        }
    }

    @Override
    public List<String> TabComplete(CommandSender cs, org.bukkit.command.Command cmd, String s, String[] args) {
        return null;
    }
}

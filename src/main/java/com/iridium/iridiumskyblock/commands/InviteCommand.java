package com.iridium.iridiumskyblock.commands;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.User;
import com.iridium.iridiumskyblock.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class InviteCommand extends Command {

    public InviteCommand() {
        super(Arrays.asList("invite"), "Invite a player to your island", "", true);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length != 2) {
            sender.sendMessage("/is invite <player>");
            return;
        }
        Player p = (Player) sender;
        User user = User.getUser(p);
        OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);
        if (player != null) {
            User u = User.getUser(player);
            if (user.getIsland() != null) {
                if (u.getIsland() == null) {
                    if (user.bypassing || user.getIsland().getPermissions(user.role).inviteMembers) {
                        u.invites.add(user.getIsland().getId());
                        p.sendMessage(Utils.color(IridiumSkyblock.getMessages().playerInvited.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                        if (player.getPlayer() != null)
                            player.getPlayer().sendMessage(Utils.color(IridiumSkyblock.getMessages().invitedByPlayer.replace("%player%", p.getName()).replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                    } else {
                        sender.sendMessage(Utils.color(IridiumSkyblock.getMessages().noPermission.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
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

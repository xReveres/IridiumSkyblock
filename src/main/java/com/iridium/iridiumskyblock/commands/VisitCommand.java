package com.iridium.iridiumskyblock.commands;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.User;
import com.iridium.iridiumskyblock.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class VisitCommand extends Command {

    public VisitCommand() {
        super(Collections.singletonList("visit"), "Visit another players island", "", true);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length != 2) {
            sender.sendMessage("/is visit <player>");
            return;
        }
        Player p = (Player) sender;
        OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);
        if (player != null) {
            User user = User.getUser(player);
            if (user.getIsland() != null) {
                if (user.getIsland().isVisit() || User.getUser(p).bypassing) {
                    user.getIsland().teleportHome(p);
                } else {
                    sender.sendMessage(Utils.color(IridiumSkyblock.getMessages().playersIslandIsPrivate.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
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

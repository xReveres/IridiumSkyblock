package com.iridium.iridiumskyblock.commands;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.User;
import com.iridium.iridiumskyblock.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class RemoveCrystalsCommand extends Command {

    public RemoveCrystalsCommand() {
        super(Collections.singletonList("removecrystals"), "remove a player's Crystals", "iridiumskyblock.removecrystals", false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length != 3) {
            sender.sendMessage(StringUtils.color(IridiumSkyblock.getInstance().getConfiguration().prefix) + "/is removecrystals <player> <amount>");
            sender.sendMessage("/is removecrystals <player> <amount>");
            return;
        }

        if (Bukkit.getPlayer(args[1]) != null) {
            OfflinePlayer player = Bukkit.getPlayer(args[1]);
            if (player != null) {
                Island island = User.getUser(player).getIsland();
                if (island != null) {
                    if (org.apache.commons.lang.StringUtils.isNumeric(args[2])) {
                        int amount = Integer.parseInt(args[2]);
                        if (amount <= island.getCrystals()) {
                            island.setCrystals(island.getCrystals() - amount);
                            sender.sendMessage(StringUtils.color(IridiumSkyblock.getInstance().getMessages().removedcrystals.replace("%crystals%", args[2]).replace("%player%", player.getName()).replace("%prefix%", IridiumSkyblock.getInstance().getConfiguration().prefix)));
                        } else {
                            sender.sendMessage(StringUtils.color(IridiumSkyblock.getInstance().getMessages().playerNotEnoughCrystals.replace("%player%", player.getName()).replace("%prefix%", IridiumSkyblock.getInstance().getConfiguration().prefix)));
                        }
                    } else {
                        sender.sendMessage(StringUtils.color(IridiumSkyblock.getInstance().getMessages().notNumber.replace("%prefix%", IridiumSkyblock.getInstance().getConfiguration().prefix).replace("%error%", args[2])));
                    }
                } else {
                    sender.sendMessage(StringUtils.color(IridiumSkyblock.getInstance().getMessages().playerNoIsland.replace("%prefix%", IridiumSkyblock.getInstance().getConfiguration().prefix)));
                }
            } else {
                sender.sendMessage(StringUtils.color(IridiumSkyblock.getInstance().getMessages().playerOffline.replace("%prefix%", IridiumSkyblock.getInstance().getConfiguration().prefix)));
            }
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

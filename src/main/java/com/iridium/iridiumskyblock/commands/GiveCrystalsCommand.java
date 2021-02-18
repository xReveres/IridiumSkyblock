package com.iridium.iridiumskyblock.commands;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.User;
import com.iridium.iridiumskyblock.utils.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class GiveCrystalsCommand extends Command {

    public GiveCrystalsCommand() {
        super(Collections.singletonList("givecrystals"), "Give a player Crystals", "iridiumskyblock.givecrystals", false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length != 3) {
            sender.sendMessage(StringUtils.color(IridiumSkyblock.getInstance().getConfiguration().prefix) + "/is givecrystals <player> <amount>");
            return;
        }

        if (Bukkit.getPlayer(args[1]) != null) {
            OfflinePlayer player = Bukkit.getPlayer(args[1]);
            if (player != null) {
                Island island = User.getUser(player).getIsland();
                if (island != null) {
                    if (NumberUtils.isNumber(args[2])) {
                        int amount = Integer.parseInt(args[2]);
                        island.setCrystals(island.getCrystals() + amount);
                        sender.sendMessage(StringUtils.color(IridiumSkyblock.getInstance().getMessages().giveCrystals.replace("%crystals%", args[2]).replace("%player%", player.getName()).replace("%prefix%", IridiumSkyblock.getInstance().getConfiguration().prefix)));
                        if (player.getPlayer() != null)
                            player.getPlayer().sendMessage(StringUtils.color(IridiumSkyblock.getInstance().getMessages().givenCrystals.replace("%crystals%", args[2]).replace("%player%", sender.getName()).replace("%prefix%", IridiumSkyblock.getInstance().getConfiguration().prefix)));
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

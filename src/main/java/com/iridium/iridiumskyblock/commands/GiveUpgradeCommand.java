package com.iridium.iridiumskyblock.commands;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.User;
import com.iridium.iridiumskyblock.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GiveUpgradeCommand extends Command {

    public GiveUpgradeCommand() {
        super(Collections.singletonList("giveupgrade"), "Give an Island an Upgrade", "giveupgrade", false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length != 3) {
            sender.sendMessage("/is giveupgrade <player> <upgrade>");
            return;
        }

        if (Bukkit.getPlayer(args[1]) != null) {
            OfflinePlayer player = Bukkit.getPlayer(args[1]);
            if (player != null) {
                Island island = User.getUser(player).getIsland();
                if (island != null) {
                    if (args[2].equalsIgnoreCase("size")) {
                        if (IridiumSkyblock.getUpgrades().sizeUpgrade.upgrades.containsKey(island.getSizeLevel() + 1)) {
                            island.setSizeLevel(island.getSizeLevel() + 1);
                        }
                    }
                    if (args[2].equalsIgnoreCase("member")) {
                        if (IridiumSkyblock.getUpgrades().memberUpgrade.upgrades.containsKey(island.getMemberLevel() + 1)) {
                            island.setMemberLevel(island.getMemberLevel() + 1);
                        }
                    }
                    if (args[2].equalsIgnoreCase("warp")) {
                        if (IridiumSkyblock.getUpgrades().warpUpgrade.upgrades.containsKey(island.getWarpLevel() + 1)) {
                            island.setWarpLevel(island.getWarpLevel() + 1);
                        }
                    }
                    if (args[2].equalsIgnoreCase("ores")) {
                        if (IridiumSkyblock.getUpgrades().oresUpgrade.upgrades.containsKey(island.getOreLevel() + 1)) {
                            island.setOreLevel(island.getOreLevel() + 1);
                        }
                    }
                } else {
                    sender.sendMessage(Utils.color(IridiumSkyblock.getMessages().playerNoIsland.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                }
            } else {
                sender.sendMessage(Utils.color(IridiumSkyblock.getMessages().playerOffline.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
            }
        }
    }

    @Override
    public List<String> TabComplete(CommandSender cs, org.bukkit.command.Command cmd, String s, String[] args) {
        return Arrays.asList("size", "member", "warp", "ores");
    }
}

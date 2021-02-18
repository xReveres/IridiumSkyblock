package com.iridium.iridiumskyblock.commands;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.User;
import com.iridium.iridiumskyblock.utils.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class CrystalsCommand extends Command {

    public CrystalsCommand() {
        super(Collections.singletonList("crystals"), "Shows you how many crystals you have", "", true);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        User user = User.getUser(p);
        if (user.getIsland() != null) {
            sender.sendMessage(StringUtils.color(IridiumSkyblock.getInstance().getMessages().crystalAmount.replace("%crystals%", user.getIsland().getFormattedCrystals()).replace("%prefix%", IridiumSkyblock.getInstance().getConfiguration().prefix)));
        } else {
            p.sendMessage(StringUtils.color(IridiumSkyblock.getInstance().getMessages().noIsland.replace("%prefix%", IridiumSkyblock.getInstance().getConfiguration().prefix)));
        }
    }

    @Override
    public void admin(CommandSender sender, String[] args, Island island) {
        Player p = (Player) sender;
        if (island != null) {
            sender.sendMessage(StringUtils.color(IridiumSkyblock.getInstance().getMessages().crystalAmount.replace("%crystals%", island.getFormattedCrystals() + "").replace("%prefix%", IridiumSkyblock.getInstance().getConfiguration().prefix)));
        } else {
            p.sendMessage(StringUtils.color(IridiumSkyblock.getInstance().getMessages().noIsland.replace("%prefix%", IridiumSkyblock.getInstance().getConfiguration().prefix)));
        }
    }

    @Override
    public List<String> TabComplete(CommandSender cs, org.bukkit.command.Command cmd, String s, String[] args) {
        return null;
    }
}

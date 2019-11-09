package com.iridium.iridiumskyblock.commands;

import com.iridium.iridiumskyblock.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class WarpsCommand extends Command {

    public WarpsCommand() {
        super(Arrays.asList("warps"), "opens the Warp GUI", "", true);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        User user = User.getUser(p);
        Island island = IridiumSkyblock.getIslandManager().getIslandViaLocation(p.getLocation());
        if (island != null) {
            if (island.getPermissions(user.islandID == island.getId() ? user.role : Role.Visitor).useNetherPortal || user.bypassing) {
                p.openInventory(island.getWarpGUI().getInventory());
            } else {
                sender.sendMessage(Utils.color(IridiumSkyblock.getMessages().noPermission.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
            }
        } else {
            if (user.getIsland() != null) {
                p.openInventory(user.getIsland().getWarpGUI().getInventory());
            } else {
                sender.sendMessage(Utils.color(IridiumSkyblock.getMessages().noIsland.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
            }
        }
    }

    @Override
    public List<String> TabComplete(CommandSender cs, org.bukkit.command.Command cmd, String s, String[] args) {
        return null;
    }
}

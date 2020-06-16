package com.iridium.iridiumskyblock.commands;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.User;
import com.iridium.iridiumskyblock.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class AdminCommand extends Command {

    public AdminCommand() {
        super(Collections.singletonList("admin"), "Control a players Island", "admin", true);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        //Open island admin gui
        if (args.length == 2) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);
            if (player != null) {
                User u = User.getUser(player);
                if (u.getIsland() != null) {
                    p.openInventory(u.getIsland().getIslandAdminGUI().getInventory());
                } else {
                    sender.sendMessage(Utils.color(IridiumSkyblock.getMessages().playerNoIsland.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                }
                sender.sendMessage(Utils.color(IridiumSkyblock.getMessages().playerOffline.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
            }
        } else if (args.length >= 2) {
            Island island = null;
            try {
                int id = Integer.parseInt(args[1]);
                island = IridiumSkyblock.getIslandManager().getIslandViaId(id);
            } catch (NumberFormatException e) {
                OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);
                if (player != null) {
                    User u = User.getUser(player);
                    if (u.getIsland() != null) {
                        p.openInventory(u.getIsland().getIslandAdminGUI().getInventory());
                    } else {
                        sender.sendMessage(Utils.color(IridiumSkyblock.getMessages().playerNoIsland.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                    }
                    sender.sendMessage(Utils.color(IridiumSkyblock.getMessages().playerOffline.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                }
            }
            if (island != null) {
                for (com.iridium.iridiumskyblock.commands.Command command : IridiumSkyblock.getCommandManager().commands) {
                    if (command.getAliases().contains(args[2]) && command.isEnabled()) {
                        if ((sender.hasPermission(command.getPermission()) || command.getPermission().equalsIgnoreCase("") || command.getPermission().equalsIgnoreCase("iridiumskyblock.")) && command.isEnabled()) {
                            command.admin(sender, args, island);
                        } else {
                            // No permission
                            sender.sendMessage(Utils.color(IridiumSkyblock.getMessages().noPermission.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                        }
                        return;
                    }
                }
            }
        } else {
            sender.sendMessage("/is admin <island>");
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

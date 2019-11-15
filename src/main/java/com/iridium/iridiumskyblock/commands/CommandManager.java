package com.iridium.iridiumskyblock.commands;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.User;
import com.iridium.iridiumskyblock.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements CommandExecutor, TabCompleter {
    private List<com.iridium.iridiumskyblock.commands.Command> commands = new ArrayList<>();

    public CommandManager(String command) {
        IridiumSkyblock.getInstance().getCommand(command).setExecutor(this);
        IridiumSkyblock.getInstance().getCommand(command).setTabCompleter(this);
    }

    public void registerCommands() {
        registerCommand(IridiumSkyblock.getCommands().aboutCommand);
        registerCommand(IridiumSkyblock.getCommands().boosterCommand);
        registerCommand(IridiumSkyblock.getCommands().bypassCommand);
        registerCommand(IridiumSkyblock.getCommands().createCommand);
        registerCommand(IridiumSkyblock.getCommands().crystalsCommand);
        registerCommand(IridiumSkyblock.getCommands().deleteCommand);
        registerCommand(IridiumSkyblock.getCommands().flyCommand);
        registerCommand(IridiumSkyblock.getCommands().homeCommand);
        registerCommand(IridiumSkyblock.getCommands().inviteCommand);
        registerCommand(IridiumSkyblock.getCommands().joinCommand);
        registerCommand(IridiumSkyblock.getCommands().kickCommand);
        registerCommand(IridiumSkyblock.getCommands().leaveCommand);
        registerCommand(IridiumSkyblock.getCommands().membersCommand);
        registerCommand(IridiumSkyblock.getCommands().missionsCommand);
        registerCommand(IridiumSkyblock.getCommands().privateCommand);
        registerCommand(IridiumSkyblock.getCommands().publicCommand);
        registerCommand(IridiumSkyblock.getCommands().regenCommand);
        registerCommand(IridiumSkyblock.getCommands().reloadCommand);
        registerCommand(IridiumSkyblock.getCommands().topCommand);
        registerCommand(IridiumSkyblock.getCommands().upgradeCommand);
        registerCommand(IridiumSkyblock.getCommands().valueCommand);
        registerCommand(IridiumSkyblock.getCommands().visitCommand);
        registerCommand(IridiumSkyblock.getCommands().warpCommand);
        registerCommand(IridiumSkyblock.getCommands().warpsCommand);
        registerCommand(IridiumSkyblock.getCommands().giveCrystalsCommand);
        registerCommand(IridiumSkyblock.getCommands().removeCrystalsCommand);
        registerCommand(IridiumSkyblock.getCommands().worldBorderCommand);
        registerCommand(IridiumSkyblock.getCommands().setHomeCommand);
        registerCommand(IridiumSkyblock.getCommands().permissionsCommand);
        registerCommand(IridiumSkyblock.getCommands().transferCommand);
        registerCommand(IridiumSkyblock.getCommands().adminCommand);
        registerCommand(IridiumSkyblock.getCommands().giveBoosterCommand);
        registerCommand(IridiumSkyblock.getCommands().banCommand);
        registerCommand(IridiumSkyblock.getCommands().unBanCommand);
    }

    public void registerCommand(com.iridium.iridiumskyblock.commands.Command command) {
        commands.add(command);
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String s, String[] args) {
        try {
            if (args.length != 0) {
                for (com.iridium.iridiumskyblock.commands.Command command : commands) {
                    if (command.getAliases().contains(args[0])) {
                        if (command.isPlayer() && !(cs instanceof Player)) {
                            // Must be a player
                            cs.sendMessage(Utils.color(IridiumSkyblock.getMessages().mustBeAPlayer.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                            return true;
                        }
                        if (cs.hasPermission(command.getPermission()) || command.getPermission().equalsIgnoreCase("iridiumskyblock.")) {
                            command.execute(cs, args);
                            return true;
                        } else {
                            // No permission
                            cs.sendMessage(Utils.color(IridiumSkyblock.getMessages().noPermission.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                            return true;
                        }
                    }
                }
            } else {
                if (cs instanceof Player) {
                    Player p = (Player) cs;
                    User u = User.getUser(p);
                    if (u.getIsland() != null) {
                        if (IridiumSkyblock.getConfiguration().islandMenu) {
                            p.openInventory(u.getIsland().getIslandMenuGUI().getInventory());
                        } else {
                            u.getIsland().teleportHome(p);
                        }
                        return true;
                    } else {
                        IridiumSkyblock.getIslandManager().createIsland(p);
                        return true;
                    }
                }
            }
            //Help Menu
            cs.sendMessage(Utils.color("&b&lIridiumSkyblock: &bHelp"));
            for (com.iridium.iridiumskyblock.commands.Command c : commands) {
                if (cs.hasPermission(c.getPermission()) || c.getPermission().equalsIgnoreCase("iridiumskyblock.")) {
                    cs.sendMessage(Utils.color(IridiumSkyblock.getMessages().helpMessage.replace("%command%", c.getAliases().get(0)).replace("%description%", c.getDescription())));
                }
            }
        } catch (Exception e) {
            IridiumSkyblock.getInstance().sendErrorMessage(e);
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender cs, Command cmd, String s, String[] args) {
        try {
            if (args.length == 1) {
                ArrayList<String> result = new ArrayList<>();
                for (com.iridium.iridiumskyblock.commands.Command command : commands) {
                    for (String alias : command.getAliases()) {
                        if (alias.toLowerCase().startsWith(args[0].toLowerCase()) && (cs.hasPermission(command.getPermission()) || command.getPermission().equalsIgnoreCase("iridiumskyblock."))) {
                            result.add(alias);
                        }
                    }
                }
                return result;
            }
            for (com.iridium.iridiumskyblock.commands.Command command : commands) {
                if (command.getAliases().contains(args[0]) && (cs.hasPermission(command.getPermission()) || command.getPermission().equalsIgnoreCase("iridiumskyblock."))) {
                    return command.TabComplete(cs, cmd, s, args);
                }
            }
        } catch (Exception e) {
            IridiumSkyblock.getInstance().sendErrorMessage(e);
        }
        return null;
    }
}

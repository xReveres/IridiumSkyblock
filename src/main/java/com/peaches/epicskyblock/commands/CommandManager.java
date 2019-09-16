package com.peaches.epicskyblock.commands;

import com.peaches.epicskyblock.EpicSkyblock;
import com.peaches.epicskyblock.User;
import com.peaches.epicskyblock.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements CommandExecutor, TabCompleter {
    private List<com.peaches.epicskyblock.commands.Command> commands = new ArrayList<>();

    public CommandManager(String command) {
        EpicSkyblock.getInstance().getCommand(command).setExecutor(this);
        EpicSkyblock.getInstance().getCommand(command).setTabCompleter(this);
    }

    public void registerCommands() {
        new CreateCommand();
        new HomeCommand();
        new DeleteCommand();
        new ReloadCommand();
        new RegenCommand();
        new InviteCommand();
        new JoinCommand();
        new MissionsCommand();
        new UpgradeCommand();
        new BoosterCommand();
        new CrystalsCommand();
        new GiveCrystalsCommand();
        new RemoveCrystalsCommand();
        new MembersCommand();
        new FlyCommand();
        new AboutCommand();
        new WarpsCommand();
        new SetWarpCommand();
        new ValueCommand();
        new TopCommand();
        new LeaveCommand();
        new WorldBorderCommand();
        new KickCommand();
        new VisitCommand();
        new PublicCommand();
        new PrivateCommand();
        new BypassCommand();
        new SetHomeCommand();
//        new EditorCommand();
    }

    public void registerCommand(com.peaches.epicskyblock.commands.Command command) {
        commands.add(command);
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String s, String[] args) {
        try {
            if (args.length != 0) {
                for (com.peaches.epicskyblock.commands.Command command : commands) {
                    if (command.getAliases().contains(args[0])) {
                        if (!command.isPlayer() || cs instanceof Player) {
                            if (cs.hasPermission(command.getPermission()) || command.getPermission().isEmpty()) {
                                command.execute(cs, args);
                                return true;
                            } else {
                                // No permission
                                cs.sendMessage(Utils.color(EpicSkyblock.getMessages().noPermission.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
                                return true;
                            }
                        } else {
                            // Must be a player
                            cs.sendMessage(Utils.color(EpicSkyblock.getMessages().mustBeAPlayer.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
                            return true;
                        }
                    }
                }
            } else {
                if (cs instanceof Player) {
                    Player p = (Player) cs;
                    User u = User.getUser(p);
                    if (u.getIsland() != null) {
                        u.getIsland().teleportHome(p);
                        return true;
                    } else {
                        EpicSkyblock.getIslandManager().createIsland(p);
                        return true;
                    }
                }
            }
            //Help Menu
            cs.sendMessage(Utils.color("&b&lEpicSkyblock: &bHelp"));
            for (com.peaches.epicskyblock.commands.Command c : commands) {
                cs.sendMessage(Utils.color("&b&l * &7" + c.getAliases().get(0) + ": &b" + c.getDescription()));
            }
        } catch (Exception e) {
            EpicSkyblock.getInstance().sendErrorMessage(e);
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender cs, Command cmd, String s, String[] args) {
        try {
            if (args.length == 1) {
                ArrayList<String> result = new ArrayList<>();
                for (com.peaches.epicskyblock.commands.Command command : commands) {
                    for (String alias : command.getAliases()) {
                        if (alias.toLowerCase().startsWith(args[0].toLowerCase())) {
                            result.add(alias);
                        }
                    }
                }
                return result;
            }
            for (com.peaches.epicskyblock.commands.Command command : commands) {
                if (command.getAliases().contains(args[0])) {
                    return command.TabComplete(cs, cmd, s, args);
                }
            }
        } catch (Exception e) {
            EpicSkyblock.getInstance().sendErrorMessage(e);
        }
        return null;
    }
}

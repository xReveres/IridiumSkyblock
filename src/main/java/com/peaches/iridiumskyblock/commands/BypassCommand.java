package com.peaches.iridiumskyblock.commands;

import com.peaches.iridiumskyblock.IridiumSkyblock;
import com.peaches.iridiumskyblock.User;
import com.peaches.iridiumskyblock.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class BypassCommand extends Command {

    public BypassCommand() {
        super(Arrays.asList("bypass"), "Bypass all island restrictions", "epicskyblock.bypass", true);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        User u = User.getUser(p);
        u.bypassing = !u.bypassing;
        if (u.bypassing) {
            p.sendMessage(Utils.color(IridiumSkyblock.getMessages().nowBypassing.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
        } else {
            p.sendMessage(Utils.color(IridiumSkyblock.getMessages().noLongerBypassing.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
        }
    }

    @Override
    public List<String> TabComplete(CommandSender cs, org.bukkit.command.Command cmd, String s, String[] args) {
        return null;
    }
}

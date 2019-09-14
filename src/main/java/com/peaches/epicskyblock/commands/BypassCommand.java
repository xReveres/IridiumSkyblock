package com.peaches.epicskyblock.commands;

import com.peaches.epicskyblock.EpicSkyblock;
import com.peaches.epicskyblock.User;
import com.peaches.epicskyblock.Utils;
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
            p.sendMessage(Utils.color(EpicSkyblock.getMessages().nowBypassing.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
        } else {
            p.sendMessage(Utils.color(EpicSkyblock.getMessages().noLongerBypassing.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
        }
    }

    @Override
    public List<String> TabComplete(CommandSender cs, org.bukkit.command.Command cmd, String s, String[] args) {
        return null;
    }
}

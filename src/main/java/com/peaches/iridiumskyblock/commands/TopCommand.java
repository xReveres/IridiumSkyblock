package com.peaches.iridiumskyblock.commands;

import com.peaches.iridiumskyblock.IridiumSkyblock;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class TopCommand extends Command {

    public TopCommand() {
        super(Arrays.asList("top"), "View the top islands", "", true);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        p.openInventory(IridiumSkyblock.topGUI.inventory);

    }

    @Override
    public List<String> TabComplete(CommandSender cs, org.bukkit.command.Command cmd, String s, String[] args) {
        return null;
    }
}

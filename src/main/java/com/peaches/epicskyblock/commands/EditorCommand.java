package com.peaches.epicskyblock.commands;

import com.peaches.epicskyblock.gui.EditorGUI;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class EditorCommand extends Command {

    public EditorCommand() {
        super(Arrays.asList("editor"), "Edits all configurable files", "EpicSkyblock.editor", true);
    }

    @Override
    public void execute(CommandSender cs, String[] var2) {
        ((Player) cs).openInventory(EditorGUI.inventory);
    }

    @Override
    public List<String> TabComplete(CommandSender var1, org.bukkit.command.Command var2, String var3, String[] var4) {
        return null;
    }
}

package com.iridium.iridiumskyblock;

//import com.sk89q.worldedit.CuboidClipboard;
//import com.sk89q.worldedit.EditSession;
//import com.sk89q.worldedit.bukkit.BukkitUtil;
//import com.sk89q.worldedit.bukkit.BukkitWorld;
//import com.sk89q.worldedit.schematic.SchematicFormat;
//import org.bukkit.Location;

import org.bukkit.Location;

import java.io.File;

public class WorldEdit6 implements WorldEdit {
    @Override
    public void paste(File file, Location location) {
//        try {
//            EditSession editSession = new EditSession(new BukkitWorld(location.getWorld()), 999999999);
//            editSession.enableQueue();
//
//            SchematicFormat schematic = SchematicFormat.getFormat(file);
//            CuboidClipboard clipboard = schematic.load(file);
//
//            clipboard.paste(editSession, BukkitUtil.toVector(location), true);
//            editSession.flushQueue();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}

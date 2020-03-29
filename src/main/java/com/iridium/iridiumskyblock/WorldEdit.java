package com.iridium.iridiumskyblock;

import org.bukkit.Location;

import java.io.File;

public interface WorldEdit {
    int version();

    void paste(File file, Location location, Island island);
}

package com.iridium.iridiumskyblock.support;

import org.bukkit.block.CreatureSpawner;

public class MergedSpawners {

    public static boolean enabled = false;

    public MergedSpawners() {
        enabled = true;
    }

    public static int getSpawnerAmount(CreatureSpawner spawner) {
        return MergedSpawners.getSpawnerAmount(spawner);
    }
}

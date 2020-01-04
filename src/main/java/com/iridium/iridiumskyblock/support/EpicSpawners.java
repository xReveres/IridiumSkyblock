package com.iridium.iridiumskyblock.support;

import org.bukkit.block.CreatureSpawner;

public class EpicSpawners {

    public static boolean enabled = false;

    public EpicSpawners() {
        enabled = true;
    }

    public static int getSpawnerAmount(CreatureSpawner spawner) {
        return com.songoda.epicspawners.EpicSpawners.getInstance().getSpawnerManager().getSpawnerFromWorld(spawner.getLocation()).getSpawnCount();
    }
}

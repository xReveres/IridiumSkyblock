package com.iridium.iridiumskyblock.support;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import org.bukkit.block.CreatureSpawner;

public class EpicSpawners {

    public static boolean enabled = false;

    public EpicSpawners() {
        IridiumSkyblock.getInstance().getLogger().info("EpicSpawners support loaded");
        enabled = true;
    }

    public static int getSpawnerAmount(CreatureSpawner spawner) {
        return com.songoda.epicspawners.EpicSpawners.getInstance().getSpawnerManager().getSpawnerFromWorld(spawner.getLocation()).getSpawnCount();
    }
}

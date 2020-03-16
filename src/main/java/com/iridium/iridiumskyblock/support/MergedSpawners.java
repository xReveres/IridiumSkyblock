package com.iridium.iridiumskyblock.support;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.vk2gpz.mergedspawner.api.MergedSpawnerAPI;
import org.bukkit.block.CreatureSpawner;

public class MergedSpawners {

    public static boolean enabled = false;

    public MergedSpawners() {
        IridiumSkyblock.getInstance().getLogger().info("MergedSpawners support loaded");
        enabled = true;
    }

    public static int getSpawnerAmount(CreatureSpawner spawner) {
        return MergedSpawnerAPI.getInstance().getCountFor(spawner.getBlock());
    }
}

package com.iridium.iridiumskyblock.support;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import org.bukkit.block.CreatureSpawner;

public class UltimateStacker {

    public static boolean enabled = false;

    public UltimateStacker() {
        IridiumSkyblock.getInstance().getLogger().info("UltimateStacker support loaded");
        enabled = true;
    }

    public static int getSpawnerAmount(CreatureSpawner spawner) {
        return com.songoda.ultimatestacker.UltimateStacker.getInstance().getSpawnerStackManager().getSpawner(spawner.getBlock()).getAmount();
    }
}

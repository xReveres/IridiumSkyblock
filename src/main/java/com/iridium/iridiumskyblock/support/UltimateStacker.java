package com.iridium.iridiumskyblock.support;

import org.bukkit.block.CreatureSpawner;

public class UltimateStacker {

    public static boolean enabled = false;

    public UltimateStacker() {
        enabled = true;
    }

    public static int getSpawnerAmount(CreatureSpawner spawner) {
        return com.songoda.ultimatestacker.UltimateStacker.getInstance().getSpawnerStackManager().getSpawner(spawner.getBlock()).getAmount();
    }
}

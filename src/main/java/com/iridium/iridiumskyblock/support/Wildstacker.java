package com.iridium.iridiumskyblock.support;

import com.bgsoftware.wildstacker.api.WildStackerAPI;
import com.iridium.iridiumskyblock.IridiumSkyblock;
import org.bukkit.block.CreatureSpawner;

public class Wildstacker {

    public static boolean enabled = false;

    public Wildstacker() {
        IridiumSkyblock.getInstance().getLogger().info("Wildstacker support loaded");
        enabled = true;
    }

    public static int getSpawnerAmount(CreatureSpawner spawner) {
        return WildStackerAPI.getSpawnersAmount(spawner);
    }
}

package com.iridium.iridiumskyblock.support;

import com.bgsoftware.wildstacker.api.WildStackerAPI;
import org.bukkit.block.CreatureSpawner;

public class Wildstacker {

    public static boolean enabled = false;

    public Wildstacker() {
        enabled = true;
    }

    public static int getSpawnerAmount(CreatureSpawner spawner) {
        return WildStackerAPI.getSpawnersAmount(spawner);
    }
}

package com.iridium.iridiumskyblock.support;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import dev.rosewood.rosestacker.api.RoseStackerAPI;
import org.bukkit.block.CreatureSpawner;

public class RoseStacker {

    public static boolean enabled = false;

    public RoseStacker() {
        IridiumSkyblock.getInstance().getLogger().info("RoseStacker support loaded");
        enabled = true;
    }

    public static int getSpawnerAmount(CreatureSpawner spawner) {
        return RoseStackerAPI.getInstance().getStackedSpawner(spawner.getBlock()).getStackSize();
    }
}

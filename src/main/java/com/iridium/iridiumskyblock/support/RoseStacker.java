package com.iridium.iridiumskyblock.support;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import dev.rosewood.rosestacker.api.RoseStackerAPI;
import org.bukkit.block.CreatureSpawner;

public class RoseStacker implements SpawnerSupport{

    public RoseStacker() {
        IridiumSkyblock.getInstance().getLogger().info("RoseStacker support loaded");
    }

    public int getSpawnerAmount(CreatureSpawner spawner) {
        return RoseStackerAPI.getInstance().getStackedSpawner(spawner.getBlock()).getStackSize();
    }
}

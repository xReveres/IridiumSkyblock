package com.iridium.iridiumskyblock.configs;

import com.iridium.iridiumskyblock.MultiversionMaterials;
import org.bukkit.Material;

import java.util.HashMap;

public class BlockValues {
    public HashMap<MultiversionMaterials, Integer> blockvalue = new HashMap<MultiversionMaterials, Integer>() {{
        put(MultiversionMaterials.DIAMOND_BLOCK, 10);
        put(MultiversionMaterials.EMERALD_BLOCK, 20);
        put(MultiversionMaterials.BEACON, 100);
    }};
    public HashMap<String, Integer> spawnervalue = new HashMap<String, Integer>() {{
        put("PIG", 100);
        put("IRON_GOLEM", 1000);
    }};
}

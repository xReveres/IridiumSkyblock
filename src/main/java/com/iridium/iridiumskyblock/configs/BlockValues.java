package com.iridium.iridiumskyblock.configs;

import org.bukkit.Material;

import java.util.HashMap;

public class BlockValues {
    public HashMap<Material, Integer> blockvalue = new HashMap<Material, Integer>() {{
        put(Material.DIAMOND_BLOCK, 10);
        put(Material.EMERALD_BLOCK, 20);
        put(Material.BEACON, 100);
    }};
    public HashMap<String, Integer> spawnervalue = new HashMap<String, Integer>() {{
        put("PIG", 100);
        put("IRON_GOLEM", 1000);
    }};
}

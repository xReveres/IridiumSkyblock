package com.iridium.iridiumskyblock.configs;

import com.iridium.iridiumskyblock.XMaterial;

import java.util.HashMap;

public class BlockValues {
    public HashMap<XMaterial, Integer> blockvalue = new HashMap<XMaterial, Integer>() {{
        put(XMaterial.DIAMOND_BLOCK, 10);
        put(XMaterial.EMERALD_BLOCK, 20);
        put(XMaterial.BEACON, 100);
    }};
    public HashMap<String, Integer> spawnervalue = new HashMap<String, Integer>() {{
        put("PIG", 100);
        put("IRON_GOLEM", 1000);
    }};
}

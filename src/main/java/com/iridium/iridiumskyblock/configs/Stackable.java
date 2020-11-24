package com.iridium.iridiumskyblock.configs;

import com.cryptomorin.xseries.XMaterial;

import java.util.List;
import java.util.ArrayList;

public class Stackable {

    public List<XMaterial> blockList = new ArrayList() {{
            add(XMaterial.IRON_BLOCK);
            add(XMaterial.GOLD_BLOCK);
            add(XMaterial.DIAMOND_BLOCK);
            add(XMaterial.EMERALD_BLOCK);
            add(XMaterial.NETHERITE_BLOCK);
    }};

    public List<XMaterial> getStackable() {
        return blockList;
    }

}

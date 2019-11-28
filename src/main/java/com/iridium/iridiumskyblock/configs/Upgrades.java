package com.iridium.iridiumskyblock.configs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Upgrades {

    public Upgrade sizeUpgrade = new Upgrade(true, 10, new HashMap<Integer, IslandUpgrade>() {{
        put(1, new IslandUpgrade(15, 0, 50));
        put(2, new IslandUpgrade(15, 0, 100));
        put(3, new IslandUpgrade(15, 0, 150));
    }});

    public Upgrade memberUpgrade = new Upgrade(true, 12, new HashMap<Integer, IslandUpgrade>() {{
        put(1, new IslandUpgrade(15, 0, 9));
        put(2, new IslandUpgrade(15, 0, 18));
        put(3, new IslandUpgrade(15, 0, 27));
    }});

    public Upgrade warpUpgrade = new Upgrade(true, 14, new HashMap<Integer, IslandUpgrade>() {{
        put(1, new IslandUpgrade(15, 0, 2));
        put(2, new IslandUpgrade(15, 0, 5));
        put(3, new IslandUpgrade(15, 0, 9));
    }});

    public Upgrade oresUpgrade = new Upgrade(true, 16, new HashMap<Integer, IslandUpgrade>() {{
        put(1, new IslandUpgrade(15, 0, Arrays.asList("COBBLESTONE:30", "IRON_ORE:30", "COAL_ORE:30", "DIAMOND_ORE:10")));
        put(2, new IslandUpgrade(15, 0, Arrays.asList("COBBLESTONE:15", "IRON_ORE:40", "COAL_ORE:40", "DIAMOND_ORE:20")));
        put(3, new IslandUpgrade(15, 0, Arrays.asList("COBBLESTONE:5", "IRON_ORE:50", "COAL_ORE:50", "DIAMOND_ORE:30")));
    }});

    public static class Upgrade {
        public boolean enabled;
        public int slot;
        public HashMap<Integer, IslandUpgrade> upgrades;

        public Upgrade(boolean enabled, int slot, HashMap<Integer, IslandUpgrade> upgrades) {
            this.enabled = enabled;
            this.slot = slot;
            this.upgrades = upgrades;
        }
    }

    public static class IslandUpgrade {
        public int crystalsCost;
        public int vaultCost;
        public int size;
        public List<String> ores;
        public List<String> netherores;

        public IslandUpgrade(int crystalsCost, int vaultCost, List<String> ores) {
            this.crystalsCost = crystalsCost;
            this.vaultCost = vaultCost;
            this.ores = ores;
            this.netherores = ores;
        }

        public IslandUpgrade(int crystalsCost, int vaultCost, int size) {
            this.crystalsCost = crystalsCost;
            this.vaultCost = vaultCost;
            this.size = size;
        }
    }
}

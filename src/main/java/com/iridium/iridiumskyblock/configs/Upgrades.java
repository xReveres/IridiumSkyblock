package com.iridium.iridiumskyblock.configs;

import com.iridium.iridiumskyblock.IridiumSkyblock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Upgrades {

    public HashMap<Integer, Upgrades.OreUpgrade> ores = new HashMap<Integer, Upgrades.OreUpgrade>() {{
        put(1, new OreUpgrade(15, new ArrayList<>(Arrays.asList("COBBLESTONE:30", "IRON_ORE:30", "COAL_ORE:30", "DIAMOND_ORE:10")), new ArrayList<>(Arrays.asList("COBBLESTONE:30", "IRON_ORE:30", "COAL_ORE:30", "DIAMOND_ORE:10"))));
        put(2, new OreUpgrade(15, new ArrayList<>(Arrays.asList("COBBLESTONE:15", "IRON_ORE:40", "COAL_ORE:40", "DIAMOND_ORE:20")), new ArrayList<>(Arrays.asList("COBBLESTONE:15", "IRON_ORE:40", "COAL_ORE:40", "DIAMOND_ORE:20"))));
        put(3, new OreUpgrade(15, new ArrayList<>(Arrays.asList("COBBLESTONE:5", "IRON_ORE:50", "COAL_ORE:50", "DIAMOND_ORE:30")), new ArrayList<>(Arrays.asList("COBBLESTONE:5", "IRON_ORE:50", "COAL_ORE:50", "DIAMOND_ORE:30"))));
    }};

    public HashMap<Integer, Upgrades.IntegerUpgrade> size = new HashMap<Integer, Upgrades.IntegerUpgrade>() {{
        put(1, new IntegerUpgrade(50, 15));
        put(2, new IntegerUpgrade(100, 15));
        put(3, new IntegerUpgrade(150, 15));
    }};

    public HashMap<Integer, Upgrades.IntegerUpgrade> member = new HashMap<Integer, Upgrades.IntegerUpgrade>() {{
        put(1, new IntegerUpgrade(9, 15));
        put(2, new IntegerUpgrade(18, 15));
        put(3, new IntegerUpgrade(27, 15));
    }};

    public HashMap<Integer, Upgrades.IntegerUpgrade> warp = new HashMap<Integer, Upgrades.IntegerUpgrade>() {{
        put(1, new IntegerUpgrade(2, 15));
        put(2, new IntegerUpgrade(5, 15));
        put(3, new IntegerUpgrade(9, 15));
    }};

    public static class OreUpgrade {
        private int cost;
        private List<String> ores;
        private List<String> netherores;

        public OreUpgrade(int cost, List<String> ores, List<String> netherores) {
            this.cost = cost;
            this.ores = ores;
            this.netherores = netherores;
        }

        public int getCost() {
            return cost;
        }

        public List<String> getOres() {
            return ores;
        }

        public List<String> getNetherores() {
            if (netherores == null) {
                netherores = ores;
                IridiumSkyblock.getInstance().saveConfigs();
            }
            return netherores;
        }
    }

    public static class IntegerUpgrade {
        private int size;
        private int cost;

        public IntegerUpgrade(int size, int cost) {
            this.size = size;
            this.cost = cost;
        }

        public int getSize() {
            return size;
        }

        public int getCost() {
            return cost;
        }
    }
}

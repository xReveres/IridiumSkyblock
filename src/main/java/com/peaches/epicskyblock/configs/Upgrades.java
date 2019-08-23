package com.peaches.epicskyblock.configs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Upgrades {

    public HashMap<Integer, Upgrades.OreUpgrade> ores = new HashMap<Integer, Upgrades.OreUpgrade>() {{
        put(1, new Upgrades.OreUpgrade(15, new ArrayList<>(Arrays.asList("COBBLESTONE:30", "IRON_ORE:30", "COAL_ORE:30", "DIAMOND_ORE:10"))));
        put(2, new Upgrades.OreUpgrade(15, new ArrayList<>(Arrays.asList("COBBLESTONE:15", "IRON_ORE:40", "COAL_ORE:40", "DIAMOND_ORE:20"))));
        put(3, new Upgrades.OreUpgrade(15, new ArrayList<>(Arrays.asList("COBBLESTONE:5", "IRON_ORE:50", "COAL_ORE:50", "DIAMOND_ORE:30"))));
    }};

    public HashMap<Integer, Upgrades.Upgrade> size = new HashMap<Integer, Upgrades.Upgrade>() {{
        put(1, new Upgrades.Upgrade(50, 15));
        put(2, new Upgrades.Upgrade(100, 15));
        put(3, new Upgrades.Upgrade(150, 15));
    }};

    public HashMap<Integer, Upgrades.Upgrade> member = new HashMap<Integer, Upgrades.Upgrade>() {{
        put(1, new Upgrades.Upgrade(9, 15));
        put(2, new Upgrades.Upgrade(18, 15));
        put(3, new Upgrades.Upgrade(27, 15));
    }};

    public HashMap<Integer, Upgrades.Upgrade> warp = new HashMap<Integer, Upgrades.Upgrade>() {{
        put(1, new Upgrades.Upgrade(2, 15));
        put(2, new Upgrades.Upgrade(5, 15));
        put(3, new Upgrades.Upgrade(9, 15));
    }};

    public class OreUpgrade {
        private int cost;
        private List<String> ores;

        public OreUpgrade(int cost, List<String> ores) {
            this.cost = cost;
            this.ores = ores;
        }

        public int getCost() {
            return cost;
        }

        public List<String> getOres() {
            return ores;
        }
    }

    public class Upgrade {
        private int size;
        private int cost;

        public Upgrade(int size, int cost) {
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

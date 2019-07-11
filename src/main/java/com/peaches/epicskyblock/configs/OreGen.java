package com.peaches.epicskyblock.configs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class OreGen {
    public HashMap<Integer, Upgrade> ores = new HashMap<Integer, Upgrade>() {{
        put(1, new Upgrade(15, new ArrayList<>(Arrays.asList("COBBLESTONE:30", "IRON_ORE:30", "COAL_ORE:30", "DIAMOND_ORE:10"))));
        put(2, new Upgrade(15, new ArrayList<>(Arrays.asList("COBBLESTONE:15", "IRON_ORE:40", "COAL_ORE:40", "DIAMOND_ORE:20"))));
        put(3, new Upgrade(15, new ArrayList<>(Arrays.asList("COBBLESTONE:5", "IRON_ORE:50", "COAL_ORE:50", "DIAMOND_ORE:30"))));
    }};


    public class Upgrade {
        private int cost;
        private List<String> ores;

        public Upgrade(int cost, List<String> ores) {
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
}

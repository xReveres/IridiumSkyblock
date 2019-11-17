package com.iridium.iridiumskyblock.configs;

public class Boosters {

    public Booster spawnerBooster = new Booster(15, 3600, true, 10);
    public Booster farmingBooster = new Booster(15, 3600, true, 12);
    public Booster experianceBooster = new Booster(15, 3600, true, 14);
    public Booster flightBooster = new Booster(15, 3600, true, 16);

    public static class Booster {
        public int cost;
        public int time;
        boolean enabled;
        public int slot;

        public Booster(int cost, int time, boolean enabled, int slot) {
            this.cost = cost;
            this.time = time;
            this.enabled = enabled;
            this.slot = slot;
        }

        public int getSlot() {
            return slot;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public int getCost() {
            return cost;
        }

        public int getTime() {
            return time;
        }
    }
}

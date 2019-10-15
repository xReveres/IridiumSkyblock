package com.iridium.iridiumskyblock.configs;

public class Boosters {

    public Booster spawnerBooster = new Booster(15, true, 10);
    public Booster farmingBooster = new Booster(15, true, 12);
    public Booster experianceBooster = new Booster(15, true, 14);
    public Booster flightBooster = new Booster(15, true, 16);

    public static class Booster {
        public int cost;
        public boolean enabled;
        public int slot;

        public Booster(int cost, boolean enabled, int slot) {
            this.cost = cost;
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
    }
}

package com.iridium.iridiumskyblock.configs;

public class Missions {
    public static class Mission {
        public int amount;
        public int crystalReward;
        public int vaultReward;

        public Mission(int amount, int crystalReward, int vaultReward) {
            this.amount = amount;
            this.crystalReward = crystalReward;
            this.vaultReward = vaultReward;
        }
    }

    public Mission treasureHunter = new Mission(1000, 15, 0);
    public Mission competitor = new Mission(10000, 15, 0);
    public Mission miner = new Mission(1000, 15, 0);
    public Mission farmer = new Mission(5000, 15, 0);
    public Mission hunter = new Mission(1000, 15, 0);
    public Mission fisherman = new Mission(100, 15, 0);
    public Mission builder = new Mission(10000, 15, 0);
}

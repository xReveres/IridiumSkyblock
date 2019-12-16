package com.iridium.iridiumskyblock.configs;

import com.iridium.iridiumskyblock.MissionType;
import com.iridium.iridiumskyblock.MultiversionMaterials;
import org.bukkit.CropState;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Missions {
    public List<Mission> missions = Arrays.asList(
            new Mission(15, 10000, 1000, MissionType.EXPERIENCE, "Treasure Hunter", Collections.emptyList()),
            new Mission(15, 10000, 10000, MissionType.VALUE_INCREASE, "Competitor", Collections.emptyList()),
            new Mission(15, 10000, 1000, MissionType.BLOCK_BREAK, "Miner", Arrays.asList(MultiversionMaterials.COAL_ORE.toString(), MultiversionMaterials.DIAMOND_ORE.toString(), MultiversionMaterials.EMERALD_ORE.toString(), MultiversionMaterials.GOLD_ORE.toString(), MultiversionMaterials.IRON_ORE.toString(), MultiversionMaterials.LAPIS_ORE.toString(), MultiversionMaterials.NETHER_QUARTZ_ORE.toString(), MultiversionMaterials.REDSTONE_ORE.toString())),
            new Mission(15, 10000, 5000, MissionType.BLOCK_BREAK, "Farmer", Collections.singletonList(CropState.RIPE.toString())),
            new Mission(15, 10000, 1000, MissionType.ENTITY_KILL, "Hunter", Collections.emptyList()),
            new Mission(15, 10000, 100, MissionType.FISH_CATCH, "Fisherman", Collections.emptyList()),
            new Mission(15, 10000, 10000, MissionType.BLOCK_PLACE, "Builder", Collections.emptyList())
    );

    public static class Mission {
        public int crystalReward;
        public int vaultReward;
        public int amount;
        public MissionType type;
        public String name;
        public List<String> conditions;

        public Mission(int crystalReward, int vaultReward, int amount, MissionType type, String name, List<String> conditions) {
            this.crystalReward = crystalReward;
            this.vaultReward = vaultReward;
            this.amount = amount;
            this.type = type;
            this.name = name;
            this.conditions = conditions;
        }
    }
}

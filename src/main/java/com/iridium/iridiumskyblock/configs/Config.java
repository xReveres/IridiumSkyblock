package com.iridium.iridiumskyblock.configs;

import com.iridium.iridiumskyblock.MissionRestart;
import com.iridium.iridiumskyblock.Permissions;
import com.iridium.iridiumskyblock.Role;
import org.bukkit.Material;
import org.bukkit.block.Biome;

import java.util.HashMap;

public class Config {

    public Config() {
        try {
            netherBiome = Biome.HELL;
        } catch (NoSuchFieldError e) {
            netherBiome = Biome.valueOf("NETHER");
        }
    }

    public String prefix = "&b&lIridiumSkyblock &8»";
    public String worldName = "IridiumSkyblock";
    public String chatRankPlaceholder = "[ISLAND_RANK]";
    public String chatValuePlaceholder = "[ISLAND_VALUE]";
    public String chatNAMEPlaceholder = "[ISLAND_NAME]";
    public boolean netherIslands = true;
    public boolean islandMenu = true;
    public boolean voidTeleport = true;
    public boolean notifyAvailableUpdate = true;
    public boolean disableExplosions = true;
    public boolean clearInventories = true;
    public int distance = 151;
    public double valuePerLevel = 100.00;
    public int blocksPerTick = 50;
    public int islandsUpdateInterval = 5;
    public Biome defaultBiome = Biome.PLAINS;
    public Biome netherBiome;
    public MissionRestart missionRestart = MissionRestart.Daily;
    public HashMap<Role, Permissions> defaultPermissions = new HashMap<Role, Permissions>() {{
        for (Role role : Role.values()) {
            if (role == Role.Visitor) {
                put(role, new Permissions(false, false, false, false, false, false, false, false, false, true, true, false, false, false));
            } else {
                put(role, new Permissions());
            }
        }
    }};
    public HashMap<Material, Integer> blockvalue = new HashMap<Material, Integer>() {{
        put(Material.DIAMOND_BLOCK, 10);
        put(Material.EMERALD_BLOCK, 20);
        put(Material.BEACON, 100);
    }};
    public HashMap<String, Integer> spawnervalue = new HashMap<String, Integer>() {{
        put("PIG", 100);
        put("IRON_GOLEM", 1000);
    }};
    public HashMap<Integer, Integer> islandTopSlots = new HashMap<Integer, Integer>() {{
        put(1, 4);
        put(2, 12);
        put(3, 14);
        put(4, 19);
        put(5, 20);
        put(6, 21);
        put(7, 22);
        put(8, 23);
        put(9, 24);
        put(10, 25);
    }};
}

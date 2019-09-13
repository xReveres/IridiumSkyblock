package com.peaches.epicskyblock.configs;

import com.peaches.epicskyblock.MissionRestart;
import org.bukkit.Material;
import org.bukkit.block.Biome;

import java.util.HashMap;

public class Config {

    public String prefix = "&b&lEpicSkyblock";
    public String UpgradeGUITitle = "&7Upgrade";
    public String BoosterGUITitle = "&7Booster";
    public String MissionsGUITitle = "&7Missions";
    public String MembersGUITitle = "&7Members";
    public String WarpGUITitle = "&7Warps";
    public String TopGUITitle = "&7Top Islands";
    public String BorderColorGUITitle = "&7Border Color";
    public String PermissionsGUITitle = "&7Permissions";
    public int distance = 200;
    public Biome defaultBiome = Biome.PLAINS;
    public MissionRestart missionRestart = MissionRestart.Daily;
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

package com.iridium.iridiumskyblock.configs;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public class Schematics {
    public List<FakeSchematic> schematics = Arrays.asList(new FakeSchematic("island.schematic", "nether.schematic", new Location(IridiumSkyblock.getIslandManager().getWorld(), 0.5, 97, -1.5), "", Material.GRASS, "&b&lDefault Island", Arrays.asList("&7The default island")));

    public static class FakeSchematic {
        public String name;
        public Location spawn;
        public String netherisland;
        public String permission;
        public Material item;
        public String displayname;
        public List<String> lore;

        public FakeSchematic(String name, String netherisland, Location spawn, String permission, Material item, String displayname, List<String> lore) {
            this.name = name;
            this.netherisland = netherisland;
            this.permission = permission;
            this.item = item;
            this.displayname = displayname;
            this.lore = lore;
        }

        public Location getSpawn() {
            if (spawn == null) {
                spawn = new Location(IridiumSkyblock.getIslandManager().getWorld(), 0.5, 97, -1.5);
                IridiumSkyblock.getInstance().saveConfigs();
            }
            return spawn;
        }
    }
}

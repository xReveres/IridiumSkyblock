package com.iridium.iridiumskyblock.configs;

import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public class Schematics {
    public List<FakeSchematic> schematics = Arrays.asList(new FakeSchematic("island.schematic", "nether.schematic", 0.5, 97.00, -1.5, "", Material.GRASS, "&b&lDefault Island", Arrays.asList("&7The default island")));

    public static class FakeSchematic {
        public String name;
        public double x;
        public double y;
        public double z;
        public String netherisland;
        public String permission;
        public Material item;
        public String displayname;
        public List<String> lore;

        public FakeSchematic(String name, String netherisland, double x, double y, double z, String permission, Material item, String displayname, List<String> lore) {
            this.name = name;
            this.netherisland = netherisland;
            this.permission = permission;
            this.item = item;
            this.displayname = displayname;
            this.lore = lore;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}

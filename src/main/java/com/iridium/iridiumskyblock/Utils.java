package com.iridium.iridiumskyblock;

import com.iridium.iridiumskyblock.configs.Inventories;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static Biome getNextBiome(Biome biome) {
        boolean next = false;
        for (Biome b : Biome.values()) {
            if (next) {
                return b;
            }
            if (b.equals(biome)) {
                next = true;
            }
        }
        return Biome.values()[0];
    }

    public static Biome getPreviousBiome(Biome biome) {
        int id = -1;
        for (int i = 0; i < Biome.values().length; i++) {
            if (Biome.values()[i].equals(biome)) {
                if (i != 0) {
                    return Biome.values()[i - 1];
                }
            }
        }
        return Biome.values()[Biome.values().length - 1];
    }

    public static String unColour(String string) {
        return string.replace(ChatColor.AQUA + "", "&b");
    }

    public static ItemStack makeItem(Material material, int amount, int type, String name) {
        ItemStack item = new ItemStack(material, amount, (short) type);
        ItemMeta m = item.getItemMeta();
        m.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        item.setItemMeta(m);
        return item;
    }

    public static ItemStack makeItem(Material material, int amount, int type, String name, List<String> lore) {
        ItemStack item = new ItemStack(material, amount, (short) type);
        ItemMeta m = item.getItemMeta();
        m.setLore(Utils.color(lore));
        m.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        item.setItemMeta(m);
        return item;
    }

    public static ItemStack makeItem(Inventories.Item item, Island island) {
        return makeItem(item.getMaterial(), item.getAmount(), item.getType(), processIslandPlaceholders(item.getTitle(), island), color(processIslandPlaceholders(item.getLore(), island)));
    }

    public static ItemStack makeItemHidden(Inventories.Item item, Island island) {
        return makeItemHidden(item.getMaterial(), item.getAmount(), item.getType(), processIslandPlaceholders(item.getTitle(), island), color(processIslandPlaceholders(item.getLore(), island)));
    }

    public static ItemStack makeItemHidden(Material material, int amount, int type, String name, List<String> lore) {
        ItemStack item = new ItemStack(material, amount, (short) type);
        ItemMeta m = item.getItemMeta();
        m.setLore(Utils.color(lore));
        m.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE);
        m.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        item.setItemMeta(m);
        return item;
    }

    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static List<String> color(List<String> strings) {
        return strings.stream().map(Utils::color).collect(Collectors.toList());
    }

    public static boolean isBlockValuable(Block b) {
        return IridiumSkyblock.getConfiguration().blockvalue.containsKey(b.getType()) || b.getState() instanceof CreatureSpawner;
    }

    public static List<Island> getTopIslands() {
        List<Island> islands = new ArrayList<>(IridiumSkyblock.getIslandManager().islands.values());
        islands.sort(Comparator.comparingInt(Island::getValue));
        Collections.reverse(islands);
        return islands;
    }


    public static boolean isSafe(Location loc, Island island) {
        return (island.isInIsland(loc) && loc.getBlock().getType().equals(Material.AIR)
                && (!loc.clone().add(0, -1, 0).getBlock().getType().equals(Material.AIR) && !loc.clone().add(0, -1, 0).getBlock().isLiquid()));
    }

    public static int getIslandRank(Island island) {
        int i = 1;
        for (Island is : getTopIslands()) {
            if (is.equals(island)) {
                return i;
            }
            i++;
        }
        return 0;
    }

    public static Location getNewHome(Island island, Location loc) {
        Block b;
        b = loc.getWorld().getHighestBlockAt(loc);
        if (isSafe(b.getLocation(), island)) {
            return b.getLocation().add(0.5, 1, 0.5);
        }

        for (double X = island.getPos1().getX(); X <= island.getPos2().getX(); X++) {
            for (double Z = island.getPos1().getZ(); Z <= island.getPos2().getZ(); Z++) {
                b = loc.getWorld().getHighestBlockAt((int) X, (int) Z);
                if (isSafe(b.getLocation(), island)) {
                    return b.getLocation().add(0.5, 1, 0.5);
                }
            }
        }
        return null;
    }

    public static String processIslandPlaceholders(String line, Island island) {
        return processMultiplePlaceholders(line, new Placeholder("generatorcost", IridiumSkyblock.getUpgrades().ores.containsKey(island.getOreLevel() + 1) ? IridiumSkyblock.getUpgrades().ores.get(island.getOreLevel() + 1).getCost() + "" : "Max Level Reached"), new Placeholder("generatorlevel", island.getOreLevel() + ""), new Placeholder("warpcost", IridiumSkyblock.getUpgrades().warp.containsKey(island.getWarpLevel() + 1) ? IridiumSkyblock.getUpgrades().warp.get(island.getWarpLevel() + 1).getCost() + "" : "Max Level Reached"), new Placeholder("warpcount", IridiumSkyblock.getUpgrades().warp.get(island.getWarpLevel()).getSize() + ""), new Placeholder("warplevel", island.getWarpLevel() + ""), new Placeholder("membercost", IridiumSkyblock.getUpgrades().member.containsKey(island.getMemberLevel() + 1) ? IridiumSkyblock.getUpgrades().member.get(island.getMemberLevel() + 1).getCost() + "" : "Max Level Reached"), new Placeholder("membercount", IridiumSkyblock.getUpgrades().member.get(island.getMemberLevel()).getSize() + ""), new Placeholder("memberlevel", island.getMemberLevel() + ""), new Placeholder("sizecost", IridiumSkyblock.getUpgrades().size.containsKey(island.getSizeLevel() + 1) ? IridiumSkyblock.getUpgrades().size.get(island.getSizeLevel() + 1).getCost() + "" : "Max Level Reached"), new Placeholder("sizeblocks", IridiumSkyblock.getUpgrades().size.get(island.getSizeLevel()).getSize() + ""), new Placeholder("sizelevel", island.getSizeLevel() + ""), new Placeholder("spawnerbooster", island.getSpawnerBooster() + ""), new Placeholder("farmingbooster", island.getFarmingBooster() + ""), new Placeholder("expbooster", island.getExpBooster() + ""), new Placeholder("flightbooster", island.getFlightBooster() + ""), new Placeholder("treasurehunterstatus", ((island.treasureHunter != -1 ? island.treasureHunter + "/" + IridiumSkyblock.getMissions().treasureHunter.getAmount() : "Completed"))), new Placeholder("competitorstatus", ((island.competitor != -1 ? island.competitor + "/" + IridiumSkyblock.getMissions().competitor.getAmount() : "Completed"))), new Placeholder("minerstatus", ((island.miner != -1 ? island.miner + "/" + IridiumSkyblock.getMissions().miner.getAmount() : "Completed"))), new Placeholder("farmerstatus", ((island.farmer != -1 ? island.farmer + "/" + IridiumSkyblock.getMissions().farmer.getAmount() : "Completed"))), new Placeholder("hunterstatus", ((island.hunter != -1 ? island.hunter + "/" + IridiumSkyblock.getMissions().hunter.getAmount() : "Completed"))), new Placeholder("fishermanstatus", ((island.fisherman != -1 ? island.fisherman + "/" + IridiumSkyblock.getMissions().fisherman.getAmount() : "Completed"))), new Placeholder("builderstatus", ((island.builder != -1 ? island.builder + "/" + IridiumSkyblock.getMissions().builder.getAmount() : "Completed"))));
    }

    public static List<String> processIslandPlaceholders(List<String> lines, Island island) {
        return processMultiplePlaceholders(lines, new Placeholder("generatorcost", IridiumSkyblock.getUpgrades().ores.containsKey(island.getOreLevel() + 1) ? IridiumSkyblock.getUpgrades().ores.get(island.getOreLevel() + 1).getCost() + "" : "Max Level Reached"), new Placeholder("generatorlevel", island.getOreLevel() + ""), new Placeholder("warpcost", IridiumSkyblock.getUpgrades().warp.containsKey(island.getWarpLevel() + 1) ? IridiumSkyblock.getUpgrades().warp.get(island.getWarpLevel() + 1).getCost() + "" : "Max Level Reached"), new Placeholder("warpcount", IridiumSkyblock.getUpgrades().warp.get(island.getWarpLevel()).getSize() + ""), new Placeholder("warplevel", island.getWarpLevel() + ""), new Placeholder("membercost", IridiumSkyblock.getUpgrades().member.containsKey(island.getMemberLevel() + 1) ? IridiumSkyblock.getUpgrades().member.get(island.getMemberLevel() + 1).getCost() + "" : "Max Level Reached"), new Placeholder("membercount", IridiumSkyblock.getUpgrades().member.get(island.getMemberLevel()).getSize() + ""), new Placeholder("memberlevel", island.getMemberLevel() + ""), new Placeholder("sizecost", IridiumSkyblock.getUpgrades().size.containsKey(island.getSizeLevel() + 1) ? IridiumSkyblock.getUpgrades().size.get(island.getSizeLevel() + 1).getCost() + "" : "Max Level Reached"), new Placeholder("sizeblocks", IridiumSkyblock.getUpgrades().size.get(island.getSizeLevel()).getSize() + ""), new Placeholder("sizelevel", island.getSizeLevel() + ""), new Placeholder("spawnerbooster", island.getSpawnerBooster() + ""), new Placeholder("farmingbooster", island.getFarmingBooster() + ""), new Placeholder("expbooster", island.getExpBooster() + ""), new Placeholder("flightbooster", island.getFlightBooster() + ""), new Placeholder("treasurehunterstatus", ((island.treasureHunter != -1 ? island.treasureHunter + "/" + IridiumSkyblock.getMissions().treasureHunter.getAmount() : "Completed"))), new Placeholder("competitorstatus", ((island.competitor != -1 ? island.competitor + "/" + IridiumSkyblock.getMissions().competitor.getAmount() : "Completed"))), new Placeholder("minerstatus", ((island.miner != -1 ? island.miner + "/" + IridiumSkyblock.getMissions().miner.getAmount() : "Completed"))), new Placeholder("farmerstatus", ((island.farmer != -1 ? island.farmer + "/" + IridiumSkyblock.getMissions().farmer.getAmount() : "Completed"))), new Placeholder("hunterstatus", ((island.hunter != -1 ? island.hunter + "/" + IridiumSkyblock.getMissions().hunter.getAmount() : "Completed"))), new Placeholder("fishermanstatus", ((island.fisherman != -1 ? island.fisherman + "/" + IridiumSkyblock.getMissions().fisherman.getAmount() : "Completed"))), new Placeholder("builderstatus", ((island.builder != -1 ? island.builder + "/" + IridiumSkyblock.getMissions().builder.getAmount() : "Completed"))));
    }

    public static String processMultiplePlaceholders(String line, Placeholder... placeholders) {
        for (Placeholder placeholder : placeholders) {
            line = placeholder.process(line);
        }
        return color(line);
    }

    public static List<String> processMultiplePlaceholders(List<String> lines, Placeholder... placeholders) {
        List<String> newlist = new ArrayList<String>();
        for (String string : lines) {
            newlist.add(processMultiplePlaceholders(string, placeholders));
        }
        return newlist;
//        return lines.stream().map(Utils::processMultiplePlaceholders).collect(Collectors.toList());
    }

    public static class Placeholder {

        private String key;
        private String value;

        public Placeholder(String key, String value) {
            this.key = "{" + key + "}";
            this.value = value;
        }

        public String process(String line) {
            return line.replace(key, value);
        }
    }
}

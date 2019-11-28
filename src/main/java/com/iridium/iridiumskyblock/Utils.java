package com.iridium.iridiumskyblock;

import com.iridium.iridiumskyblock.configs.Inventories;
import com.iridium.iridiumskyblock.support.Vault;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class Utils {

    public static Object getObject(List<String> fields) {
        Object object = IridiumSkyblock.getInstance();
        for (String field : fields) {
            try {
                if (object instanceof List) {
                    List<Object> objects = (List<Object>) object;
                    if (objects.size() > Integer.parseInt(field)) {
                        object = objects.get(Integer.parseInt(field));
                    } else {
                        return object;
                    }
                } else if (object instanceof HashSet) {
                    HashSet<Object> objects = (HashSet<Object>) object;
                    int i = 0;
                    for (Object o : objects) {
                        if ((i == Integer.parseInt(field))) {
                            object = o;
                        }
                        i++;
                    }
                } else {
                    object = object.getClass().getField(field).get(object);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return object;
    }

    public static Field getField(List<String> fields) {
        Object object = IridiumSkyblock.getInstance();
        Field f = null;
        for (String field : fields) {
            try {
                if (object instanceof List) {
                    List<Object> objects = (List<Object>) object;
                    if (objects.size() > Integer.parseInt(field)) {
                        f = object.getClass().getField(field);
                        f.setAccessible(true);
                        object = objects.get(Integer.parseInt(field));
                    } else {
                        return f;
                    }
                } else if (object instanceof HashSet) {
                    HashSet<Object> objects = (HashSet<Object>) object;
                    int i = 0;
                    for (Object o : objects) {
                        if ((i == Integer.parseInt(field))) {
                            f = object.getClass().getField(field);
                            f.setAccessible(true);
                            object = o;
                        }
                        i++;
                    }
                } else {
                    f = object.getClass().getField(field);
                    f.setAccessible(true);
                    object = object.getClass().getField(field).get(object);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return f;
    }

    public static ItemStack makeItem(Material material, int amount, int type, String name, List<String> lore, Object object) {
        ItemStack item = new ItemStack(material, amount, (short) type);
        ItemMeta m = item.getItemMeta();
        m.setLore(lore);
        m.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        item.setItemMeta(m);
        return item;
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
        try {
            return makeItem(item.material, item.amount, item.type, processIslandPlaceholders(item.title, island), color(processIslandPlaceholders(item.lore, island)));
        } catch (Exception e) {
            try {
                return makeItem(Material.getMaterial("LEGACY_" + item.material.name()), item.amount, item.type, processIslandPlaceholders(item.title, island), color(processIslandPlaceholders(item.lore, island)));
            } catch (Exception ex) {
                try {
                    return makeItem(Material.getMaterial("LEGACY_" + MultiversionMaterials.fromString(item.material.name()).name()), item.amount, item.type, processIslandPlaceholders(item.title, island), color(processIslandPlaceholders(item.lore, island)));

                } catch (Exception ex1) {
                    return makeItem(Material.STONE, item.amount, item.type, processIslandPlaceholders(item.title, island), color(processIslandPlaceholders(item.lore, island)));
                }
            }
        }
    }

    public static ItemStack makeItemHidden(Inventories.Item item) {
        try {
            return makeItemHidden(item.material, item.amount, item.type, item.title, item.lore);
        } catch (Exception e) {
            try {
                return makeItemHidden(Material.getMaterial("LEGACY_" + item.material.name()), item.amount, item.type, item.title, item.lore);
            } catch (Exception ex) {
                return makeItemHidden(Material.STONE, item.amount, item.type, item.title, item.lore);
            }
        }
    }

    public static ItemStack makeItemHidden(Inventories.Item item, Island island) {
        try {
            return makeItemHidden(item.material, item.amount, item.type, processIslandPlaceholders(item.title, island), color(processIslandPlaceholders(item.lore, island)));
        } catch (Exception e) {
            try {
                return makeItemHidden(Material.getMaterial("LEGACY_" + item.material.name()), item.amount, item.type, processIslandPlaceholders(item.title, island), color(processIslandPlaceholders(item.lore, island)));
            } catch (Exception ex) {
                return makeItemHidden(Material.STONE, item.amount, item.type, processIslandPlaceholders(item.title, island), color(processIslandPlaceholders(item.lore, island)));
            }
        }
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

    public static List<Island> getIslands() {
        List<Island> islands = new ArrayList<>(IridiumSkyblock.getIslandManager().islands.values());
        islands.sort(Comparator.comparingInt(Island::getVotes));
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
        return processMultiplePlaceholders(line,
                // Upgrades
                new Placeholder("sizevaultcost", IridiumSkyblock.getUpgrades().sizeUpgrade.upgrades.containsKey(island.getSizeLevel() + 1) ? IridiumSkyblock.getUpgrades().sizeUpgrade.upgrades.get(island.getSizeLevel() + 1).vaultCost + "" : IridiumSkyblock.getMessages().maxlevelreached),
                new Placeholder("membervaultcost", IridiumSkyblock.getUpgrades().memberUpgrade.upgrades.containsKey(island.getMemberLevel() + 1) ? IridiumSkyblock.getUpgrades().memberUpgrade.upgrades.get(island.getMemberLevel() + 1).vaultCost + "" : IridiumSkyblock.getMessages().maxlevelreached),
                new Placeholder("warpvaultcost", IridiumSkyblock.getUpgrades().warpUpgrade.upgrades.containsKey(island.getWarpLevel() + 1) ? IridiumSkyblock.getUpgrades().warpUpgrade.upgrades.get(island.getWarpLevel() + 1).vaultCost + "" : IridiumSkyblock.getMessages().maxlevelreached),
                new Placeholder("oresvaultcost", IridiumSkyblock.getUpgrades().oresUpgrade.upgrades.containsKey(island.getOreLevel() + 1) ? IridiumSkyblock.getUpgrades().oresUpgrade.upgrades.get(island.getOreLevel() + 1).vaultCost + "" : IridiumSkyblock.getMessages().maxlevelreached),

                new Placeholder("sizecrystalscost", IridiumSkyblock.getUpgrades().sizeUpgrade.upgrades.containsKey(island.getSizeLevel() + 1) ? IridiumSkyblock.getUpgrades().sizeUpgrade.upgrades.get(island.getSizeLevel() + 1).crystalsCost + "" : IridiumSkyblock.getMessages().maxlevelreached),
                new Placeholder("membercrystalscost", IridiumSkyblock.getUpgrades().memberUpgrade.upgrades.containsKey(island.getMemberLevel() + 1) ? IridiumSkyblock.getUpgrades().memberUpgrade.upgrades.get(island.getMemberLevel() + 1).crystalsCost + "" : IridiumSkyblock.getMessages().maxlevelreached),
                new Placeholder("warpcrystalscost", IridiumSkyblock.getUpgrades().warpUpgrade.upgrades.containsKey(island.getWarpLevel() + 1) ? IridiumSkyblock.getUpgrades().warpUpgrade.upgrades.get(island.getWarpLevel() + 1).crystalsCost + "" : IridiumSkyblock.getMessages().maxlevelreached),
                new Placeholder("orescrystalscost", IridiumSkyblock.getUpgrades().oresUpgrade.upgrades.containsKey(island.getOreLevel() + 1) ? IridiumSkyblock.getUpgrades().oresUpgrade.upgrades.get(island.getOreLevel() + 1).crystalsCost + "" : IridiumSkyblock.getMessages().maxlevelreached),

                new Placeholder("sizecost", IridiumSkyblock.getUpgrades().sizeUpgrade.upgrades.containsKey(island.getSizeLevel() + 1) ? IridiumSkyblock.getUpgrades().sizeUpgrade.upgrades.get(island.getSizeLevel() + 1).crystalsCost + "" : IridiumSkyblock.getMessages().maxlevelreached),
                new Placeholder("membercost", IridiumSkyblock.getUpgrades().memberUpgrade.upgrades.containsKey(island.getMemberLevel() + 1) ? IridiumSkyblock.getUpgrades().memberUpgrade.upgrades.get(island.getMemberLevel() + 1).crystalsCost + "" : IridiumSkyblock.getMessages().maxlevelreached),
                new Placeholder("warpcost", IridiumSkyblock.getUpgrades().warpUpgrade.upgrades.containsKey(island.getWarpLevel() + 1) ? IridiumSkyblock.getUpgrades().warpUpgrade.upgrades.get(island.getWarpLevel() + 1).crystalsCost + "" : IridiumSkyblock.getMessages().maxlevelreached),
                new Placeholder("generatorcost", IridiumSkyblock.getUpgrades().oresUpgrade.upgrades.containsKey(island.getOreLevel() + 1) ? IridiumSkyblock.getUpgrades().oresUpgrade.upgrades.get(island.getOreLevel() + 1).crystalsCost + "" : IridiumSkyblock.getMessages().maxlevelreached),

                new Placeholder("sizeblocks", IridiumSkyblock.getUpgrades().sizeUpgrade.upgrades.get(island.getSizeLevel()).size + ""),
                new Placeholder("membercount", IridiumSkyblock.getUpgrades().memberUpgrade.upgrades.get(island.getMemberLevel()).size + ""),
                new Placeholder("warpcount", IridiumSkyblock.getUpgrades().warpUpgrade.upgrades.get(island.getMemberLevel()).size + ""),

                new Placeholder("sizelevel", island.getSizeLevel() + ""),
                new Placeholder("memberlevel", island.getMemberLevel() + ""),
                new Placeholder("warplevel", island.getWarpLevel() + ""),
                new Placeholder("oreslevel", island.getOreLevel() + ""),
                new Placeholder("generatorlevel", island.getOreLevel() + ""),
                // Boosters
                new Placeholder("spawnerbooster", island.getSpawnerBooster() + ""),
                new Placeholder("farmingbooster", island.getFarmingBooster() + ""),
                new Placeholder("expbooster", island.getExpBooster() + ""),
                new Placeholder("flightbooster", island.getFlightBooster() + ""),
                // Missions
                new Placeholder("treasurehunterstatus", ((island.treasureHunter != -1 ? island.treasureHunter + "/" + IridiumSkyblock.getMissions().treasureHunter.amount : "Completed"))),
                new Placeholder("competitorstatus", ((island.competitor != Integer.MIN_VALUE ? island.competitor + "/" + IridiumSkyblock.getMissions().competitor.amount : "Completed"))),
                new Placeholder("minerstatus", ((island.miner != -1 ? island.miner + "/" + IridiumSkyblock.getMissions().miner.amount : "Completed"))),
                new Placeholder("farmerstatus", ((island.farmer != -1 ? island.farmer + "/" + IridiumSkyblock.getMissions().farmer.amount : "Completed"))),
                new Placeholder("hunterstatus", ((island.hunter != -1 ? island.hunter + "/" + IridiumSkyblock.getMissions().hunter.amount : "Completed"))),
                new Placeholder("fishermanstatus", ((island.fisherman != -1 ? island.fisherman + "/" + IridiumSkyblock.getMissions().fisherman.amount : "Completed"))),
                new Placeholder("builderstatus", ((island.builder != -1 ? island.builder + "/" + IridiumSkyblock.getMissions().builder.amount : "Completed"))),

                new Placeholder("treasurehunteramount", island.treasureHunter + ""),
                new Placeholder("competitoramount", island.competitor + ""),
                new Placeholder("mineramount", island.miner + ""),
                new Placeholder("farmeramount", island.fisherman + ""),
                new Placeholder("hunteramount", island.hunter + ""),
                new Placeholder("fishermanamount", island.fisherman + ""),
                new Placeholder("builderamount", island.builder + ""),

                new Placeholder("treasurehuntercrystals", IridiumSkyblock.getMissions().treasureHunter.crystalReward + ""),
                new Placeholder("competitorcrystals", IridiumSkyblock.getMissions().competitor.crystalReward + ""),
                new Placeholder("minercrystals", IridiumSkyblock.getMissions().miner.crystalReward + ""),
                new Placeholder("farmercrystals", IridiumSkyblock.getMissions().farmer.crystalReward + ""),
                new Placeholder("huntercrystals", IridiumSkyblock.getMissions().hunter.crystalReward + ""),
                new Placeholder("fishermancrystals", IridiumSkyblock.getMissions().fisherman.crystalReward + ""),
                new Placeholder("buildercrystals", IridiumSkyblock.getMissions().builder.crystalReward + ""),

                new Placeholder("treasurehuntervault", IridiumSkyblock.getMissions().treasureHunter.vaultReward + ""),
                new Placeholder("competitorvault", IridiumSkyblock.getMissions().competitor.vaultReward + ""),
                new Placeholder("minervault", IridiumSkyblock.getMissions().miner.vaultReward + ""),
                new Placeholder("farmervault", IridiumSkyblock.getMissions().farmer.vaultReward + ""),
                new Placeholder("huntervault", IridiumSkyblock.getMissions().hunter.vaultReward + ""),
                new Placeholder("fishermanvault", IridiumSkyblock.getMissions().fisherman.vaultReward + ""),
                new Placeholder("buildervault", IridiumSkyblock.getMissions().builder.vaultReward + ""),

                //Bank
                new Placeholder("experience", island.exp + ""),
                new Placeholder("crystals", island.getCrystals() + ""),
                new Placeholder("money", island.money + ""));
    }

    public static List<String> processIslandPlaceholders(List<String> lines, Island island) {
        List<String> newlist = new ArrayList<>();
        for (String string : lines) {
            newlist.add(processIslandPlaceholders(string, island));
        }
        return newlist;
    }

    public static String processMultiplePlaceholders(String line, Placeholder... placeholders) {
        for (Placeholder placeholder : placeholders) {
            line = placeholder.process(line);
        }
        return color(line);
    }

    public static boolean canBuy(Player p, int vault, int crystals) {
        User u = User.getUser(p);
        if (u.getIsland() != null) {
            if (Vault.econ != null) {
                boolean canbuy = (Vault.econ.getBalance(p) >= vault || u.getIsland().money >= vault) && u.getIsland().getCrystals() >= crystals;
                if (canbuy) {
                    u.getIsland().setCrystals(u.getIsland().getCrystals() - crystals);
                    if (u.getIsland().money >= vault) {
                        u.getIsland().money -= vault;
                    } else {
                        Vault.econ.withdrawPlayer(p, vault);
                    }
                }
                return canbuy;
            } else {
                boolean canbuy = u.getIsland().getCrystals() >= crystals;
                if (canbuy) {
                    u.getIsland().setCrystals(u.getIsland().getCrystals() - crystals);
                }
                return canbuy;
            }
        }
        return false;
    }

    public static int getExpAtLevel(final int level) {
        if (level <= 15) {
            return (2 * level) + 7;
        } else if (level <= 30) {
            return (5 * level) - 38;
        }
        return (9 * level) - 158;
    }

    public static int getTotalExperience(final Player player) {
        int exp = Math.round(getExpAtLevel(player.getLevel()) * player.getExp());
        int currentLevel = player.getLevel();

        while (currentLevel > 0) {
            currentLevel--;
            exp += getExpAtLevel(currentLevel);
        }
        if (exp < 0) {
            exp = Integer.MAX_VALUE;
        }
        return exp;
    }

    public static void setTotalExperience(final Player player, final int exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("Experience is negative!");
        }
        player.setExp(0);
        player.setLevel(0);
        player.setTotalExperience(0);

        int amount = exp;
        while (amount > 0) {
            final int expToLevel = getExpAtLevel(player.getLevel());
            amount -= expToLevel;
            if (amount >= 0) {
                // give until next level
                player.giveExp(expToLevel);
            } else {
                // give the rest
                amount += expToLevel;
                player.giveExp(amount);
                amount = 0;
            }
        }
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

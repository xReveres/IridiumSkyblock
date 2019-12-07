package com.iridium.iridiumskyblock.configs;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Inventories {


    public String upgradeGUITitle = "&7Upgrade";
    public String boosterGUITitle = "&7Booster";
    public String confirmationGUITitle = "&7Are you sure you want to do that";
    public String missionsGUITitle = "&7Missions";
    public String membersGUITitle = "&7Members";
    public String coopGUITitle = "&7Co-op Islands";
    public String islandMenuGUITitle = "&7Menu";
    public String warpGUITitle = "&7Warps";
    public String topGUITitle = "&7Top Islands";
    public String borderColorGUITitle = "&7Border Color";
    public String permissionsGUITitle = "&7Permissions";
    public String schematicselectGUITitle = "&7Select an Island";
    public String bankGUITitle = "&7Island Bank";
    public String visitGUITitle = "&7Visit an Island";

    public int upgradeGUISize = 27;
    public int boosterGUISize = 27;
    public int missionsGUISize = 27;
    public int membersGUISize = 27;
    public int coopGUISize = 27;
    public int islandMenuGUISize = 27;
    public int warpGUISize = 27;
    public int topGUISize = 27;
    public int borderColorGUISize = 27;
    public int permissionsGUISize = 27;
    public int schematicselectGUISize = 27;
    public int bankGUISize = 27;
    public int visitGUISize = 54;

    //Boosters
    public Item spawner = new Item(Material.MOB_SPAWNER, 1, 0, "&b&lIncreased Mobs", Arrays.asList("&7Are your spawners too slow? Buy this", "&7booster and increase spawner rates x2.", "", "&b&lInformation:", "&b&l * &7Time Remaining: &b{spawnerbooster_minutes} minutes and {spawnerbooster_seconds}seconds", "&b&l * &7Booster Cost: &b" + IridiumSkyblock.getBoosters().spawnerBooster.crystalsCost + " Crystals and $" + IridiumSkyblock.getBoosters().spawnerBooster.vaultCost, "", "&b&l[!] &bRight Click to Purchase this Booster."));
    public Item farming = new Item(Material.WHEAT, 1, 0, "&b&lIncreased Crops", Arrays.asList("&7Are your crops too slow? Buy this", "&7booster and increase crop growth rates x2.", "", "&b&lInformation:", "&b&l * &7Time Remaining: &b{farmingbooster_minutes} minutes and {farmingbooster_seconds}seconds", "&b&l * &7Booster Cost: &b" + IridiumSkyblock.getBoosters().farmingBooster.crystalsCost + " Crystals and $" + IridiumSkyblock.getBoosters().farmingBooster.vaultCost, "", "&b&l[!] &bRight Click to Purchase this Booster."));
    public Item exp = new Item(Material.EXP_BOTTLE, 1, 0, "&b&lIncreased Experience", Arrays.asList("&7Takes too long to get exp? Buy this", "&7booster and exp rates x2.", "", "&b&lInformation:", "&b&l * &7Time Remaining: &b{expbooster_minutes} minutes and {expbooster_seconds}seconds", "&b&l * &7Booster Cost: &b" + IridiumSkyblock.getBoosters().experianceBooster.crystalsCost + " Crystals and $" + IridiumSkyblock.getBoosters().experianceBooster.vaultCost, "", "&b&l[!] &bRight Click to Purchase this Booster."));
    public Item flight = new Item(Material.FEATHER, 1, 0, "&b&lIncreased Flight", Arrays.asList("&7Tired of falling off your island? Buy this", "&7booster and allow all members to fly.", "", "&b&lInformation:", "&b&l * &7Time Remaining: &b{flightbooster_minutes} minutes and {flightbooster_seconds}seconds", "&b&l * &7Booster Cost: &b" + IridiumSkyblock.getBoosters().flightBooster.crystalsCost + " Crystals and $" + IridiumSkyblock.getBoosters().flightBooster.vaultCost, "", "&b&l[!] &bRight Click to Purchase this Booster."));
    //Upgrade
    public Item size = new Item(Material.GRASS, 1, 0, "&b&lIsland Size", Arrays.asList("&7Need more room to expand? Buy this", "&7upgrade to increase your island size.", "", "&b&lInformation:", "&b&l * &7Current Level: &b{sizelevel}", "&b&l * &7Current Size: &b{sizeblocks}x{sizeblocks} Blocks", "&b&l * &7Upgrade Cost: &b{sizecrystalscost} Crystals and ${sizevaultcost}", "&b&lLevels:", "&b&l * &7Level 1: &b50x50 Blocks", "&b&l * &7Level 2: &b100x100 Blocks", "&b&l * &7Level 3: &b150x150 Blocks", "", "&b&l[!] &bLeft Click to Purchase this Upgrade"));
    public Item member = new Item(Material.ARMOR_STAND, 1, 0, "&b&lMember Count", Arrays.asList("&7Need more members? Buy this", "&7upgrade to increase your member count.", "", "&b&lInformation:", "&b&l * &7Current Level: &b{memberlevel}", "&b&l * &7Current Members: &b{membercount} Members", "&b&l * &7Upgrade Cost: &b{membercrystalscost} Crystals and ${membervaultcost}", "&b&lLevels:", "&b&l * &7Level 1: &b9 Members", "&b&l * &7Level 2: &b18 Members", "&b&l * &7Level 3: &b27 Members", "", "&b&l[!] &bLeft Click to Purchase this Upgrade"));
    public Item warp = new Item(Material.ENDER_PORTAL_FRAME, 1, 0, "&b&lIsland Warps", Arrays.asList("&7Need more island warps? Buy this", "&7upgrade to increase your warp count.", "", "&b&lInformation:", "&b&l * &7Current Level: &b{warplevel}", "&b&l * &7Current Warps: &b{warpcount} Warps", "&b&l * &7Upgrade Cost: &b{warpcrystalscost} Crystals and ${warpvaultcost}", "&b&lLevels:", "&b&l * &7Level 1: &b2 Warps", "&b&l * &7Level 2: &b5 Warps", "&b&l * &7Level 3: &b9 Warps", "", "&b&l[!] &bLeft Click to Purchase this Upgrade"));
    public Item ores = new Item(Material.DIAMOND_ORE, 1, 0, "&b&lIsland Generator", Arrays.asList("&7Want to improve your generator? Buy this", "&7upgrade to increase your island generator.", "", "&b&lInformation:", "&b&l * &7Current Level: &b{oreslevel}", "&b&l * &7Upgrade Cost: &b{orescrystalscost} Crystals and ${oresvaultcost}", "", "&b&l[!] &bLeft Click to Purchase this Upgrade"));

    //Menu
    public Item home = new Item(Material.BED, 0, 1, 0, "&b&lIsland Home", Collections.singletonList("&7Teleport to your island home"));
    public Item members = new Item(Material.SKULL_ITEM, 1, 1, 0, "&b&lIsland Members", Collections.singletonList("&7View your island Members."));
    public Item regen = new Item(Material.GRASS, 2, 1, 0, "&b&lIsland Regen", Collections.singletonList("&7Regenerate your island."));
    public Item upgrades = new Item(Material.BREWING_STAND, 3, 1, 0, "&b&lIsland Upgrades", Collections.singletonList("&7Upgrade your island."));
    public Item missions = new Item(Material.IRON_SWORD, 4, 1, 0, "&b&lIsland Missions", Collections.singletonList("&7View island missions."));
    public Item boosters = new Item(Material.GLOWSTONE_DUST, 5, 1, 0, "&b&lIsland Boosters", Collections.singletonList("&7Boost your island."));
    public Item permissions = new Item(Material.IRON_FENCE, 6, 1, 0, "&b&lIsland Permissions", Collections.singletonList("&7Change island permissions."));
    public Item top = new Item(Material.DIAMOND, 7, 1, 0, "&b&lIsland Top", Collections.singletonList("&7View top islands."));
    public Item warps = new Item(Material.ENDER_PORTAL_FRAME, 8, 1, 0, "&b&lIsland Warps", Collections.singletonList("&7View your island warps."));
    public Item border = new Item(Material.BLAZE_POWDER, 9, 1, 0, "&b&lIsland Border", Collections.singletonList("&7Change your island border."));
    public Item coop = new Item(Material.REDSTONE, 10, 1, 0, "&b&lIsland Coop", Collections.singletonList("&7View your Co-op Islands."));
    public Item bank = new Item(Material.PAPER, 11, 1, 0, "&b&lIsland Bank", Collections.singletonList("&7View your Island Bank."));
    public Item delete = new Item(Material.BARRIER, 27, 1, 0, "&b&lIsland Delete", Collections.singletonList("&7Delete your island."));

    //Bank
    public Item experience = new Item(Material.EXP_BOTTLE, 11, 1, 0, "&b&lIsland Experience", Arrays.asList("&7{experience} Experience", "&b&l[!] &bLeft click to withdraw", "&b&l[!] &bRight click to deposit"));
    public Item crystals = new Item(Material.NETHER_STAR, 13, 1, 0, "&b&lIsland Crystals", Arrays.asList("&7{crystals} Crystals", "&b&l[!] &bLeft click to withdraw", "&b&l[!] &bRight click to deposit"));
    public Item money = new Item(Material.PAPER, 15, 1, 0, "&b&lIsland Money", Arrays.asList("&7${money}", "&b&l[!] &bLeft click to withdraw", "&b&l[!] &bRight click to deposit"));

    public Item crystal = new Item(Material.NETHER_STAR, 1, 0, "&b*** &b&lIsland Crystal &b***", Arrays.asList("", "&b&l[!] &bto Redeem, go to your Island Bank"));

    public Item background = new Item(Material.STAINED_GLASS_PANE, 1, 15, " ", new ArrayList<>());

    public List<Item> missionsItems = Arrays.asList(
            new Item(Material.EXP_BOTTLE, 10, 1, 0, "&b&lTreasure Hunter", Arrays.asList("&7Complete island missions to gain crystals", "&7that can be spent on Boosters and Upgrades.", "", "&b&lInformation:", "&b&l * &7Objective: &bCollect {Treasure Hunteramount} Experience", "&b&l * &7Current Status: &b{Treasure Hunterstatus}", "&b&l * &7Reward: &b{Treasure Huntercrystals} Island Crystals and ${Treasure Huntervault}", "", "&b&l[!] &bComplete this mission for rewards.")),
            new Item(Material.GOLD_INGOT, 11, 1, 0, "&b&lCompetitor", Arrays.asList("&7Complete island missions to gain crystals", "&7that can be spent on Boosters and Upgrades.", "", "&b&lInformation:", "&b&l * &7Objective: &bGain {Competitoramount} Island Value", "&b&l * &7Current Status: &b{Competitorstatus}", "&b&l * &7Reward: &b{Competitorcrystals} Island Crystals and ${Competitorvault}", "", "&b&l[!] &bComplete this mission for rewards.")),
            new Item(Material.DIAMOND_ORE, 12, 1, 0, "&b&lMiner", Arrays.asList("&7Complete island missions to gain crystals", "&7that can be spent on Boosters and Upgrades.", "", "&b&lInformation:", "&b&l * &7Objective: &bDestroy {Mineramount} Ores", "&b&l * &7Current Status: &b{Minerstatus}", "&b&l * &7Reward: &b{Minercrystals} Island Crystals and ${Minervault}", "", "&b&l[!] &bComplete this mission for rewards.")),
            new Item(Material.SUGAR_CANE, 13, 1, 0, "&b&lFarmer", Arrays.asList("&7Complete island missions to gain crystals", "&7that can be spent on Boosters and Upgrades.", "", "&b&lInformation:", "&b&l * &7Objective: &bHarvest {Farmeramount} Crops", "&b&l * &7Current Status: &b{Farmerstatus}", "&b&l * &7Reward: &b{Farmercrystals} Island Crystals and ${Farmervault}", "", "&b&l[!] &bComplete this mission for rewards.")),
            new Item(Material.BLAZE_POWDER, 14, 1, 0, "&b&lHunter", Arrays.asList("&7Complete island missions to gain crystals", "&7that can be spent on Boosters and Upgrades.", "", "&b&lInformation:", "&b&l * &7Objective: &bKill {Hunteramount} Mobs", "&b&l * &7Current Status: &b{Hunterstatus}", "&b&l * &7Reward: &b{Huntercrystals} Island Crystals and ${Huntervault}", "", "&b&l[!] &bComplete this mission for rewards.")),
            new Item(Material.FISHING_ROD, 15, 1, 0, "&b&lFisherman", Arrays.asList("&7Complete island missions to gain crystals", "&7that can be spent on Boosters and Upgrades.", "", "&b&lInformation:", "&b&l * &7Objective: &bCatch {Fishermanamount} Fish", "&b&l * &7Current Status: &b{Fishermanstatus}", "&b&l * &7Reward: &b{Fishermancrystals} Island Crystals and ${Fishermanvault}", "", "&b&l[!] &bComplete this mission for rewards.")),
            new Item(Material.COBBLESTONE, 16, 1, 0, "&b&lBuilder", Arrays.asList("&7Complete island missions to gain crystals", "&7that can be spent on Boosters and Upgrades.", "", "&b&lInformation:", "&b&l * &7Objective: &bPlace {Builderamount} Blocks", "&b&l * &7Current Status: &b{Builderstatus}", "&b&l * &7Reward: &b{Buildercrystals} Island Crystals and ${Buildervault}", "", "&b&l[!] &bComplete this mission for rewards."))
    );

    public static class Item {

        public Material material;
        public int amount;
        public int type;
        public String title;
        public List<String> lore;
        public Integer slot;

        public Item(Material material, int amount, int type, String title, List<String> lore) {
            this.material = material;
            this.amount = amount;
            this.type = type;
            this.lore = lore;
            this.title = title;
        }

        public Item(Material material, int slot, int amount, int type, String title, List<String> lore) {
            this.material = material;
            this.amount = amount;
            this.type = type;
            this.lore = lore;
            this.title = title;
            this.slot = slot;
        }
    }
}

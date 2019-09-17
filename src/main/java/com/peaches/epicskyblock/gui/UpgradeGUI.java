package com.peaches.epicskyblock.gui;

import com.peaches.epicskyblock.EpicSkyblock;
import com.peaches.epicskyblock.Island;
import com.peaches.epicskyblock.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UpgradeGUI implements Listener {

    public Inventory inventory;
    public ItemStack size;
    public ItemStack member;
    public ItemStack warp;
    public ItemStack ores;
    public int islandID;
    public int scheduler;

    public UpgradeGUI(Island island) {
        this.inventory = Bukkit.createInventory(null, 27, Utils.color(EpicSkyblock.getConfiguration().UpgradeGUITitle));
        islandID = island.getId();
        scheduler = Bukkit.getScheduler().scheduleAsyncRepeatingTask(EpicSkyblock.getInstance(), this::addContent, 0, 10);
        EpicSkyblock.getInstance().registerListeners(this);
    }

    public void addContent() {
        try {
            if (EpicSkyblock.getIslandManager().islands.containsKey(islandID)) {
                Island island = EpicSkyblock.getIslandManager().islands.get(islandID);
                for (int i = 0; i < 27; i++) {
                    inventory.setItem(i, Utils.makeItem(Material.STAINED_GLASS_PANE, 1, 15, " "));
                }

                int currentsize = island.getSizeLevel();
                String sizecost = EpicSkyblock.getUpgrades().size.containsKey(currentsize + 1) ? EpicSkyblock.getUpgrades().size.get(currentsize + 1).getCost() + " Crystals" : "Max Level Reached";
                List<String> sizeLore = new ArrayList<>(Arrays.asList("&7Need more room to expand? Buy this", "&7upgrade to increase your island size.", "", "&b&lInformation:", "&b&l * &7Current Level: &b" + currentsize, "&b&l * &7Current Size: &b" + EpicSkyblock.getUpgrades().size.get(currentsize).getSize() + "x" + EpicSkyblock.getUpgrades().size.get(currentsize).getSize() + " Blocks", "&b&l * &7Upgrade Cost: &b" + sizecost, "", "&b&lLevels:"));
                for (int level : EpicSkyblock.getUpgrades().size.keySet()) {
                    sizeLore.add("&b&l * &7Level " + level + ": &b" + EpicSkyblock.getUpgrades().size.get(level).getSize() + "x" + EpicSkyblock.getUpgrades().size.get(level).getSize() + " Blocks");
                }
                sizeLore.add("");
                sizeLore.add("&b&l[!] &bLeft Click to Purchase this Upgrade");
                this.size = Utils.makeItem(Material.GRASS, 1, 0, "&b&lIsland Size", Utils.color(sizeLore));


                int currentmember = island.getMemberLevel();
                String membercost = EpicSkyblock.getUpgrades().member.containsKey(currentmember + 1) ? EpicSkyblock.getUpgrades().member.get(currentmember + 1).getCost() + " Crystals" : "Max Level Reached";
                List<String> memberLore = new ArrayList<>(Arrays.asList("&7Need more members? Buy this", "&7upgrade to increase your member count.", "", "&b&lInformation:", "&b&l * &7Current Level: &b" + currentmember, "&b&l * &7Current Members: &b" + EpicSkyblock.getUpgrades().member.get(currentmember).getSize() + " Members", "&b&l * &7Upgrade Cost: &b" + membercost, "", "&b&lLevels:"));
                for (int level : EpicSkyblock.getUpgrades().member.keySet()) {
                    memberLore.add("&b&l * &7Level " + level + ": &b" + EpicSkyblock.getUpgrades().member.get(level).getSize() + " Members");
                }
                memberLore.add("");
                memberLore.add("&b&l[!] &bLeft Click to Purchase this Upgrade");
                this.member = Utils.makeItem(Material.ARMOR_STAND, 1, 0, "&b&lIsland Member Count", Utils.color(memberLore));


                int currentwarp = island.getWarpLevel();
                String warpcost = EpicSkyblock.getUpgrades().warp.containsKey(currentwarp + 1) ? EpicSkyblock.getUpgrades().warp.get(currentwarp + 1).getCost() + " Crystals" : "Max Level Reached";
                List<String> warpLore = new ArrayList<>(Arrays.asList("&7Need more island warps? Buy this", "&7upgrade to increase your warp count.", "", "&b&lInformation:", "&b&l * &7Current Level: &b" + currentwarp, "&b&l * &7Current Warps: &b" + EpicSkyblock.getUpgrades().warp.get(currentwarp).getSize() + " Warps", "&b&l * &7Upgrade Cost: &b" + warpcost, "", "&b&lLevels:"));
                for (int level : EpicSkyblock.getUpgrades().warp.keySet()) {
                    warpLore.add("&b&l * &7Level " + level + ": &b" + EpicSkyblock.getUpgrades().warp.get(level).getSize() + " Warps");
                }
                warpLore.add("");
                warpLore.add("&b&l[!] &bLeft Click to Purchase this Upgrade");
                this.warp = Utils.makeItem(Material.ENDER_PORTAL_FRAME, 1, 0, "&b&lIsland Warp", Utils.color(warpLore));

                int currentores = island.getOreLevel();
                String orescost = EpicSkyblock.getUpgrades().ores.containsKey(currentwarp + 1) ? EpicSkyblock.getUpgrades().ores.get(currentwarp + 1).getCost() + " Crystals" : "Max Level Reached";
                List<String> oresLore = new ArrayList<>(Arrays.asList("&7Want to improve your generator? Buy this", "&7upgrade to increase your island generator.", "", "&b&lInformation:", "&b&l * &7Current Level: &b" + currentores, "&b&l * &7Upgrade Cost: &b" + orescost));
                oresLore.add("");
                oresLore.add("&b&l[!] &bLeft Click to Purchase this Upgrade");
                this.ores = Utils.makeItem(Material.DIAMOND_ORE, 1, 0, "&b&lIsland Generator", Utils.color(oresLore));
                inventory.setItem(10, size);
                inventory.setItem(12, member);
                inventory.setItem(14, warp);
                inventory.setItem(16, ores);
            }
        } catch (Exception e) {
            EpicSkyblock.getInstance().sendErrorMessage(e);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        if(e.getInventory().equals(inventory)){
            e.setCancelled(true);
            if (e.getCurrentItem().equals(ores)) {
                if (EpicSkyblock.getUpgrades().ores.containsKey(EpicSkyblock.getIslandManager().getIslandViaId(islandID).getOreLevel() + 1)) {
                    if (EpicSkyblock.getIslandManager().getIslandViaId(islandID).getCrystals() >= EpicSkyblock.getUpgrades().ores.get(EpicSkyblock.getIslandManager().getIslandViaId(islandID).getOreLevel() + 1).getCost()) {
                        EpicSkyblock.getIslandManager().getIslandViaId(islandID).setCrystals(EpicSkyblock.getIslandManager().getIslandViaId(islandID).getCrystals() - EpicSkyblock.getUpgrades().ores.get(EpicSkyblock.getIslandManager().getIslandViaId(islandID).getOreLevel() + 1).getCost());
                        EpicSkyblock.getIslandManager().getIslandViaId(islandID).setOreLevel(EpicSkyblock.getIslandManager().getIslandViaId(islandID).getOreLevel() + 1);
                    } else {
                        e.getWhoClicked().sendMessage(Utils.color(EpicSkyblock.getMessages().notEnoughCrystals.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
                    }
                } else {
                    e.getWhoClicked().sendMessage(Utils.color(EpicSkyblock.getMessages().maxLevelReached.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
                }
            }
            if (e.getCurrentItem().equals(size)) {
                if (EpicSkyblock.getUpgrades().size.containsKey(EpicSkyblock.getIslandManager().getIslandViaId(islandID).getSizeLevel() + 1)) {
                    if (EpicSkyblock.getIslandManager().getIslandViaId(islandID).getCrystals() >= EpicSkyblock.getUpgrades().size.get(EpicSkyblock.getIslandManager().getIslandViaId(islandID).getSizeLevel() + 1).getCost()) {
                        EpicSkyblock.getIslandManager().getIslandViaId(islandID).setCrystals(EpicSkyblock.getIslandManager().getIslandViaId(islandID).getCrystals() - EpicSkyblock.getUpgrades().size.get(EpicSkyblock.getIslandManager().getIslandViaId(islandID).getSizeLevel() + 1).getCost());
                        EpicSkyblock.getIslandManager().getIslandViaId(islandID).setSizeLevel(EpicSkyblock.getIslandManager().getIslandViaId(islandID).getSizeLevel() + 1);
                        EpicSkyblock.getIslandManager().getIslandViaId(islandID).sendBorder();
                    } else {
                        e.getWhoClicked().sendMessage(Utils.color(EpicSkyblock.getMessages().notEnoughCrystals.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
                    }
                } else {
                    e.getWhoClicked().sendMessage(Utils.color(EpicSkyblock.getMessages().maxLevelReached.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
                }
            }
            if (e.getCurrentItem().equals(member)) {
                if (EpicSkyblock.getUpgrades().member.containsKey(EpicSkyblock.getIslandManager().getIslandViaId(islandID).getMemberLevel() + 1)) {
                    if (EpicSkyblock.getIslandManager().getIslandViaId(islandID).getCrystals() >= EpicSkyblock.getUpgrades().member.get(EpicSkyblock.getIslandManager().getIslandViaId(islandID).getMemberLevel() + 1).getCost()) {
                        EpicSkyblock.getIslandManager().getIslandViaId(islandID).setCrystals(EpicSkyblock.getIslandManager().getIslandViaId(islandID).getCrystals() - EpicSkyblock.getUpgrades().member.get(EpicSkyblock.getIslandManager().getIslandViaId(islandID).getMemberLevel() + 1).getCost());
                        EpicSkyblock.getIslandManager().getIslandViaId(islandID).setMemberLevel(EpicSkyblock.getIslandManager().getIslandViaId(islandID).getMemberLevel() + 1);
                    } else {
                        e.getWhoClicked().sendMessage(Utils.color(EpicSkyblock.getMessages().notEnoughCrystals.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
                    }
                } else {
                    e.getWhoClicked().sendMessage(Utils.color(EpicSkyblock.getMessages().maxLevelReached.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
                }
            }
            if (e.getCurrentItem().equals(warp)) {
                if (EpicSkyblock.getUpgrades().warp.containsKey(EpicSkyblock.getIslandManager().getIslandViaId(islandID).getWarpLevel() + 1)) {
                    if (EpicSkyblock.getIslandManager().getIslandViaId(islandID).getCrystals() >= EpicSkyblock.getUpgrades().warp.get(EpicSkyblock.getIslandManager().getIslandViaId(islandID).getWarpLevel() + 1).getCost()) {
                        EpicSkyblock.getIslandManager().getIslandViaId(islandID).setCrystals(EpicSkyblock.getIslandManager().getIslandViaId(islandID).getCrystals() - EpicSkyblock.getUpgrades().warp.get(EpicSkyblock.getIslandManager().getIslandViaId(islandID).getWarpLevel() + 1).getCost());
                        EpicSkyblock.getIslandManager().getIslandViaId(islandID).setWarpLevel(EpicSkyblock.getIslandManager().getIslandViaId(islandID).getWarpLevel() + 1);
                    } else {
                        e.getWhoClicked().sendMessage(Utils.color(EpicSkyblock.getMessages().notEnoughCrystals.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
                    }
                } else {
                    e.getWhoClicked().sendMessage(Utils.color(EpicSkyblock.getMessages().maxLevelReached.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
                }
            }
        }
    }
}

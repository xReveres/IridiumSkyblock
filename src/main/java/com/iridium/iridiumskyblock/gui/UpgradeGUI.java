package com.iridium.iridiumskyblock.gui;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.Utils;
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
        this.inventory = Bukkit.createInventory(null, 27, Utils.color(IridiumSkyblock.getConfiguration().upgradeGUITitle));
        islandID = island.getId();
        scheduler = Bukkit.getScheduler().scheduleAsyncRepeatingTask(IridiumSkyblock.getInstance(), this::addContent, 0, 10);
        IridiumSkyblock.getInstance().registerListeners(this);
    }

    public void addContent() {
        try {
            if (IridiumSkyblock.getIslandManager().islands.containsKey(islandID)) {
                Island island = IridiumSkyblock.getIslandManager().islands.get(islandID);
                for (int i = 0; i < 27; i++) {
                    inventory.setItem(i, Utils.makeItem(Material.STAINED_GLASS_PANE, 1, 15, " "));
                }

                int currentsize = island.getSizeLevel();
                String sizecost = IridiumSkyblock.getUpgrades().size.containsKey(currentsize + 1) ? IridiumSkyblock.getUpgrades().size.get(currentsize + 1).getCost() + " Crystals" : "Max Level Reached";
                List<String> sizeLore = new ArrayList<>(Arrays.asList("&7Need more room to expand? Buy this", "&7upgrade to increase your island size.", "", "&b&lInformation:", "&b&l * &7Current Level: &b" + currentsize, "&b&l * &7Current Size: &b" + IridiumSkyblock.getUpgrades().size.get(currentsize).getSize() + "x" + IridiumSkyblock.getUpgrades().size.get(currentsize).getSize() + " Blocks", "&b&l * &7Upgrade Cost: &b" + sizecost, "", "&b&lLevels:"));
                for (int level : IridiumSkyblock.getUpgrades().size.keySet()) {
                    sizeLore.add("&b&l * &7Level " + level + ": &b" + IridiumSkyblock.getUpgrades().size.get(level).getSize() + "x" + IridiumSkyblock.getUpgrades().size.get(level).getSize() + " Blocks");
                }
                sizeLore.add("");
                sizeLore.add("&b&l[!] &bLeft Click to Purchase this Upgrade");
                this.size = Utils.makeItem(Material.GRASS, 1, 0, "&b&lIsland Size", Utils.color(sizeLore));


                int currentmember = island.getMemberLevel();
                String membercost = IridiumSkyblock.getUpgrades().member.containsKey(currentmember + 1) ? IridiumSkyblock.getUpgrades().member.get(currentmember + 1).getCost() + " Crystals" : "Max Level Reached";
                List<String> memberLore = new ArrayList<>(Arrays.asList("&7Need more members? Buy this", "&7upgrade to increase your member count.", "", "&b&lInformation:", "&b&l * &7Current Level: &b" + currentmember, "&b&l * &7Current Members: &b" + IridiumSkyblock.getUpgrades().member.get(currentmember).getSize() + " Members", "&b&l * &7Upgrade Cost: &b" + membercost, "", "&b&lLevels:"));
                for (int level : IridiumSkyblock.getUpgrades().member.keySet()) {
                    memberLore.add("&b&l * &7Level " + level + ": &b" + IridiumSkyblock.getUpgrades().member.get(level).getSize() + " Members");
                }
                memberLore.add("");
                memberLore.add("&b&l[!] &bLeft Click to Purchase this Upgrade");
                this.member = Utils.makeItem(Material.ARMOR_STAND, 1, 0, "&b&lIsland Member Count", Utils.color(memberLore));


                int currentwarp = island.getWarpLevel();
                String warpcost = IridiumSkyblock.getUpgrades().warp.containsKey(currentwarp + 1) ? IridiumSkyblock.getUpgrades().warp.get(currentwarp + 1).getCost() + " Crystals" : "Max Level Reached";
                List<String> warpLore = new ArrayList<>(Arrays.asList("&7Need more island warps? Buy this", "&7upgrade to increase your warp count.", "", "&b&lInformation:", "&b&l * &7Current Level: &b" + currentwarp, "&b&l * &7Current Warps: &b" + IridiumSkyblock.getUpgrades().warp.get(currentwarp).getSize() + " Warps", "&b&l * &7Upgrade Cost: &b" + warpcost, "", "&b&lLevels:"));
                for (int level : IridiumSkyblock.getUpgrades().warp.keySet()) {
                    warpLore.add("&b&l * &7Level " + level + ": &b" + IridiumSkyblock.getUpgrades().warp.get(level).getSize() + " Warps");
                }
                warpLore.add("");
                warpLore.add("&b&l[!] &bLeft Click to Purchase this Upgrade");
                this.warp = Utils.makeItem(Material.ENDER_PORTAL_FRAME, 1, 0, "&b&lIsland Warp", Utils.color(warpLore));

                int currentores = island.getOreLevel();
                String orescost = IridiumSkyblock.getUpgrades().ores.containsKey(currentwarp + 1) ? IridiumSkyblock.getUpgrades().ores.get(currentwarp + 1).getCost() + " Crystals" : "Max Level Reached";
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
            IridiumSkyblock.getInstance().sendErrorMessage(e);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        if(e.getInventory().equals(inventory)){
            e.setCancelled(true);
            if(e.getCurrentItem() != null) {
                if (e.getCurrentItem().equals(ores)) {
                    if (IridiumSkyblock.getUpgrades().ores.containsKey(IridiumSkyblock.getIslandManager().getIslandViaId(islandID).getOreLevel() + 1)) {
                        if (IridiumSkyblock.getIslandManager().getIslandViaId(islandID).getCrystals() >= IridiumSkyblock.getUpgrades().ores.get(IridiumSkyblock.getIslandManager().getIslandViaId(islandID).getOreLevel() + 1).getCost()) {
                            IridiumSkyblock.getIslandManager().getIslandViaId(islandID).setCrystals(IridiumSkyblock.getIslandManager().getIslandViaId(islandID).getCrystals() - IridiumSkyblock.getUpgrades().ores.get(IridiumSkyblock.getIslandManager().getIslandViaId(islandID).getOreLevel() + 1).getCost());
                            IridiumSkyblock.getIslandManager().getIslandViaId(islandID).setOreLevel(IridiumSkyblock.getIslandManager().getIslandViaId(islandID).getOreLevel() + 1);
                        } else {
                            e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().notEnoughCrystals.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                        }
                    } else {
                        e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().maxLevelReached.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                    }
                }
                if (e.getCurrentItem().equals(size)) {
                    if (IridiumSkyblock.getUpgrades().size.containsKey(IridiumSkyblock.getIslandManager().getIslandViaId(islandID).getSizeLevel() + 1)) {
                        if (IridiumSkyblock.getIslandManager().getIslandViaId(islandID).getCrystals() >= IridiumSkyblock.getUpgrades().size.get(IridiumSkyblock.getIslandManager().getIslandViaId(islandID).getSizeLevel() + 1).getCost()) {
                            IridiumSkyblock.getIslandManager().getIslandViaId(islandID).setCrystals(IridiumSkyblock.getIslandManager().getIslandViaId(islandID).getCrystals() - IridiumSkyblock.getUpgrades().size.get(IridiumSkyblock.getIslandManager().getIslandViaId(islandID).getSizeLevel() + 1).getCost());
                            IridiumSkyblock.getIslandManager().getIslandViaId(islandID).setSizeLevel(IridiumSkyblock.getIslandManager().getIslandViaId(islandID).getSizeLevel() + 1);
                            IridiumSkyblock.getIslandManager().getIslandViaId(islandID).sendBorder();
                        } else {
                            e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().notEnoughCrystals.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                        }
                    } else {
                        e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().maxLevelReached.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                    }
                }
                if (e.getCurrentItem().equals(member)) {
                    if (IridiumSkyblock.getUpgrades().member.containsKey(IridiumSkyblock.getIslandManager().getIslandViaId(islandID).getMemberLevel() + 1)) {
                        if (IridiumSkyblock.getIslandManager().getIslandViaId(islandID).getCrystals() >= IridiumSkyblock.getUpgrades().member.get(IridiumSkyblock.getIslandManager().getIslandViaId(islandID).getMemberLevel() + 1).getCost()) {
                            IridiumSkyblock.getIslandManager().getIslandViaId(islandID).setCrystals(IridiumSkyblock.getIslandManager().getIslandViaId(islandID).getCrystals() - IridiumSkyblock.getUpgrades().member.get(IridiumSkyblock.getIslandManager().getIslandViaId(islandID).getMemberLevel() + 1).getCost());
                            IridiumSkyblock.getIslandManager().getIslandViaId(islandID).setMemberLevel(IridiumSkyblock.getIslandManager().getIslandViaId(islandID).getMemberLevel() + 1);
                        } else {
                            e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().notEnoughCrystals.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                        }
                    } else {
                        e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().maxLevelReached.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                    }
                }
                if (e.getCurrentItem().equals(warp)) {
                    if (IridiumSkyblock.getUpgrades().warp.containsKey(IridiumSkyblock.getIslandManager().getIslandViaId(islandID).getWarpLevel() + 1)) {
                        if (IridiumSkyblock.getIslandManager().getIslandViaId(islandID).getCrystals() >= IridiumSkyblock.getUpgrades().warp.get(IridiumSkyblock.getIslandManager().getIslandViaId(islandID).getWarpLevel() + 1).getCost()) {
                            IridiumSkyblock.getIslandManager().getIslandViaId(islandID).setCrystals(IridiumSkyblock.getIslandManager().getIslandViaId(islandID).getCrystals() - IridiumSkyblock.getUpgrades().warp.get(IridiumSkyblock.getIslandManager().getIslandViaId(islandID).getWarpLevel() + 1).getCost());
                            IridiumSkyblock.getIslandManager().getIslandViaId(islandID).setWarpLevel(IridiumSkyblock.getIslandManager().getIslandViaId(islandID).getWarpLevel() + 1);
                        } else {
                            e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().notEnoughCrystals.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                        }
                    } else {
                        e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().maxLevelReached.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                    }
                }
            }
        }
    }
}

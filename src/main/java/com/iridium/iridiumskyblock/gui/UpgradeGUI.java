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

public class UpgradeGUI extends GUI implements Listener {
    public ItemStack size;
    public ItemStack member;
    public ItemStack warp;
    public ItemStack ores;

    public UpgradeGUI(Island island) {
        super(island, 27, IridiumSkyblock.getConfiguration().upgradeGUITitle);
        IridiumSkyblock.getInstance().registerListeners(this);
    }

    @Override
    public void addContent() {
        super.addContent();
        Island island = getIsland();
        if (island != null) {

            this.size = Utils.makeItemHidden(IridiumSkyblock.getInventories().size, getIsland());
            this.member = Utils.makeItemHidden(IridiumSkyblock.getInventories().member, getIsland());
            this.warp = Utils.makeItemHidden(IridiumSkyblock.getInventories().warp, getIsland());
            this.ores = Utils.makeItemHidden(IridiumSkyblock.getInventories().ores, getIsland());
            
            getInventory().setItem(10, size);
            getInventory().setItem(12, member);
            getInventory().setItem(14, warp);
            getInventory().setItem(16, ores);
        }
    }

    @EventHandler
    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().equals(getInventory())) {
            e.setCancelled(true);
            if (e.getCurrentItem() != null) {
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

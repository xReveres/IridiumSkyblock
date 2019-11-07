package com.iridium.iridiumskyblock.gui;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.Utils;
import com.iridium.iridiumskyblock.support.Vault;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
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
        super(island, 27, IridiumSkyblock.getInventories().upgradeGUITitle);
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

            setItem(10, size);
            setItem(12, member);
            setItem(14, warp);
            setItem(16, ores);
        }
    }

    @EventHandler
    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().equals(getInventory())) {
            e.setCancelled(true);
            Player p = (Player) e.getWhoClicked();
            Island island = getIsland();
            if (e.getCurrentItem() != null) {
                if (e.getCurrentItem().equals(ores)) {
                    if (IridiumSkyblock.getUpgrades().ores.containsKey(island.getOreLevel() + 1)) {
                        if (IridiumSkyblock.getConfiguration().useVault ? Vault.econ.getBalance(p) >= IridiumSkyblock.getUpgrades().ores.get(island.getOreLevel() + 1).getCost() : island.getCrystals() >= IridiumSkyblock.getUpgrades().ores.get(island.getOreLevel() + 1).getCost()) {
                            if (IridiumSkyblock.getConfiguration().useVault) {
                                Vault.econ.withdrawPlayer(p, IridiumSkyblock.getUpgrades().ores.get(island.getOreLevel() + 1).getCost());
                            } else {
                                island.setCrystals(island.getCrystals() - IridiumSkyblock.getUpgrades().ores.get(island.getOreLevel() + 1).getCost());
                            }
                            island.setOreLevel(island.getOreLevel() + 1);
                        } else {
                            e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().notEnoughCrystals.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                        }
                    } else {
                        e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().maxLevelReached.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                    }
                }
                if (e.getCurrentItem().equals(size)) {
                    if (IridiumSkyblock.getUpgrades().size.containsKey(island.getSizeLevel() + 1)) {
                        if (IridiumSkyblock.getConfiguration().useVault ? Vault.econ.getBalance(p) >= IridiumSkyblock.getUpgrades().size.get(island.getOreLevel() + 1).getCost() : island.getCrystals() >= IridiumSkyblock.getUpgrades().size.get(island.getOreLevel() + 1).getCost()) {
                            if (IridiumSkyblock.getConfiguration().useVault) {
                                Vault.econ.withdrawPlayer(p, IridiumSkyblock.getUpgrades().size.get(island.getSizeLevel() + 1).getCost());
                            } else {
                                island.setCrystals(island.getCrystals() - IridiumSkyblock.getUpgrades().size.get(island.getSizeLevel() + 1).getCost());
                            }
                            island.setSizeLevel(island.getSizeLevel() + 1);
                            island.sendBorder();
                        } else {
                            e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().notEnoughCrystals.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                        }
                    } else {
                        e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().maxLevelReached.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                    }
                }
                if (e.getCurrentItem().equals(member)) {
                    if (IridiumSkyblock.getUpgrades().member.containsKey(island.getMemberLevel() + 1)) {
                        if (IridiumSkyblock.getConfiguration().useVault ? Vault.econ.getBalance(p) >= IridiumSkyblock.getUpgrades().member.get(island.getOreLevel() + 1).getCost() : island.getCrystals() >= IridiumSkyblock.getUpgrades().member.get(island.getOreLevel() + 1).getCost()) {
                            if (IridiumSkyblock.getConfiguration().useVault) {
                                Vault.econ.withdrawPlayer(p, IridiumSkyblock.getUpgrades().member.get(island.getMemberLevel() + 1).getCost());
                            } else {
                                island.setCrystals(island.getCrystals() - IridiumSkyblock.getUpgrades().member.get(island.getMemberLevel() + 1).getCost());
                            }
                            island.setMemberLevel(island.getMemberLevel() + 1);
                        } else {
                            e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().notEnoughCrystals.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                        }
                    } else {
                        e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().maxLevelReached.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                    }
                }
                if (e.getCurrentItem().equals(warp)) {
                    if (IridiumSkyblock.getUpgrades().warp.containsKey(island.getWarpLevel() + 1)) {
                        if (IridiumSkyblock.getConfiguration().useVault ? Vault.econ.getBalance(p) >= IridiumSkyblock.getUpgrades().warp.get(island.getOreLevel() + 1).getCost() : island.getCrystals() >= IridiumSkyblock.getUpgrades().warp.get(island.getOreLevel() + 1).getCost()) {
                            if (IridiumSkyblock.getConfiguration().useVault) {
                                Vault.econ.withdrawPlayer(p, IridiumSkyblock.getUpgrades().warp.get(island.getWarpLevel() + 1).getCost());
                            } else {
                                island.setCrystals(island.getCrystals() - IridiumSkyblock.getUpgrades().warp.get(island.getWarpLevel() + 1).getCost());
                            }
                            island.setWarpLevel(island.getWarpLevel() + 1);
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

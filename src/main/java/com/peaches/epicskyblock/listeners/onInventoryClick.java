package com.peaches.epicskyblock.listeners;

import com.peaches.epicskyblock.*;
import com.peaches.epicskyblock.gui.TopGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class onInventoryClick implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        try {
            Player p = (Player) e.getWhoClicked();
            User user = User.getUser(((Player) e.getWhoClicked()).getPlayer());
            if (user.getIsland() != null) {
                if (e.getInventory().equals(user.getIsland().getMissionsGUI().inventory)) {
                    e.setCancelled(true);
                }
                if (e.getInventory().equals(user.getIsland().getUpgradeGUI().inventory)) {
                    e.setCancelled(true);
                    if (e.getCurrentItem().equals(user.getIsland().getUpgradeGUI().ores)) {
                        if (EpicSkyblock.getUpgrades().ores.containsKey(user.getIsland().getOreLevel() + 1)) {
                            if (user.getIsland().getCrystals() >= EpicSkyblock.getUpgrades().ores.get(user.getIsland().getOreLevel() + 1).getCost()) {
                                user.getIsland().setCrystals(user.getIsland().getCrystals() - EpicSkyblock.getUpgrades().ores.get(user.getIsland().getOreLevel() + 1).getCost());
                                user.getIsland().setOreLevel(user.getIsland().getOreLevel() + 1);
                            } else {
                                e.getWhoClicked().sendMessage(Utils.color(EpicSkyblock.getMessages().notEnoughCrystals.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
                            }
                        } else {
                            p.sendMessage(Utils.color(EpicSkyblock.getMessages().maxLevelReached.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
                        }
                    }
                    if (e.getCurrentItem().equals(user.getIsland().getUpgradeGUI().size)) {
                        if (EpicSkyblock.getUpgrades().size.containsKey(user.getIsland().getSizeLevel() + 1)) {
                            if (user.getIsland().getCrystals() >= EpicSkyblock.getUpgrades().size.get(user.getIsland().getSizeLevel() + 1).getCost()) {
                                user.getIsland().setCrystals(user.getIsland().getCrystals() - EpicSkyblock.getUpgrades().size.get(user.getIsland().getSizeLevel() + 1).getCost());
                                user.getIsland().setSizeLevel(user.getIsland().getSizeLevel() + 1);
                                user.getIsland().sendBorder();
                            } else {
                                e.getWhoClicked().sendMessage(Utils.color(EpicSkyblock.getMessages().notEnoughCrystals.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
                            }
                        } else {
                            p.sendMessage(Utils.color(EpicSkyblock.getMessages().maxLevelReached.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
                        }
                    }
                    if (e.getCurrentItem().equals(user.getIsland().getUpgradeGUI().member)) {
                        if (EpicSkyblock.getUpgrades().member.containsKey(user.getIsland().getMemberLevel() + 1)) {
                            if (user.getIsland().getCrystals() >= EpicSkyblock.getUpgrades().member.get(user.getIsland().getMemberLevel() + 1).getCost()) {
                                user.getIsland().setCrystals(user.getIsland().getCrystals() - EpicSkyblock.getUpgrades().member.get(user.getIsland().getMemberLevel() + 1).getCost());
                                user.getIsland().setMemberLevel(user.getIsland().getMemberLevel() + 1);
                            } else {
                                e.getWhoClicked().sendMessage(Utils.color(EpicSkyblock.getMessages().notEnoughCrystals.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
                            }
                        } else {
                            p.sendMessage(Utils.color(EpicSkyblock.getMessages().maxLevelReached.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
                        }
                    }
                    if (e.getCurrentItem().equals(user.getIsland().getUpgradeGUI().warp)) {
                        if (EpicSkyblock.getUpgrades().warp.containsKey(user.getIsland().getWarpLevel() + 1)) {
                            if (user.getIsland().getCrystals() >= EpicSkyblock.getUpgrades().warp.get(user.getIsland().getWarpLevel() + 1).getCost()) {
                                user.getIsland().setCrystals(user.getIsland().getCrystals() - EpicSkyblock.getUpgrades().warp.get(user.getIsland().getWarpLevel() + 1).getCost());
                                user.getIsland().setWarpLevel(user.getIsland().getWarpLevel() + 1);
                            } else {
                                e.getWhoClicked().sendMessage(Utils.color(EpicSkyblock.getMessages().notEnoughCrystals.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
                            }
                        } else {
                            p.sendMessage(Utils.color(EpicSkyblock.getMessages().maxLevelReached.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
                        }
                    }
                }
            }
        } catch (Exception ex) {
            EpicSkyblock.getInstance().sendErrorMessage(ex);
        }
    }
}

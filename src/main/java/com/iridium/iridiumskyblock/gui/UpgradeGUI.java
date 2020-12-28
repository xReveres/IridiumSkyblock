package com.iridium.iridiumskyblock.gui;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.User;
import com.iridium.iridiumskyblock.Utils;
import com.iridium.iridiumskyblock.configs.Upgrades;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class UpgradeGUI extends GUI implements Listener {

    public UpgradeGUI(Island island) {
        super(island, IridiumSkyblock.getInventories().upgradeGUISize, IridiumSkyblock.getInventories().upgradeGUITitle);
        IridiumSkyblock.getInstance().registerListeners(this);
    }

    @Override
    public void addContent() {
        super.addContent();
        if (getInventory().getViewers().isEmpty()) return;
        Island island = getIsland();
        if (island != null) {
            if (IridiumSkyblock.getUpgrades().sizeUpgrade.enabled)
                setItem(IridiumSkyblock.getUpgrades().sizeUpgrade.slot, Utils.makeItemHidden(IridiumSkyblock.getInventories().size, getIsland()));
            if (IridiumSkyblock.getUpgrades().memberUpgrade.enabled)
                setItem(IridiumSkyblock.getUpgrades().memberUpgrade.slot, Utils.makeItemHidden(IridiumSkyblock.getInventories().member, getIsland()));
            if (IridiumSkyblock.getUpgrades().warpUpgrade.enabled)
                setItem(IridiumSkyblock.getUpgrades().warpUpgrade.slot, Utils.makeItemHidden(IridiumSkyblock.getInventories().warp, getIsland()));
            if (IridiumSkyblock.getUpgrades().oresUpgrade.enabled)
                setItem(IridiumSkyblock.getUpgrades().oresUpgrade.slot, Utils.makeItemHidden(IridiumSkyblock.getInventories().ores, getIsland()));
            if (IridiumSkyblock.getInventories().backButtons)
                setItem(getInventory().getSize() - 5, Utils.makeItem(IridiumSkyblock.getInventories().back));
        }
    }

    private void sendMessage(Player p, String upgrade, int oldlvl, int newlvl) {
        for (String m : getIsland().members) {
            Player pl = Bukkit.getPlayer(User.getUser(m).name);
            if (pl != null) {
                pl.sendMessage(Utils.color(IridiumSkyblock.getMessages().islandUpgraded.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix).replace("%player%", p.getName()).replace("%upgradename%", upgrade).replace("%oldlvl%", oldlvl + "").replace("%newlvl%", newlvl + "")));
            }
        }
    }

    @EventHandler
    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().equals(getInventory())) {
            e.setCancelled(true);
            if (e.getClickedInventory() == null || !e.getClickedInventory().equals(getInventory())) return;
            Player p = (Player) e.getWhoClicked();
            if (e.getSlot() == getInventory().getSize() - 5 && IridiumSkyblock.getInventories().backButtons) {
                e.getWhoClicked().openInventory(getIsland().islandMenuGUI.getInventory());
            }
            if (e.getSlot() == IridiumSkyblock.getUpgrades().sizeUpgrade.slot && IridiumSkyblock.getUpgrades().sizeUpgrade.enabled) {
                if (IridiumSkyblock.getUpgrades().sizeUpgrade.upgrades.containsKey(getIsland().sizeLevel + 1)) {
                    Upgrades.IslandUpgrade upgrade = IridiumSkyblock.getUpgrades().sizeUpgrade.upgrades.get(getIsland().sizeLevel + 1);
                    Utils.BuyResponce responce = Utils.canBuy(p, upgrade.vaultCost, upgrade.crystalsCost);
                    if (responce == Utils.BuyResponce.SUCCESS) {
                        sendMessage(p, "Size", getIsland().sizeLevel, getIsland().sizeLevel + 1);
                        getIsland().setSizeLevel(getIsland().sizeLevel + 1);
                    } else {
                        p.sendMessage(Utils.color(responce == Utils.BuyResponce.NOT_ENOUGH_VAULT ? IridiumSkyblock.getMessages().cantBuy : IridiumSkyblock.getMessages().notEnoughCrystals.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                    }
                } else {
                    e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().maxLevelReached.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                }
            }
            if (e.getSlot() == IridiumSkyblock.getUpgrades().memberUpgrade.slot && IridiumSkyblock.getUpgrades().memberUpgrade.enabled) {
                if (IridiumSkyblock.getUpgrades().memberUpgrade.upgrades.containsKey(getIsland().memberLevel + 1)) {
                    Upgrades.IslandUpgrade upgrade = IridiumSkyblock.getUpgrades().memberUpgrade.upgrades.get(getIsland().memberLevel + 1);
                    Utils.BuyResponce responce = Utils.canBuy(p, upgrade.vaultCost, upgrade.crystalsCost);
                    if (responce == Utils.BuyResponce.SUCCESS) {
                        sendMessage(p, "Member", getIsland().memberLevel, getIsland().memberLevel + 1);
                        getIsland().memberLevel++;
                    } else {
                        p.sendMessage(Utils.color(responce == Utils.BuyResponce.NOT_ENOUGH_VAULT ? IridiumSkyblock.getMessages().cantBuy : IridiumSkyblock.getMessages().notEnoughCrystals.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                    }
                } else {
                    e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().maxLevelReached.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                }
            }
            if (e.getSlot() == IridiumSkyblock.getUpgrades().warpUpgrade.slot && IridiumSkyblock.getUpgrades().warpUpgrade.enabled) {
                if (IridiumSkyblock.getUpgrades().warpUpgrade.upgrades.containsKey(getIsland().warpLevel + 1)) {
                    Upgrades.IslandUpgrade upgrade = IridiumSkyblock.getUpgrades().warpUpgrade.upgrades.get(getIsland().warpLevel + 1);
                    Utils.BuyResponce responce = Utils.canBuy(p, upgrade.vaultCost, upgrade.crystalsCost);
                    if (responce == Utils.BuyResponce.SUCCESS) {
                        sendMessage(p, "Warp", getIsland().warpLevel, getIsland().warpLevel + 1);
                        getIsland().warpLevel = getIsland().warpLevel + 1;
                    } else {
                        p.sendMessage(Utils.color(responce == Utils.BuyResponce.NOT_ENOUGH_VAULT ? IridiumSkyblock.getMessages().cantBuy : IridiumSkyblock.getMessages().notEnoughCrystals.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                    }
                } else {
                    e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().maxLevelReached.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                }
            }
            if (e.getSlot() == IridiumSkyblock.getUpgrades().oresUpgrade.slot && IridiumSkyblock.getUpgrades().oresUpgrade.enabled) {
                if (IridiumSkyblock.getUpgrades().oresUpgrade.upgrades.containsKey(getIsland().oreLevel + 1)) {
                    Upgrades.IslandUpgrade upgrade = IridiumSkyblock.getUpgrades().oresUpgrade.upgrades.get(getIsland().oreLevel + 1);
                    Utils.BuyResponce responce = Utils.canBuy(p, upgrade.vaultCost, upgrade.crystalsCost);
                    if (responce == Utils.BuyResponce.SUCCESS) {
                        sendMessage(p, "Ore", getIsland().oreLevel, getIsland().oreLevel + 1);
                        getIsland().oreLevel++;
                    } else {
                        p.sendMessage(Utils.color(responce == Utils.BuyResponce.NOT_ENOUGH_VAULT ? IridiumSkyblock.getMessages().cantBuy : IridiumSkyblock.getMessages().notEnoughCrystals.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                    }
                } else {
                    e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().maxLevelReached.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                }
            }
        }
    }
}

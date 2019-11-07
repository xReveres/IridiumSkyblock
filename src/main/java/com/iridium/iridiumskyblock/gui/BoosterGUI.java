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

public class BoosterGUI extends GUI implements Listener {

    public ItemStack spawner;
    public ItemStack farming;
    public ItemStack exp;
    public ItemStack flight;

    public BoosterGUI(Island island) {
        super(island, 27, IridiumSkyblock.getInventories().boosterGUITitle);
        IridiumSkyblock.getInstance().registerListeners(this);
    }

    @Override
    public void addContent() {
        super.addContent();
        if (IridiumSkyblock.getIslandManager().islands.containsKey(islandID)) {
            Island island = IridiumSkyblock.getIslandManager().islands.get(islandID);
            this.spawner = Utils.makeItem(IridiumSkyblock.getInventories().spawner, island);
            this.farming = Utils.makeItem(IridiumSkyblock.getInventories().farming, island);
            this.exp = Utils.makeItem(IridiumSkyblock.getInventories().exp, island);
            this.flight = Utils.makeItem(IridiumSkyblock.getInventories().flight, island);
            if (IridiumSkyblock.getBoosters().spawnerBooster.isEnabled())
                setItem(IridiumSkyblock.getBoosters().spawnerBooster.getSlot(), spawner);
            if (IridiumSkyblock.getBoosters().farmingBooster.isEnabled())
                setItem(IridiumSkyblock.getBoosters().farmingBooster.getSlot(), farming);
            if (IridiumSkyblock.getBoosters().experianceBooster.isEnabled())
                setItem(IridiumSkyblock.getBoosters().experianceBooster.getSlot(), exp);
            if (IridiumSkyblock.getBoosters().flightBooster.isEnabled())
                setItem(IridiumSkyblock.getBoosters().flightBooster.getSlot(), flight);
        }
    }

    @EventHandler
    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().equals(getInventory())) {
            Player p = (Player) e.getWhoClicked();
            Island island = IridiumSkyblock.getIslandManager().islands.get(islandID);
            e.setCancelled(true);
            if (e.getCurrentItem() == null) return;
            if (IridiumSkyblock.getBoosters().spawnerBooster.isEnabled() && e.getCurrentItem().equals(spawner)) {
                if (IridiumSkyblock.getConfiguration().useVault ? Vault.econ.getBalance(p) >= IridiumSkyblock.getBoosters().spawnerBooster.getCost() : island.getCrystals() >= IridiumSkyblock.getBoosters().spawnerBooster.getCost()) {
                    if (island.getSpawnerBooster() == 0) {
                        if (IridiumSkyblock.getConfiguration().useVault) {
                            Vault.econ.withdrawPlayer(p, IridiumSkyblock.getBoosters().spawnerBooster.getCost());
                        } else {
                            island.setCrystals(island.getCrystals() - IridiumSkyblock.getBoosters().spawnerBooster.getCost());
                        }
                        island.setSpawnerBooster(IridiumSkyblock.getBoosters().spawnerBooster.time);
                    } else {
                        e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().spawnerBoosterActive.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                    }
                } else {
                    e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().notEnoughCrystals.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                }
            }
            if (IridiumSkyblock.getBoosters().farmingBooster.isEnabled() && e.getCurrentItem().equals(farming)) {
                if (IridiumSkyblock.getConfiguration().useVault ? Vault.econ.getBalance(p) >= IridiumSkyblock.getBoosters().farmingBooster.getCost() : island.getCrystals() >= IridiumSkyblock.getBoosters().farmingBooster.getCost()) {
                    if (island.getFarmingBooster() == 0) {
                        if (IridiumSkyblock.getConfiguration().useVault) {
                            Vault.econ.withdrawPlayer(p, IridiumSkyblock.getBoosters().farmingBooster.getCost());
                        } else {
                            island.setCrystals(island.getCrystals() - IridiumSkyblock.getBoosters().farmingBooster.getCost());
                        }
                        island.setFarmingBooster(IridiumSkyblock.getBoosters().farmingBooster.time);
                    } else {
                        e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().farmingBoosterActive.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                    }
                } else {
                    e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().notEnoughCrystals.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                }
            }
            if (IridiumSkyblock.getBoosters().spawnerBooster.isEnabled() && e.getCurrentItem().equals(exp)) {
                if (IridiumSkyblock.getConfiguration().useVault ? Vault.econ.getBalance(p) >= IridiumSkyblock.getBoosters().experianceBooster.getCost() : island.getCrystals() >= IridiumSkyblock.getBoosters().experianceBooster.getCost()) {
                    if (island.getExpBooster() == 0) {
                        if (IridiumSkyblock.getConfiguration().useVault) {
                            Vault.econ.withdrawPlayer(p, IridiumSkyblock.getBoosters().experianceBooster.getCost());
                        } else {
                            island.setCrystals(island.getCrystals() - IridiumSkyblock.getBoosters().experianceBooster.getCost());
                        }
                        island.setExpBooster(IridiumSkyblock.getBoosters().experianceBooster.time);
                    } else {
                        e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().expBoosterActive.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                    }
                } else {
                    e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().notEnoughCrystals.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                }
            }
            if (IridiumSkyblock.getBoosters().spawnerBooster.isEnabled() && e.getCurrentItem().equals(flight)) {
                if (IridiumSkyblock.getConfiguration().useVault ? Vault.econ.getBalance(p) >= IridiumSkyblock.getBoosters().flightBooster.getCost() : island.getCrystals() >= IridiumSkyblock.getBoosters().flightBooster.getCost()) {
                    if (island.getFlightBooster() == 0) {
                        if (IridiumSkyblock.getConfiguration().useVault) {
                            Vault.econ.withdrawPlayer(p, IridiumSkyblock.getBoosters().flightBooster.getCost());
                        } else {
                            island.setCrystals(island.getCrystals() - IridiumSkyblock.getBoosters().flightBooster.getCost());
                        }
                        island.setFlightBooster(IridiumSkyblock.getBoosters().flightBooster.time);
                    } else {
                        e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().flightBoosterActive.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                    }
                } else {
                    e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().notEnoughCrystals.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                }
            }
        }
    }
}
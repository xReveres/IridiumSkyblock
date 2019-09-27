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

public class BoosterGUI implements Listener {

    public Inventory inventory;
    public int islandID;

    public ItemStack spawner;
    public ItemStack farming;
    public ItemStack exp;
    public ItemStack flight;

    public int scheduler;

    public BoosterGUI(Island island) {
        this.inventory = Bukkit.createInventory(null, 27, Utils.color(EpicSkyblock.getConfiguration().BoosterGUITitle));
        islandID = island.getId();
        scheduler = Bukkit.getScheduler().scheduleAsyncRepeatingTask(EpicSkyblock.getInstance(), this::addContent, 0, 10);
        EpicSkyblock.getInstance().registerListeners(this);
    }

    public void addContent() {
        try {
            if (EpicSkyblock.getIslandManager().islands.containsKey(islandID)) {
                Island island = EpicSkyblock.getIslandManager().islands.get(islandID);
                for (int i = 0; i < 27; i++) {
                    inventory.setItem(i, Utils.makeItem(Material.STAINED_GLASS_PANE, 1, 7, " "));
                }
                this.spawner = Utils.makeItem(EpicSkyblock.getInventories().spawner, island);
                this.farming = Utils.makeItem(EpicSkyblock.getInventories().farming, island);
                this.exp = Utils.makeItem(EpicSkyblock.getInventories().exp, island);
                this.flight = Utils.makeItem(EpicSkyblock.getInventories().flight, island);
                if (EpicSkyblock.getBoosters().spawnerBooster.isEnabled())
                    inventory.setItem(EpicSkyblock.getBoosters().spawnerBooster.getSlot(), spawner);
                if (EpicSkyblock.getBoosters().farmingBooster.isEnabled())
                    inventory.setItem(EpicSkyblock.getBoosters().farmingBooster.getSlot(), farming);
                if (EpicSkyblock.getBoosters().experianceBooster.isEnabled())
                    inventory.setItem(EpicSkyblock.getBoosters().experianceBooster.getSlot(), exp);
                if (EpicSkyblock.getBoosters().flightBooster.isEnabled())
                    inventory.setItem(EpicSkyblock.getBoosters().flightBooster.getSlot(), flight);
            }
        } catch (Exception e) {
            EpicSkyblock.getInstance().sendErrorMessage(e);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().equals(inventory)) {
            Island island = EpicSkyblock.getIslandManager().islands.get(islandID);
            e.setCancelled(true);
            if (e.getCurrentItem() == null) return;
            if (EpicSkyblock.getBoosters().spawnerBooster.isEnabled() && e.getCurrentItem().equals(spawner)) {
                if (island.getCrystals() >= EpicSkyblock.getBoosters().spawnerBooster.getCost()) {
                    if (island.getSpawnerBooster() == 0) {
                        island.setCrystals(island.getCrystals() - EpicSkyblock.getBoosters().spawnerBooster.getCost());
                        island.setSpawnerBooster(3600);
                    } else {
                        e.getWhoClicked().sendMessage(Utils.color(EpicSkyblock.getMessages().spawnerBoosterActive.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
                    }
                } else {
                    e.getWhoClicked().sendMessage(Utils.color(EpicSkyblock.getMessages().notEnoughCrystals.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
                }
            }
            if (EpicSkyblock.getBoosters().farmingBooster.isEnabled() && e.getCurrentItem().equals(farming)) {
                if (island.getCrystals() >= EpicSkyblock.getBoosters().farmingBooster.getCost()) {
                    if (island.getFarmingBooster() == 0) {
                        island.setCrystals(island.getCrystals() - EpicSkyblock.getBoosters().farmingBooster.getCost());
                        island.setFarmingBooster(3600);
                    } else {
                        e.getWhoClicked().sendMessage(Utils.color(EpicSkyblock.getMessages().farmingBoosterActive.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
                    }
                } else {
                    e.getWhoClicked().sendMessage(Utils.color(EpicSkyblock.getMessages().notEnoughCrystals.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
                }
            }
            if (EpicSkyblock.getBoosters().spawnerBooster.isEnabled() && e.getCurrentItem().equals(exp)) {
                if (island.getCrystals() >= EpicSkyblock.getBoosters().experianceBooster.getCost()) {
                    if (island.getExpBooster() == 0) {
                        island.setCrystals(island.getCrystals() - EpicSkyblock.getBoosters().experianceBooster.getCost());
                        island.setExpBooster(3600);
                    } else {
                        e.getWhoClicked().sendMessage(Utils.color(EpicSkyblock.getMessages().expBoosterActive.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
                    }
                } else {
                    e.getWhoClicked().sendMessage(Utils.color(EpicSkyblock.getMessages().notEnoughCrystals.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
                }
            }
            if (EpicSkyblock.getBoosters().spawnerBooster.isEnabled() && e.getCurrentItem().equals(flight)) {
                if (island.getCrystals() >= EpicSkyblock.getBoosters().flightBooster.getCost()) {
                    if (island.getFlightBooster() == 0) {
                        island.setCrystals(island.getCrystals() - EpicSkyblock.getBoosters().flightBooster.getCost());
                        island.setFlightBooster(3600);
                    } else {
                        e.getWhoClicked().sendMessage(Utils.color(EpicSkyblock.getMessages().flightBoosterActive.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
                    }
                } else {
                    e.getWhoClicked().sendMessage(Utils.color(EpicSkyblock.getMessages().notEnoughCrystals.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
                }
            }
        }
    }
}
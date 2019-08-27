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
                this.spawner = Utils.makeItem(Material.MOB_SPAWNER, 1, 0, "&b&lIncreased Mobs", Utils.color(new ArrayList<>(Arrays.asList("&7Are your spawners too slow? Buy this", "&7booster and increase spawner rates x2.", "", "&b&lInformation:", "&b&l * &7Time Remaining: &b" + island.getSpawnerBooster() + "s", "&b&l * &7Booster Cost: &b" + EpicSkyblock.getBoosters().spawnerBooster.getCost() + " Crystals", "", "&b&l[!] &bRight Click to Purchase this Booster."))));
                this.farming = Utils.makeItem(Material.WHEAT, 1, 0, "&b&lIncreased Crops", Utils.color(new ArrayList<>(Arrays.asList("&7Are your crops too slow? Buy this", "&7booster and increase crop growth rates x2.", "", "&b&lInformation:", "&b&l * &7Time Remaining: &b" + island.getFarmingBooster() + "s", "&b&l * &7Booster Cost: &b" + EpicSkyblock.getBoosters().farmingBooster.getCost() + " Crystals", "", "&b&l[!] &bRight Click to Purchase this Booster."))));
                this.exp = Utils.makeItem(Material.EXP_BOTTLE, 1, 0, "&b&lIncreased Experience", Utils.color(new ArrayList<>(Arrays.asList("&7Takes too long to get exp? Buy this", "&7booster and exp rates x2.", "", "&b&lInformation:", "&b&l * &7Time Remaining: &b" + island.getExpBooster() + "s", "&b&l * &7Booster Cost: &b" + EpicSkyblock.getBoosters().experianceBooster.getCost() + "Crystals", "", "&b&l[!] &bRight Click to Purchase this Booster."))));
                this.flight = Utils.makeItem(Material.FEATHER, 1, 0, "&b&lIncreased Flight", Utils.color(new ArrayList<>(Arrays.asList("&7Tired of falling off your island? Buy this", "&7booster and allow all members to fly.", "", "&b&lInformation:", "&b&l * &7Time Remaining: &b" + island.getFlightBooster() + "s", "&b&l * &7Booster Cost: &b" + EpicSkyblock.getBoosters().flightBooster.getCost() + " Crystals", "", "&b&l[!] &bRight Click to Purchase this Booster."))));
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
            e.setCancelled(true);
            if (e.getCurrentItem() == null) return;
            if (EpicSkyblock.getBoosters().spawnerBooster.isEnabled() && e.getCurrentItem().equals(spawner)) {
                if (EpicSkyblock.getIslandManager().getIslandViaId(islandID).getCrystals() >= EpicSkyblock.getBoosters().spawnerBooster.getCost()) {
                    if (EpicSkyblock.getIslandManager().getIslandViaId(islandID).getSpawnerBooster() == 0) {
                        EpicSkyblock.getIslandManager().getIslandViaId(islandID).setCrystals(EpicSkyblock.getIslandManager().getIslandViaId(islandID).getCrystals() - EpicSkyblock.getBoosters().spawnerBooster.getCost());
                        EpicSkyblock.getIslandManager().getIslandViaId(islandID).setSpawnerBooster(3600);
                    } else {
                        e.getWhoClicked().sendMessage(Utils.color(EpicSkyblock.getMessages().spawnerBoosterActive.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
                    }
                } else {
                    e.getWhoClicked().sendMessage(Utils.color(EpicSkyblock.getMessages().notEnoughCrystals.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
                }
            }
            if (EpicSkyblock.getBoosters().farmingBooster.isEnabled() && e.getCurrentItem().equals(farming)) {
                if (EpicSkyblock.getIslandManager().getIslandViaId(islandID).getCrystals() >= EpicSkyblock.getBoosters().farmingBooster.getCost()) {
                    if (EpicSkyblock.getIslandManager().getIslandViaId(islandID).getFarmingBooster() == 0) {
                        EpicSkyblock.getIslandManager().getIslandViaId(islandID).setCrystals(EpicSkyblock.getIslandManager().getIslandViaId(islandID).getCrystals() - EpicSkyblock.getBoosters().farmingBooster.getCost());
                        EpicSkyblock.getIslandManager().getIslandViaId(islandID).setFarmingBooster(3600);
                    } else {
                        e.getWhoClicked().sendMessage(Utils.color(EpicSkyblock.getMessages().farmingBoosterActive.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
                    }
                } else {
                    e.getWhoClicked().sendMessage(Utils.color(EpicSkyblock.getMessages().notEnoughCrystals.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
                }
            }
            if (EpicSkyblock.getBoosters().spawnerBooster.isEnabled() && e.getCurrentItem().equals(exp)) {
                if (EpicSkyblock.getIslandManager().getIslandViaId(islandID).getCrystals() >= EpicSkyblock.getBoosters().experianceBooster.getCost()) {
                    if (EpicSkyblock.getIslandManager().getIslandViaId(islandID).getExpBooster() == 0) {
                        EpicSkyblock.getIslandManager().getIslandViaId(islandID).setCrystals(EpicSkyblock.getIslandManager().getIslandViaId(islandID).getCrystals() - EpicSkyblock.getBoosters().experianceBooster.getCost());
                        EpicSkyblock.getIslandManager().getIslandViaId(islandID).setExpBooster(3600);
                    } else {
                        e.getWhoClicked().sendMessage(Utils.color(EpicSkyblock.getMessages().expBoosterActive.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
                    }
                } else {
                    e.getWhoClicked().sendMessage(Utils.color(EpicSkyblock.getMessages().notEnoughCrystals.replace("%prefix%", EpicSkyblock.getConfiguration().prefix)));
                }
            }
            if (EpicSkyblock.getBoosters().spawnerBooster.isEnabled() && e.getCurrentItem().equals(flight)) {
                if (EpicSkyblock.getIslandManager().getIslandViaId(islandID).getCrystals() >= EpicSkyblock.getBoosters().flightBooster.getCost()) {
                    if (EpicSkyblock.getIslandManager().getIslandViaId(islandID).getFlightBooster() == 0) {
                        EpicSkyblock.getIslandManager().getIslandViaId(islandID).setCrystals(EpicSkyblock.getIslandManager().getIslandViaId(islandID).getCrystals() - EpicSkyblock.getBoosters().flightBooster.getCost());
                        EpicSkyblock.getIslandManager().getIslandViaId(islandID).setFlightBooster(3600);
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
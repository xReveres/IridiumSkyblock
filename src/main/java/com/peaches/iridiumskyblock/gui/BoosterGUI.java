package com.peaches.iridiumskyblock.gui;

import com.peaches.iridiumskyblock.IridiumSkyblock;
import com.peaches.iridiumskyblock.Island;
import com.peaches.iridiumskyblock.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BoosterGUI implements Listener {

    public Inventory inventory;
    public int islandID;

    public ItemStack spawner;
    public ItemStack farming;
    public ItemStack exp;
    public ItemStack flight;

    public int scheduler;

    public BoosterGUI(Island island) {
        this.inventory = Bukkit.createInventory(null, 27, Utils.color(IridiumSkyblock.getConfiguration().BoosterGUITitle));
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
                this.spawner = Utils.makeItem(IridiumSkyblock.getInventories().spawner, island);
                this.farming = Utils.makeItem(IridiumSkyblock.getInventories().farming, island);
                this.exp = Utils.makeItem(IridiumSkyblock.getInventories().exp, island);
                this.flight = Utils.makeItem(IridiumSkyblock.getInventories().flight, island);
                if (IridiumSkyblock.getBoosters().spawnerBooster.isEnabled())
                    inventory.setItem(IridiumSkyblock.getBoosters().spawnerBooster.getSlot(), spawner);
                if (IridiumSkyblock.getBoosters().farmingBooster.isEnabled())
                    inventory.setItem(IridiumSkyblock.getBoosters().farmingBooster.getSlot(), farming);
                if (IridiumSkyblock.getBoosters().experianceBooster.isEnabled())
                    inventory.setItem(IridiumSkyblock.getBoosters().experianceBooster.getSlot(), exp);
                if (IridiumSkyblock.getBoosters().flightBooster.isEnabled())
                    inventory.setItem(IridiumSkyblock.getBoosters().flightBooster.getSlot(), flight);
            }
        } catch (Exception e) {
            IridiumSkyblock.getInstance().sendErrorMessage(e);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().equals(inventory)) {
            Island island = IridiumSkyblock.getIslandManager().islands.get(islandID);
            e.setCancelled(true);
            if (e.getCurrentItem() == null) return;
            if (IridiumSkyblock.getBoosters().spawnerBooster.isEnabled() && e.getCurrentItem().equals(spawner)) {
                if (island.getCrystals() >= IridiumSkyblock.getBoosters().spawnerBooster.getCost()) {
                    if (island.getSpawnerBooster() == 0) {
                        island.setCrystals(island.getCrystals() - IridiumSkyblock.getBoosters().spawnerBooster.getCost());
                        island.setSpawnerBooster(3600);
                    } else {
                        e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().spawnerBoosterActive.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                    }
                } else {
                    e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().notEnoughCrystals.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                }
            }
            if (IridiumSkyblock.getBoosters().farmingBooster.isEnabled() && e.getCurrentItem().equals(farming)) {
                if (island.getCrystals() >= IridiumSkyblock.getBoosters().farmingBooster.getCost()) {
                    if (island.getFarmingBooster() == 0) {
                        island.setCrystals(island.getCrystals() - IridiumSkyblock.getBoosters().farmingBooster.getCost());
                        island.setFarmingBooster(3600);
                    } else {
                        e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().farmingBoosterActive.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                    }
                } else {
                    e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().notEnoughCrystals.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                }
            }
            if (IridiumSkyblock.getBoosters().spawnerBooster.isEnabled() && e.getCurrentItem().equals(exp)) {
                if (island.getCrystals() >= IridiumSkyblock.getBoosters().experianceBooster.getCost()) {
                    if (island.getExpBooster() == 0) {
                        island.setCrystals(island.getCrystals() - IridiumSkyblock.getBoosters().experianceBooster.getCost());
                        island.setExpBooster(3600);
                    } else {
                        e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().expBoosterActive.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                    }
                } else {
                    e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().notEnoughCrystals.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                }
            }
            if (IridiumSkyblock.getBoosters().spawnerBooster.isEnabled() && e.getCurrentItem().equals(flight)) {
                if (island.getCrystals() >= IridiumSkyblock.getBoosters().flightBooster.getCost()) {
                    if (island.getFlightBooster() == 0) {
                        island.setCrystals(island.getCrystals() - IridiumSkyblock.getBoosters().flightBooster.getCost());
                        island.setFlightBooster(3600);
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
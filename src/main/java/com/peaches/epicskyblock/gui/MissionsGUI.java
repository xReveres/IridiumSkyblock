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
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

public class MissionsGUI implements Listener {

    public Inventory inventory;
    public int islandID;
    public int scheduler;

    public ItemStack treasureHunter;
    public ItemStack competitor;
    public ItemStack miner;
    public ItemStack farmer;
    public ItemStack hunter;
    public ItemStack fisherman;
    public ItemStack builder;

    public MissionsGUI(Island island) {
        this.inventory = Bukkit.createInventory(null, 27, Utils.color(EpicSkyblock.getConfiguration().MissionsGUITitle));
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
                this.treasureHunter = Utils.makeItemHidden(EpicSkyblock.getInventories().treasureHunter, island);
                this.competitor = Utils.makeItemHidden(EpicSkyblock.getInventories().competitor, island);
                this.miner = Utils.makeItemHidden(EpicSkyblock.getInventories().miner, island);
                this.farmer = Utils.makeItemHidden(EpicSkyblock.getInventories().farmer, island);
                this.hunter = Utils.makeItemHidden(EpicSkyblock.getInventories().hunter, island);
                this.fisherman = Utils.makeItemHidden(EpicSkyblock.getInventories().fisherman, island);
                this.builder = Utils.makeItemHidden(EpicSkyblock.getInventories().builder, island);

                inventory.setItem(10, this.treasureHunter);
                inventory.setItem(11, this.competitor);
                inventory.setItem(12, this.miner);
                inventory.setItem(13, this.farmer);
                inventory.setItem(14, this.hunter);
                inventory.setItem(15, this.fisherman);
                inventory.setItem(16, this.builder);
            }
        } catch (Exception e) {
            EpicSkyblock.getInstance().sendErrorMessage(e);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().equals(inventory)) {
            e.setCancelled(true);
        }
    }
}

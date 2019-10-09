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
        this.inventory = Bukkit.createInventory(null, 27, Utils.color(IridiumSkyblock.getConfiguration().MissionsGUITitle));
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
                this.treasureHunter = Utils.makeItemHidden(IridiumSkyblock.getInventories().treasureHunter, island);
                this.competitor = Utils.makeItemHidden(IridiumSkyblock.getInventories().competitor, island);
                this.miner = Utils.makeItemHidden(IridiumSkyblock.getInventories().miner, island);
                this.farmer = Utils.makeItemHidden(IridiumSkyblock.getInventories().farmer, island);
                this.hunter = Utils.makeItemHidden(IridiumSkyblock.getInventories().hunter, island);
                this.fisherman = Utils.makeItemHidden(IridiumSkyblock.getInventories().fisherman, island);
                this.builder = Utils.makeItemHidden(IridiumSkyblock.getInventories().builder, island);

                inventory.setItem(10, this.treasureHunter);
                inventory.setItem(11, this.competitor);
                inventory.setItem(12, this.miner);
                inventory.setItem(13, this.farmer);
                inventory.setItem(14, this.hunter);
                inventory.setItem(15, this.fisherman);
                inventory.setItem(16, this.builder);
            }
        } catch (Exception e) {
            IridiumSkyblock.getInstance().sendErrorMessage(e);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().equals(inventory)) {
            e.setCancelled(true);
        }
    }
}

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

public class MissionsGUI extends GUI implements Listener {

    public ItemStack treasureHunter;
    public ItemStack competitor;
    public ItemStack miner;
    public ItemStack farmer;
    public ItemStack hunter;
    public ItemStack fisherman;
    public ItemStack builder;

    public MissionsGUI(Island island) {
        super(island, 27, IridiumSkyblock.getConfiguration().missionsGUITitle);
        IridiumSkyblock.getInstance().registerListeners(this);
    }

    @Override
    public void addContent() {
        super.addContent();
        if (IridiumSkyblock.getIslandManager().islands.containsKey(islandID)) {
            Island island = IridiumSkyblock.getIslandManager().islands.get(islandID);
            for (int i = 0; i < 27; i++) {
                getInventory().setItem(i, Utils.makeItem(Material.STAINED_GLASS_PANE, 1, 15, " "));
            }
            this.treasureHunter = Utils.makeItemHidden(IridiumSkyblock.getInventories().treasureHunter, island);
            this.competitor = Utils.makeItemHidden(IridiumSkyblock.getInventories().competitor, island);
            this.miner = Utils.makeItemHidden(IridiumSkyblock.getInventories().miner, island);
            this.farmer = Utils.makeItemHidden(IridiumSkyblock.getInventories().farmer, island);
            this.hunter = Utils.makeItemHidden(IridiumSkyblock.getInventories().hunter, island);
            this.fisherman = Utils.makeItemHidden(IridiumSkyblock.getInventories().fisherman, island);
            this.builder = Utils.makeItemHidden(IridiumSkyblock.getInventories().builder, island);

            getInventory().setItem(10, this.treasureHunter);
            getInventory().setItem(11, this.competitor);
            getInventory().setItem(12, this.miner);
            getInventory().setItem(13, this.farmer);
            getInventory().setItem(14, this.hunter);
            getInventory().setItem(15, this.fisherman);
            getInventory().setItem(16, this.builder);
        }
    }

    @EventHandler
    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().equals(getInventory())) {
            e.setCancelled(true);
        }
    }
}

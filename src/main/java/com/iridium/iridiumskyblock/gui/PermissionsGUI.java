package com.iridium.iridiumskyblock.gui;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.Roles;
import com.iridium.iridiumskyblock.Utils;
import com.peaches.iridiumskyblock.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public class PermissionsGUI implements Listener {

    public Inventory inventory;

    public HashMap<Integer, Roles> roles;

    public int islandid;

    public PermissionsGUI(Island island) {
        islandid = island.getId();
        this.inventory = Bukkit.createInventory(null, 27, Utils.color(IridiumSkyblock.getConfiguration().permissionsGUITitle));
        for (int i = 0; i < 27; i++) {
            inventory.setItem(i, Utils.makeItem(Material.STAINED_GLASS_PANE, 1, 15, " "));
        }
        roles = new HashMap<>();
        for (int i = 1; i < Roles.Owner.getRank(); i++) {
            roles.put(10 + i, Roles.getViaRank(i));
            inventory.setItem(10 + i, Utils.makeItem(Material.STAINED_CLAY, 1, 14, "&b"+Roles.getViaRank(i).name()));
        }
        IridiumSkyblock.getInstance().registerListeners(this);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().equals(inventory)) {
            e.setCancelled(true);
            if (roles.containsKey(e.getSlot())) {

            }
        }
    }
}

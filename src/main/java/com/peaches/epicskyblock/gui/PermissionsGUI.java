package com.peaches.epicskyblock.gui;

import com.peaches.epicskyblock.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class PermissionsGUI implements Listener {

    public Inventory inventory;

    public HashMap<Integer, Roles> roles;

    public int islandid;

    public PermissionsGUI(Island island) {
        islandid = island.getId();
        this.inventory = Bukkit.createInventory(null, 27, Utils.color(EpicSkyblock.getConfiguration().BorderColorGUITitle));
        for (int i = 0; i < 27; i++) {
            inventory.setItem(i, Utils.makeItem(Material.STAINED_GLASS_PANE, 1, 7, " "));
        }
        roles = new HashMap<>();
        for (int i = 1; i < Roles.Owner.getRank(); i++) {
            roles.put(10 + i, Roles.getViaRank(i));
            inventory.setItem(10 + i, Utils.makeItem(Material.STAINED_CLAY, 1, 14, "&b"+Roles.getViaRank(i).name()));
        }
        EpicSkyblock.getInstance().registerListeners(this);
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

package com.iridium.iridiumskyblock.gui;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.Utils;
import com.iridium.iridiumskyblock.support.Vault;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ConfirmationGUI extends GUI implements Listener {

    public Runnable runnable;

    public ConfirmationGUI(Island island, Runnable runnable) {
        super(island, 27, IridiumSkyblock.getInventories().confirmationGUITitle);
        IridiumSkyblock.getInstance().registerListeners(this);
        this.runnable = runnable;
    }

    @Override
    public void addContent() {
        super.addContent();
        setItem(12, Utils.makeItem(Material.STAINED_GLASS_PANE, 1, 5, "&a&lYes"));
        setItem(14, Utils.makeItem(Material.STAINED_GLASS_PANE, 1, 14, "&c&lNo"));
    }

    @EventHandler
    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().equals(getInventory())) {
            e.setCancelled(true);
            if (e.getSlot() == 14) {
                e.getWhoClicked().closeInventory();
                runnable.run();
            } else if (e.getSlot() == 16) {
                e.getWhoClicked().closeInventory();
            }
        }
    }
}
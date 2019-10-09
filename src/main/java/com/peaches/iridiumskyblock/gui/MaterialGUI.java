package com.peaches.iridiumskyblock.gui;

import com.peaches.iridiumskyblock.IridiumSkyblock;
import com.peaches.iridiumskyblock.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.lang.reflect.Field;

public class MaterialGUI implements Listener {

    private Inventory inventory;

    private Field field;
    private Object instance;

    public MaterialGUI(Field field, Object instance) {
        this.field = field;
        this.instance = instance;

        inventory = Bukkit.createInventory(null, 9);
        for (int i = 0; i < 9; i++) {
            if (i != 4) {
                inventory.setItem(i, Utils.makeItem(Material.STAINED_GLASS_PANE, 1, 14, " "));
            }
        }

        Bukkit.getPluginManager().registerEvents(this, IridiumSkyblock.getInstance());
    }

    public Inventory getInventory() {
        return inventory;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getClickedInventory() == null) return;
        if (e.getClickedInventory().equals(inventory)) {
            e.setCancelled(true);
            if (e.getClickedInventory() != null && e.getCursor() != null && e.getCursor().getType() != Material.AIR) {
                if (e.getSlot() == 4) {
                    try {
                        field.set(instance, e.getCursor().getType());
                        e.setCursor(null);
                        e.getWhoClicked().closeInventory();
                        e.getWhoClicked().openInventory(IridiumSkyblock.getEditor().getInventory());
                        IridiumSkyblock.getInstance().saveConfigs();
                        IridiumSkyblock.getInstance().loadConfigs();
                    } catch (IllegalAccessException ex) {
                    }
                }
            }
        }
    }
}

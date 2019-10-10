package com.peaches.iridiumskyblock.gui;

import com.peaches.iridiumskyblock.IridiumSkyblock;
import com.peaches.iridiumskyblock.Island;
import com.peaches.iridiumskyblock.NMSUtils;
import com.peaches.iridiumskyblock.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BorderColorGUI implements Listener {

    public Inventory inventory;
    public int islandID;

    public ItemStack red;
    public ItemStack green;
    public ItemStack blue;
    public ItemStack off;

    public BorderColorGUI(Island island) {
        this.inventory = Bukkit.createInventory(null, 27, Utils.color(IridiumSkyblock.getConfiguration().borderColorGUITitle));
        islandID = island.getId();

        for (int i = 0; i < 27; i++) {
            inventory.setItem(i, Utils.makeItem(Material.STAINED_GLASS_PANE, 1, 15, " "));
        }
        this.red = Utils.makeItem(Material.STAINED_GLASS_PANE, 1, 14, "&c&lRed");
        this.green = Utils.makeItem(Material.STAINED_GLASS_PANE, 1, 5, "&a&lGreen");
        this.blue = Utils.makeItem(Material.STAINED_GLASS_PANE, 1, 11, "&b&lBlue");
        this.off = Utils.makeItem(Material.STAINED_GLASS_PANE, 1, 0, "&b&lOff");

        inventory.setItem(10, this.red);
        inventory.setItem(12, this.blue);
        inventory.setItem(14, this.green);
        inventory.setItem(16, this.off);
        IridiumSkyblock.getInstance().registerListeners(this);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().equals(inventory)) {
            e.setCancelled(true);

            if(e.getCurrentItem() != null) {
                if (e.getCurrentItem().equals(blue))
                    IridiumSkyblock.getIslandManager().getIslandViaId(islandID).setBorderColor(NMSUtils.Color.Blue);
                if (e.getCurrentItem().equals(red))
                    IridiumSkyblock.getIslandManager().getIslandViaId(islandID).setBorderColor(NMSUtils.Color.Red);
                if (e.getCurrentItem().equals(green))
                    IridiumSkyblock.getIslandManager().getIslandViaId(islandID).setBorderColor(NMSUtils.Color.Green);
                if (e.getCurrentItem().equals(off))
                    IridiumSkyblock.getIslandManager().getIslandViaId(islandID).setBorderColor(NMSUtils.Color.Off);
                IridiumSkyblock.getIslandManager().getIslandViaId(islandID).sendBorder();
            }
        }
    }
}

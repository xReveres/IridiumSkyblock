package com.iridium.iridiumskyblock.gui;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.NMSUtils;
import com.iridium.iridiumskyblock.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BorderColorGUI extends GUI implements Listener {

    public ItemStack red;
    public ItemStack green;
    public ItemStack blue;
    public ItemStack off;

    public BorderColorGUI(Island island) {
        super(island, 27, IridiumSkyblock.getInventories().borderColorGUITitle);
        IridiumSkyblock.getInstance().registerListeners(this);
    }

    @Override
    public void addContent() {
        super.addContent();
        this.red = Utils.makeItem(Material.STAINED_GLASS_PANE, 1, 14, "&c&lRed");
        this.green = Utils.makeItem(Material.STAINED_GLASS_PANE, 1, 5, "&a&lGreen");
        this.blue = Utils.makeItem(Material.STAINED_GLASS_PANE, 1, 11, "&b&lBlue");
        this.off = Utils.makeItem(Material.STAINED_GLASS_PANE, 1, 0, "&b&lOff");

        getInventory().setItem(10, this.red);
        getInventory().setItem(12, this.blue);
        getInventory().setItem(14, this.green);
        getInventory().setItem(16, this.off);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().equals(getInventory())) {
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

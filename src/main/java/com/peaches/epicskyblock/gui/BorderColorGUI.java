package com.peaches.epicskyblock.gui;

import com.peaches.epicskyblock.EpicSkyblock;
import com.peaches.epicskyblock.Island;
import com.peaches.epicskyblock.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class BorderColorGUI {

    public Inventory inventory;
    public int islandID;

    public ItemStack red;
    public ItemStack green;
    public ItemStack blue;
    public ItemStack off;

    public BorderColorGUI(Island island) {
        this.inventory = Bukkit.createInventory(null, 27, Utils.color(EpicSkyblock.getConfiguration().BorderColorGUITitle));
        islandID = island.getId();

        for (int i = 0; i < 27; i++) {
            inventory.setItem(i, Utils.makeItem(Material.STAINED_GLASS_PANE, 1, 7, " "));
        }
        this.red = Utils.makeItem(Material.STAINED_GLASS_PANE, 1, 14, "&c&lRed");
        this.green = Utils.makeItem(Material.STAINED_GLASS_PANE, 1, 5, "&a&lGreen");
        this.blue = Utils.makeItem(Material.STAINED_GLASS_PANE, 1, 11, "&b&lBlue");
        this.off = Utils.makeItem(Material.STAINED_GLASS_PANE, 1, 0, "&b&lOff");

        inventory.setItem(10, this.red);
        inventory.setItem(12, this.blue);
        inventory.setItem(14, this.green);
        inventory.setItem(16, this.off);
    }
}

package com.iridium.iridiumskyblock.gui;

import com.iridium.iridiumskyblock.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class BorderColorGUI extends GUI implements Listener {

    public ItemStack red;
    public ItemStack green;
    public ItemStack blue;
    public ItemStack off;

    public BorderColorGUI(Island island) {
        super(island, IridiumSkyblock.getInventories().borderColorGUISize, IridiumSkyblock.getInventories().borderColorGUITitle);
        IridiumSkyblock.getInstance().registerListeners(this);
    }

    @Override
    public void addContent() {
        super.addContent();
        this.red = Utils.makeItem(MultiversionMaterials.RED_STAINED_GLASS_PANE, 1, "&c&lRed");
        this.green = Utils.makeItem(MultiversionMaterials.LIME_STAINED_GLASS_PANE, 1, "&a&lGreen");
        this.blue = Utils.makeItem(MultiversionMaterials.BLUE_STAINED_GLASS_PANE, 1, "&b&lBlue");
        this.off = Utils.makeItem(MultiversionMaterials.WHITE_STAINED_GLASS_PANE, 1, "&b&lOff");

        setItem(10, this.red);
        setItem(12, this.blue);
        setItem(14, this.green);
        setItem(16, this.off);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().equals(getInventory())) {
            e.setCancelled(true);
            if (e.getCurrentItem() != null) {
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

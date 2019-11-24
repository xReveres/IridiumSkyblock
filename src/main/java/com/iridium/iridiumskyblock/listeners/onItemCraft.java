package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

public class onItemCraft implements Listener {

    @EventHandler
    public void onItemCraft(PrepareItemCraftEvent e) {
        if ((e.getInventory().getResult() == null)) {
            return;
        }
        for (ItemStack itemStack : e.getInventory().getContents()) {
            if (Utils.makeItemHidden(IridiumSkyblock.getInventories().crystal).isSimilar(itemStack)) {
                e.getInventory().setResult(null);
                return;
            }
        }
    }
}

package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;

public class onLeafDecay implements Listener {
    @EventHandler
    public void onLeafDecay(LeavesDecayEvent e) {
        if (!IridiumSkyblock.getConfiguration().disableLeafDecay) return;
        if (e.getBlock().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getWorld()) || e.getBlock().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getNetherWorld())) {
            e.setCancelled(true);
        }
    }
}

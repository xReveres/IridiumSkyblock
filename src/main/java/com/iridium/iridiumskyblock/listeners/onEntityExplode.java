package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class onEntityExplode implements Listener {
    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e) {
        try {
            if (IridiumSkyblock.getConfiguration().disableExplosions && (e.getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getWorld()) || e.getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getNetherWorld()))) {
                e.setCancelled(true);
            }
        } catch (Exception ex) {
            IridiumSkyblock.getInstance().sendErrorMessage(ex);
        }
    }
}

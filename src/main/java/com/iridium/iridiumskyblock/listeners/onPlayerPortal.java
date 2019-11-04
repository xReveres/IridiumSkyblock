package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

public class onPlayerPortal implements Listener {

    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent e) {
        Island island = IridiumSkyblock.getIslandManager().getIslandViaLocation(e.getFrom());
        if (island != null) {
            if (e.getFrom().getWorld().equals(IridiumSkyblock.getIslandManager().getWorld())) {
                e.setCancelled(true);
                island.teleportNetherHome(e.getPlayer());
            } else if (e.getFrom().getWorld().equals(IridiumSkyblock.getIslandManager().getNetherWorld())) {
                e.setCancelled(true);
                island.teleportHome(e.getPlayer());
            }
        }
    }
}

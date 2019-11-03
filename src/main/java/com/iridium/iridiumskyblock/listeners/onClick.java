package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class onClick implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        try {
            User u = User.getUser(e.getPlayer());
            Island island = u.getIsland();
            if (e.getClickedBlock() != null) {
                if (e.getClickedBlock().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getWorld()) || e.getClickedBlock().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getNetherWorld())) {
                    if (island != null) {
                        if (island.isInIsland(e.getClickedBlock().getLocation())) {
                            // Block is in players island
                            if (!u.bypassing && !u.getIsland().getPermissions(u.role).interact) {
                                e.setCancelled(true);
                            }
                        } else {
                            if (!u.bypassing) {
                                e.setCancelled(true);
                            }
                        }
                    } else {
                        if (!u.bypassing) {
                            e.setCancelled(true);
                        }
                    }
                }
            } else {

            }
        } catch (
                Exception ex) {
            IridiumSkyblock.getInstance().sendErrorMessage(ex);
        }
    }
}

package com.peaches.iridiumskyblock.listeners;

import com.peaches.iridiumskyblock.IridiumSkyblock;
import com.peaches.iridiumskyblock.Island;
import com.peaches.iridiumskyblock.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class onClick implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
    	if (!IridiumSkyblock.getConfiguration().enabledWorlds.contains(e.getPlayer().getLocation().getWorld().getName()) && !IridiumSkyblock.getConfiguration().enabledWorldsIsBlacklist)
    		return;
    	if (IridiumSkyblock.getConfiguration().enabledWorlds.contains(e.getPlayer().getLocation().getWorld().getName()) && IridiumSkyblock.getConfiguration().enabledWorldsIsBlacklist)
    		return;
        try {
            User u = User.getUser(e.getPlayer());
            Island island = u.getIsland();
            if (e.getPlayer().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getWorld())) {
                if (island != null) {
                    if (e.getClickedBlock() != null) {
                        if (e.getClickedBlock().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getWorld())) {
                            if ((e.getClickedBlock().getX() > island.getPos1().getX() && e.getClickedBlock().getX() <= island.getPos2().getX()) && (e.getClickedBlock().getZ() > island.getPos1().getZ() && e.getClickedBlock().getZ() <= island.getPos2().getZ())) {
                                // Block is in players island
                                if (!u.bypassing && !u.getIsland().getPermissions(u.role).interact) {
                                    e.setCancelled(true);
                                }
                            } else {
                                if (!u.bypassing) {
                                    e.setCancelled(true);
                                }
                            }
                        }
                    } else {

                    }
                } else {
                    if (!u.bypassing) {
                        e.setCancelled(true);
                    }
                }
            }
        } catch (Exception ex) {
            IridiumSkyblock.getInstance().sendErrorMessage(ex);
        }
    }
}

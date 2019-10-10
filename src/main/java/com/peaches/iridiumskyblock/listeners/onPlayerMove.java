package com.peaches.iridiumskyblock.listeners;

import com.peaches.iridiumskyblock.IridiumSkyblock;
import com.peaches.iridiumskyblock.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class onPlayerMove implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
    	if (!IridiumSkyblock.getConfiguration().enabledWorlds.contains(e.getPlayer().getLocation().getWorld().getName()) && !IridiumSkyblock.getConfiguration().enabledWorldsIsBlacklist)
    		return;
    	if (IridiumSkyblock.getConfiguration().enabledWorlds.contains(e.getPlayer().getLocation().getWorld().getName()) && IridiumSkyblock.getConfiguration().enabledWorldsIsBlacklist)
    		return;
        try {
            if (e.getPlayer().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getWorld())) {
                if (e.getPlayer().getLocation().getY() < 0) {
                    User u = User.getUser(e.getPlayer());
                    if (u.getIsland() != null) {
                        u.getIsland().teleportHome(e.getPlayer());
                    }
                }
            }
        } catch (Exception ex) {
            IridiumSkyblock.getInstance().sendErrorMessage(ex);
        }
    }
}

package com.peaches.epicskyblock.listeners;

import com.peaches.epicskyblock.EpicSkyblock;
import com.peaches.epicskyblock.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class onPlayerMove implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        try {
            if (e.getPlayer().getLocation().getWorld().equals(EpicSkyblock.getIslandManager().getWorld())) {
                if (e.getPlayer().getLocation().getY() < 0) {
                    User u = User.getUser(e.getPlayer());
                    e.setCancelled(true);
                    if (u.getIsland() != null) {
                        u.getIsland().teleportHome(e.getPlayer());
                    }
                }
            }
        } catch (Exception ex) {
            EpicSkyblock.getInstance().sendErrorMessage(ex);
        }
    }
}

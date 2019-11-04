package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class onPlayerMove implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        try {
            if (e.getPlayer().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getWorld())) {
                if (e.getPlayer().getLocation().getY() < 0 && e.getPlayer().hasPermission("iridiumskyblock.voidtp")) {
                    User u = User.getUser(e.getPlayer());
                    if (u.getIsland() != null) {
                        u.getIsland().teleportHome(e.getPlayer());
                    }
                }
            } else if (e.getPlayer().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getNetherWorld())) {
                if (e.getPlayer().getLocation().getY() < 0) {
                    User u = User.getUser(e.getPlayer());
                    if (u.getIsland() != null) {
                        u.getIsland().teleportNetherHome(e.getPlayer());
                    }
                }
            }
        } catch (Exception ex) {
            IridiumSkyblock.getInstance().sendErrorMessage(ex);
        }
    }
}

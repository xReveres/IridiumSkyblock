package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class onPlayerMove implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        try {
            if (e.getPlayer().getLocation().getY() < 0 && e.getPlayer().hasPermission("iridiumskyblock.voidtp")) {
                Island island = IridiumSkyblock.getIslandManager().getIslandViaLocation(e.getPlayer().getLocation());
                if (island != null) {
                    if (e.getPlayer().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getWorld())) {
                        island.teleportHome(e.getPlayer());
                    } else {
                        island.teleportNetherHome(e.getPlayer());
                    }
                }
            }
        } catch (Exception ex) {
            IridiumSkyblock.getInstance().sendErrorMessage(ex);
        }
    }
}

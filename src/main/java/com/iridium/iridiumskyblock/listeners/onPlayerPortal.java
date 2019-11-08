package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.Role;
import com.iridium.iridiumskyblock.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

public class onPlayerPortal implements Listener {

    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent e) {
        User u = User.getUser(e.getPlayer());
        Island island = IridiumSkyblock.getIslandManager().getIslandViaLocation(e.getFrom());
        if (island != null) {
            if (e.getFrom().getWorld().equals(IridiumSkyblock.getIslandManager().getWorld())) {
                e.setCancelled(true);
                if (island.getPermissions(u.islandID == island.getId() ? u.role : Role.Visitor).useNetherPortal || u.bypassing)
                    island.teleportNetherHome(e.getPlayer());
            } else if (e.getFrom().getWorld().equals(IridiumSkyblock.getIslandManager().getNetherWorld())) {
                e.setCancelled(true);
                if (island.getPermissions(u.islandID == island.getId() ? u.role : Role.Visitor).useNetherPortal || u.bypassing)
                    island.teleportHome(e.getPlayer());
            }
        }
    }
}

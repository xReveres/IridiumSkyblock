package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.Roles;
import com.iridium.iridiumskyblock.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class onClick implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        try {
            User u = User.getUser(e.getPlayer());
            if (e.getClickedBlock() != null) {
                Island island = IridiumSkyblock.getIslandManager().getIslandViaLocation(e.getClickedBlock().getLocation());
                if (island != null) {
                    if ((!island.getPermissions(u.islandID == island.getId() ? u.role : Roles.Visitor).interact) && !u.bypassing)
                        e.setCancelled(true);
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

package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.Role;
import com.iridium.iridiumskyblock.User;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class onEntityPickupItem implements Listener {

    @EventHandler
    public void onEntityPickupItem(EntityPickupItemEvent e) {
        Island island = IridiumSkyblock.getIslandManager().getIslandViaLocation(e.getItem().getLocation());
        if (island != null && e.getEntity() instanceof Player) {
            User u = User.getUser((OfflinePlayer) e.getEntity());
            if ((!island.getPermissions((u.islandID == island.getId() || island.isCoop(u.getIsland())) ? (island.isCoop(u.getIsland()) ? Role.Member : u.getRole()) : Role.Visitor).pickupItems) && !u.bypassing)
                e.setCancelled(true);
        }
    }
}

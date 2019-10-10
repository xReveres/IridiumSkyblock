package com.peaches.iridiumskyblock.listeners;

import com.peaches.iridiumskyblock.*;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onPlayerJoinLeave implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
    	if (!IridiumSkyblock.getConfiguration().enabledWorlds.contains(e.getPlayer().getLocation().getWorld()))
    		return;
        try {
            if (IridiumSkyblock.getIslandManager().users.containsKey(e.getPlayer().getName())) {
                User user = IridiumSkyblock.getIslandManager().users.get(e.getPlayer().getName());
                IridiumSkyblock.getIslandManager().users.remove(e.getPlayer().getName());
                user.player = e.getPlayer().getUniqueId().toString();
                user.name = e.getPlayer().getName();
                if (user.getIsland() != null) {
                    if (user.getIsland().getOwner().equalsIgnoreCase(e.getPlayer().getName())) {
                        user.role = Roles.Owner;
                        user.getIsland().setOwner(e.getPlayer().getUniqueId().toString());
                    } else {
                        user.role = Roles.Visitor;
                    }
                    user.getIsland().getMembers().remove(e.getPlayer().getName());
                    user.getIsland().getMembers().add(e.getPlayer().getUniqueId().toString());
                }
                user.bypassing = false;
                IridiumSkyblock.getIslandManager().users.put(e.getPlayer().getUniqueId().toString(), user);
            }
            if (IridiumSkyblock.getIslandManager().users.containsKey(e.getPlayer().getUniqueId().toString())) {
                User user = IridiumSkyblock.getIslandManager().users.get(e.getPlayer().getUniqueId().toString());
                if (user.getIsland() != null) {
                    if (user.getIsland().getOwner().equals(e.getPlayer().getName())) {
                        user.getIsland().setOwner(e.getPlayer().getUniqueId().toString());
                    }
                    user.name = e.getPlayer().getName();
                    user.getIsland().getMembers().remove(e.getPlayer().getName());
                    user.getIsland().getMembers().add(e.getPlayer().getUniqueId().toString());
                }
            }
            Bukkit.getScheduler().scheduleSyncDelayedTask(IridiumSkyblock.getInstance(), () -> {
                Island island = IridiumSkyblock.getIslandManager().getIslandViaLocation(e.getPlayer().getLocation());
                if (island != null) {
                    island.sendBorder(e.getPlayer());
                }
            }, 1);
        } catch (Exception ex) {
            IridiumSkyblock.getInstance().sendErrorMessage(ex);
        }
    }
}

package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.*;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onPlayerJoinLeave implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (e.getPlayer().isOp()) {
            if (IridiumSkyblock.getConfiguration().notifyAvailableUpdate && !IridiumSkyblock.getInstance().getLatest().equals(IridiumSkyblock.getInstance().getDescription().getVersion())) {
                e.getPlayer().sendMessage(Utils.color(IridiumSkyblock.getConfiguration().prefix + " &7This message is only seen by opped players."));
                e.getPlayer().sendMessage(Utils.color(IridiumSkyblock.getConfiguration().prefix + " &7Newer version available: " + IridiumSkyblock.getInstance().getLatest()));
            }
        }
        try {
            if (IridiumSkyblock.getIslandManager().users.containsKey(e.getPlayer().getName())) {
                User user = IridiumSkyblock.getIslandManager().users.get(e.getPlayer().getName());
                IridiumSkyblock.getIslandManager().users.remove(e.getPlayer().getName());
                user.player = e.getPlayer().getUniqueId().toString();
                user.name = e.getPlayer().getName();
                if (user.getIsland() != null) {
                    if (user.getIsland().getOwner().equalsIgnoreCase(e.getPlayer().getName())) {
                        user.role = Role.Owner;
                        user.getIsland().setOwner(e.getPlayer());
                    } else {
                        user.role = Role.Visitor;
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
                        user.getIsland().setOwner(e.getPlayer());
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
        User u = User.getUser(e.getPlayer());
        u.name = e.getPlayer().getName();

        if (u.flying && (u.getIsland() == null || u.getIsland().getFlightBooster() == 0)) {
            e.getPlayer().setAllowFlight(false);
            e.getPlayer().setFlying(false);
            u.flying = false;
        }
    }
}

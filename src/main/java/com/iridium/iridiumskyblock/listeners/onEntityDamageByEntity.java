package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.User;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class onEntityDamageByEntity implements Listener {

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
    	if (!IridiumSkyblock.getConfiguration().enabledWorlds.contains(e.getEntity().getLocation().getWorld().getName()) && !IridiumSkyblock.getConfiguration().enabledWorldsIsBlacklist)
    		return;
    	if (!IridiumSkyblock.getConfiguration().enabledWorlds.contains(e.getDamager().getLocation().getWorld().getName()) && !IridiumSkyblock.getConfiguration().enabledWorldsIsBlacklist)
    		return;
    	if (IridiumSkyblock.getConfiguration().enabledWorlds.contains(e.getEntity().getLocation().getWorld().getName()) && IridiumSkyblock.getConfiguration().enabledWorldsIsBlacklist)
    		return;
    	if (IridiumSkyblock.getConfiguration().enabledWorlds.contains(e.getDamager().getLocation().getWorld().getName()) && IridiumSkyblock.getConfiguration().enabledWorldsIsBlacklist)
    		return;
        try {
            if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) { // Deals with two players pvping in IridiumSkyblock world
                if (e.getEntity().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getWorld()) || e.getEntity().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getNetherWorld())) {
                    e.setCancelled(true);
                }
            }
            if (e.getEntity() instanceof Player && e.getDamager() instanceof Arrow) { // Deals with A player getting damaged by a bow fired from a player in IridiumSkyblock world
                Arrow arrow = (Arrow) e.getDamager();
                if (arrow.getShooter() instanceof Player) {
                    if (e.getEntity().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getWorld()) || e.getEntity().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getNetherWorld())) {
                        e.setCancelled(true);
                    }
                }
            }
            if (e.getDamager() instanceof Player && !(e.getEntity() instanceof Player)) { // Deals with a player attacking animals that are not from their island
                if (e.getEntity().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getWorld()) || e.getEntity().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getNetherWorld())) {
                    User user = User.getUser((Player) e.getDamager());
                    if (user.getIsland() != null) {
                        if (!user.getIsland().isInIsland(e.getEntity().getLocation())) {
                            e.setCancelled(true);
                        }
                    } else {
                        e.setCancelled(true);
                    }
                }
            }
            if (e.getEntity() instanceof Player && !(e.getDamager() instanceof Player)) { //Deals with a mob attacking a player that doesnt belong to the island (/is home traps?)
                if (e.getEntity().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getWorld()) || e.getEntity().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getWorld())) {
                    User user = User.getUser((Player) e.getEntity());
                    if (user.getIsland() != null) {
                        if (!user.getIsland().isInIsland(e.getDamager().getLocation())) {
                            e.setCancelled(true);
                        }
                    } else {
                        e.setCancelled(true);
                    }
                }
            }
            if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) { // Deals with two allies pvping
                User u = User.getUser((Player) e.getEntity());
                User user = User.getUser((Player) e.getDamager());
                if (u.getIsland() != null) {
                    if (u.getIsland().equals(user.getIsland())) {
                        e.setCancelled(true);
                    }
                }
            }
            if (e.getEntity() instanceof Player && e.getDamager() instanceof Arrow) { // Deals with two allies pvping with bows
                Arrow arrow = (Arrow) e.getDamager();
                if (arrow.getShooter() instanceof Player) {
                    User u = User.getUser((Player) e.getEntity());
                    User user = User.getUser((Player) arrow.getShooter());
                    if (u.getIsland() != null) {
                        if (u.getIsland().equals(user.getIsland())) {
                            e.setCancelled(true);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            IridiumSkyblock.getInstance().sendErrorMessage(ex);
        }
    }
}

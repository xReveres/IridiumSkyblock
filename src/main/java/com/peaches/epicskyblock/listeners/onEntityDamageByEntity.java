package com.peaches.epicskyblock.listeners;

import com.peaches.epicskyblock.EpicSkyblock;
import com.peaches.epicskyblock.Island;
import com.peaches.epicskyblock.User;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class onEntityDamageByEntity implements Listener {

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        try {
            if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) { // Deals with two players pvping in EpicSkyblock world
                if (e.getEntity().getLocation().getWorld().equals(EpicSkyblock.getIslandManager().getWorld())) {
                    e.setCancelled(true);
                }
            }
            if (e.getEntity() instanceof Player && e.getDamager() instanceof Arrow) { // Deals with A player getting damaged by a bow fired from a player in EpicSkyblock world
                Arrow arrow = (Arrow) e.getDamager();
                if (arrow.getShooter() instanceof Player) {
                    if (e.getEntity().getLocation().getWorld().equals(EpicSkyblock.getIslandManager().getWorld())) {
                        e.setCancelled(true);
                    }
                }
            }
            if (e.getDamager() instanceof Player && !(e.getEntity() instanceof Player)) { // Deals with a player attacking animals that are not from their island
                User user = User.getUser((Player) e.getDamager());
                if (user.getIsland() != null) {
                    if (!user.getIsland().isInIsland(e.getEntity().getLocation())) {
                        e.setCancelled(true);
                    }
                } else {
                    e.setCancelled(true);
                }
            }
            if (e.getEntity() instanceof Player && !(e.getDamager() instanceof Player)) { //Deals with a mob attacking a player that doesnt belong to the island (/is home traps?)
                User user = User.getUser((Player) e.getEntity());
                if (user.getIsland() != null) {
                    if (!user.getIsland().isInIsland(e.getDamager().getLocation())) {
                        e.setCancelled(true);
                    }
                } else {
                    e.setCancelled(true);
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
            EpicSkyblock.getInstance().sendErrorMessage(ex);
        }
    }
}

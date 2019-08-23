package com.peaches.epicskyblock.listeners;

import com.peaches.epicskyblock.EpicSkyblock;
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
            if (e.getDamager() instanceof Player) {
                Player p = (Player) e.getDamager();
                if (e.getEntity() instanceof Player) {
                    Player player = (Player) e.getEntity();
                    User damager = User.getUser(p);
                    User entity = User.getUser(player);
                    if (e.getEntity().getLocation().getWorld().equals(EpicSkyblock.getIslandManager().getWorld())) {
                        e.setCancelled(true);
                    } else {
                        if (damager.getIsland() != null && entity.getIsland() != null) {
                            if (damager.getIsland().equals(entity.getIsland()) && damager.getIsland() != null) {
                                e.setCancelled(true);
                            }
                        }
                    }
                }else if(e.getDamager() instanceof Arrow){
                    Arrow a = (Arrow) e.getDamager();
                    if(a.getShooter() instanceof Player){
                        Player player = (Player) a.getShooter();
                        User damager = User.getUser(p);
                        User entity = User.getUser(player);
                        if (e.getEntity().getLocation().getWorld().equals(EpicSkyblock.getIslandManager().getWorld())) {
                            e.setCancelled(true);
                        } else {
                            if (damager.getIsland() != null && entity.getIsland() != null) {
                                if (damager.getIsland().equals(entity.getIsland()) && damager.getIsland() != null) {
                                    e.setCancelled(true);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            EpicSkyblock.getInstance().sendErrorMessage(ex);
        }
    }
}

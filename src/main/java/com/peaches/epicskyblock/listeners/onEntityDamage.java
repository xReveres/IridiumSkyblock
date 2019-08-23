package com.peaches.epicskyblock.listeners;

import com.peaches.epicskyblock.EpicSkyblock;
import com.peaches.epicskyblock.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class onEntityDamage implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        try {
            if (e.getEntity() instanceof Player) {
                if (e.getCause().equals(EntityDamageEvent.DamageCause.VOID)) {
                    Player p = (Player) e.getEntity();
                    User u = User.getUser(p);
                    if (e.getEntity().getLocation().getWorld().equals(EpicSkyblock.getIslandManager().getWorld())) {
                        e.setCancelled(true);
                        if (u.getIsland() != null) {
                            u.getIsland().teleportHome(p);
                        }
                    }
                }
                if (e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
                    User u = User.getUser(((Player) e.getEntity()).getPlayer());
                    if (u.getIsland() != null) {
                        if (u.getIsland().isInIsland(e.getEntity().getLocation())) {
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

package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.Role;
import com.iridium.iridiumskyblock.User;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.vehicle.VehicleDamageEvent;

public class onEntityDamageByEntity implements Listener {

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        try {
            Island island = IridiumSkyblock.getIslandManager().getIslandViaLocation(e.getEntity().getLocation());
            if (IridiumSkyblock.getConfiguration().disablePvPOnIslands && e.getEntity() instanceof Player && e.getDamager() instanceof Player) { // Deals with two players pvping in IridiumSkyblock world
                if (e.getEntity().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getWorld()) || e.getEntity().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getNetherWorld())) {
                    e.setCancelled(true);
                }
            }
            if (IridiumSkyblock.getConfiguration().disablePvPOnIslands && e.getEntity() instanceof Player && e.getDamager() instanceof Arrow) { // Deals with A player getting damaged by a bow fired from a player in IridiumSkyblock world
                Arrow arrow = (Arrow) e.getDamager();
                if (arrow.getShooter() instanceof Player) {
                    if (e.getEntity().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getWorld()) || e.getEntity().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getNetherWorld())) {
                        e.setCancelled(true);
                    }
                }
            }
            if (e.getDamager() instanceof Arrow && !(e.getEntity() instanceof Player)) { // Deals with a player attacking animals with bows that are not from their island
                Arrow arrow = (Arrow) e.getDamager();
                if (arrow.getShooter() instanceof Player) {
                    if (island != null) {
                        User user = User.getUser((Player) arrow.getShooter());
                        if ((!island.getPermissions((user.islandID == island.getId() || island.isCoop(user.getIsland())) ? (island.isCoop(user.getIsland()) ? Role.Member : user.getRole()) : Role.Visitor).killMobs) && !user.bypassing)
                            e.setCancelled(true);
                    }
                }
            }
            if (e.getDamager() instanceof Player && !(e.getEntity() instanceof Player)) { // Deals with a player attacking animals that are not from their island
                if (island != null) {
                    User user = User.getUser((Player) e.getDamager());
                    if ((!island.getPermissions((user.islandID == island.getId() || island.isCoop(user.getIsland())) ? (island.isCoop(user.getIsland()) ? Role.Member : user.getRole()) : Role.Visitor).killMobs) && !user.bypassing)
                        e.setCancelled(true);
                }
            }
            if (IridiumSkyblock.getConfiguration().disablePvPOnIslands && e.getEntity() instanceof Player && !(e.getDamager() instanceof Player)) { //Deals with a mob attacking a player that doesnt belong to the island (/is home traps?)
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
            if (IridiumSkyblock.getConfiguration().disablePvPBetweenIslandMembers && e.getEntity() instanceof Player && e.getDamager() instanceof Player) { // Deals with two allies pvping
                User u = User.getUser((Player) e.getEntity());
                User user = User.getUser((Player) e.getDamager());
                if (u.getIsland() != null) {
                    if (u.getIsland().equals(user.getIsland())) {
                        e.setCancelled(true);
                    }
                }
            }
            if (IridiumSkyblock.getConfiguration().disablePvPBetweenIslandMembers && e.getEntity() instanceof Player && e.getDamager() instanceof Arrow) { // Deals with two allies pvping with bows
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

    @EventHandler
    public void onVehicleDamage(VehicleDamageEvent e) {
        if (e.getAttacker() instanceof Player) {
            User user = User.getUser((Player) e.getAttacker());
            Island island = IridiumSkyblock.getIslandManager().getIslandViaLocation(e.getVehicle().getLocation());
            if ((!island.getPermissions((user.islandID == island.getId() || island.isCoop(user.getIsland())) ? (island.isCoop(user.getIsland()) ? Role.Member : user.getRole()) : Role.Visitor).killMobs) && !user.bypassing)
                e.setCancelled(true);
        }
    }
}

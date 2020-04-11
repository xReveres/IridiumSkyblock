package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.IslandManager;
import com.iridium.iridiumskyblock.User;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.vehicle.VehicleDamageEvent;
import org.bukkit.projectiles.ProjectileSource;

import java.util.Objects;
import java.util.function.Supplier;

public class EntityDamageByEntityListener implements Listener {

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        try {
            final Entity entity = event.getEntity();
            final Location location = entity.getLocation();
            final IslandManager islandManager = IridiumSkyblock.getIslandManager();
            if (!islandManager.isIslandWorld(location)) return;

            final Entity damager = event.getDamager();

            final Supplier<Island> islandSupplier = () -> islandManager.getIslandViaLocation(location);
            final Supplier<Player> playerSupplier = () -> (Player) entity;
            final Supplier<User> userSupplier = () -> User.getUser(playerSupplier.get());
            final Supplier<Island> userIslandSupplier = () -> userSupplier.get().getIsland();
            final Supplier<Arrow> arrowSupplier = () -> (Arrow) damager;
            final Supplier<ProjectileSource> projectileSourceSupplier = () -> arrowSupplier.get().getShooter();
            final Supplier<Player> shooterSupplier = () -> (Player) projectileSourceSupplier.get();
            final Supplier<User> shootingUserSupplier = () -> User.getUser(Objects.requireNonNull(shooterSupplier.get()));
            final Supplier<Player> damagingPlayerSupplier = () -> (Player) damager;
            final Supplier<User> damagingUserSupplier = () -> User.getUser(damagingPlayerSupplier.get());

            // Deals with two players pvping in IridiumSkyblock world
            if (IridiumSkyblock.getConfiguration().disablePvPOnIslands
                    && entity instanceof Player
                    && damager instanceof Player) {
                event.setCancelled(true);
                return;
            }

            // Deals with A player getting damaged by a bow fired from a player in IridiumSkyblock world
            if (IridiumSkyblock.getConfiguration().disablePvPOnIslands
                    && entity instanceof Player
                    && damager instanceof Arrow
                    && projectileSourceSupplier.get() instanceof Player) {
                event.setCancelled(true);
                return;
            }

            // Deals with a player attacking animals with bows that are not from their island
            if (damager instanceof Arrow
                    && !(entity instanceof Player)
                    && projectileSourceSupplier.get() instanceof Player
                    && islandSupplier.get() != null
                    && !islandSupplier.get().getPermissions(shootingUserSupplier.get()).killMobs) {
                event.setCancelled(true);
                return;
            }

            // Deals with a player attacking animals that are not from their island
            if (damager instanceof Player
                    && !(entity instanceof Player)
                    && islandSupplier.get() != null
                    && !islandSupplier.get().getPermissions(damagingUserSupplier.get()).killMobs) {
                event.setCancelled(true);
                return;
            }

            //Deals with a mob attacking a player that doesn't belong to the island (/is home traps?)
            if (IridiumSkyblock.getConfiguration().disablePvPOnIslands
                    && entity instanceof Player
                    && !(damager instanceof Player)) {
                if (userIslandSupplier.get() != null) {
                    if (!userIslandSupplier.get().isInIsland(damager.getLocation())) {
                        event.setCancelled(true);
                        return;
                    }
                } else {
                    event.setCancelled(true);
                    return;
                }
            }

            // Deals with two allies pvping
            if (IridiumSkyblock.getConfiguration().disablePvPBetweenIslandMembers
                    && entity instanceof Player
                    && damager instanceof Player
                    && userIslandSupplier.get() != null
                    && userIslandSupplier.get().equals(damagingUserSupplier.get().getIsland())) {
                event.setCancelled(true);
                return;
            }

            // Deals with two allies pvping with bows
            if (IridiumSkyblock.getConfiguration().disablePvPBetweenIslandMembers
                    && entity instanceof Player
                    && damager instanceof Arrow
                    && projectileSourceSupplier.get() instanceof Player
                    && userIslandSupplier.get() != null
                    && userIslandSupplier.get().equals(damagingUserSupplier.get().getIsland())) {
                event.setCancelled(true);
                return;
            }
        } catch (Exception ex) {
            IridiumSkyblock.getInstance().sendErrorMessage(ex);
        }
    }

    @EventHandler
    public void onVehicleDamage(VehicleDamageEvent event) {
        try {
            final Vehicle vehicle = event.getVehicle();
            final Location location = vehicle.getLocation();
            final IslandManager islandManager = IridiumSkyblock.getIslandManager();
            if (!islandManager.isIslandWorld(location)) return;

            final Entity attacker = event.getAttacker();
            if (!(attacker instanceof Player)) return;

            final Player player = (Player) attacker;
            final User user = User.getUser(player);

            final Island island = islandManager.getIslandViaLocation(location);
            if (island == null) return;

            if (!island.getPermissions(user).killMobs)
                event.setCancelled(true);
        } catch (Exception ex) {
            IridiumSkyblock.getInstance().sendErrorMessage(ex);
        }
    }
}

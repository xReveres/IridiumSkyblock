package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.*;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.lang.reflect.InvocationTargetException;

public class PlayerPortalListener implements Listener {

    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent event) {
        try {
            final Location fromLocation = event.getFrom();
            final IslandManager islandManager = IridiumSkyblock.getIslandManager();
            final Island island = islandManager.getIslandViaLocation(fromLocation);
            if (island == null) return;

            if (!event.getCause().equals(PlayerTeleportEvent.TeleportCause.NETHER_PORTAL)) return;

            if (!IridiumSkyblock.getConfiguration().netherIslands) {
                event.setCancelled(true);
                return;
            }

            final Player player = event.getPlayer();
            final User user = User.getUser(player);
            if (!island.getPermissions(user).useNetherPortal) {
                event.setCancelled(true);
                return;
            }

            if (XMaterial.supports(15))
                event.setCanCreatePortal(true);
            else {
                try {
                    PlayerPortalEvent.class.getMethod("useTravelAgent", boolean.class).invoke(event, true);
                    Class.forName("org.bukkit.TravelAgent")
                            .getMethod("setCanCreatePortal", boolean.class)
                            .invoke(PlayerPortalEvent.class.getMethod("getPortalTravelAgent").invoke(event), true);
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            final World world = fromLocation.getWorld();
            if (world == null) return;

            final String worldName = world.getName();
            if (worldName.equals(IridiumSkyblock.getConfiguration().worldName))
                event.setTo(island.getNetherhome());
            else if (worldName.equals(IridiumSkyblock.getConfiguration().netherWorldName))
                event.setTo(island.getHome());
        } catch (Exception e) {
            IridiumSkyblock.getInstance().sendErrorMessage(e);
        }
    }
}

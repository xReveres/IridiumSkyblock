package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.vehicle.VehicleCreateEvent;

public class onEntitySpawn implements Listener {

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent e) {
        if (IridiumSkyblock.getConfiguration().blockedEntities.contains(e.getEntityType()) && (e.getEntity().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getNetherWorld()) || e.getEntity().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getWorld()))) {
            IridiumSkyblock.getInstance().entities.put(e.getEntity().getUniqueId(), IridiumSkyblock.getIslandManager().getIslandViaLocation(e.getEntity().getLocation()));
            monitorEntity(e.getEntity());
        }
    }

    @EventHandler
    public void onVehicleSpawn(VehicleCreateEvent e) {
        if (IridiumSkyblock.getConfiguration().blockedEntities.contains(e.getVehicle().getType()) && (e.getVehicle().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getNetherWorld()) || e.getVehicle().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getWorld()))) {
            IridiumSkyblock.getInstance().entities.put(e.getVehicle().getUniqueId(), IridiumSkyblock.getIslandManager().getIslandViaLocation(e.getVehicle().getLocation()));
            monitorEntity(e.getVehicle());
        }
    }

    public void monitorEntity(Entity entity) {
        if (entity != null) {
            if (!entity.isDead()) {
                Island startingIsland = IridiumSkyblock.getInstance().entities.get(entity.getUniqueId());
                if (startingIsland.isInIsland(entity.getLocation())) {
                    //The entity is still in the island, so make a scheduler to check again
                    Bukkit.getScheduler().scheduleSyncDelayedTask(IridiumSkyblock.getInstance(), () -> monitorEntity(entity), 20);
                } else {
                    //The entity is not in the island, so remove it
                    entity.remove();
                    IridiumSkyblock.getInstance().entities.remove(entity.getUniqueId());
                }
            }
        }
    }
}

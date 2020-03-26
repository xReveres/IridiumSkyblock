package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.HashMap;
import java.util.UUID;

public class onEntitySpawn implements Listener {

    public HashMap<UUID, Island> entities = new HashMap<>();

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent e) {
        IridiumSkyblock.getInstance().getLogger().info(e.getEntityType().name());
        if (IridiumSkyblock.getConfiguration().blockedEntities.contains(e.getEntityType()) && (e.getEntity().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getNetherWorld()) || e.getEntity().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getWorld()))) {
            entities.put(e.getEntity().getUniqueId(), IridiumSkyblock.getIslandManager().getIslandViaLocation(e.getEntity().getLocation()));
            monitorEntity(e.getEntity());
        }
    }

    public void monitorEntity(Entity entity) {
        IridiumSkyblock.getInstance().getLogger().info("1");
        if (entity != null) {
            IridiumSkyblock.getInstance().getLogger().info(entity.getUniqueId().toString());
            if (!entity.isDead()) {
                Island startingIsland = entities.get(entity.getUniqueId());
                if (startingIsland.isInIsland(entity.getLocation())) {
                    //The entity is still in the island, so make a scheduler to check again
                    Bukkit.getScheduler().scheduleSyncDelayedTask(IridiumSkyblock.getInstance(), () -> monitorEntity(entity), 20);
                } else {
                    //The entity is not in the island, so remove it
                    entity.remove();
                    IridiumSkyblock.getInstance().getLogger().info("ENTITY KILLED");
                    entities.remove(entity.getUniqueId());
                }
            }
        }
    }
}

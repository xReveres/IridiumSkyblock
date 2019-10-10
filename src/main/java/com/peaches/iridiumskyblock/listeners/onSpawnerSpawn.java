package com.peaches.iridiumskyblock.listeners;

import com.peaches.iridiumskyblock.IridiumSkyblock;
import com.peaches.iridiumskyblock.Island;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.SpawnerSpawnEvent;

public class onSpawnerSpawn implements Listener {

    @EventHandler
    public void onSpawnerSpawn(SpawnerSpawnEvent e) {
    	if (!IridiumSkyblock.getConfiguration().enabledWorlds.contains(e.getLocation().getWorld()))
    		return;
        try {
            Island island = IridiumSkyblock.getIslandManager().getIslandViaLocation(e.getLocation());
            if (island != null) {
                if (island.getSpawnerBooster() != 0) {
                    Bukkit.getScheduler().scheduleSyncDelayedTask(IridiumSkyblock.getInstance(), () -> e.getSpawner().setDelay(e.getSpawner().getDelay() / 2), 0);
                }
            }
        } catch (Exception ex) {
            IridiumSkyblock.getInstance().sendErrorMessage(ex);
        }
    }

}

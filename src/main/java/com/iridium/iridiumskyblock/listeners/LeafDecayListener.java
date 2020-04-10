package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.IslandManager;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;

public class LeafDecayListener implements Listener {

    @EventHandler
    public void onLeafDecay(LeavesDecayEvent event) {
        try {
            if (!IridiumSkyblock.getConfiguration().disableLeafDecay) return;

            final Block block = event.getBlock();
            final Location location = block.getLocation();
            final World world = location.getWorld();
            if (world == null) return;

            final IslandManager islandManager = IridiumSkyblock.getIslandManager();

            final World islandWorld = islandManager.getWorld();
            if (islandWorld == null) return;

            final World islandNetherWorld = islandManager.getNetherWorld();
            if (islandNetherWorld == null) return;

            final String worldName = world.getName();
            if (!(worldName.equals(islandWorld.getName()) || worldName.equals(islandNetherWorld.getName()))) return;

            event.setCancelled(true);
        } catch (Exception e) {
            IridiumSkyblock.getInstance().sendErrorMessage(e);
        }
    }

}

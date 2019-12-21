package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;

public class onBlockPiston implements Listener {

    @EventHandler
    public void onBlockPistonExtend(BlockPistonExtendEvent e) {
        Island island = IridiumSkyblock.getIslandManager().getIslandViaLocation(e.getBlock().getLocation());
        for (Block b : e.getBlocks()) {
            Location loc = b.getLocation();
            switch (e.getDirection()) {
                case NORTH:
                    loc.add(0, 0, -1);
                    break;
                case EAST:
                    loc.add(1, 0, 0);
                    break;
                case SOUTH:
                    loc.add(0, 0, 1);
                    break;
                case WEST:
                    loc.add(-1, 0, 0);
                    break;
                case UP:
                    loc.add(0, 1, 0);
                    break;
                case DOWN:
                    loc.add(0, -1, 0);
                    break;
            }
            if (!island.isInIsland(loc)) {
                e.setCancelled(true);
            }
        }
    }


    @EventHandler
    public void onBlockPistonReact(BlockPistonRetractEvent e) {
        Island island = IridiumSkyblock.getIslandManager().getIslandViaLocation(e.getBlock().getLocation());
        for (Block b : e.getBlocks()) {
            if (!island.isInIsland(b.getLocation())) {
                e.setCancelled(true);
            }
        }
    }
}

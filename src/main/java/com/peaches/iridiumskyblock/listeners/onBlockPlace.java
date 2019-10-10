package com.peaches.iridiumskyblock.listeners;

import com.peaches.iridiumskyblock.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class onBlockPlace implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
    	if (!IridiumSkyblock.getConfiguration().enabledWorlds.contains(e.getBlock().getLocation().getWorld().getName()))
    		return;
        try {
            User u = User.getUser(e.getPlayer());
            if (e.getBlock().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getWorld())) {
                Island island = u.getIsland();
                if (island != null) {
                    if (island.builder > -1) {
                        island.builder++;
                        if (island.builder >= IridiumSkyblock.getMissions().builder.getAmount()) {
                            island.builder = IridiumSkyblock.getConfiguration().missionRestart == MissionRestart.Instantly ? 0 : -1;
                            island.completeMission("Builder", IridiumSkyblock.getMissions().builder.getReward());
                        }
                    }
                    if (island.isInIsland(e.getBlock().getLocation())) {
                        if (!u.bypassing && !u.getIsland().getPermissions(u.role).placeBlocks) {
                            e.setCancelled(true);
                        }
                        if (Utils.isBlockValuable(e.getBlock())) {
                            if (!island.blocks.contains(e.getBlock().getLocation()))
                                island.blocks.add(e.getBlock().getLocation());
                        }
                        // Block is in players island
                    } else {
                        if(!u.bypassing){
                            e.setCancelled(true);
                        }
                    }
                } else {
                    if(!u.bypassing){
                        e.setCancelled(true);
                    }
                }
            }
        } catch (Exception ex) {
            IridiumSkyblock.getInstance().sendErrorMessage(ex);
        }
    }
}

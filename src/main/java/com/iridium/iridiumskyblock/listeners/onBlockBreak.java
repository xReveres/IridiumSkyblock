package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.MissionRestart;
import com.iridium.iridiumskyblock.User;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class onBlockBreak implements Listener {

    @EventHandler
    public void onBreakBlock(BlockBreakEvent e) {
    	if (!IridiumSkyblock.getConfiguration().enabledWorlds.contains(e.getBlock().getLocation().getWorld().getName()) && !IridiumSkyblock.getConfiguration().enabledWorldsIsBlacklist)
    		return;
    	if (IridiumSkyblock.getConfiguration().enabledWorlds.contains(e.getBlock().getLocation().getWorld().getName()) && IridiumSkyblock.getConfiguration().enabledWorldsIsBlacklist)
    		return;
        try {
            User u = User.getUser(e.getPlayer());
            if (e.getBlock().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getWorld())) {
                Island island = u.getIsland();
                if (island != null) {
                    if (e.getBlock().getType().name().endsWith("ORE")) {
                        if (u.getIsland().miner > -1) {
                            u.getIsland().miner++;
                            if (u.getIsland().miner >= IridiumSkyblock.getMissions().miner.getAmount()) {
                                island.miner = IridiumSkyblock.getConfiguration().missionRestart == MissionRestart.Instantly ? 0 : -1;
                                u.getIsland().completeMission("Miner", IridiumSkyblock.getMissions().miner.getReward());
                            }
                        }
                    }
                    if (e.getBlock().getType().equals(Material.CROPS)) {
                        if (u.getIsland().farmer > -1) {
                            u.getIsland().farmer++;
                            if (u.getIsland().farmer >= IridiumSkyblock.getMissions().farmer.getAmount()) {
                                island.farmer = IridiumSkyblock.getConfiguration().missionRestart == MissionRestart.Instantly ? 0 : -1;
                                u.getIsland().completeMission("Farmer", IridiumSkyblock.getMissions().farmer.getReward());
                            }
                        }
                    }
                    if (island.isInIsland(e.getBlock().getLocation())) {
                        if (!u.bypassing && !u.getIsland().getPermissions(u.role).breakBlocks) {
                            e.setCancelled(true);
                        }
                        // Block is in players island
                    } else {
                        if (!u.bypassing) {
                            e.setCancelled(true);
                        }
                    }
                } else {
                    if (!u.bypassing) {
                        e.setCancelled(true);
                    }
                }
            }
        } catch (Exception ex) {
            IridiumSkyblock.getInstance().sendErrorMessage(ex);
        }
    }
}

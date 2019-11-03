package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class onBlockPlace implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        try {
            User u = User.getUser(e.getPlayer());
            Island island = IridiumSkyblock.getIslandManager().getIslandViaLocation(e.getBlock().getLocation());
            if (island != null) {

                if (u.islandID == island.getId()) {
                    if (island.builder > -1) {
                        island.builder++;
                        if (island.builder >= IridiumSkyblock.getMissions().builder.getAmount()) {
                            island.builder = IridiumSkyblock.getConfiguration().missionRestart == MissionRestart.Instantly ? 0 : -1;
                            island.completeMission("Builder", IridiumSkyblock.getMissions().builder.getReward());
                        }
                    }
                }
                if ((!island.getPermissions(u.islandID == island.getId() ? u.role : Role.Visitor).placeBlocks) && !u.bypassing)
                    e.setCancelled(true);
                else if (Utils.isBlockValuable(e.getBlock()))
                    island.blocks.add(e.getBlock().getLocation());
            } else {
                if (!u.bypassing) {
                    e.setCancelled(true);
                }
            }
        } catch (
                Exception ex) {
            IridiumSkyblock.getInstance().sendErrorMessage(ex);
        }
    }
}

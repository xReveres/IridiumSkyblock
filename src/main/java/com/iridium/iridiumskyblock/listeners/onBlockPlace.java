package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.*;
import com.iridium.iridiumskyblock.configs.Missions;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.material.Crops;

public class onBlockPlace implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        try {
            User u = User.getUser(e.getPlayer());
            Island island = IridiumSkyblock.getIslandManager().getIslandViaLocation(e.getBlock().getLocation());
            if (island != null) {
                if (u.islandID == island.getId()) {
                    for (Missions.Mission mission : IridiumSkyblock.getMissions().missions) {
                        if (!island.getMissionLevels().containsKey(mission.name))
                            island.getMissionLevels().put(mission.name, 1);
                        if (mission.levels.containsKey(island.getMissionLevels().get(mission.name))) {
                            if (mission.levels.get(island.getMissionLevels().get(mission.name)).type == MissionType.BLOCK_PLACE) {
                                if (mission.levels.get(island.getMissionLevels().get(mission.name)).conditions.isEmpty() || mission.levels.get(island.getMissionLevels().get(mission.name)).conditions.contains(XMaterial.matchXMaterial(e.getBlock().getType()).name()) || mission.levels.get(island.getMissionLevels().get(mission.name)).conditions.contains(((Crops) e.getBlock().getState().getData()).getState().toString())) {
                                    island.addMission(mission.name, 1);
                                }
                            }
                        }
                    }
                }
                if ((!island.getPermissions((u.islandID == island.getId() || island.isCoop(u.getIsland())) ? (island.isCoop(u.getIsland()) ? Role.Member : u.getRole()) : Role.Visitor).placeBlocks) && !u.bypassing)
                    e.setCancelled(true);
            } else {
                if (e.getBlock().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getWorld()) || e.getBlock().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getNetherWorld())) {
                    if (!u.bypassing) {
                        e.setCancelled(true);
                    }
                }
            }
        } catch (
                Exception ex) {
            IridiumSkyblock.getInstance().sendErrorMessage(ex);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onMonitorBlockPlace(BlockPlaceEvent e) {
        Island island = IridiumSkyblock.getIslandManager().getIslandViaLocation(e.getBlock().getLocation());
        if (island != null) {
            if (Utils.isBlockValuable(e.getBlock())) {
                if (!(e.getBlock().getState() instanceof CreatureSpawner)) {
                    if (!island.valuableBlocks.containsKey(XMaterial.matchXMaterial(e.getBlock().getType()).name())) {
                        island.valuableBlocks.put(XMaterial.matchXMaterial(e.getBlock().getType()).name(), 1);
                    } else {
                        island.valuableBlocks.put(XMaterial.matchXMaterial(e.getBlock().getType()).name(), island.valuableBlocks.get(XMaterial.matchXMaterial(e.getBlock().getType()).name()) + 1);
                    }
                    if (island.updating) {
                        island.tempValues.add(e.getBlock().getLocation());
                    }
                    island.calculateIslandValue();
                }
            }
        }
    }
}

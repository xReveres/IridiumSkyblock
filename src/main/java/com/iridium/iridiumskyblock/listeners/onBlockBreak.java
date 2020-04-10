package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.configs.Missions;
import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.IslandManager;
import com.iridium.iridiumskyblock.MissionType;
import com.iridium.iridiumskyblock.User;
import com.iridium.iridiumskyblock.Utils;
import com.iridium.iridiumskyblock.XMaterial;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.material.Crops;

import java.util.List;
import java.util.Map;

public class onBlockBreak implements Listener {

    @EventHandler
    public void onBreakBlock(BlockBreakEvent event) {
        try {
            if (event.isCancelled()) return;
            final Player player = event.getPlayer();
            final User user = User.getUser(player);
            final boolean userBypassing = user.bypassing;
            final Block block = event.getBlock();
            final Location location = block.getLocation();
            final World world = location.getWorld();
            final IslandManager islandManager = IridiumSkyblock.getIslandManager();
            final Island island = islandManager.getIslandViaLocation(location);

            if (island == null) {
                if (userBypassing) return;
                if (world == null) return;
                final String worldName = world.getName();
                final World islandWorld = islandManager.getWorld();
                if (islandWorld == null) return;
                final World islandNetherWorld = islandManager.getNetherWorld();
                if (islandNetherWorld == null) return;
                if (islandWorld.getName().equals(worldName) || islandNetherWorld.getName().equals(worldName))
                    event.setCancelled(true);
                return;
            }

            if (user.islandID == island.getId()) {
                for (Missions.Mission mission : IridiumSkyblock.getMissions().missions) {
                    final int key = island.getMissionLevels().computeIfAbsent(mission.name, (name) -> 1);
                    final Map<Integer, Missions.MissionData> levels = mission.levels;
                    final Missions.MissionData level = levels.get(key);

                    if (level == null) continue;
                    if (level.type != MissionType.BLOCK_BREAK) continue;

                    final List<String> conditions = level.conditions;

                    if (
                        conditions.isEmpty()
                        ||
                        conditions.contains(XMaterial.matchXMaterial(block.getType()).name())
                        ||
                        (
                            block.getState().getData() instanceof Crops
                            &&
                            conditions.contains(((Crops) block.getState().getData()).getState().toString())
                        )
                    ) island.addMission(mission.name, 1);
                }
            }

            if (!(island.getPermissions(user).breakBlocks || user.bypassing)) event.setCancelled(true);
        } catch (Exception e) {
            IridiumSkyblock.getInstance().sendErrorMessage(e);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onMonitorBreakBlock(BlockBreakEvent event) {
        try {
            final Block block = event.getBlock();
            final Location location = block.getLocation();
            final IslandManager islandManager = IridiumSkyblock.getIslandManager();
            final Island island = islandManager.getIslandViaLocation(location);
            if (island == null) return;

            if (Utils.isBlockValuable(block)) {
                if (!(block.getState() instanceof CreatureSpawner)) {
                    final Material material = block.getType();
                    final String materialName = XMaterial.matchXMaterial(material).name();
                    if (island.valuableBlocks.containsKey(materialName)) {
                        island.valuableBlocks.put(materialName, island.valuableBlocks.get(materialName) - 1);
                    }
                    if (island.updating)
                        island.tempValues.remove(location);
                    island.calculateIslandValue();
                }
            }

            island.failedGenerators.remove(location);
        } catch (Exception e) {
            IridiumSkyblock.getInstance().sendErrorMessage(e);
        }
    }
}

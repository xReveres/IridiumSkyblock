package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.MissionType;
import com.iridium.iridiumskyblock.User;
import com.iridium.iridiumskyblock.configs.Missions;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class onEntityDeath implements Listener {

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        try {
            if (e.getEntity().getKiller() == null) return;
            if (e.getEntity().getKiller().getPlayer() == null) return;
            Island island = User.getUser(e.getEntity().getKiller().getPlayer()).getIsland();
            if (!e.getEntity().getKiller().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getWorld()) && !e.getEntity().getKiller().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getNetherWorld()))
                return;
            if (island != null) {
                for (Missions.Mission mission : IridiumSkyblock.getMissions().missions) {
                    if (!island.getMissionLevels().containsKey(mission.name))
                        island.getMissionLevels().put(mission.name, 1);
                    if (mission.levels.get(island.getMissionLevels().get(mission.name)).type == MissionType.ENTITY_KILL) {
                        if (mission.levels.get(island.getMissionLevels().get(mission.name)).conditions.isEmpty() || mission.levels.get(island.getMissionLevels().get(mission.name)).conditions.contains(e.getEntityType().toString())) {
                            island.addMission(mission.name, 1);
                        }
                    }
                }

                if (island.getExpBooster() != 0) {
                    e.setDroppedExp(e.getDroppedExp() * 2);
                }
            }
        } catch (Exception ex) {
            IridiumSkyblock.getInstance().sendErrorMessage(ex);
        }
    }
}

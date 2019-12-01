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
            if (island != null) {
                for (Missions.Mission mission : IridiumSkyblock.getMissions().missions) {
                    if (mission.type == MissionType.ENTITY_KILL) {
                        if (mission.conditions.isEmpty() || mission.conditions.contains(e.getEntityType().toString())) {
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

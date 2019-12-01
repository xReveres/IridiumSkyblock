package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.MissionType;
import com.iridium.iridiumskyblock.User;
import com.iridium.iridiumskyblock.configs.Missions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class onPlayerExpChange implements Listener {

    @EventHandler
    public void onPlayerExpChange(PlayerExpChangeEvent e) {
        try {
            Player p = e.getPlayer();
            User user = User.getUser(p);
            Island island = user.getIsland();
            if (island != null) {
                for (Missions.Mission mission : IridiumSkyblock.getMissions().missions) {
                    if (mission.type == MissionType.EXPERIENCE) {
                        island.addMission(mission.name, e.getAmount());
                    }
                }
            }
        } catch (Exception ex) {
            IridiumSkyblock.getInstance().sendErrorMessage(ex);
        }
    }
}

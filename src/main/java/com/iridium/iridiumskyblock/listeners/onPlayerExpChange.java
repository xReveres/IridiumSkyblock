package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.MissionType;
import com.iridium.iridiumskyblock.User;
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
                for (String mission : IridiumSkyblock.getMissions().mission.keySet()) {
                    if (!island.missionLevels.containsKey(mission)) island.missionLevels.put(mission, 1);
                    if (IridiumSkyblock.getMissions().mission.get(mission).get(island.missionLevels.get(mission)).type == MissionType.EXPERIENCE) {
                        island.addMission(mission, e.getAmount());
                    }
                }
            }
        } catch (Exception ex) {
            IridiumSkyblock.getInstance().sendErrorMessage(ex);
        }
    }
}

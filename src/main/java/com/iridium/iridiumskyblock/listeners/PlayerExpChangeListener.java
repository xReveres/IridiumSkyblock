package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.MissionType;
import com.iridium.iridiumskyblock.User;
import com.iridium.iridiumskyblock.configs.Missions.Mission;
import com.iridium.iridiumskyblock.configs.Missions.MissionData;
import com.iridium.iridiumskyblock.managers.IslandManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

import java.util.Map;

public class PlayerExpChangeListener implements Listener {

    @EventHandler
    public void onPlayerExpChange(PlayerExpChangeEvent event) {
        final Player player = event.getPlayer();
        final Location location = player.getLocation();
        if (!IslandManager.isIslandWorld(location)) return;

        final User user = User.getUser(player);
        final Island island = user.getIsland();
        if (island == null) return;

        for (Mission mission : IridiumSkyblock.getInstance().getMissions().missions) {
            final Map<String, Integer> levels = island.getMissionLevels();
            levels.putIfAbsent(mission.name, 1);

            final MissionData level = mission.levels.get(levels.get(mission.name));
            if (level.type == MissionType.EXPERIENCE)
                island.addMission(mission.name, event.getAmount());
        }
    }
}

package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.MissionType;
import com.iridium.iridiumskyblock.User;
import com.iridium.iridiumskyblock.configs.Missions;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public class onPlayerFish implements Listener {

    @EventHandler
    public void onPlayerFish(PlayerFishEvent e) {
        try {
            if (e.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
                User u = User.getUser(e.getPlayer());
                Island island = u.getIsland();
                if (!e.getPlayer().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getWorld()) && !e.getPlayer().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getNetherWorld()))
                    return;
                if (island != null) {
                    for (Missions.Mission mission : IridiumSkyblock.getMissions().missions) {
                        if (!island.getMissionLevels().containsKey(mission.name)) island.getMissionLevels().put(mission.name, 1);
                        if(mission.levels.get(island.getMissionLevels().get(mission.name)).type==MissionType.FISH_CATCH){
                            island.addMission(mission.name, 1);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            IridiumSkyblock.getInstance().sendErrorMessage(ex);
        }
    }
}

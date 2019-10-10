package com.peaches.iridiumskyblock.listeners;

import com.peaches.iridiumskyblock.IridiumSkyblock;
import com.peaches.iridiumskyblock.Island;
import com.peaches.iridiumskyblock.MissionRestart;
import com.peaches.iridiumskyblock.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class onEntityDeath implements Listener {

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
    	if (!IridiumSkyblock.getConfiguration().enabledWorlds.contains(e.getEntity().getLocation().getWorld().getName()))
    		return;
        try {
            if (e.getEntity().getKiller() == null) return;
            if (e.getEntity().getKiller().getPlayer() == null) return;
            Island island = User.getUser(e.getEntity().getKiller().getPlayer()).getIsland();
            if (island != null) {
                if (island.hunter > -1) {
                    island.hunter++;
                    if (island.hunter >= IridiumSkyblock.getMissions().hunter.getAmount()) {
                        island.hunter = IridiumSkyblock.getConfiguration().missionRestart == MissionRestart.Instantly ? 0 : -1;
                        island.completeMission("Hunter", IridiumSkyblock.getMissions().hunter.getReward());
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

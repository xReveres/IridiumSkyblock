package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.User;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

public class EntityTargetLivingEntityListener implements Listener {

  @EventHandler
  public void onEntityTargetEntity(EntityTargetLivingEntityEvent event) {
    if (IridiumSkyblock.getConfiguration().allowMobGuestTargeting) {
      return;
    }

    if (event.getTarget() == null) {
      return;
    }

    if (event.getTarget().getType() != EntityType.PLAYER) {
      return;
    }

    Player targetedPlayer = (Player) event.getTarget();
    Island island = IridiumSkyblock.getIslandManager().getIslandViaLocation(targetedPlayer.getLocation());
    if (island == null) {
      return;
    }

    User user = User.getUser(targetedPlayer);
    if (user.islandID != island.getId()) {
      event.setCancelled(true);
    }
  }

}

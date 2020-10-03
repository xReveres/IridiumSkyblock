package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

public class EntityTargetLivingEntityListener implements Listener {

  private final Map<Entity, Long> entityTargetCooldowns = new HashMap<>();

  @EventHandler
  public void onEntityTargetEntity(EntityTargetLivingEntityEvent event) {
    if (IridiumSkyblock.getConfiguration().allowMobGuestTargeting) {
      return;
    }

    if (event.getTarget() == null || !(event.getEntity() instanceof LivingEntity)) {
      return;
    }

    if (event.getTarget().getType() != EntityType.PLAYER) {
      return;
    }

    LivingEntity entity = (LivingEntity) event.getEntity();
    if (isOnTargetCooldown(entity)) {
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
      List<Player> playersOnIsland = island.getPlayersOnIsland();
      if (playersOnIsland.size() != 1) {
        List<Player> possibleTargets = playersOnIsland.stream()
            .filter(player -> player.getGameMode() != GameMode.CREATIVE && player.getGameMode() != GameMode.SPECTATOR)
            .filter(player -> User.getUser(player).islandID == island.getId())
            .filter(entity::hasLineOfSight)
            .collect(Collectors.toList());
        if (possibleTargets.size() == 0) {
          return;
        }
        Player nextTarget = possibleTargets.get(ThreadLocalRandom.current().nextInt(possibleTargets.size()));
        event.setTarget(nextTarget);
      }
    }
  }

  private boolean isOnTargetCooldown(Entity entity) {
    if (!entityTargetCooldowns.containsKey(entity)) {
      entityTargetCooldowns.put(entity, System.currentTimeMillis());
      return false;
    }

    if (System.currentTimeMillis() >= entityTargetCooldowns.get(entity) + IridiumSkyblock.getConfiguration().intervalBetweenMobTarget * 1000L) {
      entityTargetCooldowns.remove(entity);
      return false;
    }

    return true;
  }

}

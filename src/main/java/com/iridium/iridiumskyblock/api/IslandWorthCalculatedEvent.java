package com.iridium.iridiumskyblock.api;

import com.iridium.iridiumskyblock.Island;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

public class IslandWorthCalculatedEvent extends IslandEvent {
  @Getter double islandWorth;

  public IslandWorthCalculatedEvent(@NotNull Island island, double islandWorth) {
    super(island);
    this.islandWorth = islandWorth;
  }
}

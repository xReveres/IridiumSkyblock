package com.iridium.iridiumskyblock.api;

import com.iridium.iridiumskyblock.Island;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class IslandCreateEvent extends Event {
    private static HandlerList handlers = new HandlerList();
    private Player player;
    private Island island;

    public IslandCreateEvent(Player player, Island island) {
        this.player = player;
        this.island = island;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Player getPlayer() {
        return player;
    }

    public Island getIsland() {
        return island;
    }
}

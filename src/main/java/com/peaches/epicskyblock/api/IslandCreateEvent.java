package com.peaches.epicskyblock.api;

import com.peaches.epicskyblock.Island;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class IslandCreateEvent extends Event {
    private HandlerList handlers = new HandlerList();
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

    public Player getPlayer() {
        return player;
    }

    public Island getIsland() {
        return island;
    }
}

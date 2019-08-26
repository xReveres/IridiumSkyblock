package com.peaches.epicskyblock.api;

import com.peaches.epicskyblock.Island;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class IslandDeleteEvent extends Event {
    private HandlerList handlers = new HandlerList();
    private Island island;

    public IslandDeleteEvent(Island island) {
        this.island = island;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public Island getIsland() {
        return island;
    }
}

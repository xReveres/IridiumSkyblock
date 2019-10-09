package com.peaches.iridiumskyblock.api;

import com.peaches.iridiumskyblock.Island;
import com.peaches.iridiumskyblock.Roles;
import com.peaches.iridiumskyblock.User;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class IslandDemoteEvent extends Event {
    private HandlerList handlers = new HandlerList();
    private Island island;
    private User target;
    private User demoter;
    private Roles role;
    private boolean cancel;

    public IslandDemoteEvent(Island island, User target, User demoter, Roles role) {
        this.island = island;
        this.target = target;
        this.demoter = demoter;
        this.role = role;
    }

    public boolean isCancelled() {
        return this.cancel;
    }

    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    public User getTarget() {
        return target;
    }

    public User getDemoter() {
        return demoter;
    }

    public Roles getRole() {
        return role;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public Island getIsland() {
        return island;
    }
}

package com.iridium.iridiumskyblock.api;

import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.Role;
import com.iridium.iridiumskyblock.User;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class IslandPromoteEvent extends Event {
    private static HandlerList handlers = new HandlerList();
    private Island island;
    private User target;
    private User promoter;
    private Role role;
    private boolean cancel;

    public IslandPromoteEvent(Island island, User target, User promoter, Role role) {
        this.island = island;
        this.target = target;
        this.promoter = promoter;
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

    public User getPromoter() {
        return promoter;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Island getIsland() {
        return island;
    }
}

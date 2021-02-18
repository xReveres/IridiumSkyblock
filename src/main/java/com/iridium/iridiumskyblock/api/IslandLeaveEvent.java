package com.iridium.iridiumskyblock.api;

import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.User;
import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class IslandLeaveEvent extends IslandEvent implements Cancellable {
    @NotNull
    private final User user;
    private boolean cancelled = false;

    public IslandLeaveEvent(@NotNull Island island, @Nullable User user) {
        super(island);
        this.user = user;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.cancelled = b;
    }

    public User getUser() {
        return user;
    }
}

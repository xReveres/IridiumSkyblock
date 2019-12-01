package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import me.clip.placeholderapi.events.ExpansionUnregisterEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class onExpansionUnregister implements Listener {

    @EventHandler
    public void onExpansionUnregister(ExpansionUnregisterEvent e) {
        if (e.getExpansion().getIdentifier().equals("iridiumskyblock")) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(IridiumSkyblock.getInstance(), () -> IridiumSkyblock.getInstance().setupClipsPlaceholderAPI());
        }
    }

}

package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.User;
import com.iridium.iridiumskyblock.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class onPlayerTalk implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerTalk(AsyncPlayerChatEvent e) {
        String format = e.getFormat();
        User u = User.getUser(e.getPlayer());
        Island island = u.getIsland();
        if (format.contains(IridiumSkyblock.getConfiguration().chatRankPlaceholder)) {
            if (island != null) {
                format = format.replace(IridiumSkyblock.getConfiguration().chatRankPlaceholder, Utils.getIslandRank(island) + "");
            } else {
                format = format.replace(IridiumSkyblock.getConfiguration().chatRankPlaceholder, "");
            }
        }
        if (format.contains(IridiumSkyblock.getConfiguration().chatNAMEPlaceholder)) {
            if (island != null) {
                format = format.replace(IridiumSkyblock.getConfiguration().chatNAMEPlaceholder, island.getName() + "");
            } else {
                format = format.replace(IridiumSkyblock.getConfiguration().chatNAMEPlaceholder, "");
            }
        }
        if (format.contains(IridiumSkyblock.getConfiguration().chatValuePlaceholder)) {
            if (island != null) {
                format = format.replace(IridiumSkyblock.getConfiguration().chatValuePlaceholder, island.getValue() + "");
            } else {
                format = format.replace(IridiumSkyblock.getConfiguration().chatValuePlaceholder, "");
            }
        }
        e.setFormat(Utils.color(format));
    }
}

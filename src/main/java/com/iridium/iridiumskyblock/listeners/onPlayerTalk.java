package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.User;
import com.iridium.iridiumskyblock.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class onPlayerTalk implements Listener {

    @EventHandler
    public void onPlayerTalk(AsyncPlayerChatEvent e) {
        try {
            Player p = e.getPlayer();
            User u = User.getUser(p);
            if (u.warp != null) {
                if (u.warp.getPassword().equals(e.getMessage())) {
                    Bukkit.getScheduler().runTask(IridiumSkyblock.getInstance(), () -> p.teleport(u.warp.getLocation()));
                    p.sendMessage(Utils.color(IridiumSkyblock.getMessages().teleporting.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                    u.warp = null;
                } else {
                    p.sendMessage(Utils.color(IridiumSkyblock.getMessages().wrongPassword.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                    u.warp = null;
                }
                e.setCancelled(true);
            }
        } catch (Exception ex) {
            IridiumSkyblock.getInstance().sendErrorMessage(ex);
        }
    }
}

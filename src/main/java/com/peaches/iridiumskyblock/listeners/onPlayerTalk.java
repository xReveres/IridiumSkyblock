package com.peaches.iridiumskyblock.listeners;

import com.peaches.iridiumskyblock.IridiumSkyblock;
import com.peaches.iridiumskyblock.User;
import com.peaches.iridiumskyblock.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class onPlayerTalk implements Listener {

    @EventHandler
    public void onPlayerTalk(AsyncPlayerChatEvent e) {
    	if (!IridiumSkyblock.getConfiguration().enabledWorlds.contains(e.getPlayer().getLocation().getWorld().getName()))
    		return;
        try {
            Player p = e.getPlayer();
            User u = User.getUser(p);
            if (u.warp != null) {
                if (u.warp.getPassword().equals(e.getMessage())) {
                    Bukkit.getScheduler().runTask(IridiumSkyblock.getInstance(), () -> p.teleport(u.warp.getLocation()));
                    p.sendMessage(Utils.color(IridiumSkyblock.getMessages().teleporting.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
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

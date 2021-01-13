package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.User;
import com.iridium.iridiumskyblock.Utils;
import com.iridium.iridiumskyblock.managers.IslandManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinLeaveListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final IridiumSkyblock plugin = IridiumSkyblock.getInstance();
        if (player.isOp()) {
            final String latest = plugin.getLatest();
            if (latest != null
                    && IridiumSkyblock.getConfiguration().notifyAvailableUpdate
                    && !latest.equals(plugin.getDescription().getVersion())) {
                final String prefix = IridiumSkyblock.getConfiguration().prefix;
                player.sendMessage(Utils.color(prefix + " &7This message is only seen by opped players."));
                player.sendMessage(Utils.color(prefix + " &7Newer version available: " + latest));
            }
        }
        final Location location = player.getLocation();
        final User user = User.getUser(player);
        user.name = player.getName();
        Island island = user.getIsland();
        if (island != null) {
            if (!user.tookInterestMessage && (island.interestCrystal != 0 || island.interestExp != 0 || island.interestMoney != 0)) {
                player.sendMessage(Utils.color(IridiumSkyblock.getMessages().islandInterest
                        .replace("%exp%", Utils.NumberFormatter.format(island.interestExp))
                        .replace("%crystals%", Utils.NumberFormatter.format(island.interestCrystal))
                        .replace("%money%", Utils.NumberFormatter.format(island.interestMoney))
                        .replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                user.tookInterestMessage = true;
            }
        }

        if (island == null && IridiumSkyblock.getConfiguration().createIslandOnJoin) {
            if (!user.isOnCooldown() || IridiumSkyblock.getConfiguration().ignoreCooldownOnJoinCreation) {
                IslandManager.createIsland(player);
            } else {
                player.sendMessage(Utils.color(user.getCooldownTimeMessage()));
            }
        }

        if (user.flying && (island == null || island.isInIsland(location) || island.getBoosterTime(IridiumSkyblock.getBoosters().islandFlightBooster.name) == 0)) {
            player.setAllowFlight(false);
            player.setFlying(false);
            user.flying = false;
        }
        if (IridiumSkyblock.getConfiguration().disableBypassOnJoin || !player.hasPermission(IridiumSkyblock.getCommands().bypassCommand.permission))
            user.bypassing = false;

        final Island visitingIsland = IslandManager.getIslandViaLocation(location);
        if (visitingIsland == null) return;

        Bukkit.getScheduler().runTaskLater(plugin, () -> visitingIsland.sendBorder(player), 1);
        Bukkit.getScheduler().runTaskLater(plugin, () -> visitingIsland.sendHolograms(player), 1);
    }

}

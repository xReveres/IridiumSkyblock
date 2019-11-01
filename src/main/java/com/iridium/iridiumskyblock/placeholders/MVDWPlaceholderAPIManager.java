package com.iridium.iridiumskyblock.placeholders;

import be.maximvdw.placeholderapi.PlaceholderAPI;
import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.User;
import com.iridium.iridiumskyblock.Utils;
import org.bukkit.entity.Player;

public class MVDWPlaceholderAPIManager {

    public MVDWPlaceholderAPIManager() {
    }

    public void register() {
        PlaceholderAPI.registerPlaceholder(IridiumSkyblock.getInstance(), "iridiumskyblock_island_value", e -> {
            Player player = e.getPlayer();
            if (player == null) {
                return "N/A";
            }
            User user = User.getUser(player);
            return user.getIsland() != null ? user.getIsland().getValue() + "" : "N/A";
        });

        PlaceholderAPI.registerPlaceholder(IridiumSkyblock.getInstance(), "iridiumskyblock_island_rank", e -> {
            Player player = e.getPlayer();
            if (player == null) {
                return "N/A";
            }
            User user = User.getUser(player);
            return user.getIsland() != null ? Utils.getIslandRank(user.getIsland()) + "" : "N/A";
        });


        PlaceholderAPI.registerPlaceholder(IridiumSkyblock.getInstance(), "iridiumskyblock_island_owner", e -> {
            Player player = e.getPlayer();
            if (player == null) {
                return "N/A";
            }
            User user = User.getUser(player);
            return user.getIsland() != null ? User.getUser(user.getIsland().getOwner()).name : "N/A";
        });


        PlaceholderAPI.registerPlaceholder(IridiumSkyblock.getInstance(), "iridiumskyblock_island_crystals", e -> {
            Player player = e.getPlayer();
            if (player == null) {
                return "N/A";
            }
            User user = User.getUser(player);
            return user.getIsland() != null ? user.getIsland().getCrystals() + "" : "N/A";
        });

    }

}
package com.peaches.epicskyblock;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class User {

    public String player;
    public String name;
    public int islandID;
    public Roles role;
    public ArrayList<Integer> invites;
    public Island.Warp warp;
    public boolean bypassing;

    public User(OfflinePlayer p) {
        invites = new ArrayList<>();
        this.player = p.getUniqueId().toString();
        this.name = p.getName();
        this.islandID = 0;
        bypassing = false;
        EpicSkyblock.getIslandManager().users.put(this.player, this);
    }

    public Island getIsland() {
        return EpicSkyblock.getIslandManager().islands.getOrDefault(islandID, null);
    }

    public static User getUser(String p) {
        return EpicSkyblock.getIslandManager().users.get(p);
    }

    public static User getUser(OfflinePlayer p) {
        return EpicSkyblock.getIslandManager().users.getOrDefault(p.getUniqueId().toString(), new User(p));
    }
}

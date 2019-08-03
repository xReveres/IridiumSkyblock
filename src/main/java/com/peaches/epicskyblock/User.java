package com.peaches.epicskyblock;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class User {

    public String player;
    public int islandID;
    public Roles role;
    public ArrayList<Integer> invites;
    public Island.Warp warp;
    public boolean bypassing;

    public User(String player) {
        invites = new ArrayList<>();
        this.player = player;
        this.islandID = 0;
        bypassing = false;
        EpicSkyblock.getIslandManager().users.put(this.player, this);
    }

    public Island getIsland() {
        return EpicSkyblock.getIslandManager().islands.getOrDefault(islandID, null);
    }

    public static User getUser(String p) {
        return EpicSkyblock.getIslandManager().users.containsKey(p) ? EpicSkyblock.getIslandManager().users.get(p) : new User(p);
    }

    public static User getUser(Player p) {
        return getUser(p.getUniqueId().toString());
    }

}

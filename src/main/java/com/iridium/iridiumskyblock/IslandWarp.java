package com.iridium.iridiumskyblock;

import org.bukkit.Location;

public class IslandWarp {
    Location location;
    String name;
    String password;

    public IslandWarp(Location location, String name, String password) {
        this.location = location;
        this.name = name;
        this.password = password;
    }

    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}

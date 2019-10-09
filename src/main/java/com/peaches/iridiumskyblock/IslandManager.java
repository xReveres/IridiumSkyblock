package com.peaches.iridiumskyblock;

import org.bukkit.*;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class IslandManager {

    public HashMap<Integer, Island> islands = new HashMap<>();
    public HashMap<String, User> users = new HashMap<>();

    int length = 1;
    int current = 0;

    public Direction direction = Direction.NORTH;
    public String worldName = "EpicSkyblock";
    public Location nextLocation;

    public int nextID = 1;

    public IslandManager() {
        makeWorld();
        nextLocation = new Location(getWorld(), 0, 0, 0);
    }

    public World getWorld() {
        return Bukkit.getWorld(worldName);
    }

    public Island createIsland(Player player) {
        Location pos1 = nextLocation.clone().subtract(IridiumSkyblock.getUpgrades().size.get(1).getSize() / 2, 0, IridiumSkyblock.getUpgrades().size.get(1).getSize() / 2);
        Location pos2 = nextLocation.clone().add(IridiumSkyblock.getUpgrades().size.get(1).getSize() / 2, 0, IridiumSkyblock.getUpgrades().size.get(1).getSize() / 2);
        Location center = nextLocation.clone().add(0, 100, 0);
        Location home = nextLocation.clone().add(0.5, 97, -1.5);
        Island island = new Island(player, pos1, pos2, center, home, nextID);
        islands.put(nextID, island);

        User.getUser(player).islandID = nextID;
        User.getUser(player).role = Roles.Owner;

        island.generateIsland();
        island.teleportHome(player);

        NMSUtils.sendTitle(player, "&b&lIsland Created", 20, 40, 20);

        switch (direction) {
            case NORTH:
                nextLocation.add(IridiumSkyblock.getConfiguration().distance, 0, 0);
                break;
            case EAST:
                nextLocation.add(0, 0, IridiumSkyblock.getConfiguration().distance);
                break;
            case SOUTH:
                nextLocation.subtract(IridiumSkyblock.getConfiguration().distance, 0, 0);
                break;
            case WEST:
                nextLocation.subtract(0, 0, IridiumSkyblock.getConfiguration().distance);
                break;
        }

        current++;

        if (current == length) {
            current = 0;
            direction = direction.next();
            if (direction == Direction.SOUTH || direction == Direction.NORTH) {
                length++;
            }
        }

        IridiumSkyblock.getInstance().saveConfigs();

        nextID++;

        return island;
    }

    private void makeWorld() {
        WorldCreator wc = new WorldCreator(worldName);
        wc.generateStructures(false);
        wc.generator(new SkyblockGenerator());
        wc.createWorld();
    }

    public void pasteSchematic(Location loc) {
        File schematicFolder = new File(IridiumSkyblock.getInstance().getDataFolder(), "schematics");
        File schematicFile = new File(schematicFolder, "island.schematic");
        try {
            Schematic.loadSchematic(schematicFile).pasteSchematic(loc);
        } catch (IOException e) {
            IridiumSkyblock.getInstance().sendErrorMessage(e);
        }
    }

    public Island getIslandViaLocation(Location loc) {
        if (loc.getWorld().equals(getWorld())) {
            for (Island island : islands.values()) {
                if (island.isInIsland(loc)) {
                    return island;
                }
            }
        }
        return null;
    }

    public Island getIslandViaId(int i) {
        return islands.get(i);
    }
}

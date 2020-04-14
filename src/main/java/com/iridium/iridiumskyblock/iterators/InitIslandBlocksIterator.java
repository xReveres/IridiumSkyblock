package com.iridium.iridiumskyblock.iterators;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.IslandManager;
import com.iridium.iridiumskyblock.Utils;
import com.iridium.iridiumskyblock.configs.Config;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class InitIslandBlocksIterator implements Iterator<Long> {
    private final Config config = IridiumSkyblock.getConfiguration();
    private final IslandManager islandManager = IridiumSkyblock.getIslandManager();
    private final IridiumSkyblock plugin = IridiumSkyblock.getInstance();

    private final Island island;

    private final double islandMinX;
    private final double islandMinZ;
    private final double islandMaxX;
    private final double islandMaxZ;

    private World currentWorld = islandManager.getWorld();
    private double currentX;
    private double currentY;
    private double currentZ;

    private final double maxWorldHeight = currentWorld.getMaxHeight();

    private long currentBlock = 0;

    public InitIslandBlocksIterator(Island island) {
        this.island = island;

        final Location pos1 = island.getPos1();
        islandMinX = pos1.getX();
        islandMinZ = pos1.getZ();

        final Location pos2 = island.getPos2();
        islandMaxX = pos2.getX();
        islandMaxZ = pos2.getZ();

        currentX = islandMinX;
        currentY = 0;
        currentZ = islandMinZ;
    }

    @Override
    public boolean hasNext() {
        return currentX < islandMaxX
                || currentZ < islandMaxZ
                || currentY < maxWorldHeight
                || (config.netherIslands && currentWorld.getName().equals(config.worldName));
    }

    @Override
    public Long next() {
        if (currentX < islandMaxX) {
            currentX++;
        } else if (currentZ < islandMaxZ) {
            currentX = islandMinX;
            currentZ++;
        } else if (currentY < maxWorldHeight) {
            currentX = islandMinX;
            currentZ = islandMinZ;
            currentY++;
        } else if (config.netherIslands && currentWorld.getName().equals(config.worldName)) {
            currentWorld = islandManager.getNetherWorld();
            currentX = islandMinX;
            currentY = 0;
            currentZ = islandMinZ;
        } else {
            throw new NoSuchElementException();
        }

        if (plugin.updatingBlocks) {
            final Location location = new Location(currentWorld, currentX, currentY, currentZ);
            final Block block = location.getBlock();
            if (Utils.isBlockValuable(block) && !(block.getState() instanceof CreatureSpawner))
                island.tempValues.add(location);
        }

        return currentBlock++;
    }
}

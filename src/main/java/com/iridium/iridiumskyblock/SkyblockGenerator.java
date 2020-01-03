package com.iridium.iridiumskyblock;

import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class SkyblockGenerator extends ChunkGenerator {

    @Override
    public ChunkData generateChunkData(World world, Random random, int cx, int cz, BiomeGrid biome) {
        ChunkData chunkData = createChunkData(world);
        for (int x = 0; x <= 15; x++) {
            for (int z = 0; z <= 15; z++) {
                if (world.getName().equals("IridiumSkyblock_nether")) {
                    biome.setBiome(x, z, IridiumSkyblock.getConfiguration().netherBiome);
                } else {
                    biome.setBiome(x, z, IridiumSkyblock.getConfiguration().defaultBiome);
                }
            }
        }

        return chunkData;
    }

    @Override
    public byte[][] generateBlockSections(World world, Random random, int x, int z, BiomeGrid biomes) {
        return new byte[world.getMaxHeight() / 16][];
    }

    @Override
    public boolean canSpawn(World world, int x, int z) {
        return true;
    }

    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        return new ArrayList<>();
    }
}
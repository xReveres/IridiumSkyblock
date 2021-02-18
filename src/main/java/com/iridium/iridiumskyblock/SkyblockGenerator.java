package com.iridium.iridiumskyblock;

import com.iridium.iridiumskyblock.configs.Config;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SkyblockGenerator extends ChunkGenerator {

    public byte[][] blockSections;

    @Override
    public @NotNull ChunkData generateChunkData(@NotNull World world, @NotNull Random random, int cx, int cz, @NotNull BiomeGrid biomeGrid) {
        final ChunkData chunkData = createChunkData(world);

        Biome biome;
        final Config config = IridiumSkyblock.getInstance().getConfiguration();
        final String worldName = world.getName();
        if (worldName.equals(config.worldName))
            biome = config.defaultBiome.getBiome();
        else if (worldName.equals(config.netherWorldName))
            biome = config.defaultNetherBiome.getBiome();
        else
            return chunkData;
        for (int x = 0; x <= 15; x++) {
            for (int z = 0; z <= 15; z++) {
                biomeGrid.setBiome(x, z, biome);
            }
        }

        return chunkData;
    }

    public byte[][] generateBlockSections(World world, Random random, int x, int z, BiomeGrid biomes) {
        if (blockSections == null) {
            blockSections = new byte[world.getMaxHeight() / 16][];
        }
        return blockSections;
    }

    @Override
    public boolean canSpawn(@NotNull World world, int x, int z) {
        return true;
    }

    @Override
    public @NotNull List<BlockPopulator> getDefaultPopulators(@NotNull World world) {
        return Collections.emptyList();
    }
}
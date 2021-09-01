package com.kernelprogrammer.bukkit.ExtraRecipe;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

public class Mars extends ChunkGenerator {

	int currentHeight = 50;

    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
		SimplexOctaveGenerator generator = new SimplexOctaveGenerator(new Random(world.getSeed()), 8);
        ChunkData chunk = createChunkData(world);
        generator.setScale(0.005D);

        for (int X = 0; X < 16; X++)
            for (int Z = 0; Z < 16; Z++) {
                currentHeight = (int) (generator.noise(chunkX*16+X, chunkZ*16+Z, 0.5D, 0.5D)*15D+50D);
                chunk.setBlock(X, currentHeight, Z, Material.RED_SANDSTONE);
                chunk.setBlock(X, currentHeight-1, Z, Material.RED_CONCRETE);
                for (int i = currentHeight-2; i > 0; i--)
                    chunk.setBlock(X, i, Z, Material.ANDESITE);
                chunk.setBlock(X, 0, Z, Material.BEDROCK);
            }
        return chunk;
    }

	@Override
    public boolean canSpawn(World world, int x, int z) {
        return true;
    }

	@Override
    public Location getFixedSpawnLocation(World world, Random random) {
        return new Location(world, 0, 128, 0);
    }

}
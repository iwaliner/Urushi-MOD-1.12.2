package urushi.WorldGen;

import com.google.common.collect.Lists;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.ChunkGeneratorSettings;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerBiome;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static net.minecraft.world.gen.layer.GenLayer.initializeAllBiomeGenerators;

public class BiomeProviderKakuriyo extends BiomeProvider
{
    private final KakuriyoBiomeCache mapCache;
    public BiomeProviderKakuriyo(World world) {
        getBiomesToSpawnIn().clear();
        getBiomesToSpawnIn().add(Biome.getBiome(0));
        getBiomesToSpawnIn().add( Biome.getBiome(1));
        getBiomesToSpawnIn().add( Biome.getBiome(3));
        getBiomesToSpawnIn().add( Biome.getBiome(7));
        getBiomesToSpawnIn().add( Biome.getBiome(16));
        getBiomesToSpawnIn().add( Biome.getBiome(18));
        getBiomesToSpawnIn().add( Biome.getBiome(24));
        getBiomesToSpawnIn().add( Biome.getBiome(28));


     //   makeLayers(world.getSeed());
        mapCache = new KakuriyoBiomeCache(this, 512, true);
    }


    @Override
    public Biome[] getBiomesForGeneration(Biome[] biomes, int x, int z, int width, int height) {
        return getBiomesForGeneration(biomes, x, z, width, height, true);
    }

    public Biome[] getBiomesForGeneration(Biome[] biomes, int x, int z, int width, int height, boolean useCache) {
        // for grid-centred magic maps, get from map cache
        if (useCache && mapCache.isGridAligned(x, z, width, height)) {
            Biome[] cached = mapCache.getBiomes(x, z);
            return Arrays.copyOf(cached, cached.length);
        }
        return super.getBiomesForGeneration(biomes, x, z, width, height);
    }

    @Override
    public void cleanupCache() {
        mapCache.cleanup();
        super.cleanupCache();
    }
}

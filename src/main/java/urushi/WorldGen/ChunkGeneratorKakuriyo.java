package urushi.WorldGen;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.structure.*;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ChunkGeneratorKakuriyo implements IChunkGenerator {
    private World world;



    public ChunkGeneratorKakuriyo(World worldIn) {
        world = worldIn;

    }
    public void genSurface(Chunk chunk) {
        for (int x = 0; x < 16; x++)
            for (int z = 0; z < 16; z++)
                chunk.setBlockState(new BlockPos(x, 0, z), Blocks.BEDROCK.getDefaultState());
    }

    @Override
    public Chunk generateChunk(int x, int z) {
        ChunkPrimer chunkPrimer = new ChunkPrimer();

        Chunk chunk = new Chunk(world, chunkPrimer, x, z);
        genSurface(chunk);

        Biome[] abiome = world.getBiomeProvider().getBiomes(null, x * 16, z * 16, 16, 16);
        byte[] abyte = chunk.getBiomeArray();

        for (int i = 0; i < abiome.length; i++)
            abyte[i] = (byte) Biome.getIdForBiome(abiome[i]);

        chunk.resetRelightChecks();
        return chunk;
    }

    @Override
    public void populate(int x, int z) {

    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z) {
        return false;
    }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        return world.getBiome(pos).getSpawnableList(creatureType);
    }

    @Nullable
    @Override
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
        return null;
    }

    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z) {

    }

    @Override
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
        return false;
    }

}

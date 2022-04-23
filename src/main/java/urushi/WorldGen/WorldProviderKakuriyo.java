package urushi.WorldGen;

import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.gen.ChunkGeneratorHell;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import urushi.ModCore_Urushi;

public class WorldProviderKakuriyo extends WorldProvider {
    public WorldProviderKakuriyo(){

    }
    public void init()
    {
        this.biomeProvider = new BiomeProviderSingle(Biomes.FOREST);
        this.doesWaterVaporize = true;
        this.nether = true;
    }
 /* @SideOnly(Side.CLIENT)
    public Vec3d getFogColor(float p_76562_1_, float p_76562_2_)
    {
        return new Vec3d(0.5D, 0.029999999329447746D, 0.029999999329447746D);
    }*/
 @Override
 public String getSaveFolder() {
     return "TEST";
 }

    @Override
    public DimensionType getDimensionType() {
        return ModCore_Urushi.Kakuriyo_DIMENSION;
    }

    public IChunkGenerator createChunkGenerator()
    {
        return new ChunkGeneratorHell(this.world, true, 2);
    }
  /* public IChunkGenerator createChunkGenerator()
   {
      return new ChunkGeneratorKakuriyo(world);
   }*/

    public boolean canRespawnHere()
    {
        return false;
    }

    public boolean isSurfaceWorld()
    {
        return false;
    }

    @Override
    public BlockPos getSpawnPoint() {
        return BlockPos.ORIGIN;
    }
    public BlockPos getRandomizedSpawnPoint() {
        return this.getSpawnPoint();
    }

    public boolean shouldMapSpin(String entity, double x, double y, double z) {
        return false;
    }

    public boolean shouldClientCheckLighting() {
        return false;
    }

    public boolean canCoordinateBeSpawn(int x, int z)
    {
        return false;
    }

    public float calculateCelestialAngle(long worldTime, float partialTicks)
    {
        return 0.5F;
    }



    @SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(int x, int z)
    {
        return true;
    }

    public WorldBorder createWorldBorder()
    {
        return new WorldBorder()
        {
            public double getCenterX()
            {
                return super.getCenterX() / 8.0D;
            }
            public double getCenterZ()
            {
                return super.getCenterZ() / 8.0D;
            }
        };
    }
    @SideOnly(Side.CLIENT)
    public Vec3d getFogColor(float p_76562_1_, float p_76562_2_)
    {
        return new Vec3d(0.20000000298023224D, 0.029999999329447746D, 0.029999999329447746D);
    }

    protected void generateLightBrightnessTable()
    {
        float f = 0.1F;

        for (int i = 0; i <= 15; ++i)
        {
            float f1 = 1.0F - (float)i / 15.0F;
            this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * 0.9F + 0.1F;
        }
    }
}

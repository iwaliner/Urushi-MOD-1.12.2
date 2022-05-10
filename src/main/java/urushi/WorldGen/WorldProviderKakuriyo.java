package urushi.WorldGen;

import net.minecraft.client.audio.MusicTicker;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeForest;
import net.minecraft.world.biome.BiomePlains;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.gen.ChunkGeneratorHell;
import net.minecraft.world.gen.ChunkGeneratorOverworld;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import urushi.ModCore_Urushi;


public class WorldProviderKakuriyo extends WorldProvider {
   // private static final String SEED_KEY = "CustomSeed";
    //private long seed;
    public WorldProviderKakuriyo(){
       // setDimension(183);
    }
    public void init()
    {
        this.biomeProvider = new BiomeProviderSingle(ModCore_Urushi.SakuraBiome);
       // this.biomeProvider = new BiomeProviderKakuriyo(this.world);
        this.doesWaterVaporize = false;
        this.nether = false;
this.hasSkyLight=true;



    }

 /* @SideOnly(Side.CLIENT)
    public Vec3d getFogColor(float p_76562_1_, float p_76562_2_)
    {
        return new Vec3d(0.5D, 0.029999999329447746D, 0.029999999329447746D);
    }*/
 //@Override
 //public String getSaveFolder() {
 //    return "TEST";
 //}

    @Override
    public DimensionType getDimensionType() {
        return ModCore_Urushi.Kakuriyo_DIMENSION;
    }

    public IChunkGenerator createChunkGenerator()
    {
        return new ChunkGeneratorOverworld(this.world, this.world.getSeed(), false,"kakuriyo");
    }
  /* public IChunkGenerator createChunkGenerator()
   {
      return new ChunkGeneratorKakuriyo(world);
   }*/
/*
    public boolean canRespawnHere()
    {
        return false;
    }
*/
    public boolean isSurfaceWorld()
    {
        return true;
    }
/*
    @Override
    public BlockPos getSpawnPoint() {
        return BlockPos.ORIGIN;
    }
    public BlockPos getRandomizedSpawnPoint() {
        return this.getSpawnPoint();
    }
    */
/*
    public boolean shouldMapSpin(String entity, double x, double y, double z) {
        return false;
    }
*/
   /* public boolean shouldClientCheckLighting() {
        return false;
    }*/

    public boolean canCoordinateBeSpawn(int x, int z)
    {
        return false;
    }
/*
    public float calculateCelestialAngle(long worldTime, float partialTicks)
    {
        return 0.5F;
    }

*/

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


    /*  @SideOnly(Side.CLIENT)
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
    }*/


















/*

    @Nullable
    @Override
    @SideOnly(Side.CLIENT)
    public MusicTicker.MusicType getMusicType() {
        return MusicTicker.MusicType.CREATIVE;
    }
*/
  /*  @Override
    public float[] calcSunriseSunsetColors(float celestialAngle, float f1) {
        return null;
    }*/

    @Override
    public Vec3d getFogColor(float f, float f1) {
        float bright = MathHelper.cos(0.25f * 3.141593F * 2.0F) * 2.0F + 0.5F;
        if (bright < 0.0F) {
            bright = 0.0F;
        }
        if (bright > 1.0F) {
            bright = 1.0F;
        }

        float red = 0.0039215686F*210; // 210
        float green = 1F; // 255
        float blue = 1F; // 255
        red *= bright * 0.94F + 0.06F;
        green *= bright * 0.94F + 0.06F;
        blue *= bright * 0.91F + 0.09F;
        return new Vec3d(red, green, blue);
    }

    // Pin the celestial angle at night/evening so things that use it see night
  /*  @Override
    public float calculateCelestialAngle(long worldTime, float partialTicks) {
        return 0.225f;
    }
*/

    /*@Override
    protected void generateLightBrightnessTable() {
        float f = this.hasSkyLight ? 0.0F : 0.1F;
        for (int i = 0; i <= 15; ++i) {
            float f1 = 1.0F - (float)i / 15.0F;
            this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
        }
    }*/



    /**
     * This seems to be a function checking whether we have an ocean.
     */
    @SideOnly(Side.CLIENT)
    @Override
    public boolean isSkyColored() {
        return true;
    }

   /* @Override
    public int getAverageGroundLevel() {
        return 30;
    }*/

  /*  @Override
    public double getVoidFogYFactor() {
        // allow for terrain squashing
        return super.getVoidFogYFactor() * 2.0;
    }*/

    @Override
    public boolean canRespawnHere() {
        // lie about this until the world is initialized
        // otherwise the server will try to generate enough terrain for a spawn point and that's annoying
        return true;
    }



  /*  @Override
    public boolean isDaytime() {
        return false;
    }*/
/*
    @Override
    public boolean shouldMapSpin(String entityName, double x, double z, double rotation) {
        return false;
    }
*/
    @Override
    @SideOnly(Side.CLIENT)
    public Vec3d getSkyColor(Entity cameraEntity, float partialTicks) {
        // TODO Maybe in the future we can get the return of sky color by biome?
        return new Vec3d(32 / 256.0, 34 / 256.0, 74 / 256.0);
    }

  /*  @Override
    public void getLightmapColors(float partialTicks, float sunBrightness, float skyLight, float blockLight, float[] colors) {
        final float r = 64f / 255f, g = 85f / 255f, b = 72f / 255f;
        if (!hasSkyLight) {
            colors[0] = r + blockLight * (1.0f - r);
            colors[1] = g + blockLight * (1.0f - g);
            colors[2] = b + blockLight * (1.0f - b);
        }
    }*/

    @Override
    @SideOnly(Side.CLIENT)
    public float getStarBrightness(float partialTicks) {
        return 2.0F;
    }

/*    @Override
    public double getHorizon() {
        return 1D;
    }*/

  /*  @Override
    public Biome getBiomeForCoords(BlockPos pos) {
        Biome biome = super.getBiomeForCoords(pos);
        if (biome == null) {
            biome = BiomeForest.getBiome(1);
        }
        return biome;
    }*/

    /**
     * If there is a specific twilight forest seed set, use that.  Otherwise use the world seed.
     */
  //  @Override
 ///   public long getSeed() {
   //     return seed == 0L ? super.getSeed() : seed;
   // }

  /*  private long loadSeed() {
        String seed = "testSeed";
        if (seed != null && !seed.isEmpty()) {
            try {
                return Long.parseLong(seed);
            } catch (NumberFormatException e) {
                return seed.hashCode();
            }
        }
        return 0L;
    }

    @Override
    public void onWorldSave() {
        NBTTagCompound data = new NBTTagCompound();
        //data.setLong(SEED_KEY, seed);
        // TODO: decide on persisting this
        //data.setBoolean(SKYLIGHT_KEY, hasSkyLight);

    }

    @Override
    @SideOnly(Side.CLIENT)
    public IRenderHandler getSkyRenderer() {
        if (super.getSkyRenderer() == null) {
           // this.setSkyRenderer(new TFSkyRenderer());
        }
        return super.getSkyRenderer();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IRenderHandler getWeatherRenderer() {
        if (super.getWeatherRenderer() == null) {
            //this.setWeatherRenderer(new Weath());
        }
        return super.getWeatherRenderer();
    }*/

    // no sideonly
    @Override
    public float getCloudHeight() {
        return  120F;
    }


    /**ディメンション移動の他、ワールド入りなおした時も呼ばれる*/
 /*   @Override
    public void onPlayerAdded(EntityPlayerMP player) {
        BlockPos pos=new BlockPos(Math.floor(player.posX),Math.floor(player.posY),Math.floor(player.posZ));
      world.setBlockState(pos, Blocks.GLASS.getDefaultState());
        world.setBlockState(pos.add(0,1,0), Blocks.AIR.getDefaultState());
        world.setBlockState(pos.add(1,1,0), Blocks.AIR.getDefaultState());
        world.setBlockState(pos.add(-1,1,0), Blocks.AIR.getDefaultState());
        world.setBlockState(pos.add(0,1,1), Blocks.AIR.getDefaultState());
        world.setBlockState(pos.add(0,1,-1), Blocks.AIR.getDefaultState());
        world.setBlockState(pos.add(1,1,1), Blocks.AIR.getDefaultState());
        world.setBlockState(pos.add(1,1,-1), Blocks.AIR.getDefaultState());
        world.setBlockState(pos.add(-1,1,1), Blocks.AIR.getDefaultState());
        world.setBlockState(pos.add(-1,1,-1), Blocks.AIR.getDefaultState());





    }*/

}

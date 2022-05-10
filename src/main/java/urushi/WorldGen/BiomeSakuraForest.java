package urushi.WorldGen;

import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeForest;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenBirchTree;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import urushi.Entity.EntityOni;
import urushi.ModCore_Urushi;

import java.util.Random;

public class BiomeSakuraForest extends Biome
{
    protected static final WorldGenLargeSakuraTree LARGE_SAKURA_TREE = new WorldGenLargeSakuraTree(false);


    public BiomeSakuraForest()
    {
        super(new BiomeProperties("Sakura").setBaseHeight(0.125F).setHeightVariation(0.5F).setTemperature(0.8F));
     //   this.decorator.treesPerChunk = 10;
     //   this.decorator.grassPerChunk = 2;
       // topBlock= Blocks.GRASS.getDefaultState();
       // fillerBlock=Blocks.DIRT.getDefaultState();
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.add(new SpawnListEntry(EntityOni.class,60,1,3));

        this.decorator.treesPerChunk = 3;
        this.decorator.grassPerChunk = 1;
        this.spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 40, 1, 5));
        this.decorator.flowersPerChunk = 0;



           /* this.flowers.clear();
            for (BlockFlower.EnumFlowerType type : BlockFlower.EnumFlowerType.values())
            {
                if (type.getBlockType() == BlockFlower.EnumFlowerColor.YELLOW) continue;
                if (type == BlockFlower.EnumFlowerType.BLUE_ORCHID) type = BlockFlower.EnumFlowerType.POPPY;
                addFlower(net.minecraft.init.Blocks.RED_FLOWER.getDefaultState().withProperty(net.minecraft.init.Blocks.RED_FLOWER.getTypeProperty(), type), 10);
            }*/

    }

    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {

            return LARGE_SAKURA_TREE;

    }

   /* @Override
    public void addDefaultFlowers() {
        this.addFlower(ModCore_Urushi.LycorisRadiata.getStateFromMeta(0),10);
        this.addFlower(ModCore_Urushi.LycorisRadiata.getStateFromMeta(1),10);
    }*/
    public BlockFlower.EnumFlowerType pickRandomFlower(Random rand, BlockPos pos)
    {
        return BlockFlower.EnumFlowerType.HOUSTONIA;
    }
    /**草ブロックに骨粉を使って出る花*/
    public void addFlower(IBlockState state, int weight)
    {
        this.flowers.add(new FlowerEntry(ModCore_Urushi.LycorisRadiata.getStateFromMeta(0), 30));
        this.flowers.add(new FlowerEntry(ModCore_Urushi.LycorisRadiata.getStateFromMeta(1), 10));
    }

    public void plantFlower(World world, Random rand, BlockPos pos)
    {
        if (flowers.isEmpty()) return;
        FlowerEntry flower = (FlowerEntry) WeightedRandom.getRandomItem(rand, flowers);
        if (flower == null || flower.state == null ||
                (flower.state.getBlock() instanceof net.minecraft.block.BlockBush &&
                        !((net.minecraft.block.BlockBush)flower.state.getBlock()).canBlockStay(world, pos, flower.state)))
        {
            return;
        }
        if(flower.state.getBlock()!=ModCore_Urushi.LycorisRadiata){
            return;
        }

        world.setBlockState(pos, flower.state, 3);
    }
/*  public BlockFlower.EnumFlowerType pickRandomFlower(Random rand, BlockPos pos)
    {

            double d0 = MathHelper.clamp((1.0D + GRASS_COLOR_NOISE.getValue((double)pos.getX() / 48.0D, (double)pos.getZ() / 48.0D)) / 2.0D, 0.0D, 0.9999D);
            BlockFlower.EnumFlowerType blockflower$enumflowertype = BlockFlower.EnumFlowerType.values()[(int)(d0 * (double)BlockFlower.EnumFlowerType.values().length)];
            return blockflower$enumflowertype == BlockFlower.EnumFlowerType.BLUE_ORCHID ? BlockFlower.EnumFlowerType.POPPY : blockflower$enumflowertype;

    }*/

   /* public void decorate(World worldIn, Random rand, BlockPos pos)
    {

        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS))
        { // no tab for patch
        int i = rand.nextInt(5) - 3;


            i += 2;


        this.addDoublePlants(worldIn, rand, pos, i);
        }
        super.decorate(worldIn, rand, pos);
    }

    public void addMushrooms(World p_185379_1_, Random p_185379_2_, BlockPos p_185379_3_)
    {

        for (int i = 0; i < 4; ++i)
        {
            for (int j = 0; j < 4; ++j)
            {
                int k = i * 4 + 1 + 8 + p_185379_2_.nextInt(3);
                int l = j * 4 + 1 + 8 + p_185379_2_.nextInt(3);
                BlockPos blockpos = p_185379_1_.getHeight(p_185379_3_.add(k, 0, l));

                if (p_185379_2_.nextInt(20) == 0 && net.minecraftforge.event.terraingen.TerrainGen.decorate(p_185379_1_, p_185379_2_, new net.minecraft.util.math.ChunkPos(p_185379_3_), blockpos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.BIG_SHROOM))
                {
                    WorldGenBigMushroom worldgenbigmushroom = new WorldGenBigMushroom();
                    worldgenbigmushroom.generate(p_185379_1_, p_185379_2_, blockpos);
                }
                else if (net.minecraftforge.event.terraingen.TerrainGen.decorate(p_185379_1_, p_185379_2_, new net.minecraft.util.math.ChunkPos(p_185379_3_), blockpos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE))
                {
                    WorldGenAbstractTree worldgenabstracttree = this.getRandomTreeFeature(p_185379_2_);
                    worldgenabstracttree.setDecorationDefaults();

                    if (worldgenabstracttree.generate(p_185379_1_, p_185379_2_, blockpos))
                    {
                        worldgenabstracttree.generateSaplings(p_185379_1_, p_185379_2_, blockpos);
                    }
                }
            }
        }
    }

    public void addDoublePlants(World p_185378_1_, Random p_185378_2_, BlockPos p_185378_3_, int p_185378_4_)
    {
        for (int i = 0; i < p_185378_4_; ++i)
        {
            int j = p_185378_2_.nextInt(3);

            if (j == 0)
            {
                DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.SYRINGA);
            }
            else if (j == 1)
            {
                DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.ROSE);
            }
            else if (j == 2)
            {
                DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.PAEONIA);
            }

            for (int k = 0; k < 5; ++k)
            {
                int l = p_185378_2_.nextInt(16) + 8;
                int i1 = p_185378_2_.nextInt(16) + 8;
                int j1 = p_185378_2_.nextInt(p_185378_1_.getHeight(p_185378_3_.add(l, 0, i1)).getY() + 32);

                if (DOUBLE_PLANT_GENERATOR.generate(p_185378_1_, p_185378_2_, new BlockPos(p_185378_3_.getX() + l, j1, p_185378_3_.getZ() + i1)))
                {
                    break;
                }
            }
        }
    }

    public Class <? extends Biome > getBiomeClass()
    {
        return BiomeSakuraForest.class;
    }

    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos)
    {
        int i = super.getGrassColorAtPos(pos);
        return  i;
    }*/




}

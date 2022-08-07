package urushi.WorldGen;


import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import urushi.Block.U_Stone;
import urushi.ModCore_Urushi;


import java.util.Random;

public class OreGen implements IWorldGenerator{
	private final WorldGenerator genSakuraTrees=new WorldGenSakuraTrees(false);
	private final WorldGenerator genUmeTrees=new WorldGenJapaneseApricotTrees(false);
	private final WorldGenerator genLacquerTrees=new WorldGenLacquerTrees(false);
	private final WorldGenerator genBamboo=new WorldGenBamboo();
	private final WorldGenerator genCypressTrees=new WorldGenCypressTrees(false);
	private final WorldGenerator genLycoris=new WorldGenLycoris();
	private final WorldGenerator genIndigo=new WorldGenIndigo();
	private final WorldGenerator genGateShrine=new WorldGenTeleportShrine();

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {

if(ModCore_Urushi.wheather_ganerate_CopperOre){  runGenCopper(random,chunkX,chunkZ,world,chunkGenerator,chunkProvider);}
	if(ModCore_Urushi.wheather_ganerate_Sakura){	generate(genSakuraTrees,world,random,chunkX,chunkZ,8,0,240,4);}
		if(ModCore_Urushi.wheather_ganerate_Ume){	generate(genUmeTrees,world,random,chunkX,chunkZ,8,0,240,4);}
		if(ModCore_Urushi.wheather_ganerate_Urushi){	generate(genLacquerTrees,world,random,chunkX,chunkZ,8,0,240,4);}
		if(ModCore_Urushi.wheather_ganerate_Bamboo){	generate(genBamboo,world,random,chunkX,chunkZ,2,0,240,18);}
		if(ModCore_Urushi.wheather_ganerate_Cypress){	generate(genCypressTrees,world,random,chunkX,chunkZ,8,0,240,3);}
		generateInKakuriyo(genLycoris,world,random,chunkX,chunkZ,40,0,240);
		generateInKakuriyo(genIndigo,world,random,chunkX,chunkZ,20,0,240);
if(ModCore_Urushi.wheather_ganerate_Gate) {
	generate(genGateShrine, world, random, chunkX, chunkZ, 1, 64, 110, 18);
}
	}
	private void runGenCopper(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		generateCopperOre(ModCore_Urushi.UStone.getDefaultState().withProperty(U_Stone.VARIANT, U_Stone.EnumType.CopperOre),world,random,chunkX*16,chunkZ*16,0,80,random.nextInt(7)+10,20);

	}
	private void generateCopperOre(IBlockState ore, World world, Random random, int x, int z, int minY, int maxY, int size,
			int chances) {
		int diff = maxY - minY;
		for (int i = 0; i < chances; i++) {
			BlockPos pos = new BlockPos(x + random.nextInt(16), minY + random.nextInt(diff), z + random.nextInt(16));

			WorldGenMinable generatorGenMinable = new WorldGenMinable(ore, size, BlockMatcher.forBlock(Blocks.STONE));
			generatorGenMinable.generate(world, random, pos);
		}
	}

	private void generate(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ,double chancesToSpawn, int minY, int maxY,int ID) {
		if(chancesToSpawn<1){
			if(random.nextDouble()<chancesToSpawn) chancesToSpawn=1;
			else chancesToSpawn=0;
		}
		int hightDiff=maxY-minY+1;
		for(int i=0; i<chancesToSpawn;i++){
			BlockPos pos=new BlockPos(chunkX*16+10+random.nextInt(15),minY+random.nextInt(hightDiff),chunkZ*16+10+random.nextInt(15));

			if(minY<0) pos=world.getHeight(pos);

			if(world.provider.getBiomeForCoords(pos)==Biome.getBiomeForId(ID)) generator.generate(world,random,pos);
		}
	}

	private void generateInKakuriyo(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ,double chancesToSpawn, int minY, int maxY) {
		if(chancesToSpawn<1){
			if(random.nextDouble()<chancesToSpawn) chancesToSpawn=1;
			else chancesToSpawn=0;
		}
		int hightDiff=maxY-minY+1;
		for(int i=0; i<chancesToSpawn;i++){
			BlockPos pos=new BlockPos(chunkX*16+10+random.nextInt(15),minY+random.nextInt(hightDiff),chunkZ*16+10+random.nextInt(15));

			if(minY<0) pos=world.getHeight(pos);

			if(world.provider.getDimension()==ModCore_Urushi.KakuriyoDimensionID) generator.generate(world,random,pos);
		}
	}





}

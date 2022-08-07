package urushi.WorldGen;

import net.minecraft.block.BlockPumpkin;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import urushi.ModCore_Urushi;

import java.util.Random;

public class WorldGenBamboo extends WorldGenerator
{
    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        for (int i = 0; i < 100; ++i)
        {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (worldIn.isAirBlock(blockpos) && worldIn.getBlockState(blockpos.down()).getBlock() == Blocks.GRASS )
            {
                worldIn.setBlockState(blockpos, ModCore_Urushi.JapaneseTimberBamboo.getDefaultState(), 2);
                worldIn.setBlockState(blockpos.add(0,1,0), ModCore_Urushi.JapaneseTimberBamboo.getDefaultState(), 2);
                worldIn.setBlockState(blockpos.add(0,2,0), ModCore_Urushi.JapaneseTimberBamboo.getDefaultState(), 2);
               for(int k=0;k<rand.nextInt(6);k++) {
                    worldIn.setBlockState(blockpos.add(0,3+k,0), ModCore_Urushi.JapaneseTimberBamboo.getDefaultState(), 2);

                }
            }
        }

        return true;
    }
}

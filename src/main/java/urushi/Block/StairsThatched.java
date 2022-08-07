package urushi.Block;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StairsThatched extends Stairs {
    public StairsThatched(IBlockState modelState) {
        super(modelState);
        setHardness(0.3F);
        setResistance(3F);
    }
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance)
    {
        entityIn.fall(fallDistance, 0.2F);
    }
}

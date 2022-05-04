package urushi.Block;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import urushi.ModCore_Urushi;

public class CropAzuki extends BlockCrops
{
    public CropAzuki()
    {
        this.setDefaultState(this.blockState.getBaseState().withProperty(this.getAgeProperty(), Integer.valueOf(0)));
        this.setTickRandomly(true);
        this.setHardness(0.0F);
        this.setSoundType(SoundType.PLANT);
        this.disableStats();
    }

    protected Item getSeed()
    {
        return ModCore_Urushi.Azuki;
    }

    protected Item getCrop()
    {
        return ModCore_Urushi.Azuki;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if(!worldIn.isRemote){
        if(state.getValue(this.getAgeProperty())==4) {
            state.getBlock().dropBlockAsItem(worldIn,pos,state,0);
            worldIn.setBlockState(pos,this.getDefaultState());
            return true;
        }
        }
        return false;
    }

    public int getMaxAge()
    {
        return 4;
    }



}
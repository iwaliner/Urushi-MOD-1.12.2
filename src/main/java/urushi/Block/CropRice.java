package urushi.Block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import urushi.ModCore_Urushi;

import java.util.Random;

public class CropRice extends BlockCrops
{
    public CropRice()
    {
        this.setDefaultState(this.blockState.getBaseState().withProperty(this.getAgeProperty(), Integer.valueOf(0)));
        this.setTickRandomly(true);
        this.setHardness(0.0F);
        this.setSoundType(SoundType.PLANT);
        this.disableStats();
    }
    protected Item getSeed()
    {
        return ModCore_Urushi.RiceEars;
    }

    protected Item getCrop()
    {
        return ModCore_Urushi.RiceEars;
    }



    protected boolean canSustainBush(IBlockState state)
    {
        return state.getBlock() == ModCore_Urushi.PaddyField;
    }
    public int getMaxAge()
    {
        return 4;
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

}
package urushi.Block;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
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




    public int getMaxAge()
    {
        return 4;
    }



}
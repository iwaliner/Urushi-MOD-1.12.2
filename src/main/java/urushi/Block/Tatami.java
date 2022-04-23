package urushi.Block;


import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import urushi.Else.EnumType;
import urushi.ModCore_Urushi;

public class Tatami extends BlockRotatedPillar  {
    public static final PropertyEnum<EnumType.EnumType4> VARIANT = PropertyEnum.<EnumType.EnumType4>create("variant", EnumType.EnumType4.class);
   // public static final PropertyEnum<BlockLog.EnumAxis> LOG_AXIS = PropertyEnum.<BlockLog.EnumAxis>create("axis", BlockLog.EnumAxis.class);

    public Tatami() {
        super(Material.GRASS);
        this.setCreativeTab(ModCore_Urushi.TabUrushi);
        setResistance(10F);
        setLightOpacity(255);
        setLightLevel(0.0F);
        setHardness(0.2F);
        setSoundType(SoundType.PLANT);
    }
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        items.add(new ItemStack(this, 1, 0));
        items.add(new ItemStack(this, 1, 1));

    }
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.byMetadata((meta & 3) % 4));

        switch (meta & 12)
        {
            case 0:
                iblockstate = iblockstate.withProperty(BlockRotatedPillar.AXIS, EnumFacing.Axis.Y);
                break;
            case 4:
                iblockstate = iblockstate.withProperty(BlockRotatedPillar.AXIS, EnumFacing.Axis.X);
                break;
            case 8:
                iblockstate = iblockstate.withProperty(BlockRotatedPillar.AXIS, EnumFacing.Axis.Z);
                break;
            default:
                iblockstate = iblockstate.withProperty(BlockRotatedPillar.AXIS, EnumFacing.Axis.Y);
        }

        return iblockstate;
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    @SuppressWarnings("incomplete-switch")
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | ((EnumType.EnumType4)state.getValue(VARIANT)).getMetadata();

        switch ((EnumFacing.Axis)state.getValue(BlockRotatedPillar.AXIS))
        {
            case X:
                i |= 4;
                break;
            case Z:
                i |= 8;
                break;
            //case NONE:
           //     i |= 12;
        }

        return i;
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {VARIANT, BlockRotatedPillar.AXIS});
    }
    public int damageDropped(IBlockState state)
    {
        return ((EnumType.EnumType4)state.getValue(VARIANT)).getMetadata();
    }

}

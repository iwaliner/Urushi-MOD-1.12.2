package urushi.Block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockWall;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import urushi.ModCore_Urushi;

public class StoneWall extends BlockWall {
  //  public static final PropertyEnum<urushi.Else.EnumType.EnumType8> VARIANTs = PropertyEnum.<urushi.Else.EnumType.EnumType8>create("variant", urushi.Else.EnumType.EnumType8.class);

    public StoneWall(Block block) {
        super(block);

        setCreativeTab(ModCore_Urushi.TabUrushi);
        //this.setDefaultState(this.blockState.getBaseState().withProperty(UP, Boolean.valueOf(false)).withProperty(NORTH, Boolean.valueOf(false)).withProperty(EAST, Boolean.valueOf(false)).withProperty(SOUTH, Boolean.valueOf(false)).withProperty(WEST, Boolean.valueOf(false)).withProperty(VARIANTs, urushi.Else.EnumType.EnumType8.TypeA));

    }
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {

            items.add(new ItemStack(this, 1, 0));

    }
  /*  public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        int meta = ((urushi.Else.EnumType.EnumType8) state.getValue(VARIANTs)).getMetadata();
        return new ItemStack(this.getDefaultState().getBlock(), 1, ((urushi.Else.EnumType.EnumType8)state.getValue(VARIANTs)).getMetadata());
    }


    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        for(int i=0;i<1;i++) {
            items.add(new ItemStack(this, 1, i));
        }
    }
    public int damageDropped(IBlockState state) {
        return ((urushi.Else.EnumType.EnumType8) state.getValue(VARIANTs)).getMetadata();
    }
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANTs, urushi.Else.EnumType.EnumType8.byMetadata(meta));
    }
    public int getMetaFromState(IBlockState state) {
        return ((urushi.Else.EnumType.EnumType8) state.getValue(VARIANTs)).getMetadata();
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{UP, NORTH, EAST, WEST, SOUTH,VARIANTs});
    }
*/
}

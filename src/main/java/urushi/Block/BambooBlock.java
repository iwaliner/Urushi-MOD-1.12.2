package urushi.Block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import urushi.Else.EnumType;
import urushi.ModCore_Urushi;

public class BambooBlock extends Block
{
    public static final PropertyEnum<EnumType.EnumType2> VARIANT = PropertyEnum.<EnumType.EnumType2>create("variant", EnumType.EnumType2.class);

    public BambooBlock()
    {
        super(Material.WOOD);
        setSoundType(SoundType.WOOD);
        setResistance(10F);
        setLightOpacity(255);
        setLightLevel(0.0F);
        setHardness(0.5F);
        setHarvestLevel("axe", 0);
        setCreativeTab(ModCore_Urushi.TabUrushi);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT,EnumType.EnumType2.TypeA));

    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return MapColor.WOOD;
    }


    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        int meta = ((EnumType.EnumType2) state.getValue(VARIANT)).getMetadata();
        return new ItemStack(ModCore_Urushi.BambooBlock, 1, ((EnumType.EnumType2)state.getValue(VARIANT)).getMetadata());
    }


    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        for(int i=0;i<2;i++) {
            items.add(new ItemStack(this, 1, i));
        }
    }
    public int damageDropped(IBlockState state) {
        return ((EnumType.EnumType2) state.getValue(VARIANT)).getMetadata();
    }
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT,EnumType.EnumType2.byMetadata(meta));
    }
    public int getMetaFromState(IBlockState state) {
        return ((EnumType.EnumType2) state.getValue(VARIANT)).getMetadata();
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{VARIANT});
    }

}
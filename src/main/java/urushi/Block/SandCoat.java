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
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import urushi.Else.EnumType;
import urushi.ModCore_Urushi;

public class SandCoat extends Block
{
    public static final PropertyEnum<EnumType.EnumType8> VARIANT = PropertyEnum.<EnumType.EnumType8>create("variant", EnumType.EnumType8.class);

    public SandCoat()
    {
        super(Material.SAND);
        setSoundType(SoundType.SAND);
        setResistance(3F);
        setLightOpacity(255);
        setLightLevel(0.0F);
        setHardness(0.3F);
        setHarvestLevel("shovel", 0);
        setCreativeTab(ModCore_Urushi.TabUrushi);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT,EnumType.EnumType8.TypeA));

    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return MapColor.SAND;
    }
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        int meta = ((EnumType.EnumType8) state.getValue(VARIANT)).getMetadata();
        return new ItemStack(ModCore_Urushi.SandCoast, 1, ((EnumType.EnumType8)state.getValue(VARIANT)).getMetadata());
    }
    public String getUnlocalizedName(int meta)
    {
        return super.getUnlocalizedName() + "." + EnumType.EnumType8.byMetadata(meta).getMetadata();
    }

    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        for(int i=0;i<3;i++) {
            items.add(new ItemStack(this, 1, i));
        }
    }
    public int damageDropped(IBlockState state) {
        return ((EnumType.EnumType8) state.getValue(VARIANT)).getMetadata();
    }
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT,EnumType.EnumType8.byMetadata(meta));
    }
    public int getMetaFromState(IBlockState state) {
        return ((EnumType.EnumType8) state.getValue(VARIANT)).getMetadata();
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{VARIANT});
    }

}
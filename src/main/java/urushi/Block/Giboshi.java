package urushi.Block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import urushi.Else.EnumType;
import urushi.ModCore_Urushi;

import javax.annotation.Nullable;

public class Giboshi extends Block
{
    protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.0625D*3, 0.0D, 0.0625D*3, 0.0625D*13, 1D, 0.0625D*13);
    public static final PropertyEnum<EnumType.EnumType8> VARIANT = PropertyEnum.<EnumType.EnumType8>create("variant", EnumType.EnumType8.class);

    public Giboshi()
    {
        super(Material.IRON);
        this.setCreativeTab(ModCore_Urushi.TabUrushi);
        setResistance(30F);
        setLightOpacity(0);
        setLightLevel(0.0F);
        setHardness(0.5F);
        setSoundType(SoundType.METAL);
        setHarvestLevel("pickaxe",0);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, urushi.Else.EnumType.EnumType8.TypeA));

    }

    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return AABB;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return AABB;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }


    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        int meta = ((EnumType.EnumType8) state.getValue(VARIANT)).getMetadata();
        return new ItemStack(ModCore_Urushi.Giboshi, 1, ((EnumType.EnumType8)state.getValue(VARIANT)).getMetadata());
    }


    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        for(int i=0;i<2;i++) {
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

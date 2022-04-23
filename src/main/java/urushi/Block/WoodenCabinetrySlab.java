package urushi.Block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import urushi.Else.EnumType;
import urushi.ModCore_Urushi;
import urushi.TileEntity.TileEntityWoodenCabinetrySlab;

import java.util.Random;


public class WoodenCabinetrySlab extends BlockContainer
{
   // public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyEnum<EnumType.EnumType8> VARIANT = PropertyEnum.<EnumType.EnumType8>create("variant", EnumType.EnumType8.class);

    public static final PropertyEnum<BlockSlab.EnumBlockHalf> HALF = PropertyEnum.<BlockSlab.EnumBlockHalf>create("half", BlockSlab.EnumBlockHalf.class);
    protected static final AxisAlignedBB AABB_BOTTOM_HALF = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
    protected static final AxisAlignedBB AABB_TOP_HALF = new AxisAlignedBB(0.0D, 0.5D, 0.0D, 1.0D, 1.0D, 1.0D);


    public WoodenCabinetrySlab()
    {
        super(Material.WOOD);
        IBlockState iblockstate = this.blockState.getBaseState();
        this.setDefaultState(iblockstate.withProperty(VARIANT,EnumType.EnumType8.TypeA));


          this.setCreativeTab(ModCore_Urushi.TabUrushi);

        setResistance(10F);
        setLightOpacity(255);
        setLightLevel(0.0F);
        setHardness(1.0F);
        setHarvestLevel("axe", 0);
        setSoundType(SoundType.WOOD);
        this.useNeighborBrightness=true;

    }
    protected boolean canSilkHarvest()
    {
        return false;
    }
    public int damageDropped(IBlockState state)
    {
        return 0;
    }

    public int tickRate(World worldIn)
    {
        return 2;
    }
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(ModCore_Urushi.WoodenCabinetryUnderSlab);
    }
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(ModCore_Urushi.WoodenCabinetryUnderSlab, 1, ((EnumType.EnumType8)state.getValue(VARIANT)).getMetadata());
    }
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {

            return state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP ? AABB_TOP_HALF : AABB_BOTTOM_HALF;

    }


    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityWoodenCabinetrySlab)
            {
                playerIn.displayGUIChest((TileEntityWoodenCabinetrySlab)tileentity);

            }

            return true;
        }
    }
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityWoodenCabinetrySlab();
    }

    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        IBlockState iblockstate = super.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);

if(facing != EnumFacing.DOWN && (facing == EnumFacing.UP || (double) hitY <= 0.5D) ) {
   // return facing != EnumFacing.DOWN && (facing == EnumFacing.UP || (double) hitY <= 0.5D) ? iblockstate : iblockstate.withProperty(HALF, BlockSlab.EnumBlockHalf.TOP);
if(placer.getHorizontalFacing().getOpposite()==EnumFacing.NORTH){
    return  iblockstate.withProperty(VARIANT, EnumType.EnumType8.TypeA);
}else if(placer.getHorizontalFacing().getOpposite()==EnumFacing.SOUTH){
    return  iblockstate.withProperty(VARIANT, EnumType.EnumType8.TypeB);
}else if(placer.getHorizontalFacing().getOpposite()==EnumFacing.EAST){
    return  iblockstate.withProperty(VARIANT, EnumType.EnumType8.TypeC);
}else if(placer.getHorizontalFacing().getOpposite()==EnumFacing.WEST){
    return  iblockstate.withProperty(VARIANT, EnumType.EnumType8.TypeD);
}
}else{
    if(placer.getHorizontalFacing().getOpposite()==EnumFacing.NORTH){
        return  iblockstate.withProperty(VARIANT, EnumType.EnumType8.TypeA).withProperty(HALF, BlockSlab.EnumBlockHalf.TOP);
    }else if(placer.getHorizontalFacing().getOpposite()==EnumFacing.SOUTH){
        return  iblockstate.withProperty(VARIANT, EnumType.EnumType8.TypeB).withProperty(HALF, BlockSlab.EnumBlockHalf.TOP);
    }else if(placer.getHorizontalFacing().getOpposite()==EnumFacing.EAST){
        return  iblockstate.withProperty(VARIANT, EnumType.EnumType8.TypeC).withProperty(HALF, BlockSlab.EnumBlockHalf.TOP);
    }else if(placer.getHorizontalFacing().getOpposite()==EnumFacing.WEST){
        return  iblockstate.withProperty(VARIANT, EnumType.EnumType8.TypeD).withProperty(HALF, BlockSlab.EnumBlockHalf.TOP);
    }
}
return iblockstate;
    }


    /**
     * Called serverside after this block is replaced with another in Chunk, but before the Tile Entity is updated
     */
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity instanceof TileEntityWoodenCabinetrySlab)
        {
            InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityWoodenCabinetrySlab)tileentity);
            worldIn.updateComparatorOutputLevel(pos, this);
        }

        super.breakBlock(worldIn, pos, state);
    }




    /**
     * The type of render function called. MODEL for mixed tesr and static model, MODELBLOCK_ANIMATED for TESR-only,
     * LIQUID for vanilla liquids, INVISIBLE to skip all rendering
     */
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }



    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, EnumType.EnumType8.byMetadata(meta & 7));

        return iblockstate.withProperty(HALF, (meta & 8) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
    }

    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | ((EnumType.EnumType8)state.getValue(VARIANT)).getMetadata();

        if ( state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP)
        {
            i |= 8;
        }

        return i;
    }

    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
  /*  public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }
*/

    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
  /* public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }*/


    protected BlockStateContainer createBlockState()
    {
        return  new BlockStateContainer(this, new IProperty[] {HALF, VARIANT});
    }
    public boolean isTopSolid(IBlockState state)
    {
        return state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP;
    }
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {

         if (face == EnumFacing.UP && state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP)
        {
            return BlockFaceShape.SOLID;
        }
        else
        {
            return face == EnumFacing.DOWN && state.getValue(HALF) == BlockSlab.EnumBlockHalf.BOTTOM ? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED;
        }
    }
    @Override
    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face)
    {
       if (net.minecraftforge.common.ForgeModContainer.disableStairSlabCulling)
            return super.doesSideBlockRendering(state, world, pos, face);

        if ( state.isOpaqueCube() )
            return true;

        BlockSlab.EnumBlockHalf side = state.getValue(HALF);
        return (side == BlockSlab.EnumBlockHalf.TOP && face == EnumFacing.UP) || (side == BlockSlab.EnumBlockHalf.BOTTOM && face == EnumFacing.DOWN);

        //return false;
    }
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {

         if (side != EnumFacing.UP && side != EnumFacing.DOWN && !super.shouldSideBeRendered(blockState, blockAccess, pos, side))
        {
            return false;
        }
        else if (false) // Forge: Additional logic breaks doesSideBlockRendering and is no longer useful.
        {
            IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
            boolean flag = isHalfSlab(iblockstate) && iblockstate.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP;
            boolean flag1 = isHalfSlab(blockState) && blockState.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP;

            if (flag1)
            {
                if (side == EnumFacing.DOWN)
                {
                    return true;
                }
                else if (side == EnumFacing.UP && super.shouldSideBeRendered(blockState, blockAccess, pos, side))
                {
                    return true;
                }
                else
                {
                    return !isHalfSlab(iblockstate) || !flag;
                }
            }
            else if (side == EnumFacing.UP)
            {
                return true;
            }
            else if (side == EnumFacing.DOWN && super.shouldSideBeRendered(blockState, blockAccess, pos, side))
            {
                return true;
            }
            else
            {
                return !isHalfSlab(iblockstate) || flag;
            }
        }
       // return false;
        return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
    }
    @SideOnly(Side.CLIENT)
    protected static boolean isHalfSlab(IBlockState state)
    {

        return true;
    }
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }


}

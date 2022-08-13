package urushi.Block;


import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import urushi.Else.EnumType;
import urushi.ModCore_Urushi;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public abstract class Roof225 extends BlockSlab
{
    protected static final AxisAlignedBB AABB_UNDER = new AxisAlignedBB(0.0D, 0D, 0.0D, 1.0D, 0.5D, 1.0D);
    protected static final AxisAlignedBB AABB_UPPER = new AxisAlignedBB(0.0D, -0.5D, 0.0D, 1.0D, 0D, 1.0D);
    protected static final AxisAlignedBB AABB_EXTEND_UPPER = new AxisAlignedBB(0.0D, -0.5D, 0.0D, 1.0D, 0.5D, 1.0D);

    public static final PropertyEnum<EnumType.EnumType2> VARIANT = PropertyEnum.<EnumType.EnumType2>create("variant", EnumType.EnumType2.class);
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public Roof225(Material material)
    {
        super(material);
        IBlockState iblockstate = this.blockState.getBaseState();

        if (!this.isDouble())
        {
            iblockstate = iblockstate.withProperty(HALF, EnumBlockHalf.BOTTOM);
        }
        this.useNeighborBrightness=true;
        this.setDefaultState(iblockstate.withProperty(VARIANT,EnumType.EnumType2.TypeA).withProperty(FACING, EnumFacing.NORTH));
        this.setCreativeTab(ModCore_Urushi.TabUrushi);
        setLightOpacity(255);
        setLightLevel(0.0F);

        if(material==Material.ROCK){
            setHarvestLevel("pickaxe", 0);
            setHardness(1.0F);
            setResistance(10F);
        }else{
            setSoundType(SoundType.PLANT);
            setResistance(3F);
            setHardness(0.3F);
        }

    }
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState)
    {
        if (!isActualState)
        {
            state = this.getActualState(state, worldIn, pos);
        }
     addCollisionBoxToList(pos, entityBox, collidingBoxes, state.getValue(HALF)==EnumBlockHalf.TOP?AABB_UPPER:AABB_UNDER);

    }
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
          return state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP ? AABB_EXTEND_UPPER : AABB_UNDER;

    }


    public String getUnlocalizedName(int meta)
    {
        return super.getUnlocalizedName() ;
    }

    public IProperty<?> getVariantProperty()
    {
        return VARIANT;
    }

    public Comparable<?> getTypeForItem(ItemStack stack)
    {
        return EnumType.EnumType2.byMetadata(stack.getMetadata() & 7);
    }

    public IBlockState getStateFromMeta(int meta)
    {

        if (!this.isDouble())
        {
            if(meta<4){
                return  this.getDefaultState().withProperty(HALF,EnumBlockHalf.BOTTOM).withProperty(VARIANT, EnumType.EnumType2.TypeA).withProperty(FACING,EnumFacing.getHorizontal(meta));
            }else if(meta<8){
                return  this.getDefaultState().withProperty(HALF,EnumBlockHalf.BOTTOM).withProperty(VARIANT, EnumType.EnumType2.TypeB).withProperty(FACING,EnumFacing.getHorizontal(meta-4));
            }else if(meta<12){
                return  this.getDefaultState().withProperty(HALF,EnumBlockHalf.TOP).withProperty(VARIANT, EnumType.EnumType2.TypeA).withProperty(FACING,EnumFacing.getHorizontal(meta-8));
            }else{
                return  this.getDefaultState().withProperty(HALF,EnumBlockHalf.TOP).withProperty(VARIANT, EnumType.EnumType2.TypeB).withProperty(FACING,EnumFacing.getHorizontal(meta-12));
            }
        }

        return  meta<4? this.getDefaultState().withProperty(VARIANT, EnumType.EnumType2.TypeA).withProperty(FACING, EnumFacing.getHorizontal(meta)):this.getDefaultState().withProperty(VARIANT, EnumType.EnumType2.TypeB).withProperty(FACING, EnumFacing.getHorizontal(meta-4));

    }

    public int getMetaFromState(IBlockState state)
    {
       int variantMeta= ((EnumType.EnumType2)state.getValue(VARIANT)).getMetadata();
        int facingMeta= ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
        if(!this.isDouble()) {
            EnumBlockHalf halfState = ((EnumBlockHalf) state.getValue(HALF));


            return halfState == EnumBlockHalf.BOTTOM ? facingMeta + variantMeta * 4 : facingMeta + variantMeta * 4 + 8;
        }
        return  facingMeta + variantMeta * 4 ;

    }

    protected BlockStateContainer createBlockState()
    {
        return this.isDouble() ? new BlockStateContainer(this, new IProperty[] {VARIANT,FACING}) : new BlockStateContainer(this, new IProperty[] {HALF, VARIANT,FACING});
    }

    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {

        IBlockState blockstateClicked=null;
        EnumFacing facingP=placer.getHorizontalFacing();
        EnumFacing facingState=placer.getHorizontalFacing().getOpposite();
        boolean flag1 = facingP.getAxis()== EnumFacing.Axis.Z && (worldIn.getBlockState(pos.west()).getBlock() instanceof Roof225 || worldIn.getBlockState(pos.east()).getBlock() instanceof Roof225) || facingP.getAxis() == EnumFacing.Axis.X && (worldIn.getBlockState(pos.north()).getBlock() instanceof Roof225 || worldIn.getBlockState(pos.south()).getBlock() instanceof Roof225);
        if(facingP.getAxis() == EnumFacing.Axis.Z){
            if(worldIn.getBlockState(pos.west()).getBlock() instanceof Roof225 ){
                blockstateClicked=worldIn.getBlockState(pos.west());
            }else if(worldIn.getBlockState(pos.east()).getBlock() instanceof Roof225 ){
                blockstateClicked=worldIn.getBlockState(pos.east());
            }
        }else if(facingP.getAxis() == EnumFacing.Axis.X){
            if(worldIn.getBlockState(pos.north()).getBlock() instanceof Roof225 ){
                blockstateClicked=worldIn.getBlockState(pos.north());
            }else if(worldIn.getBlockState(pos.south()).getBlock() instanceof Roof225 ){
                blockstateClicked=worldIn.getBlockState(pos.south());
            }
        }

        if(flag1&&blockstateClicked!=null){
            return this.getDefaultState().withProperty(FACING, blockstateClicked.getValue(FACING)).withProperty(HALF, blockstateClicked.getValue(HALF));

        }else if(worldIn.getBlockState(pos.down()).getBlock() instanceof BlockSlab&&!((BlockSlab) worldIn.getBlockState(pos.down()).getBlock()).isDouble()){
            if(worldIn.getBlockState(pos.down()).getValue(HALF)==EnumBlockHalf.TOP){
                return this.getDefaultState().withProperty(FACING, facingState).withProperty(HALF, EnumBlockHalf.BOTTOM);
            }else if(worldIn.getBlockState(pos.down()).getValue(HALF)==EnumBlockHalf.BOTTOM){
                return this.getDefaultState().withProperty(FACING, facingState).withProperty(HALF, EnumBlockHalf.TOP);
            }else{
                return this.getDefaultState();
            }
        }
        else{
            IBlockState blockstate1 = this.getDefaultState().withProperty(FACING, facingState).withProperty(HALF, EnumBlockHalf.BOTTOM);
            EnumFacing direction2 =facing;
            return direction2 != EnumFacing.DOWN && (direction2 == EnumFacing.UP || !(hitY - (double)pos.getY() > 0.5D)) ? blockstate1 : blockstate1.withProperty(HALF, EnumBlockHalf.TOP);

        }
    }
    public int damageDropped(IBlockState state)
    {
        return ((EnumType.EnumType2)state.getValue(VARIANT)).getMetadata()*4;
    }


}
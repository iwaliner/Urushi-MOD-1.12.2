package urushi.Block;



import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
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


public class TatamiCarpet extends Block  {
    public static final PropertyEnum<EnumType.EnumType8> VARIANT = PropertyEnum.<EnumType.EnumType8>create("variant", EnumType.EnumType8.class);
    public static final PropertyEnum<EnumType.EnumType2> VARIANT2 = PropertyEnum.<EnumType.EnumType2>create("variant2", EnumType.EnumType2.class);
    protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.0D, 0.0D, 0D, 1.0D, 0.0625D*2, 1.0D);



    private int amount;

    public TatamiCarpet(int i) {
        super(Material.GRASS);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.EnumType8.TypeA).withProperty(VARIANT2, EnumType.EnumType2.TypeA));
        this.setCreativeTab(ModCore_Urushi.TabUrushi);
        setResistance(10F);
        setLightOpacity(0);
        setLightLevel(0.0F);
        setHardness(0.2F);
        setSoundType(SoundType.PLANT);
        amount=i;
    }
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState)
    {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB);
    }

    public int damageDropped(IBlockState state) {
        return ((EnumType.EnumType8) state.getValue(VARIANT)).getMetadata();
    }


    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for(int i=0;i<amount;i++) {
            items.add(new ItemStack(this, 1, i));
        }
    }

    public IBlockState getStateFromMeta(int meta) {
        return meta<8? this.getDefaultState().withProperty(VARIANT, EnumType.EnumType8.byMetadata(meta)).withProperty(VARIANT2, EnumType.EnumType2.TypeA):this.getDefaultState().withProperty(VARIANT, EnumType.EnumType8.byMetadata(meta-8)).withProperty(VARIANT2, EnumType.EnumType2.TypeB);
    }

    public int getMetaFromState(IBlockState state) {
        return ((EnumType.EnumType8) state.getValue(VARIANT)).getMetadata()+((EnumType.EnumType2) state.getValue(VARIANT2)).getMetadata()*8;
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{VARIANT,VARIANT2});
    }

    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {

        if(meta==0 ) {
           if(placer.getHorizontalFacing().getOpposite()==EnumFacing.NORTH||placer.getHorizontalFacing().getOpposite()==EnumFacing.SOUTH){
                return  this.getDefaultState().withProperty(VARIANT, EnumType.EnumType8.TypeA).withProperty(VARIANT2, EnumType.EnumType2.TypeA);
            }else {
               return   this.getDefaultState().withProperty(VARIANT, EnumType.EnumType8.TypeA).withProperty(VARIANT2, EnumType.EnumType2.TypeB);
           }
        }else if(meta==1 ) {
            if(placer.getHorizontalFacing().getOpposite()==EnumFacing.NORTH||placer.getHorizontalFacing().getOpposite()==EnumFacing.SOUTH){
                return  this.getDefaultState().withProperty(VARIANT, EnumType.EnumType8.TypeB).withProperty(VARIANT2, EnumType.EnumType2.TypeA);
            }else {
                return  this.getDefaultState().withProperty(VARIANT, EnumType.EnumType8.TypeB).withProperty(VARIANT2, EnumType.EnumType2.TypeB);
            }
        }
        else if(meta==2 ) {
            if(placer.getHorizontalFacing().getOpposite()==EnumFacing.NORTH||placer.getHorizontalFacing().getOpposite()==EnumFacing.SOUTH){
                return  this.getDefaultState().withProperty(VARIANT, EnumType.EnumType8.TypeC).withProperty(VARIANT2, EnumType.EnumType2.TypeA);
            }else {
                return  this.getDefaultState().withProperty(VARIANT, EnumType.EnumType8.TypeC).withProperty(VARIANT2, EnumType.EnumType2.TypeB);
            }
        }
        else if(meta==3 ) {
            if(placer.getHorizontalFacing().getOpposite()==EnumFacing.NORTH||placer.getHorizontalFacing().getOpposite()==EnumFacing.SOUTH){
                return  this.getDefaultState().withProperty(VARIANT, EnumType.EnumType8.TypeD).withProperty(VARIANT2, EnumType.EnumType2.TypeA);
            }else {
                return  this.getDefaultState().withProperty(VARIANT, EnumType.EnumType8.TypeD).withProperty(VARIANT2, EnumType.EnumType2.TypeB);
            }
        }
        else if(meta==4 ) {
            if(placer.getHorizontalFacing().getOpposite()==EnumFacing.NORTH||placer.getHorizontalFacing().getOpposite()==EnumFacing.SOUTH){
                return  this.getDefaultState().withProperty(VARIANT, EnumType.EnumType8.TypeE).withProperty(VARIANT2, EnumType.EnumType2.TypeA);
            }else {
                return  this.getDefaultState().withProperty(VARIANT, EnumType.EnumType8.TypeE).withProperty(VARIANT2, EnumType.EnumType2.TypeB);
            }
        }
        else if(meta==5 ) {
            if(placer.getHorizontalFacing().getOpposite()==EnumFacing.NORTH||placer.getHorizontalFacing().getOpposite()==EnumFacing.SOUTH){
                return  this.getDefaultState().withProperty(VARIANT, EnumType.EnumType8.TypeF).withProperty(VARIANT2, EnumType.EnumType2.TypeA);
            }else {
                return  this.getDefaultState().withProperty(VARIANT, EnumType.EnumType8.TypeF).withProperty(VARIANT2, EnumType.EnumType2.TypeB);
            }
        }
        else if(meta==6 ) {
            if(placer.getHorizontalFacing().getOpposite()==EnumFacing.NORTH||placer.getHorizontalFacing().getOpposite()==EnumFacing.SOUTH){
                return  this.getDefaultState().withProperty(VARIANT, EnumType.EnumType8.TypeG).withProperty(VARIANT2, EnumType.EnumType2.TypeA);
            }else {
                return  this.getDefaultState().withProperty(VARIANT, EnumType.EnumType8.TypeG).withProperty(VARIANT2, EnumType.EnumType2.TypeB);
            }
        }
        else if(meta==7 ) {
            if(placer.getHorizontalFacing().getOpposite()==EnumFacing.NORTH||placer.getHorizontalFacing().getOpposite()==EnumFacing.SOUTH){
                return  this.getDefaultState().withProperty(VARIANT, EnumType.EnumType8.TypeH).withProperty(VARIANT2, EnumType.EnumType2.TypeA);
            }else {
                return  this.getDefaultState().withProperty(VARIANT, EnumType.EnumType8.TypeH).withProperty(VARIANT2, EnumType.EnumType2.TypeB);
            }
        }
        return     this.getDefaultState();

    }

    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
       return  AABB;
    }
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return AABB;
    }







}

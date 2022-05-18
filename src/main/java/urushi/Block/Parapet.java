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
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import urushi.Else.EnumType;
import urushi.ModCore_Urushi;

import javax.annotation.Nullable;
import java.util.List;


public class Parapet extends Block  {
    public static final PropertyEnum<EnumType.EnumType8> VARIANT = PropertyEnum.<EnumType.EnumType8>create("variant", EnumType.EnumType8.class);
    public static final PropertyEnum<EnumType.EnumType2> VARIANT2 = PropertyEnum.<EnumType.EnumType2>create("variant2", EnumType.EnumType2.class);
    protected static final AxisAlignedBB AABB_B = new AxisAlignedBB(0.0625D*5, 0.0D, 0D, 0.0625D*11, 1D, 1D);
    protected static final AxisAlignedBB AABB_A = new AxisAlignedBB(0.0D, 0.0D, 0.0625D*5, 1.0D, 1D, 0.0625D*11);
    protected static final AxisAlignedBB AABB_CollB = new AxisAlignedBB(0.0625D*5, 0.0D, 0D, 0.0625D*11, 1.5D, 1D);
    protected static final AxisAlignedBB AABB_CollA = new AxisAlignedBB(0.0D, 0.0D, 0.0625D*5, 1.0D, 1.5D, 0.0625D*11);



    private int amount;

    public Parapet(int i) {
        super(Material.WOOD);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.EnumType8.TypeA).withProperty(VARIANT2, EnumType.EnumType2.TypeA));
        this.setCreativeTab(ModCore_Urushi.TabUrushi);
        setResistance(10F);
        setLightOpacity(0);
        setLightLevel(0.0F);
        setHardness(0.5F);
        setHarvestLevel("axe", 0);
        setSoundType(SoundType.WOOD);
        amount=i;
    }
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState)
    {



        if (state.getValue(VARIANT2)== EnumType.EnumType2.TypeA)
        {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_CollA);
        }else
        {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_CollB);
        }
    }



    /**
     * Gets the metadata of the item this Block can drop. This method is called when the block gets destroyed. It
     * returns the metadata of the dropped item based on the old metadata of the block.
     */
    public int damageDropped(IBlockState state) {
        return ((EnumType.EnumType8) state.getValue(VARIANT)).getMetadata();
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for(int i=0;i<amount;i++) {
            items.add(new ItemStack(this, 1, i));
        }
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta) {
        return meta<8? this.getDefaultState().withProperty(VARIANT, EnumType.EnumType8.byMetadata(meta)).withProperty(VARIANT2, EnumType.EnumType2.TypeA):this.getDefaultState().withProperty(VARIANT, EnumType.EnumType8.byMetadata(meta-8)).withProperty(VARIANT2, EnumType.EnumType2.TypeB);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
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
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        state = state.getActualState(source, pos);
      EnumType.EnumType2 enumtype2 = (EnumType.EnumType2)state.getValue(VARIANT2);
       return  enumtype2== EnumType.EnumType2.TypeA?AABB_A:AABB_B;
    }








}

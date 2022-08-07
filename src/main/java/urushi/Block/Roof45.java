package urushi.Block;



import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.NonNullList;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import urushi.Else.EnumType;
import urushi.ModCore_Urushi;

import javax.annotation.Nullable;
import java.util.List;


public class Roof45 extends Block  {

    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    protected static final AxisAlignedBB AABB_SLAB_BASE = new AxisAlignedBB(0.0D, -1D, 0.0D, 1.0D, -0.5D, 1.0D);
    protected static final AxisAlignedBB AABB_WEST = new AxisAlignedBB(0.0D, -0.5D, 0.0D, 0.5D, 0D, 1.0D);
    protected static final AxisAlignedBB AABB_EAST = new AxisAlignedBB(0.5D, -0.5D, 0.0D, 1.0D, 0D, 1.0D);
    protected static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB(0.0D, -0.5D, 0.0D, 1.0D, 0D, 0.5D);
    protected static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB(0.0D, -0.5D, 0.5D, 1.0D, 0D, 1.0D);
    protected static final AxisAlignedBB AABB_EXTEND = new AxisAlignedBB(0.0D, 0D, 0.0D, 1.0D, 1D, 1.0D);



   public Roof45(Material material) {
        super(material);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        this.setCreativeTab(ModCore_Urushi.TabUrushi);
        setLightOpacity(225);
        setLightLevel(0.0F);
       if(material==Material.ROCK){
           setHarvestLevel("pickaxe", 0);
           this.useNeighborBrightness=true;
       }

    }
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState)
    {
        if (!isActualState)
        {
            state = this.getActualState(state, worldIn, pos);
        }
     /*   switch ((EnumFacing)state.getValue(FACING))
        {
            case NORTH:
            default:
                addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_NORTH);
            case SOUTH:
                addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_SOUTH);
            case WEST:
                addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WEST);
            case EAST:
                addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_EAST);
        }*/
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_SLAB_BASE);

    }

    public int damageDropped(IBlockState state) {
        return 0;
    }

    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
    }
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
    }


    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{FACING});
    }

    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
       return  this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
    }
     public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }
public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return  AABB_EXTEND;
    }
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos)
    {
        return AABB_EXTEND;
    }







}

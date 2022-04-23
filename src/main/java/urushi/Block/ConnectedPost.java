package urushi.Block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockShulkerBox;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import urushi.ModCore_Urushi;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ConnectedPost extends Block
{
    public static final PropertyBool NORTH_WALL = PropertyBool.create("northwall");
    public static final PropertyBool EAST_WALL = PropertyBool.create("eastwall");
    public static final PropertyBool SOUTH_WALL = PropertyBool.create("southwall");
    public static final PropertyBool WEST_WALL = PropertyBool.create("westwall");
    public static final PropertyBool NORTH_THIS = PropertyBool.create("norththis");
    public static final PropertyBool EAST_THIS = PropertyBool.create("eastthis");
    public static final PropertyBool SOUTH_THIS = PropertyBool.create("souththis");
    public static final PropertyBool WEST_THIS = PropertyBool.create("westthis");
    public static final PropertyBool OVER_WALL = PropertyBool.create("upperwall");
    public static final PropertyBool OVER_THIS = PropertyBool.create("upperthis");
    public static final PropertyBool BENEATH_WALL = PropertyBool.create("underwall");
    public static final PropertyBool BENEATH_THIS = PropertyBool.create("underthis");
    protected static final AxisAlignedBB AABB_NULL = new AxisAlignedBB(0D, 0.0D, 0D, 0D, 0D, 0D);

    protected static final AxisAlignedBB[] AABB_BY_INDEX = new AxisAlignedBB[] {new AxisAlignedBB(0.4375D, 0.0D, 0.4375D, 0.5625D, 1.0D, 0.5625D), new AxisAlignedBB(0.4375D, 0.0D, 0.4375D, 0.5625D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 0.5625D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 0.5625D, 1.0D, 1.0D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 0.5625D, 1.0D, 0.5625D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 0.5625D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.5625D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.5625D, 1.0D, 1.0D), new AxisAlignedBB(0.4375D, 0.0D, 0.4375D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.4375D, 0.0D, 0.4375D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};

    public ConnectedPost()
    {
        super(Material.WOOD);
        this.setDefaultState(this.blockState.getBaseState().withProperty(NORTH_THIS, Boolean.valueOf(false)).withProperty(EAST_THIS, Boolean.valueOf(false)).withProperty(SOUTH_THIS, Boolean.valueOf(false)).withProperty(WEST_THIS, Boolean.valueOf(false)).withProperty(NORTH_WALL, Boolean.valueOf(false)).withProperty(EAST_WALL, Boolean.valueOf(false)).withProperty(SOUTH_WALL, Boolean.valueOf(false)).withProperty(WEST_WALL, Boolean.valueOf(false)).withProperty(OVER_WALL, Boolean.valueOf(false)).withProperty(OVER_THIS, Boolean.valueOf(false)).withProperty(BENEATH_WALL, Boolean.valueOf(false)).withProperty(BENEATH_THIS, Boolean.valueOf(false)));
        this.setCreativeTab(ModCore_Urushi.TabUrushi);
        setResistance(10F);
        setLightOpacity(0);
        setLightLevel(0.0F);
        setHardness(0.5F);
        setHarvestLevel("axe", 0);
        setSoundType(SoundType.WOOD);
    }

   /* public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState)
    {
        if (!isActualState)
        {
            state = this.getActualState(state, worldIn, pos);
        }

        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BY_INDEX[0]);

        if (((Boolean)state.getValue(NORTH)).booleanValue())
        {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BY_INDEX[getBoundingBoxIndex(EnumFacing.NORTH)]);
        }

        if (((Boolean)state.getValue(SOUTH)).booleanValue())
        {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BY_INDEX[getBoundingBoxIndex(EnumFacing.SOUTH)]);
        }

        if (((Boolean)state.getValue(EAST)).booleanValue())
        {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BY_INDEX[getBoundingBoxIndex(EnumFacing.EAST)]);
        }

        if (((Boolean)state.getValue(WEST)).booleanValue())
        {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BY_INDEX[getBoundingBoxIndex(EnumFacing.WEST)]);
        }
    }

    private static int getBoundingBoxIndex(EnumFacing p_185729_0_)
    {
        return 1 << p_185729_0_.getHorizontalIndex();
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        state = this.getActualState(state, source, pos);
        return AABB_BY_INDEX[getBoundingBoxIndex(state)];
    }

    private static int getBoundingBoxIndex(IBlockState state)
    {
        int i = 0;

        if (((Boolean)state.getValue(NORTH)).booleanValue())
        {
            i |= getBoundingBoxIndex(EnumFacing.NORTH);
        }

        if (((Boolean)state.getValue(EAST)).booleanValue())
        {
            i |= getBoundingBoxIndex(EnumFacing.EAST);
        }

        if (((Boolean)state.getValue(SOUTH)).booleanValue())
        {
            i |= getBoundingBoxIndex(EnumFacing.SOUTH);
        }

        if (((Boolean)state.getValue(WEST)).booleanValue())
        {
            i |= getBoundingBoxIndex(EnumFacing.WEST);
        }

        return i;
    }
*/
    /**
     * Get the actual Block state of this Block at the given position. This applies properties not visible in the
     * metadata, such as fence connections.
     */
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return state.withProperty(NORTH_WALL, canWallConnectTo(worldIn, pos, EnumFacing.NORTH))
                    .withProperty(SOUTH_WALL, canWallConnectTo(worldIn, pos, EnumFacing.SOUTH))
                    .withProperty(WEST_WALL,  canWallConnectTo(worldIn, pos, EnumFacing.WEST))
                    .withProperty(EAST_WALL,  canWallConnectTo(worldIn, pos, EnumFacing.EAST))
                .withProperty(OVER_WALL,  canWallConnectTo(worldIn, pos, EnumFacing.UP))
                .withProperty(BENEATH_WALL,  canWallConnectTo(worldIn, pos, EnumFacing.DOWN))
                .withProperty(NORTH_THIS, canThisConnectTo(worldIn, pos, EnumFacing.NORTH))
                .withProperty(SOUTH_THIS, canThisConnectTo(worldIn, pos, EnumFacing.SOUTH))
                .withProperty(WEST_THIS,  canThisConnectTo(worldIn, pos, EnumFacing.WEST))
                .withProperty(EAST_THIS,  canThisConnectTo(worldIn, pos, EnumFacing.EAST))
                .withProperty(OVER_THIS,  canThisConnectTo(worldIn, pos, EnumFacing.UP))
                .withProperty(BENEATH_THIS,  canThisConnectTo(worldIn, pos, EnumFacing.DOWN));
    }



    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return blockAccess.getBlockState(pos.offset(side)).getBlock() == this ? false : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
    }

    public final boolean wallAttachesTo(IBlockAccess p_193393_1_, IBlockState state, BlockPos pos, EnumFacing facing)
    {
        Block block = state.getBlock();
        BlockFaceShape blockfaceshape = state.getBlockFaceShape(p_193393_1_, pos, facing);
        return !isExcepBlockForAttachWithPiston(block) && blockfaceshape == BlockFaceShape.SOLID || blockfaceshape == BlockFaceShape.MIDDLE_POLE_THIN;
    }
    public final boolean thisAttachesTo(IBlockAccess p_193393_1_, IBlockState state, BlockPos pos, EnumFacing facing)
    {
        Block block = state.getBlock();
        BlockFaceShape blockfaceshape = state.getBlockFaceShape(p_193393_1_, pos, facing);
        return (state.getBlock() instanceof ConnectedPost);
    }

    protected static boolean isExcepBlockForAttachWithPiston(Block p_193394_0_)
    {
        return p_193394_0_ instanceof BlockShulkerBox || p_193394_0_ instanceof BlockLeaves || p_193394_0_ == Blocks.BEACON || p_193394_0_ == Blocks.CAULDRON || p_193394_0_ == Blocks.GLOWSTONE || p_193394_0_ == Blocks.ICE || p_193394_0_ == Blocks.SEA_LANTERN || p_193394_0_ == Blocks.PISTON || p_193394_0_ == Blocks.STICKY_PISTON || p_193394_0_ == Blocks.PISTON_HEAD || p_193394_0_ == Blocks.MELON_BLOCK || p_193394_0_ == Blocks.PUMPKIN || p_193394_0_ == Blocks.LIT_PUMPKIN || p_193394_0_ == Blocks.BARRIER;
    }



    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return 0;
    }

    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
   public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        switch (rot)
        {
            case CLOCKWISE_180:
                return state.withProperty(NORTH_WALL, state.getValue(SOUTH_WALL)).withProperty(EAST_WALL, state.getValue(WEST_WALL)).withProperty(SOUTH_WALL, state.getValue(NORTH_WALL)).withProperty(WEST_WALL, state.getValue(EAST_WALL)).withProperty(NORTH_THIS, state.getValue(SOUTH_THIS)).withProperty(EAST_THIS, state.getValue(WEST_THIS)).withProperty(SOUTH_THIS, state.getValue(NORTH_THIS)).withProperty(WEST_THIS, state.getValue(EAST_THIS));
            case COUNTERCLOCKWISE_90:
                return state.withProperty(NORTH_WALL, state.getValue(SOUTH_WALL)).withProperty(EAST_WALL, state.getValue(WEST_WALL)).withProperty(SOUTH_WALL, state.getValue(NORTH_WALL)).withProperty(WEST_WALL, state.getValue(EAST_WALL)).withProperty(NORTH_THIS, state.getValue(SOUTH_THIS)).withProperty(EAST_THIS, state.getValue(WEST_THIS)).withProperty(SOUTH_THIS, state.getValue(NORTH_THIS)).withProperty(WEST_THIS, state.getValue(EAST_THIS));
            case CLOCKWISE_90:
                return state.withProperty(NORTH_WALL, state.getValue(SOUTH_WALL)).withProperty(EAST_WALL, state.getValue(WEST_WALL)).withProperty(SOUTH_WALL, state.getValue(NORTH_WALL)).withProperty(WEST_WALL, state.getValue(EAST_WALL)).withProperty(NORTH_THIS, state.getValue(SOUTH_THIS)).withProperty(EAST_THIS, state.getValue(WEST_THIS)).withProperty(SOUTH_THIS, state.getValue(NORTH_THIS)).withProperty(WEST_THIS, state.getValue(EAST_THIS));
            default:
                return state;
        }
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        switch (mirrorIn)
        {
            case LEFT_RIGHT:
                return state.withProperty(NORTH_WALL, state.getValue(SOUTH_WALL)).withProperty(SOUTH_WALL, state.getValue(NORTH_WALL)).withProperty(NORTH_THIS, state.getValue(SOUTH_THIS)).withProperty(SOUTH_THIS, state.getValue(NORTH_THIS));
            case FRONT_BACK:
                return state.withProperty(EAST_WALL, state.getValue(WEST_WALL)).withProperty(WEST_WALL, state.getValue(EAST_WALL)).withProperty(EAST_THIS, state.getValue(WEST_THIS)).withProperty(WEST_THIS, state.getValue(EAST_THIS));
            default:
                return super.withMirror(state, mirrorIn);
        }
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {NORTH_WALL, EAST_WALL, WEST_WALL ,SOUTH_WALL,OVER_WALL,BENEATH_WALL,NORTH_THIS,EAST_THIS,WEST_THIS,SOUTH_THIS,OVER_THIS,BENEATH_THIS});
    }

    /* ======================================== FORGE START ======================================== */

    @Override
    public boolean canBeConnectedTo(IBlockAccess world, BlockPos pos, EnumFacing facing)
    {
        BlockPos offset = pos.offset(facing);
        if(world.getBlockState(offset).getBlock() instanceof ConnectedPost) {
            return thisAttachesTo(world, world.getBlockState(offset), offset, facing.getOpposite());
        }
        return wallAttachesTo(world, world.getBlockState(offset), offset, facing.getOpposite());

    }

    public boolean canThisConnectTo(IBlockAccess world, BlockPos pos, EnumFacing dir)
    {
        BlockPos other = pos.offset(dir);
        IBlockState state = world.getBlockState(other);
        return  thisAttachesTo(world, state, other, dir.getOpposite());
    }
    public boolean canWallConnectTo(IBlockAccess world, BlockPos pos, EnumFacing dir)
    {
        BlockPos other = pos.offset(dir);
        IBlockState state = world.getBlockState(other);
        if(thisAttachesTo(world, state, other, dir.getOpposite())==true){
            return false;
        }
        return  wallAttachesTo(world, state, other, dir.getOpposite());
    }

    /* ======================================== FORGE END ======================================== */

    /**
     * Get the geometry of the queried face at the given position and state. This is used to decide whether things like
     * buttons are allowed to be placed on the face, or how glass panes connect to the face, among other things.
     * <p>
     * Common values are {@code SOLID}, which is the default, and {@code UNDEFINED}, which represents something that
     * does not fit the other descriptions and will generally cause other things not to connect to the face.
     * 
     * @return an approximation of the form of the given face
     */
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return face != EnumFacing.UP && face != EnumFacing.DOWN ? BlockFaceShape.MIDDLE_POLE_THIN : BlockFaceShape.CENTER_SMALL;
    }
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState();
    }
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return AABB_NULL;
    }

}
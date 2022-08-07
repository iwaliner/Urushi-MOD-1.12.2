package urushi.Block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
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

public class SlideMonoDoorBase extends Block {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyEnum<SlideMonoDoorBase.EnumTypeSlideDoorOpen> OPENCLOSE = PropertyEnum.<SlideMonoDoorBase.EnumTypeSlideDoorOpen>create("openclose", SlideMonoDoorBase.EnumTypeSlideDoorOpen.class);
     protected static final AxisAlignedBB AABB_CLOSED_NORTH = new AxisAlignedBB(0.0D, 0.0D, 0.0625D*7, 1.0D, 1D, 0.0625D*8);
    protected static final AxisAlignedBB AABB_CLOSED_SOUTH = new AxisAlignedBB(0.0D, 0.0D, 0.0625D*8, 1.0D, 1D, 0.0625D*9);
    protected static final AxisAlignedBB AABB_CLOSED_WEST = new AxisAlignedBB(0.0625D*7, 0.0D, 0D, 0.0625D*8, 1D, 1D);
    protected static final AxisAlignedBB AABB_CLOSED_EAST = new AxisAlignedBB(0.0625D*9, 0.0D, 0D, 0.0625D*9, 1D, 1D);
    protected static final AxisAlignedBB AABB_OPEN_NORTH = new AxisAlignedBB(0.0625D*13, 0.0D, 0.0625D*7, 1D, 1D, 0.0625D*8);
    protected static final AxisAlignedBB AABB_OPEN_SOUTH = new AxisAlignedBB(0D, 0.0D, 0.0625D*8, 0.0625D*3, 1D, 0.0625D*9);
    protected static final AxisAlignedBB AABB_OPEN_WEST = new AxisAlignedBB(0.0625D*7, 0.0D, 0D, 0.0625D*8, 1D, 0.0625D*3);
    protected static final AxisAlignedBB AABB_OPEN_EAST = new AxisAlignedBB(0.0625D*8, 0.0D, 0.0625D*13, 0.0625D*9, 1D, 1D);




BlockRenderLayer renderLayer;


    public SlideMonoDoorBase(BlockRenderLayer j) {
        super(Material.WOOD);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(OPENCLOSE, EnumTypeSlideDoorOpen.CLOSE));
        setResistance(10F);
        setLightOpacity(0);
        setLightLevel(0.0F);
        setHardness(0.3F);
        setHarvestLevel("axe", 0);
        setSoundType(SoundType.WOOD);
        setCreativeTab(ModCore_Urushi.TabUrushi);
renderLayer=j;
    }
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return renderLayer;
    }


    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        if(!placer.isSneaking()){
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());

        }else{
            return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());

        }
    }

    public EnumPushReaction getMobilityFlag(IBlockState state)
    {
        return EnumPushReaction.DESTROY;
    }

    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    /**
     * Determines if an entity can path through this block
     */
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
    {
        return worldIn.getBlockState(pos).getValue(OPENCLOSE)== EnumTypeSlideDoorOpen.OPEN?true:false;
    }
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        state = state.getActualState(source, pos);
        EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
        EnumTypeSlideDoorOpen openclose=state.getValue(OPENCLOSE);
       switch (enumfacing)
        {
            case EAST:
            default:
                return openclose== EnumTypeSlideDoorOpen.OPEN ? AABB_OPEN_EAST : AABB_CLOSED_EAST;
            case SOUTH:
                return openclose== EnumTypeSlideDoorOpen.OPEN ? AABB_OPEN_SOUTH : AABB_CLOSED_SOUTH;
            case WEST:
                return openclose== EnumTypeSlideDoorOpen.OPEN ? AABB_OPEN_WEST : AABB_CLOSED_WEST;
            case NORTH:
                return openclose== EnumTypeSlideDoorOpen.OPEN ? AABB_OPEN_NORTH : AABB_CLOSED_NORTH;
        }
    }

    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState)
    {
        if(state.getValue(OPENCLOSE)== EnumTypeSlideDoorOpen.OPEN) {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, Block.NULL_AABB);
        }else {
            if(state.getValue(FACING)==EnumFacing.NORTH) addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_CLOSED_NORTH);
            else  if(state.getValue(FACING)==EnumFacing.SOUTH) addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_CLOSED_SOUTH);
            else  if(state.getValue(FACING)==EnumFacing.WEST) addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_CLOSED_WEST);
            else addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_CLOSED_EAST);

        }
    }


    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    public int damageDropped(IBlockState state) {
        return 0;
    }




    public IBlockState getStateFromMeta(int meta) {
        if(meta<4) return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta%4)).withProperty(OPENCLOSE, EnumTypeSlideDoorOpen.CLOSE);
        else return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta%4)).withProperty(OPENCLOSE, EnumTypeSlideDoorOpen.OPEN);
    }

    public int getMetaFromState(IBlockState state) {
        return ((EnumFacing) state.getValue(FACING)).getHorizontalIndex()+4*((EnumTypeSlideDoorOpen) state.getValue(OPENCLOSE)).getMetadata();
    }


    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{FACING,OPENCLOSE});
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        EnumFacing blockFacing=state.getValue(FACING);

        if(state.getValue(OPENCLOSE)== EnumTypeSlideDoorOpen.CLOSE){
            worldIn.setBlockState(pos,this.getDefaultState().withProperty(FACING,blockFacing).withProperty(OPENCLOSE, EnumTypeSlideDoorOpen.OPEN));
            worldIn.playEvent(playerIn, 1007, pos, 0);


            return true;
        }else{
            worldIn.setBlockState(pos,this.getDefaultState().withProperty(FACING,blockFacing).withProperty(OPENCLOSE, EnumTypeSlideDoorOpen.CLOSE));
            worldIn.playEvent(playerIn, 1013, pos, 0);

            return true;
        }



    }



    public static enum EnumTypeSlideDoorOpen implements IStringSerializable
    {
        CLOSE(0, "close", MapColor.AIR),
        OPEN(1, "open", MapColor.AIR);

        private static final EnumTypeSlideDoorOpen[] META_LOOKUP = new EnumTypeSlideDoorOpen[values().length];
        private final int meta;
        private final String name;
        private final String unlocalizedName;
        /** The color that represents this entry on a map. */
        private final MapColor mapColor;

        private EnumTypeSlideDoorOpen(int metaIn, String nameIn, MapColor mapColorIn)
        {
            this(metaIn, nameIn, nameIn, mapColorIn);
        }

        private EnumTypeSlideDoorOpen(int metaIn, String nameIn, String unlocalizedNameIn, MapColor mapColorIn)
        {
            this.meta = metaIn;
            this.name = nameIn;
            this.unlocalizedName = unlocalizedNameIn;
            this.mapColor = mapColorIn;
        }

        public int getMetadata()
        {
            return this.meta;
        }

        /**
         * The color which represents this entry on a map.
         */
        public MapColor getMapColor()
        {
            return this.mapColor;
        }

        public String toString()
        {
            return this.name;
        }

        public static EnumTypeSlideDoorOpen byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        public String getName()
        {
            return this.name;
        }

        public String getUnlocalizedName()
        {
            return this.unlocalizedName;
        }

        static
        {
            for (EnumTypeSlideDoorOpen $enumtype : values())
            {
                META_LOOKUP[$enumtype.getMetadata()] = $enumtype;
            }
        }
    }

    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {

        EnumFacing enumfacing = (EnumFacing) state.getValue(FACING);


                boolean flag = worldIn.isBlockPowered(pos) ;
                if (flag) {
                    if (state.getValue(OPENCLOSE) == EnumTypeSlideDoorOpen.CLOSE) {
                        worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, enumfacing).withProperty(OPENCLOSE, EnumTypeSlideDoorOpen.OPEN));
                        worldIn.playEvent(null, 1007, pos, 0);
                     }
                } else if (blockIn.getDefaultState().canProvidePower() ) {
                    if (state.getValue(OPENCLOSE) == EnumTypeSlideDoorOpen.OPEN) {
                        worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, enumfacing).withProperty(OPENCLOSE, EnumTypeSlideDoorOpen.CLOSE));
                        worldIn.playEvent(null, 1013, pos, 0);
                    }


        }
    }
}

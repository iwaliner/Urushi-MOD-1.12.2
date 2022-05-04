package urushi.Block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
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

public class SlideDoorBase extends Block {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyEnum<SlideDoorBase.EnumTypeSlideDoorOpen> OPENCLOSE = PropertyEnum.<SlideDoorBase.EnumTypeSlideDoorOpen>create("openclose", SlideDoorBase.EnumTypeSlideDoorOpen.class);
    public static final PropertyEnum<SlideDoorBase.EnumTypeSlideDoorUnderUpper> UNDERUPPER = PropertyEnum.<SlideDoorBase.EnumTypeSlideDoorUnderUpper>create("underupper", SlideDoorBase.EnumTypeSlideDoorUnderUpper.class);
     protected static final AxisAlignedBB AABB_CLOSED_NORTH = new AxisAlignedBB(0.0D, 0.0D, 0.0625D*7, 1.0D, 1D, 0.0625D*8);
    protected static final AxisAlignedBB AABB_CLOSED_SOUTH = new AxisAlignedBB(0.0D, 0.0D, 0.0625D*8, 1.0D, 1D, 0.0625D*9);
    protected static final AxisAlignedBB AABB_CLOSED_WEST = new AxisAlignedBB(0.0625D*7, 0.0D, 0D, 0.0625D*8, 1D, 1D);
    protected static final AxisAlignedBB AABB_CLOSED_EAST = new AxisAlignedBB(0.0625D*9, 0.0D, 0D, 0.0625D*9, 1D, 1D);
    protected static final AxisAlignedBB AABB_OPEN_NORTH = new AxisAlignedBB(0.0625D*13, 0.0D, 0.0625D*7, 1D, 1D, 0.0625D*8);
    protected static final AxisAlignedBB AABB_OPEN_SOUTH = new AxisAlignedBB(0D, 0.0D, 0.0625D*8, 0.0625D*3, 1D, 0.0625D*9);
    protected static final AxisAlignedBB AABB_OPEN_WEST = new AxisAlignedBB(0.0625D*7, 0.0D, 0D, 0.0625D*8, 1D, 0.0625D*3);
    protected static final AxisAlignedBB AABB_OPEN_EAST = new AxisAlignedBB(0.0625D*8, 0.0D, 0.0625D*13, 0.0625D*9, 1D, 1D);



    int meta;
BlockRenderLayer renderLayer;


    public SlideDoorBase(int i,BlockRenderLayer j) {
        super(Material.WOOD);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(OPENCLOSE, EnumTypeSlideDoorOpen.CLOSE).withProperty(UNDERUPPER, EnumTypeSlideDoorUnderUpper.UNDER));
        setResistance(10F);
        setLightOpacity(0);
        setLightLevel(0.0F);
        setHardness(0.3F);
        setHarvestLevel("axe", 0);
        setSoundType(SoundType.WOOD);
        meta=i;
        renderLayer=j;
    }
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return renderLayer;
    }
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {

        EnumFacing enumfacing = (EnumFacing) state.getValue(FACING);
        EnumTypeSlideDoorUnderUpper underUpper=(EnumTypeSlideDoorUnderUpper) state.getValue(UNDERUPPER);

        if (state.getValue(UNDERUPPER) == EnumTypeSlideDoorUnderUpper.UPPER)
        {
            BlockPos blockpos = pos.down();
            IBlockState iblockstate = worldIn.getBlockState(blockpos);

            if (iblockstate.getBlock() != this)
            {
                worldIn.setBlockToAir(pos);
            }
            else if (blockIn != this)
            {
                iblockstate.neighborChanged(worldIn, blockpos, blockIn, fromPos);
            }
        }
        else
        {
            boolean flag1 = false;
            BlockPos blockpos1 = pos.up();
            IBlockState iblockstate1 = worldIn.getBlockState(blockpos1);

            if (iblockstate1.getBlock() != this)
            {
                worldIn.setBlockToAir(pos);
                flag1 = true;
            }

            if (!worldIn.getBlockState(pos.down()).isSideSolid(worldIn,  pos.down(), EnumFacing.UP))
            {
                worldIn.setBlockToAir(pos);
                flag1 = true;

                if (iblockstate1.getBlock() == this)
                {
                    worldIn.setBlockToAir(blockpos1);
                }
            }

            if (flag1)
            {
                if (!worldIn.isRemote)
                {
                    this.dropBlockAsItem(worldIn, pos, state, 0);
                }
            }
            else {


                BlockPos pos1;
                if (state.getValue(UNDERUPPER) == EnumTypeSlideDoorUnderUpper.UNDER) {
                    pos1 = pos.up();
                } else {
                    pos1 = pos.down();
                }
                EnumTypeSlideDoorUnderUpper underUpper2 = (EnumTypeSlideDoorUnderUpper) worldIn.getBlockState(pos1).getValue(UNDERUPPER);


                boolean flag = worldIn.isBlockPowered(pos) || worldIn.isBlockPowered(blockpos1);
                if (flag) {
                    if (state.getValue(OPENCLOSE) == EnumTypeSlideDoorOpen.CLOSE) {
                        worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, enumfacing).withProperty(OPENCLOSE, EnumTypeSlideDoorOpen.OPEN).withProperty(UNDERUPPER, underUpper));
                        worldIn.setBlockState(pos1, this.getDefaultState().withProperty(FACING, enumfacing).withProperty(OPENCLOSE, EnumTypeSlideDoorOpen.OPEN).withProperty(UNDERUPPER, underUpper2));
                        worldIn.playEvent(null, 1007, pos, 0);
                    }
                } else if (blockIn.getDefaultState().canProvidePower() || worldIn.getBlockState(blockpos1).getBlock().getDefaultState().canProvidePower()) {
                    if (state.getValue(OPENCLOSE) == EnumTypeSlideDoorOpen.OPEN) {
                        worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, enumfacing).withProperty(OPENCLOSE, EnumTypeSlideDoorOpen.CLOSE).withProperty(UNDERUPPER, underUpper));
                        worldIn.setBlockState(pos1, this.getDefaultState().withProperty(FACING, enumfacing).withProperty(OPENCLOSE, EnumTypeSlideDoorOpen.CLOSE).withProperty(UNDERUPPER, underUpper2));
                        worldIn.playEvent(null, 1013, pos, 0);
                    }
                }
            }
            }
    }


    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        BlockPos blockpos = pos.down();
        BlockPos blockpos1 = pos.up();

        if (player.capabilities.isCreativeMode && state.getValue(UNDERUPPER) == EnumTypeSlideDoorUnderUpper.UPPER && worldIn.getBlockState(blockpos).getBlock() == this)
        {
            worldIn.setBlockToAir(blockpos);
        }

        if (state.getValue(UNDERUPPER) == EnumTypeSlideDoorUnderUpper.UNDER && worldIn.getBlockState(blockpos1).getBlock() == this)
        {
            if (player.capabilities.isCreativeMode)
            {
                worldIn.setBlockToAir(pos);
            }

            worldIn.setBlockToAir(blockpos1);
        }
    }

   public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
   {
       if (pos.getY() >= worldIn.getHeight() - 1)
       {
           return false;
       }
       else
       {
           IBlockState state = worldIn.getBlockState(pos.down());
           return (state.isTopSolid() || state.getBlockFaceShape(worldIn, pos.down(), EnumFacing.UP) == BlockFaceShape.SOLID) && super.canPlaceBlockAt(worldIn, pos) && super.canPlaceBlockAt(worldIn, pos.up());
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
        return worldIn.getBlockState(pos).getValue(OPENCLOSE)==EnumTypeSlideDoorOpen.OPEN?true:false;
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
                return openclose==EnumTypeSlideDoorOpen.OPEN ? AABB_OPEN_EAST : AABB_CLOSED_EAST;
            case SOUTH:
                return openclose==EnumTypeSlideDoorOpen.OPEN ? AABB_OPEN_SOUTH : AABB_CLOSED_SOUTH;
            case WEST:
                return openclose==EnumTypeSlideDoorOpen.OPEN ? AABB_OPEN_WEST : AABB_CLOSED_WEST;
            case NORTH:
                return openclose==EnumTypeSlideDoorOpen.OPEN ? AABB_OPEN_NORTH : AABB_CLOSED_NORTH;
        }
    }

    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState)
    {
        if(state.getValue(OPENCLOSE)==EnumTypeSlideDoorOpen.OPEN) {
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
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
         return new ItemStack(ModCore_Urushi.UItems, 1,meta);
    }
    public int damageDropped(IBlockState state) {
        return meta;
    }
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return state.getValue(UNDERUPPER) == EnumTypeSlideDoorUnderUpper.UPPER ? Items.AIR : ModCore_Urushi.UItems;
    }



    public IBlockState getStateFromMeta(int meta) {
        if(meta<4) return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta%4)).withProperty(OPENCLOSE,EnumTypeSlideDoorOpen.CLOSE).withProperty(UNDERUPPER,EnumTypeSlideDoorUnderUpper.UNDER);
        else if(meta<8) return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta%4)).withProperty(OPENCLOSE,EnumTypeSlideDoorOpen.OPEN).withProperty(UNDERUPPER,EnumTypeSlideDoorUnderUpper.UNDER);
        else if(meta<12) return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta%4)).withProperty(OPENCLOSE,EnumTypeSlideDoorOpen.CLOSE).withProperty(UNDERUPPER,EnumTypeSlideDoorUnderUpper.UPPER);
        else return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta%4)).withProperty(OPENCLOSE,EnumTypeSlideDoorOpen.OPEN).withProperty(UNDERUPPER,EnumTypeSlideDoorUnderUpper.UPPER);
    }

    public int getMetaFromState(IBlockState state) {
        return ((EnumFacing) state.getValue(FACING)).getHorizontalIndex()+4*((EnumTypeSlideDoorOpen) state.getValue(OPENCLOSE)).getMetadata()+8*((EnumTypeSlideDoorUnderUpper) state.getValue(UNDERUPPER)).getMetadata();
    }


    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{FACING,OPENCLOSE,UNDERUPPER});
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        EnumFacing blockFacing=state.getValue(FACING);
        EnumTypeSlideDoorUnderUpper underUpper=state.getValue(UNDERUPPER);

        if(state.getValue(OPENCLOSE)==EnumTypeSlideDoorOpen.CLOSE){
            worldIn.setBlockState(pos,this.getDefaultState().withProperty(FACING,blockFacing).withProperty(OPENCLOSE,EnumTypeSlideDoorOpen.OPEN).withProperty(UNDERUPPER,underUpper));
            worldIn.playEvent(playerIn, 1007, pos, 0);
            if(underUpper==EnumTypeSlideDoorUnderUpper.UPPER){ worldIn.setBlockState(pos.add(0,-1,0),this.getDefaultState().withProperty(FACING,blockFacing).withProperty(OPENCLOSE,EnumTypeSlideDoorOpen.OPEN).withProperty(UNDERUPPER,EnumTypeSlideDoorUnderUpper.UNDER));
            }else{
                worldIn.setBlockState(pos.add(0,1,0),this.getDefaultState().withProperty(FACING,blockFacing).withProperty(OPENCLOSE,EnumTypeSlideDoorOpen.OPEN).withProperty(UNDERUPPER,EnumTypeSlideDoorUnderUpper.UPPER));

            }
            return true;
        }else{
            worldIn.setBlockState(pos,this.getDefaultState().withProperty(FACING,blockFacing).withProperty(OPENCLOSE,EnumTypeSlideDoorOpen.CLOSE).withProperty(UNDERUPPER,underUpper));
            worldIn.playEvent(playerIn, 1013, pos, 0);
            if(underUpper==EnumTypeSlideDoorUnderUpper.UPPER){ worldIn.setBlockState(pos.add(0,-1,0),this.getDefaultState().withProperty(FACING,blockFacing).withProperty(OPENCLOSE,EnumTypeSlideDoorOpen.CLOSE).withProperty(UNDERUPPER,EnumTypeSlideDoorUnderUpper.UNDER));
            }else{
                worldIn.setBlockState(pos.add(0,1,0),this.getDefaultState().withProperty(FACING,blockFacing).withProperty(OPENCLOSE,EnumTypeSlideDoorOpen.CLOSE).withProperty(UNDERUPPER,EnumTypeSlideDoorUnderUpper.UPPER));

            }
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
    public static enum EnumTypeSlideDoorUnderUpper implements IStringSerializable
    {
        UNDER(0, "under", MapColor.AIR),
        UPPER(1, "upper", MapColor.AIR);

        private static final EnumTypeSlideDoorUnderUpper[] META_LOOKUP = new EnumTypeSlideDoorUnderUpper[values().length];
        private final int meta;
        private final String name;
        private final String unlocalizedName;
        /** The color that represents this entry on a map. */
        private final MapColor mapColor;

        private EnumTypeSlideDoorUnderUpper(int metaIn, String nameIn, MapColor mapColorIn)
        {
            this(metaIn, nameIn, nameIn, mapColorIn);
        }

        private EnumTypeSlideDoorUnderUpper(int metaIn, String nameIn, String unlocalizedNameIn, MapColor mapColorIn)
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

        public static EnumTypeSlideDoorUnderUpper byMetadata(int meta)
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
            for (EnumTypeSlideDoorUnderUpper $enumtype : values())
            {
                META_LOOKUP[$enumtype.getMetadata()] = $enumtype;
            }
        }
    }

}

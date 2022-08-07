package urushi.Block;



import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemLeaves;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import urushi.ModCore_Urushi;
import urushi.TileEntity.TileEntityFermentationPot;


import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;


public class FermentationPot extends BlockContainer  {
    public static final PropertyEnum<FermentationPot.EnumType> VARIANT = PropertyEnum.<FermentationPot.EnumType>create("variant", FermentationPot.EnumType.class);
    protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.0625D*4, 0.0D, 0.0625D*4, 0.0625D*12, 0.0625D*10, 0.0625D*12);

    public FermentationPot() {
        super(Material.ROCK);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.Empty));
        this.setCreativeTab(ModCore_Urushi.TabUrushi);
        setResistance(10F);
        setLightOpacity(0);
        setLightLevel(0.0F);
        setHardness(0.5F);
        setHarvestLevel("pickaxe", 0);
        setSoundType(SoundType.STONE);
    }


    /**
     * Get the MapColor for this Block and the given BlockState
     */
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return ((FermentationPot.EnumType) state.getValue(VARIANT)).getMapColor();
    }


    /**
     * Gets the metadata of the item this Block can drop. This method is called when the block gets destroyed. It
     * returns the metadata of the dropped item based on the old metadata of the block.
     */
    public int damageDropped(IBlockState state) {
        return 0;
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */


    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, FermentationPot.EnumType.byMetadata(meta));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state) {
        return ((FermentationPot.EnumType) state.getValue(VARIANT)).getMetadata();
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{VARIANT});
    }








    public static enum EnumType implements IStringSerializable {
        Empty(0, MapColor.BROWN, "empty"),
        Leaves(1, MapColor.BROWN, "leaves"),
        Dirt(2, MapColor.BROWN, "dirt"),
        Rice(3, MapColor.BROWN, "rice"),
        Meat(4, MapColor.BROWN, "meat"),
        RottenMeat(5, MapColor.BROWN, "rotten_meat"),
        RiceMalt(6, MapColor.BROWN, "rice_malt"),
        MaturedApricot(7, MapColor.BROWN, "matured_apricot"),
        PickledApricot(8, MapColor.BROWN, "pickled_apricot")
        ;

        /**
         * Array of the Block's BlockStates
         */
        private static final FermentationPot.EnumType[] META_LOOKUP = new FermentationPot.EnumType[values().length];
        /**
         * The BlockState's metadata.
         */
        private final int meta;
        /**
         * The EnumType's name.
         */
        private final String name;
        private final String unlocalizedName;
        private final MapColor mapColor;


        private EnumType(int p_i46383_3_, MapColor p_i46383_4_, String p_i46383_5_) {
            this(p_i46383_3_, p_i46383_4_, p_i46383_5_, p_i46383_5_);
        }

        private EnumType(int p_i46384_3_, MapColor p_i46384_4_, String p_i46384_5_, String p_i46384_6_) {
            this.meta = p_i46384_3_;
            this.name = p_i46384_5_;
            this.unlocalizedName = p_i46384_6_;
            this.mapColor = p_i46384_4_;

        }

        /**
         * Returns the EnumType's metadata value.
         */
        public int getMetadata() {
            return this.meta;
        }

        public MapColor getMapColor() {
            return this.mapColor;
        }

        public String toString() {
            return this.name;
        }

        /**
         * Returns an EnumType for the BlockState from a metadata value.
         */
        public static FermentationPot.EnumType byMetadata(int meta) {
            if (meta < 0 || meta >= META_LOOKUP.length) {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        public String getName() {
            return this.name;
        }

        public String getUnlocalizedName() {
            return this.unlocalizedName;
        }


        static {
            for (FermentationPot.EnumType uplanks$enumtype : values()) {
                META_LOOKUP[uplanks$enumtype.getMetadata()] = uplanks$enumtype;
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        ITextComponent textComponentA = new TextComponentTranslation("item.info.pot1", new Object[0]);
        ITextComponent textComponentB = new TextComponentTranslation("item.info.pot2", new Object[0]);
        ITextComponent textComponentC = new TextComponentTranslation("item.info.pot3", new Object[0]);
        ITextComponent textComponentD = new TextComponentTranslation("item.info.pot4", new Object[0]);
        tooltip.add(textComponentA.getFormattedText());
        tooltip.add(textComponentB.getFormattedText());
        tooltip.add(textComponentC.getFormattedText());
        tooltip.add(textComponentD.getFormattedText());


    }
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return AABB;
    }
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return AABB;
    }

    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
   @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
       ItemStack itemStack = playerIn.getHeldItem(hand);
       int blockmeta = state.getValue(VARIANT).getMetadata();
if(worldIn.getTileEntity(pos) instanceof  TileEntityFermentationPot) {
    //  TileEntityFermentationPot tileEntity = (TileEntityFermentationPot) worldIn.getTileEntity(pos);





    if (((FermentationPot.EnumType) worldIn.getBlockState(pos).getValue(VARIANT)) == EnumType.Empty) {
        if (itemStack.getItem() instanceof ItemLeaves || itemStack.getItem() == Item.getItemFromBlock(ModCore_Urushi.ULeaves)|| itemStack.getItem() == Item.getItemFromBlock(ModCore_Urushi.ULeaves2)) {
            worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, EnumType.Leaves));
            TileEntityFermentationPot tileEntity = (TileEntityFermentationPot) worldIn.getTileEntity(pos);
            tileEntity.itemAmount = itemStack.getCount();
            playerIn.setHeldItem(hand,ItemStack.EMPTY);
            worldIn.playSound((EntityPlayer) null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1.0F, 1.0F);

            return true;

        } else if (itemStack.getItem() == Items.BEEF||itemStack.getItem() == Items.PORKCHOP||itemStack.getItem() == Items.CHICKEN||itemStack.getItem() == Items.MUTTON||itemStack.getItem() == Items.RABBIT) {
            worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, EnumType.Meat));
            TileEntityFermentationPot tileEntity = (TileEntityFermentationPot) worldIn.getTileEntity(pos);
            tileEntity.itemAmount = itemStack.getCount();
            playerIn.setHeldItem(hand,ItemStack.EMPTY);
            worldIn.playSound((EntityPlayer) null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1.0F, 1.0F);

            return true;
        }else if (itemStack.getItem()  == ModCore_Urushi.Rice) {
            worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, EnumType.Rice));
            TileEntityFermentationPot tileEntity = (TileEntityFermentationPot) worldIn.getTileEntity(pos);
            tileEntity.itemAmount = itemStack.getCount();
            playerIn.setHeldItem(hand,ItemStack.EMPTY);
            worldIn.playSound((EntityPlayer) null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1.0F, 1.0F);

            return true;

        }else if (itemStack.getItem()  == ModCore_Urushi.MaturedApricot) {
            worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, EnumType.MaturedApricot));
            TileEntityFermentationPot tileEntity = (TileEntityFermentationPot) worldIn.getTileEntity(pos);
            tileEntity.itemAmount = itemStack.getCount();
            playerIn.setHeldItem(hand,ItemStack.EMPTY);
            worldIn.playSound((EntityPlayer) null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1.0F, 1.0F);

            return true;

        }
    } else if (((FermentationPot.EnumType) worldIn.getBlockState(pos).getValue(VARIANT)) == EnumType.Dirt) {
        TileEntityFermentationPot tileEntity = (TileEntityFermentationPot) worldIn.getTileEntity(pos);
        if (!worldIn.isRemote) {
            EntityItem entityItem = new EntityItem(worldIn, (double) pos.getX(), (double) pos.getY() + 1, (double) pos.getZ());
            entityItem.setItem(new ItemStack(Blocks.DIRT, tileEntity.itemAmount, 0));
            worldIn.spawnEntity(entityItem);
            worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, EnumType.Empty));

        }
        //worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, EnumType.Empty));
        worldIn.playSound((EntityPlayer) null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1.0F, 1.0F);

        return true;
    } else if (((FermentationPot.EnumType) worldIn.getBlockState(pos).getValue(VARIANT)) == EnumType.RottenMeat) {
        TileEntityFermentationPot tileEntity = (TileEntityFermentationPot) worldIn.getTileEntity(pos);

        if (!worldIn.isRemote) {
            EntityItem entityItem = new EntityItem(worldIn, (double) pos.getX(), (double) pos.getY() + 1, (double) pos.getZ());

            entityItem.setItem(new ItemStack(Items.ROTTEN_FLESH, tileEntity.itemAmount, 0));
            worldIn.spawnEntity(entityItem);
            worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, EnumType.Empty));

        }
       // worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, EnumType.Empty));
        worldIn.playSound((EntityPlayer) null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1.0F, 1.0F);

        return true;
    }else if (((FermentationPot.EnumType) worldIn.getBlockState(pos).getValue(VARIANT)) == EnumType.RiceMalt) {
        TileEntityFermentationPot tileEntity = (TileEntityFermentationPot) worldIn.getTileEntity(pos);

        if (!worldIn.isRemote) {
            EntityItem entityItem = new EntityItem(worldIn, (double) pos.getX(), (double) pos.getY() + 1, (double) pos.getZ());

            entityItem.setItem(new ItemStack(ModCore_Urushi.UItems, tileEntity.itemAmount, 34));
            worldIn.spawnEntity(entityItem);
            worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, EnumType.Empty));

        }
        //worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, EnumType.Empty));
        worldIn.playSound((EntityPlayer) null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1.0F, 1.0F);

        return true;
    }else if (((FermentationPot.EnumType) worldIn.getBlockState(pos).getValue(VARIANT)) == EnumType.PickledApricot) {
        TileEntityFermentationPot tileEntity = (TileEntityFermentationPot) worldIn.getTileEntity(pos);

        if (!worldIn.isRemote) {
            EntityItem entityItem = new EntityItem(worldIn, (double) pos.getX(), (double) pos.getY() + 1, (double) pos.getZ());

            entityItem.setItem(new ItemStack(ModCore_Urushi.PickledApricot, tileEntity.itemAmount, 0));
            worldIn.spawnEntity(entityItem);
            worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, EnumType.Empty));

        }
        //worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, EnumType.Empty));
        worldIn.playSound((EntityPlayer) null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1.0F, 1.0F);

        return true;
    }

}
           return false;

   }
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        if (stateIn.getValue(VARIANT)== EnumType.Dirt||stateIn.getValue(VARIANT)== EnumType.RottenMeat||stateIn.getValue(VARIANT)== EnumType.RiceMalt||stateIn.getValue(VARIANT)== EnumType.PickledApricot) {

            worldIn.spawnParticle(EnumParticleTypes.SPELL, (double) ((float) pos.getX() + 0.5F), (double) ((float) pos.getY() + 0.8F), (double) ((float) pos.getZ() + 0.5F), 0.0D, 0.1D, 0.0D);
        }
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityFermentationPot();
    }
}

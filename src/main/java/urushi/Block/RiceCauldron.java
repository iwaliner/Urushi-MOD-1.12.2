package urushi.Block;



import net.minecraft.block.BlockContainer;
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
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import urushi.ModCore_Urushi;
import urushi.TileEntity.TileEntityRiceCauldron;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;


public class RiceCauldron extends BlockContainer  {
    public static final PropertyEnum<RiceCauldron.EnumType> VARIANT = PropertyEnum.<RiceCauldron.EnumType>create("variant", RiceCauldron.EnumType.class);
    protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.0625D*2, 0.0D, 0.0625D*2, 1D-0.0625D*2, 0.0625D*5, 1D-0.0625D*2);

    public RiceCauldron() {
        super(Material.IRON);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.ClosedEmpty));
        this.setCreativeTab(ModCore_Urushi.TabUrushi);
        setResistance(10F);
        setLightOpacity(0);
        setLightLevel(0.0F);
        setHardness(0.5F);
        setHarvestLevel("pickaxe", 0);
        setSoundType(SoundType.METAL);
    }


    /**
     * Get the MapColor for this Block and the given BlockState
     */
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return ((RiceCauldron.EnumType) state.getValue(VARIANT)).getMapColor();
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
        return this.getDefaultState().withProperty(VARIANT, RiceCauldron.EnumType.byMetadata(meta));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state) {
        return ((RiceCauldron.EnumType) state.getValue(VARIANT)).getMetadata();
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{VARIANT});
    }








    public static enum EnumType implements IStringSerializable {
        ClosedEmpty(0, MapColor.STONE, "closed_empty"),
        Empty(1, MapColor.STONE, "empty"),
        ClosedRawRice1(2, MapColor.STONE, "closed_raw_rice1"),
        ClosedRice1(3, MapColor.STONE, "closed_rice1"),
        Rice1(4, MapColor.STONE, "rice1"),
        ClosedRawRice16(5, MapColor.STONE, "closed_raw_rice16"),
        ClosedRice16(6, MapColor.STONE, "closed_rice16"),
        Rice16(7, MapColor.STONE, "rice16"),
        ClosedRawRice32(8, MapColor.STONE, "closed_raw_rice32"),
        ClosedRice32(9, MapColor.STONE, "closed_rice32"),
        Rice32(10, MapColor.STONE, "rice32"),
        ClosedRawRice64(11, MapColor.STONE, "closed_raw_rice64"),
        ClosedRice64(12, MapColor.STONE, "closed_rice64"),
        Rice64(13, MapColor.STONE, "rice64")
        ;

        /**
         * Array of the Block's BlockStates
         */
        private static final RiceCauldron.EnumType[] META_LOOKUP = new RiceCauldron.EnumType[values().length];
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
        public static RiceCauldron.EnumType byMetadata(int meta) {
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
            for (RiceCauldron.EnumType uplanks$enumtype : values()) {
                META_LOOKUP[uplanks$enumtype.getMetadata()] = uplanks$enumtype;
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        int metadata = stack.getItemDamage();
        ITextComponent textComponentA = new TextComponentTranslation("item.info.ricecauldron", new Object[0]);
   tooltip.add(textComponentA.getFormattedText());
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
           ItemStack itemStack =playerIn.getHeldItem(hand);
            int blockmeta=state.getValue(VARIANT).getMetadata();
       if(worldIn.getTileEntity(pos) instanceof TileEntityRiceCauldron) {
           if (itemStack.getItem() == ModCore_Urushi.UItems && blockmeta == 1 && itemStack.getItemDamage() == 5) {
               worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, EnumType.ClosedRawRice1));
               TileEntityRiceCauldron tileEntityRiceCauldron= (TileEntityRiceCauldron) worldIn.getTileEntity(pos);
               tileEntityRiceCauldron.itemAmount = itemStack.getCount();
               playerIn.setHeldItem(hand,ItemStack.EMPTY);
               worldIn.playSound((EntityPlayer) null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1.0F, 1.0F);

               return true;

           } else if (blockmeta == 4) {
               TileEntityRiceCauldron tileEntityRiceCauldron= (TileEntityRiceCauldron) worldIn.getTileEntity(pos);

               if (!worldIn.isRemote) {
                   EntityItem entityItem = new EntityItem(worldIn, (double) pos.getX(), (double) pos.getY() + 1, (double) pos.getZ());
                   entityItem.setItem(new ItemStack(ModCore_Urushi.Rice, tileEntityRiceCauldron.itemAmount, 0));
                   worldIn.spawnEntity(entityItem);
                   worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, EnumType.Empty));

               }
              worldIn.playSound((EntityPlayer) null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1.0F, 1.0F);

               return true;
           } else if (blockmeta == 0) {
               worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, EnumType.Empty));
               return true;
           } else if (blockmeta == 1) {
               worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, EnumType.ClosedEmpty));
               return true;
           } else if (blockmeta == 3) {
               TileEntityRiceCauldron tileEntityRiceCauldron= (TileEntityRiceCauldron) worldIn.getTileEntity(pos);

               worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, EnumType.Rice1));
               if(worldIn.getTileEntity(pos) instanceof  TileEntityRiceCauldron) {
                   TileEntityRiceCauldron tileEntity = (TileEntityRiceCauldron) worldIn.getTileEntity(pos);
                   tileEntity.itemAmount = tileEntityRiceCauldron.itemAmount;
               }
               return true;
           } else if (blockmeta == 4) {
               worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, EnumType.ClosedRice1));
               return true;
           } else if (blockmeta == 6) {
               worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, EnumType.Rice16));
               return true;
           } else if (blockmeta == 7) {
               worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, EnumType.ClosedRice16));
               return true;
           } else if (blockmeta == 9) {
               worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, EnumType.Rice32));
               return true;
           } else if (blockmeta == 10) {
               worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, EnumType.ClosedRice32));
               return true;
           } else if (blockmeta == 12) {
               worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, EnumType.Rice64));
               return true;
           } else if (blockmeta == 13) {
               worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, EnumType.ClosedRice64));
               return true;
           }

       }
        return false;
    }
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        if ((stateIn.getValue(VARIANT)== EnumType.ClosedRice1||stateIn.getValue(VARIANT)== EnumType.ClosedRice16)||(stateIn.getValue(VARIANT)== EnumType.ClosedRice32||stateIn.getValue(VARIANT)== EnumType.ClosedRice64)||(stateIn.getValue(VARIANT)== EnumType.Rice1||stateIn.getValue(VARIANT)== EnumType.Rice16)||(stateIn.getValue(VARIANT)== EnumType.Rice32||stateIn.getValue(VARIANT)== EnumType.Rice64)) {

            worldIn.spawnParticle(EnumParticleTypes.CLOUD, (double) ((float) pos.getX() + 0.5F), (double) ((float) pos.getY() + 0.8F), (double) ((float) pos.getZ() + 0.5F), 0.0D, 0.1D, 0.0D);
        }
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityRiceCauldron();
    }

}

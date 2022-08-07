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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import urushi.ModCore_Urushi;
import urushi.TileEntity.TileEntityKama;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;


public class Kama extends BlockContainer  {
    public static final PropertyEnum<Kama.EnumType> VARIANT = PropertyEnum.<Kama.EnumType>create("variant", Kama.EnumType.class);
    protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.0625D*2, 0.0D, 0.0625D*2, 1D-0.0625D*2, 0.0625D*5, 1D-0.0625D*2);

    public Kama() {
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
     * Gets the localized name of this block. Used for the statistics page.
     */
    public String getLocalizedName() {
        return I18n.translateToLocal(this.getUnlocalizedName() + "." + EnumType.ClosedEmpty.getUnlocalizedName() + ".name");
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return ((Kama.EnumType) state.getValue(VARIANT)).getMapColor();
    }


    /**
     * Gets the metadata of the item this Block can drop. This method is called when the block gets destroyed. It
     * returns the metadata of the dropped item based on the old metadata of the block.
     */
    public int damageDropped(IBlockState state) {
        return ((Kama.EnumType) state.getValue(VARIANT)).getMetadata();
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */


    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, Kama.EnumType.byMetadata(meta));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state) {
        return ((Kama.EnumType) state.getValue(VARIANT)).getMetadata();
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{VARIANT});
    }







    public static enum EnumType implements IStringSerializable {
        ClosedEmpty(0, MapColor.STONE, "closed_empty"),
        ClosedRawRice(1, MapColor.STONE, "closed_raw_rice"),
        ClosedRice(2, MapColor.STONE, "closed_rice"),
        Empty(3, MapColor.STONE, "empty"),
        RawRice(4, MapColor.STONE, "raw_rice"),
        Rice(5, MapColor.STONE, "rice");

        /**
         * Array of the Block's BlockStates
         */
        private static final Kama.EnumType[] META_LOOKUP = new Kama.EnumType[values().length];
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
        public static Kama.EnumType byMetadata(int meta) {
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
            for (Kama.EnumType uplanks$enumtype : values()) {
                META_LOOKUP[uplanks$enumtype.getMetadata()] = uplanks$enumtype;
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        int metadata = stack.getItemDamage();
        ITextComponent textComponent6 = new TextComponentTranslation("item.info.redplanks", new Object[0]);
        ITextComponent textComponent7 = new TextComponentTranslation("item.info.smoothredplanks", new Object[0]);
        ITextComponent textComponent8 = new TextComponentTranslation("item.info.blackplanks", new Object[0]);
        ITextComponent textComponent9 = new TextComponentTranslation("item.info.smoothblackplanks", new Object[0]);
        if (metadata == 6) tooltip.add(textComponent6.getFormattedText());
        else if (metadata == 7) tooltip.add(textComponent7.getFormattedText());
        else if (metadata == 8) tooltip.add(textComponent8.getFormattedText());
        else if (metadata == 9) tooltip.add(textComponent9.getFormattedText());
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

                TileEntityKama tileEntity = (TileEntityKama) worldIn.getTileEntity(pos);
                if (itemStack.getItem() == ModCore_Urushi.UItems && blockmeta == 3 && itemStack.getItemDamage() == 5) {
                    //   tileEntity.setItemAmount(itemStack.getCount());
                   // tileEntity.stacks.set(2, itemStack);
                    tileEntity.itemAmount=itemStack.getCount();
                    playerIn.setHeldItem(hand, ItemStack.EMPTY);
                    worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, EnumType.RawRice));
                    worldIn.playSound((EntityPlayer) null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1.0F, 1.0F);

                    return true;
                } else if (blockmeta == 5&&itemStack==ItemStack.EMPTY) {
                   // playerIn.setHeldItem(hand, new ItemStack(ModCore_Urushi.UItems,64,6));
                    playerIn.setHeldItem(hand, new ItemStack(ModCore_Urushi.UItems,tileEntity.itemAmount,6));

                    //  playerIn.setHeldItem(hand,new ItemStack(ModCore_Urushi.UItems,tileEntity.getItemAmount(),6));
                    // tileEntity.setItemAmount(0);
                    //tileEntity.stacks.set(1, ItemStack.EMPTY);
                    worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, EnumType.Empty));
                    worldIn.playSound((EntityPlayer) null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1.0F, 1.0F);

                    return true;
                } else if (blockmeta == 0) {
                    worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, EnumType.Empty));
                    return true;
                } else if (blockmeta == 1) {
                    worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, EnumType.RawRice));
                    return true;
                } else if (blockmeta == 2) {
                    worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, EnumType.Rice));
                    return true;
                } else if (blockmeta == 3) {
                    worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, EnumType.ClosedEmpty));
                    return true;
                } else if (blockmeta == 4) {
                    worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, EnumType.ClosedRawRice));
                    return true;
                }
                else if (blockmeta == 5) {
                    worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, EnumType.ClosedRice));
                    return true;
                }

        return false;
    }
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        if ((stateIn.getValue(VARIANT)== EnumType.ClosedRice||stateIn.getValue(VARIANT)== EnumType.Rice)) {

            worldIn.spawnParticle(EnumParticleTypes.CLOUD, (double) ((float) pos.getX() + 0.5F), (double) ((float) pos.getY() + 0.8F), (double) ((float) pos.getZ() + 0.5F), 0.0D, 0.1D, 0.0D);
        }
    }
/*    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemStack =playerIn.getHeldItem(hand);
        int blockmeta=state.getValue(VARIANT).getMetadata();

            TileEntityRiceCauldron tileEntity = (TileEntityRiceCauldron) worldIn.getTileEntity(pos);
            if (!worldIn.isRemote&&itemStack.getItem() == ModCore_Urushi.UItems && blockmeta == 3 && itemStack.getItemDamage() == 5) {
                worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, EnumType.ClosedRawRice));
                tileEntity.setInventorySlotContents(0,itemStack);
                playerIn.setHeldItem(hand, new ItemStack(Blocks.AIR, 1, 0));
                worldIn.playSound((EntityPlayer) null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1.0F, 1.0F);
                return true;
            } else if (blockmeta == 1) {
                playerIn.setHeldItem(hand, tileEntity.getStackInSlot(2));

                //  playerIn.setHeldItem(hand,new ItemStack(ModCore_Urushi.UItems,tileEntity.getItemAmount(),6));
                // tileEntity.setItemAmount(0);
                tileEntity.setInventorySlotContents(2, ItemStack.EMPTY);
                worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, EnumType.Empty));
                worldIn.playSound((EntityPlayer) null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1.0F, 1.0F);

                return true;
            }else if (blockmeta == 0) {
                worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, EnumType.Empty));
                return true;
            }
            else if (blockmeta == 3) {
                worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, EnumType.ClosedEmpty));
                return true;

        }
        return false;
    }*/
    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityKama();
    }
}

package urushi.Block;



import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import urushi.ModCore_Urushi;

import javax.annotation.Nullable;
import java.util.List;


public class U_Planks extends Block {
    public static final PropertyEnum<U_Planks.EnumType> VARIANT = PropertyEnum.<U_Planks.EnumType>create("variant", U_Planks.EnumType.class);

    public U_Planks() {
        super(Material.WOOD);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, U_Planks.EnumType.SmoothOak));
        this.setCreativeTab(ModCore_Urushi.TabUrushi);
        setResistance(10F);
        setLightOpacity(255);
        setLightLevel(0.0F);
        setHardness(1.0F);
        setHarvestLevel("axe", 0);
        setSoundType(SoundType.WOOD);
    }

    /**
     * Gets the localized name of this block. Used for the statistics page.
     */
    public String getLocalizedName() {
        return I18n.translateToLocal(this.getUnlocalizedName() + "." + EnumType.SmoothOak.getUnlocalizedName() + ".name");
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return ((U_Planks.EnumType) state.getValue(VARIANT)).getMapColor();
    }


    /**
     * Gets the metadata of the item this Block can drop. This method is called when the block gets destroyed. It
     * returns the metadata of the dropped item based on the old metadata of the block.
     */
    public int damageDropped(IBlockState state) {
        return ((U_Planks.EnumType) state.getValue(VARIANT)).getMetadata();
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for (U_Planks.EnumType uplanks$enumtype : U_Planks.EnumType.values()) {
            items.add(new ItemStack(this, 1, uplanks$enumtype.getMetadata()));
        }
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, U_Planks.EnumType.byMetadata(meta));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state) {
        return ((U_Planks.EnumType) state.getValue(VARIANT)).getMetadata();
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{VARIANT});
    }

    public boolean canStainRedPlanks(int meta) {
        return (meta == 8 || meta == 10 || meta == 12) ? true : false;

    }

    public boolean canStainBlackPlanks(int meta) {
        return (meta == 6 || meta == 10 || meta == 12) ? true : false;

    }

    public boolean canStainRedSmoothPlanks(int meta) {
        return (meta == 6 || meta == 7 || meta == 9 || meta == 11 || meta == 13) ? false : true;

    }

    public boolean canStainBlackSmoothPlanks(int meta) {
        return (meta == 6 || meta == 8 || meta == 9 || meta == 11 || meta == 13) ? false : true;

    }



    public static enum EnumType implements IStringSerializable {
        SmoothOak(0, MapColor.WOOD, "smooth_oak_planks"),
        SmoothBirch(2, MapColor.SAND, "smooth_birch_planks"),
        SmoothSpruce(1, MapColor.OBSIDIAN, "smooth_spruce_planks"),
        SmoothJungle(3, MapColor.DIRT, "smooth_jungle_planks"),
        SmoothAcacia(4, MapColor.ADOBE, "smooth_acacia_planks"),
        SmoothDarkoak(5, MapColor.BROWN, "smooth_dark_oak_planks"),
        Red(6, MapColor.RED, "planks_red_urushi_stained"),
        SmoothRed(7, MapColor.RED, "smooth_planks_red_urushi_stained"),
        Black(8, MapColor.BLACK, "planks_black_urushi_stained"),
        SmoothBlack(9, MapColor.BLACK, "smooth_planks_black_urushi_stained"),
        JapaneseApricot(10, MapColor.ADOBE, "japanese_apricot_planks"),
        SmoothJapaneseApricot(11, MapColor.ADOBE, "smooth_japanese_apricot_planks"),
        Sakura(12, MapColor.WOOD, "sakura_planks"),
        SmoothSakura(13, MapColor.WOOD, "smooth_sakura_planks"),
        Cypress(14, MapColor.SAND, "cypress_planks"),
        SmoothCypress(15, MapColor.SAND, "smooth_cypress_planks");

        /**
         * Array of the Block's BlockStates
         */
        private static final U_Planks.EnumType[] META_LOOKUP = new U_Planks.EnumType[values().length];
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
        public static U_Planks.EnumType byMetadata(int meta) {
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
            for (U_Planks.EnumType uplanks$enumtype : values()) {
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
}

package urushi.Block;



import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import urushi.ModCore_Urushi;


public class U_Stone extends Block   {
    public static final PropertyEnum<U_Stone.EnumType> VARIANT = PropertyEnum.<U_Stone.EnumType>create("variant", U_Stone.EnumType.class);

    public U_Stone()
    {
        super(Material.ROCK);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.CupricOxideKawara));
        this.setCreativeTab(ModCore_Urushi.TabUrushi);
        setResistance(10F);
        setLightOpacity(255);
        setLightLevel(0.0F);
        setHardness(1.0F);
        setHarvestLevel("pickaxe", 0);
    }

    /**
     * Gets the localized name of this block. Used for the statistics page.
     */
    public String getLocalizedName()
    {
        return I18n.translateToLocal(this.getUnlocalizedName() + "." + EnumType.CupricOxideKawara.getUnlocalizedName() + ".name");
    }



    /**
     * Get the MapColor for this Block and the given BlockState
     */
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return ((U_Stone.EnumType)state.getValue(VARIANT)).getMapColor();
    }


    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        int meta = ((U_Stone.EnumType) state.getValue(VARIANT)).getMetadata();
        if (meta == 6 || meta == 7 || meta == 8) {
            return new ItemStack(ModCore_Urushi.UStone, 1, 1);
        }else if (meta == 9 || meta == 10 || meta == 11) {
            return new ItemStack(ModCore_Urushi.UStone, 1, 0);
        }else if (meta == 12 || meta ==13 || meta == 14) {
            return new ItemStack(ModCore_Urushi.UStone, 1, 5);
        }
            else {
            return new ItemStack(ModCore_Urushi.UStone, 1, meta);
        }
    }
    public int damageDropped(IBlockState state)
    {
        int meta=((U_Stone.EnumType)state.getValue(VARIANT)).getMetadata();
        if(meta==6||meta==7||meta==8){
            return 1;
        }else if(meta==9||meta==10||meta==11){
            return 0;
        }else if(meta==12||meta==13||meta==14){
            return 5;
        }

        else {
            return meta;
        }
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        for (int u = 0; u < 6; u++)
        {
            items.add(new ItemStack(this, 1, u));
        }
        items.add(new ItemStack(this, 1, 15));
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, U_Stone.EnumType.byMetadata(meta));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return ((U_Stone.EnumType)state.getValue(VARIANT)).getMetadata();
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {VARIANT});
    }
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {

            if(meta==1) {
                if (placer.getHorizontalFacing().getOpposite() == EnumFacing.SOUTH) {
                    return ModCore_Urushi.UStone.getDefaultState().withProperty(VARIANT, EnumType.IbushiKawara);
                } else if (placer.getHorizontalFacing().getOpposite() == EnumFacing.NORTH) {
                    return ModCore_Urushi.UStone.getDefaultState().withProperty(VARIANT, EnumType.IbushiKawara2);
                } else if (placer.getHorizontalFacing().getOpposite() == EnumFacing.EAST) {
                    return ModCore_Urushi.UStone.getDefaultState().withProperty(VARIANT, EnumType.IbushiKawara3);
                } else if (placer.getHorizontalFacing().getOpposite() == EnumFacing.WEST) {
                    return ModCore_Urushi.UStone.getDefaultState().withProperty(VARIANT, EnumType.IbushiKawara4);
                }
            }else if(meta==0) {
                if (placer.getHorizontalFacing().getOpposite() == EnumFacing.SOUTH) {
                    return ModCore_Urushi.UStone.getDefaultState().withProperty(VARIANT, EnumType.CupricOxideKawara);
                } else if (placer.getHorizontalFacing().getOpposite() == EnumFacing.NORTH) {
                    return ModCore_Urushi.UStone.getDefaultState().withProperty(VARIANT, EnumType.CupricOxideKawara2);
                } else if (placer.getHorizontalFacing().getOpposite() == EnumFacing.EAST) {
                    return ModCore_Urushi.UStone.getDefaultState().withProperty(VARIANT, EnumType.CupricOxideKawara3);
                } else if (placer.getHorizontalFacing().getOpposite() == EnumFacing.WEST) {
                    return ModCore_Urushi.UStone.getDefaultState().withProperty(VARIANT, EnumType.CupricOxideKawara4);
                }
            }else if(meta==5) {
                if (placer.getHorizontalFacing().getOpposite() == EnumFacing.SOUTH) {
                    return ModCore_Urushi.UStone.getDefaultState().withProperty(VARIANT, EnumType.CopperKawara);
                } else if (placer.getHorizontalFacing().getOpposite() == EnumFacing.NORTH) {
                    return ModCore_Urushi.UStone.getDefaultState().withProperty(VARIANT, EnumType.CopperKawara2);
                } else if (placer.getHorizontalFacing().getOpposite() == EnumFacing.EAST) {
                    return ModCore_Urushi.UStone.getDefaultState().withProperty(VARIANT, EnumType.CopperKawara3);
                } else if (placer.getHorizontalFacing().getOpposite() == EnumFacing.WEST) {
                    return ModCore_Urushi.UStone.getDefaultState().withProperty(VARIANT, EnumType.CopperKawara4);
                }
            }
        return ModCore_Urushi.UStone.getDefaultState().withProperty(VARIANT, U_Stone.EnumType.byMetadata(meta));

    }





    public static enum EnumType implements IStringSerializable
    {
        CupricOxideKawara(0, MapColor.CYAN, "cupric_oxide_kawara_block"),
        IbushiKawara(1, MapColor.BLACK, "ibushi_kawara_block"),
        CopperOre(2, MapColor.STONE, "copper_ore"),
        Plaster(3, MapColor.SNOW, "plaster"),
        NamakoPlaster(4, MapColor.BLACK, "namako"),
        CopperKawara(5, MapColor.ORANGE_STAINED_HARDENED_CLAY, "copper_kawara_block"),
        IbushiKawara2(6, MapColor.BLACK, "ibushi_kawara_block2"),
        IbushiKawara3(7, MapColor.BLACK, "ibushi_kawara_block3"),
        IbushiKawara4(8, MapColor.BLACK, "ibushi_kawara_block4"),
        CupricOxideKawara2(9, MapColor.CYAN, "cupric_oxide_kawara_block2"),
        CupricOxideKawara3(10, MapColor.CYAN, "cupric_oxide_kawara_block3"),
        CupricOxideKawara4(11, MapColor.CYAN, "cupric_oxide_kawara_block4"),
        CopperKawara2(12, MapColor.ORANGE_STAINED_HARDENED_CLAY, "copper_kawara_block2"),
        CopperKawara3(13, MapColor.ORANGE_STAINED_HARDENED_CLAY, "copper_kawara_block3"),
        CopperKawara4(14, MapColor.ORANGE_STAINED_HARDENED_CLAY, "copper_kawara_block4"),
        RoughStone(15, MapColor.STONE, "rough_stone");

        /** Array of the Block's BlockStates */
        private static final U_Stone.EnumType[] META_LOOKUP = new U_Stone.EnumType[values().length];
        /** The BlockState's metadata. */
        private final int meta;
        /** The EnumType's name. */
        private final String name;
        private final String unlocalizedName;
        private final MapColor mapColor;


        private EnumType(int p_i46383_3_, MapColor p_i46383_4_, String p_i46383_5_)
        {
            this(p_i46383_3_, p_i46383_4_, p_i46383_5_, p_i46383_5_);
        }

        private EnumType(int p_i46384_3_, MapColor p_i46384_4_, String p_i46384_5_, String p_i46384_6_)
        {
            this.meta = p_i46384_3_;
            this.name = p_i46384_5_;
            this.unlocalizedName = p_i46384_6_;
            this.mapColor = p_i46384_4_;

        }

        /**
         * Returns the EnumType's metadata value.
         */
        public int getMetadata()
        {
            return this.meta;
        }

        public MapColor getMapColor()
        {
            return this.mapColor;
        }

        public String toString()
        {
            return this.name;
        }

        /**
         * Returns an EnumType for the BlockState from a metadata value.
         */
        public static U_Stone.EnumType byMetadata(int meta)
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
            for (U_Stone.EnumType uplanks$enumtype : values())
            {
                META_LOOKUP[uplanks$enumtype.getMetadata()] = uplanks$enumtype;
            }
        }
    }
}




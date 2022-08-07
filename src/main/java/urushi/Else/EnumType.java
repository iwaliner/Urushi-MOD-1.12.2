package urushi.Else;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.MapColor;
import net.minecraft.util.IStringSerializable;

public class EnumType {
    public static enum EnumType8 implements IStringSerializable
    {
        TypeA(0, "type_a", MapColor.AIR),
        TypeB(1, "type_b", MapColor.AIR),
        TypeC(2, "type_c", MapColor.AIR),
        TypeD(3, "type_d", MapColor.AIR),
        TypeE(4, "type_e", MapColor.AIR),
        TypeF(5, "type_f", MapColor.AIR),
        TypeG(6, "type_g", MapColor.AIR),
        TypeH(7, "type_h", MapColor.AIR);

        private static final EnumType.EnumType8[] META_LOOKUP = new EnumType.EnumType8[values().length];
        private final int meta;
        private final String name;
        private final String unlocalizedName;
        /** The color that represents this entry on a map. */
        private final MapColor mapColor;

        private EnumType8(int metaIn, String nameIn, MapColor mapColorIn)
        {
            this(metaIn, nameIn, nameIn, mapColorIn);
        }

        private EnumType8(int metaIn, String nameIn, String unlocalizedNameIn, MapColor mapColorIn)
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

        public static EnumType.EnumType8 byMetadata(int meta)
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
            for (EnumType.EnumType8 $enumtype : values())
            {
                META_LOOKUP[$enumtype.getMetadata()] = $enumtype;
            }
        }
    }
    public static enum EnumType16 implements IStringSerializable
    {
        TypeA(0, "type_a", MapColor.AIR),
        TypeB(1, "type_b", MapColor.AIR),
        TypeC(2, "type_c", MapColor.AIR),
        TypeD(3, "type_d", MapColor.AIR),
        TypeE(4, "type_e", MapColor.AIR),
        TypeF(5, "type_f", MapColor.AIR),
        TypeG(6, "type_g", MapColor.AIR),
        TypeH(7, "type_h", MapColor.AIR),
        TypeI(8, "type_i", MapColor.AIR),
        TypeJ(9, "type_j", MapColor.AIR),
        TypeK(10, "type_k", MapColor.AIR),
        TypeL(11, "type_l", MapColor.AIR),
        TypeM(12, "type_m", MapColor.AIR),
        TypeN(13, "type_n", MapColor.AIR),
        TypeO(14, "type_o", MapColor.AIR),
        TypeP(15, "type_p", MapColor.AIR);

        private static final EnumType.EnumType16[] META_LOOKUP = new EnumType.EnumType16[values().length];
        private final int meta;
        private final String name;
        private final String unlocalizedName;
        /** The color that represents this entry on a map. */
        private final MapColor mapColor;

        private EnumType16(int metaIn, String nameIn, MapColor mapColorIn)
        {
            this(metaIn, nameIn, nameIn, mapColorIn);
        }

        private EnumType16(int metaIn, String nameIn, String unlocalizedNameIn, MapColor mapColorIn)
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

        public static EnumType.EnumType16 byMetadata(int meta)
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
            for (EnumType.EnumType16 $enumtype : values())
            {
                META_LOOKUP[$enumtype.getMetadata()] = $enumtype;
            }
        }
    }
    public static enum EnumType4 implements IStringSerializable
    {
        TypeA(0, "type_a", MapColor.AIR),
        TypeB(1, "type_b", MapColor.AIR),
        TypeC(2, "type_c", MapColor.AIR),
        TypeD(3, "type_d", MapColor.AIR);

        private static final EnumType.EnumType4[] META_LOOKUP = new EnumType.EnumType4[values().length];
        private final int meta;
        private final String name;
        private final String unlocalizedName;
        /** The color that represents this entry on a map. */
        private final MapColor mapColor;

        private EnumType4(int metaIn, String nameIn, MapColor mapColorIn)
        {
            this(metaIn, nameIn, nameIn, mapColorIn);
        }

        private EnumType4(int metaIn, String nameIn, String unlocalizedNameIn, MapColor mapColorIn)
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

        public static EnumType.EnumType4 byMetadata(int meta)
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
            for (EnumType.EnumType4 $enumtype : values())
            {
                META_LOOKUP[$enumtype.getMetadata()] = $enumtype;
            }
        }
    }
    public static enum EnumType2 implements IStringSerializable
    {
        TypeA(0, "type_a", MapColor.AIR),
        TypeB(1, "type_b", MapColor.AIR);

        private static final EnumType.EnumType2[] META_LOOKUP = new EnumType.EnumType2[values().length];
        private final int meta;
        private final String name;
        private final String unlocalizedName;
        /** The color that represents this entry on a map. */
        private final MapColor mapColor;

        private EnumType2(int metaIn, String nameIn, MapColor mapColorIn)
        {
            this(metaIn, nameIn, nameIn, mapColorIn);
        }

        private EnumType2(int metaIn, String nameIn, String unlocalizedNameIn, MapColor mapColorIn)
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

        public static EnumType.EnumType2 byMetadata(int meta)
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
            for (EnumType.EnumType2 $enumtype : values())
            {
                META_LOOKUP[$enumtype.getMetadata()] = $enumtype;
            }
        }
    }
}

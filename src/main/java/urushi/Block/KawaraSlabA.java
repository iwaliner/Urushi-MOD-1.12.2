package urushi.Block;


import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import urushi.Else.EnumType;
import urushi.ModCore_Urushi;

import java.util.Random;

public abstract class KawaraSlabA extends BlockSlab
{
    public static final PropertyEnum<EnumType.EnumType2> VARIANT = PropertyEnum.<EnumType.EnumType2>create("variant", EnumType.EnumType2.class);
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public KawaraSlabA()
    {
        super(Material.ROCK);
        IBlockState iblockstate = this.blockState.getBaseState();

        if (!this.isDouble())
        {
            iblockstate = iblockstate.withProperty(HALF, EnumBlockHalf.BOTTOM);
        }
        this.useNeighborBrightness=!this.isDouble();
        this.setDefaultState(iblockstate.withProperty(VARIANT,EnumType.EnumType2.TypeA).withProperty(FACING, EnumFacing.NORTH));
        this.setCreativeTab(ModCore_Urushi.TabUrushi);
        setResistance(10F);
        setLightOpacity(255);
        setLightLevel(0.0F);
        setHardness(1.0F);
        setHarvestLevel("pickaxe", 0);

    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return ((EnumType.EnumType2)state.getValue(VARIANT)).getMapColor();
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
          return Item.getItemFromBlock(ModCore_Urushi.KawaraSlabASingle);
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {

        return new ItemStack(ModCore_Urushi.KawaraSlabASingle, 1, ((EnumType.EnumType2)state.getValue(VARIANT)).getMetadata()*4);
    }

    /**
     * Returns the slab block name with the type associated with it
     */
    public String getUnlocalizedName(int meta)
    {
        return super.getUnlocalizedName() + "." + EnumType.EnumType2.byMetadata(meta).getUnlocalizedName();
    }

    public IProperty<?> getVariantProperty()
    {
        return VARIANT;
    }

    public Comparable<?> getTypeForItem(ItemStack stack)
    {
        return EnumType.EnumType2.byMetadata(stack.getMetadata() & 7);
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {

            items.add(new ItemStack(this, 1, 0));
        items.add(new ItemStack(this, 1, 4));

    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {

        if (!this.isDouble())
        {
            if(meta<4){
                return  this.getDefaultState().withProperty(HALF,EnumBlockHalf.BOTTOM).withProperty(VARIANT, EnumType.EnumType2.TypeA).withProperty(FACING,EnumFacing.getHorizontal(meta));
            }else if(meta<8){
                return  this.getDefaultState().withProperty(HALF,EnumBlockHalf.BOTTOM).withProperty(VARIANT, EnumType.EnumType2.TypeB).withProperty(FACING,EnumFacing.getHorizontal(meta-4));
            }else if(meta<12){
                return  this.getDefaultState().withProperty(HALF,EnumBlockHalf.TOP).withProperty(VARIANT, EnumType.EnumType2.TypeA).withProperty(FACING,EnumFacing.getHorizontal(meta-8));
            }else{
                return  this.getDefaultState().withProperty(HALF,EnumBlockHalf.TOP).withProperty(VARIANT, EnumType.EnumType2.TypeB).withProperty(FACING,EnumFacing.getHorizontal(meta-12));
            }
        }

        return  meta<4? this.getDefaultState().withProperty(VARIANT, EnumType.EnumType2.TypeA).withProperty(FACING, EnumFacing.getHorizontal(meta)):this.getDefaultState().withProperty(VARIANT, EnumType.EnumType2.TypeB).withProperty(FACING, EnumFacing.getHorizontal(meta-4));

    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
       int variantMeta= ((EnumType.EnumType2)state.getValue(VARIANT)).getMetadata();
        int facingMeta= ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
        if(!this.isDouble()) {
            EnumBlockHalf halfState = ((EnumBlockHalf) state.getValue(HALF));


            return halfState == EnumBlockHalf.BOTTOM ? facingMeta + variantMeta * 4 : facingMeta + variantMeta * 4 + 8;
        }
        return  facingMeta + variantMeta * 4 ;

    }

    protected BlockStateContainer createBlockState()
    {
        return this.isDouble() ? new BlockStateContainer(this, new IProperty[] {VARIANT,FACING}) : new BlockStateContainer(this, new IProperty[] {HALF, VARIANT,FACING});
    }

    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        EnumFacing facingState=placer.getHorizontalFacing();
        if(facing != EnumFacing.DOWN && (facing == EnumFacing.UP || (double) hitY <= 0.5D) ) {
            if (meta == 0) {
                    return this.getDefaultState().withProperty(VARIANT, EnumType.EnumType2.TypeA).withProperty(HALF, EnumBlockHalf.BOTTOM).withProperty(FACING, facingState);

            }else{
                return this.getDefaultState().withProperty(VARIANT, EnumType.EnumType2.TypeB).withProperty(HALF, EnumBlockHalf.BOTTOM).withProperty(FACING, facingState);

            }
        }else{
            if (meta == 0) {
                return this.getDefaultState().withProperty(VARIANT, EnumType.EnumType2.TypeA).withProperty(HALF, EnumBlockHalf.TOP).withProperty(FACING, facingState);

            }else{
                return this.getDefaultState().withProperty(VARIANT, EnumType.EnumType2.TypeB).withProperty(HALF, EnumBlockHalf.TOP).withProperty(FACING, facingState);

            }
        }

    }
    public int damageDropped(IBlockState state)
    {
        return ((EnumType.EnumType2)state.getValue(VARIANT)).getMetadata()*4;
    }


}
package urushi.Block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import urushi.Else.EnumType;
import urushi.ModCore_Urushi;

import java.util.Random;

public class ThatchedBlock extends Block
{
    public static final PropertyEnum<EnumType.EnumType2> VARIANT = PropertyEnum.<EnumType.EnumType2>create("variant", EnumType.EnumType2.class);

    public ThatchedBlock()
    {
        super(Material.GRASS);
        setSoundType(SoundType.PLANT);
        setResistance(3F);
        setLightOpacity(255);
        setLightLevel(0.0F);
        setHardness(0.3F);
        setCreativeTab(ModCore_Urushi.TabUrushi);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT,EnumType.EnumType2.TypeA));
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return MapColor.GRASS;
    }
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance)
    {
        entityIn.fall(fallDistance, 0.2F);
    }
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        int meta = ((EnumType.EnumType2) state.getValue(VARIANT)).getMetadata();
        return new ItemStack(ModCore_Urushi.ThatchedBlock, 1, ((EnumType.EnumType2)state.getValue(VARIANT)).getMetadata());
    }


    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        for(int i=0;i<2;i++) {
            items.add(new ItemStack(this, 1, i));
        }
    }
    public int damageDropped(IBlockState state) {
        return ((EnumType.EnumType2) state.getValue(VARIANT)).getMetadata();
    }
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT,EnumType.EnumType2.byMetadata(meta));
    }
    public int getMetaFromState(IBlockState state) {
        return ((EnumType.EnumType2) state.getValue(VARIANT)).getMetadata();
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{VARIANT});
    }






}
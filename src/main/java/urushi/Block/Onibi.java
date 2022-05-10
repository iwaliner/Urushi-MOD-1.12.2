package urushi.Block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import urushi.ModCore_Urushi;

import javax.annotation.Nullable;
import java.util.Random;

public class Onibi extends Block
{
    public Onibi()
    {
        super(Material.GLASS);
        setSoundType(SoundType.WOOD);
        setResistance(40F);
        setLightOpacity(0);
        setLightLevel(1.0F);
        setHardness(0F);
    }
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return MapColor.RED;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }
    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return Block.NULL_AABB;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return ModCore_Urushi.ItemOnibi;
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(ModCore_Urushi.ItemOnibi);
    }
   // public EnumBlockRenderType getRenderType(IBlockState state)
  //  {
  //      return EnumBlockRenderType.INVISIBLE;
  //  }

}
package urushi.Block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import urushi.Else.EnumType;
import urushi.ModCore_Urushi;

import javax.annotation.Nullable;
import java.util.Random;

public class RawUrushiLayer extends BlockFalling
{
    protected static final AxisAlignedBB AABB = new AxisAlignedBB(0D, 0.0D, 0D, 1D, 0.125D, 1D);

    public RawUrushiLayer()
    {
        super(Material.GROUND);
        setSoundType(SoundType.SLIME);
        setResistance(3F);
        setLightOpacity(0);
        setLightLevel(0.0F);
        setHardness(0.3F);
       // setCreativeTab(ModCore_Urushi.TabUrushi);
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return MapColor.WOOD;
    }
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return AABB;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return AABB;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(ModCore_Urushi.UItems, 1, 8);
    }
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return ModCore_Urushi.UItems;
    }

    @Override
    public int damageDropped(IBlockState state) {
        return 8;
    }

    @Override
    public EnumPushReaction getMobilityFlag(IBlockState state) {
        return EnumPushReaction.DESTROY;
    }





}
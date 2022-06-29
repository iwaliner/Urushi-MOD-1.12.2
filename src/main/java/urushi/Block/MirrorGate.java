package urushi.Block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import urushi.ModCore_Urushi;
import urushi.WorldGen.TeleporterBuildingGenKakuriyo;

import javax.annotation.Nullable;
import java.util.Random;

public class MirrorGate extends Block
{

    public MirrorGate()
    {
        super(Material.GLASS);
        setSoundType(SoundType.GLASS);
        setResistance(100F);
        setLightOpacity(0);
        setLightLevel(0F);
        setHardness(3F);
        setCreativeTab(ModCore_Urushi.TabUrushi);

    }
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return MapColor.GREEN;
    }

    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return Block.FULL_BLOCK_AABB;
    }
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return Block.NULL_AABB;
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Items.AIR;
    }



    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (
                !worldIn.isRemote &&
                        !player.isRiding() && !player.isBeingRidden() && player.isNonBoss() )
        {
            player.setPositionAndUpdate(pos.getX()+0.5D,pos.getY()+0.5D-1.0D,pos.getZ()+0.5D-1D);

            MinecraftServer server = worldIn.getMinecraftServer();
            Teleporter teleporter = new TeleporterBuildingGenKakuriyo(server.getWorld(ModCore_Urushi.KakuriyoDimensionID));
            if(player.dimension==ModCore_Urushi.KakuriyoDimensionID){
                player.changeDimension(0,teleporter);

            }else{
                player.changeDimension(ModCore_Urushi.KakuriyoDimensionID,teleporter);

            }
            return true;
        }else{
            return false;
        }
    }

}
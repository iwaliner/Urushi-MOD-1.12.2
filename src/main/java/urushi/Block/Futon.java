package urushi.Block;

import net.minecraft.block.*;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import urushi.ModCore_Urushi;
import urushi.TileEntity.TileEntityFuton;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class Futon extends BlockBed
{
    protected static final AxisAlignedBB FUTON_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D*2, 1.0D);

    public Futon()
    {
        super();
        this.setDefaultState(this.blockState.getBaseState().withProperty(PART, BlockBed.EnumPartType.FOOT).withProperty(OCCUPIED, Boolean.valueOf(false)));
        this.hasTileEntity = true;
        setResistance(10F);
        setLightOpacity(0);
        setLightLevel(0.0F);
        setHardness(0.3F);
        setSoundType(SoundType.CLOTH);
    }
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.SOLID;
    }
    @Deprecated
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.SOLID;
    }

    @Override
    public int damageDropped(IBlockState state) {
        return 33;
    }

@Override
    public boolean isBed(IBlockState state, IBlockAccess world, BlockPos pos, @Nullable Entity player)
    {
        return true;
    }
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
            if (state.getValue(PART) != BlockBed.EnumPartType.HEAD)
            {
                pos = pos.offset((EnumFacing)state.getValue(FACING));
                state = worldIn.getBlockState(pos);

                if (state.getBlock() != this)
                {
                    return true;
                }
            }

            net.minecraft.world.WorldProvider.WorldSleepResult sleepResult = worldIn.provider.canSleepAt(playerIn, pos);
            if (sleepResult != net.minecraft.world.WorldProvider.WorldSleepResult.BED_EXPLODES)
            {
                if (sleepResult == net.minecraft.world.WorldProvider.WorldSleepResult.DENY) return true;
                if (((Boolean)state.getValue(OCCUPIED)).booleanValue())
                {
                    EntityPlayer entityplayer = this.getPlayerInBed(worldIn, pos);

                    if (entityplayer != null)
                    {
                        playerIn.sendStatusMessage(new TextComponentTranslation("tile.bed.occupied", new Object[0]), true);
                        return true;
                    }

                    state = state.withProperty(OCCUPIED, Boolean.valueOf(false));
                    worldIn.setBlockState(pos, state, 4);
                }

                EntityPlayer.SleepResult entityplayer$sleepresult = playerIn.trySleep(pos);

                if (entityplayer$sleepresult == EntityPlayer.SleepResult.OK)
                {
                    state = state.withProperty(OCCUPIED, Boolean.valueOf(true));
                    worldIn.setBlockState(pos, state, 4);
                    playerIn.setPosition((double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.0625F*2), (double)((float)pos.getZ() + 0.5F));

                    return true;
                }
                else
                {
                    if (entityplayer$sleepresult == EntityPlayer.SleepResult.NOT_POSSIBLE_NOW)
                    {
                        playerIn.sendStatusMessage(new TextComponentTranslation("tile.bed.noSleep", new Object[0]), true);
                    }
                    else if (entityplayer$sleepresult == EntityPlayer.SleepResult.NOT_SAFE)
                    {
                        playerIn.sendStatusMessage(new TextComponentTranslation("tile.bed.notSafe", new Object[0]), true);
                    }
                    else if (entityplayer$sleepresult == EntityPlayer.SleepResult.TOO_FAR_AWAY)
                    {
                        playerIn.sendStatusMessage(new TextComponentTranslation("tile.bed.tooFarAway", new Object[0]), true);
                    }

                    return true;
                }
            }
            else
            {
                worldIn.setBlockToAir(pos);
                BlockPos blockpos = pos.offset(((EnumFacing)state.getValue(FACING)).getOpposite());

                if (worldIn.getBlockState(blockpos).getBlock() == this)
                {
                    worldIn.setBlockToAir(blockpos);
                }

                playerIn.sendStatusMessage(new TextComponentTranslation("tile.bed.tooFarAway", new Object[0]), true);
                return true;
            }
        }
    }

    @Nullable
    private EntityPlayer getPlayerInBed(World worldIn, BlockPos pos)
    {
        for (EntityPlayer entityplayer : worldIn.playerEntities)
        {
            if (entityplayer.isPlayerSleeping() && entityplayer.bedLocation.equals(pos))
            {
                return entityplayer;
            }
        }

        return null;
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return state.getValue(PART) == BlockBed.EnumPartType.FOOT ? Items.AIR : ModCore_Urushi.UItems;
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return FUTON_AABB;
    }
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance)
    {
        if (entityIn.isSneaking())
        {
            super.onFallenUpon(worldIn, pos, entityIn, fallDistance);
        }
        else
        {
            entityIn.fall(fallDistance, 0.0F);
        }
    }

    public void onLanded(World worldIn, Entity entityIn)
    {
        if (entityIn.isSneaking())
        {
            super.onLanded(worldIn, entityIn);
        }
        else if (entityIn.motionY < 0.0D)
        {
            entityIn.motionY = -entityIn.motionY;

            if (!(entityIn instanceof EntityLivingBase))
            {
                entityIn.motionY *= 0.8D;
            }
        }
    }





    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
    {
        if (state.getValue(PART) == BlockBed.EnumPartType.HEAD)
        {

           spawnAsEntity(worldIn, pos, new ItemStack(ModCore_Urushi.UItems, 1, 33));
        }
    }


    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        BlockPos blockpos = pos;

        if (state.getValue(PART) == BlockBed.EnumPartType.FOOT)
        {
            blockpos = pos.offset((EnumFacing)state.getValue(FACING));
        }

        return new ItemStack(ModCore_Urushi.UItems, 1, 33);
    }




    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        //return new TileEntityBed();
        return new TileEntityFuton();
    }







    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return MapColor.CLOTH;
    }








    @SideOnly(Side.CLIENT)
    public boolean hasCustomBreakingProgress(IBlockState state)
    {
        return false;
    }





    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te, ItemStack stack)
    {
      /*  if (state.getValue(PART) == BlockBed.EnumPartType.HEAD && te instanceof TileEntityBed)
        {
            TileEntityBed tileentitybed = (TileEntityBed)te;
            ItemStack itemstack = tileentitybed.getItemStack();
            spawnAsEntity(worldIn, pos, itemstack);
        }*/
        if (state.getValue(PART) == BlockBed.EnumPartType.HEAD && te instanceof TileEntityFuton)
        {
            TileEntityFuton tileentitybed = (TileEntityFuton)te;
            ItemStack itemstack = tileentitybed.getItemStack();
            spawnAsEntity(worldIn, pos, itemstack);
        }
        else
        {
            super.harvestBlock(worldIn, player, pos, state, (TileEntity)null, stack);
        }
    }

}

package urushi.Block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import urushi.Else.EnumType;
import urushi.ModCore_Urushi;
import urushi.TileEntity.TileEntityRiceCauldron;
import urushi.TileEntity.TileEntityRiceHokora;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class RiceHokora extends BlockContainer {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyEnum<EnumType.EnumType4> VARIANT = PropertyEnum.<EnumType.EnumType4>create("variant", EnumType.EnumType4.class);

    private boolean isSlab = false;

    public RiceHokora() {
        super(Material.WOOD);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(VARIANT, EnumType.EnumType4.TypeA));
        setResistance(10F);
        setLightOpacity(0);
        setLightLevel(0.0F);
        setHardness(1.0F);
        setHarvestLevel("axe", 0);
        setSoundType(SoundType.WOOD);

    }

    public int damageDropped(IBlockState state) {
        return 0;
    }


    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!worldIn.isRemote){
            EnumFacing blockFacing=worldIn.getBlockState(pos).getValue(FACING);
            if(worldIn.getTileEntity(pos)instanceof TileEntityRiceHokora) {
                TileEntityRiceHokora  tileEntity= (TileEntityRiceHokora) worldIn.getTileEntity(pos);
                playerIn.sendStatusMessage(new TextComponentTranslation("stored Reiryoku"+" : "+tileEntity.manaStored, new Object[0]), true);

            }
        }
        return false;
    }

    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }


    public IBlockState getStateFromMeta(int meta) {

        return meta>3? this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta%4)).withProperty(VARIANT, EnumType.EnumType4.TypeB):this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta%4)).withProperty(VARIANT, EnumType.EnumType4.TypeA);
    }

    public int getMetaFromState(IBlockState state) {
        return ((EnumFacing) state.getValue(FACING)).getHorizontalIndex()+4*((EnumType.EnumType4) state.getValue(VARIANT)).getMetadata();
    }

    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state.withProperty(FACING, rot.rotate((EnumFacing) state.getValue(FACING)));
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
        return state.withRotation(mirrorIn.toRotation((EnumFacing) state.getValue(FACING)));
    }


    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{FACING,VARIANT});
    }

    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        int metadata = stack.getItemDamage();
        ITextComponent textComponentA = new TextComponentTranslation("item.info.ricehokora1", new Object[0]);
        tooltip.add(textComponentA.getFormattedText());
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityRiceHokora();
    }
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

}
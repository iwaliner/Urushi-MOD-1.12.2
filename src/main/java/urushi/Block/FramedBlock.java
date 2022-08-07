package urushi.Block;


import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockShulkerBox;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import urushi.Else.EnumType;
import urushi.ModCore_Urushi;


import javax.annotation.Nullable;
import java.util.List;

public class FramedBlock extends Block
{
    public static final PropertyBool NORTH = PropertyBool.create("north");
    public static final PropertyBool EAST = PropertyBool.create("east");
    public static final PropertyBool SOUTH = PropertyBool.create("south");
    public static final PropertyBool WEST = PropertyBool.create("west");
    public static final PropertyBool UPPER = PropertyBool.create("upper");
    public static final PropertyBool UNDER = PropertyBool.create("under");

    public static final PropertyEnum<EnumType.EnumType8> VARIANT = PropertyEnum.<EnumType.EnumType8>create("variant", EnumType.EnumType8.class);
    public static final PropertyEnum<EnumType.EnumType2> VARIANT2 = PropertyEnum.<EnumType.EnumType2>create("variant2", EnumType.EnumType2.class);

    int var;
    //private Block blockThis;
    public FramedBlock(int i,String s,Material material)
    {
        super(material);
        this.setDefaultState(this.blockState.getBaseState().withProperty(NORTH, Boolean.valueOf(false)).withProperty(SOUTH, Boolean.valueOf(false)).withProperty(WEST, Boolean.valueOf(false)).withProperty(EAST, Boolean.valueOf(false)).withProperty(UPPER, Boolean.valueOf(false)).withProperty(UNDER, Boolean.valueOf(false)).withProperty(VARIANT,EnumType.EnumType8.TypeA).withProperty(VARIANT2,EnumType.EnumType2.TypeA));
        this.setCreativeTab(ModCore_Urushi.TabUrushi);
        setResistance(10F);
        setLightOpacity(255);
        setLightLevel(0.0F);
        setHardness(0.4F);

        var=i;
        if(s!=null) {
            setHarvestLevel(s, 0);
        }

if(s=="shovel"){
    setSoundType(SoundType.GROUND);
}

    }
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for(int i=0;i<var;i++) {
            items.add(new ItemStack(this, 1, i));
        }
    }
    public String getUnlocalizedName(int meta)
    {
        return super.getUnlocalizedName() + "." + EnumType.EnumType8.byMetadata(meta).getUnlocalizedName();
    }
    public int damageDropped(IBlockState state) {
        return ((EnumType.EnumType8) state.getValue(VARIANT)).getMetadata();
    }
    public IBlockState getStateFromMeta(int meta) {
        return meta<8? this.getDefaultState().withProperty(VARIANT, urushi.Else.EnumType.EnumType8.byMetadata(meta)).withProperty(VARIANT2, EnumType.EnumType2.TypeA):this.getDefaultState().withProperty(VARIANT, urushi.Else.EnumType.EnumType8.byMetadata(meta-8)).withProperty(VARIANT2, EnumType.EnumType2.TypeB);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state) {
        return ((urushi.Else.EnumType.EnumType8) state.getValue(VARIANT)).getMetadata()+((urushi.Else.EnumType.EnumType2) state.getValue(VARIANT2)).getMetadata()*8;
    }
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return state.withProperty(NORTH, canThisConnectTo(worldIn, pos, EnumFacing.NORTH))
                    .withProperty(SOUTH, canThisConnectTo(worldIn, pos, EnumFacing.SOUTH))
                    .withProperty(WEST,  canThisConnectTo(worldIn, pos, EnumFacing.WEST))
                    .withProperty(EAST,  canThisConnectTo(worldIn, pos, EnumFacing.EAST))
                .withProperty(UPPER,  canThisConnectTo(worldIn, pos, EnumFacing.UP))
                .withProperty(UNDER,  canThisConnectTo(worldIn, pos, EnumFacing.DOWN));
    }




    public final boolean attachesTo(IBlockAccess p_193393_1_, IBlockState state, BlockPos pos, EnumFacing facing,IBlockState stateThis)
    {

        return state.getBlock() instanceof FramedBlock && state.getValue(VARIANT)==stateThis.getValue(VARIANT) && state.getValue(VARIANT2)==stateThis.getValue(VARIANT2) ;
    }

    public boolean canThisConnectTo(IBlockAccess world, BlockPos pos, EnumFacing dir)
    {
        BlockPos other = pos.offset(dir);
        IBlockState state = world.getBlockState(other);
        return

                        attachesTo(world, state, other, dir.getOpposite(),world.getBlockState(pos));
    }







    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {NORTH, EAST, WEST ,SOUTH,UPPER,UNDER,VARIANT,VARIANT2});
    }

    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {

        if(meta==0 ) {
            if(!placer.isSneaking()){
                return  this.getDefaultState().withProperty(VARIANT, EnumType.EnumType8.TypeA).withProperty(VARIANT2, EnumType.EnumType2.TypeA);
            }else {
                return   this.getDefaultState().withProperty(VARIANT, EnumType.EnumType8.TypeA).withProperty(VARIANT2, EnumType.EnumType2.TypeB);
            }
        }else if(meta==1 ) {
            if(!placer.isSneaking()){
                return  this.getDefaultState().withProperty(VARIANT, EnumType.EnumType8.TypeB).withProperty(VARIANT2, EnumType.EnumType2.TypeA);
            }else {
                return   this.getDefaultState().withProperty(VARIANT, EnumType.EnumType8.TypeB).withProperty(VARIANT2, EnumType.EnumType2.TypeB);
            }
        }else if(meta==2 ) {
            if(!placer.isSneaking()){
                return  this.getDefaultState().withProperty(VARIANT, EnumType.EnumType8.TypeC).withProperty(VARIANT2, EnumType.EnumType2.TypeA);
            }else {
                return   this.getDefaultState().withProperty(VARIANT, EnumType.EnumType8.TypeC).withProperty(VARIANT2, EnumType.EnumType2.TypeB);
            }
        }else if(meta==3 ) {
            if(!placer.isSneaking()){
                return  this.getDefaultState().withProperty(VARIANT, EnumType.EnumType8.TypeD).withProperty(VARIANT2, EnumType.EnumType2.TypeA);
            }else {
                return   this.getDefaultState().withProperty(VARIANT, EnumType.EnumType8.TypeD).withProperty(VARIANT2, EnumType.EnumType2.TypeB);
            }
        }else if(meta==4 ) {
            if(!placer.isSneaking()){
                return  this.getDefaultState().withProperty(VARIANT, EnumType.EnumType8.TypeE).withProperty(VARIANT2, EnumType.EnumType2.TypeA);
            }else {
                return   this.getDefaultState().withProperty(VARIANT, EnumType.EnumType8.TypeE).withProperty(VARIANT2, EnumType.EnumType2.TypeB);
            }
        }else if(meta==5 ) {
            if(!placer.isSneaking()){
                return  this.getDefaultState().withProperty(VARIANT, EnumType.EnumType8.TypeF).withProperty(VARIANT2, EnumType.EnumType2.TypeA);
            }else {
                return   this.getDefaultState().withProperty(VARIANT, EnumType.EnumType8.TypeF).withProperty(VARIANT2, EnumType.EnumType2.TypeB);
            }
        }else if(meta==6 ) {
            if(!placer.isSneaking()){
                return  this.getDefaultState().withProperty(VARIANT, EnumType.EnumType8.TypeG).withProperty(VARIANT2, EnumType.EnumType2.TypeA);
            }else {
                return   this.getDefaultState().withProperty(VARIANT, EnumType.EnumType8.TypeG).withProperty(VARIANT2, EnumType.EnumType2.TypeB);
            }
        }else if(meta==7 ) {
            if(!placer.isSneaking()){
                return  this.getDefaultState().withProperty(VARIANT, EnumType.EnumType8.TypeH).withProperty(VARIANT2, EnumType.EnumType2.TypeA);
            }else {
                return   this.getDefaultState().withProperty(VARIANT, EnumType.EnumType8.TypeH).withProperty(VARIANT2, EnumType.EnumType2.TypeB);
            }
        }
        return     this.getDefaultState();

    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        ITextComponent textComponent1 = new TextComponentTranslation("item.info.framedblocks1", new Object[0]);
        ITextComponent textComponent2 = new TextComponentTranslation("item.info.framedblocks2", new Object[0]);

        tooltip.add(textComponent1.getFormattedText());
        tooltip.add(textComponent2.getFormattedText());
    }
}
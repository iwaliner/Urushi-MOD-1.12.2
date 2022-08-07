package urushi.Block;



import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
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


public class FramedGlassPane extends Block  {
    public static final PropertyEnum<EnumType.EnumType4> VARIANT = PropertyEnum.<EnumType.EnumType4>create("variant", EnumType.EnumType4.class);
    public static final PropertyEnum<EnumType.EnumType2> VARIANT2 = PropertyEnum.<EnumType.EnumType2>create("variant2", EnumType.EnumType2.class);
    public static final PropertyEnum<EnumType.EnumType2> VARIANT3 = PropertyEnum.<EnumType.EnumType2>create("variant3", EnumType.EnumType2.class);
    protected static final AxisAlignedBB AABB_B = new AxisAlignedBB(0.0625D*7, 0.0D, 0D, 0.0625D*9, 1D, 1D);
    protected static final AxisAlignedBB AABB_A = new AxisAlignedBB(0.0D, 0.0D, 0.0625D*7, 1.0D, 1D, 0.0625D*9);
    public static final PropertyBool NORTHC = PropertyBool.create("north");
    public static final PropertyBool EASTC = PropertyBool.create("east");
    public static final PropertyBool SOUTHC = PropertyBool.create("south");
    public static final PropertyBool WESTC = PropertyBool.create("west");
    public static final PropertyBool UPPERC = PropertyBool.create("upper");
    public static final PropertyBool UNDERC = PropertyBool.create("under");


    private int amount;

    public FramedGlassPane(int i) {
        super(Material.GLASS);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.EnumType4.TypeA).withProperty(VARIANT2, EnumType.EnumType2.TypeA).withProperty(VARIANT3, EnumType.EnumType2.TypeA).withProperty(NORTHC, Boolean.valueOf(false)).withProperty(SOUTHC, Boolean.valueOf(false)).withProperty(WESTC, Boolean.valueOf(false)).withProperty(EASTC, Boolean.valueOf(false)).withProperty(UPPERC, Boolean.valueOf(false)).withProperty(UNDERC, Boolean.valueOf(false)));
        this.setCreativeTab(ModCore_Urushi.TabUrushi);
        setResistance(10F);
        setLightOpacity(0);
        setLightLevel(0.0F);
        setHardness(0.4F);
        setSoundType(SoundType.GLASS);
        amount=i;
    }
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return MapColor.AIR;
    }

    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
        Block block = iblockstate.getBlock();
        if ((block instanceof FramedGlassPane)){
            return iblockstate.getValue(VARIANT)!=blockState.getValue(VARIANT);
        }else{
            return true;
        }
    }
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState)
    {
        if (state.getValue(VARIANT2)== EnumType.EnumType2.TypeA)
        {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_A);
        }else
        {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_B);
        }
    }
    public int damageDropped(IBlockState state) {
        return ((EnumType.EnumType4) state.getValue(VARIANT)).getMetadata();
    }


    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for(int i=0;i<amount;i++) {
            items.add(new ItemStack(this, 1, i));
        }
    }
    public IBlockState getStateFromMeta(int meta) {
        if(meta<4){
         return this.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.byMetadata(meta)).withProperty(VARIANT2, EnumType.EnumType2.TypeA).withProperty(VARIANT3, EnumType.EnumType2.TypeA);
        }else if(meta<8){
            return this.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.byMetadata(meta-4)).withProperty(VARIANT2, EnumType.EnumType2.TypeB).withProperty(VARIANT3, EnumType.EnumType2.TypeA);
        }else if(meta<12){
            return this.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.byMetadata(meta-8)).withProperty(VARIANT2, EnumType.EnumType2.TypeA).withProperty(VARIANT3, EnumType.EnumType2.TypeB);
        }else {
            return this.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.byMetadata(meta-12)).withProperty(VARIANT2, EnumType.EnumType2.TypeB).withProperty(VARIANT3, EnumType.EnumType2.TypeB);
        }
    }
    public int getMetaFromState(IBlockState state) {
        if(state.getValue(VARIANT2)== EnumType.EnumType2.TypeA&&state.getValue(VARIANT3)== EnumType.EnumType2.TypeA){
            return ((EnumType.EnumType4) state.getValue(VARIANT)).getMetadata();
        }else if(state.getValue(VARIANT2)== EnumType.EnumType2.TypeB&&state.getValue(VARIANT3)== EnumType.EnumType2.TypeA){
            return ((EnumType.EnumType4) state.getValue(VARIANT)).getMetadata()+4;
        }else if(state.getValue(VARIANT2)== EnumType.EnumType2.TypeA&&state.getValue(VARIANT3)== EnumType.EnumType2.TypeB){
            return ((EnumType.EnumType4) state.getValue(VARIANT)).getMetadata()+8;
        }else {
            return ((EnumType.EnumType4) state.getValue(VARIANT)).getMetadata()+12;
        }
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{NORTHC, EASTC, WESTC ,SOUTHC,UPPERC,UNDERC,VARIANT,VARIANT2,VARIANT3});
    }
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return state.withProperty(NORTHC, canThisConnectTo(worldIn, pos, EnumFacing.NORTH))
                .withProperty(SOUTHC, canThisConnectTo(worldIn, pos, EnumFacing.SOUTH))
                .withProperty(WESTC,  canThisConnectTo(worldIn, pos, EnumFacing.WEST))
                .withProperty(EASTC,  canThisConnectTo(worldIn, pos, EnumFacing.EAST))
                .withProperty(UPPERC,  canThisConnectTo(worldIn, pos, EnumFacing.UP))
                .withProperty(UNDERC,  canThisConnectTo(worldIn, pos, EnumFacing.DOWN));
    }




    public final boolean attachesTo(IBlockAccess p_193393_1_, IBlockState state, BlockPos pos, EnumFacing facing,IBlockState stateThis)
    {
        if(state.getBlock() instanceof FramedGlassPane&&stateThis.getBlock() instanceof FramedGlassPane){
            return  state.getValue(VARIANT)==stateThis.getValue(VARIANT) && state.getValue(VARIANT2)==stateThis.getValue(VARIANT2)&& state.getValue(VARIANT3)==stateThis.getValue(VARIANT3) ;

        }
        return false;    }

    public boolean canThisConnectTo(IBlockAccess world, BlockPos pos, EnumFacing dir)
    {
        BlockPos other = pos.offset(dir);
        IBlockState state = world.getBlockState(other);
        return attachesTo(world, state, other, dir.getOpposite(),world.getBlockState(pos));
    }

    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        EnumType.EnumType2 sneakVariant= placer.isSneaking()? EnumType.EnumType2.TypeB: EnumType.EnumType2.TypeA;
        if(meta==0 ) {
           if(placer.getHorizontalFacing().getOpposite()==EnumFacing.NORTH||placer.getHorizontalFacing().getOpposite()==EnumFacing.SOUTH){
                return  this.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeA).withProperty(VARIANT2, EnumType.EnumType2.TypeA).withProperty(VARIANT3, sneakVariant);
            }else {
               return   this.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeA).withProperty(VARIANT2, EnumType.EnumType2.TypeB).withProperty(VARIANT3, sneakVariant);
           }
        }else if(meta==1 ) {
            if(placer.getHorizontalFacing().getOpposite()==EnumFacing.NORTH||placer.getHorizontalFacing().getOpposite()==EnumFacing.SOUTH){
                return  this.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeB).withProperty(VARIANT2, EnumType.EnumType2.TypeA).withProperty(VARIANT3, sneakVariant);
            }else {
                return  this.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeB).withProperty(VARIANT2, EnumType.EnumType2.TypeB).withProperty(VARIANT3, sneakVariant);
            }
        }
        else if(meta==2 ) {
            if(placer.getHorizontalFacing().getOpposite()==EnumFacing.NORTH||placer.getHorizontalFacing().getOpposite()==EnumFacing.SOUTH){
                return  this.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeC).withProperty(VARIANT2, EnumType.EnumType2.TypeA).withProperty(VARIANT3, sneakVariant);
            }else {
                return  this.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeC).withProperty(VARIANT2, EnumType.EnumType2.TypeB).withProperty(VARIANT3, sneakVariant);
            }
        }
        else if(meta==3 ) {
            if(placer.getHorizontalFacing().getOpposite()==EnumFacing.NORTH||placer.getHorizontalFacing().getOpposite()==EnumFacing.SOUTH){
                return  this.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeD).withProperty(VARIANT2, EnumType.EnumType2.TypeA).withProperty(VARIANT3, sneakVariant);
            }else {
                return  this.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeD).withProperty(VARIANT2, EnumType.EnumType2.TypeB).withProperty(VARIANT3, sneakVariant);
            }
        }

        return     this.getDefaultState();

    }


    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        state = state.getActualState(source, pos);
      EnumType.EnumType2 enumtype2 = (EnumType.EnumType2)state.getValue(VARIANT2);
       return  enumtype2== EnumType.EnumType2.TypeA?AABB_A:AABB_B;
    }
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        state = state.getActualState(source, pos);
        EnumType.EnumType2 enumtype2 = (EnumType.EnumType2)state.getValue(VARIANT2);
        return  enumtype2== EnumType.EnumType2.TypeA?AABB_A:AABB_B;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        ITextComponent textComponent1 = new TextComponentTranslation("item.info.framedblocks1", new Object[0]);
        ITextComponent textComponent2 = new TextComponentTranslation("item.info.framedblocks2", new Object[0]);

        tooltip.add(textComponent1.getFormattedText());
        tooltip.add(textComponent2.getFormattedText());
    }






}

package urushi.Block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFlintAndSteel;
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
import java.util.Random;

public class Kakejiku extends Block {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyEnum<EnumType.EnumType4> VARIANT = PropertyEnum.<EnumType.EnumType4>create("variant", EnumType.EnumType4.class);
     protected static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB(0.0D, 0.0D, 0D, 1.0D, 1D, 0.0625D);
    protected static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB(0.0D, 0.0D, 0.0625D*15, 1.0D, 1D, 1D);
    protected static final AxisAlignedBB AABB_WEST = new AxisAlignedBB(0D, 0.0D, 0D, 0.0625D, 1D, 1D);
    protected static final AxisAlignedBB AABB_EAST = new AxisAlignedBB(0.0625D*15, 0.0D, 0D, 1D, 1D, 1D);

    private boolean isSlab = false;

    public Kakejiku() {
        super(Material.WOOD);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(VARIANT, EnumType.EnumType4.TypeA));
        setResistance(10F);
        setLightOpacity(0);
        setLightLevel(0.0F);
        setHardness(0F);
        setSoundType(SoundType.WOOD);

    }
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        state = state.getActualState(source, pos);
        EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
         switch (enumfacing)
        {
            case EAST:
            default:
                return AABB_EAST;
            case SOUTH:
                return AABB_SOUTH;
            case WEST:
                return AABB_WEST;
            case NORTH:
                return AABB_NORTH;
        }
    }
    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
        addCollisionBoxToList(pos, entityBox, collidingBoxes, Block.NULL_AABB);

    }

    public int damageDropped(IBlockState state) {
        return 0;
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(ModCore_Urushi.KakejikuItem);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return ModCore_Urushi.KakejikuItem;
    }

   /* public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        Random random=new Random();
        int i=14;
        if(!worldIn.isRemote) {
            if (random.nextInt(i) == 0) {
                return ModCore_Urushi.KakejikuA.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeA).withProperty(FACING, placer.getHorizontalFacing());
            } else if (random.nextInt(i) == 1) {
                return ModCore_Urushi.KakejikuA.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeB).withProperty(FACING, placer.getHorizontalFacing());
            } else if (random.nextInt(i) == 2) {
                return ModCore_Urushi.KakejikuA.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeC).withProperty(FACING, placer.getHorizontalFacing());
            } else if (random.nextInt(i) == 3) {
                return ModCore_Urushi.KakejikuA.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeD).withProperty(FACING, placer.getHorizontalFacing());
            } else if (random.nextInt(i) == 4) {
                return ModCore_Urushi.KakejikuB.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeA).withProperty(FACING, placer.getHorizontalFacing());
            } else if (random.nextInt(i) == 5) {
                return ModCore_Urushi.KakejikuB.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeB).withProperty(FACING, placer.getHorizontalFacing());
            } else if (random.nextInt(i) == 6) {
                return ModCore_Urushi.KakejikuB.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeC).withProperty(FACING, placer.getHorizontalFacing());
            } else if (random.nextInt(i) == 7) {
                return ModCore_Urushi.KakejikuB.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeD).withProperty(FACING, placer.getHorizontalFacing());
            } else if (random.nextInt(i) == 8) {
                return ModCore_Urushi.KakejikuC.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeA).withProperty(FACING, placer.getHorizontalFacing());
            } else if (random.nextInt(i) == 9) {
                return ModCore_Urushi.KakejikuC.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeB).withProperty(FACING, placer.getHorizontalFacing());
            } else if (random.nextInt(i) == 10) {
                return ModCore_Urushi.KakejikuC.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeC).withProperty(FACING, placer.getHorizontalFacing());
            } else if (random.nextInt(i) == 11) {
                return ModCore_Urushi.KakejikuC.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeD).withProperty(FACING, placer.getHorizontalFacing());
            } else if (random.nextInt(i) == 12) {
                return ModCore_Urushi.KakejikuD.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeB).withProperty(FACING, placer.getHorizontalFacing());
            } else if (random.nextInt(i) == 13) {
                return ModCore_Urushi.KakejikuD.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeC).withProperty(FACING, placer.getHorizontalFacing());
            } else {
                return ModCore_Urushi.KakejikuD.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeA).withProperty(FACING, placer.getHorizontalFacing());
            }
        }
        return  this.getDefaultState();

    }
*/


    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        Random random=new Random();
        int i=14;
        if(!worldIn.isRemote) {
            if (random.nextInt(i) == 0) {
                worldIn.setBlockState(pos, ModCore_Urushi.KakejikuA.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeA).withProperty(FACING, placer.getHorizontalFacing()));
               worldIn.setBlockState(pos, ModCore_Urushi.KakejikuA.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeA).withProperty(FACING, placer.getHorizontalFacing()));
            } else if (random.nextInt(i) == 1) {
                worldIn.setBlockState(pos,  ModCore_Urushi.KakejikuA.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeB).withProperty(FACING, placer.getHorizontalFacing()));
                worldIn.setBlockState(pos,  ModCore_Urushi.KakejikuA.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeB).withProperty(FACING, placer.getHorizontalFacing()));
            } else if (random.nextInt(i) == 2) {
                worldIn.setBlockState(pos,  ModCore_Urushi.KakejikuA.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeC).withProperty(FACING, placer.getHorizontalFacing()));

                worldIn.setBlockState(pos,  ModCore_Urushi.KakejikuA.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeC).withProperty(FACING, placer.getHorizontalFacing()));
            } else if (random.nextInt(i) == 3) {
                worldIn.setBlockState(pos,  ModCore_Urushi.KakejikuA.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeD).withProperty(FACING, placer.getHorizontalFacing()));
                worldIn.setBlockState(pos,  ModCore_Urushi.KakejikuA.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeD).withProperty(FACING, placer.getHorizontalFacing()));
            } else if (random.nextInt(i) == 4) {
                worldIn.setBlockState(pos, ModCore_Urushi.KakejikuB.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeA).withProperty(FACING, placer.getHorizontalFacing()));
                worldIn.setBlockState(pos, ModCore_Urushi.KakejikuB.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeA).withProperty(FACING, placer.getHorizontalFacing()));
            } else if (random.nextInt(i) == 5) {
                worldIn.setBlockState(pos, ModCore_Urushi.KakejikuB.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeB).withProperty(FACING, placer.getHorizontalFacing()));
                worldIn.setBlockState(pos, ModCore_Urushi.KakejikuB.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeB).withProperty(FACING, placer.getHorizontalFacing()));
            } else if (random.nextInt(i) == 6) {
                worldIn.setBlockState(pos, ModCore_Urushi.KakejikuB.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeC).withProperty(FACING, placer.getHorizontalFacing()));
                worldIn.setBlockState(pos, ModCore_Urushi.KakejikuB.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeC).withProperty(FACING, placer.getHorizontalFacing()));
            } else if (random.nextInt(i) == 7) {
                worldIn.setBlockState(pos, ModCore_Urushi.KakejikuB.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeD).withProperty(FACING, placer.getHorizontalFacing()));
                worldIn.setBlockState(pos, ModCore_Urushi.KakejikuB.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeD).withProperty(FACING, placer.getHorizontalFacing()));
            } else if (random.nextInt(i) == 8) {
                worldIn.setBlockState(pos, ModCore_Urushi.KakejikuC.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeA).withProperty(FACING, placer.getHorizontalFacing()));
                worldIn.setBlockState(pos, ModCore_Urushi.KakejikuC.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeA).withProperty(FACING, placer.getHorizontalFacing()));
            } else if (random.nextInt(i) == 9) {
                worldIn.setBlockState(pos, ModCore_Urushi.KakejikuC.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeB).withProperty(FACING, placer.getHorizontalFacing()));
                worldIn.setBlockState(pos, ModCore_Urushi.KakejikuC.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeB).withProperty(FACING, placer.getHorizontalFacing()));
            } else if (random.nextInt(i) == 10) {
                worldIn.setBlockState(pos, ModCore_Urushi.KakejikuC.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeC).withProperty(FACING, placer.getHorizontalFacing()));
                worldIn.setBlockState(pos, ModCore_Urushi.KakejikuC.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeC).withProperty(FACING, placer.getHorizontalFacing()));
            } else if (random.nextInt(i) == 11) {
                worldIn.setBlockState(pos, ModCore_Urushi.KakejikuC.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeD).withProperty(FACING, placer.getHorizontalFacing()));
                worldIn.setBlockState(pos, ModCore_Urushi.KakejikuC.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeD).withProperty(FACING, placer.getHorizontalFacing()));
            } else if (random.nextInt(i) == 12) {
                worldIn.setBlockState(pos, ModCore_Urushi.KakejikuD.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeB).withProperty(FACING, placer.getHorizontalFacing()));
                worldIn.setBlockState(pos, ModCore_Urushi.KakejikuD.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeB).withProperty(FACING, placer.getHorizontalFacing()));
            } else if (random.nextInt(i) == 13) {
                worldIn.setBlockState(pos, ModCore_Urushi.KakejikuD.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeC).withProperty(FACING, placer.getHorizontalFacing()));
                worldIn.setBlockState(pos, ModCore_Urushi.KakejikuD.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeC).withProperty(FACING, placer.getHorizontalFacing()));
            } else {
                worldIn.setBlockState(pos, ModCore_Urushi.KakejikuD.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeA).withProperty(FACING, placer.getHorizontalFacing()));
                worldIn.setBlockState(pos, ModCore_Urushi.KakejikuD.getDefaultState().withProperty(VARIANT, EnumType.EnumType4.TypeA).withProperty(FACING, placer.getHorizontalFacing()));
            }
        }
    }



    public IBlockState getStateFromMeta(int meta) {
        if(meta>11){return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta%4)).withProperty(VARIANT, EnumType.EnumType4.TypeD);}
      else  if(meta>7){return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta%4)).withProperty(VARIANT, EnumType.EnumType4.TypeC);}
        else  if(meta>3){return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta%4)).withProperty(VARIANT, EnumType.EnumType4.TypeB);}
        else  {return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta%4)).withProperty(VARIANT, EnumType.EnumType4.TypeA);}

    }

    public int getMetaFromState(IBlockState state) {
        return ((EnumFacing) state.getValue(FACING)).getHorizontalIndex()+4*((EnumType.EnumType4) state.getValue(VARIANT)).getMetadata();
    }

 /*
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state.withProperty(FACING, rot.rotate((EnumFacing) state.getValue(FACING)));
    }


    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
        return state.withRotation(mirrorIn.toRotation((EnumFacing) state.getValue(FACING)));
    }*/


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


}
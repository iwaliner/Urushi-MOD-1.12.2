package urushi.Block;



import com.google.common.base.Predicate;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import urushi.ModCore_Urushi;

import javax.annotation.Nullable;
import java.util.Random;


public class ChiseledLacquerLog extends BlockLog {
    public static final PropertyEnum<BlockPlanks.EnumType> VARIANT = PropertyEnum.<BlockPlanks.EnumType>create("variant", BlockPlanks.EnumType.class, new Predicate<BlockPlanks.EnumType>()
    {
        public boolean apply(@Nullable BlockPlanks.EnumType p_apply_1_)
        {
            return p_apply_1_.getMetadata() < 4;
        }
    });
    public ChiseledLacquerLog()
    {
        super();
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockPlanks.EnumType.OAK).withProperty(LOG_AXIS, EnumAxis.Y));
        this.setCreativeTab(ModCore_Urushi.TabUrushi);
        setResistance(10F);
        setLightOpacity(255);
        setLightLevel(0.0F);
        setHardness(1.0F);
        setHarvestLevel("axe", 0);
        setSoundType(SoundType.WOOD);
        this.setTickRandomly(true);
    }

    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        BlockPlanks.EnumType blockplanks$enumtype = (BlockPlanks.EnumType)state.getValue(VARIANT);

        switch ((EnumAxis)state.getValue(LOG_AXIS))
        {
            case X:
            case Z:
            case NONE:
            default:

                switch (blockplanks$enumtype)
                {
                    case OAK:
                    default:
                        return BlockPlanks.EnumType.OAK.getMapColor();
                    case SPRUCE:
                        return BlockPlanks.EnumType.SPRUCE.getMapColor();
                    case BIRCH:
                        return MapColor.QUARTZ;
                    case JUNGLE:
                        return BlockPlanks.EnumType.JUNGLE.getMapColor();
                }

            case Y:
                return blockplanks$enumtype.getMapColor();
        }
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        items.add(new ItemStack(this, 1, BlockPlanks.EnumType.OAK.getMetadata()));
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, BlockPlanks.EnumType.byMetadata((meta & 3) % 4));

        switch (meta & 12)
        {
            case 0:
                iblockstate = iblockstate.withProperty(LOG_AXIS, EnumAxis.Y);
                break;
            case 4:
                iblockstate = iblockstate.withProperty(LOG_AXIS, EnumAxis.X);
                break;
            case 8:
                iblockstate = iblockstate.withProperty(LOG_AXIS, EnumAxis.Z);
                break;
            default:
                iblockstate = iblockstate.withProperty(LOG_AXIS, EnumAxis.NONE);
        }

        return iblockstate;
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    @SuppressWarnings("incomplete-switch")
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | ((BlockPlanks.EnumType)state.getValue(VARIANT)).getMetadata();

        switch ((EnumAxis)state.getValue(LOG_AXIS))
        {
            case X:
                i |= 4;
                break;
            case Z:
                i |= 8;
                break;
            case NONE:
                i |= 12;
        }

        return i;
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {VARIANT, LOG_AXIS});
    }



    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(ModCore_Urushi.ChiseledLacquerLog,1,0);
    }
    /**
     * Gets the metadata of the item this Block can drop. This method is called when the block gets destroyed. It
     * returns the metadata of the dropped item based on the old metadata of the block.
     */
    public int damageDropped(IBlockState state)
    {
        return 0;
    }



    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        int meta=state.getBlock().getMetaFromState(state);
        int p=0,q=0,r=0;
        if(meta==0){
          r=-1;
        }else if(meta==1){
                  r=1;
        }else if(meta==2){
               p=1;
        }else if(meta==3){
       p=-1;
        }
        for(int i=1; i<256;i++) {
            if(worldIn.getBlockState(pos.add(p,q-i,r)).getBlock()==ModCore_Urushi.WoodenBucket){
                WoodenBucket woodenBucket= (WoodenBucket) worldIn.getBlockState(pos.add(p,q-i,r)).getBlock();
                int blockMeta=woodenBucket.getMetaFromState(worldIn.getBlockState(pos.add(p,q-i,r)));
                if((woodenBucket.isEmpty(blockMeta)||woodenBucket.isRawUrushi(blockMeta))&&blockMeta!=3){
                    woodenBucket.setUrushiLevel(worldIn,pos.add(p,q-i,r),worldIn.getBlockState(pos.add(p,q-i,r)),blockMeta+1);
                    worldIn.playSound((EntityPlayer)null, pos.add(p,q-i,r), SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    break;
                }else{
                    break;
                }
            }else if(worldIn.getBlockState(pos.add(p,q-i,r)).getBlock()!= Blocks.AIR&&worldIn.getBlockState(pos.add(p,q-i,r)).getBlock()!= ModCore_Urushi.RawUrushiLayer){
                worldIn.setBlockState(pos.add(p,q-i+1,r),ModCore_Urushi.RawUrushiLayer.getDefaultState());

                break;
            }else{
                continue;
            }
        }
    }
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        int meta=stateIn.getBlock().getMetaFromState(stateIn);
        int p=0,q=0,r=0;
        if(meta==0){
            r=-1;
        }else if(meta==1){
            r=1;
        }else if(meta==2){
            p=1;
        }else if(meta==3){
            p=-1;
        }

       int f= rand.nextInt(6);
        if(f==1)
        worldIn.spawnParticle(EnumParticleTypes.CLOUD, (double) pos.getX()+0.5D+p, (double)pos.getY()+0.5D+q, (double)pos.getZ()+0.5D+r, (double)0D, (double)-0.2D, (double)0D);

    }
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        if (placer.getHorizontalFacing() == EnumFacing.SOUTH) {
            return this.getDefaultState().withProperty(ChiseledLacquerLog.VARIANT, BlockPlanks.EnumType.OAK);

        } else if (placer.getHorizontalFacing() == EnumFacing.NORTH) {
            return this.getDefaultState().withProperty(ChiseledLacquerLog.VARIANT, BlockPlanks.EnumType.SPRUCE);
        } else if (placer.getHorizontalFacing() == EnumFacing.EAST) {
            return this.getDefaultState().withProperty(ChiseledLacquerLog.VARIANT, BlockPlanks.EnumType.JUNGLE);
        } else if (placer.getHorizontalFacing() == EnumFacing.WEST) {
            return this.getDefaultState().withProperty(ChiseledLacquerLog.VARIANT, BlockPlanks.EnumType.BIRCH);
        }
        return this.getDefaultState();
    }

    }
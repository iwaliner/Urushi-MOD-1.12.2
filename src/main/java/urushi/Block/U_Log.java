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
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import urushi.ModCore_Urushi;

import javax.annotation.Nullable;
import java.util.List;


public class U_Log extends BlockLog  {
    public static final PropertyEnum<BlockPlanks.EnumType> VARIANT = PropertyEnum.<BlockPlanks.EnumType>create("variant", BlockPlanks.EnumType.class, new Predicate<BlockPlanks.EnumType>()
    {
        public boolean apply(@Nullable BlockPlanks.EnumType p_apply_1_)
        {
            return p_apply_1_.getMetadata() < 4;
        }
    });
    public U_Log()
    {
        super();
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockPlanks.EnumType.OAK).withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
        this.setCreativeTab(ModCore_Urushi.TabUrushi);
        setResistance(10F);
        setLightOpacity(255);
        setLightLevel(0.0F);
        setHardness(1.0F);
        setHarvestLevel("axe", 0);
        setSoundType(SoundType.WOOD);
    }

    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        BlockPlanks.EnumType blockplanks$enumtype = (BlockPlanks.EnumType)state.getValue(VARIANT);

        switch ((BlockLog.EnumAxis)state.getValue(LOG_AXIS))
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
        items.add(new ItemStack(this, 1, BlockPlanks.EnumType.SPRUCE.getMetadata()));
        items.add(new ItemStack(this, 1, BlockPlanks.EnumType.BIRCH.getMetadata()));
        items.add(new ItemStack(this, 1, BlockPlanks.EnumType.JUNGLE.getMetadata()));

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
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y);
                break;
            case 4:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.X);
                break;
            case 8:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z);
                break;
            default:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
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

        switch ((BlockLog.EnumAxis)state.getValue(LOG_AXIS))
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



    /**
     * Gets the metadata of the item this Block can drop. This method is called when the block gets destroyed. It
     * returns the metadata of the dropped item based on the old metadata of the block.
     */
    public int damageDropped(IBlockState state)
    {
        return ((BlockPlanks.EnumType)state.getValue(VARIANT)).getMetadata();
    }



    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        int blockMeta=state.getBlock().getMetaFromState(state);
        if(state.getValue(VARIANT)== BlockPlanks.EnumType.OAK&&playerIn.getHeldItem(hand).getItem() instanceof ItemAxe){
            worldIn.setBlockState(pos,ModCore_Urushi.UStrippedLog.getDefaultState().withProperty(BlockLog.LOG_AXIS,state.getValue(BlockLog.LOG_AXIS)).withProperty(U_StrippedLog.VARIANT, BlockPlanks.EnumType.SPRUCE));
            if(!worldIn.isRemote) {
                EntityItem entityItem = new EntityItem(worldIn, (double) pos.getX(), (double) pos.getY(), (double) pos.getZ(), new ItemStack(ModCore_Urushi.UItems, 8, 57));
                worldIn.spawnEntity(entityItem);
            }
            worldIn.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_WOOD_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
            playerIn.swingArm(hand);
            playerIn.getHeldItem(hand).damageItem(1,playerIn);
            return  true;
        }
        else if(state.getValue(VARIANT)== BlockPlanks.EnumType.SPRUCE&&playerIn.getHeldItem(hand).getItem() instanceof ItemAxe){
            worldIn.setBlockState(pos,ModCore_Urushi.UStrippedLog.getDefaultState().withProperty(BlockLog.LOG_AXIS,state.getValue(BlockLog.LOG_AXIS)).withProperty(U_StrippedLog.VARIANT, BlockPlanks.EnumType.BIRCH));
            if(!worldIn.isRemote) {
                EntityItem entityItem = new EntityItem(worldIn, (double) pos.getX(), (double) pos.getY(), (double) pos.getZ(), new ItemStack(ModCore_Urushi.UItems, 8, 58));
                worldIn.spawnEntity(entityItem);
            }
            worldIn.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_WOOD_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
            playerIn.swingArm(hand);
            playerIn.getHeldItem(hand).damageItem(1,playerIn);
            return  true;
        }
        else if(blockMeta==2&&(playerIn.getHeldItem(hand).getItem()== Items.FLINT||playerIn.getHeldItem(hand).getItem() instanceof ItemTool)){
            if(facing==EnumFacing.NORTH){
                worldIn.setBlockState(pos,ModCore_Urushi.ChiseledLacquerLog.getDefaultState().withProperty(ChiseledLacquerLog.VARIANT,BlockPlanks.EnumType.OAK));
                return true;
            }else  if(facing==EnumFacing.SOUTH){
                worldIn.setBlockState(pos,ModCore_Urushi.ChiseledLacquerLog.getDefaultState().withProperty(ChiseledLacquerLog.VARIANT,BlockPlanks.EnumType.SPRUCE));
                return true;
            }else  if(facing==EnumFacing.EAST){
                worldIn.setBlockState(pos,ModCore_Urushi.ChiseledLacquerLog.getDefaultState().withProperty(ChiseledLacquerLog.VARIANT,BlockPlanks.EnumType.BIRCH));
                return true;
            }else  if(facing==EnumFacing.WEST){
                worldIn.setBlockState(pos,ModCore_Urushi.ChiseledLacquerLog.getDefaultState().withProperty(ChiseledLacquerLog.VARIANT,BlockPlanks.EnumType.JUNGLE));
                return true;
            }
        }else if(state.getValue(VARIANT)== BlockPlanks.EnumType.JUNGLE&&playerIn.getHeldItem(hand).getItem() instanceof ItemAxe){
            worldIn.setBlockState(pos,ModCore_Urushi.UStrippedLog.getDefaultState().withProperty(BlockLog.LOG_AXIS,state.getValue(BlockLog.LOG_AXIS)));
            if(!worldIn.isRemote) {
                EntityItem entityItem = new EntityItem(worldIn, (double) pos.getX(), (double) pos.getY(), (double) pos.getZ(), new ItemStack(ModCore_Urushi.UItems, 8, 37));
                worldIn.spawnEntity(entityItem);
            }
            worldIn.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_WOOD_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
            playerIn.swingArm(hand);
            playerIn.getHeldItem(hand).damageItem(1,playerIn);
        return  true;
        }
        return false;
    }
    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        int metadata=stack.getItemDamage();
        ITextComponent textComponent2=new TextComponentTranslation("item.info.lacquerlog", new Object[0]);

        if(metadata==2)tooltip.add(textComponent2.getFormattedText());
       }
}
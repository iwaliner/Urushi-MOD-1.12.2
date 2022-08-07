package urushi.Block;

import net.minecraft.block.*;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import urushi.Else.EnumType;
import urushi.ModCore_Urushi;

import javax.annotation.Nullable;
import java.util.List;

public class WoodenBucket
        extends Block
        //BlockContainer
{
    protected static final AxisAlignedBB AABB_LEGS = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.3125D, 1.0D);
    protected static final AxisAlignedBB AABB_WALL_NORTH = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.9375D, 0.125D);
    protected static final AxisAlignedBB AABB_WALL_SOUTH = new AxisAlignedBB(0.0D, 0.0D, 0.875D, 1.0D, 0.9375D, 1.0D);
    protected static final AxisAlignedBB AABB_WALL_EAST = new AxisAlignedBB(0.875D, 0.0D, 0.0D, 1.0D, 0.9375D, 1.0D);
    protected static final AxisAlignedBB AABB_WALL_WEST = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.125D, 0.9375D, 1.0D);

    public static final PropertyEnum<EnumType.EnumType16> VARIANT = PropertyEnum.<EnumType.EnumType16>create("variant", EnumType.EnumType16.class);


    public WoodenBucket()
    {
        super(Material.WOOD);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT,EnumType.EnumType16.TypeA));
        this.setCreativeTab(ModCore_Urushi.TabUrushi);
        setResistance(10F);
        setLightOpacity(255);
        setLightLevel(0.0F);
        setHardness(1.0F);
        setHarvestLevel("axe", 0);
        setSoundType(SoundType.WOOD);
        this.useNeighborBrightness=true;
    }

    public int damageDropped(IBlockState state)
    {
        return ((EnumType.EnumType16)state.getValue(VARIANT)).getMetadata();
    }

    public int tickRate(World worldIn)
    {
        return 2;
    }


    protected BlockStateContainer createBlockState()
    {
        return  new BlockStateContainer(this, new IProperty[] {VARIANT});
    }


   // public TileEntity createNewTileEntity(World worldIn, int meta)
  //  {
  //      return new TileEntityWCabinetry();
  //  }



    /**
     * Called serverside after this block is replaced with another in Chunk, but before the Tile Entity is updated
     */
    /*public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity instanceof TileEntityWCabinetry)
        {
            InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityWCabinetry)tileentity);
            worldIn.updateComparatorOutputLevel(pos, this);
        }

        super.breakBlock(worldIn, pos, state);
    }
*/


    /**
     * The type of render function called. MODEL for mixed tesr and static model, MODELBLOCK_ANIMATED for TESR-only,
     * LIQUID for vanilla liquids, INVISIBLE to skip all rendering
     */
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return ((EnumType.EnumType16)state.getValue(VARIANT)).getMapColor();
    }
    public IProperty<?> getVariantProperty()
    {
        return VARIANT;
    }
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, EnumType.EnumType16.byMetadata(meta));

        return iblockstate;
    }
    public int getMetaFromState(IBlockState state)
    {
      /*  int i = 0;
        i = i | ((EnumType.EnumType16)state.getValue(VARIANT)).getMetadata();

        return i;*/
        return ((EnumType.EnumType16)state.getValue(VARIANT)).getMetadata();
    }

    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState)
    {
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_LEGS);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_WEST);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_NORTH);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_EAST);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_SOUTH);
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return FULL_BLOCK_AABB;
    }

    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {

        int blockMeta =state.getValue(VARIANT).getMetadata();
        float f = (float)pos.getY() + (6.0F + (float)(3 * blockMeta)) / 16.0F;
if(!worldIn.isRemote && entityIn instanceof EntityItem){
    EntityItem entityItem= (EntityItem) entityIn;
    Item item=entityItem.getItem().getItem();
    ItemStack itemStack=entityItem.getItem();
    double vel=0.4D;
    int itemMeta=entityItem.getItem().getItemDamage();
    int amountItem=entityItem.getItem().getCount();
    if(item==Items.DYE){
        if(itemMeta==0&&isRawUrushi(blockMeta)){
            setUrushiLevel(worldIn, pos, state, blockMeta + 6);

                ((EntityItem) entityIn).setItem(new ItemStack(item, amountItem - 1, itemMeta));
                entityItem.motionY =vel;
            worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);

        }else if(itemMeta==1&&isRawUrushi(blockMeta)) {
            setUrushiLevel(worldIn, pos, state, blockMeta + 3);

            ((EntityItem) entityIn).setItem(new ItemStack(item, amountItem - 1, itemMeta));
            entityItem.motionY=vel;
            worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);

        }
    }else if(Block.getBlockFromItem(item) instanceof BlockPlanks&&isRedUrushi(blockMeta)){
        setUrushiLevel(worldIn, pos, state, blockMeta==4?0:blockMeta-1);
        entityItem.setItem(new ItemStack(ModCore_Urushi.UPlanks, amountItem, 6));
        entityItem.motionY =vel;
        worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

    }else if(Block.getBlockFromItem(item) instanceof BlockPlanks&&isBlackUrushi(blockMeta)){
        setUrushiLevel(worldIn, pos, state, blockMeta==7?0:blockMeta-1);
        entityItem.setItem(new ItemStack(ModCore_Urushi.UPlanks, amountItem, 8));
        entityItem.motionY =vel;
        worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

    }else if(Block.getBlockFromItem(item)==ModCore_Urushi.UPlanks){
        U_Planks uplanks= (U_Planks) Block.getBlockFromItem(item);

        if(isRedUrushi(blockMeta)&&uplanks.canStainRedPlanks(itemMeta)){
            setUrushiLevel(worldIn, pos, state, blockMeta==4?0:blockMeta-1);
            entityItem.setItem(new ItemStack(ModCore_Urushi.UPlanks, amountItem, 6));
            entityItem.motionY =vel;
            worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

        }else if(isBlackUrushi(blockMeta)&&uplanks.canStainBlackPlanks(itemMeta)){
            setUrushiLevel(worldIn, pos, state, blockMeta==7?0:blockMeta-1);
            entityItem.setItem(new ItemStack(ModCore_Urushi.UPlanks, amountItem, 8));
            entityItem.motionY =vel;
            worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

        }else if(isRedUrushi(blockMeta)&&uplanks.canStainRedSmoothPlanks(itemMeta)){
            setUrushiLevel(worldIn, pos, state, blockMeta==4?0:blockMeta-1);
            entityItem.setItem(new ItemStack(ModCore_Urushi.UPlanks, amountItem, 7));
            entityItem.motionY =vel;
            worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

        }else if(isBlackUrushi(blockMeta)&&uplanks.canStainBlackSmoothPlanks(itemMeta)){
            setUrushiLevel(worldIn, pos, state, blockMeta==7?0:blockMeta-1);
            entityItem.setItem(new ItemStack(ModCore_Urushi.UPlanks, amountItem, 9));
            entityItem.motionY =vel;
            worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }

    }else if(item==Items.WHEAT&isRawUrushi(blockMeta)) {
        setUrushiLevel(worldIn, pos, state, blockMeta-1);
            ((EntityItem) entityIn).setItem(new ItemStack(ModCore_Urushi.UItems, amountItem, 8));
            entityItem.motionY=vel;
        worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

    }else if(isWater(blockMeta)&&item==Item.getItemFromBlock(Blocks.CONCRETE_POWDER)){
        setUrushiLevel(worldIn, pos, state, blockMeta==10?0:blockMeta-1);
        entityItem.setItem(new ItemStack(Item.getItemFromBlock(Blocks.CONCRETE), amountItem, itemStack.getItemDamage()));
        entityItem.motionY =vel;
        worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
    }else if(isWater(blockMeta)&&item==Item.getItemFromBlock(Blocks.DIRT)){
        setUrushiLevel(worldIn, pos, state, blockMeta==10?0:blockMeta-1);
        entityItem.setItem(new ItemStack(Item.getItemFromBlock(Blocks.CLAY), amountItem, 0));
        entityItem.motionY =vel;
        worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
    }
}else if (!worldIn.isRemote && entityIn.isBurning()  && entityIn.getEntityBoundingBox().minY <= (double)f&&isWater(blockMeta))
{
    entityIn.extinguish();
    this.setUrushiLevel(worldIn, pos, state, blockMeta==10?0:blockMeta-1);
    worldIn.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0F, 1.0F);

}

    }

    /**
     * Called when the block is right clicked by a player.
     */
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemstack = playerIn.getHeldItem(hand);
        Item item = itemstack.getItem();
        int blockMeta = state.getValue(VARIANT).getMetadata();
        int amountItem = itemstack.getCount();

        int itemMeta = itemstack.getItemDamage();

            if (item == Items.DYE && isRawUrushi(blockMeta)) {
                if (itemMeta == 1) {
                    setUrushiLevel(worldIn, pos, state, blockMeta + 3);
                    itemstack.shrink(1);
                    worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);

                    return true;
                } else if (itemMeta == 0) {
                    setUrushiLevel(worldIn, pos, state, blockMeta + 6);
                    itemstack.shrink(1);
                    worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);

                    return true;
                }

            } else if (isRedUrushi(blockMeta)) {
                if (Block.getBlockFromItem(item) instanceof BlockPlanks) {
                    playerIn.setHeldItem(hand, new ItemStack(ModCore_Urushi.UPlanks, amountItem, 6));
                    setUrushiLevel(worldIn, pos, state, blockMeta==4?0:blockMeta-1);
                    worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

                    return true;
                }
                else if (Block.getBlockFromItem(item) ==ModCore_Urushi.UPlanks) {
                    U_Planks uPlanks= (U_Planks) Block.getBlockFromItem(item);
                    if(uPlanks.canStainRedPlanks(itemMeta)) {
                        playerIn.setHeldItem(hand, new ItemStack(ModCore_Urushi.UPlanks, amountItem, 6));
                        setUrushiLevel(worldIn, pos, state, blockMeta == 4 ? 0 : blockMeta - 1);
                        worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

                        return true;
                    }else if(uPlanks.canStainRedSmoothPlanks(itemMeta)) {
                        playerIn.setHeldItem(hand, new ItemStack(ModCore_Urushi.UPlanks, amountItem, 7));
                        setUrushiLevel(worldIn, pos, state, blockMeta == 4 ? 0 : blockMeta - 1);
                        worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

                        return true;
                    }
                }

            } else if (isBlackUrushi(blockMeta)) {
                if (Block.getBlockFromItem(item) instanceof BlockPlanks) {
                    playerIn.setHeldItem(hand, new ItemStack(ModCore_Urushi.UPlanks, amountItem, 8));
                        setUrushiLevel(worldIn, pos, state, blockMeta==7?0:blockMeta-1);
                    worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

                    return true;
                }else if (Block.getBlockFromItem(item) ==ModCore_Urushi.UPlanks) {
                    U_Planks uPlanks= (U_Planks) Block.getBlockFromItem(item);
                    if(uPlanks.canStainBlackPlanks(itemMeta)) {
                        playerIn.setHeldItem(hand, new ItemStack(ModCore_Urushi.UPlanks, amountItem, 8));
                        setUrushiLevel(worldIn, pos, state, blockMeta == 7 ? 0 : blockMeta - 1);
                        worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

                        return true;
                    }else if(uPlanks.canStainBlackSmoothPlanks(itemMeta)) {
                        playerIn.setHeldItem(hand, new ItemStack(ModCore_Urushi.UPlanks, amountItem, 9));
                        setUrushiLevel(worldIn, pos, state, blockMeta == 7 ? 0 : blockMeta - 1);
                        worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

                        return true;
                    }
                }



        }else  if (item == Items.WHEAT && isRawUrushi(blockMeta)) {
                    setUrushiLevel(worldIn, pos, state, blockMeta -1);
                playerIn.setHeldItem(hand, new ItemStack(ModCore_Urushi.UItems, amountItem, 8));
                worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

                return true;


        }else if(isWater(blockMeta)){
               if(item==Items.GLASS_BOTTLE){
                   setUrushiLevel(worldIn, pos, state,  blockMeta==10?0: blockMeta -1);
                   double vel=0.4D;
                   itemstack.shrink(1);
                 if(!worldIn.isRemote){
                     EntityItem entityItem=new EntityItem(worldIn,(double)pos.getX()+0.5D,(double)pos.getY()+0.5D,(double)pos.getZ()+0.5D);
                     ItemStack itemstackP = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.WATER);
                     ((EntityItem) entityItem).setItem(itemstackP);
                     entityItem.motionY=vel;
                     worldIn.spawnEntity(entityItem);
                 }
               worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                   return true;

               }else if(item==Items.BUCKET){
                   setUrushiLevel(worldIn, pos, state,  0);
                   itemstack.shrink(1);
                   double vel=0.4D;
                   if(!worldIn.isRemote){
                       EntityItem entityItem=new EntityItem(worldIn,(double)pos.getX()+0.5D,(double)pos.getY()+0.5D,(double)pos.getZ()+0.5D);
                       ItemStack itemstackP = new ItemStack(Items.WATER_BUCKET);
                       ((EntityItem) entityItem).setItem(itemstackP);
                       entityItem.motionY=vel;
                       worldIn.spawnEntity(entityItem);
                   }
                   worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                   return true;

               }else if(item==Items.POTIONITEM&&blockMeta!=12){
                   setUrushiLevel(worldIn, pos, state,  blockMeta+1);
                   playerIn.setHeldItem(hand, new ItemStack(Items.GLASS_BOTTLE, 1, 0));
                   worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                   return true;
               }else if(item==Item.getItemFromBlock(Blocks.CONCRETE_POWDER)){
                   setUrushiLevel(worldIn, pos, state,  blockMeta==10?0: blockMeta -1);
                   playerIn.setHeldItem(hand, new ItemStack(Blocks.CONCRETE, amountItem, itemstack.getItemDamage()));
                   worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                   return true;
               }else if(item==Item.getItemFromBlock(Blocks.DIRT)){
                   setUrushiLevel(worldIn, pos, state,  blockMeta==10?0: blockMeta -1);
                   playerIn.setHeldItem(hand, new ItemStack(Blocks.CLAY, amountItem, 0));
                   worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                   return true;
               }
            }else if(isEmpty(blockMeta)&&item==Items.WATER_BUCKET){
                setUrushiLevel(worldIn, pos, state,  12);
                playerIn.setHeldItem(hand, new ItemStack(Items.BUCKET, 1, 0));
                worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                return true;
            }else if(isEmpty(blockMeta)&&item==Items.POTIONITEM){
                setUrushiLevel(worldIn, pos, state,  10);
                playerIn.setHeldItem(hand, new ItemStack(Items.GLASS_BOTTLE, 1, 0));
                worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                return true;
            }
        return false;
    }
    public void setUrushiLevel(World worldIn, BlockPos pos, IBlockState state, int level)
    {
        worldIn.setBlockState(pos, state.getBlock().getStateFromMeta(level));
        worldIn.updateComparatorOutputLevel(pos, this);


    }
    public boolean hasComparatorInputOverride(IBlockState state)
    {
        return true;
    }

    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos)
    {
        return blockState.getValue(VARIANT).getMetadata();
    }
    public boolean isEmpty(int meta){
        return meta==0? true:false;
    }
    public boolean isRawUrushi(int meta){
        return (meta==1||meta==2||meta==3)? true:false;
    }
    public boolean isRedUrushi(int meta){
        return (meta==4||meta==5||meta==6)? true:false;
    }
    public boolean isBlackUrushi(int meta){
        return (meta==7||meta==8||meta==9)? true:false;
    }
    public boolean isWater(int meta){
        return (meta==10||meta==11||meta==12)? true:false;
    }


    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        int metadata=stack.getItemDamage();
        ITextComponent textComponentA=new TextComponentTranslation("item.info.woodenbucket1", new Object[0]);
        ITextComponent textComponentB=new TextComponentTranslation("item.info.woodenbucket2", new Object[0]);
        ITextComponent textComponentC=new TextComponentTranslation("item.info.woodenbucket3", new Object[0]);
        ITextComponent textComponentD=new TextComponentTranslation("item.info.woodenbucket4", new Object[0]);
        if(metadata==0){
            tooltip.add(textComponentA.getFormattedText());
            tooltip.add(textComponentB.getFormattedText());
            tooltip.add(textComponentC.getFormattedText());
            tooltip.add(textComponentD.getFormattedText());

        }
          }
}
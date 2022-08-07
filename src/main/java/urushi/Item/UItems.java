package urushi.Item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.*;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBed;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import urushi.Block.Futon;
import urushi.Block.SlideDoorBase;
import urushi.Entity.EntityCushion;
import urushi.ModCore_Urushi;

import javax.annotation.Nullable;
import java.util.List;

public class UItems extends Item  {
	public static final PropertyDirection FACING = BlockHorizontal.FACING;

	public UItems() {
		        super();
		        this.setRegistryName(ModCore_Urushi.modid, "u_items");
		        this.setCreativeTab(ModCore_Urushi.TabUrushi);
		        this.setUnlocalizedName("UItems");
		        this.setMaxDamage(0);
		        this.setHasSubtypes(true);

		    }
		 public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
		    {
		       if (this.isInCreativeTab(tab))
		        {
		        	for(int i=0;i<62;i++) {

	items.add(new ItemStack(this, 1, i));

		        	}

		        }
		    }
		 @Override
			public String getUnlocalizedName(ItemStack stack) {
				return super.getUnlocalizedName()+"."+ +stack.getItemDamage();
			}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
			 int metadata=stack.getItemDamage();
		ITextComponent textComponent0=new TextComponentTranslation("item.info.bamboo", new Object[0]);
		ITextComponent textComponent8=new TextComponentTranslation("item.info.urushiglue", new Object[0]);

		if(metadata==0)tooltip.add(textComponent0.getFormattedText());
		else if(metadata==8)tooltip.add(textComponent8.getFormattedText());
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
			 int meta=player.getHeldItem(hand).getItemDamage();
			 ItemStack itemStack=player.getHeldItem(hand);

		if(!worldIn.isRemote&&facing==EnumFacing.UP&&worldIn.getBlockState(pos.add(0,1,0)).getBlock() instanceof BlockAir){
			if(worldIn.getBlockState(pos.add(0,2,0)).getBlock() instanceof BlockAir) {
			if (meta == 11) {
				if (!player.isSneaking()) {
					worldIn.setBlockState(pos.add(0, 1, 0), ModCore_Urushi.OakShouji.getDefaultState().withProperty(FACING, player.getHorizontalFacing().getOpposite()).withProperty(SlideDoorBase.UNDERUPPER, SlideDoorBase.EnumTypeSlideDoorUnderUpper.UNDER));
					worldIn.setBlockState(pos.add(0, 2, 0), ModCore_Urushi.OakShouji.getDefaultState().withProperty(FACING, player.getHorizontalFacing().getOpposite()).withProperty(SlideDoorBase.UNDERUPPER, SlideDoorBase.EnumTypeSlideDoorUnderUpper.UPPER));
					player.swingArm(hand);
					itemStack.shrink(1);
					worldIn.playSound((EntityPlayer)null,pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);

					return EnumActionResult.SUCCESS;
				} else {
					worldIn.setBlockState(pos.add(0, 1, 0), ModCore_Urushi.OakShouji.getDefaultState().withProperty(FACING, player.getHorizontalFacing()).withProperty(SlideDoorBase.UNDERUPPER, SlideDoorBase.EnumTypeSlideDoorUnderUpper.UNDER));
					worldIn.setBlockState(pos.add(0, 2, 0), ModCore_Urushi.OakShouji.getDefaultState().withProperty(FACING, player.getHorizontalFacing()).withProperty(SlideDoorBase.UNDERUPPER, SlideDoorBase.EnumTypeSlideDoorUnderUpper.UPPER));
					player.swingArm(hand);
					itemStack.shrink(1);
					worldIn.playSound((EntityPlayer)null,pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);

					return EnumActionResult.SUCCESS;
				}
			} else if (meta == 12) {
				if (!player.isSneaking()) {
					worldIn.setBlockState(pos.add(0, 1, 0), ModCore_Urushi.SpruceShouji.getDefaultState().withProperty(FACING, player.getHorizontalFacing().getOpposite()).withProperty(SlideDoorBase.UNDERUPPER, SlideDoorBase.EnumTypeSlideDoorUnderUpper.UNDER));
					worldIn.setBlockState(pos.add(0, 2, 0), ModCore_Urushi.SpruceShouji.getDefaultState().withProperty(FACING, player.getHorizontalFacing().getOpposite()).withProperty(SlideDoorBase.UNDERUPPER, SlideDoorBase.EnumTypeSlideDoorUnderUpper.UPPER));
					player.swingArm(hand);
					itemStack.shrink(1);
					worldIn.playSound((EntityPlayer)null,pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);

					return EnumActionResult.SUCCESS;
				} else {
					worldIn.setBlockState(pos.add(0, 1, 0), ModCore_Urushi.SpruceShouji.getDefaultState().withProperty(FACING, player.getHorizontalFacing()).withProperty(SlideDoorBase.UNDERUPPER, SlideDoorBase.EnumTypeSlideDoorUnderUpper.UNDER));
					worldIn.setBlockState(pos.add(0, 2, 0), ModCore_Urushi.SpruceShouji.getDefaultState().withProperty(FACING, player.getHorizontalFacing()).withProperty(SlideDoorBase.UNDERUPPER, SlideDoorBase.EnumTypeSlideDoorUnderUpper.UPPER));
					player.swingArm(hand);
					itemStack.shrink(1);
					worldIn.playSound((EntityPlayer)null,pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);

					return EnumActionResult.SUCCESS;
				}
			} else if (meta == 13) {
				if (!player.isSneaking()) {
					worldIn.setBlockState(pos.add(0, 1, 0), ModCore_Urushi.RedShouji.getDefaultState().withProperty(FACING, player.getHorizontalFacing().getOpposite()).withProperty(SlideDoorBase.UNDERUPPER, SlideDoorBase.EnumTypeSlideDoorUnderUpper.UNDER));
					worldIn.setBlockState(pos.add(0, 2, 0), ModCore_Urushi.RedShouji.getDefaultState().withProperty(FACING, player.getHorizontalFacing().getOpposite()).withProperty(SlideDoorBase.UNDERUPPER, SlideDoorBase.EnumTypeSlideDoorUnderUpper.UPPER));
					player.swingArm(hand);
					itemStack.shrink(1);
					worldIn.playSound((EntityPlayer)null,pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);

					return EnumActionResult.SUCCESS;
				} else {
					worldIn.setBlockState(pos.add(0, 1, 0), ModCore_Urushi.RedShouji.getDefaultState().withProperty(FACING, player.getHorizontalFacing()).withProperty(SlideDoorBase.UNDERUPPER, SlideDoorBase.EnumTypeSlideDoorUnderUpper.UNDER));
					worldIn.setBlockState(pos.add(0, 2, 0), ModCore_Urushi.RedShouji.getDefaultState().withProperty(FACING, player.getHorizontalFacing()).withProperty(SlideDoorBase.UNDERUPPER, SlideDoorBase.EnumTypeSlideDoorUnderUpper.UPPER));
					player.swingArm(hand);
					itemStack.shrink(1);
					worldIn.playSound((EntityPlayer)null,pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);

					return EnumActionResult.SUCCESS;
				}
			} else if (meta == 7) {
				if (!player.isSneaking()) {
					worldIn.setBlockState(pos.add(0, 1, 0), ModCore_Urushi.SlidingGlassDoor.getDefaultState().withProperty(FACING, player.getHorizontalFacing().getOpposite()).withProperty(SlideDoorBase.UNDERUPPER, SlideDoorBase.EnumTypeSlideDoorUnderUpper.UNDER));
					worldIn.setBlockState(pos.add(0, 2, 0), ModCore_Urushi.SlidingGlassDoor.getDefaultState().withProperty(FACING, player.getHorizontalFacing().getOpposite()).withProperty(SlideDoorBase.UNDERUPPER, SlideDoorBase.EnumTypeSlideDoorUnderUpper.UPPER));
					player.swingArm(hand);
					itemStack.shrink(1);
					worldIn.playSound((EntityPlayer)null,pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);

					return EnumActionResult.SUCCESS;
				} else {
					worldIn.setBlockState(pos.add(0, 1, 0), ModCore_Urushi.SlidingGlassDoor.getDefaultState().withProperty(FACING, player.getHorizontalFacing()).withProperty(SlideDoorBase.UNDERUPPER, SlideDoorBase.EnumTypeSlideDoorUnderUpper.UNDER));
					worldIn.setBlockState(pos.add(0, 2, 0), ModCore_Urushi.SlidingGlassDoor.getDefaultState().withProperty(FACING, player.getHorizontalFacing()).withProperty(SlideDoorBase.UNDERUPPER, SlideDoorBase.EnumTypeSlideDoorUnderUpper.UPPER));
					player.swingArm(hand);
					itemStack.shrink(1);
					worldIn.playSound((EntityPlayer)null,pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);

					return EnumActionResult.SUCCESS;
				}
			} else if (meta == 30) {
				if (!player.isSneaking()) {
					worldIn.setBlockState(pos.add(0, 1, 0), ModCore_Urushi.BlankFusuma.getDefaultState().withProperty(FACING, player.getHorizontalFacing().getOpposite()).withProperty(SlideDoorBase.UNDERUPPER, SlideDoorBase.EnumTypeSlideDoorUnderUpper.UNDER));
					worldIn.setBlockState(pos.add(0, 2, 0), ModCore_Urushi.BlankFusuma.getDefaultState().withProperty(FACING, player.getHorizontalFacing().getOpposite()).withProperty(SlideDoorBase.UNDERUPPER, SlideDoorBase.EnumTypeSlideDoorUnderUpper.UPPER));
					player.swingArm(hand);
					itemStack.shrink(1);
					worldIn.playSound((EntityPlayer)null,pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);

					return EnumActionResult.SUCCESS;
				} else {
					worldIn.setBlockState(pos.add(0, 1, 0), ModCore_Urushi.BlankFusuma.getDefaultState().withProperty(FACING, player.getHorizontalFacing()).withProperty(SlideDoorBase.UNDERUPPER, SlideDoorBase.EnumTypeSlideDoorUnderUpper.UNDER));
					worldIn.setBlockState(pos.add(0, 2, 0), ModCore_Urushi.BlankFusuma.getDefaultState().withProperty(FACING, player.getHorizontalFacing()).withProperty(SlideDoorBase.UNDERUPPER, SlideDoorBase.EnumTypeSlideDoorUnderUpper.UPPER));
					player.swingArm(hand);
					itemStack.shrink(1);
					worldIn.playSound((EntityPlayer)null,pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);

					return EnumActionResult.SUCCESS;
				}
			}else if (meta == 53) {
				if (!player.isSneaking()) {
					worldIn.setBlockState(pos.add(0, 1, 0), ModCore_Urushi.BlueSeigaihaFusuma.getDefaultState().withProperty(FACING, player.getHorizontalFacing().getOpposite()).withProperty(SlideDoorBase.UNDERUPPER, SlideDoorBase.EnumTypeSlideDoorUnderUpper.UNDER));
					worldIn.setBlockState(pos.add(0, 2, 0), ModCore_Urushi.BlueSeigaihaFusuma.getDefaultState().withProperty(FACING, player.getHorizontalFacing().getOpposite()).withProperty(SlideDoorBase.UNDERUPPER, SlideDoorBase.EnumTypeSlideDoorUnderUpper.UPPER));
					player.swingArm(hand);
					itemStack.shrink(1);
					worldIn.playSound((EntityPlayer)null,pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);

					return EnumActionResult.SUCCESS;
				} else {
					worldIn.setBlockState(pos.add(0, 1, 0), ModCore_Urushi.BlueSeigaihaFusuma.getDefaultState().withProperty(FACING, player.getHorizontalFacing()).withProperty(SlideDoorBase.UNDERUPPER, SlideDoorBase.EnumTypeSlideDoorUnderUpper.UNDER));
					worldIn.setBlockState(pos.add(0, 2, 0), ModCore_Urushi.BlueSeigaihaFusuma.getDefaultState().withProperty(FACING, player.getHorizontalFacing()).withProperty(SlideDoorBase.UNDERUPPER, SlideDoorBase.EnumTypeSlideDoorUnderUpper.UPPER));
					player.swingArm(hand);
					itemStack.shrink(1);
					worldIn.playSound((EntityPlayer)null,pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);

					return EnumActionResult.SUCCESS;
				}
			}else if (meta == 54) {
				if (!player.isSneaking()) {
					worldIn.setBlockState(pos.add(0, 1, 0), ModCore_Urushi.BlueSayagataFusuma.getDefaultState().withProperty(FACING, player.getHorizontalFacing().getOpposite()).withProperty(SlideDoorBase.UNDERUPPER, SlideDoorBase.EnumTypeSlideDoorUnderUpper.UNDER));
					worldIn.setBlockState(pos.add(0, 2, 0), ModCore_Urushi.BlueSayagataFusuma.getDefaultState().withProperty(FACING, player.getHorizontalFacing().getOpposite()).withProperty(SlideDoorBase.UNDERUPPER, SlideDoorBase.EnumTypeSlideDoorUnderUpper.UPPER));
					player.swingArm(hand);
					itemStack.shrink(1);
					worldIn.playSound((EntityPlayer)null,pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);

					return EnumActionResult.SUCCESS;
				} else {
					worldIn.setBlockState(pos.add(0, 1, 0), ModCore_Urushi.BlueSayagataFusuma.getDefaultState().withProperty(FACING, player.getHorizontalFacing()).withProperty(SlideDoorBase.UNDERUPPER, SlideDoorBase.EnumTypeSlideDoorUnderUpper.UNDER));
					worldIn.setBlockState(pos.add(0, 2, 0), ModCore_Urushi.BlueSayagataFusuma.getDefaultState().withProperty(FACING, player.getHorizontalFacing()).withProperty(SlideDoorBase.UNDERUPPER, SlideDoorBase.EnumTypeSlideDoorUnderUpper.UPPER));
					player.swingArm(hand);
					itemStack.shrink(1);
					worldIn.playSound((EntityPlayer)null,pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);

					return EnumActionResult.SUCCESS;
				}
			}else if (meta == 6) {

				IBlockState iblockstate = worldIn.getBlockState(pos);
				Block block = iblockstate.getBlock();

				if (!block.isReplaceable(worldIn, pos))
				{
					pos = pos.offset(facing);
				}

				ItemStack itemstack = player.getHeldItem(hand);

				if (player.canPlayerEdit(pos, facing, itemstack) &&ModCore_Urushi.RedUrushiDoor.canPlaceBlockAt(worldIn, pos))
				{
					EnumFacing enumfacing = EnumFacing.fromAngle((double)player.rotationYaw);
					int i = enumfacing.getFrontOffsetX();
					int j = enumfacing.getFrontOffsetZ();
					boolean flag = i < 0 && hitZ < 0.5F || i > 0 && hitZ > 0.5F || j < 0 && hitX > 0.5F || j > 0 && hitX < 0.5F;
					ItemDoor.placeDoor(worldIn, pos, enumfacing, ModCore_Urushi.RedUrushiDoor, flag);
					worldIn.playSound((EntityPlayer)null,pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
					itemstack.shrink(1);
					return EnumActionResult.SUCCESS;
				}
				else
				{
					return EnumActionResult.FAIL;
				}

			}

		}

			 if(meta>13&&meta<30){

					EntityCushion entityCushion=new EntityCushion(worldIn,pos.getX()+0.5,pos.getY()+1.0,pos.getZ()+0.5);
					entityCushion.rotationYaw=player.rotationYaw;
					entityCushion.setColorType(EntityCushion.Type.byId(meta-14));
					worldIn.spawnEntity(entityCushion);
				 worldIn.playSound((EntityPlayer)null,pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);

				 itemStack.shrink(1);


					return EnumActionResult.SUCCESS;

			}else if(meta==31){

					worldIn.setBlockState(pos.add(0, 1, 0), ModCore_Urushi.Andon.getDefaultState());
					player.swingArm(hand);
					itemStack.shrink(1);
				 worldIn.playSound((EntityPlayer)null,pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);

				 return EnumActionResult.SUCCESS;

			}else if(meta==32){

				worldIn.setBlockState(pos.add(0, 1, 0), ModCore_Urushi.AriakeAndon.getDefaultState().withProperty(FACING,player.getHorizontalFacing().getOpposite()));
				player.swingArm(hand);
				itemStack.shrink(1);
				 worldIn.playSound((EntityPlayer)null,pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);

				 return EnumActionResult.SUCCESS;

			}else if(meta==0){

				worldIn.setBlockState(pos.add(0, 1, 0), ModCore_Urushi.JapaneseTimberBamboo.getDefaultState());
				player.swingArm(hand);
				itemStack.shrink(1);
				 worldIn.playSound((EntityPlayer)null,pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);

				 return EnumActionResult.SUCCESS;

			}else if(meta==33){

				 IBlockState iblockstate = worldIn.getBlockState(pos);
				 Block block = iblockstate.getBlock();
				 boolean flag = block.isReplaceable(worldIn, pos);

				 if (!flag)
				 {
					 pos = pos.up();
				 }

				 int i = MathHelper.floor((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
				 EnumFacing enumfacing = EnumFacing.getHorizontal(i);
				 BlockPos blockpos = pos.offset(enumfacing);
				 ItemStack itemstack = player.getHeldItem(hand);

				 if (player.canPlayerEdit(pos, facing, itemstack) && player.canPlayerEdit(blockpos, facing, itemstack))
				 {
					 IBlockState iblockstate1 = worldIn.getBlockState(blockpos);
					 boolean flag1 = iblockstate1.getBlock().isReplaceable(worldIn, blockpos);
					 boolean flag2 = flag || worldIn.isAirBlock(pos);
					 boolean flag3 = flag1 || worldIn.isAirBlock(blockpos);

					 if (flag2 && flag3 && worldIn.getBlockState(pos.down()).isTopSolid() && worldIn.getBlockState(blockpos.down()).isTopSolid())
					 {
						 IBlockState iblockstate2 = ModCore_Urushi.Futon.getDefaultState().withProperty(Futon.OCCUPIED, Boolean.valueOf(false)).withProperty(Futon.FACING, enumfacing).withProperty(Futon.PART, Futon.EnumPartType.FOOT);
						 worldIn.setBlockState(pos, iblockstate2, 10);
						 worldIn.setBlockState(blockpos, iblockstate2.withProperty(Futon.PART, Futon.EnumPartType.HEAD), 10);
						 SoundType soundtype = iblockstate2.getBlock().getSoundType(iblockstate2, worldIn, pos, player);
						 worldIn.playSound((EntityPlayer)null,pos, SoundEvents.BLOCK_CLOTH_PLACE, SoundCategory.PLAYERS, 1.0F, 1.0F);
						 TileEntity tileentity = worldIn.getTileEntity(blockpos);

						 if (tileentity instanceof TileEntityBed)
						 {
							 ((TileEntityBed)tileentity).setItemValues(itemstack);
						 }

						 TileEntity tileentity1 = worldIn.getTileEntity(pos);

						 if (tileentity1 instanceof TileEntityBed)
						 {
							 ((TileEntityBed)tileentity1).setItemValues(itemstack);
						 }

						 worldIn.notifyNeighborsRespectDebug(pos, block, false);
						 worldIn.notifyNeighborsRespectDebug(blockpos, iblockstate1.getBlock(), false);

						 if (player instanceof EntityPlayerMP)
						 {
							 CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos, itemstack);
						 }

						 itemstack.shrink(1);
						 return EnumActionResult.SUCCESS;
					 }
					 else
					 {
						 return EnumActionResult.FAIL;
					 }
				 }
				 else
				 {
					 return EnumActionResult.FAIL;
				 }

			 }else if(meta==38){

				 IBlockState iblockstate = worldIn.getBlockState(pos);
				 Block block = iblockstate.getBlock();
				 boolean flag = block.isReplaceable(worldIn, pos);

				 if (!flag)
				 {
					 pos = pos.up();
				 }

				 int i = MathHelper.floor((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
				 EnumFacing enumfacing = EnumFacing.getHorizontal(i);
				 BlockPos blockpos = pos.offset(enumfacing);
				 ItemStack itemstack = player.getHeldItem(hand);

				 if (player.canPlayerEdit(pos, facing, itemstack) && player.canPlayerEdit(blockpos, facing, itemstack))
				 {
					 IBlockState iblockstate1 = worldIn.getBlockState(blockpos);
					 boolean flag1 = iblockstate1.getBlock().isReplaceable(worldIn, blockpos);
					 boolean flag2 = flag || worldIn.isAirBlock(pos);
					 boolean flag3 = flag1 || worldIn.isAirBlock(blockpos);

					 if (flag2 && flag3 && worldIn.getBlockState(pos.down()).isTopSolid() && worldIn.getBlockState(blockpos.down()).isTopSolid())
					 {
						 IBlockState iblockstate2 = ModCore_Urushi.WhiteFuton.getDefaultState().withProperty(Futon.OCCUPIED, Boolean.valueOf(false)).withProperty(Futon.FACING, enumfacing).withProperty(Futon.PART, Futon.EnumPartType.FOOT);
						 worldIn.setBlockState(pos, iblockstate2, 10);
						 worldIn.setBlockState(blockpos, iblockstate2.withProperty(Futon.PART, Futon.EnumPartType.HEAD), 10);
						 SoundType soundtype = iblockstate2.getBlock().getSoundType(iblockstate2, worldIn, pos, player);
						 worldIn.playSound((EntityPlayer)null,pos, SoundEvents.BLOCK_CLOTH_PLACE, SoundCategory.PLAYERS, 1.0F, 1.0F);
						 TileEntity tileentity = worldIn.getTileEntity(blockpos);

						 if (tileentity instanceof TileEntityBed)
						 {
							 ((TileEntityBed)tileentity).setItemValues(itemstack);
						 }

						 TileEntity tileentity1 = worldIn.getTileEntity(pos);

						 if (tileentity1 instanceof TileEntityBed)
						 {
							 ((TileEntityBed)tileentity1).setItemValues(itemstack);
						 }

						 worldIn.notifyNeighborsRespectDebug(pos, block, false);
						 worldIn.notifyNeighborsRespectDebug(blockpos, iblockstate1.getBlock(), false);

						 if (player instanceof EntityPlayerMP)
						 {
							 CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos, itemstack);
						 }

						 itemstack.shrink(1);
						 return EnumActionResult.SUCCESS;
					 }
					 else
					 {
						 return EnumActionResult.FAIL;
					 }
				 }
				 else
				 {
					 return EnumActionResult.FAIL;
				 }

			 }else if(meta==39){

				 IBlockState iblockstate = worldIn.getBlockState(pos);
				 Block block = iblockstate.getBlock();
				 boolean flag = block.isReplaceable(worldIn, pos);

				 if (!flag)
				 {
					 pos = pos.up();
				 }

				 int i = MathHelper.floor((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
				 EnumFacing enumfacing = EnumFacing.getHorizontal(i);
				 BlockPos blockpos = pos.offset(enumfacing);
				 ItemStack itemstack = player.getHeldItem(hand);

				 if (player.canPlayerEdit(pos, facing, itemstack) && player.canPlayerEdit(blockpos, facing, itemstack))
				 {
					 IBlockState iblockstate1 = worldIn.getBlockState(blockpos);
					 boolean flag1 = iblockstate1.getBlock().isReplaceable(worldIn, blockpos);
					 boolean flag2 = flag || worldIn.isAirBlock(pos);
					 boolean flag3 = flag1 || worldIn.isAirBlock(blockpos);

					 if (flag2 && flag3 && worldIn.getBlockState(pos.down()).isTopSolid() && worldIn.getBlockState(blockpos.down()).isTopSolid())
					 {
						 IBlockState iblockstate2 = ModCore_Urushi.OrangeFuton.getDefaultState().withProperty(Futon.OCCUPIED, Boolean.valueOf(false)).withProperty(Futon.FACING, enumfacing).withProperty(Futon.PART, Futon.EnumPartType.FOOT);
						 worldIn.setBlockState(pos, iblockstate2, 10);
						 worldIn.setBlockState(blockpos, iblockstate2.withProperty(Futon.PART, Futon.EnumPartType.HEAD), 10);
						 SoundType soundtype = iblockstate2.getBlock().getSoundType(iblockstate2, worldIn, pos, player);
						 worldIn.playSound((EntityPlayer)null,pos, SoundEvents.BLOCK_CLOTH_PLACE, SoundCategory.PLAYERS, 1.0F, 1.0F);
						 TileEntity tileentity = worldIn.getTileEntity(blockpos);

						 if (tileentity instanceof TileEntityBed)
						 {
							 ((TileEntityBed)tileentity).setItemValues(itemstack);
						 }

						 TileEntity tileentity1 = worldIn.getTileEntity(pos);

						 if (tileentity1 instanceof TileEntityBed)
						 {
							 ((TileEntityBed)tileentity1).setItemValues(itemstack);
						 }

						 worldIn.notifyNeighborsRespectDebug(pos, block, false);
						 worldIn.notifyNeighborsRespectDebug(blockpos, iblockstate1.getBlock(), false);

						 if (player instanceof EntityPlayerMP)
						 {
							 CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos, itemstack);
						 }

						 itemstack.shrink(1);
						 return EnumActionResult.SUCCESS;
					 }
					 else
					 {
						 return EnumActionResult.FAIL;
					 }
				 }
				 else
				 {
					 return EnumActionResult.FAIL;
				 }

			 }else if(meta==40){

				 IBlockState iblockstate = worldIn.getBlockState(pos);
				 Block block = iblockstate.getBlock();
				 boolean flag = block.isReplaceable(worldIn, pos);

				 if (!flag)
				 {
					 pos = pos.up();
				 }

				 int i = MathHelper.floor((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
				 EnumFacing enumfacing = EnumFacing.getHorizontal(i);
				 BlockPos blockpos = pos.offset(enumfacing);
				 ItemStack itemstack = player.getHeldItem(hand);

				 if (player.canPlayerEdit(pos, facing, itemstack) && player.canPlayerEdit(blockpos, facing, itemstack))
				 {
					 IBlockState iblockstate1 = worldIn.getBlockState(blockpos);
					 boolean flag1 = iblockstate1.getBlock().isReplaceable(worldIn, blockpos);
					 boolean flag2 = flag || worldIn.isAirBlock(pos);
					 boolean flag3 = flag1 || worldIn.isAirBlock(blockpos);

					 if (flag2 && flag3 && worldIn.getBlockState(pos.down()).isTopSolid() && worldIn.getBlockState(blockpos.down()).isTopSolid())
					 {
						 IBlockState iblockstate2 = ModCore_Urushi.MagentaFuton.getDefaultState().withProperty(Futon.OCCUPIED, Boolean.valueOf(false)).withProperty(Futon.FACING, enumfacing).withProperty(Futon.PART, Futon.EnumPartType.FOOT);
						 worldIn.setBlockState(pos, iblockstate2, 10);
						 worldIn.setBlockState(blockpos, iblockstate2.withProperty(Futon.PART, Futon.EnumPartType.HEAD), 10);
						 SoundType soundtype = iblockstate2.getBlock().getSoundType(iblockstate2, worldIn, pos, player);
						 worldIn.playSound((EntityPlayer)null,pos, SoundEvents.BLOCK_CLOTH_PLACE, SoundCategory.PLAYERS, 1.0F, 1.0F);
						 TileEntity tileentity = worldIn.getTileEntity(blockpos);

						 if (tileentity instanceof TileEntityBed)
						 {
							 ((TileEntityBed)tileentity).setItemValues(itemstack);
						 }

						 TileEntity tileentity1 = worldIn.getTileEntity(pos);

						 if (tileentity1 instanceof TileEntityBed)
						 {
							 ((TileEntityBed)tileentity1).setItemValues(itemstack);
						 }

						 worldIn.notifyNeighborsRespectDebug(pos, block, false);
						 worldIn.notifyNeighborsRespectDebug(blockpos, iblockstate1.getBlock(), false);

						 if (player instanceof EntityPlayerMP)
						 {
							 CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos, itemstack);
						 }

						 itemstack.shrink(1);
						 return EnumActionResult.SUCCESS;
					 }
					 else
					 {
						 return EnumActionResult.FAIL;
					 }
				 }
				 else
				 {
					 return EnumActionResult.FAIL;
				 }

			 }else if(meta==41){

				 IBlockState iblockstate = worldIn.getBlockState(pos);
				 Block block = iblockstate.getBlock();
				 boolean flag = block.isReplaceable(worldIn, pos);

				 if (!flag)
				 {
					 pos = pos.up();
				 }

				 int i = MathHelper.floor((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
				 EnumFacing enumfacing = EnumFacing.getHorizontal(i);
				 BlockPos blockpos = pos.offset(enumfacing);
				 ItemStack itemstack = player.getHeldItem(hand);

				 if (player.canPlayerEdit(pos, facing, itemstack) && player.canPlayerEdit(blockpos, facing, itemstack))
				 {
					 IBlockState iblockstate1 = worldIn.getBlockState(blockpos);
					 boolean flag1 = iblockstate1.getBlock().isReplaceable(worldIn, blockpos);
					 boolean flag2 = flag || worldIn.isAirBlock(pos);
					 boolean flag3 = flag1 || worldIn.isAirBlock(blockpos);

					 if (flag2 && flag3 && worldIn.getBlockState(pos.down()).isTopSolid() && worldIn.getBlockState(blockpos.down()).isTopSolid())
					 {
						 IBlockState iblockstate2 = ModCore_Urushi.LightBlueFuton.getDefaultState().withProperty(Futon.OCCUPIED, Boolean.valueOf(false)).withProperty(Futon.FACING, enumfacing).withProperty(Futon.PART, Futon.EnumPartType.FOOT);
						 worldIn.setBlockState(pos, iblockstate2, 10);
						 worldIn.setBlockState(blockpos, iblockstate2.withProperty(Futon.PART, Futon.EnumPartType.HEAD), 10);
						 SoundType soundtype = iblockstate2.getBlock().getSoundType(iblockstate2, worldIn, pos, player);
						 worldIn.playSound((EntityPlayer)null,pos, SoundEvents.BLOCK_CLOTH_PLACE, SoundCategory.PLAYERS, 1.0F, 1.0F);
						 TileEntity tileentity = worldIn.getTileEntity(blockpos);

						 if (tileentity instanceof TileEntityBed)
						 {
							 ((TileEntityBed)tileentity).setItemValues(itemstack);
						 }

						 TileEntity tileentity1 = worldIn.getTileEntity(pos);

						 if (tileentity1 instanceof TileEntityBed)
						 {
							 ((TileEntityBed)tileentity1).setItemValues(itemstack);
						 }

						 worldIn.notifyNeighborsRespectDebug(pos, block, false);
						 worldIn.notifyNeighborsRespectDebug(blockpos, iblockstate1.getBlock(), false);

						 if (player instanceof EntityPlayerMP)
						 {
							 CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos, itemstack);
						 }

						 itemstack.shrink(1);
						 return EnumActionResult.SUCCESS;
					 }
					 else
					 {
						 return EnumActionResult.FAIL;
					 }
				 }
				 else
				 {
					 return EnumActionResult.FAIL;
				 }

			 }else if(meta==42){

				 IBlockState iblockstate = worldIn.getBlockState(pos);
				 Block block = iblockstate.getBlock();
				 boolean flag = block.isReplaceable(worldIn, pos);

				 if (!flag)
				 {
					 pos = pos.up();
				 }

				 int i = MathHelper.floor((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
				 EnumFacing enumfacing = EnumFacing.getHorizontal(i);
				 BlockPos blockpos = pos.offset(enumfacing);
				 ItemStack itemstack = player.getHeldItem(hand);

				 if (player.canPlayerEdit(pos, facing, itemstack) && player.canPlayerEdit(blockpos, facing, itemstack))
				 {
					 IBlockState iblockstate1 = worldIn.getBlockState(blockpos);
					 boolean flag1 = iblockstate1.getBlock().isReplaceable(worldIn, blockpos);
					 boolean flag2 = flag || worldIn.isAirBlock(pos);
					 boolean flag3 = flag1 || worldIn.isAirBlock(blockpos);

					 if (flag2 && flag3 && worldIn.getBlockState(pos.down()).isTopSolid() && worldIn.getBlockState(blockpos.down()).isTopSolid())
					 {
						 IBlockState iblockstate2 = ModCore_Urushi.YellowFuton.getDefaultState().withProperty(Futon.OCCUPIED, Boolean.valueOf(false)).withProperty(Futon.FACING, enumfacing).withProperty(Futon.PART, Futon.EnumPartType.FOOT);
						 worldIn.setBlockState(pos, iblockstate2, 10);
						 worldIn.setBlockState(blockpos, iblockstate2.withProperty(Futon.PART, Futon.EnumPartType.HEAD), 10);
						 SoundType soundtype = iblockstate2.getBlock().getSoundType(iblockstate2, worldIn, pos, player);
						 worldIn.playSound((EntityPlayer)null,pos, SoundEvents.BLOCK_CLOTH_PLACE, SoundCategory.PLAYERS, 1.0F, 1.0F);
						 TileEntity tileentity = worldIn.getTileEntity(blockpos);

						 if (tileentity instanceof TileEntityBed)
						 {
							 ((TileEntityBed)tileentity).setItemValues(itemstack);
						 }

						 TileEntity tileentity1 = worldIn.getTileEntity(pos);

						 if (tileentity1 instanceof TileEntityBed)
						 {
							 ((TileEntityBed)tileentity1).setItemValues(itemstack);
						 }

						 worldIn.notifyNeighborsRespectDebug(pos, block, false);
						 worldIn.notifyNeighborsRespectDebug(blockpos, iblockstate1.getBlock(), false);

						 if (player instanceof EntityPlayerMP)
						 {
							 CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos, itemstack);
						 }

						 itemstack.shrink(1);
						 return EnumActionResult.SUCCESS;
					 }
					 else
					 {
						 return EnumActionResult.FAIL;
					 }
				 }
				 else
				 {
					 return EnumActionResult.FAIL;
				 }

			 }else if(meta==43){

				 IBlockState iblockstate = worldIn.getBlockState(pos);
				 Block block = iblockstate.getBlock();
				 boolean flag = block.isReplaceable(worldIn, pos);

				 if (!flag)
				 {
					 pos = pos.up();
				 }

				 int i = MathHelper.floor((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
				 EnumFacing enumfacing = EnumFacing.getHorizontal(i);
				 BlockPos blockpos = pos.offset(enumfacing);
				 ItemStack itemstack = player.getHeldItem(hand);

				 if (player.canPlayerEdit(pos, facing, itemstack) && player.canPlayerEdit(blockpos, facing, itemstack))
				 {
					 IBlockState iblockstate1 = worldIn.getBlockState(blockpos);
					 boolean flag1 = iblockstate1.getBlock().isReplaceable(worldIn, blockpos);
					 boolean flag2 = flag || worldIn.isAirBlock(pos);
					 boolean flag3 = flag1 || worldIn.isAirBlock(blockpos);

					 if (flag2 && flag3 && worldIn.getBlockState(pos.down()).isTopSolid() && worldIn.getBlockState(blockpos.down()).isTopSolid())
					 {
						 IBlockState iblockstate2 = ModCore_Urushi.LimeFuton.getDefaultState().withProperty(Futon.OCCUPIED, Boolean.valueOf(false)).withProperty(Futon.FACING, enumfacing).withProperty(Futon.PART, Futon.EnumPartType.FOOT);
						 worldIn.setBlockState(pos, iblockstate2, 10);
						 worldIn.setBlockState(blockpos, iblockstate2.withProperty(Futon.PART, Futon.EnumPartType.HEAD), 10);
						 SoundType soundtype = iblockstate2.getBlock().getSoundType(iblockstate2, worldIn, pos, player);
						 worldIn.playSound((EntityPlayer)null,pos, SoundEvents.BLOCK_CLOTH_PLACE, SoundCategory.PLAYERS, 1.0F, 1.0F);
						 TileEntity tileentity = worldIn.getTileEntity(blockpos);

						 if (tileentity instanceof TileEntityBed)
						 {
							 ((TileEntityBed)tileentity).setItemValues(itemstack);
						 }

						 TileEntity tileentity1 = worldIn.getTileEntity(pos);

						 if (tileentity1 instanceof TileEntityBed)
						 {
							 ((TileEntityBed)tileentity1).setItemValues(itemstack);
						 }

						 worldIn.notifyNeighborsRespectDebug(pos, block, false);
						 worldIn.notifyNeighborsRespectDebug(blockpos, iblockstate1.getBlock(), false);

						 if (player instanceof EntityPlayerMP)
						 {
							 CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos, itemstack);
						 }

						 itemstack.shrink(1);
						 return EnumActionResult.SUCCESS;
					 }
					 else
					 {
						 return EnumActionResult.FAIL;
					 }
				 }
				 else
				 {
					 return EnumActionResult.FAIL;
				 }

			 }else if(meta==44){

				 IBlockState iblockstate = worldIn.getBlockState(pos);
				 Block block = iblockstate.getBlock();
				 boolean flag = block.isReplaceable(worldIn, pos);

				 if (!flag)
				 {
					 pos = pos.up();
				 }

				 int i = MathHelper.floor((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
				 EnumFacing enumfacing = EnumFacing.getHorizontal(i);
				 BlockPos blockpos = pos.offset(enumfacing);
				 ItemStack itemstack = player.getHeldItem(hand);

				 if (player.canPlayerEdit(pos, facing, itemstack) && player.canPlayerEdit(blockpos, facing, itemstack))
				 {
					 IBlockState iblockstate1 = worldIn.getBlockState(blockpos);
					 boolean flag1 = iblockstate1.getBlock().isReplaceable(worldIn, blockpos);
					 boolean flag2 = flag || worldIn.isAirBlock(pos);
					 boolean flag3 = flag1 || worldIn.isAirBlock(blockpos);

					 if (flag2 && flag3 && worldIn.getBlockState(pos.down()).isTopSolid() && worldIn.getBlockState(blockpos.down()).isTopSolid())
					 {
						 IBlockState iblockstate2 = ModCore_Urushi.PinkFuton.getDefaultState().withProperty(Futon.OCCUPIED, Boolean.valueOf(false)).withProperty(Futon.FACING, enumfacing).withProperty(Futon.PART, Futon.EnumPartType.FOOT);
						 worldIn.setBlockState(pos, iblockstate2, 10);
						 worldIn.setBlockState(blockpos, iblockstate2.withProperty(Futon.PART, Futon.EnumPartType.HEAD), 10);
						 SoundType soundtype = iblockstate2.getBlock().getSoundType(iblockstate2, worldIn, pos, player);
						 worldIn.playSound((EntityPlayer)null,pos, SoundEvents.BLOCK_CLOTH_PLACE, SoundCategory.PLAYERS, 1.0F, 1.0F);
						 TileEntity tileentity = worldIn.getTileEntity(blockpos);

						 if (tileentity instanceof TileEntityBed)
						 {
							 ((TileEntityBed)tileentity).setItemValues(itemstack);
						 }

						 TileEntity tileentity1 = worldIn.getTileEntity(pos);

						 if (tileentity1 instanceof TileEntityBed)
						 {
							 ((TileEntityBed)tileentity1).setItemValues(itemstack);
						 }

						 worldIn.notifyNeighborsRespectDebug(pos, block, false);
						 worldIn.notifyNeighborsRespectDebug(blockpos, iblockstate1.getBlock(), false);

						 if (player instanceof EntityPlayerMP)
						 {
							 CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos, itemstack);
						 }

						 itemstack.shrink(1);
						 return EnumActionResult.SUCCESS;
					 }
					 else
					 {
						 return EnumActionResult.FAIL;
					 }
				 }
				 else
				 {
					 return EnumActionResult.FAIL;
				 }

			 }else if(meta==45){

				 IBlockState iblockstate = worldIn.getBlockState(pos);
				 Block block = iblockstate.getBlock();
				 boolean flag = block.isReplaceable(worldIn, pos);

				 if (!flag)
				 {
					 pos = pos.up();
				 }

				 int i = MathHelper.floor((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
				 EnumFacing enumfacing = EnumFacing.getHorizontal(i);
				 BlockPos blockpos = pos.offset(enumfacing);
				 ItemStack itemstack = player.getHeldItem(hand);

				 if (player.canPlayerEdit(pos, facing, itemstack) && player.canPlayerEdit(blockpos, facing, itemstack))
				 {
					 IBlockState iblockstate1 = worldIn.getBlockState(blockpos);
					 boolean flag1 = iblockstate1.getBlock().isReplaceable(worldIn, blockpos);
					 boolean flag2 = flag || worldIn.isAirBlock(pos);
					 boolean flag3 = flag1 || worldIn.isAirBlock(blockpos);

					 if (flag2 && flag3 && worldIn.getBlockState(pos.down()).isTopSolid() && worldIn.getBlockState(blockpos.down()).isTopSolid())
					 {
						 IBlockState iblockstate2 = ModCore_Urushi.GrayFuton.getDefaultState().withProperty(Futon.OCCUPIED, Boolean.valueOf(false)).withProperty(Futon.FACING, enumfacing).withProperty(Futon.PART, Futon.EnumPartType.FOOT);
						 worldIn.setBlockState(pos, iblockstate2, 10);
						 worldIn.setBlockState(blockpos, iblockstate2.withProperty(Futon.PART, Futon.EnumPartType.HEAD), 10);
						 SoundType soundtype = iblockstate2.getBlock().getSoundType(iblockstate2, worldIn, pos, player);
						 worldIn.playSound((EntityPlayer)null,pos, SoundEvents.BLOCK_CLOTH_PLACE, SoundCategory.PLAYERS, 1.0F, 1.0F);
						 TileEntity tileentity = worldIn.getTileEntity(blockpos);

						 if (tileentity instanceof TileEntityBed)
						 {
							 ((TileEntityBed)tileentity).setItemValues(itemstack);
						 }

						 TileEntity tileentity1 = worldIn.getTileEntity(pos);

						 if (tileentity1 instanceof TileEntityBed)
						 {
							 ((TileEntityBed)tileentity1).setItemValues(itemstack);
						 }

						 worldIn.notifyNeighborsRespectDebug(pos, block, false);
						 worldIn.notifyNeighborsRespectDebug(blockpos, iblockstate1.getBlock(), false);

						 if (player instanceof EntityPlayerMP)
						 {
							 CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos, itemstack);
						 }

						 itemstack.shrink(1);
						 return EnumActionResult.SUCCESS;
					 }
					 else
					 {
						 return EnumActionResult.FAIL;
					 }
				 }
				 else
				 {
					 return EnumActionResult.FAIL;
				 }

			 }else if(meta==46){

				 IBlockState iblockstate = worldIn.getBlockState(pos);
				 Block block = iblockstate.getBlock();
				 boolean flag = block.isReplaceable(worldIn, pos);

				 if (!flag)
				 {
					 pos = pos.up();
				 }

				 int i = MathHelper.floor((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
				 EnumFacing enumfacing = EnumFacing.getHorizontal(i);
				 BlockPos blockpos = pos.offset(enumfacing);
				 ItemStack itemstack = player.getHeldItem(hand);

				 if (player.canPlayerEdit(pos, facing, itemstack) && player.canPlayerEdit(blockpos, facing, itemstack))
				 {
					 IBlockState iblockstate1 = worldIn.getBlockState(blockpos);
					 boolean flag1 = iblockstate1.getBlock().isReplaceable(worldIn, blockpos);
					 boolean flag2 = flag || worldIn.isAirBlock(pos);
					 boolean flag3 = flag1 || worldIn.isAirBlock(blockpos);

					 if (flag2 && flag3 && worldIn.getBlockState(pos.down()).isTopSolid() && worldIn.getBlockState(blockpos.down()).isTopSolid())
					 {
						 IBlockState iblockstate2 = ModCore_Urushi.LightGrayFuton.getDefaultState().withProperty(Futon.OCCUPIED, Boolean.valueOf(false)).withProperty(Futon.FACING, enumfacing).withProperty(Futon.PART, Futon.EnumPartType.FOOT);
						 worldIn.setBlockState(pos, iblockstate2, 10);
						 worldIn.setBlockState(blockpos, iblockstate2.withProperty(Futon.PART, Futon.EnumPartType.HEAD), 10);
						 SoundType soundtype = iblockstate2.getBlock().getSoundType(iblockstate2, worldIn, pos, player);
						 worldIn.playSound((EntityPlayer)null,pos, SoundEvents.BLOCK_CLOTH_PLACE, SoundCategory.PLAYERS, 1.0F, 1.0F);
						 TileEntity tileentity = worldIn.getTileEntity(blockpos);

						 if (tileentity instanceof TileEntityBed)
						 {
							 ((TileEntityBed)tileentity).setItemValues(itemstack);
						 }

						 TileEntity tileentity1 = worldIn.getTileEntity(pos);

						 if (tileentity1 instanceof TileEntityBed)
						 {
							 ((TileEntityBed)tileentity1).setItemValues(itemstack);
						 }

						 worldIn.notifyNeighborsRespectDebug(pos, block, false);
						 worldIn.notifyNeighborsRespectDebug(blockpos, iblockstate1.getBlock(), false);

						 if (player instanceof EntityPlayerMP)
						 {
							 CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos, itemstack);
						 }

						 itemstack.shrink(1);
						 return EnumActionResult.SUCCESS;
					 }
					 else
					 {
						 return EnumActionResult.FAIL;
					 }
				 }
				 else
				 {
					 return EnumActionResult.FAIL;
				 }

			 }else if(meta==47){

				 IBlockState iblockstate = worldIn.getBlockState(pos);
				 Block block = iblockstate.getBlock();
				 boolean flag = block.isReplaceable(worldIn, pos);

				 if (!flag)
				 {
					 pos = pos.up();
				 }

				 int i = MathHelper.floor((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
				 EnumFacing enumfacing = EnumFacing.getHorizontal(i);
				 BlockPos blockpos = pos.offset(enumfacing);
				 ItemStack itemstack = player.getHeldItem(hand);

				 if (player.canPlayerEdit(pos, facing, itemstack) && player.canPlayerEdit(blockpos, facing, itemstack))
				 {
					 IBlockState iblockstate1 = worldIn.getBlockState(blockpos);
					 boolean flag1 = iblockstate1.getBlock().isReplaceable(worldIn, blockpos);
					 boolean flag2 = flag || worldIn.isAirBlock(pos);
					 boolean flag3 = flag1 || worldIn.isAirBlock(blockpos);

					 if (flag2 && flag3 && worldIn.getBlockState(pos.down()).isTopSolid() && worldIn.getBlockState(blockpos.down()).isTopSolid())
					 {
						 IBlockState iblockstate2 = ModCore_Urushi.CyanFuton.getDefaultState().withProperty(Futon.OCCUPIED, Boolean.valueOf(false)).withProperty(Futon.FACING, enumfacing).withProperty(Futon.PART, Futon.EnumPartType.FOOT);
						 worldIn.setBlockState(pos, iblockstate2, 10);
						 worldIn.setBlockState(blockpos, iblockstate2.withProperty(Futon.PART, Futon.EnumPartType.HEAD), 10);
						 SoundType soundtype = iblockstate2.getBlock().getSoundType(iblockstate2, worldIn, pos, player);
						 worldIn.playSound((EntityPlayer)null,pos, SoundEvents.BLOCK_CLOTH_PLACE, SoundCategory.PLAYERS, 1.0F, 1.0F);
						 TileEntity tileentity = worldIn.getTileEntity(blockpos);

						 if (tileentity instanceof TileEntityBed)
						 {
							 ((TileEntityBed)tileentity).setItemValues(itemstack);
						 }

						 TileEntity tileentity1 = worldIn.getTileEntity(pos);

						 if (tileentity1 instanceof TileEntityBed)
						 {
							 ((TileEntityBed)tileentity1).setItemValues(itemstack);
						 }

						 worldIn.notifyNeighborsRespectDebug(pos, block, false);
						 worldIn.notifyNeighborsRespectDebug(blockpos, iblockstate1.getBlock(), false);

						 if (player instanceof EntityPlayerMP)
						 {
							 CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos, itemstack);
						 }

						 itemstack.shrink(1);
						 return EnumActionResult.SUCCESS;
					 }
					 else
					 {
						 return EnumActionResult.FAIL;
					 }
				 }
				 else
				 {
					 return EnumActionResult.FAIL;
				 }

			 }else if(meta==48){

				 IBlockState iblockstate = worldIn.getBlockState(pos);
				 Block block = iblockstate.getBlock();
				 boolean flag = block.isReplaceable(worldIn, pos);

				 if (!flag)
				 {
					 pos = pos.up();
				 }

				 int i = MathHelper.floor((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
				 EnumFacing enumfacing = EnumFacing.getHorizontal(i);
				 BlockPos blockpos = pos.offset(enumfacing);
				 ItemStack itemstack = player.getHeldItem(hand);

				 if (player.canPlayerEdit(pos, facing, itemstack) && player.canPlayerEdit(blockpos, facing, itemstack))
				 {
					 IBlockState iblockstate1 = worldIn.getBlockState(blockpos);
					 boolean flag1 = iblockstate1.getBlock().isReplaceable(worldIn, blockpos);
					 boolean flag2 = flag || worldIn.isAirBlock(pos);
					 boolean flag3 = flag1 || worldIn.isAirBlock(blockpos);

					 if (flag2 && flag3 && worldIn.getBlockState(pos.down()).isTopSolid() && worldIn.getBlockState(blockpos.down()).isTopSolid())
					 {
						 IBlockState iblockstate2 = ModCore_Urushi.PurpleFuton.getDefaultState().withProperty(Futon.OCCUPIED, Boolean.valueOf(false)).withProperty(Futon.FACING, enumfacing).withProperty(Futon.PART, Futon.EnumPartType.FOOT);
						 worldIn.setBlockState(pos, iblockstate2, 10);
						 worldIn.setBlockState(blockpos, iblockstate2.withProperty(Futon.PART, Futon.EnumPartType.HEAD), 10);
						 SoundType soundtype = iblockstate2.getBlock().getSoundType(iblockstate2, worldIn, pos, player);
						 worldIn.playSound((EntityPlayer)null,pos, SoundEvents.BLOCK_CLOTH_PLACE, SoundCategory.PLAYERS, 1.0F, 1.0F);
						 TileEntity tileentity = worldIn.getTileEntity(blockpos);

						 if (tileentity instanceof TileEntityBed)
						 {
							 ((TileEntityBed)tileentity).setItemValues(itemstack);
						 }

						 TileEntity tileentity1 = worldIn.getTileEntity(pos);

						 if (tileentity1 instanceof TileEntityBed)
						 {
							 ((TileEntityBed)tileentity1).setItemValues(itemstack);
						 }

						 worldIn.notifyNeighborsRespectDebug(pos, block, false);
						 worldIn.notifyNeighborsRespectDebug(blockpos, iblockstate1.getBlock(), false);

						 if (player instanceof EntityPlayerMP)
						 {
							 CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos, itemstack);
						 }

						 itemstack.shrink(1);
						 return EnumActionResult.SUCCESS;
					 }
					 else
					 {
						 return EnumActionResult.FAIL;
					 }
				 }
				 else
				 {
					 return EnumActionResult.FAIL;
				 }

			 }else if(meta==49){

				 IBlockState iblockstate = worldIn.getBlockState(pos);
				 Block block = iblockstate.getBlock();
				 boolean flag = block.isReplaceable(worldIn, pos);

				 if (!flag)
				 {
					 pos = pos.up();
				 }

				 int i = MathHelper.floor((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
				 EnumFacing enumfacing = EnumFacing.getHorizontal(i);
				 BlockPos blockpos = pos.offset(enumfacing);
				 ItemStack itemstack = player.getHeldItem(hand);

				 if (player.canPlayerEdit(pos, facing, itemstack) && player.canPlayerEdit(blockpos, facing, itemstack))
				 {
					 IBlockState iblockstate1 = worldIn.getBlockState(blockpos);
					 boolean flag1 = iblockstate1.getBlock().isReplaceable(worldIn, blockpos);
					 boolean flag2 = flag || worldIn.isAirBlock(pos);
					 boolean flag3 = flag1 || worldIn.isAirBlock(blockpos);

					 if (flag2 && flag3 && worldIn.getBlockState(pos.down()).isTopSolid() && worldIn.getBlockState(blockpos.down()).isTopSolid())
					 {
						 IBlockState iblockstate2 = ModCore_Urushi.BlueFuton.getDefaultState().withProperty(Futon.OCCUPIED, Boolean.valueOf(false)).withProperty(Futon.FACING, enumfacing).withProperty(Futon.PART, Futon.EnumPartType.FOOT);
						 worldIn.setBlockState(pos, iblockstate2, 10);
						 worldIn.setBlockState(blockpos, iblockstate2.withProperty(Futon.PART, Futon.EnumPartType.HEAD), 10);
						 SoundType soundtype = iblockstate2.getBlock().getSoundType(iblockstate2, worldIn, pos, player);
						 worldIn.playSound((EntityPlayer)null,pos, SoundEvents.BLOCK_CLOTH_PLACE, SoundCategory.PLAYERS, 1.0F, 1.0F);
						 TileEntity tileentity = worldIn.getTileEntity(blockpos);

						 if (tileentity instanceof TileEntityBed)
						 {
							 ((TileEntityBed)tileentity).setItemValues(itemstack);
						 }

						 TileEntity tileentity1 = worldIn.getTileEntity(pos);

						 if (tileentity1 instanceof TileEntityBed)
						 {
							 ((TileEntityBed)tileentity1).setItemValues(itemstack);
						 }

						 worldIn.notifyNeighborsRespectDebug(pos, block, false);
						 worldIn.notifyNeighborsRespectDebug(blockpos, iblockstate1.getBlock(), false);

						 if (player instanceof EntityPlayerMP)
						 {
							 CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos, itemstack);
						 }

						 itemstack.shrink(1);
						 return EnumActionResult.SUCCESS;
					 }
					 else
					 {
						 return EnumActionResult.FAIL;
					 }
				 }
				 else
				 {
					 return EnumActionResult.FAIL;
				 }

			 }else if(meta==50){

				 IBlockState iblockstate = worldIn.getBlockState(pos);
				 Block block = iblockstate.getBlock();
				 boolean flag = block.isReplaceable(worldIn, pos);

				 if (!flag)
				 {
					 pos = pos.up();
				 }

				 int i = MathHelper.floor((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
				 EnumFacing enumfacing = EnumFacing.getHorizontal(i);
				 BlockPos blockpos = pos.offset(enumfacing);
				 ItemStack itemstack = player.getHeldItem(hand);

				 if (player.canPlayerEdit(pos, facing, itemstack) && player.canPlayerEdit(blockpos, facing, itemstack))
				 {
					 IBlockState iblockstate1 = worldIn.getBlockState(blockpos);
					 boolean flag1 = iblockstate1.getBlock().isReplaceable(worldIn, blockpos);
					 boolean flag2 = flag || worldIn.isAirBlock(pos);
					 boolean flag3 = flag1 || worldIn.isAirBlock(blockpos);

					 if (flag2 && flag3 && worldIn.getBlockState(pos.down()).isTopSolid() && worldIn.getBlockState(blockpos.down()).isTopSolid())
					 {
						 IBlockState iblockstate2 = ModCore_Urushi.BrownFuton.getDefaultState().withProperty(Futon.OCCUPIED, Boolean.valueOf(false)).withProperty(Futon.FACING, enumfacing).withProperty(Futon.PART, Futon.EnumPartType.FOOT);
						 worldIn.setBlockState(pos, iblockstate2, 10);
						 worldIn.setBlockState(blockpos, iblockstate2.withProperty(Futon.PART, Futon.EnumPartType.HEAD), 10);
						 SoundType soundtype = iblockstate2.getBlock().getSoundType(iblockstate2, worldIn, pos, player);
						 worldIn.playSound((EntityPlayer)null,pos, SoundEvents.BLOCK_CLOTH_PLACE, SoundCategory.PLAYERS, 1.0F, 1.0F);
						 TileEntity tileentity = worldIn.getTileEntity(blockpos);

						 if (tileentity instanceof TileEntityBed)
						 {
							 ((TileEntityBed)tileentity).setItemValues(itemstack);
						 }

						 TileEntity tileentity1 = worldIn.getTileEntity(pos);

						 if (tileentity1 instanceof TileEntityBed)
						 {
							 ((TileEntityBed)tileentity1).setItemValues(itemstack);
						 }

						 worldIn.notifyNeighborsRespectDebug(pos, block, false);
						 worldIn.notifyNeighborsRespectDebug(blockpos, iblockstate1.getBlock(), false);

						 if (player instanceof EntityPlayerMP)
						 {
							 CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos, itemstack);
						 }

						 itemstack.shrink(1);
						 return EnumActionResult.SUCCESS;
					 }
					 else
					 {
						 return EnumActionResult.FAIL;
					 }
				 }
				 else
				 {
					 return EnumActionResult.FAIL;
				 }

			 }else if(meta==51){

				 IBlockState iblockstate = worldIn.getBlockState(pos);
				 Block block = iblockstate.getBlock();
				 boolean flag = block.isReplaceable(worldIn, pos);

				 if (!flag)
				 {
					 pos = pos.up();
				 }

				 int i = MathHelper.floor((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
				 EnumFacing enumfacing = EnumFacing.getHorizontal(i);
				 BlockPos blockpos = pos.offset(enumfacing);
				 ItemStack itemstack = player.getHeldItem(hand);

				 if (player.canPlayerEdit(pos, facing, itemstack) && player.canPlayerEdit(blockpos, facing, itemstack))
				 {
					 IBlockState iblockstate1 = worldIn.getBlockState(blockpos);
					 boolean flag1 = iblockstate1.getBlock().isReplaceable(worldIn, blockpos);
					 boolean flag2 = flag || worldIn.isAirBlock(pos);
					 boolean flag3 = flag1 || worldIn.isAirBlock(blockpos);

					 if (flag2 && flag3 && worldIn.getBlockState(pos.down()).isTopSolid() && worldIn.getBlockState(blockpos.down()).isTopSolid())
					 {
						 IBlockState iblockstate2 = ModCore_Urushi.GreenFuton.getDefaultState().withProperty(Futon.OCCUPIED, Boolean.valueOf(false)).withProperty(Futon.FACING, enumfacing).withProperty(Futon.PART, Futon.EnumPartType.FOOT);
						 worldIn.setBlockState(pos, iblockstate2, 10);
						 worldIn.setBlockState(blockpos, iblockstate2.withProperty(Futon.PART, Futon.EnumPartType.HEAD), 10);
						 SoundType soundtype = iblockstate2.getBlock().getSoundType(iblockstate2, worldIn, pos, player);
						 worldIn.playSound((EntityPlayer)null,pos, SoundEvents.BLOCK_CLOTH_PLACE, SoundCategory.PLAYERS, 1.0F, 1.0F);
						 TileEntity tileentity = worldIn.getTileEntity(blockpos);

						 if (tileentity instanceof TileEntityBed)
						 {
							 ((TileEntityBed)tileentity).setItemValues(itemstack);
						 }

						 TileEntity tileentity1 = worldIn.getTileEntity(pos);

						 if (tileentity1 instanceof TileEntityBed)
						 {
							 ((TileEntityBed)tileentity1).setItemValues(itemstack);
						 }

						 worldIn.notifyNeighborsRespectDebug(pos, block, false);
						 worldIn.notifyNeighborsRespectDebug(blockpos, iblockstate1.getBlock(), false);

						 if (player instanceof EntityPlayerMP)
						 {
							 CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos, itemstack);
						 }

						 itemstack.shrink(1);
						 return EnumActionResult.SUCCESS;
					 }
					 else
					 {
						 return EnumActionResult.FAIL;
					 }
				 }
				 else
				 {
					 return EnumActionResult.FAIL;
				 }

			 }else if(meta==52){

				 IBlockState iblockstate = worldIn.getBlockState(pos);
				 Block block = iblockstate.getBlock();
				 boolean flag = block.isReplaceable(worldIn, pos);

				 if (!flag)
				 {
					 pos = pos.up();
				 }

				 int i = MathHelper.floor((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
				 EnumFacing enumfacing = EnumFacing.getHorizontal(i);
				 BlockPos blockpos = pos.offset(enumfacing);
				 ItemStack itemstack = player.getHeldItem(hand);

				 if (player.canPlayerEdit(pos, facing, itemstack) && player.canPlayerEdit(blockpos, facing, itemstack))
				 {
					 IBlockState iblockstate1 = worldIn.getBlockState(blockpos);
					 boolean flag1 = iblockstate1.getBlock().isReplaceable(worldIn, blockpos);
					 boolean flag2 = flag || worldIn.isAirBlock(pos);
					 boolean flag3 = flag1 || worldIn.isAirBlock(blockpos);

					 if (flag2 && flag3 && worldIn.getBlockState(pos.down()).isTopSolid() && worldIn.getBlockState(blockpos.down()).isTopSolid())
					 {
						 IBlockState iblockstate2 = ModCore_Urushi.BlackFuton.getDefaultState().withProperty(Futon.OCCUPIED, Boolean.valueOf(false)).withProperty(Futon.FACING, enumfacing).withProperty(Futon.PART, Futon.EnumPartType.FOOT);
						 worldIn.setBlockState(pos, iblockstate2, 10);
						 worldIn.setBlockState(blockpos, iblockstate2.withProperty(Futon.PART, Futon.EnumPartType.HEAD), 10);
						 SoundType soundtype = iblockstate2.getBlock().getSoundType(iblockstate2, worldIn, pos, player);
						 worldIn.playSound((EntityPlayer)null,pos, SoundEvents.BLOCK_CLOTH_PLACE, SoundCategory.PLAYERS, 1.0F, 1.0F);
						 TileEntity tileentity = worldIn.getTileEntity(blockpos);

						 if (tileentity instanceof TileEntityBed)
						 {
							 ((TileEntityBed)tileentity).setItemValues(itemstack);
						 }

						 TileEntity tileentity1 = worldIn.getTileEntity(pos);

						 if (tileentity1 instanceof TileEntityBed)
						 {
							 ((TileEntityBed)tileentity1).setItemValues(itemstack);
						 }

						 worldIn.notifyNeighborsRespectDebug(pos, block, false);
						 worldIn.notifyNeighborsRespectDebug(blockpos, iblockstate1.getBlock(), false);

						 if (player instanceof EntityPlayerMP)
						 {
							 CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos, itemstack);
						 }

						 itemstack.shrink(1);
						 return EnumActionResult.SUCCESS;
					 }
					 else
					 {
						 return EnumActionResult.FAIL;
					 }
				 }
				 else
				 {
					 return EnumActionResult.FAIL;
				 }

			 }









		}
		return EnumActionResult.FAIL;
	}

	@Override
	public int getItemBurnTime(ItemStack itemStack) {
		if(itemStack.getItemDamage()==37||itemStack.getItemDamage()==56||itemStack.getItemDamage()==57||itemStack.getItemDamage()==58||itemStack.getItemDamage()==0){
			return 200;
		}else{
			return 0;
		}
	}
}

package urushi.Item;


import net.minecraft.block.BlockFarmland;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import urushi.ModCore_Urushi;

import javax.annotation.Nullable;
import java.util.List;

public class Wagasa extends Item  {
		 public Wagasa() {
		        super();
		        this.setRegistryName(ModCore_Urushi.modid, "wagasa");
		        this.setCreativeTab(ModCore_Urushi.TabUrushi);
		        this.setUnlocalizedName("Wagasa");
		        this.setMaxDamage(0);
                this.setMaxStackSize(1);
			 this.setHasSubtypes(true);
		    }

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if (playerIn.getHeldItem(handIn).getItemDamage() == 0) {
	//		playerIn.setHeldItem(handIn, new ItemStack(ModCore_Urushi.Wagasa, 1, 1));
			return  new ActionResult<ItemStack>(EnumActionResult.SUCCESS, new ItemStack(ModCore_Urushi.Wagasa, 1, 1));

		}else{
		//	playerIn.setHeldItem(handIn, new ItemStack(ModCore_Urushi.Wagasa, 1, 0));
			return  new ActionResult<ItemStack>(EnumActionResult.SUCCESS, new ItemStack(ModCore_Urushi.Wagasa, 1, 0));

		}
	}
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (stack.getItemDamage() == 0) {
			if (entityIn instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) entityIn;

				if (player.getHeldItemMainhand() == stack || player.getHeldItemOffhand() == stack) {
					if (entityIn.motionY < 0D) {
						entityIn.motionY *= 0.6D;
					}
					entityIn.fallDistance = 0f;
				}
				} else {
					if (entityIn.motionY < 0D) {
						entityIn.motionY *= 0.6D;
					}
					entityIn.fallDistance = 0f;
				}

		}
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		ITextComponent textComponent=new TextComponentTranslation("item.info.wagasa", new Object[0]);
		int metadata=stack.getItemDamage();
		if(metadata==0)
		tooltip.add(textComponent.getFormattedText());
	}
}

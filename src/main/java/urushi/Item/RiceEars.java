package urushi.Item;


import net.minecraft.block.BlockFarmland;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import urushi.Block.CropRice;
import urushi.ModCore_Urushi;

import javax.annotation.Nullable;
import java.util.List;

public class RiceEars extends Item  {
		 public RiceEars() {
		        super();
		        this.setRegistryName(ModCore_Urushi.modid, "rice_ears");
		        this.setCreativeTab(ModCore_Urushi.TabUrushi);
		        this.setUnlocalizedName("RiceEars");
		        this.setMaxDamage(0);

		    }


	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote&&facing==EnumFacing.UP&&worldIn.getBlockState(pos).getBlock() instanceof BlockFarmland){
			worldIn.setBlockState(pos.add(0,1,0), ModCore_Urushi.CropRice.getDefaultState());
			player.getHeldItem(hand).shrink(1);
			player.swingArm(hand);
			if(worldIn.getBlockState(pos).getBlock()== Blocks.FARMLAND){
				worldIn.setBlockState(pos,ModCore_Urushi.PaddyField.getDefaultState().withProperty(BlockFarmland.MOISTURE,Integer.valueOf(7)));
			}
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.FAIL;
	}
	@Override
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
		ITextComponent textComponent2=new TextComponentTranslation("item.info.riceears", new Object[0]);

		tooltip.add(textComponent2.getFormattedText());
	}
}

package urushi.Itemblock;


import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import urushi.Block.U_Planks;


public class ItemBlockMetadata extends ItemBlock {

    public ItemBlockMetadata(Block block) {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    public int getMetadata(int damage)
    {
        return damage;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
     /*   int meta=stack.getItemDamage();
        switch(meta) {
            case 0:return "smooth_oak_planks";
            case 1:return "smooth_birch_planks";
            case 2:return "smooth_spruce_planks";
            case 3:return "smooth_jungle_planks";
            case 4:return "smooth_acacia_planks";
            case 5:return "smooth_dark_oak_planks";
            case 6:return "planks_red_urushi_stained";
            case 7:return "smooth_planks_red_urushi_stained";
            case 8:return "planks_black_urushi_stained";
            case 9:return "smooth_planks_black_urushi_stained";
            case 10:return "red_pine_planks";
            case 11:return "smooth_red_pine_planks";
            case 12:return "sakura_planks";
            case 13:return "smooth_sakura_planks";

        }
    	return getUnlocalizedName();*/
        return getUnlocalizedName()+"."+stack.getItemDamage();
    }
}
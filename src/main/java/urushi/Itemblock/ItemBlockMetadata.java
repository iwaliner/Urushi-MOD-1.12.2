package urushi.Itemblock;


import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;


public class ItemBlockMetadata extends ItemBlock {

    public ItemBlockMetadata(Block block) {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return this.getUnlocalizedName()+"."+stack.getItemDamage();
    }

    public int getMetadata(int damage)
    {
        return damage;
    }


}
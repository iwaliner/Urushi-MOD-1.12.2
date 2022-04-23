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


}
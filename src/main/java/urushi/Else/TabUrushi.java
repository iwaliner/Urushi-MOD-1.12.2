package urushi.Else;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import urushi.ModCore_Urushi;

public class TabUrushi extends CreativeTabs {

    public TabUrushi(String label) {
        super(label);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ModCore_Urushi.UPlanks,1,6);
    }
}


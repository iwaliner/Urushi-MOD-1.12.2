package urushi.Item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ImmatureApricot extends ItemFood
{
    public ImmatureApricot(int amount, float saturation, boolean isWolfFood)
    {
        super(amount, saturation, isWolfFood);
    }


    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        if (!worldIn.isRemote)
        {

                player.addPotionEffect(new PotionEffect(MobEffects.WITHER, 800, 5));


        }
    }


}

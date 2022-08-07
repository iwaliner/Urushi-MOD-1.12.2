package urushi.Item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import urushi.ModCore_Urushi;

public class MetalClub extends ItemSword {
    public MetalClub(ToolMaterial material) {
        super(material);
        this.setRegistryName(ModCore_Urushi.modid, "metal_club");
        this.setCreativeTab(ModCore_Urushi.TabUrushi);
        this.setUnlocalizedName("MetalClub");
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        stack.damageItem(1, attacker);
        target.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 100, 1));
        return true;
    }
}

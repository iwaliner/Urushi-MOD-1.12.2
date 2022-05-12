package urushi.Block;

import net.minecraft.block.BlockFence;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

import urushi.ModCore_Urushi;

public class Fence extends BlockFence {

    public Fence(MapColor mapColor) {
        super(Material.WOOD,mapColor);
        this.setCreativeTab(ModCore_Urushi.TabUrushi);
        setResistance(10F);
        setLightOpacity(0);
        setLightLevel(0.0F);
        setHardness(1.0F);
        setHarvestLevel("axe", 0);
        setSoundType(SoundType.WOOD);
    }


}

package urushi.Block;


import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;

import urushi.ModCore_Urushi;

public class FenceGate extends BlockFenceGate {

    public FenceGate() {
        super(BlockPlanks.EnumType.OAK);
        this.setCreativeTab(ModCore_Urushi.TabUrushi);
        setResistance(10F);
        setLightOpacity(0);
        setLightLevel(0.0F);
        setHardness(1.0F);
        setHarvestLevel("axe", 0);
        setSoundType(SoundType.WOOD);
    }


}

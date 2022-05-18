package urushi.Block;

import net.minecraft.block.BlockFarmland;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import urushi.ModCore_Urushi;

public class PaddyField extends BlockFarmland {
    public PaddyField()
    {
        super();
        this.setDefaultState(this.blockState.getBaseState().withProperty(MOISTURE, Integer.valueOf(0)));
        this.setTickRandomly(true);
        this.setLightOpacity(255);
        setResistance(3F);
        setLightLevel(0.0F);
        setHardness(0.2F);
        setHarvestLevel("shovel", 0);
        setSoundType(SoundType.GROUND);
        this.useNeighborBrightness=true;
    }
}

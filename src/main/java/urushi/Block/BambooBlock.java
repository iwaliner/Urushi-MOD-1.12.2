package urushi.Block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import urushi.ModCore_Urushi;

public class BambooBlock extends Block
{
    public BambooBlock()
    {
        super(Material.WOOD);
        setSoundType(SoundType.WOOD);
        setResistance(10F);
        setLightOpacity(255);
        setLightLevel(0.0F);
        setHardness(0.5F);
        setHarvestLevel("axe", 0);
        setCreativeTab(ModCore_Urushi.TabUrushi);
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return MapColor.WOOD;
    }


}
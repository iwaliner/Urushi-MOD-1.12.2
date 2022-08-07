package urushi.Block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockSand;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import urushi.ModCore_Urushi;

import java.util.Random;

public class WattleAndDaub extends Block
{
    public WattleAndDaub()
    {
        super(Material.GROUND);
        setSoundType(SoundType.GROUND);
        setResistance(3F);
        setLightOpacity(255);
        setLightLevel(0.0F);
        setHardness(0.3F);
        setHarvestLevel("shovel", 0);
        setCreativeTab(ModCore_Urushi.TabUrushi);
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return MapColor.DIRT;
    }


}
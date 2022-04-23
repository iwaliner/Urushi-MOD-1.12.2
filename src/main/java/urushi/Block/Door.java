package urushi.Block;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import urushi.ModCore_Urushi;

import java.util.Random;

public class Door extends BlockDoor {
    public Door() {
        super(Material.WOOD);
        this.setCreativeTab(ModCore_Urushi.TabUrushi);
        setResistance(10F);
        setLightOpacity(0);
        setLightLevel(0.0F);
        setHardness(3F);
        setHarvestLevel("axe", 0);
        setSoundType(SoundType.WOOD);
    }

    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return MapColor.RED;
    }
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(ModCore_Urushi.UItems,1,6);
    }
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return ModCore_Urushi.UItems;
    }

    @Override
    public int damageDropped(IBlockState state) {
        return 6;
    }
}

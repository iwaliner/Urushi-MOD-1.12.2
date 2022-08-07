package urushi.Block;


import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import urushi.ModCore_Urushi;

public class Stairs extends BlockStairs {
	public Stairs(IBlockState state)
    {
        super(state);
        setHardness(1.0F);
		setResistance(10F);
		setLightOpacity(255);
		setLightLevel(0F);
		 this.setCreativeTab(ModCore_Urushi.TabUrushi);
		 useNeighborBrightness=true;
		if( this.getMaterial(state)== Material.WOOD) {
			setHarvestLevel("axe", 0);
            setSoundType(SoundType.WOOD);
		}else if( this.getMaterial(state)== Material.GRASS) {
            setSoundType(SoundType.PLANT);
        }
        else{
            setHarvestLevel("pickaxe", 0);
        }
    }
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }


}

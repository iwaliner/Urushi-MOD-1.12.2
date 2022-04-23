package urushi.Block;


import net.minecraft.block.*;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import urushi.Else.IMetaBlockName;
import urushi.ModCore_Urushi;

import java.util.Random;


public class LycorisRadiata extends BlockFlower implements IMetaBlockName, IGrowable,net.minecraftforge.common.IPlantable {
	public static final PropertyEnum TYPE = PropertyEnum.create("type", LycorisRadiata.EnumType.class);
	public LycorisRadiata()
    {
        super();
        setHardness(0F);
		setResistance(0F);
		setLightOpacity(0);
		setLightLevel(0F);
		setHarvestLevel("shovel", 0);
		 this.setCreativeTab(ModCore_Urushi.TabUrushi);
		 this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, EnumType.meta0));
    }
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        return true;
    }
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    {
        return true;
    }
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        int i=0;
        if ((worldIn.getBlockState(pos.add(0, -1-i, 1)).getBlock() instanceof BlockGrass || worldIn.getBlockState(pos.add(0, -1-i, 1)).getBlock() instanceof BlockDirt)&&worldIn.getBlockState(pos.add(0,-i,1)).getBlock()instanceof BlockAir) {
            if (rand.nextInt(3) == 1) {
                worldIn.setBlockState(pos.add(0, -i, 1),this.getDefaultState());
            }
        }
        if ((worldIn.getBlockState(pos.add(0, -1-i, -1)).getBlock() instanceof BlockGrass || worldIn.getBlockState(pos.add(0, -1-i, -1)).getBlock() instanceof BlockDirt)&&worldIn.getBlockState(pos.add(0,-i,-1)).getBlock()instanceof  BlockAir) {
            if (rand.nextInt(3) == 1) {
                worldIn.setBlockState(pos.add(0, -i, -1),this.getDefaultState());
            }
        }
        if ((worldIn.getBlockState(pos.add(1, -1-i, 0)).getBlock() instanceof BlockGrass || worldIn.getBlockState(pos.add(1, -1-i, 0)).getBlock() instanceof BlockDirt)&&worldIn.getBlockState(pos.add(1,-i,0)).getBlock()instanceof  BlockAir) {
            if (rand.nextInt(3) == 1) {
                worldIn.setBlockState(pos.add(1, -i, 0), this.getDefaultState());
            }
        }
        if ((worldIn.getBlockState(pos.add(-1, -1-i, 0)).getBlock() instanceof BlockGrass || worldIn.getBlockState(pos.add(-1, -1-i, 0)).getBlock() instanceof BlockDirt)&&worldIn.getBlockState(pos.add(-1,-i,0)).getBlock()instanceof  BlockAir) {
            if (rand.nextInt(3) == 1) {
                worldIn.setBlockState(pos.add(-1, -i, 0), this.getDefaultState());
            }
        }

        if ((worldIn.getBlockState(pos.add(1, -1-i, 1)).getBlock() instanceof BlockGrass || worldIn.getBlockState(pos.add(1, -1-i, 1)).getBlock() instanceof BlockDirt)&&worldIn.getBlockState(pos.add(1,-i,1)).getBlock()instanceof  BlockAir) {
            if (rand.nextInt(3) == 1) {
                worldIn.setBlockState(pos.add(1, -i, 1),this.getDefaultState());
            }
        }
        if ((worldIn.getBlockState(pos.add(1, -1-i, -1)).getBlock() instanceof BlockGrass || worldIn.getBlockState(pos.add(1, -1-i, -1)).getBlock() instanceof BlockDirt)&&worldIn.getBlockState(pos.add(1,-i,-1)).getBlock()instanceof  BlockAir) {
            if (rand.nextInt(3) == 1) {
                worldIn.setBlockState(pos.add(1, -i, -1), this.getDefaultState());
            }
        }
        if ((worldIn.getBlockState(pos.add(-1, -1-i, 1)).getBlock() instanceof BlockGrass || worldIn.getBlockState(pos.add(-1, -1-i, 1)).getBlock() instanceof BlockDirt)&&worldIn.getBlockState(pos.add(-1,-i,1)).getBlock()instanceof  BlockAir) {
            if (rand.nextInt(3) == 1) {
                worldIn.setBlockState(pos.add(-1, -i, 1),this.getDefaultState());
            }
        }
        if ((worldIn.getBlockState(pos.add(-1, -1-i, -1)).getBlock() instanceof BlockGrass || worldIn.getBlockState(pos.add(-1, -1-i, -1)).getBlock() instanceof BlockDirt)&&worldIn.getBlockState(pos.add(-1,-i,-1)).getBlock()instanceof  BlockAir) {
            if (rand.nextInt(3) == 1) {
                worldIn.setBlockState(pos.add(-1, -i, -1),this.getDefaultState());
            }

        }
    }


	@Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
    	for(int i=0; i<2;i++) {
    	       items.add(new ItemStack(this,1,i));
    	    	}
    }
    @Override
    public IBlockState getStateFromMeta(int meta) {
    	switch(meta) {
    	case 0:return getDefaultState().withProperty(TYPE, EnumType.meta0);
    	}
		return getDefaultState().withProperty(TYPE, EnumType.meta0);

    }
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] { TYPE });
    }
 public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(Item.getItemFromBlock(this), 1, this.getMetaFromState(world.getBlockState(pos)));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        EnumType type = (EnumType) state.getValue(TYPE);
        return type.getID();
    }
    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }
    @Override
    public String getSpecialName(ItemStack stack) {

    	switch(stack.getItemDamage()) {
    	case 0:return "meta0";
    	}
    	return "meta0";
    }
    public enum EnumType implements IStringSerializable {
        meta0(0, "meta0");

        private int ID;
        private String name;

        private EnumType(int ID, String name) {
            this.ID = ID;
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }

        public int getID() {
            return ID;
        }
        @Override
        public String toString() {
            return getName();
        }
    }
	@Override
	public EnumFlowerColor getBlockType() {
		// TODO 自動生成されたメソッド・スタブ
		return EnumFlowerColor.RED;
	}


}
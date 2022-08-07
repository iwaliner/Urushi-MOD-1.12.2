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
import urushi.Else.EnumType;
import urushi.ModCore_Urushi;

import java.util.Random;


public class KakuriyoFlower extends BlockRedFlower implements  IGrowable,net.minecraftforge.common.IPlantable {
   // public static final PropertyEnum<EnumType.EnumType2> VARIANT = PropertyEnum.<EnumType.EnumType2>create("variant", EnumType.EnumType2.class);

    public KakuriyoFlower()
    {
        super();
        setHardness(0F);
		setResistance(0F);
		setLightOpacity(0);
		setLightLevel(0F);
		 this.setCreativeTab(ModCore_Urushi.TabUrushi);
         this.setSoundType(SoundType.PLANT);
    //    this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT,EnumType.EnumType2.TypeA));

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
        if(state.getBlock().getMetaFromState(state)==1) {
            int i = 0;
            if ((worldIn.getBlockState(pos.add(0, -1 - i, 1)).getBlock() instanceof BlockGrass || worldIn.getBlockState(pos.add(0, -1 - i, 1)).getBlock() instanceof BlockDirt) && worldIn.getBlockState(pos.add(0, -i, 1)).getBlock() instanceof BlockAir) {
                if (rand.nextInt(3) == 1) {
                    worldIn.setBlockState(pos.add(0, -i, 1), this.getStateFromMeta(1));
                }
            }
            if ((worldIn.getBlockState(pos.add(0, -1 - i, -1)).getBlock() instanceof BlockGrass || worldIn.getBlockState(pos.add(0, -1 - i, -1)).getBlock() instanceof BlockDirt) && worldIn.getBlockState(pos.add(0, -i, -1)).getBlock() instanceof BlockAir) {
                if (rand.nextInt(3) == 1) {
                    worldIn.setBlockState(pos.add(0, -i, -1), this.getStateFromMeta(1));
                }
            }
            if ((worldIn.getBlockState(pos.add(1, -1 - i, 0)).getBlock() instanceof BlockGrass || worldIn.getBlockState(pos.add(1, -1 - i, 0)).getBlock() instanceof BlockDirt) && worldIn.getBlockState(pos.add(1, -i, 0)).getBlock() instanceof BlockAir) {
                if (rand.nextInt(3) == 1) {
                    worldIn.setBlockState(pos.add(1, -i, 0), this.getStateFromMeta(1));
                }
            }
            if ((worldIn.getBlockState(pos.add(-1, -1 - i, 0)).getBlock() instanceof BlockGrass || worldIn.getBlockState(pos.add(-1, -1 - i, 0)).getBlock() instanceof BlockDirt) && worldIn.getBlockState(pos.add(-1, -i, 0)).getBlock() instanceof BlockAir) {
                if (rand.nextInt(3) == 1) {
                    worldIn.setBlockState(pos.add(-1, -i, 0), this.getStateFromMeta(1));
                }
            }

            if ((worldIn.getBlockState(pos.add(1, -1 - i, 1)).getBlock() instanceof BlockGrass || worldIn.getBlockState(pos.add(1, -1 - i, 1)).getBlock() instanceof BlockDirt) && worldIn.getBlockState(pos.add(1, -i, 1)).getBlock() instanceof BlockAir) {
                if (rand.nextInt(3) == 1) {
                    worldIn.setBlockState(pos.add(1, -i, 1), this.getStateFromMeta(1));
                }
            }
            if ((worldIn.getBlockState(pos.add(1, -1 - i, -1)).getBlock() instanceof BlockGrass || worldIn.getBlockState(pos.add(1, -1 - i, -1)).getBlock() instanceof BlockDirt) && worldIn.getBlockState(pos.add(1, -i, -1)).getBlock() instanceof BlockAir) {
                if (rand.nextInt(3) == 1) {
                    worldIn.setBlockState(pos.add(1, -i, -1), this.getStateFromMeta(1));
                }
            }
            if ((worldIn.getBlockState(pos.add(-1, -1 - i, 1)).getBlock() instanceof BlockGrass || worldIn.getBlockState(pos.add(-1, -1 - i, 1)).getBlock() instanceof BlockDirt) && worldIn.getBlockState(pos.add(-1, -i, 1)).getBlock() instanceof BlockAir) {
                if (rand.nextInt(3) == 1) {
                    worldIn.setBlockState(pos.add(-1, -i, 1), this.getStateFromMeta(1));
                }
            }
            if ((worldIn.getBlockState(pos.add(-1, -1 - i, -1)).getBlock() instanceof BlockGrass || worldIn.getBlockState(pos.add(-1, -1 - i, -1)).getBlock() instanceof BlockDirt) && worldIn.getBlockState(pos.add(-1, -i, -1)).getBlock() instanceof BlockAir) {
                if (rand.nextInt(3) == 1) {
                    worldIn.setBlockState(pos.add(-1, -i, -1),this.getStateFromMeta(1));
                }

            }
        }else{

                int i = 0;
                if ((worldIn.getBlockState(pos.add(0, -1 - i, 1)).getBlock() instanceof BlockGrass || worldIn.getBlockState(pos.add(0, -1 - i, 1)).getBlock() instanceof BlockDirt) && worldIn.getBlockState(pos.add(0, -i, 1)).getBlock() instanceof BlockAir) {
                    if (rand.nextInt(3) == 1) {
                        worldIn.setBlockState(pos.add(0, -i, 1), this.getDefaultState());
                    }
                }
                if ((worldIn.getBlockState(pos.add(0, -1 - i, -1)).getBlock() instanceof BlockGrass || worldIn.getBlockState(pos.add(0, -1 - i, -1)).getBlock() instanceof BlockDirt) && worldIn.getBlockState(pos.add(0, -i, -1)).getBlock() instanceof BlockAir) {
                    if (rand.nextInt(3) == 1) {
                        worldIn.setBlockState(pos.add(0, -i, -1), this.getDefaultState());
                    }
                }
                if ((worldIn.getBlockState(pos.add(1, -1 - i, 0)).getBlock() instanceof BlockGrass || worldIn.getBlockState(pos.add(1, -1 - i, 0)).getBlock() instanceof BlockDirt) && worldIn.getBlockState(pos.add(1, -i, 0)).getBlock() instanceof BlockAir) {
                    if (rand.nextInt(3) == 1) {
                        worldIn.setBlockState(pos.add(1, -i, 0), this.getDefaultState());
                    }
                }
                if ((worldIn.getBlockState(pos.add(-1, -1 - i, 0)).getBlock() instanceof BlockGrass || worldIn.getBlockState(pos.add(-1, -1 - i, 0)).getBlock() instanceof BlockDirt) && worldIn.getBlockState(pos.add(-1, -i, 0)).getBlock() instanceof BlockAir) {
                    if (rand.nextInt(3) == 1) {
                        worldIn.setBlockState(pos.add(-1, -i, 0), this.getDefaultState());
                    }
                }

                if ((worldIn.getBlockState(pos.add(1, -1 - i, 1)).getBlock() instanceof BlockGrass || worldIn.getBlockState(pos.add(1, -1 - i, 1)).getBlock() instanceof BlockDirt) && worldIn.getBlockState(pos.add(1, -i, 1)).getBlock() instanceof BlockAir) {
                    if (rand.nextInt(3) == 1) {
                        worldIn.setBlockState(pos.add(1, -i, 1), this.getDefaultState());
                    }
                }
                if ((worldIn.getBlockState(pos.add(1, -1 - i, -1)).getBlock() instanceof BlockGrass || worldIn.getBlockState(pos.add(1, -1 - i, -1)).getBlock() instanceof BlockDirt) && worldIn.getBlockState(pos.add(1, -i, -1)).getBlock() instanceof BlockAir) {
                    if (rand.nextInt(3) == 1) {
                        worldIn.setBlockState(pos.add(1, -i, -1), this.getDefaultState());
                    }
                }
                if ((worldIn.getBlockState(pos.add(-1, -1 - i, 1)).getBlock() instanceof BlockGrass || worldIn.getBlockState(pos.add(-1, -1 - i, 1)).getBlock() instanceof BlockDirt) && worldIn.getBlockState(pos.add(-1, -i, 1)).getBlock() instanceof BlockAir) {
                    if (rand.nextInt(3) == 1) {
                        worldIn.setBlockState(pos.add(-1, -i, 1), this.getDefaultState());
                    }
                }
                if ((worldIn.getBlockState(pos.add(-1, -1 - i, -1)).getBlock() instanceof BlockGrass || worldIn.getBlockState(pos.add(-1, -1 - i, -1)).getBlock() instanceof BlockDirt) && worldIn.getBlockState(pos.add(-1, -i, -1)).getBlock() instanceof BlockAir) {
                    if (rand.nextInt(3) == 1) {
                        worldIn.setBlockState(pos.add(-1, -i, -1),this.getDefaultState());
                    }

                }

        }
    }


	@Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
    	for(int i=0; i<2;i++) {
    	       items.add(new ItemStack(this,1,i));
    	    	}
    }





}
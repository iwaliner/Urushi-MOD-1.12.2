package urushi.WorldGen;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import urushi.Block.*;
import urushi.Else.EnumType;
import urushi.ModCore_Urushi;

import java.util.Random;

public class WorldGenTeleportShrine extends WorldGenerator
{
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        if(world.getBlockState(pos.add(0,-3,0)).getMaterial()==Material.GRASS) {
            int height = 2;

            for (int u = 2; u < 255; u++) {
                if (world.getBlockState(pos.add(0, -u, 0)).getMaterial() == Material.GRASS || world.getBlockState(pos.add(0, -u, 0)).getMaterial() == Material.GROUND || world.getBlockState(pos.add(0, -u, 0)).getMaterial() == Material.ROCK) {
                    height = u;
                    break;
                }
            }
            for (int xx = -5; xx < 6; xx++) {
                for (int yy = -height + 1; yy < -1; yy++) {
                    for (int zz = -5; zz < 6; zz++) {

                        world.setBlockToAir(pos.add(xx, yy, zz));
                    }
                }
            }


            for (int xx = -5; xx < 6; xx++) {
                for (int yy = -2; yy < 7; yy++) {
                    for (int zz = -5; zz < 6; zz++) {
                        BlockPos aroundPos = pos.add(xx, yy, zz);
                        if (yy == -2) {
                            if ((zz == -5) && (xx == -1 || xx == 0 || xx == 1)) {
                                world.setBlockState(aroundPos, ModCore_Urushi.StairsRedUrushiStained.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
                                world.setBlockState(aroundPos.add(0, -1, 0), ModCore_Urushi.StairsRedUrushiStained.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP));

                            } else if ((xx == 4 && zz == 4) || (xx == 4 && zz == -4) || (xx == -4 && zz == 4) || (xx == -4 && zz == -4)) {
                                world.setBlockState(aroundPos, ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));

                            } else {
                                world.setBlockToAir(aroundPos);
                            }
                        } else if (yy == -1) {
                            if (xx > -3 && xx < 3 && zz > -3 && zz < 3) {
                                world.setBlockState(aroundPos, ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.Red));
                            } else if (xx > -5 && xx < 5 && zz > -5 && zz < 5) {
                                if ((zz == -4) && (xx == -1 || xx == 0 || xx == 1)) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.StairsRedUrushiStained.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
                                    world.setBlockState(aroundPos.add(0, -1, 0), ModCore_Urushi.StairsRedUrushiStained.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP));

                                } else if ((xx == 4 && zz == 4) || (xx == 4 && zz == -4) || (xx == -4 && zz == 4) || (xx == -4 && zz == -4)) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));

                                } else {
                                    world.setBlockState(aroundPos, ModCore_Urushi.UWoodenSlabBSingle.getDefaultState().withProperty(U_WoodenSlabB.VARIANT, EnumType.EnumType8.TypeA).withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.TOP));
                                }
                            } else {
                                world.setBlockToAir(aroundPos);
                            }
                        } else if (yy == 0) {
                            if (xx == 2) {
                                if (zz == -1 || zz == 0 || zz == 1) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.FramedPlaster.getDefaultState());
                                } else if (zz == 2 || zz == -2) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.FramedPlaster.getDefaultState().withProperty(FramedBlock.VARIANT2, EnumType.EnumType2.TypeB));
                                } else if (zz == 4 || zz == -4) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.RedUrushiFence.getDefaultState());
                                } else {
                                    world.setBlockToAir(aroundPos);
                                }
                            } else if (xx == -2) {
                                if (zz == -1 || zz == 0 || zz == 1) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.FramedPlaster.getDefaultState());
                                } else if (zz == 2 || zz == -2) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.FramedPlaster.getDefaultState().withProperty(FramedBlock.VARIANT2, EnumType.EnumType2.TypeB));
                                } else if (zz == 4 || zz == -4) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.RedUrushiFence.getDefaultState());
                                } else {
                                    world.setBlockToAir(aroundPos);
                                }
                            } else if (xx == -1 || xx == 0 || xx == 1) {
                                if (zz == 2) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.FramedPlaster.getDefaultState());
                                } else if (zz == -2) {
                                    if (xx == 0) {
                                        world.setBlockState(aroundPos, ModCore_Urushi.RedShouji.getDefaultState().withProperty(SlideDoorBase.FACING, EnumFacing.SOUTH).withProperty(SlideDoorBase.UNDERUPPER, SlideDoorBase.EnumTypeSlideDoorUnderUpper.UNDER));
                                    } else {
                                        world.setBlockState(aroundPos, ModCore_Urushi.RedShouji.getDefaultState().withProperty(SlideDoorBase.FACING, EnumFacing.NORTH).withProperty(SlideDoorBase.UNDERUPPER, SlideDoorBase.EnumTypeSlideDoorUnderUpper.UNDER));

                                    }
                                } else if ((zz == 1) && (xx == 1 || xx == -1)) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.Andon.getDefaultState());

                                } else if (zz == 4) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.RedUrushiFence.getDefaultState());
                                } else {
                                    if (xx == 0 && zz == 1) {
                                        world.setBlockState(aroundPos, ModCore_Urushi.WoodenCabinetry.getDefaultState());

                                    } else {
                                        world.setBlockToAir(aroundPos);
                                    }
                                }
                            } else if (xx == 4 || xx == -4) {
                                if (zz > -5 && zz < 5) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.RedUrushiFence.getDefaultState());

                                }
                            } else if (xx == 3 || xx == -3) {
                                if (zz == 4 || zz == -4) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.RedUrushiFence.getDefaultState());

                                }
                            }else {
                                world.setBlockToAir(aroundPos);
                            }

                        } else if (yy == 1) {
                            if (xx == 2) {
                                if (zz == -1 || zz == 0 || zz == 1) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.FramedPlaster.getDefaultState());
                                } else if (zz == 2 || zz == -2) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.FramedPlaster.getDefaultState().withProperty(FramedBlock.VARIANT2, EnumType.EnumType2.TypeB));
                                } else {
                                    world.setBlockToAir(aroundPos);
                                }
                            } else if (xx == -2) {
                                if (zz == -1 || zz == 0 || zz == 1) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.FramedPlaster.getDefaultState());
                                } else if (zz == 2 || zz == -2) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.FramedPlaster.getDefaultState().withProperty(FramedBlock.VARIANT2, EnumType.EnumType2.TypeB));
                                } else {
                                    world.setBlockToAir(aroundPos);
                                }
                            } else if (xx == -1 || xx == 0 || xx == 1) {
                                if (zz == 2) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.Bars.getDefaultState().withProperty(Bars.VARIANT, EnumType.EnumType8.TypeG).withProperty(Bars.VARIANT2, EnumType.EnumType2.TypeA));
                                } else if (zz == -2) {
                                    if (xx == 0) {
                                        world.setBlockState(aroundPos, ModCore_Urushi.RedShouji.getDefaultState().withProperty(SlideDoorBase.FACING, EnumFacing.SOUTH).withProperty(SlideDoorBase.UNDERUPPER, SlideDoorBase.EnumTypeSlideDoorUnderUpper.UPPER));
                                    } else {
                                        world.setBlockState(aroundPos, ModCore_Urushi.RedShouji.getDefaultState().withProperty(SlideDoorBase.FACING, EnumFacing.NORTH).withProperty(SlideDoorBase.UNDERUPPER, SlideDoorBase.EnumTypeSlideDoorUnderUpper.UPPER));

                                    }
                                } else {
                                    if (xx == 0 && zz == 1) {
                                        world.setBlockState(aroundPos, ModCore_Urushi.MirrorGate.getDefaultState());

                                    } else {
                                        world.setBlockToAir(aroundPos);
                                    }
                                }
                            }else {
                                world.setBlockToAir(aroundPos);
                            }

                        } else if (yy == 2) {
                            if (xx == 2) {
                                if (zz == -1 || zz == 0 || zz == 1) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.FramedPlaster.getDefaultState().withProperty(FramedBlock.VARIANT2, EnumType.EnumType2.TypeB));
                                } else if (zz == 2 || zz == -2) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.FramedPlaster.getDefaultState());
                                } else {
                                    world.setBlockToAir(aroundPos);
                                }
                            } else if (xx == -2) {
                                if (zz == -1 || zz == 0 || zz == 1) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.FramedPlaster.getDefaultState().withProperty(FramedBlock.VARIANT2, EnumType.EnumType2.TypeB));
                                } else if (zz == 2 || zz == -2) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.FramedPlaster.getDefaultState());
                                } else {
                                    world.setBlockToAir(aroundPos);
                                }
                            } else if (xx == -1 || xx == 0 || xx == 1) {
                                if (zz == 2 || zz == -2) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.FramedPlaster.getDefaultState().withProperty(FramedBlock.VARIANT2, EnumType.EnumType2.TypeB));
                                } else {
                                    world.setBlockToAir(aroundPos);
                                }
                            }else {
                                world.setBlockToAir(aroundPos);
                            }

                        } else if (yy == 3) {
                            if (zz == 2 || zz == -2) {
                                if (xx == -1 || xx == 0 || xx == 1 || xx == 2 || xx == -2) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.Red));
                                } else if (xx == 3) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.HiwadabukiStairs.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
                                } else if (xx == -3) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.HiwadabukiStairs.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));

                                } else {
                                    world.setBlockToAir(aroundPos);
                                }
                            } else if (zz == -1 || zz == 0 || zz == 1) {
                                if (xx == 2 || xx == -2) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.ThatchedBlock.getDefaultState().withProperty(ThatchedBlock.VARIANT, EnumType.EnumType2.TypeB));
                                } else if (xx == 3) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.HiwadabukiStairs.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
                                } else if (xx == -3) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.HiwadabukiStairs.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));

                                } else {
                                    world.setBlockToAir(aroundPos);
                                }

                            } else if (zz == -3 || zz == 3) {
                                if (xx == 2) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.HiwadabukiStairs.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP));
                                } else if (xx == -2) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.HiwadabukiStairs.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP));
                                } else if (xx == 3) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.HiwadabukiStairs.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
                                } else if (xx == -3) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.HiwadabukiStairs.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));

                                } else {
                                    world.setBlockToAir(aroundPos);
                                }

                            } else {
                                world.setBlockToAir(pos.add(xx, yy, zz));
                            }

                        } else if (yy == 4) {
                            if (zz == 2 || zz == -2) {
                                if (xx == -1 || xx == 0 || xx == 1) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.Red));
                                } else if (xx == 2) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.HiwadabukiStairs.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
                                } else if (xx == -2) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.HiwadabukiStairs.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));

                                } else {
                                    world.setBlockToAir(aroundPos);
                                }
                            } else if (zz == -1 || zz == 0 || zz == 1) {
                                if (xx == 1 || xx == -1) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.ThatchedBlock.getDefaultState().withProperty(ThatchedBlock.VARIANT, EnumType.EnumType2.TypeB));
                                } else if (xx == 2) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.HiwadabukiStairs.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
                                } else if (xx == -2) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.HiwadabukiStairs.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));

                                } else {
                                    world.setBlockToAir(aroundPos);
                                }

                            } else if (zz == -3 || zz == 3) {
                                if (xx == 1) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.HiwadabukiStairs.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP));
                                } else if (xx == -1) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.HiwadabukiStairs.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP));
                                } else if (xx == 2) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.HiwadabukiStairs.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
                                } else if (xx == -2) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.HiwadabukiStairs.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));

                                } else {
                                    world.setBlockToAir(aroundPos);
                                }

                            } else {
                                world.setBlockToAir(pos.add(xx, yy, zz));
                            }

                        } else if (yy == 5) {
                            if (zz == 2 || zz == -2) {
                                if (xx == 0) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.Red));
                                } else if (xx == 1) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.HiwadabukiStairs.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
                                } else if (xx == -1) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.HiwadabukiStairs.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));

                                } else {
                                    world.setBlockToAir(aroundPos);
                                }
                            } else if (zz == -1 || zz == 0 || zz == 1) {
                                if (xx == 0) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.ThatchedBlock.getDefaultState().withProperty(ThatchedBlock.VARIANT, EnumType.EnumType2.TypeB));
                                } else if (xx == 1) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.HiwadabukiStairs.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
                                } else if (xx == -1) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.HiwadabukiStairs.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));

                                } else {
                                    world.setBlockToAir(aroundPos);
                                }

                            } else if (zz == -3 || zz == 3) {
                                if (xx == 0) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.ThatchedBlock.getDefaultState().withProperty(ThatchedBlock.VARIANT, EnumType.EnumType2.TypeB));
                                } else if (xx == 1) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.HiwadabukiStairs.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
                                } else if (xx == -1) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.HiwadabukiStairs.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));

                                } else {
                                    world.setBlockToAir(aroundPos);
                                }

                            } else {
                                world.setBlockToAir(pos.add(xx, yy, zz));
                            }

                        } else if (yy == 6) {
                            if (xx == 0) {
                                if (zz > -4 && zz < 4) {
                                    world.setBlockState(aroundPos, ModCore_Urushi.ThatchedSlabSingle.getDefaultState().withProperty(ThatchedSlab.VARIANT, EnumType.EnumType8.TypeB).withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.BOTTOM));
                                } else {
                                    world.setBlockToAir(aroundPos);
                                }
                            } else {
                                world.setBlockToAir(pos.add(xx, yy, zz));
                            }

                        } else {
                            world.setBlockToAir(pos.add(xx, yy, zz));
                        }
                    }
                }
            }
            if (height > 3) {
                for (int u = 3; u < height; u++) {
                    for (int i = 3; i < u; i++) {
                        world.setBlockToAir(pos.add(0, -i, -u - 3));
                        world.setBlockToAir(pos.add(-1, -i, -u - 3));
                        world.setBlockToAir(pos.add(1, -i, -u - 3));
                    }
                    world.setBlockState(pos.add(-1, -u, -u - 3), ModCore_Urushi.StairsRedUrushiStained.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
                    world.setBlockState(pos.add(0, -u, -u - 3), ModCore_Urushi.StairsRedUrushiStained.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
                    world.setBlockState(pos.add(1, -u, -u - 3), ModCore_Urushi.StairsRedUrushiStained.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));

                    world.setBlockState(pos.add(-1, -u - 1, -u - 3), ModCore_Urushi.StairsRedUrushiStained.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP));
                    world.setBlockState(pos.add(0, -u - 1, -u - 3), ModCore_Urushi.StairsRedUrushiStained.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP));
                    world.setBlockState(pos.add(1, -u - 1, -u - 3), ModCore_Urushi.StairsRedUrushiStained.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP));


                    if (u == height - 1) {
                        for (int i = 3; i <= u; i++) {
                            world.setBlockToAir(pos.add(0, -i, -u - 4));
                            world.setBlockToAir(pos.add(1, -i, -u - 4));
                            world.setBlockToAir(pos.add(-1, -i, -u - 4));
                            world.setBlockToAir(pos.add(2, -i, -u - 4));
                            world.setBlockToAir(pos.add(-2, -i, -u - 4));

                            world.setBlockToAir(pos.add(0, -i, -u - 5));
                            world.setBlockToAir(pos.add(1, -i, -u - 5));
                            world.setBlockToAir(pos.add(-1, -i, -u - 5));
                            world.setBlockToAir(pos.add(2, -i, -u - 5));
                            world.setBlockToAir(pos.add(-2, -i, -u - 5));

                            world.setBlockToAir(pos.add(0, -i, -u - 6));
                            world.setBlockToAir(pos.add(1, -i, -u - 6));
                            world.setBlockToAir(pos.add(-1, -i, -u - 6));
                            world.setBlockToAir(pos.add(2, -i, -u - 6));
                            world.setBlockToAir(pos.add(-2, -i, -u - 6));

                        }

                        world.setBlockState(pos.add(-1, -u - 1, -u - 6), Blocks.COBBLESTONE.getDefaultState());
                        world.setBlockState(pos.add(1, -u - 1, -u - 6), Blocks.COBBLESTONE.getDefaultState());
                        world.setBlockState(pos.add(0, -u - 1, -u - 6), Blocks.COBBLESTONE.getDefaultState());
                        world.setBlockState(pos.add(-1, -u - 1, -u - 5), Blocks.COBBLESTONE.getDefaultState());
                        world.setBlockState(pos.add(1, -u - 1, -u - 5), Blocks.COBBLESTONE.getDefaultState());
                        world.setBlockState(pos.add(0, -u - 1, -u - 5), Blocks.COBBLESTONE.getDefaultState());
                        world.setBlockState(pos.add(-1, -u - 1, -u - 4), Blocks.COBBLESTONE.getDefaultState());
                        world.setBlockState(pos.add(1, -u - 1, -u - 4), Blocks.COBBLESTONE.getDefaultState());
                        world.setBlockState(pos.add(0, -u - 1, -u - 4), Blocks.COBBLESTONE.getDefaultState());

                        world.setBlockState(pos.add(-1, -u , -u - 6), Blocks.AIR.getDefaultState());
                        world.setBlockState(pos.add(1, -u , -u - 6), Blocks.AIR.getDefaultState());
                        world.setBlockState(pos.add(0, -u , -u - 6), Blocks.AIR.getDefaultState());
                        world.setBlockState(pos.add(-1, -u , -u - 5), Blocks.AIR.getDefaultState());
                        world.setBlockState(pos.add(1, -u , -u - 5), Blocks.AIR.getDefaultState());
                        world.setBlockState(pos.add(0, -u , -u - 5), Blocks.AIR.getDefaultState());
                        world.setBlockState(pos.add(-1, -u , -u - 4), Blocks.AIR.getDefaultState());
                        world.setBlockState(pos.add(1, -u , -u - 4), Blocks.AIR.getDefaultState());
                        world.setBlockState(pos.add(0, -u , -u - 4), Blocks.AIR.getDefaultState());

                        world.setBlockState(pos.add(-2, -u, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothBlack));
                        world.setBlockState(pos.add(2, -u, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothBlack));
                        world.setBlockState(pos.add(-2, -u + 1, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                        world.setBlockState(pos.add(2, -u + 1, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                        world.setBlockState(pos.add(-2, -u + 2, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                        world.setBlockState(pos.add(2, -u + 2, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                        world.setBlockState(pos.add(-2, -u + 3, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                        world.setBlockState(pos.add(2, -u + 3, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                        world.setBlockState(pos.add(-3, -u + 4, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                        world.setBlockState(pos.add(3, -u + 4, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                        world.setBlockState(pos.add(-2, -u + 4, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                        world.setBlockState(pos.add(2, -u + 4, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                        world.setBlockState(pos.add(-1, -u + 4, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                        world.setBlockState(pos.add(1, -u + 4, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                        world.setBlockState(pos.add(0, -u + 4, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                        world.setBlockState(pos.add(-2, -u + 5, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                        world.setBlockState(pos.add(2, -u + 5, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                        world.setBlockState(pos.add(-3, -u + 6, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                        world.setBlockState(pos.add(3, -u + 6, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                        world.setBlockState(pos.add(-2, -u + 6, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                        world.setBlockState(pos.add(2, -u + 6, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                        world.setBlockState(pos.add(-1, -u + 6, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                        world.setBlockState(pos.add(1, -u + 6, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                        world.setBlockState(pos.add(0, -u + 6, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));


                        world.setBlockState(pos.add(4, -u, 4), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothBlack));
                        world.setBlockState(pos.add(4, -u, -4), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothBlack));
                        world.setBlockState(pos.add(-4, -u, 4), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothBlack));
                        world.setBlockState(pos.add(-4, -u, -4), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothBlack));

                    } else {
                        world.setBlockState(pos.add(4, -u, 4), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                        world.setBlockState(pos.add(4, -u, -4), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                        world.setBlockState(pos.add(-4, -u, 4), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                        world.setBlockState(pos.add(-4, -u, -4), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));

                    }


                }

            } else {
                int u=2;
                world.setBlockState(pos.add(4, -2, 4), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothBlack));
                world.setBlockState(pos.add(4, -2, -4), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothBlack));
                world.setBlockState(pos.add(-4, -2, 4), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothBlack));
                world.setBlockState(pos.add(-4, -2, -4), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothBlack));

                world.setBlockState(pos.add(-1, -u - 1, -u - 6), Blocks.COBBLESTONE.getDefaultState());
                world.setBlockState(pos.add(1, -u - 1, -u - 6), Blocks.COBBLESTONE.getDefaultState());
                world.setBlockState(pos.add(0, -u - 1, -u - 6), Blocks.COBBLESTONE.getDefaultState());
                world.setBlockState(pos.add(-1, -u - 1, -u - 5), Blocks.COBBLESTONE.getDefaultState());
                world.setBlockState(pos.add(1, -u - 1, -u - 5), Blocks.COBBLESTONE.getDefaultState());
                world.setBlockState(pos.add(0, -u - 1, -u - 5), Blocks.COBBLESTONE.getDefaultState());
                world.setBlockState(pos.add(-1, -u - 1, -u - 4), Blocks.COBBLESTONE.getDefaultState());
                world.setBlockState(pos.add(1, -u - 1, -u - 4), Blocks.COBBLESTONE.getDefaultState());
                world.setBlockState(pos.add(0, -u - 1, -u - 4), Blocks.COBBLESTONE.getDefaultState());

                world.setBlockState(pos.add(-1, -u , -u - 6), Blocks.AIR.getDefaultState());
                world.setBlockState(pos.add(1, -u , -u - 6), Blocks.AIR.getDefaultState());
                world.setBlockState(pos.add(0, -u , -u - 6), Blocks.AIR.getDefaultState());
                world.setBlockState(pos.add(-1, -u , -u - 5), Blocks.AIR.getDefaultState());
                world.setBlockState(pos.add(1, -u , -u - 5), Blocks.AIR.getDefaultState());
                world.setBlockState(pos.add(0, -u , -u - 5), Blocks.AIR.getDefaultState());
                world.setBlockState(pos.add(-1, -u , -u - 4), Blocks.AIR.getDefaultState());
                world.setBlockState(pos.add(1, -u , -u - 4), Blocks.AIR.getDefaultState());
                world.setBlockState(pos.add(0, -u , -u - 4), Blocks.AIR.getDefaultState());

                world.setBlockState(pos.add(-2, -u, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothBlack));
                world.setBlockState(pos.add(2, -u, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothBlack));
                world.setBlockState(pos.add(-2, -u + 1, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                world.setBlockState(pos.add(2, -u + 1, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                world.setBlockState(pos.add(-2, -u + 2, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                world.setBlockState(pos.add(2, -u + 2, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                world.setBlockState(pos.add(-2, -u + 3, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                world.setBlockState(pos.add(2, -u + 3, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                world.setBlockState(pos.add(-3, -u + 4, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                world.setBlockState(pos.add(3, -u + 4, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                world.setBlockState(pos.add(-2, -u + 4, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                world.setBlockState(pos.add(2, -u + 4, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                world.setBlockState(pos.add(-1, -u + 4, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                world.setBlockState(pos.add(1, -u + 4, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                world.setBlockState(pos.add(0, -u + 4, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                world.setBlockState(pos.add(-2, -u + 5, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                world.setBlockState(pos.add(2, -u + 5, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                world.setBlockState(pos.add(-3, -u + 6, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                world.setBlockState(pos.add(3, -u + 6, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                world.setBlockState(pos.add(-2, -u + 6, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                world.setBlockState(pos.add(2, -u + 6, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                world.setBlockState(pos.add(-1, -u + 6, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                world.setBlockState(pos.add(1, -u + 6, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
                world.setBlockState(pos.add(0, -u + 6, -u - 6), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));


                world.setBlockState(pos.add(4, -u, 4), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothBlack));
                world.setBlockState(pos.add(4, -u, -4), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothBlack));
                world.setBlockState(pos.add(-4, -u, 4), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothBlack));
                world.setBlockState(pos.add(-4, -u, -4), ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothBlack));



            }

return true;
        }

        return false;
    }
}

package urushi.WorldGen;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import urushi.Block.*;
import urushi.Else.EnumType;
import urushi.ModCore_Urushi;

public class TeleporterBuildingGenKakuriyo extends Teleporter {
    public TeleporterBuildingGenKakuriyo(WorldServer worldIn) {
        super(worldIn);
    }

    @Override
    public boolean makePortal(Entity entityIn) {

        int posX = MathHelper.floor(entityIn.posX);
        int posY = MathHelper.floor(entityIn.posY);
        int posZ = MathHelper.floor(entityIn.posZ);

      /*  BlockPos pos=new BlockPos(posX,posY,posZ);
        for(int xx=-5;xx<6;xx++) {
            for(int yy=-1;yy<6;yy++) {
                for(int zz=-5;zz<6;zz++) {
            if(yy==-1){
    world.setBlockState(pos.add(xx,yy,zz),ModCore_Urushi.ThatchedBlock.getDefaultState());
             }else {
                world.setBlockToAir(pos.add(xx, yy, zz));
            }
                }
            }
        }*/
        BlockPos pos=new BlockPos(posX,posY,posZ);
        for(int xx=-5;xx<6;xx++) {
            for(int yy=-2;yy<6;yy++) {
                for(int zz=-5;zz<6;zz++) {
                    BlockPos aroundPos=pos.add(xx,yy,zz);
                    if(yy==-1){
                       if(xx>-3&&xx<3&&zz>-3&&zz<3){
                           world.setBlockState(aroundPos,ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.Red));
                       }else if(xx>-5&&xx<5&&zz>-5&&zz<5){
                           world.setBlockState(aroundPos,ModCore_Urushi.UWoodenSlabBSingle.getDefaultState().withProperty(U_WoodenSlabB.VARIANT, EnumType.EnumType8.TypeA).withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.TOP));
                       }else{
                           world.setBlockToAir(aroundPos);
                       }
                    }else if(yy==0){
                        if(xx==2){
                            if(zz==-1||zz==0||zz==1){
                                world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState());
                            }else if(zz==2||zz==-2){
                                world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState().withProperty(FramedBlock.VARIANT2, EnumType.EnumType2.TypeB));
                            }else{
                                world.setBlockToAir(aroundPos);
                            }
                        }else if(xx==-2){
                            if(zz==-1||zz==0||zz==1){
                                world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState());
                            }else if(zz==2||zz==-2){
                                world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState().withProperty(FramedBlock.VARIANT2, EnumType.EnumType2.TypeB));
                            }else{
                                world.setBlockToAir(aroundPos);
                            }
                        }else if(xx==-1||xx==0||xx==1){
                           if(zz==2){
                               world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState());
                           }else{
                               world.setBlockToAir(aroundPos);
                           }
                        }

                    }else if(yy==1){
                        if(xx==2){
                            if(zz==-1||zz==0||zz==1){
                                world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState());
                            }else if(zz==2||zz==-2){
                                world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState().withProperty(FramedBlock.VARIANT2, EnumType.EnumType2.TypeB));
                            }else{
                                world.setBlockToAir(aroundPos);
                            }
                        }else if(xx==-2){
                            if(zz==-1||zz==0||zz==1){
                                world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState());
                            }else if(zz==2||zz==-2){
                                world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState().withProperty(FramedBlock.VARIANT2, EnumType.EnumType2.TypeB));
                            }else{
                                world.setBlockToAir(aroundPos);
                            }
                        }else if(xx==-1||xx==0||xx==1){
                            if(zz==2){
                                world.setBlockState(aroundPos,ModCore_Urushi.Bars.getDefaultState().withProperty(Bars.VARIANT, EnumType.EnumType8.TypeG).withProperty(Bars.VARIANT2, EnumType.EnumType2.TypeA));
                            }else{
                                world.setBlockToAir(aroundPos);
                            }
                        }

                    }else if(yy==2){
                        if(xx==2){
                            if(zz==-1||zz==0||zz==1){
                                world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState().withProperty(FramedBlock.VARIANT2, EnumType.EnumType2.TypeB));
                            }else if(zz==2||zz==-2){
                                world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState());
                            }else{
                                world.setBlockToAir(aroundPos);
                            }
                        }else if(xx==-2){
                            if(zz==-1||zz==0||zz==1){
                                world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState().withProperty(FramedBlock.VARIANT2, EnumType.EnumType2.TypeB));
                            }else if(zz==2||zz==-2){
                                world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState());
                            }else{
                                world.setBlockToAir(aroundPos);
                            }
                        }else if(xx==-1||xx==0||xx==1){
                            if(zz==2){
                                world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState().withProperty(FramedBlock.VARIANT2, EnumType.EnumType2.TypeB));
                            }else{
                                world.setBlockToAir(aroundPos);
                            }
                        }

                    }
                    else{
                        world.setBlockToAir(pos.add(xx, yy, zz));
                    }
                }
            }
        }


        return true;
    }

    @Override
    public boolean placeInExistingPortal(Entity entityIn, float rotationYaw) {
        int posX = MathHelper.floor(entityIn.posX);
        int posY = MathHelper.floor(entityIn.posY);
        int posZ = MathHelper.floor(entityIn.posZ);

       /* BlockPos pos=new BlockPos(posX,posY,posZ);
        for(int xx=-5;xx<6;xx++) {
            for(int yy=-1;yy<6;yy++) {
                for(int zz=-5;zz<6;zz++) {
                    if(yy==-1){
                        world.setBlockState(pos.add(xx,yy,zz),ModCore_Urushi.ThatchedBlock.getDefaultState());
                    }else {
                        world.setBlockToAir(pos.add(xx, yy, zz));
                    }
                }
            }
        }*/

        BlockPos pos=new BlockPos(posX,posY,posZ);
        for(int xx=-5;xx<6;xx++) {
            for(int yy=-2;yy<6;yy++) {
                for(int zz=-5;zz<6;zz++) {
                    BlockPos aroundPos=pos.add(xx,yy,zz);
                    if(yy==-1){
                        if(xx>-3&&xx<3&&zz>-3&&zz<3){
                            world.setBlockState(aroundPos,ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.Red));
                        }else if(xx>-5&&xx<5&&zz>-5&&zz<5){
                            world.setBlockState(aroundPos,ModCore_Urushi.UWoodenSlabBSingle.getDefaultState().withProperty(U_WoodenSlabB.VARIANT, EnumType.EnumType8.TypeA).withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.TOP));
                        }else{
                            world.setBlockToAir(aroundPos);
                        }
                    }else if(yy==0){
                        if(xx==2){
                            if(zz==-1||zz==0||zz==1){
                                world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState());
                            }else if(zz==2||zz==-2){
                                world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState().withProperty(FramedBlock.VARIANT2, EnumType.EnumType2.TypeB));
                            }else{
                                world.setBlockToAir(aroundPos);
                            }
                        }else if(xx==-2){
                            if(zz==-1||zz==0||zz==1){
                                world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState());
                            }else if(zz==2||zz==-2){
                                world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState().withProperty(FramedBlock.VARIANT2, EnumType.EnumType2.TypeB));
                            }else{
                                world.setBlockToAir(aroundPos);
                            }
                        }else if(xx==-1||xx==0||xx==1){
                            if(zz==2){
                                world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState());
                            }else{
                                world.setBlockToAir(aroundPos);
                            }
                        }

                    }else if(yy==1){
                        if(xx==2){
                            if(zz==-1||zz==0||zz==1){
                                world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState());
                            }else if(zz==2||zz==-2){
                                world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState().withProperty(FramedBlock.VARIANT2, EnumType.EnumType2.TypeB));
                            }else{
                                world.setBlockToAir(aroundPos);
                            }
                        }else if(xx==-2){
                            if(zz==-1||zz==0||zz==1){
                                world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState());
                            }else if(zz==2||zz==-2){
                                world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState().withProperty(FramedBlock.VARIANT2, EnumType.EnumType2.TypeB));
                            }else{
                                world.setBlockToAir(aroundPos);
                            }
                        }else if(xx==-1||xx==0||xx==1){
                            if(zz==2){
                                world.setBlockState(aroundPos,ModCore_Urushi.Bars.getDefaultState().withProperty(Bars.VARIANT, EnumType.EnumType8.TypeG).withProperty(Bars.VARIANT2, EnumType.EnumType2.TypeA));
                            }else{
                                world.setBlockToAir(aroundPos);
                            }
                        }

                    }else if(yy==2){
                        if(xx==2){
                            if(zz==-1||zz==0||zz==1){
                                world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState().withProperty(FramedBlock.VARIANT2, EnumType.EnumType2.TypeB));
                            }else if(zz==2||zz==-2){
                                world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState());
                            }else{
                                world.setBlockToAir(aroundPos);
                            }
                        }else if(xx==-2){
                            if(zz==-1||zz==0||zz==1){
                                world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState().withProperty(FramedBlock.VARIANT2, EnumType.EnumType2.TypeB));
                            }else if(zz==2||zz==-2){
                                world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState());
                            }else{
                                world.setBlockToAir(aroundPos);
                            }
                        }else if(xx==-1||xx==0||xx==1){
                            if(zz==2){
                                world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState().withProperty(FramedBlock.VARIANT2, EnumType.EnumType2.TypeB));
                            }else{
                                world.setBlockToAir(aroundPos);
                            }
                        }

                    }
                    else{
                        world.setBlockToAir(pos.add(xx, yy, zz));
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void placeInPortal(Entity entityIn, float rotationYaw) {
        if (this.world.provider.getDimensionType().getId() != 1) {
            if (!this.placeInExistingPortal(entityIn, rotationYaw)) {
                this.makePortal(entityIn);
                this.placeInExistingPortal(entityIn, rotationYaw);
            }
        } else {
            int posX = MathHelper.floor(entityIn.posX);
            int posY = MathHelper.floor(entityIn.posY);
            int posZ = MathHelper.floor(entityIn.posZ);

        /*    BlockPos pos=new BlockPos(posX,posY,posZ);
            for(int xx=-5;xx<6;xx++) {
                for(int yy=-1;yy<6;yy++) {
                    for(int zz=-5;zz<6;zz++) {
                        if(yy==-1){
                            world.setBlockState(pos.add(xx,yy,zz),ModCore_Urushi.ThatchedBlock.getDefaultState());
                        }else {
                            world.setBlockToAir(pos.add(xx, yy, zz));
                        }
                    }
                }
            }*/

            BlockPos pos=new BlockPos(posX,posY,posZ);
            for(int xx=-5;xx<6;xx++) {
                for(int yy=-2;yy<6;yy++) {
                    for(int zz=-5;zz<6;zz++) {
                        BlockPos aroundPos=pos.add(xx,yy,zz);
                        if(yy==-1){
                            if(xx>-3&&xx<3&&zz>-3&&zz<3){
                                world.setBlockState(aroundPos,ModCore_Urushi.UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.Red));
                            }else if(xx>-5&&xx<5&&zz>-5&&zz<5){
                                world.setBlockState(aroundPos,ModCore_Urushi.UWoodenSlabBSingle.getDefaultState().withProperty(U_WoodenSlabB.VARIANT, EnumType.EnumType8.TypeA).withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.TOP));
                            }else{
                                world.setBlockToAir(aroundPos);
                            }
                        }else if(yy==0){
                            if(xx==2){
                                if(zz==-1||zz==0||zz==1){
                                    world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState());
                                }else if(zz==2||zz==-2){
                                    world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState().withProperty(FramedBlock.VARIANT2, EnumType.EnumType2.TypeB));
                                }else{
                                    world.setBlockToAir(aroundPos);
                                }
                            }else if(xx==-2){
                                if(zz==-1||zz==0||zz==1){
                                    world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState());
                                }else if(zz==2||zz==-2){
                                    world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState().withProperty(FramedBlock.VARIANT2, EnumType.EnumType2.TypeB));
                                }else{
                                    world.setBlockToAir(aroundPos);
                                }
                            }else if(xx==-1||xx==0||xx==1){
                                if(zz==2){
                                    world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState());
                                }else{
                                    world.setBlockToAir(aroundPos);
                                }
                            }

                        }else if(yy==1){
                            if(xx==2){
                                if(zz==-1||zz==0||zz==1){
                                    world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState());
                                }else if(zz==2||zz==-2){
                                    world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState().withProperty(FramedBlock.VARIANT2, EnumType.EnumType2.TypeB));
                                }else{
                                    world.setBlockToAir(aroundPos);
                                }
                            }else if(xx==-2){
                                if(zz==-1||zz==0||zz==1){
                                    world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState());
                                }else if(zz==2||zz==-2){
                                    world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState().withProperty(FramedBlock.VARIANT2, EnumType.EnumType2.TypeB));
                                }else{
                                    world.setBlockToAir(aroundPos);
                                }
                            }else if(xx==-1||xx==0||xx==1){
                                if(zz==2){
                                    world.setBlockState(aroundPos,ModCore_Urushi.Bars.getDefaultState().withProperty(Bars.VARIANT, EnumType.EnumType8.TypeG).withProperty(Bars.VARIANT2, EnumType.EnumType2.TypeA));
                                }else{
                                    world.setBlockToAir(aroundPos);
                                }
                            }

                        }else if(yy==2){
                            if(xx==2){
                                if(zz==-1||zz==0||zz==1){
                                    world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState().withProperty(FramedBlock.VARIANT2, EnumType.EnumType2.TypeB));
                                }else if(zz==2||zz==-2){
                                    world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState());
                                }else{
                                    world.setBlockToAir(aroundPos);
                                }
                            }else if(xx==-2){
                                if(zz==-1||zz==0||zz==1){
                                    world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState().withProperty(FramedBlock.VARIANT2, EnumType.EnumType2.TypeB));
                                }else if(zz==2||zz==-2){
                                    world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState());
                                }else{
                                    world.setBlockToAir(aroundPos);
                                }
                            }else if(xx==-1||xx==0||xx==1){
                                if(zz==2){
                                    world.setBlockState(aroundPos,ModCore_Urushi.FramedPlaster.getDefaultState().withProperty(FramedBlock.VARIANT2, EnumType.EnumType2.TypeB));
                                }else{
                                    world.setBlockToAir(aroundPos);
                                }
                            }

                        }
                        else{
                            world.setBlockToAir(pos.add(xx, yy, zz));
                        }
                    }
                }
            }





        }
    }

    @Override
    public void removeStalePortalLocations(long worldTime) {
        super.removeStalePortalLocations(worldTime);
    }
}

package urushi.TileEntity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import urushi.Block.RiceCauldron;
import urushi.ModCore_Urushi;

public class TileEntityRiceHokora extends TileEntity implements ITickable
{


    public int manaStored;
    public int coolTimeA;
    public int coolTimeB;
    public int coolTimeC;
    public int coolTimeD;
    public int coolTimeE;
    public int coolTimeF;
    public int coolTimeG;
    public int coolTimeH;

    private int debugCoolTimeAmount=200;





    public void readFromNBT(NBTTagCompound compound)
    {
        this.manaStored = compound.getInteger("ManaStored");
        this.coolTimeA = compound.getInteger("CoolTimeA");
        this.coolTimeB = compound.getInteger("CoolTimeB");
        this.coolTimeC = compound.getInteger("CoolTimeC");
        this.coolTimeD = compound.getInteger("CoolTimeD");
        this.coolTimeE = compound.getInteger("CoolTimeE");
        this.coolTimeF = compound.getInteger("CoolTimeF");
        this.coolTimeG = compound.getInteger("CoolTimeG");
        this.coolTimeH = compound.getInteger("CoolTimeH");

        super.readFromNBT(compound);

    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        compound.setInteger("ManaStored", this.manaStored);
        compound.setInteger("CoolTimeA", this.coolTimeA);
        compound.setInteger("CoolTimeB", this.coolTimeB);
        compound.setInteger("CoolTimeC", this.coolTimeC);
        compound.setInteger("CoolTimeD", this.coolTimeD);
        compound.setInteger("CoolTimeE", this.coolTimeE);
        compound.setInteger("CoolTimeF", this.coolTimeF);
        compound.setInteger("CoolTimeG", this.coolTimeG);
        compound.setInteger("CoolTimeH", this.coolTimeH);


        return   super.writeToNBT(compound);
       //    return compound;
    }
    public void update()
    {

int amountPer1=300;
        if( world.getBlockState(pos.add(1,0,0)).getBlock()==ModCore_Urushi.Tawara&&coolTimeA<debugCoolTimeAmount) {
            coolTimeA++;
        } else if( world.getBlockState(pos.add(1,0,0)).getBlock()==ModCore_Urushi.Tawara){
            coolTimeA=0;
            manaStored+=amountPer1;
            if(!world.isRemote) world.setBlockToAir(pos.add(1,0,0));
        }

        if( world.getBlockState(pos.add(-1,0,0)).getBlock()==ModCore_Urushi.Tawara&&coolTimeB<debugCoolTimeAmount) {
            coolTimeB++;
        } else if( world.getBlockState(pos.add(-1,0,0)).getBlock()==ModCore_Urushi.Tawara){
            coolTimeB=0;
            manaStored+=amountPer1;
            if(!world.isRemote) world.setBlockToAir(pos.add(-1,0,0));
        }

        if( world.getBlockState(pos.add(0,0,1)).getBlock()==ModCore_Urushi.Tawara&&coolTimeC<debugCoolTimeAmount) {
            coolTimeC++;
        } else if( world.getBlockState(pos.add(0,0,1)).getBlock()==ModCore_Urushi.Tawara){
            coolTimeC=0;
            manaStored+=amountPer1;
            if(!world.isRemote) world.setBlockToAir(pos.add(0,0,1));
        }

        if( world.getBlockState(pos.add(0,0,-1)).getBlock()==ModCore_Urushi.Tawara&&coolTimeD<debugCoolTimeAmount) {
            coolTimeD++;
        } else if( world.getBlockState(pos.add(0,0,-1)).getBlock()==ModCore_Urushi.Tawara){
            coolTimeD=0;
            manaStored+=amountPer1;
            if(!world.isRemote) world.setBlockToAir(pos.add(0,0,-1));
        }

        if( world.getBlockState(pos.add(1,0,1)).getBlock()==ModCore_Urushi.Tawara&&coolTimeE<debugCoolTimeAmount) {
            coolTimeE++;
        } else if( world.getBlockState(pos.add(1,0,1)).getBlock()==ModCore_Urushi.Tawara){
            coolTimeE=0;
            manaStored+=amountPer1;
            if(!world.isRemote) world.setBlockToAir(pos.add(1,0,1));
        }

        if( world.getBlockState(pos.add(1,0,-1)).getBlock()==ModCore_Urushi.Tawara&&coolTimeF<debugCoolTimeAmount) {
            coolTimeF++;
        } else if( world.getBlockState(pos.add(1,0,-1)).getBlock()==ModCore_Urushi.Tawara){
            coolTimeF=0;
            manaStored+=amountPer1;
            if(!world.isRemote) world.setBlockToAir(pos.add(1,0,-1));
        }

        if( world.getBlockState(pos.add(-1,0,1)).getBlock()==ModCore_Urushi.Tawara&&coolTimeG<debugCoolTimeAmount) {
            coolTimeG++;
        } else if( world.getBlockState(pos.add(-1,0,1)).getBlock()==ModCore_Urushi.Tawara){
            coolTimeG=0;
            manaStored+=amountPer1;
            if(!world.isRemote) world.setBlockToAir(pos.add(-1,0,1));
        }

        if( world.getBlockState(pos.add(-1,0,-1)).getBlock()==ModCore_Urushi.Tawara&&coolTimeH<debugCoolTimeAmount) {
            coolTimeH++;
        } else if( world.getBlockState(pos.add(-1,0,-1)).getBlock()==ModCore_Urushi.Tawara){
            coolTimeH=0;
            manaStored+=amountPer1;
            if(!world.isRemote) world.setBlockToAir(pos.add(-1,0,-1));
        }




    }




}
package urushi.TileEntity;


import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import net.minecraft.util.ITickable;

import urushi.Block.FermentationPot;
import urushi.ModCore_Urushi;

import static urushi.Block.FermentationPot.VARIANT;

public class TileEntityFermentationPot extends TileEntity implements ITickable
{


    private int cookTime;
    public int itemAmount;






   public void update()
    {
          if(((FermentationPot.EnumType) getWorld().getBlockState(pos).getValue(VARIANT))== FermentationPot.EnumType.Leaves) {
                if (cookTime < 400) {
                    ++cookTime;
                } else {
                    getWorld().setBlockState(getPos(), ModCore_Urushi.FermentationPot.getDefaultState().withProperty(FermentationPot.VARIANT, FermentationPot.EnumType.Dirt));
                    if(getWorld().getTileEntity(getPos()) instanceof  TileEntityFermentationPot) {
                        TileEntityFermentationPot tileEntityFermentationPot = (TileEntityFermentationPot) getWorld().getTileEntity(getPos());
                        tileEntityFermentationPot.itemAmount = this.itemAmount;
                    }
                    cookTime = 0;
                }
            }else if(((FermentationPot.EnumType) getWorld().getBlockState(pos).getValue(VARIANT))== FermentationPot.EnumType.Meat) {
                if (cookTime < 400) {
                    ++cookTime;
                } else {
                    getWorld().setBlockState(getPos(), ModCore_Urushi.FermentationPot.getDefaultState().withProperty(FermentationPot.VARIANT, FermentationPot.EnumType.RottenMeat));
                    if(getWorld().getTileEntity(getPos()) instanceof  TileEntityFermentationPot) {
                        TileEntityFermentationPot tileEntityFermentationPot = (TileEntityFermentationPot) getWorld().getTileEntity(getPos());
                        tileEntityFermentationPot.itemAmount = this.itemAmount;
                    }
                   cookTime = 0;
                }
            }else if(((FermentationPot.EnumType) getWorld().getBlockState(pos).getValue(VARIANT))== FermentationPot.EnumType.Rice) {
              if (cookTime < 400) {
                  ++cookTime;
              } else {
                  getWorld().setBlockState(getPos(), ModCore_Urushi.FermentationPot.getDefaultState().withProperty(FermentationPot.VARIANT, FermentationPot.EnumType.RiceMalt));
                  if(getWorld().getTileEntity(getPos()) instanceof  TileEntityFermentationPot) {
                      TileEntityFermentationPot tileEntityFermentationPot = (TileEntityFermentationPot) getWorld().getTileEntity(getPos());
                      tileEntityFermentationPot.itemAmount = this.itemAmount;
                  }
                  cookTime = 0;
              }
          }else if(((FermentationPot.EnumType) getWorld().getBlockState(pos).getValue(VARIANT))== FermentationPot.EnumType.MaturedApricot) {
              if (cookTime < 400) {
                  ++cookTime;
              } else {
                  getWorld().setBlockState(getPos(), ModCore_Urushi.FermentationPot.getDefaultState().withProperty(FermentationPot.VARIANT, FermentationPot.EnumType.PickledApricot));
                  if(getWorld().getTileEntity(getPos()) instanceof  TileEntityFermentationPot) {
                      TileEntityFermentationPot tileEntityFermentationPot = (TileEntityFermentationPot) getWorld().getTileEntity(getPos());
                      tileEntityFermentationPot.itemAmount = this.itemAmount;
                  }
                  cookTime = 0;
              }
          }


    }
    public void readFromNBT(NBTTagCompound compound)
    {
        this.cookTime = compound.getInteger("CookTime");
        this.itemAmount = compound.getInteger("ItemAmount");

        super.readFromNBT(compound);

    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        compound.setInteger("CookTime", (short)this.cookTime);
        compound.setInteger("ItemAmount", this.itemAmount);

        return   super.writeToNBT(compound);

    }





}
package urushi.TileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import urushi.Block.Kama;
import urushi.Block.RiceCauldron;
import urushi.ModCore_Urushi;

public class TileEntityRiceCauldron extends TileEntity implements ITickable
{


    private int cookTime;
    public int itemAmount;



    public void update()
    {

        if(world.getBlockState(pos.add(0,-1,0)).getBlock()==ModCore_Urushi.DirtFurnace&&world.getBlockState(pos.add(0,-1,0)).getBlock().getMetaFromState(world.getBlockState(pos.add(0,-1,0)))>3){
            if(getBlockMetadata()==2) {
                if (cookTime < 200) {
                    ++cookTime;
                } else {
                   getWorld().setBlockState(getPos(), ModCore_Urushi.RiceCauldron.getDefaultState().withProperty(RiceCauldron.VARIANT, RiceCauldron.EnumType.ClosedRice1));
                    if(getWorld().getTileEntity(getPos()) instanceof  TileEntityRiceCauldron) {
                        TileEntityRiceCauldron tileEntity = (TileEntityRiceCauldron) getWorld().getTileEntity(getPos());
                        tileEntity.itemAmount = this.itemAmount;
                    }
                   cookTime = 0;
                }
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
       // return compound==null? compound:super.writeToNBT(compound);
         //  return compound;
    }




}
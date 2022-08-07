package urushi.TileEntity;


import net.minecraft.nbt.NBTTagCompound;

import net.minecraft.tileentity.TileEntity;

import net.minecraft.util.ITickable;

import urushi.Block.Kama;
import urushi.Else.EnumType;
import urushi.ModCore_Urushi;

import javax.annotation.Nullable;

public class TileEntityKama extends TileEntity implements ITickable
{

   // private NonNullList<ItemStack> stacks = NonNullList.<ItemStack>withSize(2, ItemStack.EMPTY);
    //private int openCount;
    private int cookTime;
    public int itemAmount;
    //public int blockMeta=getBlockMetadata();


   /* public void setItemAmount(int i) {

      //  this.itemAmount = itemAmount;
       // writeToNBT(serializeNBT());
        serializeNBT().setInteger("ItemAmount",i);
    }
public int getItemAmount(){

        return serializeNBT().getInteger("ItemAmount");
}
*/
    public void update()
    {
        // boolean isBurning = false;
        // ItemStack itemstack=getItems().get(0);
        if(world.getBlockState(pos.add(0,-1,0)).getBlock()==ModCore_Urushi.DirtFurnace&&world.getBlockState(pos.add(0,-1,0)).getBlock().getMetaFromState(world.getBlockState(pos.add(0,-1,0)))>3){
        if(getBlockMetadata()==1) {
            if (cookTime < 200) {
                ++cookTime;
            } else {
                //   this.stacks.set(1,new ItemStack(ModCore_Urushi.UItems,stacks.get(0).getCount(),6));
                //  this.stacks.set(0,null);
                getWorld().setBlockState(getPos(), ModCore_Urushi.RiceCauldron.getDefaultState().withProperty(Kama.VARIANT, Kama.EnumType.ClosedRice));
                cookTime = 0;
            }
        }
        }

    }
    public void readFromNBT(NBTTagCompound compound)
    {
        //   this.stacks = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        this.cookTime = compound.getInteger("CookTime");
        this.itemAmount = compound.getInteger("ItemAmount");
        // if (!this.checkLootAndRead(compound))
        //  {
        //      ItemStackHelper.loadAllItems(compound, this.stacks);
        //  }

        //   if (compound.hasKey("CustomName", 8))
        //   {
        //       this.customName = compound.getString("CustomName");
        //   }
        super.readFromNBT(compound);

    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
    //    super.writeToNBT(compound);
        compound.setInteger("CookTime", (short)this.cookTime);
        compound.setInteger("ItemAmount", this.itemAmount);
        //  if (!this.checkLootAndWrite(compound))
        //  {
        //      ItemStackHelper.saveAllItems(compound, this.stacks);
        //  }

        //  if (this.hasCustomName())
        // {
        //      compound.setString("CustomName", this.customName);
        // }
       return   super.writeToNBT(compound);
     //   return compound;
    }

  /*  @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        this.writeToNBT(nbtTagCompound);
        return new SPacketUpdateTileEntity(pos, 1, nbtTagCompound);
       // return super.getUpdatePacket();
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        this.readFromNBT(pkt.getNbtCompound());
    }
*/
   /*   public int getSizeInventory()
    {
        return this.stacks.size();
    }

    public boolean isEmpty()
    {
        for (ItemStack itemstack : this.stacks)
        {
            if (!itemstack.isEmpty())
            {
                return false;
            }
        }

        return true;
    }



    public int addItemStack(ItemStack stack)
    {
        for (int i = 0; i < this.stacks.size(); ++i)
        {
            if (((ItemStack)this.stacks.get(i)).isEmpty())
            {
                this.setInventorySlotContents(i, stack);
                return i;
            }
        }

        return -1;
    }
    public ItemStack getStackInSlot(int index)
    {
        return this.stacks.get(index);
    }


    public ItemStack decrStackSize(int index, int count)
    {
        return ItemStackHelper.getAndSplit(this.stacks, index, count);
    }


    public ItemStack removeStackFromSlot(int index)
    {
        return ItemStackHelper.getAndRemove(this.stacks, index);
    }


    public String getName()
    {
        ITextComponent i=new TextComponentTranslation("item.woodencabinetry.container", new Object[0]);

        return this.hasCustomName() ? this.customName : i.getFormattedText();
    }

    public static void registerFixes(DataFixer fixer)
    {
        fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(TileEntityKama.class, new String[] {"Items"}));
    }

  */



 /*
    public int getInventoryStackLimit()
    {
        return 64;
    }

    public String getGuiID()
    {
        return "minecraft:furnace";
    }

    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        //this.fillWithLoot(playerIn);
        return new ContainerFurnace(playerInventory, this);
    }

    protected NonNullList<ItemStack> getItems()
    {
        return this.stacks;
    }

    public void openInventory(EntityPlayer player)
    {
        if (!player.isSpectator())
        {
            if (this.openCount < 0)
            {
                this.openCount = 0;
            }

            ++this.openCount;
            this.world.addBlockEvent(this.pos, this.getBlockType(), 1, this.openCount);

            if (this.openCount == 1)
            {


                this.world.playSound((EntityPlayer)null, this.pos, SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
            }
        }
    }

    public void closeInventory(EntityPlayer player)
    {
        if (!player.isSpectator())
        {
            --this.openCount;
            this.world.addBlockEvent(this.pos, this.getBlockType(), 1, this.openCount);

            if (this.openCount <= 0)
            {
                this.world.playSound((EntityPlayer)null, this.pos, SoundEvents.BLOCK_CHEST_CLOSE, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
            }
        }
    }


    public void setInventorySlotContents(int index, ItemStack stack)
    {
        ItemStack itemstack = this.stacks.get(index);
     //   boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
        this.stacks.set(index, stack);

        if (stack.getCount() > this.getInventoryStackLimit())
        {
            stack.setCount(this.getInventoryStackLimit());
        }

        if (index == 1
             //   && !flag
        )
        {
          //  this.stacks = this.getCookTime(stack);
            this.cookTime = 0;
            this.markDirty();
        }
    }*/


}
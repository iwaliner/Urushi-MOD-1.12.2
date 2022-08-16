package urushi.Container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;

public class ContainerDoubledWoodenCabinetry extends Container
{
    private final IInventory lowerChestInventory;
    private final int numRows;
    private int wide=13;

    public ContainerDoubledWoodenCabinetry(IInventory playerInventory, IInventory chestInventory, EntityPlayer player)
    {
        this.lowerChestInventory = chestInventory;
        this.numRows = 8;
        chestInventory.openInventory(player);
        int i = (this.numRows - 4) * 18;

        for (int j = 0; j < this.numRows; ++j)
        {
            for (int k = 0; k <wide; ++k)
            {
                this.addSlotToContainer(new Slot(chestInventory, k + j * wide, 8 + k * 18, 18 + j * 18));
            }
        }
        this.addSlotToContainer(new Slot(chestInventory, 104, 8 + (12 * 18), 18 + 126+18));
        this.addSlotToContainer(new Slot(chestInventory, 105, 8 + (12 * 18), 18 + 126+18*2));
        this.addSlotToContainer(new Slot(chestInventory, 106, 8 + (12 * 18), 18 + 126+18*3));
        this.addSlotToContainer(new Slot(chestInventory, 107, 8 + (12 * 18), 18 + 126+18*4));
        for (int l = 0; l < 3; ++l)
        {
            for (int j1 = 0; j1 < 9; ++j1)
            {
                this.addSlotToContainer(new Slot(playerInventory, j1 + l * 9 + 9, 8 + j1 * 18+2*18, 103 + l * 18 + i+1));
            }
        }

        for (int i1 = 0; i1 < 9; ++i1)
        {
            this.addSlotToContainer(new Slot(playerInventory, i1, 8 + i1 * 18+2*18, 161 + i+1));
        }



    }

    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return this.lowerChestInventory.isUsableByPlayer(playerIn);
    }

    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();


   if (index < 108)
            {
                if (!this.mergeItemStack(itemstack1, 108, this.inventorySlots.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, 108, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    public void onContainerClosed(EntityPlayer playerIn)
    {
        super.onContainerClosed(playerIn);
        this.lowerChestInventory.closeInventory(playerIn);
    }

}
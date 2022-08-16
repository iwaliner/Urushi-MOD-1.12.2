package urushi.TileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import urushi.Container.ContainerDoubledWoodenCabinetry;

public class TileEntityDoubledWoodenCabinetry extends TileEntityLockableLoot {
    private NonNullList<ItemStack> stacks = NonNullList.<ItemStack>withSize(108, ItemStack.EMPTY);
    private int openCount;
    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return 108;
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


    /**
     * Add the given ItemStack to this Dispenser. Return the Slot the Item was placed in or -1 if no free slot is
     * available.
     */
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

    /**
     * Get the name of this object. For players this returns their username
     */
    public String getName()
    {
        ITextComponent i=new TextComponentTranslation("item.doubledwoodencabinetry.container", new Object[0]);

        return this.hasCustomName() ? this.customName : i.getFormattedText();
    }

    public static void registerFixes(DataFixer fixer)
    {
        fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(TileEntityDoubledWoodenCabinetry.class, new String[] {"Items"}));
    }

    public void readFromNBT(NBTTagCompound compound)
    {

        this.stacks = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);

        if (!this.checkLootAndRead(compound))
        {
            ItemStackHelper.loadAllItems(compound, this.stacks);
        }

        if (compound.hasKey("CustomName", 8))
        {
            this.customName = compound.getString("CustomName");
        }
        super.readFromNBT(compound);
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        if (!this.checkLootAndWrite(compound))
        {
            ItemStackHelper.saveAllItems(compound, this.stacks);
        }

        if (this.hasCustomName())
        {
            compound.setString("CustomName", this.customName);
        }

        return compound;
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended.
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    public String getGuiID()
    {
        return "urushi:double_wooden_cabinetry";
    }

    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        this.fillWithLoot(playerIn);
        return new ContainerDoubledWoodenCabinetry(playerInventory, this, playerIn);
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







}

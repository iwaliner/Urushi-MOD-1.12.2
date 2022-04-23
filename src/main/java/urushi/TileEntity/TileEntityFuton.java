package urushi.TileEntity;

import net.minecraft.block.BlockBed;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import urushi.ModCore_Urushi;

public class TileEntityFuton extends TileEntity
{

    public SPacketUpdateTileEntity getUpdatePacket()
    {
        return new SPacketUpdateTileEntity(this.pos, 11, this.getUpdateTag());
    }



    @SideOnly(Side.CLIENT)
    public boolean isHeadPiece()
    {
        return BlockBed.isHeadPiece(this.getBlockMetadata());
    }

    public ItemStack getItemStack()
    {
        return new ItemStack(ModCore_Urushi.UItems, 1, 33);
    }
}

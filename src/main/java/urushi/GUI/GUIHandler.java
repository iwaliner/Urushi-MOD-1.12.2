package urushi.GUI;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import urushi.Block.DoubledWoodenCabinetry;
import urushi.Container.ContainerDoubledWoodenCabinetry;
import urushi.TileEntity.TileEntityDoubledWoodenCabinetry;

public class GUIHandler implements IGuiHandler {
    public static final int DoubledWoodenCabinetry = 1;
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == DoubledWoodenCabinetry) {
            return new ContainerDoubledWoodenCabinetry(player.inventory,(TileEntityDoubledWoodenCabinetry) world.getTileEntity(new BlockPos(x,y,z)),player);
        }
        return null;
    }
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == DoubledWoodenCabinetry){
            return new GUIDoubledWoodenCabinetry(player.inventory,(TileEntityDoubledWoodenCabinetry) world.getTileEntity(new BlockPos(x,y,z)));
        }
        return null;
    }
}

package urushi.GUI;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import urushi.Container.ContainerDoubledWoodenCabinetry;

@SideOnly(Side.CLIENT)
public class GUIDoubledWoodenCabinetry extends GuiContainer {
    private static final ResourceLocation DISPENSER_GUI_TEXTURES = new ResourceLocation("urushi:textures/gui/doubled_wooden_cabinetry.png");
    private final InventoryPlayer playerInventory;
    public IInventory dispenserInventory;

    public GUIDoubledWoodenCabinetry(InventoryPlayer playerInv, IInventory dispenserInv)
    {
        super(new ContainerDoubledWoodenCabinetry(playerInv, dispenserInv, playerInv.player));
        this.playerInventory = playerInv;
        this.dispenserInventory = dispenserInv;
        this.xSize+=4*18;
        //this.ySize+=2*18;
        this.ySize = 114 +8* 18;
       // this.inventoryLabelY = this.imageHeight - 94;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String s = this.dispenserInventory.getDisplayName().getUnformattedText();
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(DISPENSER_GUI_TEXTURES);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        //this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
        this.drawTexturedModalRect( i, j, 0, 0, this.xSize, 8 * 18 + 17);
        this.drawTexturedModalRect( i, j + 8 * 18 + 17-(2*18), 0, 126-1, this.xSize, 96+(2*18)-2);

    }

}

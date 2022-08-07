package urushi.Render;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelMinecart;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import urushi.Entity.EntityCushion;
import urushi.Model.ModelCushion;

@SideOnly(Side.CLIENT)
public class RenderCushion<T extends EntityCushion> extends Render<T>
{
    private static final ResourceLocation[] CUSHION_TEXTURES = new ResourceLocation[] {new ResourceLocation("urushi:textures/entity/cushion_white.png"), new ResourceLocation("urushi:textures/entity/cushion_orange.png"), new ResourceLocation("urushi:textures/entity/cushion_magenta.png"), new ResourceLocation("urushi:textures/entity/cushion_light_blue.png"), new ResourceLocation("urushi:textures/entity/cushion_yellow.png"), new ResourceLocation("urushi:textures/entity/cushion_lime.png")
            ,new ResourceLocation("urushi:textures/entity/cushion_pink.png") ,new ResourceLocation("urushi:textures/entity/cushion_gray.png") ,new ResourceLocation("urushi:textures/entity/cushion_silver.png") ,new ResourceLocation("urushi:textures/entity/cushion_cyan.png") ,new ResourceLocation("urushi:textures/entity/cushion_purple.png") ,new ResourceLocation("urushi:textures/entity/cushion_blue.png") ,new ResourceLocation("urushi:textures/entity/cushion_brown.png")
            ,new ResourceLocation("urushi:textures/entity/cushion_green.png") ,new ResourceLocation("urushi:textures/entity/cushion_red.png") ,new ResourceLocation("urushi:textures/entity/cushion_black.png")};

    protected ModelBase modelMinecart = new ModelCushion();

    public RenderCushion(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
        this.shadowSize = 0F;
    }


    public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.pushMatrix();
         GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.rotate(180.0F - entityYaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.pushMatrix();
            this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            GlStateManager.popMatrix();
            this.bindEntityTexture(entity);
            GlStateManager.scale(1.0F, 1.0F, 1.0F);
        this.modelMinecart.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }


    protected ResourceLocation getEntityTexture(T entity)
    {

      return   CUSHION_TEXTURES[entity.getColorType().ordinal()];
    }


}
package urushi.Render;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import urushi.Entity.EntityOni;
import urushi.Model.ModelRedOni;


@SideOnly(Side.CLIENT)
public class RenderOni extends RenderBiped<EntityOni>
{
    private static final ResourceLocation ZOMBIE_TEXTURES = new ResourceLocation("urushi:textures/entity/oni_red.png");

    public RenderOni(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelRedOni(), 0.5F);
        LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this)
        {
            protected void initArmor()
            {
                this.modelLeggings = new ModelRedOni();
                this.modelArmor = new ModelRedOni();
            }
        };
        this.addLayer(layerbipedarmor);
    }

    protected ResourceLocation getEntityTexture(EntityOni entity)
    {
        return ZOMBIE_TEXTURES;
    }
}
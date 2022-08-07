package urushi.Render;

import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import urushi.Entity.EntityOni;
import urushi.Entity.EntityOniGirl;
import urushi.Model.ModelGirlOni;
import urushi.Model.ModelRedOni;


@SideOnly(Side.CLIENT)
public class RenderOniGirl extends RenderBiped<EntityOniGirl>
{
    private static final ResourceLocation ZOMBIE_TEXTURES = new ResourceLocation("urushi:textures/entity/oni_girl.png");

    public RenderOniGirl(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelGirlOni(), 0.5F);
        LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this)
        {
            protected void initArmor()
            {
                this.modelLeggings = new ModelGirlOni();
                this.modelArmor = new ModelGirlOni();
            }
        };
        this.addLayer(layerbipedarmor);
    }

    protected ResourceLocation getEntityTexture(EntityOniGirl entity)
    {
        return ZOMBIE_TEXTURES;
    }
}
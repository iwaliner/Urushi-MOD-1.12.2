package urushi.Model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelCushionOld extends ModelBase
{
    public ModelRenderer[] sideModels = new ModelRenderer[1];

    public ModelCushionOld()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.sideModels[0] = new ModelRenderer(this, 0, 0);


        this.sideModels[0].addBox(-6F, -8F, -8F, 12, 12, 3, 0.0F);
        this.sideModels[0].setRotationPoint(0.0F, 0F, 0.0F);

       // this.sideModels[0].rotateAngleX = ((float)Math.PI / 2F);

    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {

            this.sideModels[0].render(scale);

    }
}
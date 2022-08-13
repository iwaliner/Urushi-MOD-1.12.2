package urushi.Proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.tileentity.TileEntityEndPortalRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import urushi.Entity.EntityCushion;
import urushi.Entity.EntityOni;
import urushi.Entity.EntityOniGirl;
import urushi.Entity.EntityOnibi;
import urushi.ModCore_Urushi;
import urushi.Render.RenderCushion;
import urushi.Render.RenderOni;
import urushi.Render.RenderOniGirl;
import urushi.TileEntity.*;

import static urushi.ModCore_Urushi.*;
import static urushi.ModCore_Urushi.UItems;

public class ClientProxy extends CommonProxy {
    @Override
    public World getClientWorld(){
        return FMLClientHandler.instance().getClient().world;
    }
    @Override
    public void registerTileEntity() {
        GameRegistry.registerTileEntity(TileEntityRiceCauldron.class,new ResourceLocation(ModCore_Urushi.modid,"rice_cauldron"));
        GameRegistry.registerTileEntity(TileEntityWCabinetry.class,new ResourceLocation(ModCore_Urushi.modid,"wooden_cabinetry"));
        GameRegistry.registerTileEntity(TileEntityWoodenCabinetrySlab.class,new ResourceLocation(ModCore_Urushi.modid,"wooden_cabinetry_under_slab"));
        GameRegistry.registerTileEntity(TileEntityRiceHokora.class,new ResourceLocation(ModCore_Urushi.modid,"rice_hokora"));
        GameRegistry.registerTileEntity(TileEntityFuton.class,new ResourceLocation(ModCore_Urushi.modid,"futon"));
        GameRegistry.registerTileEntity(TileEntityFermentationPot.class,new ResourceLocation(ModCore_Urushi.modid,"fermentation_pot"));

    }
    public void registerEntityRender(){
        RenderingRegistry.registerEntityRenderingHandler(EntityCushion.class, new IRenderFactory<EntityCushion>(){
            @Override
            public Render<? super EntityCushion> createRenderFor(RenderManager manager){
                return new RenderCushion(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityOni.class, new IRenderFactory<EntityOni>(){
            @Override
            public Render<? super EntityOni> createRenderFor(RenderManager manager){
                return new RenderOni(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityOniGirl.class, new IRenderFactory<EntityOniGirl>(){
            @Override
            public Render<? super EntityOniGirl> createRenderFor(RenderManager manager){
                return new RenderOniGirl(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityOnibi.class, new IRenderFactory<EntityOnibi>(){
            @Override
            public Render<? super EntityOnibi> createRenderFor(RenderManager manager){
                Minecraft mcIn = Minecraft.getMinecraft();
                return (Render)new RenderSnowball<EntityOnibi>(manager, ModCore_Urushi.ItemOnibi, mcIn.getRenderItem());
            }
        });
    }
    public void registerFurnaceRecipe(){
        GameRegistry.addSmelting(new ItemStack(UItems, 1, 10), new ItemStack(UItems, 1, 3), 5F);
        GameRegistry.addSmelting(new ItemStack(UStone, 1, 2), new ItemStack(UItems, 1, 9), 5F);
        GameRegistry.addSmelting(new ItemStack(UItems, 1, 5), new ItemStack(Rice, 1, 0), 5F);
        GameRegistry.addSmelting(new ItemStack(Mochi, 1, 0), new ItemStack(YakiMochi, 1, 0), 5F);
        GameRegistry.addSmelting(new ItemStack(UItems, 1, 35), new ItemStack(FermentationPot, 1, 0), 5F);
        GameRegistry.addSmelting(new ItemStack(UItems, 1, 0), new ItemStack(UItems, 1, 61), 5F);
    }
}

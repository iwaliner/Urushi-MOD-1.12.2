package urushi;


import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.*;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import urushi.Block.*;
import urushi.Else.EnumType;
import urushi.WorldGen.OreGen;
import urushi.Else.TabUrushi;
import urushi.Entity.EntityCushion;
import urushi.Item.*;
import urushi.Itemblock.ItemBlockMetadata;
import urushi.Render.RenderCushion;
import urushi.TileEntity.*;
import urushi.WorldGen.WorldProviderKakuriyo;

import java.util.Random;


@Mod(modid = "urushi", version = "alpha2.22", name = "Urushi MOD")
public class  ModCore_Urushi {
   public static String modid="urushi";
    public static final CreativeTabs TabUrushi = new TabUrushi("TabUrushi");
    public static final Block UPlanks = new U_Planks();
    public static final ItemBlock ItemBlockUPlanks=new ItemBlockMetadata(UPlanks);
    public static final Block ULog = new U_Log();
    public static final ItemBlock ItemBlockULog=new ItemBlockMetadata(ULog);
    public static final BlockSlab UWoodenSlabASingle = new U_WoodenSlabA() {@Override public boolean isDouble() {return false;}};
    public static final BlockSlab UWoodenSlabADouble = new U_WoodenSlabA() {@Override public boolean isDouble() {return true;}};
    public static final ItemBlock ItemBlockUWoodenSlabA=new ItemSlab(UWoodenSlabASingle, UWoodenSlabASingle, UWoodenSlabADouble);
    public static final BlockSlab UWoodenSlabBSingle = new U_WoodenSlabB() {@Override public boolean isDouble() {return false;}};
    public static final BlockSlab UWoodenSlabBDouble = new U_WoodenSlabB() {@Override public boolean isDouble() {return true;}};
    public static final ItemBlock ItemBlockUWoodenSlabB=new ItemSlab(UWoodenSlabBSingle, UWoodenSlabBSingle, UWoodenSlabBDouble);
    public static final Block UStone = new U_Stone();
    public static final ItemBlock ItemBlockUStone=new ItemBlockMetadata(UStone);
    public static final Block SmoothOakStairs = new Stairs(UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothOak));
    public static final Block SmoothSpruceStairs = new Stairs(UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothSpruce));
    public static final Block SmoothBirchStairs = new Stairs(UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothBirch));
    public static final Block SmoothJungleStairs = new Stairs(UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothJungle));
    public static final Block SmoothAcaciaStairs = new Stairs(UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothAcacia));
    public static final Block SmoothDarkOakStairs = new Stairs(UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothDarkoak));
    public static final Block JapaneseApricotStairs = new Stairs(UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.JapaneseApricot));
    public static final Block SmoothJapaneseApricotStairs = new Stairs(UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothJapaneseApricot));
    public static final Block SakuraStairs = new Stairs(UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.Sakura));
    public static final Block SmoothSakuraStairs = new Stairs(UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothSakura));
    public static final Block StairsRedUrushiStained = new Stairs(UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.Red));
    public static final Block SmoothStairsRedUrushiStained = new Stairs(UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothRed));
    public static final Block StairsBlackUrushiStained = new Stairs(UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.Black));
    public static final Block SmoothStairsBlackUrushiStained = new Stairs(UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothBlack));
    public static final Block CupricOxideKawaraStairs = new Stairs(UStone.getDefaultState().withProperty(U_Stone.VARIANT, U_Stone.EnumType.CupricOxideKawara));
    public static final Block IbushiKawaraStairs = new Stairs(UStone.getDefaultState().withProperty(U_Stone.VARIANT, U_Stone.EnumType.IbushiKawara));
    public static final Block PlasterStairs = new Stairs(UStone.getDefaultState().withProperty(U_Stone.VARIANT, U_Stone.EnumType.Plaster));
    public static final BlockFlower LycorisRadiata = (BlockFlower) new BlockYellowFlower().setCreativeTab(TabUrushi);
    public static final Block TrapdoorRedUrushiStained = new WoodenTrapDoor();
    public static final Item UItems = new UItems();
    public static final Block WoodenCabinetry = new BlockWoodenCabinetry();
    public static final TileEntity TileEntityWoodenCabinetry = new TileEntityWCabinetry();
    public static final Block WoodenCabinetryUnderSlab = new WoodenCabinetrySlab();
    public static final TileEntity TileEntityWoodenCabinetryUnderSlab = new TileEntityWoodenCabinetrySlab();
    public static final Block JapaneseTimberBamboo = new JapaneseTimberBamboo();
    public static final Block JapaneseTimberBambooShoot = new JapaneseTimberBambooShoot();
    public static final Block BambooWall = new PaneWall(Material.WOOD);
    public static final Block WoodenBucket = new WoodenBucket();
    public static final ItemBlock ItemBlockWoodenBucket=new ItemBlockMetadata(WoodenBucket);
    public static final Block ULeaves = new U_Leaves();
    public static final ItemBlock ItemBlockULeaves=new ItemBlockMetadata(ULeaves);
    public static final BlockSlab UStoneSlabSingle = new U_StoneSlab() {@Override public boolean isDouble() {return false;}};
    public static final BlockSlab UStoneSlabDouble = new U_StoneSlab() {@Override public boolean isDouble() {return true;}};
    public static final ItemBlock ItemBlockUStoneSlab=new ItemSlab(UStoneSlabSingle, UStoneSlabSingle, UStoneSlabDouble);
     public static final Block ChiseledLacquerLog = new ChiseledLacquerLog();
    public static final Block USapling = new USapling();
    public static final ItemBlock ItemBlockUSapling=new ItemBlockMetadata(USapling);
    public static final Block PaddyField = new PaddyField();
    public static final Block CropRice = new CropRice();
    public static final Item RiceEars = new RiceEars();
    public static final Block Tatami = new Tatami();
    public static final ItemBlock ItemBlockTatami=new ItemBlockMetadata(Tatami);
    public static final Block Tawara = new Tawara();
    public static final Item RiceBall = new ItemFood(6, 0.6F, false);
    public static final Item Mochi = new ItemFood(4, 0.6F, false);
    public static final Item KusaDango = new ItemFood(8, 1F, false);
    public static final Item ColorDango = new ItemFood(8, 1F, false);
    public static final Item MitarashiDango = new ItemFood(8, 1F, false);
    public static final Item YakiMochi = new ItemFood(8, 1F, false);
    public static final Block DirtFurnace = new DirtFurnace();
    public static final Block RiceCauldron = new RiceCauldron();
    public static final ItemBlock ItemBlockRiceCauldron=new ItemBlockMetadata(RiceCauldron);
    public static final TileEntity TileEntityRiceCauldron = new TileEntityRiceCauldron();
    public static final Block WattleAndDaub = new WattleAndDaub();
    public static final Block OakShouji = new SlideDoorBase(11);
    public static final Block SpruceShouji = new SlideDoorBase(12);
    public static final Block RedShouji = new SlideDoorBase(13);
    public static final Block ShoujiPaneOak = new PaneWall(Material.WOOD);
     public static final Block ShoujiPaneSpruce = new PaneWall(Material.WOOD);
    public static final Block ShoujiPaneRed = new PaneWall(Material.WOOD);
    public static final Block RiceHokora = new RiceHokora();
    public static final TileEntity TileEntityRiceHokora = new TileEntityRiceHokora();
    public static final Item QuartzMagatama = new QuartzMagatama();
   public static final Block BlankFusuma = new SlideDoorBase(30);
    public static final Block SlidingGlassDoor = new SlideDoorBase(7);
    public static final Block Bars = new Bars();
    public static final ItemBlock ItemBlockBars=new ItemBlockMetadata(Bars);
    public static final Block FramedPlaster = new FramedBlock();
    public static final ItemBlock ItemBlockFramedPlaster=new ItemBlockMetadata(FramedPlaster);
    public static final Block CopperKawaraStairs = new Stairs(UStone.getDefaultState().withProperty(U_Stone.VARIANT, U_Stone.EnumType.CopperKawara));
    public static final BlockSlab KawaraSlabASingle = new KawaraSlabA() {@Override public boolean isDouble() {return false;}};
    //public static final BlockSlab KawaraSlabADouble = new KawaraSlabA() {@Override public boolean isDouble() {return true;}};
    public static final ItemBlock ItemBlockKawaraSlabASingle=new ItemBlockMetadata(KawaraSlabASingle);
    public static final BlockSlab KawaraSlabBSingle = new KawaraSlabB() {@Override public boolean isDouble() {return false;}};
    public static final ItemBlock ItemBlockKawaraSlabBSingle=new ItemBlockMetadata(KawaraSlabBSingle);
    public static final Block SandCoast = new SandCoat();
    public static final Block ThatchedBlock = new ThatchedBlock();
    public static final ItemBlock ItemBlockThatchedBlock=new ItemBlockMetadata(ThatchedBlock);
    public static final BlockSlab ThatchedSlabSingle = new ThatchedSlab() {@Override public boolean isDouble() {return false;}};
    public static final BlockSlab ThatchedSlabDouble = new ThatchedSlab() {@Override public boolean isDouble() {return true;}};
    public static final ItemBlock ItemBlockThatchedSlab=new ItemSlab(ThatchedSlabSingle, ThatchedSlabSingle, ThatchedSlabDouble);
    public static final Block ThatchedStairs = new StairsThatched(ThatchedBlock.getDefaultState());
    public static final Block Andon = new PaperLamp();
    public static final Block AriakeAndon = new AriakeAndon();
    public static final Block Futon = new Futon();
    public static final TileEntity TileEntityFuton = new TileEntityFuton();
    public static boolean wheather_ganerate_Sakura=true;
    public static boolean wheather_ganerate_Ume=true;
    public static boolean wheather_ganerate_Urushi=true;
    public static boolean wheather_ganerate_Bamboo=true;
    public static boolean wheather_ganerate_CopperOre=true;
    public static boolean wheather_ganerate_Cypress=true;
    public static int max_height_Bamboo=10;
   // public static final Item HayatoShield=new ItemShield();
   public static final Block OakShitomi = new Shitomi();
    public static final Block SpruceShitomi = new Shitomi();
    public static final Block BirchShitomi = new Shitomi();
    public static final Block JungleShitomi = new Shitomi();
   public static final Block AcaciaShitomi = new Shitomi();
    public static final Block DarkOakShitomi = new Shitomi();
    public static final Block RedShitomi = new Shitomi();
    public static final Block BlackShitomi = new Shitomi();
    public static final Block JapaneseApricotShitomi = new Shitomi();
    public static final Block SakuraShitomi = new Shitomi();
    public static  DimensionType Kakuriyo_DIMENSION ;
    public static final Item Rice = new ItemFood(4, 0.6F, false);
    public static final Block RedUrushiDoor = new Door();
    public static final ItemBlock ItemBlockSandCoast=new ItemBlockMetadata(SandCoast);
    public static final Block CypressStairs = new Stairs(UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.Cypress));
    public static final Block SmoothCypressStairs = new Stairs(UPlanks.getDefaultState().withProperty(U_Planks.VARIANT, U_Planks.EnumType.SmoothCypress));
    public static final Block HiwadabukiStairs = new StairsThatched(ThatchedBlock.getDefaultState().withProperty(urushi.Block.ThatchedBlock.VARIANT, EnumType.EnumType2.TypeB));
    public static final Block FermentationPot = new FermentationPot();
    public static final TileEntity TileEntityFermentationPot = new TileEntityFermentationPot();
    public static final Item ImmatureApricot = new ImmatureApricot(1, 0.1F, false);
    public static final Item MaturedApricot = new ItemFood(2, 0.2F, false);
    public static final Item PickledApricot = new ItemFood(4, 0.4F, false);
    public static final Item Azuki = new Azuki();
    public static final Block CropAzuki = new CropAzuki();
    public static final Item Ohagi = new ItemFood(8, 1F, false);
    public static final Item SakuraMochi = new ItemFood(8, 1F, false);
    public static final Item Gyudon = new ItemFood(10, 1.2F, false);
    public static final Item Butadon = new ItemFood(10, 1.2F, false);
    public static final Item SalmonSashimi = new ItemFood(4, 0.4F, false);
   public static final Item Charm = new Charm();
    public static final Block ULeaves2 = new U_Leaves2();
    public static boolean SakuraLeavesAndJapaneseApricotLeavesIsLight=true;
    public static final ItemBlock ItemBlockULeaves2=new ItemBlockMetadata(ULeaves2);

    @EventHandler
    public void construct(FMLConstructionEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.TERRAIN_GEN_BUS.register(this);
    }



    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(ItemBlockUPlanks.setRegistryName(modid, "u_planks"));
        event.getRegistry().register(ItemBlockULog.setRegistryName(modid, "u_log"));
        event.getRegistry().register(ItemBlockUWoodenSlabA.setRegistryName(modid, "u_wooden_slab_a_single"));
        event.getRegistry().register(ItemBlockUWoodenSlabB.setRegistryName(modid, "u_wooden_slab_b_single"));
        event.getRegistry().register(ItemBlockUStone.setRegistryName(modid, "u_stone"));
        event.getRegistry().register(new ItemBlock(SmoothOakStairs).setRegistryName(modid, "smooth_oak_stairs"));
        event.getRegistry().register(new ItemBlock(SmoothSpruceStairs).setRegistryName(modid, "smooth_spruce_stairs"));
        event.getRegistry().register(new ItemBlock(SmoothBirchStairs).setRegistryName(modid, "smooth_birch_stairs"));
        event.getRegistry().register(new ItemBlock(SmoothJungleStairs).setRegistryName(modid, "smooth_jungle_stairs"));
        event.getRegistry().register(new ItemBlock(SmoothAcaciaStairs).setRegistryName(modid, "smooth_acacia_stairs"));
        event.getRegistry().register(new ItemBlock(SmoothDarkOakStairs).setRegistryName(modid, "smooth_dark_oak_stairs"));
        event.getRegistry().register(new ItemBlock(JapaneseApricotStairs).setRegistryName(modid, "japanese_apricot_stairs"));
        event.getRegistry().register(new ItemBlock(SmoothJapaneseApricotStairs).setRegistryName(modid, "smooth_japanese_apricot_stairs"));
        event.getRegistry().register(new ItemBlock(SakuraStairs).setRegistryName(modid, "sakura_stairs"));
        event.getRegistry().register(new ItemBlock(SmoothSakuraStairs).setRegistryName(modid, "smooth_sakura_stairs"));
        event.getRegistry().register(new ItemBlock(StairsRedUrushiStained).setRegistryName(modid, "stairs_red_urushi_stained"));
        event.getRegistry().register(new ItemBlock(SmoothStairsRedUrushiStained).setRegistryName(modid, "smooth_stairs_red_urushi_stained"));
        event.getRegistry().register(new ItemBlock(StairsBlackUrushiStained).setRegistryName(modid, "stairs_black_urushi_stained"));
        event.getRegistry().register(new ItemBlock(SmoothStairsBlackUrushiStained).setRegistryName(modid, "smooth_stairs_black_urushi_stained"));
        event.getRegistry().register(new ItemBlock(CupricOxideKawaraStairs).setRegistryName(modid, "cupric_oxide_kawara_stairs"));
        event.getRegistry().register(new ItemBlock(IbushiKawaraStairs).setRegistryName(modid, "ibushi_kawara_stairs"));
       event.getRegistry().register(new ItemBlock(LycorisRadiata).setRegistryName(modid, "lycoris_radiata"));
        event.getRegistry().register(new ItemBlock(TrapdoorRedUrushiStained).setRegistryName(modid, "trapdoor_red_urushi_stained"));
        event.getRegistry().register(UItems);
        event.getRegistry().register(new ItemBlock(WoodenCabinetry).setRegistryName(modid, "wooden_cabinetry"));
        event.getRegistry().register(new ItemBlock(WoodenCabinetryUnderSlab).setRegistryName(modid, "wooden_cabinetry_under_slab"));
        event.getRegistry().register(new ItemBlock(JapaneseTimberBambooShoot).setRegistryName(modid, "japanese_timber_bamboo_shoot"));
        event.getRegistry().register(new ItemBlock(BambooWall).setRegistryName(modid, "bamboo_wall"));
        event.getRegistry().register(ItemBlockWoodenBucket.setRegistryName(modid, "wooden_bucket"));
        event.getRegistry().register(ItemBlockULeaves.setRegistryName(modid, "u_leaves"));
        event.getRegistry().register(ItemBlockUStoneSlab.setRegistryName(modid, "u_stone_slab_single"));
       event.getRegistry().register(new ItemBlock(ChiseledLacquerLog).setRegistryName(modid, "chiseled_lacquer_log"));
        event.getRegistry().register(ItemBlockUSapling.setRegistryName(modid, "u_sapling"));
        event.getRegistry().register(new ItemBlock(PlasterStairs).setRegistryName(modid, "plaster_stairs"));
        event.getRegistry().register(new ItemBlock(Tawara).setRegistryName(modid, "tawara"));
        event.getRegistry().register(RiceEars);
        event.getRegistry().register(ItemBlockTatami.setRegistryName(modid, "tatami_block"));
        event.getRegistry().register(RiceBall.setUnlocalizedName("RiceBall").setRegistryName("rice_ball").setCreativeTab(TabUrushi));
        event.getRegistry().register(Mochi.setUnlocalizedName("RiceCake").setRegistryName("rice_cake").setCreativeTab(TabUrushi));
        event.getRegistry().register(KusaDango.setUnlocalizedName("KusaDango").setRegistryName("kusa_dango").setCreativeTab(TabUrushi));
        event.getRegistry().register(ColorDango.setUnlocalizedName("ColorDango").setRegistryName("color_dango").setCreativeTab(TabUrushi));
        event.getRegistry().register(MitarashiDango.setUnlocalizedName("MitarashiDango").setRegistryName("mitarashi_dango").setCreativeTab(TabUrushi));
        event.getRegistry().register(YakiMochi.setUnlocalizedName("RoastedRiceCake").setRegistryName("roasted_rice_cake").setCreativeTab(TabUrushi));
        event.getRegistry().register(new ItemBlock(DirtFurnace).setRegistryName(modid, "dirt_furnace"));
        event.getRegistry().register(ItemBlockRiceCauldron.setRegistryName(modid, "rice_cauldron"));
        event.getRegistry().register(new ItemBlock(WattleAndDaub).setRegistryName(modid, "wattle_and_daub"));
         event.getRegistry().register(new ItemBlock(ShoujiPaneOak).setRegistryName(modid, "shouji_pane_oak"));
        event.getRegistry().register(new ItemBlock(ShoujiPaneSpruce).setRegistryName(modid, "shouji_pane_spruce"));
        event.getRegistry().register(new ItemBlock(ShoujiPaneRed).setRegistryName(modid, "shouji_pane_red"));
        event.getRegistry().register(new ItemBlock(RiceHokora).setRegistryName(modid, "rice_hokora"));
        event.getRegistry().register(QuartzMagatama);
        event.getRegistry().register(ItemBlockBars.setRegistryName(modid, "bars"));
        event.getRegistry().register(ItemBlockFramedPlaster.setRegistryName(modid, "framed_plaster"));
        event.getRegistry().register(new ItemBlock(CopperKawaraStairs).setRegistryName(modid, "copper_kawara_stairs"));
        event.getRegistry().register(ItemBlockKawaraSlabASingle.setRegistryName(modid, "kawara_slab_a_single"));
        event.getRegistry().register(ItemBlockKawaraSlabBSingle.setRegistryName(modid, "kawara_slab_b_single"));
        event.getRegistry().register(ItemBlockSandCoast.setRegistryName(modid, "sand_coast"));
        event.getRegistry().register(ItemBlockThatchedBlock.setRegistryName(modid, "thatched_block"));
        event.getRegistry().register(ItemBlockThatchedSlab.setRegistryName(modid, "thatched_slab_single"));
        event.getRegistry().register(new ItemBlock(ThatchedStairs).setRegistryName(modid, "thatched_stairs"));
    //    event.getRegistry().register(HayatoShield.setRegistryName(modid, "hayato_shield"));
        event.getRegistry().register(new ItemBlock(OakShitomi).setRegistryName(modid, "oak_shitomi"));
        event.getRegistry().register(new ItemBlock(SpruceShitomi).setRegistryName(modid, "spruce_shitomi"));
        event.getRegistry().register(new ItemBlock(BirchShitomi).setRegistryName(modid, "birch_shitomi"));
        event.getRegistry().register(new ItemBlock(JungleShitomi).setRegistryName(modid, "jungle_shitomi"));
        event.getRegistry().register(new ItemBlock(AcaciaShitomi).setRegistryName(modid, "acacia_shitomi"));
         event.getRegistry().register(new ItemBlock(DarkOakShitomi).setRegistryName(modid, "dark_oak_shitomi"));
        event.getRegistry().register(new ItemBlock(RedShitomi).setRegistryName(modid, "red_shitomi"));
        event.getRegistry().register(new ItemBlock(BlackShitomi).setRegistryName(modid, "black_shitomi"));
        event.getRegistry().register(new ItemBlock(JapaneseApricotShitomi).setRegistryName(modid, "japanese_apricot_shitomi"));
        event.getRegistry().register(new ItemBlock(SakuraShitomi).setRegistryName(modid, "sakura_shitomi"));
        event.getRegistry().register(Rice.setUnlocalizedName("Rice").setRegistryName("rice").setCreativeTab(TabUrushi));
        event.getRegistry().register(new ItemBlock(CypressStairs).setRegistryName(modid, "cypress_stairs"));
        event.getRegistry().register(new ItemBlock(SmoothCypressStairs).setRegistryName(modid, "smooth_cypress_stairs"));
        event.getRegistry().register(new ItemBlock(HiwadabukiStairs).setRegistryName(modid, "hiwadabuki_stairs"));
        event.getRegistry().register(new ItemBlock(FermentationPot).setRegistryName(modid, "fermentation_pot"));
        event.getRegistry().register(ImmatureApricot.setUnlocalizedName("ImmatureApricot").setRegistryName("immature_apricot").setCreativeTab(TabUrushi));
        event.getRegistry().register(MaturedApricot.setUnlocalizedName("MaturedApricot").setRegistryName("matured_apricot").setCreativeTab(TabUrushi));
        event.getRegistry().register(PickledApricot.setUnlocalizedName("PickledApricot").setRegistryName("pickled_apricot").setCreativeTab(TabUrushi));
        event.getRegistry().register(Azuki);
        event.getRegistry().register(Ohagi.setUnlocalizedName("Ohagi").setRegistryName("ohagi").setCreativeTab(TabUrushi));
        event.getRegistry().register(SakuraMochi.setUnlocalizedName("SakuraMochi").setRegistryName("sakura_mochi").setCreativeTab(TabUrushi));
        event.getRegistry().register(Gyudon.setUnlocalizedName("Gyudon").setRegistryName("gyudon").setCreativeTab(TabUrushi));
        event.getRegistry().register(Butadon.setUnlocalizedName("Butadon").setRegistryName("butadon").setCreativeTab(TabUrushi));
        event.getRegistry().register(SalmonSashimi.setUnlocalizedName("SalmonSashimi").setRegistryName("salmon_sashimi").setCreativeTab(TabUrushi));
        event.getRegistry().register(Charm);
        event.getRegistry().register(ItemBlockULeaves2.setRegistryName(modid, "u_leaves2"));


    }


    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(UPlanks.setRegistryName(modid,"u_planks").setUnlocalizedName("UPlanks"));
        event.getRegistry().register(ULog.setRegistryName(modid,"u_log").setUnlocalizedName("ULog"));
        event.getRegistry().register(UWoodenSlabASingle.setRegistryName(modid,"u_wooden_slab_a_single").setUnlocalizedName("UWoodenSlabASingle"));
        event.getRegistry().register(UWoodenSlabADouble.setRegistryName(modid,"u_wooden_slab_a_double").setUnlocalizedName("UWoodenSlabADouble"));
        event.getRegistry().register(UWoodenSlabBSingle.setRegistryName(modid,"u_wooden_slab_b_single").setUnlocalizedName("UWoodenSlabBSingle"));
        event.getRegistry().register(UWoodenSlabBDouble.setRegistryName(modid,"u_wooden_slab_b_double").setUnlocalizedName("UWoodenSlabBDouble"));
        event.getRegistry().register(UStone.setRegistryName(modid,"u_stone").setUnlocalizedName("UStone"));
        event.getRegistry().register(SmoothOakStairs.setRegistryName(modid,"smooth_oak_stairs").setUnlocalizedName("SmoothOakStairs"));
        event.getRegistry().register(SmoothSpruceStairs.setRegistryName(modid,"smooth_spruce_stairs").setUnlocalizedName("SmoothSpruceStairs"));
        event.getRegistry().register(SmoothBirchStairs.setRegistryName(modid,"smooth_birch_stairs").setUnlocalizedName("SmoothBirchStairs"));
        event.getRegistry().register(SmoothJungleStairs.setRegistryName(modid,"smooth_jungle_stairs").setUnlocalizedName("SmoothJungleStairs"));
        event.getRegistry().register(SmoothAcaciaStairs.setRegistryName(modid,"smooth_acacia_stairs").setUnlocalizedName("SmoothAcaciaStairs"));
        event.getRegistry().register(SmoothDarkOakStairs.setRegistryName(modid,"smooth_dark_oak_stairs").setUnlocalizedName("SmoothDarkOakStairs"));
        event.getRegistry().register(JapaneseApricotStairs.setRegistryName(modid,"japanese_apricot_stairs").setUnlocalizedName("JapaneseApricotStairs"));
        event.getRegistry().register(SmoothJapaneseApricotStairs.setRegistryName(modid,"smooth_japanese_apricot_stairs").setUnlocalizedName("SmoothJapaneseApricotStairs"));
        event.getRegistry().register(SakuraStairs.setRegistryName(modid,"sakura_stairs").setUnlocalizedName("SakuraStairs"));
        event.getRegistry().register(SmoothSakuraStairs.setRegistryName(modid,"smooth_sakura_stairs").setUnlocalizedName("SmoothSakuraStairs"));
        event.getRegistry().register(StairsRedUrushiStained.setRegistryName(modid,"stairs_red_urushi_stained").setUnlocalizedName("StairsRedUrushiStained"));
        event.getRegistry().register(SmoothStairsRedUrushiStained.setRegistryName(modid,"smooth_stairs_red_urushi_stained").setUnlocalizedName("SmoothStairsRedUrushiStained"));
        event.getRegistry().register(StairsBlackUrushiStained.setRegistryName(modid,"stairs_black_urushi_stained").setUnlocalizedName("StairsBlackUrushiStained"));
        event.getRegistry().register(SmoothStairsBlackUrushiStained.setRegistryName(modid,"smooth_stairs_black_urushi_stained").setUnlocalizedName("SmoothStairsBlackUrushiStained"));
        event.getRegistry().register(CupricOxideKawaraStairs.setRegistryName(modid,"cupric_oxide_kawara_stairs").setUnlocalizedName("CupricOxideKawaraStairs"));
        event.getRegistry().register(IbushiKawaraStairs.setRegistryName(modid,"ibushi_kawara_stairs").setUnlocalizedName("IbushiKawaraStairs"));
        event.getRegistry().register(LycorisRadiata.setRegistryName(modid,"lycoris_radiata").setUnlocalizedName("LycorisRadiata"));
        event.getRegistry().register(TrapdoorRedUrushiStained.setRegistryName(modid,"trapdoor_red_urushi_stained").setUnlocalizedName("TrapdoorRedUrushiStained"));
        event.getRegistry().register(WoodenCabinetry.setRegistryName(modid,"wooden_cabinetry").setUnlocalizedName("WoodenCabinetry"));
        event.getRegistry().register(WoodenCabinetryUnderSlab.setRegistryName(modid,"wooden_cabinetry_under_slab").setUnlocalizedName("WoodenCabinetryUnderSlab"));
       event.getRegistry().register(JapaneseTimberBamboo.setRegistryName(modid,"japanese_timber_bamboo").setUnlocalizedName("JapaneseTimberBamboo"));
        event.getRegistry().register(JapaneseTimberBambooShoot.setRegistryName(modid,"japanese_timber_bamboo_shoot").setUnlocalizedName("JapaneseTimberBambooShoot"));
        event.getRegistry().register(BambooWall.setRegistryName(modid,"bamboo_wall").setUnlocalizedName("BambooWall"));
        event.getRegistry().register(WoodenBucket.setRegistryName(modid,"wooden_bucket").setUnlocalizedName("WoodenBucket"));
        event.getRegistry().register(ULeaves.setRegistryName(modid,"u_leaves").setUnlocalizedName("ULeaves"));
        event.getRegistry().register(UStoneSlabSingle.setRegistryName(modid,"u_stone_slab_single").setUnlocalizedName("UStoneSlabSingle"));
        event.getRegistry().register(UStoneSlabDouble.setRegistryName(modid,"u_stone_slab_double").setUnlocalizedName("UStoneSlabDouble"));
        event.getRegistry().register(ChiseledLacquerLog.setRegistryName(modid,"chiseled_lacquer_log").setUnlocalizedName("ChiseledLacquerLog"));
        event.getRegistry().register(USapling.setRegistryName(modid,"u_sapling").setUnlocalizedName("USapling"));
        event.getRegistry().register(PlasterStairs.setRegistryName(modid,"plaster_stairs").setUnlocalizedName("PlasterStairs"));
        event.getRegistry().register(PaddyField.setRegistryName(modid,"paddy_field").setUnlocalizedName("PaddyField"));
        event.getRegistry().register(CropRice.setRegistryName(modid,"crop_rice").setUnlocalizedName("CropRice"));
        event.getRegistry().register(Tatami.setRegistryName(modid,"tatami_block").setUnlocalizedName("TatamiBlock"));
        event.getRegistry().register(Tawara.setRegistryName(modid,"tawara").setUnlocalizedName("Tawara"));
        event.getRegistry().register(DirtFurnace.setRegistryName(modid,"dirt_furnace").setUnlocalizedName("DirtFurnace"));
        event.getRegistry().register(RiceCauldron.setRegistryName(modid,"rice_cauldron").setUnlocalizedName("RiceCauldron"));
        event.getRegistry().register(WattleAndDaub.setRegistryName(modid,"wattle_and_daub").setUnlocalizedName("WattleAndDaub"));
        event.getRegistry().register(OakShouji.setRegistryName(modid,"oak_shouji").setUnlocalizedName("OakShouji"));
        event.getRegistry().register(SpruceShouji.setRegistryName(modid,"spruce_shouji").setUnlocalizedName("SpruceShouji"));
        event.getRegistry().register(RedShouji.setRegistryName(modid,"red_shouji").setUnlocalizedName("RedShouji"));
        event.getRegistry().register(ShoujiPaneOak.setRegistryName(modid,"shouji_pane_oak").setUnlocalizedName("ShoujiPaneOak"));
        event.getRegistry().register(ShoujiPaneSpruce.setRegistryName(modid,"shouji_pane_spruce").setUnlocalizedName("ShoujiPaneSpruce"));
        event.getRegistry().register(ShoujiPaneRed.setRegistryName(modid,"shouji_pane_red").setUnlocalizedName("ShoujiPaneRed"));
        event.getRegistry().register(RiceHokora.setRegistryName(modid,"rice_hokora").setUnlocalizedName("RiceHokora"));
        event.getRegistry().register(BlankFusuma.setRegistryName(modid,"blank_fusuma").setUnlocalizedName("BlankFusuma"));
        event.getRegistry().register(SlidingGlassDoor.setRegistryName(modid,"sliding_glass_door").setUnlocalizedName("SlidingGlassDoor"));
        event.getRegistry().register(Bars.setRegistryName(modid,"bars").setUnlocalizedName("Bars"));
         event.getRegistry().register(FramedPlaster.setRegistryName(modid,"framed_plaster").setUnlocalizedName("FramedPlaster"));
        event.getRegistry().register(CopperKawaraStairs.setRegistryName(modid,"copper_kawara_stairs").setUnlocalizedName("CopperKawaraStairs"));
        event.getRegistry().register(KawaraSlabASingle.setRegistryName(modid,"kawara_slab_a_single").setUnlocalizedName("KawaraSlabASingle"));
      //  event.getRegistry().register(KawaraSlabADouble.setRegistryName(modid,"kawara_slab_a_double").setUnlocalizedName("KawaraSlabADouble"));
        event.getRegistry().register(KawaraSlabBSingle.setRegistryName(modid,"kawara_slab_b_single").setUnlocalizedName("KawaraSlabBSingle"));
        event.getRegistry().register(SandCoast.setRegistryName(modid,"sand_coast").setUnlocalizedName("SandCoast"));
        event.getRegistry().register(ThatchedBlock.setRegistryName(modid,"thatched_block").setUnlocalizedName("ThatchedBlock"));
        event.getRegistry().register(ThatchedSlabSingle.setRegistryName(modid,"thatched_slab_single").setUnlocalizedName("ThatchedSlabSingle"));
        event.getRegistry().register(ThatchedSlabDouble.setRegistryName(modid,"thatched_slab_double").setUnlocalizedName("ThatchedSlabDouble"));
        event.getRegistry().register(ThatchedStairs.setRegistryName(modid,"thatched_stairs").setUnlocalizedName("ThatchedStairs"));
        event.getRegistry().register(Andon.setRegistryName(modid,"andon").setUnlocalizedName("Andon"));
        event.getRegistry().register(AriakeAndon.setRegistryName(modid,"ariake_andon").setUnlocalizedName("AriakeAndon"));
        event.getRegistry().register(Futon.setRegistryName(modid,"futon").setUnlocalizedName("Futon"));
        event.getRegistry().register(OakShitomi.setRegistryName(modid,"oak_shitomi").setUnlocalizedName("OakShitomi"));
        event.getRegistry().register(SpruceShitomi.setRegistryName(modid,"spruce_shitomi").setUnlocalizedName("SpruceShitomi"));
        event.getRegistry().register(BirchShitomi.setRegistryName(modid,"birch_shitomi").setUnlocalizedName("BirchShitomi"));
        event.getRegistry().register(JungleShitomi.setRegistryName(modid,"jungle_shitomi").setUnlocalizedName("JungleShitomi"));
        event.getRegistry().register(AcaciaShitomi.setRegistryName(modid,"acacia_shitomi").setUnlocalizedName("AcaciaShitomi"));
         event.getRegistry().register(DarkOakShitomi.setRegistryName(modid,"dark_oak_shitomi").setUnlocalizedName("DarkOakShitomi"));
        event.getRegistry().register(RedShitomi.setRegistryName(modid,"red_shitomi").setUnlocalizedName("RedShitomi"));
        event.getRegistry().register(BlackShitomi.setRegistryName(modid,"black_shitomi").setUnlocalizedName("BlackShitomi"));
        event.getRegistry().register(JapaneseApricotShitomi.setRegistryName(modid,"japanese_apricot_shitomi").setUnlocalizedName("JapaneseApricotShitomi"));
        event.getRegistry().register(SakuraShitomi.setRegistryName(modid,"sakura_shitomi").setUnlocalizedName("SakuraShitomi"));
        event.getRegistry().register(RedUrushiDoor.setRegistryName(modid,"red_urushi_door").setUnlocalizedName("RedUrushiDoor"));
        event.getRegistry().register(CypressStairs.setRegistryName(modid,"cypress_stairs").setUnlocalizedName("CypressStairs"));
        event.getRegistry().register(SmoothCypressStairs.setRegistryName(modid,"smooth_cypress_stairs").setUnlocalizedName("SmoothCypressStairs"));
        event.getRegistry().register(HiwadabukiStairs.setRegistryName(modid,"hiwadabuki_stairs").setUnlocalizedName("HiwadabukiStairs"));
        event.getRegistry().register(FermentationPot.setRegistryName(modid,"fermentation_pot").setUnlocalizedName("FermentationPot"));
        event.getRegistry().register(CropAzuki.setRegistryName(modid,"crop_azuki").setUnlocalizedName("CropAzuki"));
        event.getRegistry().register(ULeaves2.setRegistryName(modid,"u_leaves2").setUnlocalizedName("ULeaves2"));

    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void registerModels(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(UItems, 0, new ModelResourceLocation(new ResourceLocation(modid, "bamboo_item"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 1, new ModelResourceLocation(new ResourceLocation(modid, "copper_kawara"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 2, new ModelResourceLocation(new ResourceLocation(modid, "cupric_oxide_kawara"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 3, new ModelResourceLocation(new ResourceLocation(modid, "ibushi_kawara"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 4, new ModelResourceLocation(new ResourceLocation(modid, "hihiiroakane_ingot"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 5, new ModelResourceLocation(new ResourceLocation(modid, "raw_rice"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(RiceEars, 0, new ModelResourceLocation(new ResourceLocation(modid, "rice_ears"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 8, new ModelResourceLocation(new ResourceLocation(modid, "urushi_glue"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 9, new ModelResourceLocation(new ResourceLocation(modid, "copper_ingot"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 10, new ModelResourceLocation(new ResourceLocation(modid, "raw_ibushi_kawara"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 11, new ModelResourceLocation(new ResourceLocation(modid, "shouji_oak"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 12, new ModelResourceLocation(new ResourceLocation(modid, "shouji_spruce"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 13, new ModelResourceLocation(new ResourceLocation(modid, "shouji_red"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UPlanks), 0, new ModelResourceLocation(new ResourceLocation(modid, "smooth_oak_planks"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UPlanks), 1, new ModelResourceLocation(new ResourceLocation(modid, "smooth_spruce_planks"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UPlanks), 2, new ModelResourceLocation(new ResourceLocation(modid, "smooth_birch_planks"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UPlanks), 3, new ModelResourceLocation(new ResourceLocation(modid, "smooth_jungle_planks"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UPlanks), 4, new ModelResourceLocation(new ResourceLocation(modid, "smooth_acacia_planks"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UPlanks), 5, new ModelResourceLocation(new ResourceLocation(modid, "smooth_dark_oak_planks"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UPlanks), 6, new ModelResourceLocation(new ResourceLocation(modid, "planks_red_urushi_stained"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UPlanks), 7, new ModelResourceLocation(new ResourceLocation(modid, "smooth_planks_red_urushi_stained"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UPlanks), 8, new ModelResourceLocation(new ResourceLocation(modid, "planks_black_urushi_stained"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UPlanks), 9, new ModelResourceLocation(new ResourceLocation(modid, "smooth_planks_black_urushi_stained"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UPlanks), 10, new ModelResourceLocation(new ResourceLocation(modid, "japanese_apricot_planks"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UPlanks), 11, new ModelResourceLocation(new ResourceLocation(modid, "smooth_japanese_apricot_planks"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UPlanks), 12, new ModelResourceLocation(new ResourceLocation(modid, "sakura_planks"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UPlanks), 13, new ModelResourceLocation(new ResourceLocation(modid, "smooth_sakura_planks"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UPlanks), 14, new ModelResourceLocation(new ResourceLocation(modid, "cypress_planks"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UPlanks), 15, new ModelResourceLocation(new ResourceLocation(modid, "smooth_cypress_planks"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ULog), 0, new ModelResourceLocation(new ResourceLocation(modid, "japanese_apricot_log"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ULog), 1, new ModelResourceLocation(new ResourceLocation(modid, "sakura_log"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ULog), 2, new ModelResourceLocation(new ResourceLocation(modid, "lacquer_log"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ULog), 3, new ModelResourceLocation(new ResourceLocation(modid, "cypress_log"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UStone), 0, new ModelResourceLocation(new ResourceLocation(modid, "cupric_oxide_kawara_block"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UStone), 1, new ModelResourceLocation(new ResourceLocation(modid, "ibushi_kawara_block"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UStone), 2, new ModelResourceLocation(new ResourceLocation(modid, "copper_ore"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UStone), 3, new ModelResourceLocation(new ResourceLocation(modid, "plaster"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UStone), 4, new ModelResourceLocation(new ResourceLocation(modid, "namako"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UStone), 5, new ModelResourceLocation(new ResourceLocation(modid, "copper_kawara_block"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(LycorisRadiata), 0, new ModelResourceLocation(new ResourceLocation(modid, "lycoris_radiata"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(TrapdoorRedUrushiStained), 0, new ModelResourceLocation(new ResourceLocation(modid, "trapdoor_red_urushi_stained"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UWoodenSlabASingle), 0, new ModelResourceLocation(new ResourceLocation(modid, "smooth_oak_slab"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UWoodenSlabASingle), 1, new ModelResourceLocation(new ResourceLocation(modid, "smooth_spruce_slab"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UWoodenSlabASingle), 2, new ModelResourceLocation(new ResourceLocation(modid, "smooth_birch_slab"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UWoodenSlabASingle), 3, new ModelResourceLocation(new ResourceLocation(modid, "smooth_jungle_slab"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UWoodenSlabASingle), 4, new ModelResourceLocation(new ResourceLocation(modid, "smooth_acacia_slab"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UWoodenSlabASingle), 5, new ModelResourceLocation(new ResourceLocation(modid, "smooth_dark_oak_slab"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UWoodenSlabASingle), 6, new ModelResourceLocation(new ResourceLocation(modid, "japanese_apricot_slab"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UWoodenSlabASingle), 7, new ModelResourceLocation(new ResourceLocation(modid, "smooth_japanese_apricot_slab"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UWoodenSlabBSingle), 0, new ModelResourceLocation(new ResourceLocation(modid, "slab_red_urushi_stained"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UWoodenSlabBSingle), 1, new ModelResourceLocation(new ResourceLocation(modid, "smooth_slab_red_urushi_stained"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UWoodenSlabBSingle), 2, new ModelResourceLocation(new ResourceLocation(modid, "slab_black_urushi_stained"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UWoodenSlabBSingle), 3, new ModelResourceLocation(new ResourceLocation(modid, "smooth_slab_black_urushi_stained"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UWoodenSlabBSingle), 4, new ModelResourceLocation(new ResourceLocation(modid, "sakura_slab"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UWoodenSlabBSingle), 5, new ModelResourceLocation(new ResourceLocation(modid, "smooth_sakura_slab"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UWoodenSlabBSingle), 6, new ModelResourceLocation(new ResourceLocation(modid, "cypress_slab"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UWoodenSlabBSingle), 7, new ModelResourceLocation(new ResourceLocation(modid, "smooth_cypress_slab"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(WoodenCabinetry), 0, new ModelResourceLocation(new ResourceLocation(modid, "wooden_cabinetry"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(WoodenCabinetryUnderSlab), 0, new ModelResourceLocation(new ResourceLocation(modid, "wooden_cabinetry_slab"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(JapaneseTimberBambooShoot), 0, new ModelResourceLocation(new ResourceLocation(modid, "bamboo_shoot"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(BambooWall), 0, new ModelResourceLocation(new ResourceLocation(modid, "bamboo_wall"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UStoneSlabSingle), 0, new ModelResourceLocation(new ResourceLocation(modid, "cupric_oxide_kawara_slab"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UStoneSlabSingle), 1, new ModelResourceLocation(new ResourceLocation(modid, "ibushi_kawara_slab"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UStoneSlabSingle), 2, new ModelResourceLocation(new ResourceLocation(modid, "plaster_slab"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UStoneSlabSingle), 3, new ModelResourceLocation(new ResourceLocation(modid, "copper_kawara_slab"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(WoodenBucket), 0, new ModelResourceLocation(new ResourceLocation(modid, "wooden_bucket"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(WoodenBucket), 1, new ModelResourceLocation(new ResourceLocation(modid, "wooden_bucket_raw1"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(WoodenBucket), 2, new ModelResourceLocation(new ResourceLocation(modid, "wooden_bucket_raw2"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(WoodenBucket), 3, new ModelResourceLocation(new ResourceLocation(modid, "wooden_bucket_raw3"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(WoodenBucket), 4, new ModelResourceLocation(new ResourceLocation(modid, "wooden_bucket_red1"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(WoodenBucket), 5, new ModelResourceLocation(new ResourceLocation(modid, "wooden_bucket_red2"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(WoodenBucket), 6, new ModelResourceLocation(new ResourceLocation(modid, "wooden_bucket_red3"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(WoodenBucket), 7, new ModelResourceLocation(new ResourceLocation(modid, "wooden_bucket_black1"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(WoodenBucket), 8, new ModelResourceLocation(new ResourceLocation(modid, "wooden_bucket_black2"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(WoodenBucket), 9, new ModelResourceLocation(new ResourceLocation(modid, "wooden_bucket_black3"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(USapling), 0, new ModelResourceLocation(new ResourceLocation(modid, "japanese_apricot_sapling"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(USapling), 1, new ModelResourceLocation(new ResourceLocation(modid, "sakura_sapling"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(USapling), 2, new ModelResourceLocation(new ResourceLocation(modid, "lacquer_sapling"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(USapling), 3, new ModelResourceLocation(new ResourceLocation(modid, "cypress_sapling"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ULeaves), 4, new ModelResourceLocation(new ResourceLocation(modid, "japanese_apricot_leaves"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ULeaves), 5, new ModelResourceLocation(new ResourceLocation(modid, "sakura_leaves"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ULeaves), 6, new ModelResourceLocation(new ResourceLocation(modid, "lacquer_leaves"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ULeaves), 7, new ModelResourceLocation(new ResourceLocation(modid, "cypress_leaves"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(SmoothOakStairs), 0, new ModelResourceLocation(new ResourceLocation(modid, "smooth_oak_stairs"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(SmoothSpruceStairs), 0, new ModelResourceLocation(new ResourceLocation(modid, "smooth_spruce_stairs"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(SmoothBirchStairs), 0, new ModelResourceLocation(new ResourceLocation(modid, "smooth_birch_stairs"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(SmoothJungleStairs), 0, new ModelResourceLocation(new ResourceLocation(modid, "smooth_jungle_stairs"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(SmoothAcaciaStairs), 0, new ModelResourceLocation(new ResourceLocation(modid, "smooth_acacia_stairs"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(SmoothDarkOakStairs), 0, new ModelResourceLocation(new ResourceLocation(modid, "smooth_dark_oak_stairs"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(SmoothJapaneseApricotStairs), 0, new ModelResourceLocation(new ResourceLocation(modid, "smooth_japanese_apricot_stairs"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(SmoothSakuraStairs), 0, new ModelResourceLocation(new ResourceLocation(modid, "smooth_sakura_stairs"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(SmoothStairsRedUrushiStained), 0, new ModelResourceLocation(new ResourceLocation(modid, "smooth_stairs_red_urushi_stained"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(SmoothStairsBlackUrushiStained), 0, new ModelResourceLocation(new ResourceLocation(modid, "smooth_stairs_black_urushi_stained"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(StairsRedUrushiStained), 0, new ModelResourceLocation(new ResourceLocation(modid, "stairs_red_urushi_stained"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(StairsBlackUrushiStained), 0, new ModelResourceLocation(new ResourceLocation(modid, "stairs_black_urushi_stained"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(JapaneseApricotStairs), 0, new ModelResourceLocation(new ResourceLocation(modid, "japanese_apricot_stairs"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(SakuraStairs), 0, new ModelResourceLocation(new ResourceLocation(modid, "sakura_stairs"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(CupricOxideKawaraStairs), 0, new ModelResourceLocation(new ResourceLocation(modid, "cupric_oxide_kawara_stairs"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(IbushiKawaraStairs), 0, new ModelResourceLocation(new ResourceLocation(modid, "ibushi_kawara_stairs"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(PlasterStairs), 0, new ModelResourceLocation(new ResourceLocation(modid, "plaster_stairs"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(Tatami), 0, new ModelResourceLocation(new ResourceLocation(modid, "brown_tatami"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(Tatami), 1, new ModelResourceLocation(new ResourceLocation(modid, "green_tatami"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(Tawara), 0, new ModelResourceLocation(new ResourceLocation(modid, "tawara"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(RiceBall, 0, new ModelResourceLocation(new ResourceLocation(modid, "rice_ball"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Mochi, 0, new ModelResourceLocation(new ResourceLocation(modid, "rice_cake"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(KusaDango, 0, new ModelResourceLocation(new ResourceLocation(modid, "kusa_dango"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(ColorDango, 0, new ModelResourceLocation(new ResourceLocation(modid, "color_dango"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(MitarashiDango, 0, new ModelResourceLocation(new ResourceLocation(modid, "mitarashi_dango"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(YakiMochi, 0, new ModelResourceLocation(new ResourceLocation(modid, "roasted_rice_cake"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(DirtFurnace), 0, new ModelResourceLocation(new ResourceLocation(modid, "dirt_furnace"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(RiceCauldron), 1, new ModelResourceLocation(new ResourceLocation(modid, "empty_rice_cauldron"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(RiceCauldron), 2, new ModelResourceLocation(new ResourceLocation(modid, "closed_rice_cauldron"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(RiceCauldron), 3, new ModelResourceLocation(new ResourceLocation(modid, "closed_rice_cauldron"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(RiceCauldron), 4, new ModelResourceLocation(new ResourceLocation(modid, "rice_rice_cauldron"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(RiceCauldron), 5, new ModelResourceLocation(new ResourceLocation(modid, "closed_rice_cauldron"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(RiceCauldron), 6, new ModelResourceLocation(new ResourceLocation(modid, "closed_rice_cauldron"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(RiceCauldron), 7, new ModelResourceLocation(new ResourceLocation(modid, "rice_rice_cauldron"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(RiceCauldron), 8, new ModelResourceLocation(new ResourceLocation(modid, "closed_rice_cauldron"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(RiceCauldron), 9, new ModelResourceLocation(new ResourceLocation(modid, "closed_rice_cauldron"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(RiceCauldron), 10, new ModelResourceLocation(new ResourceLocation(modid, "rice_rice_cauldron"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(RiceCauldron), 11, new ModelResourceLocation(new ResourceLocation(modid, "closed_rice_cauldron"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(RiceCauldron), 12, new ModelResourceLocation(new ResourceLocation(modid, "closed_rice_cauldron"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(RiceCauldron), 13, new ModelResourceLocation(new ResourceLocation(modid, "rice_rice_cauldron"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(RiceCauldron), 0, new ModelResourceLocation(new ResourceLocation(modid, "closed_rice_cauldron"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(WattleAndDaub), 0, new ModelResourceLocation(new ResourceLocation(modid, "wattle_and_daub"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ShoujiPaneOak), 0, new ModelResourceLocation(new ResourceLocation(modid, "shouji_pane_oak"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ShoujiPaneSpruce), 0, new ModelResourceLocation(new ResourceLocation(modid, "shouji_pane_spruce"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ShoujiPaneRed), 0, new ModelResourceLocation(new ResourceLocation(modid, "shouji_pane_red"), "inventory"));

        ModelLoader.setCustomModelResourceLocation(QuartzMagatama, 0, new ModelResourceLocation(new ResourceLocation(modid, "quartz_magatama"), "inventory"));
      ModelLoader.setCustomModelResourceLocation(UItems, 14, new ModelResourceLocation(new ResourceLocation(modid, "cushion_white"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 15, new ModelResourceLocation(new ResourceLocation(modid, "cushion_orange"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 16, new ModelResourceLocation(new ResourceLocation(modid, "cushion_magenta"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 17, new ModelResourceLocation(new ResourceLocation(modid, "cushion_light_blue"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 18, new ModelResourceLocation(new ResourceLocation(modid, "cushion_yellow"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 19, new ModelResourceLocation(new ResourceLocation(modid, "cushion_lime"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 20, new ModelResourceLocation(new ResourceLocation(modid, "cushion_pink"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 21, new ModelResourceLocation(new ResourceLocation(modid, "cushion_gray"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 22, new ModelResourceLocation(new ResourceLocation(modid, "cushion_silver"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 23, new ModelResourceLocation(new ResourceLocation(modid, "cushion_cyan"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 24, new ModelResourceLocation(new ResourceLocation(modid, "cushion_purple"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 25, new ModelResourceLocation(new ResourceLocation(modid, "cushion_blue"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 26, new ModelResourceLocation(new ResourceLocation(modid, "cushion_brown"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 27, new ModelResourceLocation(new ResourceLocation(modid, "cushion_green"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 28, new ModelResourceLocation(new ResourceLocation(modid, "cushion_red"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 29, new ModelResourceLocation(new ResourceLocation(modid, "cushion_black"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 30, new ModelResourceLocation(new ResourceLocation(modid, "blank_fusuma"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 7, new ModelResourceLocation(new ResourceLocation(modid, "sliding_glass_door"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(Bars), 0, new ModelResourceLocation(new ResourceLocation(modid, "oak_bars"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(Bars), 1, new ModelResourceLocation(new ResourceLocation(modid, "spruce_bars"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(Bars), 2, new ModelResourceLocation(new ResourceLocation(modid, "birch_bars"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(Bars), 3, new ModelResourceLocation(new ResourceLocation(modid, "jungle_bars"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(Bars), 4, new ModelResourceLocation(new ResourceLocation(modid, "acacia_bars"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(Bars), 5, new ModelResourceLocation(new ResourceLocation(modid, "dark_oak_bars"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(Bars), 6, new ModelResourceLocation(new ResourceLocation(modid, "green_bars"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(FramedPlaster), 0, new ModelResourceLocation(new ResourceLocation(modid, "red_framed_plaster"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(FramedPlaster), 1, new ModelResourceLocation(new ResourceLocation(modid, "oak_framed_plaster"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(FramedPlaster), 2, new ModelResourceLocation(new ResourceLocation(modid, "spruce_framed_plaster"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(FramedPlaster), 3, new ModelResourceLocation(new ResourceLocation(modid, "birch_framed_plaster"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(FramedPlaster), 4, new ModelResourceLocation(new ResourceLocation(modid, "jungle_framed_plaster"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(FramedPlaster), 5, new ModelResourceLocation(new ResourceLocation(modid, "acacia_framed_plaster"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(FramedPlaster), 6, new ModelResourceLocation(new ResourceLocation(modid, "dark_oak_framed_plaster"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(CopperKawaraStairs), 0, new ModelResourceLocation(new ResourceLocation(modid, "copper_kawara_stairs"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(KawaraSlabASingle), 0, new ModelResourceLocation(new ResourceLocation(modid, "ibushi_kawara_slab"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(KawaraSlabASingle), 4, new ModelResourceLocation(new ResourceLocation(modid, "cupric_oxide_kawara_slab"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(KawaraSlabBSingle), 0, new ModelResourceLocation(new ResourceLocation(modid, "copper_kawara_slab"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(SandCoast), 0, new ModelResourceLocation(new ResourceLocation(modid, "sand_coast"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(SandCoast), 1, new ModelResourceLocation(new ResourceLocation(modid, "red_sand_coast"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(SandCoast), 2, new ModelResourceLocation(new ResourceLocation(modid, "lime_sand_coast"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ThatchedBlock), 0, new ModelResourceLocation(new ResourceLocation(modid, "thatched_block"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ThatchedBlock), 1, new ModelResourceLocation(new ResourceLocation(modid, "hiwadabuki_block"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ThatchedSlabSingle), 0, new ModelResourceLocation(new ResourceLocation(modid, "thatched_slab"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ThatchedSlabSingle), 1, new ModelResourceLocation(new ResourceLocation(modid, "hiwadabuki_slab"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ThatchedStairs), 0, new ModelResourceLocation(new ResourceLocation(modid, "thatched_stairs"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(HiwadabukiStairs), 0, new ModelResourceLocation(new ResourceLocation(modid, "hiwadabuki_stairs"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 31, new ModelResourceLocation(new ResourceLocation(modid, "andon"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 32, new ModelResourceLocation(new ResourceLocation(modid, "ariake_andon"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 33, new ModelResourceLocation(new ResourceLocation(modid, "futon_item"), "inventory"));
      //  ModelLoader.setCustomModelResourceLocation(HayatoShield, 33, new ModelResourceLocation(new ResourceLocation(modid, "hayato_sheild"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(OakShitomi), 0, new ModelResourceLocation(new ResourceLocation(modid, "oak_shitomi"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(SpruceShitomi), 0, new ModelResourceLocation(new ResourceLocation(modid, "spruce_shitomi"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(BirchShitomi), 0, new ModelResourceLocation(new ResourceLocation(modid, "birch_shitomi"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(JungleShitomi), 0, new ModelResourceLocation(new ResourceLocation(modid, "jungle_shitomi"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(AcaciaShitomi), 0, new ModelResourceLocation(new ResourceLocation(modid, "acacia_shitomi"), "inventory"));
         ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(DarkOakShitomi), 0, new ModelResourceLocation(new ResourceLocation(modid, "dark_oak_shitomi"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(RedShitomi), 0, new ModelResourceLocation(new ResourceLocation(modid, "red_shitomi"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(BlackShitomi), 0, new ModelResourceLocation(new ResourceLocation(modid, "black_shitomi"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(JapaneseApricotShitomi), 0, new ModelResourceLocation(new ResourceLocation(modid, "japanese_apricot_shitomi"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(SakuraShitomi), 0, new ModelResourceLocation(new ResourceLocation(modid, "sakura_shitomi"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Rice, 0, new ModelResourceLocation(new ResourceLocation(modid, "rice"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 6, new ModelResourceLocation(new ResourceLocation(modid, "red_urushi_door"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(CypressStairs), 0, new ModelResourceLocation(new ResourceLocation(modid, "cypress_stairs"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(SmoothCypressStairs), 0, new ModelResourceLocation(new ResourceLocation(modid, "smooth_cypress_stairs"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems,34, new ModelResourceLocation(new ResourceLocation(modid, "rice_malt"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(ImmatureApricot,0, new ModelResourceLocation(new ResourceLocation(modid, "immature_apricot"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(MaturedApricot,0, new ModelResourceLocation(new ResourceLocation(modid, "matured_apricot"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(PickledApricot,0, new ModelResourceLocation(new ResourceLocation(modid, "pickled_apricot"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(FermentationPot), 0, new ModelResourceLocation(new ResourceLocation(modid, "empty_fermentation_pot"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems,35, new ModelResourceLocation(new ResourceLocation(modid, "raw_fermentation_pot"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Azuki,0, new ModelResourceLocation(new ResourceLocation(modid, "azuki"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Ohagi,0, new ModelResourceLocation(new ResourceLocation(modid, "ohagi"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(SakuraMochi,0, new ModelResourceLocation(new ResourceLocation(modid, "sakura_mochi"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Gyudon,0, new ModelResourceLocation(new ResourceLocation(modid, "gyudon"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Butadon,0, new ModelResourceLocation(new ResourceLocation(modid, "butadon"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(SalmonSashimi,0, new ModelResourceLocation(new ResourceLocation(modid, "salmon_sashimi"), "inventory"));

          ModelLoader.setCustomModelResourceLocation(Charm, 0, new ModelResourceLocation(new ResourceLocation(modid, "water_charm"), "inventory"));
          ModelLoader.setCustomModelResourceLocation(Charm, 1, new ModelResourceLocation(new ResourceLocation(modid, "fasting_charm"), "inventory"));
          ModelLoader.setCustomModelResourceLocation(Charm, 2, new ModelResourceLocation(new ResourceLocation(modid, "protection_charm"), "inventory"));
          ModelLoader.setCustomModelResourceLocation(Charm, 3, new ModelResourceLocation(new ResourceLocation(modid, "cleanse_charm"), "inventory"));
          ModelLoader.setCustomModelResourceLocation(Charm, 4, new ModelResourceLocation(new ResourceLocation(modid, "health_boost_charm"), "inventory"));
          ModelLoader.setCustomModelResourceLocation(Charm, 5, new ModelResourceLocation(new ResourceLocation(modid, "mayoke_charm"), "inventory"));
          ModelLoader.setCustomModelResourceLocation(UItems,36, new ModelResourceLocation(new ResourceLocation(modid, "blank_charm"), "inventory"));
          ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ULeaves2), 6, new ModelResourceLocation(new ResourceLocation(modid, "lacquer_leaves"), "inventory"));
          ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ULeaves2), 7, new ModelResourceLocation(new ResourceLocation(modid, "cypress_leaves"), "inventory"));
          ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ChiseledLacquerLog), 0, new ModelResourceLocation(new ResourceLocation(modid, "chiseled_lacquer_log"), "inventory"));





    }


    @EventHandler
    public void init(FMLInitializationEvent event) {
/**鉱石辞書に登録 */
        OreDictionary.registerOre("plankWood",new ItemStack(UPlanks,1,6));
        OreDictionary.registerOre("plankWood",new ItemStack(UPlanks,1,8));
        OreDictionary.registerOre("plankWood",new ItemStack(UPlanks,1,10));
        OreDictionary.registerOre("plankWood",new ItemStack(UPlanks,1,12));
        OreDictionary.registerOre("plankWood",new ItemStack(UPlanks,1,14));
        OreDictionary.registerOre("slabWood",new ItemStack(UWoodenSlabASingle,1,6));
        OreDictionary.registerOre("slabWood",new ItemStack(UWoodenSlabBSingle,1,0));
        OreDictionary.registerOre("slabWood",new ItemStack(UWoodenSlabBSingle,1,2));
        OreDictionary.registerOre("slabWood",new ItemStack(UWoodenSlabBSingle,1,4));
        OreDictionary.registerOre("slabWood",new ItemStack(UWoodenSlabBSingle,1,6));
        OreDictionary.registerOre("ingotCopper",new ItemStack(UItems,1,9));
        OreDictionary.registerOre("oreCopper",new ItemStack(UStone,1,2));
        OreDictionary.registerOre("logWood",new ItemStack(ULog,1,0));
        OreDictionary.registerOre("logWood",new ItemStack(ULog,1,1));
        OreDictionary.registerOre("logWood",new ItemStack(ULog,1,2));
        OreDictionary.registerOre("logWood",new ItemStack(ULog,1,3));
        OreDictionary.registerOre("treeSapling",new ItemStack(USapling,1,0));
        OreDictionary.registerOre("treeSapling",new ItemStack(USapling,1,1));
        OreDictionary.registerOre("treeSapling",new ItemStack(USapling,1,2));
        OreDictionary.registerOre("treeSapling",new ItemStack(USapling,1,3));
        OreDictionary.registerOre("treeLeaves",new ItemStack(ULeaves,1,4));
        OreDictionary.registerOre("treeLeaves",new ItemStack(ULeaves,1,5));
        OreDictionary.registerOre("treeLeaves",new ItemStack(ULeaves,1,6));
        OreDictionary.registerOre("treeLeaves",new ItemStack(ULeaves,1,7));
        OreDictionary.registerOre("slimeball",new ItemStack(UItems,1,8));
        OreDictionary.registerOre("sakuraLeaves",new ItemStack(ULeaves,1,5));
        OreDictionary.registerOre("seedRice",new ItemStack(RiceEars,1,0));
        OreDictionary.registerOre("bamboo",new ItemStack(UItems,1,0));
        OreDictionary.registerOre("stickBamboo",new ItemStack(UItems,1,0));
        OreDictionary.registerOre("cropBamboo",new ItemStack(UItems,1,0));
        OreDictionary.registerOre("cropBambooshoot",new ItemStack(JapaneseTimberBambooShoot,1,0));
        OreDictionary.registerOre("foodBamboo",new ItemStack(JapaneseTimberBambooShoot,1,0));
        OreDictionary.registerOre("cookingRice",new ItemStack(Rice,1,0));
        OreDictionary.registerOre("foodBowlofrice",new ItemStack(Rice,1,0));
        OreDictionary.registerOre("rice",new ItemStack(Rice,1,0));
        OreDictionary.registerOre("riceSeed",new ItemStack(RiceEars,1,0));
        OreDictionary.registerOre("RiceBall",new ItemStack(RiceBall,1,0));
        OreDictionary.registerOre("cropRice",new ItemStack(Rice,1,0));
        OreDictionary.registerOre("foodCookedRice",new ItemStack(Rice,1,0));
        OreDictionary.registerOre("foodRice",new ItemStack(Rice,1,0));
        OreDictionary.registerOre("foodRiceCake",new ItemStack(Mochi,1,0));
        OreDictionary.registerOre("foodMochi",new ItemStack(Mochi,1,0));
        OreDictionary.registerOre("foodMochiCake",new ItemStack(Mochi,1,0));
        OreDictionary.registerOre("azuki",new ItemStack(Azuki,1,0));
        OreDictionary.registerOre("cropAzuki",new ItemStack(Azuki,1,0));
        OreDictionary.registerOre("cropBean",new ItemStack(Azuki,1,0));
        OreDictionary.registerOre("cropRedbean",new ItemStack(Azuki,1,0));

       // Kakuriyo_DIMENSION = DimensionType.register("KakuriyoDimension", "_kakuriyo", 2, WorldProviderKakuriyo.class, false);
      //  DimensionManager.registerDimension(2, Kakuriyo_DIMENSION);








        EntityRegistry.registerModEntity(new ResourceLocation("entity_cushion"), EntityCushion.class, "EntityCushion", 0, this, 50, 1, true);

    }
    @Mod.EventHandler
    public void preInt(FMLPreInitializationEvent event)  {
        Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());

      //  Kakuriyo_DIMENSION = DimensionType.register("Kakuriyo Dimension", "_kakuriyo", DimensionManager.getNextFreeDimId(), WorldProviderKakuriyo.class, false);
        // DimensionManager.registerDimension(Kakuriyo_DIMENSION.getId(), Kakuriyo_DIMENSION);


        try {
            cfg.load();
           Property e1ID1 = cfg.get("world generation", "ganerate Sakura trees in Forest Biome" ,wheather_ganerate_Sakura);
            Property e1ID2 = cfg.get("world generation", "ganerate Japanese Apricot trees in Forest Biome",wheather_ganerate_Ume);
            Property e1ID3 = cfg.get("world generation", "ganerate Lacquer trees in Forest Biome" ,wheather_ganerate_Urushi);
            Property e1ID7 = cfg.get("world generation", "ganerate Cypress trees in ExtremeHills Biome" ,wheather_ganerate_Cypress);
            Property e1ID4 = cfg.get("world generation", "generate Japanese Timber Bamboo in ForestHills Biome" ,wheather_ganerate_Bamboo);
            Property e1ID5 = cfg.get("world generation", "generate Copper Ore" ,wheather_ganerate_CopperOre);
            Property e1ID6 = cfg.get("block settings", "max height of Japanese Timber Bamboo" ,max_height_Bamboo);
            Property e1ID8 = cfg.get("block settings", "Japanese Apricot Leaves and Sakura Leaves glow" ,SakuraLeavesAndJapaneseApricotLeavesIsLight);

            // 項目に入っている値を取得してIDに入れる。
            wheather_ganerate_Sakura = e1ID1.getBoolean();
            wheather_ganerate_Ume = e1ID2.getBoolean();
            wheather_ganerate_Urushi = e1ID3.getBoolean();
            wheather_ganerate_Bamboo = e1ID4.getBoolean();
            wheather_ganerate_CopperOre = e1ID5.getBoolean();
            max_height_Bamboo=e1ID6.getInt();
            wheather_ganerate_Cypress = e1ID7.getBoolean();
            SakuraLeavesAndJapaneseApricotLeavesIsLight = e1ID8.getBoolean();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cfg.save();
        }


       if(event.getSide().isClient()) {

           GameRegistry.registerTileEntity(TileEntityRiceCauldron.getClass(),new ResourceLocation(modid,"rice_cauldron"));
           GameRegistry.registerTileEntity(TileEntityWoodenCabinetry.getClass(),new ResourceLocation(modid,"wooden_cabinetry"));
            GameRegistry.registerTileEntity(TileEntityWoodenCabinetryUnderSlab.getClass(),new ResourceLocation(modid,"wooden_cabinetry_under_slab"));
           GameRegistry.registerTileEntity(TileEntityRiceHokora.getClass(),new ResourceLocation(modid,"rice_hokora"));
           GameRegistry.registerWorldGenerator(new OreGen(), 3);
           GameRegistry.registerTileEntity(TileEntityFuton.getClass(),new ResourceLocation(modid,"futon"));
           GameRegistry.registerTileEntity(TileEntityFermentationPot.getClass(),new ResourceLocation(modid,"fermentation_pot"));

           RenderingRegistry.registerEntityRenderingHandler(EntityCushion.class, new IRenderFactory<EntityCushion>(){
               @Override
               public Render<? super EntityCushion> createRenderFor(RenderManager manager){
                   return new RenderCushion(manager);
               }
           });
       }
    }
    @Mod.EventHandler
    public void postInt(FMLPostInitializationEvent event) {
        if (event.getSide().isClient()) {


            GameRegistry.addSmelting(new ItemStack(UItems, 1, 10), new ItemStack(UItems, 1, 3), 5F);
            GameRegistry.addSmelting(new ItemStack(UStone, 1, 2), new ItemStack(UItems, 1, 9), 5F);
            GameRegistry.addSmelting(new ItemStack(UItems, 1, 5), new ItemStack(Rice, 1, 0), 5F);
            GameRegistry.addSmelting(new ItemStack(Mochi, 1, 0), new ItemStack(YakiMochi, 1, 0), 5F);
            GameRegistry.addSmelting(new ItemStack(UItems, 1, 35), new ItemStack(FermentationPot, 1, 0), 5F);

        }
    }

    @SubscribeEvent
    public void EntityDropEvent(LivingDeathEvent event) {
      //  event.getEntityLiving().drop
    }
    }

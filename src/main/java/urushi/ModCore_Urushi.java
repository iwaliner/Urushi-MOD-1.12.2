package urushi;


import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.*;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import urushi.Block.*;
import urushi.Else.EnumType;
import urushi.Entity.EntityOni;
import urushi.Entity.EntityOniGirl;
import urushi.Entity.EntityOnibi;
import urushi.Render.RenderOni;
import urushi.Render.RenderOniGirl;
import urushi.WorldGen.BiomeSakuraForest;
import urushi.WorldGen.OreGen;
import urushi.Else.TabUrushi;
import urushi.Entity.EntityCushion;
import urushi.Item.*;
import urushi.Itemblock.ItemBlockMetadata;
import urushi.Render.RenderCushion;
import urushi.TileEntity.*;
import urushi.WorldGen.WorldProviderKakuriyo;


@Mod(modid = "urushi", version = "alpha2.33", name = "Urushi MOD")
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
    //public static final BlockFlower LycorisRadiata = (BlockFlower) new BlockYellowFlower().setCreativeTab(TabUrushi);
    public static final BlockFlower LycorisRadiata = (BlockFlower) new KakuriyoFlower();
    public static final ItemBlock ItemBlockLycorisRadiata=new ItemBlockMetadata(LycorisRadiata);
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
    public static final Block USapling2 = new USapling2();
    public static final ItemBlock ItemBlockUSapling=new ItemBlockMetadata(USapling);
    public static final ItemBlock ItemBlockUSapling2=new ItemBlockMetadata(USapling2);
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
    public static final Block OakShouji = new SlideDoorBase(11,BlockRenderLayer.TRANSLUCENT);
    public static final Block SpruceShouji = new SlideDoorBase(12,BlockRenderLayer.TRANSLUCENT);
    public static final Block RedShouji = new SlideDoorBase(13,BlockRenderLayer.TRANSLUCENT);
    public static final Block ShoujiPaneOak = new PaneWall(Material.WOOD);
    public static final Block ShoujiPaneSpruce = new PaneWall(Material.WOOD);
    public static final Block ShoujiPaneRed = new PaneWall(Material.WOOD);
    public static final Block RiceHokora = new RiceHokora();
    public static final TileEntity TileEntityRiceHokora = new TileEntityRiceHokora();
    public static final Item QuartzMagatama = new QuartzMagatama();
    public static final Block BlankFusuma = new SlideDoorBase(30,BlockRenderLayer.SOLID);
    public static final Block SlidingGlassDoor = new SlideDoorBase(7,BlockRenderLayer.CUTOUT);
    public static final Block Bars = new Bars();
    public static final ItemBlock ItemBlockBars=new ItemBlockMetadata(Bars);
    public static final Block FramedPlaster = new FramedBlock(8,"pickaxe",Material.WOOD);
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
    public static final Block Futon = new Futon(33);
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
    public static final ItemBlock ItemBlockULeaves2=new ItemBlockMetadata(ULeaves2);
    public static final Item RawTsuna = new ItemFood(4, 0.4F, false);
    public static int KakuriyoDimensionID=36;
    public static final Biome SakuraBiome=new BiomeSakuraForest();
    public static final Block UStrippedLog = new U_StrippedLog();
    public static final ItemBlock ItemBlockUStrippedLog=new ItemBlockMetadata(UStrippedLog);
    public static final Block WhiteFuton = new Futon(38);
    public static final Block OrangeFuton = new Futon(39);
    public static final Block MagentaFuton = new Futon(40);
    public static final Block LightBlueFuton = new Futon(41);
    public static final Block YellowFuton = new Futon(42);
    public static final Block LimeFuton = new Futon(43);
    public static final Block PinkFuton = new Futon(44);
    public static final Block GrayFuton = new Futon(45);
    public static final Block LightGrayFuton = new Futon(46);
    public static final Block CyanFuton = new Futon(47);
    public static final Block PurpleFuton = new Futon(48);
    public static final Block BlueFuton = new Futon(49);
    public static final Block BrownFuton = new Futon(50);
    public static final Block GreenFuton = new Futon(51);
    public static final Block BlackFuton = new Futon(52);
    public static final Block KakejikuA = new Kakejiku();
    public static final Block KakejikuB = new Kakejiku();
    public static final Block KakejikuC = new Kakejiku();
    public static final Block KakejikuD = new Kakejiku();
    public static final Block FishBowl = new FishBowl();
    public static final Block BlankTenbukuroFusuma = new SlideMonoDoorBase(BlockRenderLayer.SOLID);
    public static final Item KakejikuItem = new KakejikuItem();
    public static final Block BlueSeigaihaFusuma = new SlideDoorBase(53,BlockRenderLayer.SOLID);
    public static final Block BlueSayagataFusuma = new SlideDoorBase(54,BlockRenderLayer.SOLID);
    public static final Item Wagasa = new Wagasa();
    public static int EntityCushionID=0;
    public static int EntityRedOniID=180;
    public static int EntityOniGirlID=181;
    public static int EntityOnibiID=182;
   // public static ResourceLocation ONI_GIRL = new ResourceLocation(modid, "oni_girl");
    public static ResourceLocation RED_ONI = new ResourceLocation("urushi:red_oni");
    public static final Item.ToolMaterial MetalClubMaterial= EnumHelper.addToolMaterial("metal_club", 0, 81, 0F, 4F, 2);
    public static final Item MetalClub = new MetalClub(MetalClubMaterial);
    public static final Block Onibi = new Onibi();
    public static final Item ItemOnibi=new ItemOnibi();
    public static int range_of_onibi=24;
    public static final Block RedUrushiFence = new Fence( MapColor.RED);
    public static final Block BlackUrushiFence = new Fence( MapColor.BLACK);
    public static final Block JapaneseApricotFence = new Fence( MapColor.ADOBE);
    public static final Block SakuraFence = new Fence( MapColor.WOOD);
    public static final Block CypressFence = new Fence( MapColor.SAND);
    public static final Block RedUrushiFenceGate = new FenceGate();
    public static final Block BlackUrushiFenceGate = new FenceGate();
    public static final Block JapaneseApricotFenceGate = new FenceGate();
    public static final Block SakuraFenceGate = new FenceGate();
    public static final Block CypressFenceGate = new FenceGate();
    public static final Block MirrorGate = new MirrorGate();
    public static final Item TenguFan = new TenguFan();
    public static final Block FramedPlaster2 = new FramedBlock(3,"pickaxe",Material.WOOD);
    public static final ItemBlock ItemBlockFramedPlaster2=new ItemBlockMetadata(FramedPlaster2);
    public static final Block Parapet = new Parapet(8);
    public static final ItemBlock ItemBlockParapet=new ItemBlockMetadata(Parapet);
    public static final Block Parapet2 = new Parapet(3);
    public static final ItemBlock ItemBlockParapet2=new ItemBlockMetadata(Parapet2);
    public static final Block FramedWattleAndDaub = new FramedBlock(6,"shovel",Material.WOOD);
    public static final ItemBlock ItemBlockFramedWattleAndDaub=new ItemBlockMetadata(FramedWattleAndDaub);
    public static final Block Giboshi = new Giboshi();
    public static final ItemBlock ItemBlockGiboshi=new ItemBlockMetadata(Giboshi);
    public static final Block BambooBlock = new BambooBlock();
    public static final ItemBlock ItemBlockBambooBlock=new ItemBlockMetadata(BambooBlock);

    public static final BlockSlab BambooSlabSingle = new BambooSlab() {@Override public boolean isDouble() {return false;}};
    public static final BlockSlab BambooSlabDouble = new BambooSlab() {@Override public boolean isDouble() {return true;}};
    public static final ItemBlock ItemBlockBambooSlab=new ItemSlab(BambooSlabSingle, BambooSlabSingle, BambooSlabDouble);
    public static final Block BambooStairs = new Stairs(BambooBlock.getDefaultState());
    public static final Block RawUrushiLayer = new RawUrushiLayer();
    public static boolean wheather_ganerate_Gate=false;
    public static final BlockSlab WattleAndDaubSlabSingle = new WattleAndDaubSlab() {@Override public boolean isDouble() {return false;}};
    public static final BlockSlab WattleAndDaubSlabDouble = new WattleAndDaubSlab() {@Override public boolean isDouble() {return true;}};
    public static final ItemBlock ItemBlockWattleAndDaubSlab=new ItemSlab(WattleAndDaubSlabSingle, WattleAndDaubSlabSingle, WattleAndDaubSlabDouble);
    public static final Block WattleAndDaubStairs = new Stairs(WattleAndDaub.getDefaultState());
    public static final Block RoughStoneStairs = new Stairs(UStone.getDefaultState().withProperty(U_Stone.VARIANT, U_Stone.EnumType.RoughStone));
    public static final Block UStone2 = new U_Stone2();
    public static final Block ConcreteStairs = new Stairs(UStone2.getDefaultState().withProperty(U_Stone2.VARIANT, U_Stone2.EnumType.Concrete));
    public static final ItemBlock ItemBlockUStone2=new ItemBlockMetadata(UStone2);
    public static final Block RoughStoneWall = new StoneWall(UStone);
    public static final ItemBlock ItemBlockRoughStoneWall=new ItemBlockMetadata(RoughStoneWall);
    public static final Block FramedGlass = new FramedGlass(2);
    public static final ItemBlock ItemBlockFramedGlass=new ItemBlockMetadata(FramedGlass);
    public static final Block FramedGlassPane = new FramedGlassPane(2);
    public static final ItemBlock ItemBlockFramedGlassPane=new ItemBlockMetadata(FramedGlassPane);
    public static final Block TatamiCarpet = new TatamiCarpet(2);
    public static final ItemBlock ItemBlockTatamiCarpet=new ItemBlockMetadata(TatamiCarpet);
    public static boolean wheather_player_speed_up=true;
    //public static final Block IbushiKawaraRoof45 = new Roof45(Material.ROCK).setHardness(1.0F).setResistance(10F);

    //public static final BlockSlab KawaraRoof225Single = new Roof225(Material.ROCK) {@Override public boolean isDouble() {return false;}};
    //public static final BlockSlab KawaraRoof225Double = new Roof225(Material.ROCK) {@Override public boolean isDouble() {return true;}};
    //public static final ItemBlock ItemBlockKawaraRoof225=new ItemSlab(KawaraRoof225Single, KawaraRoof225Single, KawaraRoof225Double);

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
        event.getRegistry().register(ItemBlockLycorisRadiata.setRegistryName(modid, "lycoris_radiata"));
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
        event.getRegistry().register(ItemBlockUSapling2.setRegistryName(modid, "u_sapling2"));
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
        event.getRegistry().register(RawTsuna.setUnlocalizedName("RawTsuna").setRegistryName("raw_tsuna").setCreativeTab(TabUrushi));
        event.getRegistry().register(ItemBlockUStrippedLog.setRegistryName(modid, "u_stripped_log"));
        event.getRegistry().register(new ItemBlock(KakejikuA).setRegistryName(modid, "kakejiku_a"));
        event.getRegistry().register(new ItemBlock(FishBowl).setRegistryName(modid, "golden_fish_bowl"));
        event.getRegistry().register(new ItemBlock(BlankTenbukuroFusuma).setRegistryName(modid, "blank_tenbukuro_fusuma"));
        event.getRegistry().register(KakejikuItem);
        event.getRegistry().register(Wagasa);
        event.getRegistry().register(MetalClub);
        event.getRegistry().register(ItemOnibi);
        event.getRegistry().register(new ItemBlock(RedUrushiFence).setRegistryName(modid, "red_urushi_fence"));
        event.getRegistry().register(new ItemBlock(BlackUrushiFence).setRegistryName(modid, "black_urushi_fence"));
        event.getRegistry().register(new ItemBlock(JapaneseApricotFence).setRegistryName(modid, "japanese_apricot_fence"));
        event.getRegistry().register(new ItemBlock(SakuraFence).setRegistryName(modid, "sakura_fence"));
        event.getRegistry().register(new ItemBlock(CypressFence).setRegistryName(modid, "cypress_fence"));
        event.getRegistry().register(new ItemBlock(RedUrushiFenceGate).setRegistryName(modid, "red_urushi_fence_gate"));
        event.getRegistry().register(new ItemBlock(BlackUrushiFenceGate).setRegistryName(modid, "black_urushi_fence_gate"));
        event.getRegistry().register(new ItemBlock(JapaneseApricotFenceGate).setRegistryName(modid, "japanese_apricot_fence_gate"));
        event.getRegistry().register(new ItemBlock(SakuraFenceGate).setRegistryName(modid, "sakura_fence_gate"));
        event.getRegistry().register(new ItemBlock(CypressFenceGate).setRegistryName(modid, "cypress_fence_gate"));
        event.getRegistry().register(new ItemBlock(MirrorGate).setRegistryName(modid, "mirror_gate"));
        event.getRegistry().register(TenguFan);
        event.getRegistry().register(ItemBlockFramedPlaster2.setRegistryName(modid, "framed_plaster2"));
        event.getRegistry().register(ItemBlockParapet.setRegistryName(modid, "parapet"));
        event.getRegistry().register(ItemBlockParapet2.setRegistryName(modid, "parapet2"));
        event.getRegistry().register(ItemBlockFramedWattleAndDaub.setRegistryName(modid, "framed_wattle_and_daub"));
        event.getRegistry().register(ItemBlockGiboshi.setRegistryName(modid, "giboshi"));
        event.getRegistry().register(ItemBlockBambooBlock.setRegistryName(modid, "bamboo_block"));
        event.getRegistry().register(ItemBlockBambooSlab.setRegistryName(modid, "bamboo_slab_single"));
        event.getRegistry().register(new ItemBlock(BambooStairs).setRegistryName(modid, "bamboo_stairs"));
        event.getRegistry().register(new ItemBlock(RawUrushiLayer).setRegistryName(modid, "raw_urushi_layer"));
        event.getRegistry().register(ItemBlockWattleAndDaubSlab.setRegistryName(modid, "wattle_and_daub_slab_single"));
        event.getRegistry().register(new ItemBlock(WattleAndDaubStairs).setRegistryName(modid, "wattle_and_daub_stairs"));
        event.getRegistry().register(new ItemBlock(RoughStoneStairs).setRegistryName(modid, "rough_stone_stairs"));
        event.getRegistry().register(ItemBlockUStone2.setRegistryName(modid, "u_stone2"));
        event.getRegistry().register(new ItemBlock(ConcreteStairs).setRegistryName(modid, "concrete_stairs"));
        event.getRegistry().register(ItemBlockRoughStoneWall.setRegistryName(modid, "rough_stone_wall"));
        event.getRegistry().register(ItemBlockFramedGlass.setRegistryName(modid, "framed_glass"));
        event.getRegistry().register(ItemBlockFramedGlassPane.setRegistryName(modid, "framed_glass_pane"));
        event.getRegistry().register(ItemBlockTatamiCarpet.setRegistryName(modid, "tatami_carpet"));
        //event.getRegistry().register(new ItemBlock(IbushiKawaraRoof45).setRegistryName(modid, "ibushi_kawara_roof_45"));
        //event.getRegistry().register(ItemBlockKawaraRoof225.setRegistryName(modid, "kawara_roof_225"));




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
        event.getRegistry().register(USapling2.setRegistryName(modid,"u_sapling2").setUnlocalizedName("USapling2"));
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
        event.getRegistry().register(UStrippedLog.setRegistryName(modid,"u_stripped_log").setUnlocalizedName("UStrippedLog"));
        event.getRegistry().register(WhiteFuton.setRegistryName(modid,"white_futon").setUnlocalizedName("Futon"));
        event.getRegistry().register(OrangeFuton.setRegistryName(modid,"orange_futon").setUnlocalizedName("Futon"));
        event.getRegistry().register(MagentaFuton.setRegistryName(modid,"magenta_futon").setUnlocalizedName("Futon"));
        event.getRegistry().register(LightBlueFuton.setRegistryName(modid,"light_blue_futon").setUnlocalizedName("Futon"));
        event.getRegistry().register(YellowFuton.setRegistryName(modid,"yellow_futon").setUnlocalizedName("Futon"));
        event.getRegistry().register(LimeFuton.setRegistryName(modid,"lime_futon").setUnlocalizedName("Futon"));
        event.getRegistry().register(PinkFuton.setRegistryName(modid,"pink_futon").setUnlocalizedName("Futon"));
        event.getRegistry().register(GrayFuton.setRegistryName(modid,"gray_futon").setUnlocalizedName("Futon"));
        event.getRegistry().register(LightGrayFuton.setRegistryName(modid,"light_gray_futon").setUnlocalizedName("Futon"));
        event.getRegistry().register(CyanFuton.setRegistryName(modid,"cyan_futon").setUnlocalizedName("Futon"));
        event.getRegistry().register(PurpleFuton.setRegistryName(modid,"purple_futon").setUnlocalizedName("Futon"));
        event.getRegistry().register(BlueFuton.setRegistryName(modid,"blue_futon").setUnlocalizedName("Futon"));
        event.getRegistry().register(BrownFuton.setRegistryName(modid,"brown_futon").setUnlocalizedName("Futon"));
        event.getRegistry().register(GreenFuton.setRegistryName(modid,"green_futon").setUnlocalizedName("Futon"));
        event.getRegistry().register(BlackFuton.setRegistryName(modid,"black_futon").setUnlocalizedName("Futon"));
        event.getRegistry().register(KakejikuA.setRegistryName(modid,"kakejiku_a").setUnlocalizedName("CharacterKakejiku"));
        event.getRegistry().register(KakejikuB.setRegistryName(modid,"kakejiku_b").setUnlocalizedName("CharacterKakejiku"));
        event.getRegistry().register(KakejikuC.setRegistryName(modid,"kakejiku_c").setUnlocalizedName("CharacterKakejiku"));
        event.getRegistry().register(KakejikuD.setRegistryName(modid,"kakejiku_d").setUnlocalizedName("CharacterKakejiku"));
        event.getRegistry().register(FishBowl.setRegistryName(modid,"golden_fish_bowl").setUnlocalizedName("GoldenFishBowl"));
        event.getRegistry().register(BlankTenbukuroFusuma.setRegistryName(modid,"blank_tenbukuro_fusuma").setUnlocalizedName("BlankTenbukuroFusuma"));
        event.getRegistry().register(BlueSeigaihaFusuma.setRegistryName(modid,"blue_seigaiha_fusuma").setUnlocalizedName("BlueSeigaihaFusuma"));
        event.getRegistry().register(BlueSayagataFusuma.setRegistryName(modid,"blue_sayagata_fusuma").setUnlocalizedName("BlueSayagataFusuma"));
        event.getRegistry().register(Onibi.setRegistryName(modid,"onibi_block").setUnlocalizedName("Onibi"));
        event.getRegistry().register(RedUrushiFence.setRegistryName(modid,"red_urushi_fence").setUnlocalizedName("RedUrushiFence"));
        event.getRegistry().register(BlackUrushiFence.setRegistryName(modid,"black_urushi_fence").setUnlocalizedName("BlackUrushiFence"));
        event.getRegistry().register(JapaneseApricotFence.setRegistryName(modid,"japanese_apricot_fence").setUnlocalizedName("JapaneseApricotFence"));
        event.getRegistry().register(SakuraFence.setRegistryName(modid,"sakura_fence").setUnlocalizedName("SakuraFence"));
        event.getRegistry().register(CypressFence.setRegistryName(modid,"cypress_fence").setUnlocalizedName("CypressFence"));
        event.getRegistry().register(RedUrushiFenceGate.setRegistryName(modid,"red_urushi_fence_gate").setUnlocalizedName("RedUrushiFenceGate"));
        event.getRegistry().register(BlackUrushiFenceGate.setRegistryName(modid,"black_urushi_fence_gate").setUnlocalizedName("BlackUrushiFenceGate"));
        event.getRegistry().register(JapaneseApricotFenceGate.setRegistryName(modid,"japanese_apricot_fence_gate").setUnlocalizedName("JapaneseApricotFenceGate"));
        event.getRegistry().register(SakuraFenceGate.setRegistryName(modid,"sakura_fence_gate").setUnlocalizedName("SakuraFenceGate"));
        event.getRegistry().register(CypressFenceGate.setRegistryName(modid,"cypress_fence_gate").setUnlocalizedName("CypressFenceGate"));
        event.getRegistry().register(MirrorGate.setRegistryName(modid,"mirror_gate").setUnlocalizedName("MirrorGate"));
        event.getRegistry().register(FramedPlaster2.setRegistryName(modid,"framed_plaster2").setUnlocalizedName("FramedPlaster2"));
        event.getRegistry().register(Parapet.setRegistryName(modid,"parapet").setUnlocalizedName("Parapet"));
        event.getRegistry().register(Parapet2.setRegistryName(modid,"parapet2").setUnlocalizedName("Parapet2"));
        event.getRegistry().register(FramedWattleAndDaub.setRegistryName(modid,"framed_wattle_and_daub").setUnlocalizedName("FramedWattleAndDaub"));
        event.getRegistry().register(Giboshi.setRegistryName(modid,"giboshi").setUnlocalizedName("Giboshi"));
        event.getRegistry().register(BambooBlock.setRegistryName(modid,"bamboo_block").setUnlocalizedName("BambooBlock"));
        event.getRegistry().register(BambooSlabSingle.setRegistryName(modid,"bamboo_slab_single").setUnlocalizedName("BambooSlab"));
        event.getRegistry().register(BambooSlabDouble.setRegistryName(modid,"bamboo_slab_double").setUnlocalizedName("BambooSlab"));
        event.getRegistry().register(BambooStairs.setRegistryName(modid,"bamboo_stairs").setUnlocalizedName("BambooStairs"));
        event.getRegistry().register(RawUrushiLayer.setRegistryName(modid,"raw_urushi_layer").setUnlocalizedName("RawUrushiLayer"));
        event.getRegistry().register(WattleAndDaubSlabSingle.setRegistryName(modid,"wattle_and_daub_slab_single").setUnlocalizedName("WattleAndDaubSlab"));
        event.getRegistry().register(WattleAndDaubSlabDouble.setRegistryName(modid,"wattle_and_daub_slab_double").setUnlocalizedName("WattleAndDaubSlab"));
        event.getRegistry().register(WattleAndDaubStairs.setRegistryName(modid,"wattle_and_daub_stairs").setUnlocalizedName("WattleAndDaubStairs"));
        event.getRegistry().register(RoughStoneStairs.setRegistryName(modid,"rough_stone_stairs").setUnlocalizedName("RoughStoneStairs"));
        event.getRegistry().register(UStone2.setRegistryName(modid,"u_stone2").setUnlocalizedName("UStone2"));
        event.getRegistry().register(ConcreteStairs.setRegistryName(modid,"concrete_stairs").setUnlocalizedName("ConcreteStairs"));
        event.getRegistry().register(RoughStoneWall.setRegistryName(modid,"rough_stone_wall").setUnlocalizedName("RoughStoneWall"));
        event.getRegistry().register(FramedGlass.setRegistryName(modid,"framed_glass").setUnlocalizedName("FramedGlass"));
        event.getRegistry().register(FramedGlassPane.setRegistryName(modid,"framed_glass_pane").setUnlocalizedName("FramedGlassPane"));
        event.getRegistry().register(TatamiCarpet.setRegistryName(modid,"tatami_carpet").setUnlocalizedName("TatamiCarpet"));
        //event.getRegistry().register(IbushiKawaraRoof45.setRegistryName(modid,"ibushi_kawara_roof_45").setUnlocalizedName("IbushiKawaraRoof45"));
        //event.getRegistry().register(KawaraRoof225Single.setRegistryName(modid,"kawara_roof_225_single").setUnlocalizedName("KawaraRoof225"));
        //event.getRegistry().register(KawaraRoof225Double.setRegistryName(modid,"kawara_roof_225_double").setUnlocalizedName("KawaraRoof225"));



    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void registerModels(ModelRegistryEvent event) {
        /**アイテム状態のモデルを指定*/
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
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UStone), 15, new ModelResourceLocation(new ResourceLocation(modid, "rough_stone"), "inventory"));
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
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UStoneSlabSingle), 4, new ModelResourceLocation(new ResourceLocation(modid, "rough_stone_slab"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UStoneSlabSingle), 5, new ModelResourceLocation(new ResourceLocation(modid, "concrete_slab"), "inventory"));
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
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(WoodenBucket), 10, new ModelResourceLocation(new ResourceLocation(modid, "wooden_bucket_water1"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(WoodenBucket), 11, new ModelResourceLocation(new ResourceLocation(modid, "wooden_bucket_water2"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(WoodenBucket), 12, new ModelResourceLocation(new ResourceLocation(modid, "wooden_bucket_water3"), "inventory"));
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
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(FramedPlaster), 7, new ModelResourceLocation(new ResourceLocation(modid, "sakura_framed_plaster"), "inventory"));
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
        ModelLoader.setCustomModelResourceLocation(RawTsuna,0, new ModelResourceLocation(new ResourceLocation(modid, "raw_tsuna"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(USapling), 4, new ModelResourceLocation(new ResourceLocation(modid, "large_sakura_sapling"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(LycorisRadiata), 1, new ModelResourceLocation(new ResourceLocation(modid, "indigo"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems,37, new ModelResourceLocation(new ResourceLocation(modid, "cypress_bark"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UStrippedLog), 0, new ModelResourceLocation(new ResourceLocation(modid, "stripped_cypress_log"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ULeaves2), 4, new ModelResourceLocation(new ResourceLocation(modid, "japanese_apricot_leaves"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ULeaves2), 5, new ModelResourceLocation(new ResourceLocation(modid, "sakura_leaves"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(USapling2), 0, new ModelResourceLocation(new ResourceLocation(modid, "glowing_japanese_apricot_sapling"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(USapling2), 1, new ModelResourceLocation(new ResourceLocation(modid, "glowing_sakura_sapling"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(USapling2), 2, new ModelResourceLocation(new ResourceLocation(modid, "glowing_big_sakura_sapling"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 38, new ModelResourceLocation(new ResourceLocation(modid, "futon_item_white"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 39, new ModelResourceLocation(new ResourceLocation(modid, "futon_item_orange"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 40, new ModelResourceLocation(new ResourceLocation(modid, "futon_item_magenta"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 41, new ModelResourceLocation(new ResourceLocation(modid, "futon_item_light_blue"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 42, new ModelResourceLocation(new ResourceLocation(modid, "futon_item_yellow"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 43, new ModelResourceLocation(new ResourceLocation(modid, "futon_item_lime"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 44, new ModelResourceLocation(new ResourceLocation(modid, "futon_item_pink"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 45, new ModelResourceLocation(new ResourceLocation(modid, "futon_item_gray"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 46, new ModelResourceLocation(new ResourceLocation(modid, "futon_item_light_gray"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 47, new ModelResourceLocation(new ResourceLocation(modid, "futon_item_cyan"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 48, new ModelResourceLocation(new ResourceLocation(modid, "futon_item_purple"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 49, new ModelResourceLocation(new ResourceLocation(modid, "futon_item_blue"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 50, new ModelResourceLocation(new ResourceLocation(modid, "futon_item_brown"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 51, new ModelResourceLocation(new ResourceLocation(modid, "futon_item_green"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 52, new ModelResourceLocation(new ResourceLocation(modid, "futon_item_black"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(FishBowl), 0, new ModelResourceLocation(new ResourceLocation(modid, "goldfish_bowl"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(BlankTenbukuroFusuma), 0, new ModelResourceLocation(new ResourceLocation(modid, "blank_tenbukuro_fusuma"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(KakejikuItem, 0, new ModelResourceLocation(new ResourceLocation(modid, "kakejiku0"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(KakejikuItem, 1, new ModelResourceLocation(new ResourceLocation(modid, "kakejiku1"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(KakejikuItem, 2, new ModelResourceLocation(new ResourceLocation(modid, "kakejiku2"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(KakejikuItem, 3, new ModelResourceLocation(new ResourceLocation(modid, "kakejiku3"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(KakejikuItem, 4, new ModelResourceLocation(new ResourceLocation(modid, "kakejiku4"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(KakejikuItem, 5, new ModelResourceLocation(new ResourceLocation(modid, "kakejiku5"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(KakejikuItem, 6, new ModelResourceLocation(new ResourceLocation(modid, "kakejiku6"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(KakejikuItem, 7, new ModelResourceLocation(new ResourceLocation(modid, "kakejiku7"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(KakejikuItem, 8, new ModelResourceLocation(new ResourceLocation(modid, "kakejiku8"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(KakejikuItem, 9, new ModelResourceLocation(new ResourceLocation(modid, "kakejiku9"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(KakejikuItem, 10, new ModelResourceLocation(new ResourceLocation(modid, "kakejiku10"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(KakejikuItem, 11, new ModelResourceLocation(new ResourceLocation(modid, "kakejiku11"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(KakejikuItem, 12, new ModelResourceLocation(new ResourceLocation(modid, "kakejiku12"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(KakejikuItem, 13, new ModelResourceLocation(new ResourceLocation(modid, "kakejiku13"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(KakejikuItem, 14, new ModelResourceLocation(new ResourceLocation(modid, "kakejiku14"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 53, new ModelResourceLocation(new ResourceLocation(modid, "blue_seigaiha_fusuma"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 54, new ModelResourceLocation(new ResourceLocation(modid, "blue_sayagata_fusuma"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Wagasa, 0, new ModelResourceLocation(new ResourceLocation(modid, "wagasa"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Wagasa, 1, new ModelResourceLocation(new ResourceLocation(modid, "wagasa_closed"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(MetalClub, 0, new ModelResourceLocation(new ResourceLocation(modid, "metal_club"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(ItemOnibi, 0, new ModelResourceLocation(new ResourceLocation(modid, "onibi"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(RedUrushiFence), 0, new ModelResourceLocation(new ResourceLocation(modid, "red_urushi_fence"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(BlackUrushiFence), 0, new ModelResourceLocation(new ResourceLocation(modid, "black_urushi_fence"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(JapaneseApricotFence), 0, new ModelResourceLocation(new ResourceLocation(modid, "japanese_apricot_fence"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(SakuraFence), 0, new ModelResourceLocation(new ResourceLocation(modid, "sakura_fence"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(CypressFence), 0, new ModelResourceLocation(new ResourceLocation(modid, "cypress_fence"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(RedUrushiFenceGate), 0, new ModelResourceLocation(new ResourceLocation(modid, "red_urushi_fence_gate"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(BlackUrushiFenceGate), 0, new ModelResourceLocation(new ResourceLocation(modid, "black_urushi_fence_gate"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(JapaneseApricotFenceGate), 0, new ModelResourceLocation(new ResourceLocation(modid, "japanese_apricot_fence_gate"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(SakuraFenceGate), 0, new ModelResourceLocation(new ResourceLocation(modid, "sakura_fence_gate"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(CypressFenceGate), 0, new ModelResourceLocation(new ResourceLocation(modid, "cypress_fence_gate"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(MirrorGate), 0, new ModelResourceLocation(new ResourceLocation(modid, "mirror"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(TenguFan, 0, new ModelResourceLocation(new ResourceLocation(modid, "tengu_fan"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(FramedPlaster2), 0, new ModelResourceLocation(new ResourceLocation(modid, "black_framed_plaster"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(FramedPlaster2), 1, new ModelResourceLocation(new ResourceLocation(modid, "japanese_apricot_framed_plaster"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(FramedPlaster2), 2, new ModelResourceLocation(new ResourceLocation(modid, "cypress_framed_plaster"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 55, new ModelResourceLocation(new ResourceLocation(modid, "indigo_ball"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 56, new ModelResourceLocation(new ResourceLocation(modid, "wood_chip"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 57, new ModelResourceLocation(new ResourceLocation(modid, "japanese_apricot_bark"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 58, new ModelResourceLocation(new ResourceLocation(modid, "sakura_bark"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UStrippedLog), 1, new ModelResourceLocation(new ResourceLocation(modid, "stripped_japanese_apricot_log"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UStrippedLog), 2, new ModelResourceLocation(new ResourceLocation(modid, "stripped_sakura_log"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(Parapet), 0, new ModelResourceLocation(new ResourceLocation(modid, "oak_parapet"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(Parapet), 1, new ModelResourceLocation(new ResourceLocation(modid, "spruce_parapet"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(Parapet), 2, new ModelResourceLocation(new ResourceLocation(modid, "birch_parapet"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(Parapet), 3, new ModelResourceLocation(new ResourceLocation(modid, "jungle_parapet"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(Parapet), 4, new ModelResourceLocation(new ResourceLocation(modid, "acacia_parapet"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(Parapet), 5, new ModelResourceLocation(new ResourceLocation(modid, "dark_oak_parapet"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(Parapet), 6, new ModelResourceLocation(new ResourceLocation(modid, "red_parapet"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(Parapet), 7, new ModelResourceLocation(new ResourceLocation(modid, "black_parapet"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(Parapet2), 0, new ModelResourceLocation(new ResourceLocation(modid, "japanese_apricot_parapet"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(Parapet2), 1, new ModelResourceLocation(new ResourceLocation(modid, "sakura_parapet"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(Parapet2), 2, new ModelResourceLocation(new ResourceLocation(modid, "cypress_parapet"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(FramedWattleAndDaub), 0, new ModelResourceLocation(new ResourceLocation(modid, "wattle_and_daub_oak_framed"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(FramedWattleAndDaub), 1, new ModelResourceLocation(new ResourceLocation(modid, "wattle_and_daub_spruce_framed"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(FramedWattleAndDaub), 2, new ModelResourceLocation(new ResourceLocation(modid, "wattle_and_daub_birch_framed"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(FramedWattleAndDaub), 3, new ModelResourceLocation(new ResourceLocation(modid, "wattle_and_daub_jungle_framed"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(FramedWattleAndDaub), 4, new ModelResourceLocation(new ResourceLocation(modid, "wattle_and_daub_acacia_framed"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(FramedWattleAndDaub), 5, new ModelResourceLocation(new ResourceLocation(modid, "wattle_and_daub_dark_oak_framed"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(Giboshi), 0, new ModelResourceLocation(new ResourceLocation(modid, "iron_giboshi"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(Giboshi), 1, new ModelResourceLocation(new ResourceLocation(modid, "gold_giboshi"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 59, new ModelResourceLocation(new ResourceLocation(modid, "red_urushi_ball"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 60, new ModelResourceLocation(new ResourceLocation(modid, "black_urushi_ball"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(RawUrushiLayer), 0, new ModelResourceLocation(new ResourceLocation(modid, "raw_urushi_layer"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(BambooBlock), 0, new ModelResourceLocation(new ResourceLocation(modid, "bamboo_block"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(BambooBlock), 1, new ModelResourceLocation(new ResourceLocation(modid, "bamboo_charcoal_block"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(BambooSlabSingle), 0, new ModelResourceLocation(new ResourceLocation(modid, "bamboo_slab"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(BambooStairs), 0, new ModelResourceLocation(new ResourceLocation(modid, "bamboo_stairs"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(UItems, 61, new ModelResourceLocation(new ResourceLocation(modid, "bamboo_charcoal"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(WattleAndDaubSlabSingle), 0, new ModelResourceLocation(new ResourceLocation(modid, "wattle_and_daub_slab"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(WattleAndDaubStairs), 0, new ModelResourceLocation(new ResourceLocation(modid, "wattle_and_daub_stairs"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(RoughStoneStairs), 0, new ModelResourceLocation(new ResourceLocation(modid, "rough_stone_stairs"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ConcreteStairs), 0, new ModelResourceLocation(new ResourceLocation(modid, "concrete_stairs"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(UStone2), 0, new ModelResourceLocation(new ResourceLocation(modid, "concrete"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(RoughStoneWall), 0, new ModelResourceLocation(new ResourceLocation(modid, "rough_stone_wall"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(FramedGlass), 0, new ModelResourceLocation(new ResourceLocation(modid, "connectable_glass"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(FramedGlass), 1, new ModelResourceLocation(new ResourceLocation(modid, "metal_framed_glass"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(FramedGlassPane), 0, new ModelResourceLocation(new ResourceLocation(modid, "connectable_glass_pane"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(FramedGlassPane), 1, new ModelResourceLocation(new ResourceLocation(modid, "metal_framed_glass_pane"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(TatamiCarpet), 1, new ModelResourceLocation(new ResourceLocation(modid, "green_tatami_carpet"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(TatamiCarpet), 0, new ModelResourceLocation(new ResourceLocation(modid, "brown_tatami_carpet"), "inventory"));
        //ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(IbushiKawaraRoof45), 0, new ModelResourceLocation(new ResourceLocation(modid, "ibushi_kawara_roof_45"), "inventory"));



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
        OreDictionary.registerOre("treeSapling",new ItemStack(USapling,1,4));
        OreDictionary.registerOre("treeSapling",new ItemStack(USapling2,1,0));
        OreDictionary.registerOre("treeSapling",new ItemStack(USapling2,1,1));
        OreDictionary.registerOre("treeSapling",new ItemStack(USapling2,1,2));
        OreDictionary.registerOre("treeLeaves",new ItemStack(ULeaves,1,4));
        OreDictionary.registerOre("treeLeaves",new ItemStack(ULeaves,1,5));
        OreDictionary.registerOre("treeLeaves",new ItemStack(ULeaves2,1,4));
        OreDictionary.registerOre("treeLeaves",new ItemStack(ULeaves2,1,5));
        OreDictionary.registerOre("treeLeaves",new ItemStack(ULeaves2,1,6));
        OreDictionary.registerOre("treeLeaves",new ItemStack(ULeaves2,1,7));
        OreDictionary.registerOre("slimeball",new ItemStack(UItems,1,8));
        OreDictionary.registerOre("sakuraLeaves",new ItemStack(ULeaves,1,5));
        OreDictionary.registerOre("sakuraLeaves",new ItemStack(ULeaves2,1,5));
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
        OreDictionary.registerOre("logWood",new ItemStack(UStrippedLog,1,0));
        OreDictionary.registerOre("logWood",new ItemStack(UStrippedLog,1,1));
        OreDictionary.registerOre("logWood",new ItemStack(UStrippedLog,1,2));
        OreDictionary.registerOre("dyeBlue",new ItemStack(UItems,1,55));
        OreDictionary.registerOre("slimeball",new ItemStack(UItems,1,59));
        OreDictionary.registerOre("slimeball",new ItemStack(UItems,1,60));
        OreDictionary.registerOre("plankWood",new ItemStack(BambooBlock,1,0));
        OreDictionary.registerOre("slabWood",new ItemStack(BambooSlabSingle,1,0));
        OreDictionary.registerOre("stairWood",new ItemStack(BambooStairs,1,0));
        OreDictionary.registerOre("stairWood",new ItemStack(JapaneseApricotStairs,1,0));
        OreDictionary.registerOre("stairWood",new ItemStack(SakuraStairs,1,0));
        OreDictionary.registerOre("stairWood",new ItemStack(CypressStairs,1,0));
        OreDictionary.registerOre("stairWood",new ItemStack(StairsRedUrushiStained,1,0));
        OreDictionary.registerOre("stairWood",new ItemStack(StairsBlackUrushiStained,1,0));
        OreDictionary.registerOre("fenceWood",new ItemStack(SakuraFence,1,0));
        OreDictionary.registerOre("fenceWood",new ItemStack(JapaneseApricotFence,1,0));
        OreDictionary.registerOre("fenceWood",new ItemStack(CypressFence,1,0));
        OreDictionary.registerOre("fenceWood",new ItemStack(RedUrushiFence,1,0));
        OreDictionary.registerOre("fenceWood",new ItemStack(BlackUrushiFence,1,0));
        OreDictionary.registerOre("fenceGateWood",new ItemStack(JapaneseApricotFenceGate,1,0));
        OreDictionary.registerOre("fenceGateWood",new ItemStack(JapaneseApricotFenceGate,1,0));
        OreDictionary.registerOre("fenceGateWood",new ItemStack(SakuraFenceGate,1,0));
        OreDictionary.registerOre("fenceGateWood",new ItemStack(CypressFenceGate,1,0));
        OreDictionary.registerOre("fenceGateWood",new ItemStack(RedUrushiFenceGate,1,0));
        OreDictionary.registerOre("fenceGateWood",new ItemStack(BlackUrushiFenceGate,1,0));




        /**エンティティを登録*/
        EntityRegistry.registerModEntity(new ResourceLocation("entity_cushion"), EntityCushion.class, "EntityCushion", EntityCushionID, this, 10, 1, true);
        EntityRegistry.registerModEntity(new ResourceLocation("entity_red_oni"), EntityOni.class, "EntityRedOni", EntityRedOniID, this, 50, 1, true,000555555,150000000);
        EntityRegistry.registerModEntity(new ResourceLocation("entity_oni_girl"), EntityOniGirl.class, "EntityOniGirl", EntityOniGirlID, this, 50, 1, true,100,200);
        EntityRegistry.registerModEntity(new ResourceLocation("entity_onibi"), EntityOnibi.class, "EntityOnibi", EntityOnibiID, this, 10, 1, true);


        /**草を壊した時に出る種を登録*/
        MinecraftForge.addGrassSeed(new ItemStack(ModCore_Urushi.RiceEars),10);
        MinecraftForge.addGrassSeed(new ItemStack(ModCore_Urushi.Azuki),10);
    }

    @Mod.EventHandler
    public void preInt(FMLPreInitializationEvent event)  {
/**モブドロップや宝箱チェストの中身などを決定するルートテーブルを登録*/
        LootTableList.register(RED_ONI);



        /**コンフィグ設定を追加*/
        Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
        try {
            cfg.load();
           Property e1ID1 = cfg.get("world generation", "ganerate Sakura trees in Forest Biome" ,wheather_ganerate_Sakura);
            Property e1ID2 = cfg.get("world generation", "ganerate Japanese Apricot trees in Forest Biome",wheather_ganerate_Ume);
            Property e1ID3 = cfg.get("world generation", "ganerate Lacquer trees in Forest Biome" ,wheather_ganerate_Urushi);
            Property e1ID7 = cfg.get("world generation", "ganerate Cypress trees in ExtremeHills Biome" ,wheather_ganerate_Cypress);
            Property e1ID4 = cfg.get("world generation", "generate Japanese Timber Bamboo in ForestHills Biome" ,wheather_ganerate_Bamboo);
            Property e1ID5 = cfg.get("world generation", "generate Copper Ore" ,wheather_ganerate_CopperOre);
            Property e1ID6 = cfg.get("block settings", "max height of Japanese Timber Bamboo" ,max_height_Bamboo);
            Property e1ID9 = cfg.get("world generation", "the dimension ID of Kakuriyo Dimension" ,KakuriyoDimensionID);
            Property e1ID10 = cfg.get("entity settings", "the Entity ID of Cushion" ,EntityCushionID);
            Property e1ID11 = cfg.get("entity settings", "the Entity ID of Red Oni" ,EntityRedOniID);
            Property e1ID12 = cfg.get("entity settings", "the Entity ID of Oni Girl" ,EntityOniGirlID);
            Property e1ID13 = cfg.get("entity settings", "the Entity ID of Onibi" ,EntityOnibiID);
            Property e1ID14 = cfg.get("item settings", "the range of Onibi" ,range_of_onibi);
            Property e1ID15 = cfg.get("world generation", "generate the gates for Kakuriyo Dimension in Forest Biome" ,wheather_ganerate_Gate);
            Property e1ID16 = cfg.get("entity settings", "increase the player walking spped by 16% so that you can place blocks comfortably" ,wheather_player_speed_up);


            wheather_ganerate_Sakura = e1ID1.getBoolean();
            wheather_ganerate_Ume = e1ID2.getBoolean();
            wheather_ganerate_Urushi = e1ID3.getBoolean();
            wheather_ganerate_Bamboo = e1ID4.getBoolean();
            wheather_ganerate_CopperOre = e1ID5.getBoolean();
            max_height_Bamboo=e1ID6.getInt();
            wheather_ganerate_Cypress = e1ID7.getBoolean();
            KakuriyoDimensionID=e1ID9.getInt();
            EntityCushionID=e1ID10.getInt();
            EntityRedOniID=e1ID11.getInt();
            EntityOniGirlID=e1ID12.getInt();
            EntityOnibiID=e1ID13.getInt();
            range_of_onibi=e1ID14.getInt();
            wheather_ganerate_Gate=e1ID15.getBoolean();
            wheather_player_speed_up=e1ID16.getBoolean();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cfg.save();
        }


       if(event.getSide().isClient()) {
        /**タイルエンティティを登録*/
           GameRegistry.registerTileEntity(TileEntityRiceCauldron.getClass(),new ResourceLocation(modid,"rice_cauldron"));
           GameRegistry.registerTileEntity(TileEntityWoodenCabinetry.getClass(),new ResourceLocation(modid,"wooden_cabinetry"));
            GameRegistry.registerTileEntity(TileEntityWoodenCabinetryUnderSlab.getClass(),new ResourceLocation(modid,"wooden_cabinetry_under_slab"));
           GameRegistry.registerTileEntity(TileEntityRiceHokora.getClass(),new ResourceLocation(modid,"rice_hokora"));
           GameRegistry.registerTileEntity(TileEntityFuton.getClass(),new ResourceLocation(modid,"futon"));
           GameRegistry.registerTileEntity(TileEntityFermentationPot.getClass(),new ResourceLocation(modid,"fermentation_pot"));



           /**鉱石や樹木、花などを自然生成させる*/
           GameRegistry.registerWorldGenerator(new OreGen(), 3);

           /**エンティティのレンダーとモデルを登録*/
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
    }
    @Mod.EventHandler
    public void postInt(FMLPostInitializationEvent event) {
        /**ディメンションを登録*/
        Kakuriyo_DIMENSION = DimensionType.register("KakuriyoDimension", "_kakuriyo", KakuriyoDimensionID, WorldProviderKakuriyo.class, false);
        DimensionManager.registerDimension(KakuriyoDimensionID, Kakuriyo_DIMENSION);

        if (event.getSide().isClient()) {
        /**精錬レシピを登録*/
            GameRegistry.addSmelting(new ItemStack(UItems, 1, 10), new ItemStack(UItems, 1, 3), 5F);
            GameRegistry.addSmelting(new ItemStack(UStone, 1, 2), new ItemStack(UItems, 1, 9), 5F);
            GameRegistry.addSmelting(new ItemStack(UItems, 1, 5), new ItemStack(Rice, 1, 0), 5F);
            GameRegistry.addSmelting(new ItemStack(Mochi, 1, 0), new ItemStack(YakiMochi, 1, 0), 5F);
            GameRegistry.addSmelting(new ItemStack(UItems, 1, 35), new ItemStack(FermentationPot, 1, 0), 5F);
            GameRegistry.addSmelting(new ItemStack(UItems, 1, 0), new ItemStack(UItems, 1, 61), 5F);

        }

        /**バイオームを追加*/
         SakuraBiome.setRegistryName("Sakura");
         ForgeRegistries.BIOMES.register(SakuraBiome);
         BiomeDictionary.addTypes(SakuraBiome, BiomeDictionary.Type.FOREST);
         /**下の2行を実装すると、オーバーワールドに指定したバイオームが生成されるようになる*/
         //BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(SakuraBiome, 500));
         //BiomeManager.addSpawnBiome(SakuraBiome);
    }
    @SubscribeEvent
    public void FuelEvent(FurnaceFuelBurnTimeEvent event) {
        /**燃料の燃焼時間を設定*/
        if (event.getItemStack().getItem()==ModCore_Urushi.UItems&&event.getItemStack().getItemDamage()==61) {
            event.setBurnTime(1600);
        }else if (event.getItemStack().getItem()==Item.getItemFromBlock(ModCore_Urushi.BambooBlock)&&event.getItemStack().getItemDamage()==1) {
            event.setBurnTime(16000);
        }
    }
@SubscribeEvent
    public void LoottableAddEvent(LootTableLoadEvent event){



        /**釣りのアイテムを追加*/
/*
    if (event.getName().equals(LootTableList.GAMEPLAY_FISHING_FISH))
    {
        LootEntry entry1 = new LootEntryItem(ModCore_Urushi.RawTsuna, 5, 20, new LootFunction[0], new LootCondition[0] ,"urushi:entry_fish1");
        LootPool pool1 = new LootPool(new LootEntry[]{entry1}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0,1), "urushi:pool_fish1");
        event.getTable().addPool(pool1);
    }
    */
}

    @SubscribeEvent
    public void PlayerSpeedEvent(EntityEvent.EnteringChunk event) {
        if (wheather_player_speed_up) {
            if (event.getEntity() instanceof EntityPlayer) {
                EntityPlayer entityPlayer = (EntityPlayer) event.getEntity();
                if (event.getEntity().world.isRemote) {
                    entityPlayer.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.116d);
                }
            }
        }
    }
    @SubscribeEvent
    public void TorchIgniteEntityEvent(PlayerInteractEvent.EntityInteract event) {
        if (!event.getWorld().isRemote) {
            if (event.getItemStack().getItem()== Item.getItemFromBlock(Blocks.TORCH)||event.getItemStack().getItem()== Items.FLINT_AND_STEEL) {
                event.getTarget().setFire(6);
                event.getWorld().playSound((EntityPlayer)null, event.getPos(), SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);
            }

        }
    }


    }

package urushi.Item;

import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockConcretePowder;
import net.minecraft.block.BlockFire;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import urushi.ModCore_Urushi;
import urushi.TileEntity.TileEntityRiceHokora;

import javax.annotation.Nullable;
import java.util.List;

public class Charm extends Item {
    protected static final AxisAlignedBB AABB1 = new AxisAlignedBB(0D, 0D, 0D, 1D, 1D, 1D);

    public Charm() {
        super();
        this.setRegistryName(ModCore_Urushi.modid, "charm");
        this.setCreativeTab(ModCore_Urushi.TabUrushi);
        this.setUnlocalizedName("Charm");
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }
    /*public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (this.isInCreativeTab(tab))
        {
            for(int i=0;i<6;i++) {
                if(i!=7)
                    items.add(new ItemStack(this,1,i));

            }

        }
    }*/

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand) {
        ItemStack itemStack=player.getHeldItem(hand);
        int meta=itemStack.getItemDamage();
        EnumFacing facing=player.getHorizontalFacing();
        double xadd=0D; double zadd=0D;
        if(facing==EnumFacing.NORTH){zadd=-1D;}
        else if(facing==EnumFacing.SOUTH){zadd=1D;}
        else if(facing==EnumFacing.EAST){xadd=1D;}
        else if(facing==EnumFacing.WEST){xadd=-1D;}
BlockPos pos=new BlockPos(Math.floor(player.posX),Math.floor(player.posY),Math.floor(player.posZ));
        if(findAmmo(player,300)!=999){
            ItemStack magatama=player.inventory.getStackInSlot(findAmmo(player,300));
            int preMeta=magatama.getItemDamage();
            //if(preMeta<4700) {

                if(meta==0) {
                    List<EntityLivingBase> list = worldIn.<EntityLivingBase>getEntitiesWithinAABB(EntityLivingBase.class, AABB1.grow(5D,5D,5D));

                    if (!list.isEmpty()) {
                        for (EntityLivingBase entitylivingbase : list) {
                            if(entitylivingbase.isBurning()) {
                                entitylivingbase.extinguish();
                            }
                        }
                   }
                    if(player.isBurning()){
                        player.extinguish();
                    }
                        for(int p=-10;p<11;p++) {
                            for(int q=-10;q<11;q++) {
                                for(int r=-10;r<11;r++) {
                                    if(worldIn.getBlockState(pos.add(p,q,r)).getBlock() instanceof BlockFire){
                                        worldIn.setBlockToAir(pos.add(p,q,r));
                                        worldIn.playSound((EntityPlayer)null, pos.add(p,q,r), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0F, 1.0F);
                                        worldIn.spawnParticle(EnumParticleTypes.END_ROD,(double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, 0.0D, 0.2D, 0.0D);
                                        worldIn.spawnParticle(EnumParticleTypes.END_ROD, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, 0.2D, 0D, 0.0D);
                                        worldIn.spawnParticle(EnumParticleTypes.END_ROD, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, -0.2D, 0D, 0.0D);
                                        worldIn.spawnParticle(EnumParticleTypes.END_ROD, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, 0D, 0D, 0.2D);
                                        worldIn.spawnParticle(EnumParticleTypes.END_ROD, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, 0.0D, 0D, -0.2D);
                                        worldIn.spawnParticle(EnumParticleTypes.END_ROD, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, 0.2D, 0D, 0.2D);
                                        worldIn.spawnParticle(EnumParticleTypes.END_ROD, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, -0.2D, 0D, 0.2D);
                                        worldIn.spawnParticle(EnumParticleTypes.END_ROD, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, 0.2D, 0D, -0.2D);
                                        worldIn.spawnParticle(EnumParticleTypes.END_ROD, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, -0.2D, 0D, -0.2D);
                                        worldIn.spawnParticle(EnumParticleTypes.END_ROD, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, 0D, -0.2D, 0.0D);


                                    }
                                    if(worldIn.getBlockState(pos.add(p,q,r)).getMaterial()== Material.LAVA){
                                        worldIn.setBlockState(pos.add(p,q,r), Blocks.OBSIDIAN.getDefaultState());
                                        worldIn.playSound((EntityPlayer)null, pos.add(p,q,r), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0F, 1.0F);
                                        worldIn.spawnParticle(EnumParticleTypes.END_ROD,(double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, 0.0D, 0.2D, 0.0D);
                                        worldIn.spawnParticle(EnumParticleTypes.END_ROD, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, 0.2D, 0D, 0.0D);
                                        worldIn.spawnParticle(EnumParticleTypes.END_ROD, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, -0.2D, 0D, 0.0D);
                                        worldIn.spawnParticle(EnumParticleTypes.END_ROD, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, 0D, 0D, 0.2D);
                                        worldIn.spawnParticle(EnumParticleTypes.END_ROD, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, 0.0D, 0D, -0.2D);
                                        worldIn.spawnParticle(EnumParticleTypes.END_ROD, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, 0.2D, 0D, 0.2D);
                                        worldIn.spawnParticle(EnumParticleTypes.END_ROD, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, -0.2D, 0D, 0.2D);
                                        worldIn.spawnParticle(EnumParticleTypes.END_ROD, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, 0.2D, 0D, -0.2D);
                                        worldIn.spawnParticle(EnumParticleTypes.END_ROD, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, -0.2D, 0D, -0.2D);
                                        worldIn.spawnParticle(EnumParticleTypes.END_ROD, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, 0D, -0.2D, 0.0D);
                                    }
                                    if(worldIn.getBlockState(pos.add(p,q,r)).getBlock()==Blocks.CONCRETE_POWDER){
                                        worldIn.setBlockState(pos.add(p,q,r), Blocks.CONCRETE.getDefaultState().withProperty(BlockColored.COLOR,worldIn.getBlockState(pos.add(p,q,r)).getValue(BlockConcretePowder.COLOR)));
                                        worldIn.playSound((EntityPlayer)null, pos.add(p,q,r), SoundEvents.ENTITY_BOAT_PADDLE_WATER, SoundCategory.BLOCKS, 2.0F, 1.0F);
                                        worldIn.spawnParticle(EnumParticleTypes.END_ROD,(double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, 0.0D, 0.2D, 0.0D);
                                        worldIn.spawnParticle(EnumParticleTypes.END_ROD, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, 0.2D, 0D, 0.0D);
                                        worldIn.spawnParticle(EnumParticleTypes.END_ROD, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, -0.2D, 0D, 0.0D);
                                        worldIn.spawnParticle(EnumParticleTypes.END_ROD, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, 0D, 0D, 0.2D);
                                        worldIn.spawnParticle(EnumParticleTypes.END_ROD, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, 0.0D, 0D, -0.2D);
                                        worldIn.spawnParticle(EnumParticleTypes.END_ROD, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, 0.2D, 0D, 0.2D);
                                        worldIn.spawnParticle(EnumParticleTypes.END_ROD, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, -0.2D, 0D, 0.2D);
                                        worldIn.spawnParticle(EnumParticleTypes.END_ROD, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, 0.2D, 0D, -0.2D);
                                        worldIn.spawnParticle(EnumParticleTypes.END_ROD, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, -0.2D, 0D, -0.2D);
                                        worldIn.spawnParticle(EnumParticleTypes.END_ROD, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, 0D, -0.2D, 0.0D);
                                    }
                                }
                            }
                        }

                    player.inventory.setInventorySlotContents(findAmmo(player,300), new ItemStack(ModCore_Urushi.QuartzMagatama, 1, preMeta + 300));
                    itemStack.shrink(1);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD,player.posX+xadd, player.posY + 1D, player.posZ+zadd, 0.0D, 0.15D, 0.0D);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD, player.posX+xadd, player.posY + 1D, player.posZ+zadd, 0.15D, 0D, 0.0D);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD, player.posX+xadd, player.posY + 1D, player.posZ+zadd, -0.15D, 0D, 0.0D);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD, player.posX+xadd, player.posY + 1D, player.posZ+zadd, 0D, 0D, 0.15D);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD, player.posX+xadd, player.posY + 1D, player.posZ+zadd, 0.0D, 0D, -0.15D);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD, player.posX+xadd, player.posY + 1D, player.posZ+zadd, 0.15D, 0D, 0.15D);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD, player.posX+xadd, player.posY + 1D, player.posZ+zadd, -0.15D, 0D, 0.15D);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD, player.posX+xadd, player.posY + 1D, player.posZ+zadd, 0.15D, 0D, -0.15D);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD, player.posX+xadd, player.posY + 1D, player.posZ+zadd, -0.15D, 0D, -0.15D);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD, player.posX+xadd, player.posY + 1D, player.posZ+zadd, 0D, -0.15D, 0.0D);
                    worldIn.playSound(player, pos, SoundEvents.ENTITY_ZOMBIE_VILLAGER_CURE, SoundCategory.PLAYERS, 0.5F, 1.0F);

                    return  new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStack);

                }else if(meta==1){

                    player.addPotionEffect(new PotionEffect(MobEffects.SATURATION, 600, 1));
                    player.inventory.setInventorySlotContents(findAmmo(player,300), new ItemStack(ModCore_Urushi.QuartzMagatama, 1, preMeta + 300));
                    itemStack.shrink(1);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD,player.posX+xadd, player.posY + 1D, player.posZ+zadd, 0.0D, 0.15D, 0.0D);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD, player.posX+xadd, player.posY + 1D, player.posZ+zadd, 0.15D, 0D, 0.0D);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD, player.posX+xadd, player.posY + 1D, player.posZ+zadd, -0.15D, 0D, 0.0D);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD, player.posX+xadd, player.posY + 1D, player.posZ+zadd, 0D, 0D, 0.15D);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD, player.posX+xadd, player.posY + 1D, player.posZ+zadd, 0.0D, 0D, -0.15D);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD, player.posX+xadd, player.posY + 1D, player.posZ+zadd, 0.15D, 0D, 0.15D);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD, player.posX+xadd, player.posY + 1D, player.posZ+zadd, -0.15D, 0D, 0.15D);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD, player.posX+xadd, player.posY + 1D, player.posZ+zadd, 0.15D, 0D, -0.15D);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD, player.posX+xadd, player.posY + 1D, player.posZ+zadd, -0.15D, 0D, -0.15D);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD, player.posX+xadd, player.posY + 1D, player.posZ+zadd, 0D, -0.15D, 0.0D);
                    worldIn.playSound(player, pos, SoundEvents.ENTITY_ZOMBIE_VILLAGER_CURE, SoundCategory.PLAYERS, 0.5F, 1.0F);

                    return  new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStack);
                }else if(meta==2){

                    player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 400, 3));
                    player.inventory.setInventorySlotContents(findAmmo(player,300), new ItemStack(ModCore_Urushi.QuartzMagatama, 1, preMeta + 300));
                    itemStack.shrink(1);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD,player.posX+xadd, player.posY + 1D, player.posZ+zadd, 0.0D, 0.15D, 0.0D);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD, player.posX+xadd, player.posY + 1D, player.posZ+zadd, 0.15D, 0D, 0.0D);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD, player.posX+xadd, player.posY + 1D, player.posZ+zadd, -0.15D, 0D, 0.0D);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD, player.posX+xadd, player.posY + 1D, player.posZ+zadd, 0D, 0D, 0.15D);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD, player.posX+xadd, player.posY + 1D, player.posZ+zadd, 0.0D, 0D, -0.15D);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD, player.posX+xadd, player.posY + 1D, player.posZ+zadd, 0.15D, 0D, 0.15D);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD, player.posX+xadd, player.posY + 1D, player.posZ+zadd, -0.15D, 0D, 0.15D);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD, player.posX+xadd, player.posY + 1D, player.posZ+zadd, 0.15D, 0D, -0.15D);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD, player.posX+xadd, player.posY + 1D, player.posZ+zadd, -0.15D, 0D, -0.15D);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD, player.posX+xadd, player.posY + 1D, player.posZ+zadd, 0D, -0.15D, 0.0D);
                    worldIn.playSound(player, pos, SoundEvents.ENTITY_ZOMBIE_VILLAGER_CURE, SoundCategory.PLAYERS, 0.5F, 1.0F);

                    return  new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStack);
                }else if(meta==4){

                    player.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, 1200, 4));
                    player.inventory.setInventorySlotContents(findAmmo(player,300), new ItemStack(ModCore_Urushi.QuartzMagatama, 1, preMeta + 300));
                    itemStack.shrink(1);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD,player.posX+xadd, player.posY + 1D, player.posZ+zadd, 0.0D, 0.15D, 0.0D);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD, player.posX+xadd, player.posY + 1D, player.posZ+zadd, 0.15D, 0D, 0.0D);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD, player.posX+xadd, player.posY + 1D, player.posZ+zadd, -0.15D, 0D, 0.0D);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD, player.posX+xadd, player.posY + 1D, player.posZ+zadd, 0D, 0D, 0.15D);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD, player.posX+xadd, player.posY + 1D, player.posZ+zadd, 0.0D, 0D, -0.15D);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD, player.posX+xadd, player.posY + 1D, player.posZ+zadd, 0.15D, 0D, 0.15D);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD, player.posX+xadd, player.posY + 1D, player.posZ+zadd, -0.15D, 0D, 0.15D);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD, player.posX+xadd, player.posY + 1D, player.posZ+zadd, 0.15D, 0D, -0.15D);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD, player.posX+xadd, player.posY + 1D, player.posZ+zadd, -0.15D, 0D, -0.15D);
                    worldIn.spawnParticle(EnumParticleTypes.END_ROD, player.posX+xadd, player.posY + 1D, player.posZ+zadd, 0D, -0.15D, 0.0D);
                    worldIn.playSound(player, pos, SoundEvents.ENTITY_ZOMBIE_VILLAGER_CURE, SoundCategory.PLAYERS, 0.5F, 1.0F);

                    return  new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStack);
                }

            }else{
                return  new ActionResult<ItemStack>(EnumActionResult.FAIL, itemStack);
            }
      //  }
        return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemStack);
    }



    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName()+"."+ +stack.getItemDamage();
    }




    public static int findAmmo(EntityPlayer player,int amount)
    {
int maxMana=5000;
            for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
            {
                ItemStack itemstack = player.inventory.getStackInSlot(i);

                if (itemstack.getItem()==ModCore_Urushi.QuartzMagatama&&itemstack.getItemDamage()<maxMana-amount)
                {
                    return i;
                }else{
                    continue;
                }
            }

            return 999;

    }
    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        int metadata = stack.getItemDamage();
        ITextComponent textComponent0 = new TextComponentTranslation("item.info.charm0", new Object[0]);
        ITextComponent textComponent1 = new TextComponentTranslation("item.info.charm1", new Object[0]);
        ITextComponent textComponent2 = new TextComponentTranslation("item.info.charm2", new Object[0]);
        ITextComponent textComponent3 = new TextComponentTranslation("item.info.charm3", new Object[0]);
        ITextComponent textComponent4 = new TextComponentTranslation("item.info.charm4", new Object[0]);
        ITextComponent textComponent5 = new TextComponentTranslation("item.info.charm5", new Object[0]);
        ITextComponent textComponent6 = new TextComponentTranslation("item.info.charm6", new Object[0]);
        ITextComponent textComponent7 = new TextComponentTranslation("item.info.charm7", new Object[0]);

        tooltip.add(textComponent0.getFormattedText());
        if (metadata == 0){
            tooltip.add(textComponent1.getFormattedText());
            tooltip.add(textComponent2.getFormattedText());
            tooltip.add(textComponent3.getFormattedText());
            tooltip.add(textComponent4.getFormattedText());
        }
        else if (metadata == 1) tooltip.add(textComponent5.getFormattedText());
        else if (metadata ==2) tooltip.add(textComponent6.getFormattedText());
        else if (metadata == 4) tooltip.add(textComponent7.getFormattedText());
    }
}

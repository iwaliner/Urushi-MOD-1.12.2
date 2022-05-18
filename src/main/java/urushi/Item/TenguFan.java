package urushi.Item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockFire;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import urushi.ModCore_Urushi;
import urushi.TileEntity.TileEntityRiceHokora;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.Set;


public class TenguFan extends Item {


    public TenguFan() {
        //super();

        this.setRegistryName(ModCore_Urushi.modid, "tengu_fan");
        this.setCreativeTab(ModCore_Urushi.TabUrushi);
        this.setUnlocalizedName("TenguFan");
        this.setMaxDamage(700);
   this.setMaxStackSize(1);
        this.addPropertyOverride(new ResourceLocation("blocking"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
            }
        });
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        BlockPos pos=new BlockPos(Math.floor(entityIn.posX),Math.floor(entityIn.posY),Math.floor(entityIn.posZ));
        if(entityIn instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityIn;
            if(Charm.findAmmo(player,1)!=999){
                ItemStack magatama=player.inventory.getStackInSlot(Charm.findAmmo(player,1));
                int preMeta=magatama.getItemDamage();

            if (player.isHandActive() && player.getActiveItemStack() == stack) {
                Random rand=new Random();
                double d0 = (double)Math.floor(entityIn.posX)-2D + 0.1D*rand.nextInt(64);
                double d1 =(double)Math.floor(entityIn.posY) +1D+ 0.1D*rand.nextInt(16);
                double d2 = (double)Math.floor(entityIn.posZ)-2D + 0.1D*rand.nextInt(64);
                worldIn.spawnParticle(EnumParticleTypes.SWEEP_ATTACK,  d0, d1, d2, 0.0D, 0D, 0.0D);




                AxisAlignedBB axisalignedbb = entityIn.getEntityBoundingBox().grow(3.0D, 2.0D, 3.0D);

                List<EntityLivingBase> list = worldIn.<EntityLivingBase>getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);
                if (!list.isEmpty()) {
                    for (EntityLivingBase entitylivingbase : list) {
                        if (entitylivingbase != entityIn) {
                            entitylivingbase.motionX = (entitylivingbase.posX - entityIn.posX) * 1.2D;
                            entitylivingbase.motionZ = (entitylivingbase.posZ - entityIn.posZ) * 1.2D;



                            //    stack.damageItem(1, (EntityLivingBase) entityIn);
                            player.inventory.setInventorySlotContents(Charm.findAmmo(player,1), new ItemStack(ModCore_Urushi.QuartzMagatama, 1, preMeta + 1));

                        }
                    }
                }

                for(int p=-10;p<11;p++) {
                    for(int q=-10;q<11;q++) {
                        for (int r = -10; r < 11; r++) {
                            if(worldIn.getBlockState(pos.add(p,q,r)).getBlock() instanceof BlockFire){
                                worldIn.setBlockToAir(pos.add(p,q,r));
                                worldIn.playSound((EntityPlayer)null, pos.add(p,q,r), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0F, 1.0F);
                                worldIn.spawnParticle(EnumParticleTypes.SWEEP_ATTACK,(double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, 0.0D, 0.2D, 0.0D);
                                worldIn.spawnParticle(EnumParticleTypes.SWEEP_ATTACK, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, 0.2D, 0D, 0.0D);
                                worldIn.spawnParticle(EnumParticleTypes.SWEEP_ATTACK, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, -0.2D, 0D, 0.0D);
                                worldIn.spawnParticle(EnumParticleTypes.SWEEP_ATTACK, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, 0D, 0D, 0.2D);
                                worldIn.spawnParticle(EnumParticleTypes.SWEEP_ATTACK, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, 0.0D, 0D, -0.2D);
                                worldIn.spawnParticle(EnumParticleTypes.SWEEP_ATTACK, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, 0.2D, 0D, 0.2D);
                                worldIn.spawnParticle(EnumParticleTypes.SWEEP_ATTACK, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, -0.2D, 0D, 0.2D);
                                worldIn.spawnParticle(EnumParticleTypes.SWEEP_ATTACK, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, 0.2D, 0D, -0.2D);
                                worldIn.spawnParticle(EnumParticleTypes.SWEEP_ATTACK, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, -0.2D, 0D, -0.2D);
                                worldIn.spawnParticle(EnumParticleTypes.SWEEP_ATTACK, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, 0D, -0.2D, 0.0D);

                            }
                            if(worldIn.getBlockState(pos.add(p,q,r)).getBlock() instanceof BlockBush){
                                worldIn.getBlockState(pos.add(p,q,r)).getBlock().dropBlockAsItem(worldIn,pos.add(p,q,r),worldIn.getBlockState(pos.add(p,q,r)),0);
                                worldIn.setBlockToAir(pos.add(p,q,r));
                                worldIn.playSound((EntityPlayer)null, pos.add(p,q,r), SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
                                worldIn.spawnParticle(EnumParticleTypes.SWEEP_ATTACK,(double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, 0.0D, 0.2D, 0.0D);
                                worldIn.spawnParticle(EnumParticleTypes.SWEEP_ATTACK, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, 0.2D, 0D, 0.0D);
                                worldIn.spawnParticle(EnumParticleTypes.SWEEP_ATTACK, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, -0.2D, 0D, 0.0D);
                                worldIn.spawnParticle(EnumParticleTypes.SWEEP_ATTACK, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, 0D, 0D, 0.2D);
                                worldIn.spawnParticle(EnumParticleTypes.SWEEP_ATTACK, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, 0.0D, 0D, -0.2D);
                                worldIn.spawnParticle(EnumParticleTypes.SWEEP_ATTACK, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, 0.2D, 0D, 0.2D);
                                worldIn.spawnParticle(EnumParticleTypes.SWEEP_ATTACK, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, -0.2D, 0D, 0.2D);
                                worldIn.spawnParticle(EnumParticleTypes.SWEEP_ATTACK, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, 0.2D, 0D, -0.2D);
                                worldIn.spawnParticle(EnumParticleTypes.SWEEP_ATTACK, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, -0.2D, 0D, -0.2D);
                                worldIn.spawnParticle(EnumParticleTypes.SWEEP_ATTACK, (double) pos.add(p,q,r).getX()+0.5D, (double) pos.add(p,q,r).getY()+0.5D, (double) pos.add(p,q,r).getZ()+0.5D, 0D, -0.2D, 0.0D);

                            }

                        }
                    }
                }

            }
            }
        }





    }

    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BLOCK;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);

      /*  if(!isUsing){
            isUsing=true;
            playerIn.setActiveHand(handIn);
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
        }else{
            return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);

        }*/
        worldIn.playSound( playerIn.posX, playerIn.posY,  playerIn.posZ, SoundEvents.ITEM_ELYTRA_FLYING, SoundCategory.PLAYERS, 1.0F, 1F, false);

        itemstack.damageItem(1,  playerIn);
        playerIn.setActiveHand(handIn);
        playerIn.getCooldownTracker().setCooldown(this, 30);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }

   /* @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
        if(isUsing) {
            isUsing = false;
        }
    }*/

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 20;
    }





}

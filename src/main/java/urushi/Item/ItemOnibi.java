package urushi.Item;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import urushi.Entity.EntityOni;
import urushi.Entity.EntityOnibi;
import urushi.ModCore_Urushi;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ItemOnibi extends Item {
    public ItemOnibi() {
        super();
        this.setRegistryName(ModCore_Urushi.modid, "onibi_item");
        this.setCreativeTab(ModCore_Urushi.TabUrushi);
        this.setUnlocalizedName("Onibi");
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

            if (entityIn instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) entityIn;
                BlockPos pos=new BlockPos(Math.floor(entityIn.posX),Math.floor(entityIn.posY),Math.floor(entityIn.posZ));

                if (player.getHeldItemMainhand() == stack || player.getHeldItemOffhand() == stack) {
                    for(int i=-ModCore_Urushi.range_of_onibi; i<ModCore_Urushi.range_of_onibi+1;i++) {
                        for(int j=-ModCore_Urushi.range_of_onibi; j<ModCore_Urushi.range_of_onibi+1;j++) {
                            for(int k=-ModCore_Urushi.range_of_onibi; k<ModCore_Urushi.range_of_onibi+1;k++) {
                               if( worldIn.getBlockState(pos.add(i,j,k))== ModCore_Urushi.Onibi.getDefaultState()){
                                   Random rand=new Random();
                                   double d0 = (double)pos.getX()+(double)i + 0.1D*rand.nextInt(16);
                                   double d1 = (double)pos.getY() +(double)j+ 0.1D*rand.nextInt(16);
                                   double d2 = (double)pos.getZ() +(double)k+ 0.1D*rand.nextInt(16);
                                   if(rand.nextInt(8)==0) {
                                       worldIn.spawnParticle(EnumParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
                          //         }else if(rand.nextInt(16)==1){
                         ///              worldIn.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, d0, d1, d2, 0.0D, 0.0D, 0.0D);
//
                                   }
                               }
                            }
                        }
                    }
                }
            }


    }
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote)
        {
            return EnumActionResult.SUCCESS;
        }
        else
        {
            pos = pos.offset(facing);
            ItemStack itemstack = player.getHeldItem(hand);

            if (!player.canPlayerEdit(pos, facing, itemstack))
            {
                return EnumActionResult.FAIL;
            }
            else
            {
                if (worldIn.getBlockState(pos).getMaterial() == Material.AIR)
                {
                    worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.BLOCKS, 1.0F, (itemRand.nextFloat() - itemRand.nextFloat()) * 0.2F + 1.0F);
                    worldIn.setBlockState(pos, ModCore_Urushi.Onibi.getDefaultState());
                }

                if (!player.capabilities.isCreativeMode)
                {
                    itemstack.shrink(1);
                }

                return EnumActionResult.SUCCESS;
            }
        }
    }
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        ITextComponent textComponent=new TextComponentTranslation("item.info.onibi", new Object[0]);
            tooltip.add(textComponent.getFormattedText());
    }
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {

        ItemStack itemstack = playerIn.getHeldItem(handIn);

        if (!playerIn.capabilities.isCreativeMode)
        {
            itemstack.shrink(1);
        }

        worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.NEUTRAL, 1.0F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!worldIn.isRemote)
        {
            EntityOnibi entitysnowball = new EntityOnibi(worldIn, playerIn);
            entitysnowball.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.spawnEntity(entitysnowball);
        }

        playerIn.addStat(StatList.getObjectUseStats(this));
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }


}

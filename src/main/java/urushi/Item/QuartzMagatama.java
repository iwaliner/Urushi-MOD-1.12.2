package urushi.Item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.ITeleporter;
import urushi.ModCore_Urushi;
import urushi.TileEntity.TileEntityRiceHokora;
import urushi.WorldGen.TeleporterBuildingGenKakuriyo;
import urushi.WorldGen.TeleporterGenKakuriyo;

import javax.annotation.Nullable;
import java.util.List;

public class QuartzMagatama extends Item {

    public QuartzMagatama() {
        //super();
        this.setRegistryName(ModCore_Urushi.modid, "quartz_magatama");
        //this.setCreativeTab(ModCore_Urushi.TabUrushi);
        this.setUnlocalizedName("QuartzMagatama");
        this.setMaxDamage(5000);

   this.setMaxStackSize(1);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        int preDamage= player.getHeldItem(hand).getItemDamage();
        ItemStack itemstack = player.getHeldItem(hand);
        double xadd=0D; double zadd=0D;
        if(facing==EnumFacing.NORTH){zadd=-1D;}
        else if(facing==EnumFacing.SOUTH){zadd=1D;}
        else if(facing==EnumFacing.EAST){xadd=1D;}
        else if(facing==EnumFacing.WEST){xadd=-1D;}
            if (worldIn.getBlockState(pos).getBlock() == ModCore_Urushi.RiceHokora) {
                TileEntityRiceHokora tileEntityRiceHokora = (TileEntityRiceHokora) worldIn.getTileEntity(pos);

               // itemstack.setItemDamage(preDamage + tileEntityRiceHokora.manaStored);
              //  itemstack.damageItem(tileEntityRiceHokora.manaStored, player);
                if(preDamage-tileEntityRiceHokora.manaStored>=0) {
                    player.setHeldItem(hand, new ItemStack(this, 1, preDamage - tileEntityRiceHokora.manaStored));
                    tileEntityRiceHokora.manaStored = 0;

                }else{
                    player.setHeldItem(hand, new ItemStack(this, 1, 0));
                       tileEntityRiceHokora.manaStored-=preDamage;

                }
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
                worldIn.playSound(player, pos, SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.PLAYERS, 1F, 1.0F);

                return  EnumActionResult.SUCCESS;

        }



        return EnumActionResult.FAIL;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        ITextComponent textComponent0 = new TextComponentTranslation("item.info.magatama1", new Object[0]);
        ITextComponent textComponent1 = new TextComponentTranslation("item.info.magatama2", new Object[0]);
        tooltip.add(textComponent0.getFormattedText()+" : "+(5000-stack.getItemDamage()));
        tooltip.add(textComponent1.getFormattedText());

    }



}

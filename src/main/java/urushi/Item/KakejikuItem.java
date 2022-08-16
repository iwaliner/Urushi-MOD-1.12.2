package urushi.Item;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import urushi.Else.EnumType;
import urushi.ModCore_Urushi;

import javax.annotation.Nullable;
import java.util.List;

public class KakejikuItem extends Item {
    protected static final AxisAlignedBB AABB1 = new AxisAlignedBB(0D, 0D, 0D, 1D, 1D, 1D);

    public KakejikuItem() {
        super();
        this.setRegistryName(ModCore_Urushi.modid, "kakejiku");
        this.setCreativeTab(ModCore_Urushi.TabUrushi);
        this.setUnlocalizedName("Kakejiku");
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (this.isInCreativeTab(tab))
        {
            for(int i=0;i<15;i++) {
                items.add(new ItemStack(this, 1, i));


            }
        }
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemStack=player.getHeldItem(hand);
        PropertyDirection FACING = BlockHorizontal.FACING;
      PropertyEnum<EnumType.EnumType4> VARIANT = PropertyEnum.<EnumType.EnumType4>create("variant", EnumType.EnumType4.class);
        int meta=itemStack.getItemDamage();
        double xadd=0D; double zadd=0D;double yadd=0D;
        if(facing==EnumFacing.NORTH){zadd=-1D;}
        else if(facing==EnumFacing.SOUTH){zadd=1D;}
        else if(facing==EnumFacing.EAST){xadd=1D;}
        else if(facing==EnumFacing.WEST){xadd=-1D;}
        else if(facing==EnumFacing.UP){yadd=1D;}
        else if(facing==EnumFacing.DOWN){yadd=-1D;}
        BlockPos newPos=pos.add(xadd,yadd,zadd);
        if(player.isSneaking()&&!worldIn.isRemote) {
            if (facing != EnumFacing.UP && facing != EnumFacing.DOWN) {
                if (meta == 0) {
                    worldIn.setBlockState(newPos, ModCore_Urushi.KakejikuA.getDefaultState().withProperty(VARIANT,EnumType.EnumType4.TypeA).withProperty(FACING, facing.getOpposite()));
                    itemStack.shrink(1);
                    return EnumActionResult.SUCCESS;
                }else if (meta == 1) {
                    worldIn.setBlockState(newPos, ModCore_Urushi.KakejikuA.getDefaultState().withProperty(VARIANT,EnumType.EnumType4.TypeB).withProperty(FACING, facing.getOpposite()));
                    itemStack.shrink(1);

                    return EnumActionResult.SUCCESS;
                }else if (meta ==2) {
                    worldIn.setBlockState(newPos, ModCore_Urushi.KakejikuA.getDefaultState().withProperty(VARIANT,EnumType.EnumType4.TypeC).withProperty(FACING, facing.getOpposite()));
                    itemStack.shrink(1);

                    return EnumActionResult.SUCCESS;
                }else if (meta == 3) {
                    worldIn.setBlockState(newPos, ModCore_Urushi.KakejikuA.getDefaultState().withProperty(VARIANT,EnumType.EnumType4.TypeD).withProperty(FACING, facing.getOpposite()));
                    itemStack.shrink(1);

                    return EnumActionResult.SUCCESS;
                }else if (meta == 4) {
                    worldIn.setBlockState(newPos, ModCore_Urushi.KakejikuB.getDefaultState().withProperty(VARIANT,EnumType.EnumType4.TypeA).withProperty(FACING, facing.getOpposite()));
                    itemStack.shrink(1);

                    return EnumActionResult.SUCCESS;
                }else if (meta == 5) {
                    worldIn.setBlockState(newPos, ModCore_Urushi.KakejikuB.getDefaultState().withProperty(VARIANT,EnumType.EnumType4.TypeB).withProperty(FACING, facing.getOpposite()));
                    itemStack.shrink(1);

                    return EnumActionResult.SUCCESS;
                }else if (meta == 6) {
                    worldIn.setBlockState(newPos, ModCore_Urushi.KakejikuB.getDefaultState().withProperty(VARIANT,EnumType.EnumType4.TypeC).withProperty(FACING, facing.getOpposite()));
                    itemStack.shrink(1);

                    return EnumActionResult.SUCCESS;
                }else if (meta == 7) {
                    worldIn.setBlockState(newPos, ModCore_Urushi.KakejikuB.getDefaultState().withProperty(VARIANT,EnumType.EnumType4.TypeD).withProperty(FACING, facing.getOpposite()));
                    itemStack.shrink(1);

                    return EnumActionResult.SUCCESS;
                }else if (meta == 8) {
                    worldIn.setBlockState(newPos, ModCore_Urushi.KakejikuC.getDefaultState().withProperty(VARIANT,EnumType.EnumType4.TypeA).withProperty(FACING, facing.getOpposite()));
                    itemStack.shrink(1);

                    return EnumActionResult.SUCCESS;
                }else if (meta == 9) {
                    worldIn.setBlockState(newPos, ModCore_Urushi.KakejikuC.getDefaultState().withProperty(VARIANT,EnumType.EnumType4.TypeB).withProperty(FACING, facing.getOpposite()));
                    itemStack.shrink(1);

                    return EnumActionResult.SUCCESS;
                }else if (meta == 10) {
                    worldIn.setBlockState(newPos, ModCore_Urushi.KakejikuC.getDefaultState().withProperty(VARIANT,EnumType.EnumType4.TypeC).withProperty(FACING, facing.getOpposite()));
                    itemStack.shrink(1);

                    return EnumActionResult.SUCCESS;
                }else if (meta == 11) {
                    worldIn.setBlockState(newPos, ModCore_Urushi.KakejikuC.getDefaultState().withProperty(VARIANT,EnumType.EnumType4.TypeD).withProperty(FACING, facing.getOpposite()));
                    itemStack.shrink(1);

                    return EnumActionResult.SUCCESS;
                }else if (meta == 12) {
                    worldIn.setBlockState(newPos, ModCore_Urushi.KakejikuD.getDefaultState().withProperty(VARIANT,EnumType.EnumType4.TypeA).withProperty(FACING, facing.getOpposite()));
                       itemStack.shrink(1);
                    return EnumActionResult.SUCCESS;
                }else if (meta == 13) {
                    worldIn.setBlockState(newPos, ModCore_Urushi.KakejikuD.getDefaultState().withProperty(VARIANT,EnumType.EnumType4.TypeB).withProperty(FACING, facing.getOpposite()));
                    itemStack.shrink(1);

                    return EnumActionResult.SUCCESS;
                }else if (meta == 14) {
                    worldIn.setBlockState(newPos, ModCore_Urushi.KakejikuD.getDefaultState().withProperty(VARIANT,EnumType.EnumType4.TypeC).withProperty(FACING, facing.getOpposite()));
                    itemStack.shrink(1);

                    return EnumActionResult.SUCCESS;
                }
            }
        }
        return EnumActionResult.FAIL;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand) {
        ItemStack itemStack=player.getHeldItem(hand);
        int meta=itemStack.getItemDamage();
        int amount=itemStack.getCount();
        if(!player.isSneaking()) {
            if (meta != 14) {
                player.setHeldItem(hand, new ItemStack(this, amount, meta + 1));
                return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStack);
            } else {
                player.setHeldItem(hand, new ItemStack(this, amount, 0));
                return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStack);
            }
        }
        return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemStack);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        ITextComponent textComponent6 = new TextComponentTranslation("item.info.kakejiku", new Object[0]);
        tooltip.add(textComponent6.getFormattedText());

    }


}

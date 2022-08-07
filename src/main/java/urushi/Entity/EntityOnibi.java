package urushi.Entity;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import urushi.ModCore_Urushi;

public class EntityOnibi extends EntityThrowable  {
    public EntityOnibi(World worldIn) {
        super(worldIn);
        //this.noClip=true;
    }
    public EntityOnibi(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntityOnibi(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }
    public static void registerFixesSnowball(DataFixer fixer)
    {
        EntityThrowable.registerFixesThrowable(fixer, "Snowball");
    }

    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        if (id == 3)
        {
            for (int i = 0; i < 8; ++i)
            {
                this.world.spawnParticle(EnumParticleTypes.SNOWBALL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
            }
        }
    }
    @Override
    public void onImpact(RayTraceResult result) {
        double xadd = 0D;
        double zadd = 0D;
        double yadd = 0D;
        if (result.sideHit == EnumFacing.NORTH) {
            zadd = -1D;
        } else if (result.sideHit == EnumFacing.SOUTH) {
            zadd = 1D;
        } else if (result.sideHit == EnumFacing.EAST) {
            xadd = 1D;
        } else if (result.sideHit == EnumFacing.WEST) {
            xadd = -1D;
        } else if (result.sideHit == EnumFacing.UP) {
            yadd = 1D;
        } else if (result.sideHit == EnumFacing.DOWN) {
            yadd = -1D;
        }
        //BlockPos newPos = result.getBlockPos().add(xadd, yadd, zadd);
        BlockPos newPos = new BlockPos(this).add(xadd, yadd, zadd);
        if (
                !this.world.isRemote  &&
                       this.world.isBlockLoaded(new BlockPos(this))
                &&result.entityHit==null
       && result.typeOfHit == RayTraceResult.Type.BLOCK
        ) {

          //  if (!this.world.isRemote)
          //  {
            // this.setDead();

          //  }
            if(world.getBlockState(newPos).getMaterial()== Material.AIR) {
                world.setBlockState(newPos, ModCore_Urushi.Onibi.getDefaultState());
                world.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, newPos.getX(), newPos.getY(), newPos.getZ(), 0.0D, 0.0D, 0.0D);

            }else{
                EntityItem entityItem=new EntityItem(world,newPos.getX()+0.5D,newPos.getY()+0.5D,newPos.getZ()+0.5D);
           entityItem.setItem(new ItemStack(ModCore_Urushi.ItemOnibi));
                world.spawnEntity(entityItem);
            }
            this.setDead();
        }else if(result.typeOfHit == RayTraceResult.Type.ENTITY&&result.entityHit!=null){
            if(result.entityHit instanceof EntityMob){
                EntityLivingBase entityLivingBase= (EntityLivingBase) result.entityHit;
                entityLivingBase.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 400, 1));

            }else{
                if (!this.world.isRemote) {
                    EntityItem entityItem=new EntityItem(world,newPos.getX()+0.5D,newPos.getY()+0.5D,newPos.getZ()+0.5D);
                    entityItem.setItem(new ItemStack(ModCore_Urushi.ItemOnibi));
                    world.spawnEntity(entityItem);
                    this.setDead();

                }
            }

        }

        else {
            if (!this.world.isRemote) {
                EntityItem entityItem=new EntityItem(world,newPos.getX()+0.5D,newPos.getY()+0.5D,newPos.getZ()+0.5D);
                entityItem.setItem(new ItemStack(ModCore_Urushi.ItemOnibi));
                world.spawnEntity(entityItem);
                this.setDead();

            }
        }
    }

}

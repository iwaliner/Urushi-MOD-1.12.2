package urushi.Entity;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import urushi.ModCore_Urushi;

import javax.annotation.Nullable;
import java.util.List;

public class EntityCushion extends Entity {
    private static final DataParameter<Integer> CUSHION_COLOR_TYPE = EntityDataManager.<Integer>createKey(EntityCushion.class, DataSerializers.VARINT);

    public EntityCushion(World worldIn) {
        super(worldIn);
        this.setSize(0.0625F*12, 0.0625F*3);
        //this.preventEntitySpawning = true;
       // this.motionY = -0.1D;
        //this.setPosition(this.posX, this.posY + (double)((1.0F - this.height) / 2.0F), this.posZ);
    }
    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        compound.setString("Color", this.getColorType().getName());
    }
    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        if (compound.hasKey("Color", 8))
        {
            this.setColorType(EntityCushion.Type.getTypeFromString(compound.getString("Color")));
        }
    }
    @Override
    protected void entityInit() {
        this.dataManager.register(CUSHION_COLOR_TYPE, Integer.valueOf(EntityCushion.Type.WHITE.ordinal()));

    }
    public EntityCushion(World worldIn, double x, double y, double z)
    {
        this(worldIn);
        this.setPosition(x, y, z);
        this.motionX = 0.0D;
        this.motionY = -0.1D;
        this.motionZ = 0.0D;
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
    }
    protected boolean canTriggerWalking()
    {
        return false;
    }
    @Nullable
    public AxisAlignedBB getCollisionBox(Entity entityIn)
    {
        return  entityIn.getEntityBoundingBox() ;
       // return null;
    }
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox()
    {
       // return null;
        return this.getEntityBoundingBox();
    }
    public boolean canBePushed()
    {
        return false;
    }
    public double getMountedYOffset()
   {
        return -0.05D;
   }
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else if (!this.world.isRemote && !this.isDead)
        {
            if (source instanceof EntityDamageSourceIndirect && source.getTrueSource() != null && this.isPassenger(source.getTrueSource()))
            {
                return false;
            }
            else
            {

                    this.setDead();


                return true;
            }
        }
        else
        {
            return true;
        }
    }
    public void applyEntityCollision(Entity entityIn)
    {
        if (entityIn ==this)
        {
            if (entityIn.getEntityBoundingBox().minY < this.getEntityBoundingBox().maxY)
            {
                super.applyEntityCollision(entityIn);
            }
        }
        else if (entityIn.getEntityBoundingBox().minY <= this.getEntityBoundingBox().minY)
        {
            super.applyEntityCollision(entityIn);
        }
    }
    @Override
    public void onUpdate() {
      //this.motionY-=1D;
        List<Entity> list = this.world.getEntitiesInAABBexcluding(this,  this.getEntityBoundingBox().grow(0.1D,0.1D,0.1D), EntitySelectors.getTeamCollisionPredicate(this));

        if (!list.isEmpty())
        {
            for (int j1 = 0; j1 < list.size(); ++j1)
            {
                Entity entity1 = list.get(j1);

                if (!(entity1 instanceof EntityPlayer)&&!(entity1 instanceof EntityCushion) && !this.isBeingRidden() && !entity1.isRiding()){
                    entity1.startRiding(this);
                    world.playSound((EntityPlayer)null, new BlockPos(posX,posY,posZ), SoundEvents.BLOCK_CLOTH_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);

                }
               else{entity1.applyEntityCollision(this);}
            }
        }

    }

    @Override
    public boolean canRiderInteract() {
        return true;
    }

    public boolean canBeCollidedWith()
    {
        return true;
    }
    public void setColorType(EntityCushion.Type boatType)
    {
        this.dataManager.set(CUSHION_COLOR_TYPE, Integer.valueOf(boatType.ordinal()));
    }


    public EntityCushion.Type getColorType()
    {
        return EntityCushion.Type.byId(((Integer)this.dataManager.get(CUSHION_COLOR_TYPE)).intValue());
    }
    @Nullable
    public Entity getControllingPassenger()
    {
        return this.getPassengers().isEmpty() ? null : (Entity)this.getPassengers().get(0);
    }


    @Override
    public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
        if (!this.world.isRemote&&!player.isRiding())
        {
            player.startRiding(this);
            world.playSound((EntityPlayer)null, new BlockPos(posX,posY,posZ), SoundEvents.BLOCK_CLOTH_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);

            return  true;
        }
        return true;
    }

    @Override
    public void onRemovedFromWorld() {
        super.onRemovedFromWorld();
        if(!world.isRemote) {
            EntityItem entityItem = new EntityItem(world, posX, posY, posZ, new ItemStack(ModCore_Urushi.UItems, 1, ((Integer)this.dataManager.get(CUSHION_COLOR_TYPE)).intValue()+14));
            world.spawnEntity(entityItem);
            world.playSound((EntityPlayer)null, new BlockPos(posX,posY,posZ), SoundEvents.BLOCK_CLOTH_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);

        }
    }

    @Override
    protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos) {
         this.motionY=-0.1D;
    }

    public static enum Type
    {
        WHITE(EnumDyeColor.WHITE.getMetadata(), "white"),
        ORANGE(EnumDyeColor.ORANGE.getMetadata(), "orange"),
        MAGENTA(EnumDyeColor.MAGENTA.getMetadata(), "magenta"),
        LIGHT_BLUE(EnumDyeColor.LIGHT_BLUE.getMetadata(), "light_lue"),
        YELLOW(EnumDyeColor.YELLOW.getMetadata(), "yellow"),
        LIME(EnumDyeColor.LIME.getMetadata(), "lime"),
        PINK(EnumDyeColor.PINK.getMetadata(), "pink"),
        GRAY(EnumDyeColor.GRAY.getMetadata(), "gray"),
        SILVER(EnumDyeColor.SILVER.getMetadata(), "silver"),
        CYAN(EnumDyeColor.CYAN.getMetadata(), "cyan"),
        PURPLE(EnumDyeColor.PURPLE.getMetadata(), "purple"),
        BLUE(EnumDyeColor.BLUE.getMetadata(), "blue"),
        BROUN(EnumDyeColor.BROWN.getMetadata(), "brown"),
        GREEN(EnumDyeColor.GREEN.getMetadata(), "green"),
        RED(EnumDyeColor.RED.getMetadata(), "red"),
        BLACK(EnumDyeColor.BLACK.getMetadata(), "black");

        private final String name;
        private final int metadata;

        private Type(int metadataIn, String nameIn)
        {
            this.name = nameIn;
            this.metadata = metadataIn;
        }

        public String getName()
        {
            return this.name;
        }

        public int getMetadata()
        {
            return this.metadata;
        }

        public String toString()
        {
            return this.name;
        }

        /**
         * Get a boat type by it's enum ordinal
         */
        public static EntityCushion.Type byId(int id)
        {
            if (id < 0 || id >= values().length)
            {
                id = 0;
            }

            return values()[id];
        }

        public static EntityCushion.Type getTypeFromString(String nameIn)
        {
            for (int i = 0; i < values().length; ++i)
            {
                if (values()[i].getName().equals(nameIn))
                {
                    return values()[i];
                }
            }

            return values()[0];
        }
    }

}

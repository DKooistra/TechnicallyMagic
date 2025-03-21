package net.technicallyMagic.entity.custom;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.technicallyMagic.entity.ModEntities;
import net.technicallyMagic.item.ModItems;
import net.technicallyMagic.particle.custom.TestParticles;

public class ProjectileEntity extends ThrowableItemProjectile {

    public ProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ProjectileEntity(Level pLevel) {
        super(ModEntities.KILL_PROJECTILE.get(), pLevel);
    }

    public ProjectileEntity(Level pLevel, LivingEntity livingEntity) {
        super(ModEntities.KILL_PROJECTILE.get(), livingEntity, pLevel);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.KILL_PROJECTILE.get();
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        if(!this.level().isClientSide()) {
            Entity hitEntity = pResult.getEntity();
            this.level().broadcastEntityEvent(this, ((byte) 3));
            hitEntity.kill();

        }

        super.onHitEntity(pResult);
    }

    @Override
    public void tick() {

        if (this.level().isClientSide()) {
            TestParticles.summonTestParticle(this.level(), this.getX(), this.getY(), this.getZ());
        }

        super.tick();
    }
}


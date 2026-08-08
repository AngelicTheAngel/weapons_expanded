package net.angelic.weaponsexpanded.entity.projectile;

import net.angelic.weaponsexpanded.entity.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class ExplosiveArrowEntity extends ArrowEntity {
    private static final float EXPLOSION_POWER = 2f;

    private ItemStack weaponsexpanded$pickupStack = ItemStack.EMPTY;

    private boolean weaponsexpanded$exploded = false;

    public ExplosiveArrowEntity(EntityType<? extends ArrowEntity> type, World world) {
        super(type, world);
    }

    public ExplosiveArrowEntity(World world, LivingEntity owner, ItemStack pickupStack) {
        this(ModEntities.EXPLOSIVE_ARROW, world);

        this.setOwner(owner);
        this.setPosition(owner.getX(), owner.getEyeY() - 0.1, owner.getZ());

        ItemStack one = pickupStack.copy();
        one.setCount(1);
        this.weaponsexpanded$pickupStack = one;
    }

    @Override
    protected ItemStack asItemStack() {
        return this.weaponsexpanded$pickupStack.isEmpty()
                ? super.asItemStack()
                : this.weaponsexpanded$pickupStack.copy();
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        this.weaponsexpanded$explode();
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        this.weaponsexpanded$explode();
    }

    private void weaponsexpanded$explode() {
        if (this.getEntityWorld().isClient()) return;

        // Use our own guard; vanilla may discard the arrow during super.onEntityHit(...)
        if (this.weaponsexpanded$exploded) return;
        this.weaponsexpanded$exploded = true;

        World world = this.getEntityWorld();

        world.createExplosion(
                this,
                this.getX(), this.getY(), this.getZ(),
                EXPLOSION_POWER,
                false,
                World.ExplosionSourceType.TNT
        );

        this.discard();
    }
}
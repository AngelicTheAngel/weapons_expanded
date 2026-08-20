package net.angelic.weaponsexpanded.entity.projectile;

import net.angelic.weaponsexpanded.Config;
import net.angelic.weaponsexpanded.entity.ModEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class ExplosiveArrowEntity extends Arrow {
    private static final float EXPLOSION_POWER = 2f;

    private ItemStack weaponsexpanded$pickupStack = ItemStack.EMPTY;

    private boolean weaponsexpanded$exploded = false;

    public ExplosiveArrowEntity(EntityType<? extends Arrow> type, Level world) {
        super(type, world);
    }

    public ExplosiveArrowEntity(Level world, LivingEntity owner, ItemStack pickupStack) {
        this(ModEntities.EXPLOSIVE_ARROW.get(), world);

        this.setOwner(owner);
        this.setPos(owner.getX(), owner.getEyeY() - 0.1, owner.getZ());

        ItemStack one = pickupStack.copy();
        one.setCount(1);
        this.weaponsexpanded$pickupStack = one;
    }

    @Override
    protected ItemStack getPickupItem() {
        return this.weaponsexpanded$pickupStack.isEmpty()
                ? super.getPickupItem()
                : this.weaponsexpanded$pickupStack.copy();
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        this.weaponsexpanded$explode();
    }

    @Override
    protected void onHitBlock(BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);
        this.weaponsexpanded$explode();
    }

    private void weaponsexpanded$explode() {
        if (this.level().isClientSide()) return;

        if (this.weaponsexpanded$exploded) return;
        this.weaponsexpanded$exploded = true;

        Level world = this.level();
        Level.ExplosionInteraction explosionType;

        if (Config.dynamiteArrow) {
            explosionType = Level.ExplosionInteraction.TNT;
        } else {
            explosionType = Level.ExplosionInteraction.NONE;
        }

        world.explode(
                this,
                this.getX(), this.getY(), this.getZ(),
                EXPLOSION_POWER,
                false,
                explosionType
        );

        this.discard();
    }
}

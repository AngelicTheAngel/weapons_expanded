package net.angelic.weaponsexpanded.entity.projectile;

import net.angelic.weaponsexpanded.entity.ModEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class ExplosiveArrowEntity extends Arrow {
    private static final float EXPLOSION_POWER = 2.0F;

    private boolean weaponsexpanded$exploded;

    public ExplosiveArrowEntity(
            EntityType<? extends Arrow> type,
            Level level
    ) {
        super(type, level);
    }

    public ExplosiveArrowEntity(
            Level level,
            LivingEntity owner,
            ItemStack pickupStack,
            ItemStack weaponStack
    ) {
        this(
                ModEntities.EXPLOSIVE_ARROW.get(),
                level
        );

        this.setOwner(owner);
        this.setPos(
                owner.getX(),
                owner.getEyeY() - 0.1D,
                owner.getZ()
        );

        ItemStack singleArrow = pickupStack.copy();
        singleArrow.setCount(1);

        this.setPickupItemStack(singleArrow);
    }

    @Override
    protected void onHitEntity(
            EntityHitResult hitResult
    ) {
        super.onHitEntity(hitResult);
        this.weaponsexpanded$explode();
    }

    @Override
    protected void onHitBlock(
            BlockHitResult hitResult
    ) {
        super.onHitBlock(hitResult);
        this.weaponsexpanded$explode();
    }

    private void weaponsexpanded$explode() {
        if (this.level().isClientSide()) {
            return;
        }

        /*
         * Use a separate guard because vanilla may discard the arrow
         * during super.onHitEntity().
         */
        if (this.weaponsexpanded$exploded) {
            return;
        }

        this.weaponsexpanded$exploded = true;

        this.level().explode(
                this,
                this.getX(),
                this.getY(),
                this.getZ(),
                EXPLOSION_POWER,
                false,
                Level.ExplosionInteraction.TNT
        );

        this.discard();
    }
}
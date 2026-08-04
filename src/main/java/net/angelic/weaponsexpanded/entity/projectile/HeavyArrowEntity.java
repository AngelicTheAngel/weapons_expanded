package net.angelic.weaponsexpanded.entity.projectile;

import net.angelic.weaponsexpanded.entity.ModEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class HeavyArrowEntity extends Arrow {
    public static final double BASE_DAMAGE = 3.6D;

    private static final double EXTRA_AIR_DRAG = 0.9D;
    private static final double GRAVITY = 0.1D;
    private static final double VANILLA_ARROW_GRAVITY = 0.05D;

    private ItemStack weaponsexpanded$pickupStack =
            ItemStack.EMPTY;

    public HeavyArrowEntity(
            EntityType<? extends Arrow> type,
            Level level
    ) {
        super(type, level);
        this.setBaseDamage(BASE_DAMAGE);
    }

    public HeavyArrowEntity(
            Level level,
            LivingEntity owner,
            ItemStack pickupItemStack,
            ItemStack weaponStack
    ) {
        this(
                ModEntities.HEAVY_ARROW.get(),
                level
        );

        this.setOwner(owner);
        this.setPos(
                owner.getX(),
                owner.getEyeY() - 0.1D,
                owner.getZ()
        );

        this.setBaseDamage(BASE_DAMAGE);

        this.weaponsexpanded$pickupStack =
                pickupItemStack.copy();
    }

    public void weaponsexpanded$setPunchLevel(int level) {
        this.setKnockback(Math.max(0, level));
    }

    @Override
    protected ItemStack getPickupItem() {
        return this.weaponsexpanded$pickupStack.isEmpty()
                ? super.getPickupItem()
                : this.weaponsexpanded$pickupStack.copy();
    }

    @Override
    public void tick() {
        super.tick();

        /*
         * In Minecraft 1.20.1, inGround remains a protected field.
         */
        if (!this.inGround) {
            Vec3 velocity = this.getDeltaMovement();

            /*
             * Vanilla has already applied 0.05 gravity. Apply only
             * the difference needed to reach 0.1 gravity.
             */
            if (!this.isNoGravity()) {
                velocity = velocity.add(
                        0.0D,
                        -(GRAVITY - VANILLA_ARROW_GRAVITY),
                        0.0D
                );
            }

            this.setDeltaMovement(
                    velocity.scale(EXTRA_AIR_DRAG)
            );
        }
    }
}
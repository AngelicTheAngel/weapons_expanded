package net.angelic.weaponsexpanded.entity.projectile;

import net.angelic.weaponsexpanded.entity.ModEntities;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class HeavyArrowEntity extends Arrow {
    public static final double BASE_DAMAGE = 3.6D;

    private static final double EXTRA_AIR_DRAG = 0.9D;
    private static final double GRAVITY = 0.1D;
    private int weaponsexpanded$punchLevel;

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

        ItemStack singleArrow = pickupItemStack.copy();
        singleArrow.setCount(1);
        this.setPickupItemStack(singleArrow);
    }

    public void weaponsexpanded$setPunchLevel(int level) {
        this.weaponsexpanded$punchLevel = Math.max(0, level);
    }

    @Override
    protected void doKnockback(
            LivingEntity target,
            DamageSource damageSource
    ) {
        super.doKnockback(target, damageSource);

        if (this.weaponsexpanded$punchLevel > 0) {
            target.knockback(
                    this.weaponsexpanded$punchLevel * 0.6D,
                    this.getX() - target.getX(),
                    this.getZ() - target.getZ()
            );
        }
    }

    @Override
    protected double getDefaultGravity() {
        return GRAVITY;
    }

    @Override
    public void tick() {
        super.tick();

        // inGround is still protected in Minecraft 1.21.1.
        if (!this.isInGround()) {
            Vec3 velocity = this.getDeltaMovement();

            this.setDeltaMovement(
                    velocity.scale(EXTRA_AIR_DRAG)
            );
        }
    }
}
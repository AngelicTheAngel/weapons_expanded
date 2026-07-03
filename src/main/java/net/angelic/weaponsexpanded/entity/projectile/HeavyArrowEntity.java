package net.angelic.weaponsexpanded.entity.projectile;

import net.angelic.weaponsexpanded.entity.ModEntities;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class HeavyArrowEntity extends Arrow {
    public static final double BASE_DAMAGE = 3.6;
    private static final float EXTRA_AIR_DRAG = 0.9f;
    private static final float GRAVITY = 0.1f;

    private ItemStack weaponsexpanded$pickupStack = ItemStack.EMPTY;

    // Stores Punch level applied by helper (since PersistentProjectileEntity doesn't expose setPunch here)
    private int weaponsexpanded$punchLevel = 0;

    public HeavyArrowEntity(EntityType<? extends Arrow> type, Level world) {
        super(type, world);
        this.setBaseDamage(BASE_DAMAGE);
    }

    public HeavyArrowEntity(Level world, LivingEntity owner, ItemStack pickupItemStack, ItemStack weaponStack) {
        this(ModEntities.HEAVY_ARROW.get(), world);

        this.setOwner(owner);
        this.setPos(owner.getX(), owner.getEyeY() - 0.1, owner.getZ());
        this.setBaseDamage(BASE_DAMAGE);

        this.weaponsexpanded$pickupStack = pickupItemStack.copy();
    }

    public void weaponsexpanded$setPunchLevel(int level) {
        this.weaponsexpanded$punchLevel = Math.max(0, level);
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

        if (!this.isInGround()) {
            this.setDeltaMovement(this.getDeltaMovement().scale(EXTRA_AIR_DRAG));
        }
    }

    @Override
    protected double getDefaultGravity() {
        return GRAVITY;
    }

    @Override
    protected void doKnockback(LivingEntity target, DamageSource source) {
        super.doKnockback(target, source);

        if (this.weaponsexpanded$punchLevel <= 0) return;

        // Vanilla-ish punch: add extra horizontal knockback, respecting knockback resistance.
        double resistance = target.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE);
        double e = Math.max(0.0, 1.0 - resistance);

        Vec3 horiz = this.getDeltaMovement().multiply(1.0, 0.0, 1.0);
        if (horiz.lengthSqr() <= 0.0) return;

        Vec3 extra = horiz.normalize().scale(this.weaponsexpanded$punchLevel * 0.6 * e);
        target.push(extra.x, 0.1, extra.z);
    }
}

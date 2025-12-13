package net.angelic.weaponsexpanded.entity.projectile;

import net.angelic.weaponsexpanded.entity.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class HeavyArrowEntity extends ArrowEntity {
    public static final double BASE_DAMAGE = 5.0;
    private static final float EXTRA_AIR_DRAG = 0.85f;

    private ItemStack weaponsexpanded$pickupStack = ItemStack.EMPTY;

    // Stores Punch level applied by helper (since PersistentProjectileEntity doesn't expose setPunch here)
    private int weaponsexpanded$punchLevel = 0;

    public HeavyArrowEntity(EntityType<? extends ArrowEntity> type, World world) {
        super(type, world);
        this.setDamage(BASE_DAMAGE);
    }

    public HeavyArrowEntity(World world, LivingEntity owner, ItemStack pickupItemStack, ItemStack weaponStack) {
        this(ModEntities.HEAVY_ARROW, world);

        this.setOwner(owner);
        this.setPosition(owner.getX(), owner.getEyeY() - 0.1, owner.getZ());
        this.setDamage(BASE_DAMAGE);

        this.weaponsexpanded$pickupStack = pickupItemStack.copy();
    }

    public void weaponsexpanded$setPunchLevel(int level) {
        this.weaponsexpanded$punchLevel = Math.max(0, level);
    }

    @Override
    protected ItemStack asItemStack() {
        return this.weaponsexpanded$pickupStack.isEmpty()
                ? super.asItemStack()
                : this.weaponsexpanded$pickupStack.copy();
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.isInGround()) {
            this.setVelocity(this.getVelocity().multiply(EXTRA_AIR_DRAG));
        }
    }

    @Override
    protected double getGravity() {
        return 0.08;
    }

    @Override
    protected void knockback(LivingEntity target, DamageSource source) {
        super.knockback(target, source);

        if (this.weaponsexpanded$punchLevel <= 0) return;

        // Vanilla-ish punch: add extra horizontal knockback, respecting knockback resistance.
        double resistance = target.getAttributeValue(EntityAttributes.KNOCKBACK_RESISTANCE);
        double e = Math.max(0.0, 1.0 - resistance);

        Vec3d horiz = this.getVelocity().multiply(1.0, 0.0, 1.0);
        if (horiz.lengthSquared() <= 0.0) return;

        Vec3d extra = horiz.normalize().multiply(this.weaponsexpanded$punchLevel * 0.6 * e);
        target.addVelocity(extra.x, 0.1, extra.z);
    }
}

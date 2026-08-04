package net.angelic.weaponsexpanded.entity.projectile;

import net.angelic.weaponsexpanded.entity.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class HeavyArrowEntity extends ArrowEntity {
    public static final double BASE_DAMAGE = 3.6;

    private static final float EXTRA_AIR_DRAG = 0.9f;
    private static final float GRAVITY = 0.1f;
    private static final float VANILLA_ARROW_GRAVITY = 0.05f;

    private ItemStack weaponsexpanded$pickupStack = ItemStack.EMPTY;

    public HeavyArrowEntity(
            EntityType<? extends ArrowEntity> type,
            World world
    ) {
        super(type, world);
        this.setDamage(BASE_DAMAGE);
    }

    public HeavyArrowEntity(
            World world,
            LivingEntity owner,
            ItemStack pickupItemStack,
            ItemStack weaponStack
    ) {
        this(ModEntities.HEAVY_ARROW, world);

        this.setOwner(owner);
        this.setPosition(
                owner.getX(),
                owner.getEyeY() - 0.1,
                owner.getZ()
        );
        this.setDamage(BASE_DAMAGE);

        this.weaponsexpanded$pickupStack = pickupItemStack.copy();
    }

    public void weaponsexpanded$setPunchLevel(int level) {
        // PersistentProjectileEntity provides this in 1.20.1.
        this.setPunch(Math.max(0, level));
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

        // In 1.20.1, inGround is a protected field rather than
        // being exposed through isInGround().
        if (!this.inGround) {
            Vec3d velocity = this.getVelocity();

            // Vanilla already applied 0.05 gravity. Apply only the
            // difference needed to reach the desired 0.1 gravity.
            if (!this.hasNoGravity()) {
                velocity = velocity.add(
                        0.0,
                        -(GRAVITY - VANILLA_ARROW_GRAVITY),
                        0.0
                );
            }

            this.setVelocity(velocity.multiply(EXTRA_AIR_DRAG));
        }
    }
}
package net.angelic.weaponsexpanded.entity.projectile;

import net.angelic.weaponsexpanded.entity.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class HeavyArrowEntity extends ArrowEntity {
    private static final double BASE_DAMAGE = 5.0; // vanilla arrow is ~2.0 base before velocity scaling
    private static final float EXTRA_AIR_DRAG = 0.85f; // lower = shorter range (more drag)

    public HeavyArrowEntity(EntityType<? extends ArrowEntity> type, World world) {
        super(type, world);
        this.setDamage(BASE_DAMAGE);
    }

    public HeavyArrowEntity(World world, LivingEntity owner, ItemStack pickupItemStack, ItemStack weaponStack) {
        this(ModEntities.HEAVY_ARROW, world);
        this.setOwner(owner);
        this.setPosition(owner.getX(), owner.getEyeY() - 0.1, owner.getZ());
        this.setDamage(BASE_DAMAGE);
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
}

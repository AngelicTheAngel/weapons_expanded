package net.angelic.weaponsexpanded.mixin.explosive_arrow;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.angelic.weaponsexpanded.item.ModItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(ProjectileWeaponItem.class)
public class CrossbowExplosiveArrowDurabilityMixin {

    @Unique
    private static final int WEAPONSEXPANDED$DURABILITY_PER_ARROW_FIRED = 4;

    @WrapOperation(
            method = "shoot(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/item/ItemStack;Ljava/util/List;FFZLnet/minecraft/world/entity/LivingEntity;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;hurtAndBreak(ILnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/EquipmentSlot;)V"
            )
    )
    private void weaponsexpanded$damageWeaponForExplosiveArrows(
            ItemStack instance,
            int amount,
            LivingEntity owner,
            EquipmentSlot slot,
            Operation<Void> original,
            ServerLevel level,
            LivingEntity shooter,
            InteractionHand hand,
            ItemStack weapon,
            List<ItemStack> projectiles,
            float power,
            float uncertainty,
            boolean isCrit,
            LivingEntity targetOverride
    ) {
        int newAmount = amount;

        if (weapon.getItem() instanceof CrossbowItem) {
            boolean hasExplosive = projectiles.stream()
                    .anyMatch(s -> !s.isEmpty() && s.getItem() == ModItems.EXPLOSIVE_ARROW);

            if (hasExplosive) {
                // This wrapper is invoked once per projectile, so set the per-arrow cost here.
                newAmount = WEAPONSEXPANDED$DURABILITY_PER_ARROW_FIRED;
            }
        }

        original.call(instance, newAmount, owner, slot);
    }
}

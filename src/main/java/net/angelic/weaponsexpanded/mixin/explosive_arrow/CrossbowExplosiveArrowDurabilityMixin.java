package net.angelic.weaponsexpanded.mixin.explosive_arrow;

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
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(ProjectileWeaponItem.class)
public class CrossbowExplosiveArrowDurabilityMixin {

    @Unique
    private static final int WEAPONSEXPANDED$DURABILITY_PER_ARROW_FIRED = 4;

    @Redirect(
            method = "shoot(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/item/ItemStack;Ljava/util/List;FFZLnet/minecraft/world/entity/LivingEntity;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;hurtAndBreak(ILnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/EquipmentSlot;)V"
            )
    )
    private void weaponsexpanded$damageWeaponForExplosiveArrows(
            ItemStack instance,
            int originalAmount,
            LivingEntity damageReceiver,
            EquipmentSlot slot,
            ServerLevel world,
            LivingEntity shooter,
            InteractionHand hand,
            ItemStack weaponStack,
            List<ItemStack> projectiles,
            float speed,
            float divergence,
            boolean critical,
            LivingEntity target
    ) {
        int newAmount = originalAmount;

        if (weaponStack.getItem() instanceof CrossbowItem) {
            boolean hasExplosive = projectiles.stream()
                    .anyMatch(s -> !s.isEmpty() && s.getItem() == ModItems.EXPLOSIVE_ARROW.get());

            if (hasExplosive) {
                // This redirect is invoked once PER projectile, so set per-arrow cost here.
                newAmount = WEAPONSEXPANDED$DURABILITY_PER_ARROW_FIRED;
            }
        }

        instance.hurtAndBreak(newAmount, damageReceiver, slot);
    }
}

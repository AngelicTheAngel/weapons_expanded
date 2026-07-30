package net.angelic.weaponsexpanded.mixin.explosive_arrow;

import net.angelic.weaponsexpanded.item.ModItems;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(RangedWeaponItem.class)
public class CrossbowExplosiveArrowDurabilityMixin {

    @Unique
    private static final int WEAPONSEXPANDED$DURABILITY_PER_ARROW_FIRED = 4;

    @Redirect(
            method = "shootAll(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/util/Hand;Lnet/minecraft/item/ItemStack;Ljava/util/List;FFZLnet/minecraft/entity/LivingEntity;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;damage(ILnet/minecraft/entity/LivingEntity;Lnet/minecraft/entity/EquipmentSlot;)V"
            )
    )
    private void weaponsexpanded$damageWeaponForExplosiveArrows(
            ItemStack instance,
            int originalAmount,
            LivingEntity damageReceiver,
            EquipmentSlot slot,
            ServerWorld world,
            LivingEntity shooter,
            Hand hand,
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
                    .anyMatch(s -> !s.isEmpty() && s.getItem() == ModItems.EXPLOSIVE_ARROW);

            if (hasExplosive) {
                // This redirect is invoked once PER projectile, so set per-arrow cost here.
                newAmount = WEAPONSEXPANDED$DURABILITY_PER_ARROW_FIRED;
            }
        }

        instance.damage(newAmount, damageReceiver, slot);
    }
}

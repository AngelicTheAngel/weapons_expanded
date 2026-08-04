package net.angelic.weaponsexpanded.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.angelic.weaponsexpanded.WeaponsExpandedConfig;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.function.Consumer;

@Mixin(DiggerItem.class)
public abstract class AxeDurabilityMixin {

    @WrapOperation(
            method =
                    "hurtEnemy("
                            + "Lnet/minecraft/world/item/ItemStack;"
                            + "Lnet/minecraft/world/entity/LivingEntity;"
                            + "Lnet/minecraft/world/entity/LivingEntity;"
                            + ")Z",
            at = @At(
                    value = "INVOKE",
                    target =
                            "Lnet/minecraft/world/item/ItemStack;"
                                    + "hurtAndBreak("
                                    + "I"
                                    + "Lnet/minecraft/world/entity/LivingEntity;"
                                    + "Ljava/util/function/Consumer;"
                                    + ")V"
            ),
            require = 1
    )
    private void weaponsexpanded$axeHitDurabilityCost(
            ItemStack stack,
            int originalAmount,
            LivingEntity attacker,
            Consumer<LivingEntity> breakCallback,
            Operation<Void> original
    ) {
        int durabilityCost =
                originalAmount;

        if (WeaponsExpandedConfig
                .DISABLE_EXTRA_DURABILITY_DAMAGE_FOR_AXES
                .get()
                && stack.is(ItemTags.AXES)) {
            durabilityCost = 1;
        }

        original.call(
                stack,
                durabilityCost,
                attacker,
                breakCallback
        );
    }
}
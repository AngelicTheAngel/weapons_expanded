package net.angelic.weaponsexpanded.mixin.explosive_arrow;

import net.angelic.weaponsexpanded.item.ModItems;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.item.component.CustomModelData;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(CrossbowItem.class)
public class CrossbowItemExplosiveVisualMixin {

    @Unique
    private static final float WEAPONSEXPANDED$CMD_EXPLOSIVE = 1.0F;

    @Inject(method = "tryLoadProjectiles", at = @At("RETURN"))
    private static void weaponsexpanded$markExplosiveLoaded(
            LivingEntity shooter,
            ItemStack crossbow,
            CallbackInfoReturnable<Boolean> cir
    ) {
        if (!cir.getReturnValue()) return;

        ChargedProjectiles charged =
                crossbow.getOrDefault(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY);

        boolean hasExplosive = charged.items().stream()
                .anyMatch(s -> s.item().value() == ModItems.EXPLOSIVE_ARROW);

        if (hasExplosive) {
            crossbow.set(
                    DataComponents.CUSTOM_MODEL_DATA,
                    new CustomModelData(List.of(WEAPONSEXPANDED$CMD_EXPLOSIVE), List.of(), List.of(), List.of())
            );
        } else {
            crossbow.remove(DataComponents.CUSTOM_MODEL_DATA);
        }
    }

    @Inject(method = "performShooting", at = @At("HEAD"))
    private void weaponsexpanded$clearExplosiveFlagOnFire(
            Level world,
            LivingEntity shooter,
            InteractionHand hand,
            ItemStack stack,
            float speed,
            float divergence,
            LivingEntity target,
            CallbackInfo ci
    ) {
        // Remove the flag before firing so it doesn't "stick" after the shot.
        stack.remove(DataComponents.CUSTOM_MODEL_DATA);
    }
}

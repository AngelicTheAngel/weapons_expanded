package net.angelic.weaponsexpanded.mixin.explosive_arrow;

import net.angelic.weaponsexpanded.item.ModItems;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.component.type.CustomModelDataComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
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

    @Inject(method = "loadProjectiles", at = @At("RETURN"))
    private static void weaponsexpanded$markExplosiveLoaded(
            LivingEntity shooter,
            ItemStack crossbow,
            CallbackInfoReturnable<Boolean> cir
    ) {
        if (!cir.getReturnValue()) return;

        ChargedProjectilesComponent charged =
                crossbow.getOrDefault(DataComponentTypes.CHARGED_PROJECTILES, ChargedProjectilesComponent.DEFAULT);

        boolean hasExplosive = charged.getProjectiles().stream()
                .anyMatch(s -> !s.isEmpty() && s.getItem() == ModItems.EXPLOSIVE_ARROW);

        if (hasExplosive) {
            crossbow.set(
                    DataComponentTypes.CUSTOM_MODEL_DATA,
                    new CustomModelDataComponent(List.of(WEAPONSEXPANDED$CMD_EXPLOSIVE), List.of(), List.of(), List.of())
            );
        } else {
            crossbow.remove(DataComponentTypes.CUSTOM_MODEL_DATA);
        }
    }

    @Inject(method = "shootAll", at = @At("HEAD"))
    private void weaponsexpanded$clearExplosiveFlagOnFire(
            World world,
            LivingEntity shooter,
            Hand hand,
            ItemStack stack,
            float speed,
            float divergence,
            LivingEntity target,
            CallbackInfo ci
    ) {
        // Remove the flag before firing so it doesn't "stick" after the shot.
        stack.remove(DataComponentTypes.CUSTOM_MODEL_DATA);
    }
}

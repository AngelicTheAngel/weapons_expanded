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
public abstract class CrossbowItemExplosiveVisualMixin {

    @Unique
    private static final float
            WEAPONSEXPANDED$CMD_EXPLOSIVE = 1.0F;

    @Inject(
            method =
                    "tryLoadProjectiles("
                            + "Lnet/minecraft/world/entity/LivingEntity;"
                            + "Lnet/minecraft/world/item/ItemStack;"
                            + ")Z",
            at = @At("RETURN"),
            require = 1
    )
    private static void weaponsexpanded$markExplosiveLoaded(
            LivingEntity shooter,
            ItemStack crossbow,
            CallbackInfoReturnable<Boolean> callback
    ) {
        if (callback.getReturnValue()) {
            weaponsexpanded$updateExplosiveVisual(crossbow);
        }
    }

    @Inject(
            method =
                    "performShooting("
                            + "Lnet/minecraft/world/level/Level;"
                            + "Lnet/minecraft/world/entity/LivingEntity;"
                            + "Lnet/minecraft/world/InteractionHand;"
                            + "Lnet/minecraft/world/item/ItemStack;"
                            + "FF"
                            + "Lnet/minecraft/world/entity/LivingEntity;"
                            + ")V",
            at = @At("RETURN"),
            require = 1
    )
    private void weaponsexpanded$refreshExplosiveVisualAfterShooting(
            Level level,
            LivingEntity shooter,
            InteractionHand hand,
            ItemStack crossbow,
            float power,
            float uncertainty,
            LivingEntity targetOverride,
            CallbackInfo callback
    ) {
        weaponsexpanded$updateExplosiveVisual(crossbow);
    }

    @Unique
    private static void weaponsexpanded$updateExplosiveVisual(
            ItemStack crossbow
    ) {
        ChargedProjectiles charged = crossbow.getOrDefault(
                DataComponents.CHARGED_PROJECTILES,
                ChargedProjectiles.EMPTY
        );

        boolean hasExplosive = charged.getItems().stream()
                .anyMatch(projectile ->
                        projectile.is(
                                ModItems.EXPLOSIVE_ARROW.get()
                        )
                );

        if (hasExplosive) {
            crossbow.set(
                    DataComponents.CUSTOM_MODEL_DATA,
                    new CustomModelData(List.of(WEAPONSEXPANDED$CMD_EXPLOSIVE), List.of(), List.of(), List.of())
            );
        } else {
            crossbow.remove(
                    DataComponents.CUSTOM_MODEL_DATA
            );
        }
    }
}
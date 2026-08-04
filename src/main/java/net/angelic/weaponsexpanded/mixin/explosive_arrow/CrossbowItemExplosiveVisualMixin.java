package net.angelic.weaponsexpanded.mixin.explosive_arrow;

import net.angelic.weaponsexpanded.item.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CrossbowItem.class)
public abstract class CrossbowItemExplosiveVisualMixin {

    @Unique
    private static final String WEAPONSEXPANDED$CUSTOM_MODEL_DATA =
            "CustomModelData";

    @Unique
    private static final int WEAPONSEXPANDED$CMD_EXPLOSIVE = 1;

    @Inject(
            method = "loadProjectiles(Lnet/minecraft/entity/LivingEntity;"
                    + "Lnet/minecraft/item/ItemStack;)Z",
            at = @At("RETURN")
    )
    private static void weaponsexpanded$markExplosiveLoaded(
            LivingEntity shooter,
            ItemStack crossbow,
            CallbackInfoReturnable<Boolean> cir
    ) {
        if (!cir.getReturnValue()) return;

        boolean hasExplosive = CrossbowItem.hasProjectile(
                crossbow,
                ModItems.EXPLOSIVE_ARROW
        );

        if (hasExplosive) {
            crossbow.getOrCreateNbt().putInt(
                    WEAPONSEXPANDED$CUSTOM_MODEL_DATA,
                    WEAPONSEXPANDED$CMD_EXPLOSIVE
            );
        } else {
            weaponsexpanded$removeCustomModelData(crossbow);
        }
    }

    @Inject(
            method = "shootAll(Lnet/minecraft/world/World;"
                    + "Lnet/minecraft/entity/LivingEntity;"
                    + "Lnet/minecraft/util/Hand;"
                    + "Lnet/minecraft/item/ItemStack;FF)V",
            at = @At("HEAD")
    )
    private static void weaponsexpanded$clearExplosiveFlagOnFire(
            World world,
            LivingEntity shooter,
            Hand hand,
            ItemStack crossbow,
            float speed,
            float divergence,
            CallbackInfo ci
    ) {
        // Remove the flag before firing so it does not remain after the shot.
        weaponsexpanded$removeCustomModelData(crossbow);
    }

    @Unique
    private static void weaponsexpanded$removeCustomModelData(
            ItemStack stack
    ) {
        NbtCompound nbt = stack.getNbt();

        if (nbt != null) {
            nbt.remove(WEAPONSEXPANDED$CUSTOM_MODEL_DATA);
        }
    }
}
package net.angelic.weaponsexpanded.mixin.enchantment;

import net.angelic.weaponsexpanded.config.WeaponsExpandedConfig;
import net.angelic.weaponsexpanded.enchantment.ModEnchantmentHelper;
import net.angelic.weaponsexpanded.enchantment.ModEnchantments;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CrossbowItem.class)
public abstract class AutoLoadDelayMixin {
    @Unique
    private static final String WEAPONSEXPANDED$AUTO_LOAD_READY_AT = "weaponsexpanded:auto_load_ready_at";

    @Inject(method = "performShooting", at = @At("TAIL"))
    private void weaponsexpanded$delayAutoLoadAfterFiring(
            Level level,
            LivingEntity shooter,
            InteractionHand hand,
            ItemStack crossbow,
            float speed,
            float divergence,
            LivingEntity target,
            CallbackInfo ci
    ) {
        if (level.isClientSide()) return;

        int autoLoadLevel = ModEnchantmentHelper.getLevel(level, crossbow, ModEnchantments.AUTO_LOAD);
        if (autoLoadLevel <= 0) return;

        CompoundTag root = weaponsexpanded$getCustomData(crossbow);

        root.putLong(
                WEAPONSEXPANDED$AUTO_LOAD_READY_AT,
                level.getGameTime() + WeaponsExpandedConfig.get().autoLoadFiringDelay
        );

        root.remove("weaponsexpanded:auto_load_start");
        weaponsexpanded$setCustomData(crossbow, root);
    }

    @Unique
    private static CompoundTag weaponsexpanded$getCustomData(ItemStack stack) {
        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        return customData != null
                ? customData.copyTag()
                : new CompoundTag();
    }

    @Unique
    private static void weaponsexpanded$setCustomData(ItemStack stack, CompoundTag root) {
        if (root.isEmpty()) {
            stack.remove(DataComponents.CUSTOM_DATA);
        } else {
            stack.set(DataComponents.CUSTOM_DATA, CustomData.of(root));
        }
    }
}
package net.angelic.weaponsexpanded.mixin.enchantment;

import net.angelic.weaponsexpanded.config.WeaponsExpandedConfig;
import net.angelic.weaponsexpanded.enchantment.ModEnchantmentHelper;
import net.angelic.weaponsexpanded.enchantment.ModEnchantments;
import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.angelic.weaponsexpanded.mixin.invoker.CrossbowItemInvoker;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("ConstantValue")
@Mixin(LivingEntity.class)
public abstract class AutoLoadMixin {
    @Unique
    private static final String AUTO_LOAD_START_KEY = "weaponsexpanded:auto_load_start";
    @Unique
    private static final String AUTO_LOAD_READY_AT_KEY = "weaponsexpanded:auto_load_ready_at";

    @Inject(method = "tick", at = @At("HEAD"))
    private void weaponsexpanded$autoLoadCheck(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (!(entity instanceof Player player)) return;
        if (player.level().isClientSide()) return;


        for (ItemStack stack : player.getInventory()) {
            if (stack.getItem() instanceof CrossbowItem crossbow) {
                weaponsexpanded$tickAutoLoad(player, stack, crossbow, ModEnchantmentHelper.getLevel(player.level(), stack, ModEnchantments.AUTO_LOAD));
            }
        }
    }

    @Unique
    private static void weaponsexpanded$tickAutoLoad(Player player, ItemStack stack, CrossbowItem crossbow, int enchantmentLevel) {
        boolean playerIsUsingThisStack = player.isUsingItem() && player.getUseItem() == stack;

        boolean hasAmmunition = !player.getProjectile(stack).isEmpty() || player.isCreative();

        ChainCrossbowItem chainCrossbow = crossbow instanceof ChainCrossbowItem chain ? chain : null;

        boolean fullyLoaded = chainCrossbow != null
                ? chainCrossbow.weaponsexpanded$isMagazineFull(stack)
                : CrossbowItem.isCharged(stack);

        if (enchantmentLevel <= 0 || fullyLoaded || playerIsUsingThisStack || !hasAmmunition) {
            weaponsexpanded$clearAutoLoadTimer(stack);
            ChainCrossbowItem.weaponsexpanded$setModelFloat(stack, 1, 0.0F);
            return;
        }

        CompoundTag root = weaponsexpanded$getCustomData(stack);
        long gameTime = player.level().getGameTime();
        long readyAt = root.getLong(AUTO_LOAD_READY_AT_KEY).orElse(0L);

        if (gameTime < readyAt) {
            weaponsexpanded$clearAutoLoadTimer(stack);
            ChainCrossbowItem.weaponsexpanded$setModelFloat(stack, 1, 0.0F);
            return;
        }

        if (readyAt != 0L) {
            root.remove(AUTO_LOAD_READY_AT_KEY);
            weaponsexpanded$setCustomData(stack, root);
        }

        long startedAt = root.getLong(AUTO_LOAD_START_KEY).orElse(-1L);

        if (startedAt < 0L) {
            root.putLong(AUTO_LOAD_START_KEY, gameTime);
            weaponsexpanded$setCustomData(stack, root);
            return;
        }

        float chargeTicks = Math.max(1, CrossbowItem.getChargeDuration(stack, player) * WeaponsExpandedConfig.get().autoLoadChargeMultiplier);

        long elapsed = gameTime - startedAt;
        float progress = Math.min(1.0F, elapsed / chargeTicks);

        float frame;

        if (progress < 0.4F) {
            frame = 1.0F;
        } else if (progress < 0.8F) {
            frame = 2.0F;
        } else {
            frame = 3.0F;
        }

        ChainCrossbowItem.weaponsexpanded$setModelFloat(stack, 1, frame);

        if (gameTime - startedAt < chargeTicks) {
            return;
        }

        weaponsexpanded$clearAutoLoadTimer(stack);
        boolean prepared = chainCrossbow == null || chainCrossbow.weaponsexpanded$prepareAutoLoad(player.level(), stack);

        boolean loaded = false;
        if (prepared) {
            loaded = CrossbowItemInvoker.weaponsexpanded$tryLoadProjectiles(player, stack);
        }

        if (chainCrossbow != null && prepared) {
            chainCrossbow.weaponsexpanded$finishAutoLoad(player.level(), stack, loaded);
        }

        ChainCrossbowItem.weaponsexpanded$setModelFloat(stack, 1, 0.0F);

        if (loaded) {
            if (stack.getItem() instanceof ChainCrossbowItem) {
                ChainCrossbowItem.weaponsexpanded$refreshLoadedVisual(stack);
            }

            if (player instanceof ServerPlayer serverPlayer) {
                serverPlayer.containerMenu.sendAllDataToRemote();
            }

            player.level().playSound(
                    null,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    SoundEvents.CROSSBOW_LOADING_END,
                    SoundSource.PLAYERS,
                    1.0F,
                    1.0F
            );
        }
    }

    @Unique
    private static void weaponsexpanded$clearAutoLoadTimer(ItemStack stack) {
        CompoundTag root = weaponsexpanded$getCustomData(stack);

        if (root.contains(AUTO_LOAD_START_KEY)) {
            root.remove(AUTO_LOAD_START_KEY);
            weaponsexpanded$setCustomData(stack, root);
        }
    }

    @Unique
    private static CompoundTag weaponsexpanded$getCustomData(ItemStack stack) {
        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        return customData != null ? customData.copyTag() : new CompoundTag();
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
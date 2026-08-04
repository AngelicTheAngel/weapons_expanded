package net.angelic.weaponsexpanded.mixin.chain_crossbow;

import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.angelic.weaponsexpanded.sound.ModSounds;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CrossbowItem.class)
public abstract class ChainCrossbowFireMixin {

    @Inject(
            method = "shootAll(Lnet/minecraft/world/World;"
                    + "Lnet/minecraft/entity/LivingEntity;"
                    + "Lnet/minecraft/util/Hand;"
                    + "Lnet/minecraft/item/ItemStack;FF)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void weaponsexpanded$blockShootAllWhileOnCooldown(
            World world,
            LivingEntity shooter,
            Hand hand,
            ItemStack stack,
            float speed,
            float divergence,
            CallbackInfo ci
    ) {
        if (world.isClient) {
            return;
        }

        if (!(stack.getItem() instanceof ChainCrossbowItem)) {
            return;
        }

        if (shooter instanceof PlayerEntity player
                && player.getItemCooldownManager()
                .isCoolingDown(stack.getItem())) {
            ci.cancel();
        }
    }

    @Inject(
            method = "shootAll(Lnet/minecraft/world/World;"
                    + "Lnet/minecraft/entity/LivingEntity;"
                    + "Lnet/minecraft/util/Hand;"
                    + "Lnet/minecraft/item/ItemStack;FF)V",
            at = @At("TAIL")
    )
    private static void weaponsexpanded$autoReloadFromStoredQueue(
            World world,
            LivingEntity shooter,
            Hand hand,
            ItemStack stack,
            float speed,
            float divergence,
            CallbackInfo ci
    ) {
        if (world.isClient) {
            return;
        }

        if (!(stack.getItem() instanceof ChainCrossbowItem)) {
            return;
        }

        /*
         * Cooldowns in 1.20.1 operate on Item, not ItemStack.
         */
        if (shooter instanceof PlayerEntity player) {
            player.getItemCooldownManager().set(
                    stack.getItem(),
                    8
            );
        }

        /*
         * Vanilla shootAll should have emptied the active chamber.
         * Do not consume a queued chamber if another mixin or mod kept
         * the current chamber charged.
         */
        if (CrossbowItem.isCharged(stack)) {
            return;
        }

        boolean loadedNextChamber =
                ChainCrossbowItem.weaponsexpanded$loadNextChamber(
                        world,
                        stack
                );

        if (loadedNextChamber) {
            world.playSound(
                    null,
                    shooter.getX(),
                    shooter.getY(),
                    shooter.getZ(),
                    ModSounds.CHAIN_CROSSBOW_CHAMBER,
                    SoundCategory.PLAYERS,
                    0.7F,
                    1.0F
            );
        }

        ChainCrossbowItem.weaponsexpanded$refreshLoadedVisual(
                stack
        );

        if (shooter instanceof ServerPlayerEntity serverPlayer) {
            serverPlayer.currentScreenHandler.syncState();
        }
    }
}
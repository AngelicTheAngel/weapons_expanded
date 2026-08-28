package net.angelic.weaponsexpanded.mixin.chain_crossbow;

import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.angelic.weaponsexpanded.mixin.accessor.PersistentProjectileEntityAccessor;
import net.angelic.weaponsexpanded.sound.ModSounds;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(CrossbowItem.class)
public abstract class ChainCrossbowFireMixin {

    @Inject(method = "shootProjectile", at = @At("HEAD"))
    private void weaponsexpanded$ensureWeaponIsSet(
            LivingEntity shooter,
            Projectile projectile,
            int index,
            float speed,
            float divergence,
            float yaw,
            @Nullable LivingEntity target,
            CallbackInfo ci
    ) {
        if (projectile instanceof AbstractArrow persistentProjectile) {
            // Check both hands since the fire request might come from the main hand 
            // but vanilla logic sometimes checks active hand.
            ItemStack main = shooter.getMainHandItem();
            ItemStack off = shooter.getOffhandItem();
            
            boolean isChainCrossbow = (main.getItem() instanceof ChainCrossbowItem) || (off.getItem() instanceof ChainCrossbowItem);

            if (isChainCrossbow) {
                // Spoof the weapon as a vanilla crossbow so advancements that check for "minecraft:crossbow" specifically will trigger.
                ItemStack spoofedWeapon = new ItemStack(Items.CROSSBOW);
                // Copy enchantments and components so Piercing/Multishot are recognized
                spoofedWeapon.applyComponents((main.getItem() instanceof ChainCrossbowItem ? main : off).getComponents());
                
                ((PersistentProjectileEntityAccessor) persistentProjectile).weaponsexpanded$setWeapon(spoofedWeapon);
            }
        }
    }

    @Inject(method = "performShooting", at = @At("HEAD"), cancellable = true)
    private void weaponsexpanded$blockShootAllWhileOnCooldown(
            Level world,
            LivingEntity shooter,
            InteractionHand hand,
            ItemStack stack,
            float speed,
            float divergence,
            @Nullable LivingEntity target,
            CallbackInfo ci
    ) {
        if (world.isClientSide()) return;
        if (!(stack.getItem() instanceof ChainCrossbowItem)) return;

        if (shooter instanceof Player player) {
            if (player.getCooldowns().isOnCooldown(stack)) {
                ci.cancel(); // Prevent firing while cooldown is active
            }
        }
    }

    @Inject(method = "performShooting", at = @At("TAIL"))
    private void weaponsexpanded$autoReloadFromStoredQueue(
            Level world,
            LivingEntity shooter,
            InteractionHand hand,
            ItemStack stack,
            float speed,
            float divergence,
            @Nullable LivingEntity target,
            CallbackInfo ci
    ) {
        if (world.isClientSide()) return;
        if (!(stack.getItem() instanceof ChainCrossbowItem)) return;

        // Apply 8-tick cooldown after firing
        if (shooter instanceof Player player) {
            player.getCooldowns().addCooldown(stack, 8);
        }

        ChargedProjectiles charged =
                stack.getOrDefault(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY);

        // Only refill when the chamber is actually empty
        if (!charged.isEmpty()) return;

        List<ItemStackTemplate> nextChamber = ChainCrossbowItem.weaponsexpanded$popNextChamber(world, stack);
        if (nextChamber.isEmpty()) return;

        // Play chamber sound after firing
        world.playSound(
                null,
                shooter.getX(), shooter.getY(), shooter.getZ(),
                ModSounds.CHAIN_CROSSBOW_CHAMBER,
                SoundSource.PLAYERS,
                0.7F,
                1.0F
        );

        stack.set(DataComponents.CHARGED_PROJECTILES, new ChargedProjectiles(nextChamber));

        //refresh custom model data based on what we just loaded
        ChainCrossbowItem.weaponsexpanded$refreshLoadedVisual(stack);

        // SYNC FIX: Ensure the client knows the item has been updated
        if (shooter instanceof ServerPlayer serverPlayer) {
            serverPlayer.containerMenu.sendAllDataToRemote();
        }
    }
}
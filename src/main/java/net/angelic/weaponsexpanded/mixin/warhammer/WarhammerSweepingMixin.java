package net.angelic.weaponsexpanded.mixin.warhammer;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.angelic.weaponsexpanded.item.custom.WarhammerItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Player.class)
public abstract class WarhammerSweepingMixin {

    @WrapOperation(
            method = "attack(Lnet/minecraft/world/entity/Entity;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Player;isSweepAttack(ZZZ)Z"
            )
    )
    private boolean weaponsexpanded$disableWarhammerSweepWhenBlunt(
            Player player,
            boolean fullStrengthAttack,
            boolean criticalAttack,
            boolean knockbackAttack,
            Operation<Boolean> original
    ) {
        ItemStack stack = player.getWeaponItem();

        if (stack.getItem() instanceof WarhammerItem warhammer && !warhammer.isSharpSide(stack)) {
            return false;
        }

        return original.call(player, fullStrengthAttack, criticalAttack, knockbackAttack);
    }
}

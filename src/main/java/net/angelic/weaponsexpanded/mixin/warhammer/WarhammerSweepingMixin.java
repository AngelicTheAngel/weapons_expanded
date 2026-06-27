package net.angelic.weaponsexpanded.mixin.warhammer;

import net.angelic.weaponsexpanded.item.custom.WarhammerItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Player.class)
public abstract class WarhammerSweepingMixin {

    @Invoker("isSweepAttack")
    public abstract boolean weaponsexpanded$callIsSweepAttack(
            boolean fullStrengthAttack,
            boolean criticalAttack,
            boolean knockbackAttack
    );

    @Redirect(
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
            boolean knockbackAttack
    ) {
        ItemStack stack = player.getWeaponItem();

        if (stack.getItem() instanceof WarhammerItem warhammer && !warhammer.isSharpSide(stack)) {
            return false;
        }

        return ((WarhammerSweepingMixin) (Object) player)
                .weaponsexpanded$callIsSweepAttack(
                        fullStrengthAttack,
                        criticalAttack,
                        knockbackAttack
                );
    }
}
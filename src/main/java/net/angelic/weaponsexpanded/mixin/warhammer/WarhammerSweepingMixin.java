package net.angelic.weaponsexpanded.mixin.warhammer;

import net.angelic.weaponsexpanded.item.custom.WarhammerItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerEntity.class)
public abstract class WarhammerSweepingMixin {

    @Invoker("canUseSweepAttack")
    public abstract boolean weaponsexpanded$callCanUseSweepAttack(
            boolean cooledDown,
            boolean criticalHit,
            boolean sprinting
    );

    @Redirect(
            method = "attack(Lnet/minecraft/entity/Entity;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/PlayerEntity;canUseSweepAttack(ZZZ)Z"
            )
    )
    private boolean weaponsexpanded$disableWarhammerSweepWhenBlunt(
            PlayerEntity player,
            boolean cooledDown,
            boolean criticalHit,
            boolean sprinting
    ) {
        ItemStack stack = player.getWeaponStack();

        if (stack.getItem() instanceof WarhammerItem warhammer && !warhammer.isSharpSide(stack)) {
            return false;
        }

        return ((WarhammerSweepingMixin) (Object) player)
                .weaponsexpanded$callCanUseSweepAttack(cooledDown, criticalHit, sprinting);
    }
}
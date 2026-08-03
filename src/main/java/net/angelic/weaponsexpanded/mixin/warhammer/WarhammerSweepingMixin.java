package net.angelic.weaponsexpanded.mixin.warhammer;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.angelic.weaponsexpanded.item.custom.WarhammerItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerEntity.class)
public abstract class WarhammerSweepingMixin {

    @ModifyExpressionValue(
            method = "attack(Lnet/minecraft/entity/Entity;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/PlayerEntity;"
                            + "canUseSweepAttack(ZZZ)Z"
            ),
            require = 1
    )
    private boolean weaponsexpanded$disableWarhammerSweepWhenBlunt(
            boolean vanillaCanSweep
    ) {
        if (!vanillaCanSweep) {
            return false;
        }

        PlayerEntity player = (PlayerEntity) (Object) this;
        ItemStack weapon = player.getWeaponStack();

        assert weapon != null;
        return !(weapon.getItem() instanceof WarhammerItem warhammer)
                || warhammer.isSharpSide(weapon);
    }
}
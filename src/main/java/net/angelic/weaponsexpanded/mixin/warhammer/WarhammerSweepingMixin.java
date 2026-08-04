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
                            + "isOnGround()Z",
                    ordinal = 1
            ),
            require = 1
    )
    private boolean weaponsexpanded$disableWarhammerSweepWhenBlunt(
            boolean vanillaOnGround
    ) {
        if (!vanillaOnGround) {
            return false;
        }

        PlayerEntity player = (PlayerEntity) (Object) this;
        ItemStack weapon = player.getMainHandStack();

        if (weapon.getItem() instanceof WarhammerItem warhammer) {
            return warhammer.isSharpSide(weapon);
        }

        return true;
    }
}
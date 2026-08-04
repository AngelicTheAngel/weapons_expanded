package net.angelic.weaponsexpanded.mixin.warhammer;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.angelic.weaponsexpanded.item.custom.WarhammerItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Player.class)
public abstract class WarhammerSweepingMixin {

    @ModifyExpressionValue(
            method =
                    "attack("
                            + "Lnet/minecraft/world/entity/Entity;"
                            + ")V",
            at = @At(
                    value = "INVOKE",
                    target =
                            "Lnet/minecraft/world/entity/player/Player;"
                                    + "onGround()Z",
                    ordinal = 1
            ),
            require = 1
    )
    private boolean
    weaponsexpanded$disableWarhammerSweepWhenBlunt(
            boolean vanillaOnGround
    ) {
        if (!vanillaOnGround) {
            return false;
        }

        Player player =
                (Player) (Object) this;

        ItemStack weapon =
                player.getMainHandItem();

        if (weapon.getItem()
                instanceof WarhammerItem warhammer) {
            return warhammer.isSharpSide(weapon);
        }

        return true;
    }
}
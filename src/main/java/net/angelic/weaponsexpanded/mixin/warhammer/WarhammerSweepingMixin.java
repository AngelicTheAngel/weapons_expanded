package net.angelic.weaponsexpanded.mixin.warhammer;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.angelic.weaponsexpanded.item.custom.WarhammerItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Player.class)
public abstract class WarhammerSweepingMixin {

    @ModifyReturnValue(
            method = "isSweepAttack(ZZZ)Z",
            at = @At("RETURN"),
            require = 1
    )
    private boolean weaponsexpanded$disableWarhammerSweepWhenBlunt(
            boolean vanillaCanSweep
    ) {
        if (!vanillaCanSweep) {
            return false;
        }

        Player player =
                (Player) (Object) this;

        ItemStack weapon =
                player.getWeaponItem();

        if (weapon.getItem()
                instanceof WarhammerItem warhammer) {
            return warhammer.isSharpSide(weapon);
        }

        return true;
    }
}
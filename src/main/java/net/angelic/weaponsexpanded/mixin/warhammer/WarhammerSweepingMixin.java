package net.angelic.weaponsexpanded.mixin.warhammer;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.angelic.weaponsexpanded.item.custom.WarhammerItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerEntity.class)
public abstract class WarhammerSweepingMixin {

    @ModifyExpressionValue(
            method = "attack(Lnet/minecraft/entity/Entity;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;",
                    ordinal = 1
            ),
            require = 1
    )
    private Item weaponsexpanded$disableBluntWarhammerSweep(Item originalItem) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        ItemStack weapon = player.getMainHandStack();

        if (weapon.getItem() instanceof WarhammerItem warhammer
                && !warhammer.isSharpSide(weapon)) {
            // Makes vanilla's `instanceof SwordItem` sweep check fail.
            return Items.AIR;
        }

        return originalItem;
    }
}
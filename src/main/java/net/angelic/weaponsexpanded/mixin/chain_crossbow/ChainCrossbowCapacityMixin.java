package net.angelic.weaponsexpanded.mixin.chain_crossbow;

import net.angelic.weaponsexpanded.enchantment.ModEnchantmentHelper;
import net.angelic.weaponsexpanded.enchantment.ModEnchantments;
import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class ChainCrossbowCapacityMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    private void weaponsexpanded$updateCapacity(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (!(entity instanceof Player player)) return;

        for (ItemStack stack : player.getInventory()) {
            if (stack.getItem() instanceof ChainCrossbowItem crossbow) {
                crossbow.setMaxShots(stack, ModEnchantmentHelper.getLevel(player.level(), stack, ModEnchantments.CAPACITY));
            }
        }
    }
}
package net.angelic.weaponsexpanded.mixin.entity_equipment;

import net.angelic.weaponsexpanded.item.ModItems;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PiglinEntity.class)
public class PiglinEquipmentMixin {

    @Inject(
            method = "initEquipment(Lnet/minecraft/util/math/random/Random;Lnet/minecraft/world/LocalDifficulty;)V",
            at = @At("TAIL")
    )
    private void weaponsexpanded$maybeSwapSwordToLongsword(Random random, LocalDifficulty localDifficulty, CallbackInfo ci) {
        PiglinEntity self = (PiglinEntity) (Object) this;

        ItemStack mainHand = self.getEquippedStack(EquipmentSlot.MAINHAND);

        // Only replace if vanilla gave them a golden sword
        if (!mainHand.isOf(Items.GOLDEN_SWORD)) return;

        // 1/4 chance to swap
        if (random.nextInt(4) != 0) return;

        ItemStack replacement = new ItemStack(ModItems.GOLDEN_LONGSWORD);

        ItemEnchantmentsComponent ench =
                mainHand.getOrDefault(DataComponentTypes.ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT);
        if (!ench.isEmpty()) {
            replacement.set(DataComponentTypes.ENCHANTMENTS, ench);
        }

        self.equipStack(EquipmentSlot.MAINHAND, replacement);
    }
}

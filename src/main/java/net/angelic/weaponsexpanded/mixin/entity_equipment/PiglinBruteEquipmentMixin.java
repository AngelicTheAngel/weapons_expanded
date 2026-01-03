package net.angelic.weaponsexpanded.mixin.entity_equipment;

import net.angelic.weaponsexpanded.item.ModItems;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.PiglinBruteEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PiglinBruteEntity.class)
public class PiglinBruteEquipmentMixin {

    @Inject(
            method = "initEquipment(Lnet/minecraft/util/math/random/Random;Lnet/minecraft/world/LocalDifficulty;)V",
            at = @At("TAIL")
    )
    private void weaponsexpanded$maybeSwapAxeToBattleaxe(Random random, LocalDifficulty localDifficulty, CallbackInfo ci) {
        PiglinBruteEntity self = (PiglinBruteEntity) (Object) this;

        ItemStack mainHand = self.getEquippedStack(EquipmentSlot.MAINHAND);

        // Only replace if vanilla gave them a golden axe
        if (!mainHand.isOf(Items.GOLDEN_AXE)) return;

        // 1/3 chance to swap
        if (random.nextInt(3) != 0) return;

        ItemStack replacement = new ItemStack(ModItems.GOLDEN_BATTLEAXE);

        ItemEnchantmentsComponent ench =
                mainHand.getOrDefault(DataComponentTypes.ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT);
        if (!ench.isEmpty()) {
            replacement.set(DataComponentTypes.ENCHANTMENTS, ench);
        }

        self.equipStack(EquipmentSlot.MAINHAND, replacement);
    }
}

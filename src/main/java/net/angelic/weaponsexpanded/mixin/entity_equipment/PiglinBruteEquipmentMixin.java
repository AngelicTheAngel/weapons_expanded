package net.angelic.weaponsexpanded.mixin.entity_equipment;

import net.angelic.weaponsexpanded.WeaponsExpandedConfig;
import net.angelic.weaponsexpanded.item.ModItems;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PiglinBrute.class)
public class PiglinBruteEquipmentMixin {

    @Inject(
            method = "populateDefaultEquipmentSlots(Lnet/minecraft/util/RandomSource;Lnet/minecraft/world/DifficultyInstance;)V",
            at = @At("TAIL")
    )
    private void weaponsexpanded$maybeSwapAxeToBattleaxe(RandomSource random, DifficultyInstance localDifficulty, CallbackInfo ci) {
        if (WeaponsExpandedConfig.meleeEquip) {
            PiglinBrute self = (PiglinBrute) (Object) this;

            ItemStack mainHand = self.getItemBySlot(EquipmentSlot.MAINHAND);

            // Only replace if vanilla gave them a golden axe
            if (!mainHand.is(Items.GOLDEN_AXE)) return;

            // 1/3 chance to swap
            if (random.nextInt(3) != 0) return;

            ItemStack replacement = new ItemStack(ModItems.GOLDEN_BATTLEAXE.get());

            ItemEnchantments ench =
                    mainHand.getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY);
            if (!ench.isEmpty()) {
                replacement.set(DataComponents.ENCHANTMENTS, ench);
            }

            self.setItemSlot(EquipmentSlot.MAINHAND, replacement);
        }
    }
}

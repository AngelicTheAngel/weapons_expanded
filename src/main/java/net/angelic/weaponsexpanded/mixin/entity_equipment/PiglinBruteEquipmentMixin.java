package net.angelic.weaponsexpanded.mixin.entity_equipment;

import net.angelic.weaponsexpanded.WeaponsExpandedConfig;
import net.angelic.weaponsexpanded.item.ModItems;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PiglinBrute.class)
public class PiglinBruteEquipmentMixin {

    @Inject(
            method = "populateDefaultEquipmentSlots",
            at = @At("TAIL")
    )
    private void
    weaponsexpanded$maybeSwapAxeToBattleaxe(
            RandomSource random,
            DifficultyInstance difficulty,
            CallbackInfo ci
    ) {
        if (!WeaponsExpandedConfig
                .ENABLE_ENTITY_MELEE_EQUIPMENT
                .get()) {
            return;
        }

        PiglinBrute self =
                (PiglinBrute) (Object) this;

        ItemStack mainHand =
                self.getItemBySlot(
                        EquipmentSlot.MAINHAND
                );

        // Only replace a golden axe assigned by vanilla.
        if (!mainHand.is(Items.GOLDEN_AXE)) {
            return;
        }

        // 1/3 chance to replace it.
        if (random.nextInt(3) != 0) {
            return;
        }

        ItemStack replacement =
                new ItemStack(
                        ModItems.GOLDEN_BATTLEAXE.get()
                );

        ItemEnchantments enchantments =
                EnchantmentHelper
                        .getEnchantmentsForCrafting(
                                mainHand
                        );

        if (!enchantments.isEmpty()) {
            EnchantmentHelper.setEnchantments(
                    replacement,
                    enchantments
            );
        }

        self.setItemSlot(
                EquipmentSlot.MAINHAND,
                replacement
        );
    }
}
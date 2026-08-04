package net.angelic.weaponsexpanded.mixin.entity_equipment;

import net.angelic.weaponsexpanded.WeaponsExpandedConfig;
import net.angelic.weaponsexpanded.item.ModItems;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(Piglin.class)
public class PiglinEquipmentMixin {

    @Inject(
            method = "populateDefaultEquipmentSlots",
            at = @At("TAIL")
    )
    private void
    weaponsexpanded$maybeSwapSwordToLongsword(
            RandomSource random,
            DifficultyInstance difficulty,
            CallbackInfo ci
    ) {
        if (!WeaponsExpandedConfig
                .ENABLE_ENTITY_MELEE_EQUIPMENT
                .get()) {
            return;
        }

        Piglin self =
                (Piglin) (Object) this;

        ItemStack mainHand =
                self.getItemBySlot(
                        EquipmentSlot.MAINHAND
                );

        // Only replace a golden sword assigned by vanilla.
        if (!mainHand.is(Items.GOLDEN_SWORD)) {
            return;
        }

        // 1/3 chance to replace it.
        if (random.nextInt(3) != 0) {
            return;
        }

        ItemStack replacement =
                new ItemStack(
                        ModItems.GOLDEN_LONGSWORD.get()
                );

        Map<Enchantment, Integer> enchantments =
                EnchantmentHelper.getEnchantments(
                        mainHand
                );

        if (!enchantments.isEmpty()) {
            EnchantmentHelper.setEnchantments(
                    enchantments,
                    replacement
            );
        }

        self.setItemSlot(
                EquipmentSlot.MAINHAND,
                replacement
        );
    }
}
package net.angelic.weaponsexpanded.mixin.entity_equipment;

import net.angelic.weaponsexpanded.config.WeaponsExpandedConfig;
import net.angelic.weaponsexpanded.item.ModItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
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

import java.util.Map;

@Mixin(PiglinBruteEntity.class)
public class PiglinBruteEquipmentMixin {

    @Inject(
            method = "initEquipment(Lnet/minecraft/util/math/random/Random;Lnet/minecraft/world/LocalDifficulty;)V",
            at = @At("TAIL")
    )
    private void weaponsexpanded$maybeSwapAxeToBattleaxe(
            Random random,
            LocalDifficulty localDifficulty,
            CallbackInfo ci
    ) {
        if (!WeaponsExpandedConfig.get().enableEntityMeleeEquipment) return;

        PiglinBruteEntity self = (PiglinBruteEntity) (Object) this;
        ItemStack mainHand = self.getEquippedStack(EquipmentSlot.MAINHAND);

        // Only replace a golden axe assigned by vanilla.
        if (!mainHand.isOf(Items.GOLDEN_AXE)) return;

        // 1/3 chance to replace it.
        if (random.nextInt(3) != 0) return;

        ItemStack replacement = new ItemStack(ModItems.GOLDEN_BATTLEAXE);

        // Item enchantments were stored in NBT before data components existed.
        Map<Enchantment, Integer> enchantments =
                EnchantmentHelper.get(mainHand);

        if (!enchantments.isEmpty()) {
            EnchantmentHelper.set(enchantments, replacement);
        }

        self.equipStack(EquipmentSlot.MAINHAND, replacement);
    }
}
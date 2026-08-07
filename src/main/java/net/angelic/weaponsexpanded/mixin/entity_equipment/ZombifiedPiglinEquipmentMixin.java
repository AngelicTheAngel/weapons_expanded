package net.angelic.weaponsexpanded.mixin.entity_equipment;

import net.angelic.weaponsexpanded.config.WeaponsExpandedConfig;
import net.angelic.weaponsexpanded.item.ModItems;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ZombifiedPiglinEntity.class)
public class ZombifiedPiglinEquipmentMixin {

    @Inject(
            method = "initEquipment(Lnet/minecraft/util/math/random/Random;Lnet/minecraft/world/LocalDifficulty;)V",
            at = @At("TAIL")
    )
    private void weaponsexpanded$maybeSwapSwordToLongsword(Random random, LocalDifficulty localDifficulty, CallbackInfo ci) {
        if (WeaponsExpandedConfig.get().enableEntityMeleeEquipment) {
            ZombifiedPiglinEntity self = (ZombifiedPiglinEntity) (Object) this;

            ItemStack mainHand = self.getEquippedStack(EquipmentSlot.MAINHAND);
            if (!mainHand.isOf(Items.GOLDEN_SWORD)) return;

            if (random.nextInt(3) != 0) return;

            ItemStack replacement = new ItemStack(ModItems.GOLDEN_LONGSWORD);

            ItemEnchantmentsComponent ench =
                    mainHand.getOrDefault(DataComponentTypes.ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT);
            if (!ench.isEmpty()) {
                replacement.set(DataComponentTypes.ENCHANTMENTS, ench);
            }

            self.equipStack(EquipmentSlot.MAINHAND, replacement);
        }
    }
}

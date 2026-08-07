package net.angelic.weaponsexpanded.mixin;

import net.angelic.weaponsexpanded.config.WeaponsExpandedConfig;
import net.minecraft.item.AxeItem;
import net.minecraft.item.MiningToolItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.ItemTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(MiningToolItem.class)
public abstract class AxeDurabilityMixin {

    @ModifyArg(
            method =
                    "postDamageEntity(" +
                            "Lnet/minecraft/item/ItemStack;" +
                            "Lnet/minecraft/entity/LivingEntity;" +
                            "Lnet/minecraft/entity/LivingEntity;)V",
            at = @At(
                    value = "INVOKE",
                    target =
                            "Lnet/minecraft/item/ItemStack;" +
                                    "damage(" +
                                    "I" +
                                    "Lnet/minecraft/entity/LivingEntity;" +
                                    "Lnet/minecraft/entity/EquipmentSlot;" +
                                    ")V"
            ),
            index = 0,
            require = 1
    )
    private int weaponsexpanded$axeHitDurabilityCost(
            int amount
    ) {
        MiningToolItem item = (MiningToolItem) (Object) this;
        if (WeaponsExpandedConfig.get()
                .disableExtraDurabilityDamageForAxes
                && (Registries.ITEM.getEntry(item).isIn(ItemTags.AXES) || item.getDefaultStack().getItem() instanceof AxeItem)) {
            return 1;
        }

        return amount;
    }
}
package net.angelic.weaponsexpanded.mixin.two_handed_sword;

import net.angelic.weaponsexpanded.item.custom.BastardSwordItem;
import net.angelic.weaponsexpanded.item.custom.TwoHandedSwordItem;
import net.angelic.weaponsexpanded.item.custom.WarhammerItem;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Locale;

@Mixin(ItemStack.class)
public abstract class TwoHandedSwordTooltipMixin {

    @Inject(
            method = "getTooltip(Lnet/minecraft/entity/player/PlayerEntity;"
                    + "Lnet/minecraft/client/item/TooltipContext;)Ljava/util/List;",
            at = @At("RETURN")
    )
    private void weaponsexpanded$correctWeaponAttributeTooltip(
            @Nullable PlayerEntity player,
            TooltipContext context,
            CallbackInfoReturnable<List<Text>> cir
    ) {
        ItemStack stack = (ItemStack) (Object) this;
        List<Text> tooltip = cir.getReturnValue();

        boolean useBothHandsHeader = false;
        boolean replaceAttributeLines = false;

        double displayedDamage = 0.0D;
        double displayedSpeed = 0.0D;

        if (stack.getItem() instanceof TwoHandedSwordItem) {
            useBothHandsHeader = true;
        }

        if (stack.getItem() instanceof BastardSwordItem bastardSword
                && bastardSword.isTwoHanded(stack)) {
            useBothHandsHeader = true;
            replaceAttributeLines = true;

            displayedDamage =
                    bastardSword.getTwoHandedDisplayedAttackDamage();

            displayedSpeed =
                    bastardSword.getTwoHandedDisplayedAttackSpeed();
        }

        if (stack.getItem() instanceof WarhammerItem warhammer
                && warhammer.isSharpSide(stack)) {
            replaceAttributeLines = true;

            displayedDamage =
                    warhammer.getSharpSideDisplayedAttackDamage();

            displayedSpeed =
                    warhammer.getSharpSideDisplayedAttackSpeed();
        }

        /*
         * Match vanilla's tooltip calculation by including enchantment
         * damage, such as Sharpness.
         */
        if (replaceAttributeLines) {
            displayedDamage += EnchantmentHelper.getAttackDamage(
                    stack,
                    EntityGroup.DEFAULT
            );
        }

        int damageLineIndex = -1;
        int speedLineIndex = -1;

        for (int index = 0; index < tooltip.size(); index++) {
            Text line = tooltip.get(index);

            if (useBothHandsHeader
                    && weaponsexpanded$isTranslation(
                    line,
                    "item.modifiers.mainhand"
            )) {
                tooltip.set(
                        index,
                        Text.translatable("item.modifiers.bothhands")
                                .setStyle(line.getStyle())
                );

                continue;
            }

            if (!replaceAttributeLines) {
                continue;
            }

            if (weaponsexpanded$isAttributeLine(
                    line,
                    "attribute.name.generic.attack_damage"
            )) {
                damageLineIndex = index;
            } else if (weaponsexpanded$isAttributeLine(
                    line,
                    "attribute.name.generic.attack_speed"
            )) {
                speedLineIndex = index;
            }
        }

        /*
         * Remove the raw red/blue NBT lines and reinsert them in the
         * same order used by vanilla weapons: damage, then speed.
         */
        if (damageLineIndex >= 0 && speedLineIndex >= 0) {
            int insertionIndex = Math.min(
                    damageLineIndex,
                    speedLineIndex
            );

            int laterIndex = Math.max(
                    damageLineIndex,
                    speedLineIndex
            );

            tooltip.remove(laterIndex);
            tooltip.remove(insertionIndex);

            tooltip.add(
                    insertionIndex,
                    weaponsexpanded$attributeLine(
                            displayedDamage,
                            "attribute.name.generic.attack_damage"
                    )
            );

            tooltip.add(
                    insertionIndex + 1,
                    weaponsexpanded$attributeLine(
                            displayedSpeed,
                            "attribute.name.generic.attack_speed"
                    )
            );
        }
    }

    @Unique
    private static boolean weaponsexpanded$isTranslation(
            Text text,
            String translationKey
    ) {
        return text.getContent()
                instanceof TranslatableTextContent translatable
                && translationKey.equals(translatable.getKey());
    }

    @Unique
    private static boolean weaponsexpanded$isAttributeLine(
            Text line,
            String attributeTranslationKey
    ) {
        if (!(line.getContent()
                instanceof TranslatableTextContent translatable)) {
            return false;
        }

        for (Object argument : translatable.getArgs()) {
            if (argument instanceof Text argumentText
                    && weaponsexpanded$isTranslation(
                    argumentText,
                    attributeTranslationKey
            )) {
                return true;
            }
        }

        return false;
    }

    @Unique
    private static MutableText weaponsexpanded$attributeLine(
            double value,
            String attributeTranslationKey
    ) {
        return Text.literal(
                        " " + weaponsexpanded$formatNumber(value) + " "
                )
                .append(Text.translatable(attributeTranslationKey))
                .formatted(Formatting.DARK_GREEN);
    }

    @Unique
    private static String weaponsexpanded$formatNumber(double value) {
        double rounded = Math.round(value * 10.0D) / 10.0D;

        if (Math.abs(rounded - Math.rint(rounded)) < 1.0E-9D) {
            return Long.toString(Math.round(rounded));
        }

        return String.format(Locale.ROOT, "%.1f", rounded);
    }
}
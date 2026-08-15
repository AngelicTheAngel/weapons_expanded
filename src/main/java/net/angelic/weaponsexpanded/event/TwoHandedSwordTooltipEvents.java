package net.angelic.weaponsexpanded.event;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.item.custom.BastardSwordItem;
import net.angelic.weaponsexpanded.item.custom.TwoHandedSwordItem;
import net.angelic.weaponsexpanded.item.custom.WarhammerItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import java.util.List;
import java.util.Locale;

@EventBusSubscriber(
        modid = WeaponsExpanded.MOD_ID,
        value = Dist.CLIENT
)
public final class TwoHandedSwordTooltipEvents {

    private TwoHandedSwordTooltipEvents() {
    }

    @SubscribeEvent
    public static void
    weaponsexpanded$correctWeaponAttributeTooltip(
            ItemTooltipEvent event
    ) {
        ItemStack stack =
                event.getItemStack();

        List<Component> tooltip =
                event.getToolTip();

        boolean useBothHandsHeader = false;
        boolean replaceAttributeLines = false;

        double displayedDamage = 0.0D;
        double displayedSpeed = 0.0D;

        if (stack.getItem()
                instanceof TwoHandedSwordItem) {
            useBothHandsHeader = true;
        }

        if (stack.getItem()
                instanceof BastardSwordItem bastardSword
                && bastardSword.isTwoHanded(stack)) {
            useBothHandsHeader = true;
            replaceAttributeLines = true;

            displayedDamage =
                    bastardSword
                            .getTwoHandedDisplayedAttackDamage();

            displayedSpeed =
                    bastardSword
                            .getTwoHandedDisplayedAttackSpeed();
        }

        if (stack.getItem()
                instanceof WarhammerItem warhammer
                && warhammer.isSharpSide(stack)) {
            replaceAttributeLines = true;

            displayedDamage =
                    warhammer
                            .getSharpSideDisplayedAttackDamage();

            displayedSpeed =
                    warhammer
                            .getSharpSideDisplayedAttackSpeed();
        }

        /*
         * Include enchantment damage such as Sharpness.
         */
        if (replaceAttributeLines) {
            displayedDamage +=
                    weaponsexpanded$getSharpnessBonus(
                            event,
                            stack
                    );
        }

        int damageLineIndex = -1;
        int speedLineIndex = -1;

        for (int index = 0;
             index < tooltip.size();
             index++) {
            Component line = tooltip.get(index);

            if (useBothHandsHeader
                    && weaponsexpanded$isTranslation(
                    line,
                    "item.modifiers.mainhand"
            )) {
                tooltip.set(
                        index,
                        Component.translatable(
                                "item.modifiers.bothhands"
                        ).setStyle(line.getStyle())
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

        if (damageLineIndex >= 0
                && speedLineIndex >= 0) {
            int insertionIndex =
                    Math.min(
                            damageLineIndex,
                            speedLineIndex
                    );

            int laterIndex =
                    Math.max(
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

    private static boolean weaponsexpanded$isTranslation(
            Component component,
            String translationKey
    ) {
        return component.getContents()
                instanceof TranslatableContents translatable
                && translationKey.equals(
                translatable.getKey()
        );
    }

    private static double weaponsexpanded$getSharpnessBonus(
            ItemTooltipEvent event,
            ItemStack stack
    ) {
        HolderLookup.Provider registries =
                event.getContext().registries();

        if (registries == null) {
            return 0.0D;
        }

        Holder.Reference<Enchantment> sharpness =
                registries.lookupOrThrow(
                        Registries.ENCHANTMENT
                ).getOrThrow(
                        Enchantments.SHARPNESS
                );

        int level = stack.getEnchantmentLevel(
                sharpness
        );

        return level > 0
                ? 0.5D * level + 0.5D
                : 0.0D;
    }

    private static boolean weaponsexpanded$isAttributeLine(
            Component line,
            String attributeTranslationKey
    ) {
        if (!(line.getContents()
                instanceof TranslatableContents translatable)) {
            return false;
        }

        for (Object argument : translatable.getArgs()) {
            if (argument instanceof Component component
                    && weaponsexpanded$isTranslation(
                    component,
                    attributeTranslationKey
            )) {
                return true;
            }
        }

        return false;
    }

    private static MutableComponent
    weaponsexpanded$attributeLine(
            double value,
            String attributeTranslationKey
    ) {
        return Component.literal(
                        " "
                                + weaponsexpanded$formatNumber(
                                value
                        )
                                + " "
                )
                .append(
                        Component.translatable(
                                attributeTranslationKey
                        )
                )
                .withStyle(
                        ChatFormatting.DARK_GREEN
                );
    }

    private static String weaponsexpanded$formatNumber(
            double value
    ) {
        double rounded =
                Math.round(value * 10.0D) / 10.0D;

        if (Math.abs(
                rounded - Math.rint(rounded)
        ) < 1.0E-9D) {
            return Long.toString(
                    Math.round(rounded)
            );
        }

        return String.format(
                Locale.ROOT,
                "%.1f",
                rounded
        );
    }
}
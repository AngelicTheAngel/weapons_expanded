package net.angelic.weaponsexpanded.mixin.two_handed_sword;

import net.angelic.weaponsexpanded.item.custom.BastardSwordItem;
import net.angelic.weaponsexpanded.item.custom.TwoHandedSwordItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Locale;
import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class TwoHandedSwordTooltipMixin {

    @ModifyVariable(
            method = "addAttributeTooltips(Ljava/util/function/Consumer;Lnet/minecraft/world/item/component/TooltipDisplay;Lnet/minecraft/world/entity/player/Player;)V",
            at = @At("HEAD"),
            argsOnly = true,
            index = 1
    )
    private Consumer<Component> weaponsexpanded$replaceMainHandHeaderForTwoHandedSwords(Consumer<Component> original) {
        ItemStack stack = (ItemStack) (Object) this;

        // Only TwoHandedSwordItem should replace the vanilla header
        if (!(stack.getItem() instanceof TwoHandedSwordItem)) {
            return original;
        }

        return text -> {
            if (text.getContents() instanceof TranslatableContents translatable
                    && "item.modifiers.mainhand".equals(translatable.getKey())) {
                // Keep vanilla styling (gray, etc.) by copying the original header's style.
                original.accept(Component.translatable("item.modifiers.bothhands").setStyle(text.getStyle()));
                return;
            }
            original.accept(text);
        };
    }

    @ModifyVariable(
            method = "addAttributeTooltips(Ljava/util/function/Consumer;Lnet/minecraft/world/item/component/TooltipDisplay;Lnet/minecraft/world/entity/player/Player;)V",
            at = @At("HEAD"),
            argsOnly = true,
            index = 1
    )
    private Consumer<Component> weaponsexpanded$appendBothHandsSectionForLongswords(Consumer<Component> original) {
        ItemStack stack = (ItemStack) (Object) this;

        if (stack.getItem() instanceof BastardSwordItem) {
            if (!((BastardSwordItem) stack.getItem()).isTwoHanded(stack)) {
                return original;
            }
        } else {
            return original;
        }

        return text -> {
            if (text.getContents() instanceof TranslatableContents translatable
                    && "item.modifiers.mainhand".equals(translatable.getKey())) {
                // Keep vanilla styling (gray, etc.) by copying the original header's style.
                original.accept(Component.translatable("item.modifiers.bothhands").setStyle(text.getStyle()));
                return;
            }
            original.accept(text);
        };
    }

    @Unique
    private static String weaponsexpanded$formatVanillaNumber(double value) {
        double rounded = Math.round(value * 10.0D) / 10.0D;

        if (Math.abs(rounded - Math.rint(rounded)) < 1.0E-9) {
            return Long.toString(Math.round(rounded));
        }
        return String.format(Locale.ROOT, "%.1f", rounded);
    }

    @Unique
    private static MutableComponent weaponsexpanded$indented(MutableComponent line) {
        return Component.literal(" ").append(line);
    }

    @Unique
    private static MutableComponent weaponsexpanded$vanillaStyleAttributeLine(double value, Component attributeName) {
        return Component.literal(weaponsexpanded$formatVanillaNumber(value) + " ")
                .append(attributeName)
                .withStyle(ChatFormatting.DARK_GREEN);
    }
}

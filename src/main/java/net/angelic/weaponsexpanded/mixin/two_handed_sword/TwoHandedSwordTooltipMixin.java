package net.angelic.weaponsexpanded.mixin.two_handed_sword;

import net.angelic.weaponsexpanded.item.custom.LongswordItem;
import net.angelic.weaponsexpanded.item.custom.TwoHandedSwordItem;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.entity.attribute.EntityAttributes;

import java.util.Locale;
import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class TwoHandedSwordTooltipMixin {

    @ModifyVariable(
            method = "appendAttributeModifiersTooltip(Ljava/util/function/Consumer;Lnet/minecraft/component/type/TooltipDisplayComponent;Lnet/minecraft/entity/player/PlayerEntity;)V",
            at = @At("HEAD"),
            argsOnly = true,
            index = 1
    )
    private Consumer<Text> weaponsexpanded$replaceMainHandHeaderForTwoHandedSwords(Consumer<Text> original) {
        ItemStack stack = (ItemStack) (Object) this;

        // Only TwoHandedSwordItem should replace the vanilla header
        if (!(stack.getItem() instanceof TwoHandedSwordItem)) {
            return original;
        }

        return text -> {
            if (text.getContent() instanceof TranslatableTextContent translatable
                    && "item.modifiers.mainhand".equals(translatable.getKey())) {
                // Keep vanilla styling (gray, etc.) by copying the original header's style.
                original.accept(Text.translatable("item.modifiers.bothhands").setStyle(text.getStyle()));
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
    private static MutableText weaponsexpanded$indented(MutableText line) {
        return Text.literal(" ").append(line);
    }

    @Unique
    private static MutableText weaponsexpanded$vanillaStyleAttributeLine(double value, Text attributeName) {
        return Text.literal(weaponsexpanded$formatVanillaNumber(value) + " ")
                .append(attributeName)
                .formatted(Formatting.DARK_GREEN);
    }

    @Inject(
            method = "appendAttributeModifiersTooltip(Ljava/util/function/Consumer;Lnet/minecraft/component/type/TooltipDisplayComponent;Lnet/minecraft/entity/player/PlayerEntity;)V",
            at = @At("TAIL")
    )
    private void weaponsexpanded$appendBothHandsSectionForLongswords(
            Consumer<Text> textConsumer,
            TooltipDisplayComponent displayComponent,
            net.minecraft.entity.player.PlayerEntity player,
            CallbackInfo ci
    ) {
        ItemStack stack = (ItemStack) (Object) this;
        if (!(stack.getItem() instanceof LongswordItem longsword)) return;

        textConsumer.accept(Text.translatable("item.modifiers.bothhands").formatted(Formatting.GRAY));

        Text attackDamageName = Text.translatable(EntityAttributes.ATTACK_DAMAGE.value().getTranslationKey());
        Text attackSpeedName = Text.translatable(EntityAttributes.ATTACK_SPEED.value().getTranslationKey());

        textConsumer.accept(weaponsexpanded$indented(
                weaponsexpanded$vanillaStyleAttributeLine(longsword.getTwoHandedDisplayedAttackDamage(), attackDamageName)
        ));

        textConsumer.accept(weaponsexpanded$indented(
                weaponsexpanded$vanillaStyleAttributeLine(longsword.getTwoHandedDisplayedAttackSpeed(), attackSpeedName)
        ));
    }
}

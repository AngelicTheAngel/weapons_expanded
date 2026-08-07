package net.angelic.weaponsexpanded.mixin.two_handed_sword;

import net.angelic.weaponsexpanded.item.custom.BastardSwordItem;
import net.angelic.weaponsexpanded.item.custom.TwoHandedSwordItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class TwoHandedSwordTooltipMixin {

    @ModifyVariable(
            method = "appendAttributeModifiersTooltip(Ljava/util/function/Consumer;Lnet/minecraft/entity/player/PlayerEntity;)V",
            at = @At("HEAD"),
            argsOnly = true,
            index = 1
    )
    private Consumer<Text> weaponsexpanded$replaceMainHandHeader(
            Consumer<Text> original
    ) {
        ItemStack stack = (ItemStack) (Object) this;
        Item item = stack.getItem();

        boolean useBothHandsHeader;

        /*
         * Check BastardSwordItem first in case it inherits from
         * TwoHandedSwordItem. Its current mode determines the header.
         */
        if (item instanceof BastardSwordItem bastardSword) {
            useBothHandsHeader = bastardSword.isTwoHanded(stack);
        } else {
            useBothHandsHeader = item instanceof TwoHandedSwordItem;
        }

        if (!useBothHandsHeader) {
            return original;
        }

        return text -> {
            if (text.getContent() instanceof TranslatableTextContent translatable
                    && "item.modifiers.mainhand".equals(translatable.getKey())) {
                original.accept(
                        Text.translatable("item.modifiers.bothhands")
                                .setStyle(text.getStyle())
                );
            } else {
                original.accept(text);
            }
        };
    }
}
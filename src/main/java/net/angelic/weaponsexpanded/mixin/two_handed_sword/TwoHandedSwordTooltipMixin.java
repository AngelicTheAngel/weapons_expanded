package net.angelic.weaponsexpanded.mixin.two_handed_sword;

import net.angelic.weaponsexpanded.item.custom.BastardSwordItem;
import net.angelic.weaponsexpanded.item.custom.TwoHandedSwordItem;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class TwoHandedSwordTooltipMixin {

    @ModifyVariable(
            method = "addAttributeTooltips(Ljava/util/function/Consumer;Lnet/minecraft/world/item/component/TooltipDisplay;Lnet/minecraft/world/entity/player/Player;)V",
            at = @At("HEAD"),
            argsOnly = true,
            index = 1
    )
    private Consumer<Component> weaponsexpanded$replaceMainHandHeaderForTwoHandedWeapons(Consumer<Component> original) {
        ItemStack stack = (ItemStack) (Object) this;

        boolean shouldUseBothHandsHeader = stack.getItem() instanceof TwoHandedSwordItem;

        if (stack.getItem() instanceof BastardSwordItem bastardSword) {
            shouldUseBothHandsHeader = bastardSword.isTwoHanded(stack);
        }

        if (!shouldUseBothHandsHeader) {
            return original;
        }

        return text -> {
            if (text.getContents() instanceof TranslatableContents translatable
                    && "item.modifiers.mainhand".equals(translatable.getKey())) {
                original.accept(Component.translatable("item.modifiers.bothhands").setStyle(text.getStyle()));
                return;
            }

            original.accept(text);
        };
    }
}

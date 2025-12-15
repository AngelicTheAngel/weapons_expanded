package net.angelic.weaponsexpanded.mixin;

import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(CrossbowItem.class)
public abstract class ChainCrossbowFireMixin {

    @Inject(method = "shootAll", at = @At("TAIL"))
    private void weaponsexpanded$autoReloadFromStoredQueue(
            World world,
            LivingEntity shooter,
            Hand hand,
            ItemStack stack,
            float speed,
            float divergence,
            @Nullable LivingEntity target,
            CallbackInfo ci
    ) {
        if (world.isClient()) return;
        if (!(stack.getItem() instanceof ChainCrossbowItem)) return;

        // Apply 10-tick cooldown after firing
        if (shooter instanceof PlayerEntity player) {
            player.getItemCooldownManager().set(stack, 10);
        }

        ChargedProjectilesComponent charged =
                stack.getOrDefault(DataComponentTypes.CHARGED_PROJECTILES, ChargedProjectilesComponent.DEFAULT);

        // Only refill when the chamber is actually empty
        if (!charged.isEmpty()) return;

        List<ItemStack> nextChamber = ChainCrossbowItem.weaponsexpanded$popNextChamber(world, stack);
        if (nextChamber.isEmpty()) return;

        stack.set(DataComponentTypes.CHARGED_PROJECTILES, ChargedProjectilesComponent.of(nextChamber));
    }
}
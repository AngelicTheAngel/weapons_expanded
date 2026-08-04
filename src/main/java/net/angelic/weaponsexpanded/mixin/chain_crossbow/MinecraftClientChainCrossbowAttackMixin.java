package net.angelic.weaponsexpanded.mixin.chain_crossbow;

import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.angelic.weaponsexpanded.network.FireChainCrossbowPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientChainCrossbowAttackMixin {

    @Inject(
            method = "doAttack",
            at = @At("HEAD"),
            cancellable = true
    )
    private void weaponsexpanded$fireChainCrossbowInsteadOfPunch(
            CallbackInfoReturnable<Boolean> cir
    ) {
        MinecraftClient client =
                (MinecraftClient) (Object) this;

        if (client.player == null) {
            return;
        }

        ItemStack stack =
                client.player.getMainHandStack();

        if (!(stack.getItem()
                instanceof ChainCrossbowItem)) {
            return;
        }

        if (!CrossbowItem.isCharged(stack)) {
            return;
        }

        /*
         * Prevent both firing and the normal punch action while the
         * chain crossbow is cooling down.
         */
        if (client.player.getItemCooldownManager()
                .isCoolingDown(stack.getItem())) {
            cir.setReturnValue(false);
            return;
        }

        ClientPlayNetworking.send(
                FireChainCrossbowPayload.ID,
                PacketByteBufs.empty()
        );

        // Prevent Minecraft from also performing a normal attack.
        cir.setReturnValue(false);
    }
}
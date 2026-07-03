package net.angelic.weaponsexpanded.mixin.chain_crossbow;

import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.angelic.weaponsexpanded.network.FireChainCrossbowPayload;
import net.angelic.weaponsexpanded.network.ModPackets;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public abstract class MinecraftClientChainCrossbowAttackMixin {

    @Inject(method = "startAttack", at = @At("HEAD"), cancellable = true)
    private void weaponsexpanded$fireChainCrossbowInsteadOfPunch(CallbackInfoReturnable<Boolean> cir) {
        Minecraft client = (Minecraft) (Object) this;
        if (client.player == null) return;

        ItemStack stack = client.player.getMainHandItem();
        if (!(stack.getItem() instanceof ChainCrossbowItem)) return;

        if (!net.minecraft.world.item.CrossbowItem.isCharged(stack)) return;

        // Send "fire" request and cancel normal attack (prevents punching/mining while loaded)
        ModPackets.sendToServer(new FireChainCrossbowPayload());
        cir.setReturnValue(false);
    }
}

package net.angelic.weaponsexpanded.mixin;

import net.angelic.weaponsexpanded.item.ModItems;
import net.angelic.weaponsexpanded.item.custom.LongbowItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class LongbowFovMixin {

    @Unique
    private static float weaponsexpanded$currentZoom = 0.0f;

    @Unique
    private static float weaponsexpanded$lerp(float from, float to, float speed) {
        return from + (to - from) * speed;
    }

    @Inject(method = "getFov", at = @At("RETURN"), cancellable = true)
    private void weaponsexpanded$longbowFovZoom(
            net.minecraft.client.render.Camera camera,
            float tickDelta,
            boolean changingFov,
            CallbackInfoReturnable<Float> cir
    ) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        float targetZoom = 0.0f;

        if (client.player.isUsingItem()) {
            ItemStack active = client.player.getActiveItem();
            if (active.isOf(ModItems.LONGBOW)) {
                int usedTicks = active.getMaxUseTime(client.player) - client.player.getItemUseTimeLeft();
                float usedTicksSmooth = usedTicks + tickDelta;

                float pull = usedTicksSmooth / (float) LongbowItem.getFullDrawTicks();
                pull = Math.min(Math.max(pull, 0.0f), 1.0f);

                targetZoom = pull;
            }
        }

        // Different speeds feel more “vanilla”: faster zoom-in, slower zoom-out
        float speedIn = 0.35f;
        float speedOut = 0.15f;

        float speed = (targetZoom > weaponsexpanded$currentZoom) ? speedIn : speedOut;
        weaponsexpanded$currentZoom = weaponsexpanded$lerp(weaponsexpanded$currentZoom, targetZoom, speed);

        // Prevent tiny drifting forever
        if (weaponsexpanded$currentZoom < 0.0005f) weaponsexpanded$currentZoom = 0.0f;

        float baseFov = cir.getReturnValue();
        float zoomStrength = 0.15f; // how strong full draw zoom is (vanilla-ish)
        float zoomedFov = baseFov * (1.0f - weaponsexpanded$currentZoom * zoomStrength);

        cir.setReturnValue(zoomedFov);
    }
}

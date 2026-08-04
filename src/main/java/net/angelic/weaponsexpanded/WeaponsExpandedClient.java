package net.angelic.weaponsexpanded;

import net.angelic.weaponsexpanded.client.ModModelPredicates;
import net.angelic.weaponsexpanded.client.render.HeavyArrowEntityRenderer;
import net.angelic.weaponsexpanded.entity.ModEntities;
import net.angelic.weaponsexpanded.item.custom.BastardSwordItem;
import net.angelic.weaponsexpanded.item.custom.WarhammerItem;
import net.angelic.weaponsexpanded.network.ToggleBastardSwordModePayload;
import net.angelic.weaponsexpanded.network.ToggleWarhammerModePayload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.entity.ArrowEntityRenderer;
import net.minecraft.item.ItemStack;
import org.lwjgl.glfw.GLFW;

public class WeaponsExpandedClient implements ClientModInitializer {

    private static final String WEAPONSEXPANDED$KEY_CATEGORY =
            "key.category.weaponsexpanded.general";

    private static final String WEAPONSEXPANDED$KEY_TOGGLE_WEAPON_MODE =
            "key.weaponsexpanded.toggle_bastard_sword_mode";

    private static KeyBinding weaponsexpanded$toggleWeaponModeKey;

    @Override
    public void onInitializeClient() {
        ModModelPredicates.register();

        EntityRendererRegistry.register(
                ModEntities.HEAVY_ARROW,
                HeavyArrowEntityRenderer::new
        );

        EntityRendererRegistry.register(
                ModEntities.EXPLOSIVE_ARROW,
                ArrowEntityRenderer::new
        );

        weaponsexpanded$toggleWeaponModeKey =
                KeyBindingHelper.registerKeyBinding(
                        new KeyBinding(
                                WEAPONSEXPANDED$KEY_TOGGLE_WEAPON_MODE,
                                GLFW.GLFW_KEY_V,
                                WEAPONSEXPANDED$KEY_CATEGORY
                        )
                );

        /*
         * Do not register a chain-crossbow attack tick handler here.
         * MinecraftClientChainCrossbowAttackMixin handles firing.
         */
        ClientTickEvents.END_CLIENT_TICK.register(
                WeaponsExpandedClient::
                        weaponsexpanded$handleWeaponToggleKey
        );
    }

    private static void weaponsexpanded$handleWeaponToggleKey(
            MinecraftClient client
    ) {
        if (client.player == null) {
            return;
        }

        while (weaponsexpanded$toggleWeaponModeKey.wasPressed()) {
            ItemStack stack = client.player.getMainHandStack();

            if (stack.getItem() instanceof BastardSwordItem) {
                ClientPlayNetworking.send(
                        ToggleBastardSwordModePayload.ID,
                        PacketByteBufs.empty()
                );

                client.player.resetLastAttackedTicks();
            } else if (stack.getItem() instanceof WarhammerItem) {
                ClientPlayNetworking.send(
                        ToggleWarhammerModePayload.ID,
                        PacketByteBufs.empty()
                );

                client.player.resetLastAttackedTicks();
            }
        }
    }
}
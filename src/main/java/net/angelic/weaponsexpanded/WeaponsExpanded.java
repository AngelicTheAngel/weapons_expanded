package net.angelic.weaponsexpanded;

import net.angelic.weaponsexpanded.effect.ModEffects;
import net.angelic.weaponsexpanded.enchantment.ModEnchantmentEffects;
import net.angelic.weaponsexpanded.entity.ModEntities;
import net.angelic.weaponsexpanded.item.ModItems;
import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.angelic.weaponsexpanded.network.FireChainCrossbowPayload;
import net.angelic.weaponsexpanded.sound.ModSounds;
import net.fabricmc.api.ModInitializer;
import net.angelic.weaponsexpanded.network.ModPackets;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeaponsExpanded implements ModInitializer {
    public static final String MOD_ID = "weaponsexpanded";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
		ModItems.registerModItems();
		ModEnchantmentEffects.registerEnchantmentEffects();
		ModEffects.registerEffects();
		ModEntities.registerEntities();
        ModSounds.register();

        // Register payload types once (safe if called again elsewhere)
        ModPackets.register();

        ServerPlayNetworking.registerGlobalReceiver(FireChainCrossbowPayload.ID, (payload, context) -> context.server().execute(() -> weaponsexpanded$tryFireChainCrossbow(context.player())));
    }

    private static void weaponsexpanded$tryFireChainCrossbow(PlayerEntity player) {
        ItemStack stack = player.getMainHandStack();
        if (!(stack.getItem() instanceof ChainCrossbowItem chainCrossbow)) return;
        if (!net.minecraft.item.CrossbowItem.isCharged(stack)) return;

        ChargedProjectilesComponent charged =
                stack.getOrDefault(DataComponentTypes.CHARGED_PROJECTILES, ChargedProjectilesComponent.DEFAULT);

        float speed = charged.contains(net.minecraft.item.Items.FIREWORK_ROCKET) ? 1.6F : 3.15F;

        chainCrossbow.shootAll(player.getEntityWorld(), player, Hand.MAIN_HAND, stack, speed, 1.0F, null);
    }
}
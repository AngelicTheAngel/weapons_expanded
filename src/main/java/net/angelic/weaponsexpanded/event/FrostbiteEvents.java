package net.angelic.weaponsexpanded.event;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.effect.ModEffects;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;

@EventBusSubscriber(modid = WeaponsExpanded.MOD_ID)
public final class FrostbiteEvents {

    private FrostbiteEvents() {
    }

    /*
     * Handles Frostbite being removed manually, including removal
     * caused by fire damage.
     */
    @SubscribeEvent
    public static void onEffectRemoved(
            MobEffectEvent.Remove event
    ) {
        if (!event.getEffect().is(
                ModEffects.FROSTBITE.getKey()
        )) {
            return;
        }

        weaponsexpanded$clearFrozenTicks(
                event.getEntity()
        );
    }

    /*
     * MobEffectEvent.Remove is not fired when an effect expires
     * naturally, so expiration requires a separate event.
     */
    @SubscribeEvent
    public static void onEffectExpired(
            MobEffectEvent.Expired event
    ) {
        MobEffectInstance effect =
                event.getEffectInstance();

        if (effect == null
                || !effect.getEffect().is(
                ModEffects.FROSTBITE.getKey()
        )) {
            return;
        }

        weaponsexpanded$clearFrozenTicks(
                event.getEntity()
        );
    }

    @SubscribeEvent
    public static void onLivingDamage(
            LivingDamageEvent.Post event
    ) {
        if (!event.getSource().is(DamageTypeTags.IS_FIRE)) {
            return;
        }

        LivingEntity entity = event.getEntity();

        if (entity.hasEffect(ModEffects.FROSTBITE)) {
            entity.removeEffect(ModEffects.FROSTBITE);
        }
    }

    private static void weaponsexpanded$clearFrozenTicks(
            LivingEntity entity
    ) {
        /*
         * Powder snow applies freezing independently of Frostbite,
         * so do not clear the frozen ticks while the entity is
         * standing inside powder snow.
         */
        boolean inPowderSnow =
                entity.level()
                        .getBlockState(
                                entity.blockPosition()
                        )
                        .is(Blocks.POWDER_SNOW);

        if (!inPowderSnow) {
            entity.setTicksFrozen(0);
        }
    }
}
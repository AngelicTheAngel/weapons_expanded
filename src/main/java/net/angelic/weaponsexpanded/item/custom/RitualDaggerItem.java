package net.angelic.weaponsexpanded.item.custom;

import net.angelic.weaponsexpanded.datagen.ModDamageTypes;
import net.angelic.weaponsexpanded.effect.ModEffects;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.Level;

public class RitualDaggerItem extends Item {
    public RitualDaggerItem(ToolMaterial material, float attackDamage, float attackSpeed, Properties properties) {
        super(properties.sword(material, attackDamage, attackSpeed).component(DataComponents.MAX_DAMAGE, 100));
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if(player.hasEffect(MobEffects.STRENGTH)) {
            if(player.getEffect(MobEffects.STRENGTH).getAmplifier() >= 1) {
                player.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 1200, player.getEffect(MobEffects.STRENGTH).getAmplifier()));
            } else {
                player.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 1200, player.getEffect(MobEffects.STRENGTH).getAmplifier() + 1));
            }
        } else {
            player.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 1200, 0));
        }

        if(player.hasEffect(MobEffects.SPEED)) {
            if(player.getEffect(MobEffects.SPEED).getAmplifier() >= 1) {
                player.addEffect(new MobEffectInstance(MobEffects.SPEED, 1200, player.getEffect(MobEffects.SPEED).getAmplifier()));
            } else {
                player.addEffect(new MobEffectInstance(MobEffects.SPEED, 1200, player.getEffect(MobEffects.SPEED).getAmplifier() + 1));
            }
        } else {
            player.addEffect(new MobEffectInstance(MobEffects.SPEED, 1200, 0));
        }

        if(player.hasEffect(ModEffects.DEGRADATION)) {
            if(player.getEffect(ModEffects.DEGRADATION).getAmplifier() >= 1 && player.getEffect(MobEffects.STRENGTH).getAmplifier() >= 1) {
                player.addEffect(new MobEffectInstance(ModEffects.DEGRADATION, 1200, player.getEffect(ModEffects.DEGRADATION).getAmplifier()));
                if (player.level() instanceof ServerLevel serverLevel) {
                    player.hurtServer(serverLevel, ModDamageTypes.create(serverLevel, ModDamageTypes.RITUAL), 2.0F);
                }
            } else {
                player.addEffect(new MobEffectInstance(ModEffects.DEGRADATION, 1200, player.getEffect(ModEffects.DEGRADATION).getAmplifier() + 1));
            }
        } else {
            player.addEffect(new MobEffectInstance(ModEffects.DEGRADATION, 1200, 0));
        }

        player.getItemInHand(hand).hurtAndBreak(1, player, hand.asEquipmentSlot());
        player.getCooldowns().addCooldown(player.getItemInHand(hand), 20);
        return super.use(level, player, hand);
    }
}

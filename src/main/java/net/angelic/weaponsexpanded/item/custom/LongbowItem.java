package net.angelic.weaponsexpanded.item.custom;

import java.util.Optional;

import net.angelic.weaponsexpanded.util.ProjectileEnchantmentApplier;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

public class LongbowItem extends BowItem {
    private static final int FULL_DRAW_TICKS = 32;   // vanilla bow: 20
    private static final float VELOCITY_MULT = 4f;   // vanilla uses 3.0f

    public LongbowItem(Properties settings) {
        super(settings);
    }

    private static float getLongbowPullProgress(int useTicks) {
        float f = (float) useTicks / (float) FULL_DRAW_TICKS;
        f = (f * f + f * 2.0f) / 3.0f;
        return Math.min(f, 1.0f);
    }

    private static boolean hasInfinity(ItemStack bowStack, Level world) {
        ResourceKey<Enchantment> infinityKey =
                ResourceKey.create(Registries.ENCHANTMENT, Identifier.withDefaultNamespace("infinity"));

        Optional<Holder.Reference<Enchantment>> infinityOpt =
                world.registryAccess()
                        .lookupOrThrow(Registries.ENCHANTMENT)
                        .get(infinityKey);

        if (infinityOpt.isEmpty()) return false;

        Holder<Enchantment> infinity = infinityOpt.get();
        return EnchantmentHelper.getItemEnchantmentLevel(infinity, bowStack) > 0;
    }

    private static boolean isNormalArrow(ItemStack ammo) {
        return ammo.is(Items.ARROW);
    }

    @Override
    public boolean releaseUsing(ItemStack stack, Level world, LivingEntity user, int remainingUseTicks) {
        if (!(user instanceof Player player)) return false;

        ItemStack ammo = player.getProjectile(stack);

        boolean hasInfinity = hasInfinity(stack, world);

        // Infinity should only "cover" normal arrows
        boolean infinityCoversThisShot = hasInfinity && (ammo.isEmpty() || isNormalArrow(ammo));

        // Only allow shooting with NO ammo if Infinity is present (and it will shoot a normal arrow)
        boolean canShootWithoutAmmo = player.getAbilities().instabuild || infinityCoversThisShot;

        int usedTicks = this.getUseDuration(stack, user) - remainingUseTicks;
        float pull = getLongbowPullProgress(usedTicks);

        if (pull < 0.1f) return false;

        if (ammo.isEmpty()) {
            if (!canShootWithoutAmmo) return false;
            ammo = new ItemStack(Items.ARROW);
        }

        if (!(ammo.getItem() instanceof ArrowItem arrowItem)) return false;

        // Recompute now that ammo is guaranteed non-empty
        boolean infinityFreeNormalArrow = hasInfinity && isNormalArrow(ammo);

        if (!world.isClientSide()) {
            AbstractArrow projectile = arrowItem.createArrow(world, ammo, player, stack);

            // Apply Freeze/Flame to ANY projectile fired from the longbow
            ProjectileEnchantmentApplier.applyFreezeAndFlame(world, stack, projectile);

            // Pickup rules:
            // - Normal arrow + Infinity: survival cannot pick up (vanilla-ish)
            // - Other arrows: allow pickup in survival
            if (!player.getAbilities().instabuild) {
                if (infinityFreeNormalArrow) {
                    projectile.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                } else {
                    projectile.pickup = AbstractArrow.Pickup.ALLOWED;
                }
            }

            projectile.shootFromRotation(
                    player,
                    player.getXRot(),
                    player.getYRot(),
                    0.0f,
                    pull * VELOCITY_MULT,
                    1.0f
            );

            world.addFreshEntity(projectile);
        }

        world.playSound(
                null,
                player.getX(), player.getY(), player.getZ(),
                SoundEvents.ARROW_SHOOT,
                SoundSource.PLAYERS,
                1.0f,
                1.0f / (world.getRandom().nextFloat() * 0.4f + 1.2f) + pull * 0.5f
        );

        player.awardStat(Stats.ITEM_USED.get(this));

        // Damage bow durability (vanilla behavior). Unbreaking is handled by damage().
        if (!player.getAbilities().instabuild) {
            InteractionHand hand = player.getUsedItemHand();
            EquipmentSlot slot = (hand == InteractionHand.MAIN_HAND) ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
            stack.hurtAndBreak(1, player, slot);
        }

        // Consume ammo:
        // - Creative: never consume
        // - Infinity: only prevents consuming normal arrows
        if (!player.getAbilities().instabuild && !infinityFreeNormalArrow) {
            ammo.shrink(1);
            if (ammo.isEmpty()) {
                player.getInventory().removeItem(ammo);
            }
        }

        return true;
    }

    public static int getFullDrawTicks() {
        return FULL_DRAW_TICKS;
    }
}

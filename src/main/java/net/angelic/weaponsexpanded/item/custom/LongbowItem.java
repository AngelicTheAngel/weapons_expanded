package net.angelic.weaponsexpanded.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.Optional;

import net.angelic.weaponsexpanded.util.ProjectileEnchantmentApplier;

public class LongbowItem extends BowItem {
    // Vanilla bow effectively “full draws” at 20 ticks
    private static final int FULL_DRAW_TICKS = 32;     // longbow: slower draw
    private static final float VELOCITY_MULT = 4.0f;   // vanilla uses 3.0f

    public LongbowItem(Settings settings) {
        super(settings);
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        // How long you *can* hold it drawn. This doesn’t define full power,
        // but it affects client pull animation and how long “using” can last.
        return 72000;
    }

    private static float getLongbowPullProgress(int useTicks) {
        float f = (float) useTicks / (float) FULL_DRAW_TICKS;
        f = (f * f + f * 2.0f) / 3.0f;
        return Math.min(f, 1.0f);
    }

    private static boolean hasInfinity(ItemStack bowStack, World world) {
        RegistryKey<Enchantment> infinityKey =
                RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.ofVanilla("infinity"));

        Optional<RegistryEntry.Reference<Enchantment>> infinityOpt =
                world.getRegistryManager()
                        .getOrThrow(RegistryKeys.ENCHANTMENT)
                        .getOptional(infinityKey);

        if (infinityOpt.isEmpty()) return false;

        RegistryEntry<Enchantment> infinity = infinityOpt.get();
        return EnchantmentHelper.getLevel(infinity, bowStack) > 0;
    }

    private static boolean isNormalArrow(ItemStack ammo) {
        return ammo.isOf(Items.ARROW);
    }

    @Override
    public boolean onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!(user instanceof PlayerEntity player)) return false;

        ItemStack ammo = player.getProjectileType(stack);

        boolean hasInfinity = hasInfinity(stack, world);

        // Infinity should only "cover" normal arrows
        boolean infinityCoversThisShot = hasInfinity && (ammo.isEmpty() || isNormalArrow(ammo));

        // Only allow shooting with NO ammo if Infinity is present (and it will shoot a normal arrow)
        boolean canShootWithoutAmmo = player.getAbilities().creativeMode || infinityCoversThisShot;

        int usedTicks = this.getMaxUseTime(stack, user) - remainingUseTicks;
        float pull = getLongbowPullProgress(usedTicks);

        if (pull < 0.1f) return false;

        if (ammo.isEmpty()) {
            if (!canShootWithoutAmmo) return false;
            ammo = new ItemStack(Items.ARROW);
        }

        if (!(ammo.getItem() instanceof ArrowItem arrowItem)) return false;

        // Recompute now that ammo is guaranteed non-empty
        boolean infinityFreeNormalArrow = hasInfinity && isNormalArrow(ammo);

        if (!world.isClient()) {
            PersistentProjectileEntity projectile = arrowItem.createArrow(world, ammo, player, stack);

            // Apply Freeze/Flame to ANY projectile fired from the longbow
            ProjectileEnchantmentApplier.applyFreezeAndFlame(world, stack, projectile);

            // Pickup rules:
            // - Normal arrow + Infinity: survival cannot pick up (vanilla-ish)
            // - Other arrows: allow pickup in survival
            if (!player.getAbilities().creativeMode) {
                if (infinityFreeNormalArrow) {
                    projectile.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                } else {
                    projectile.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
                }
            }

            projectile.setVelocity(
                    player,
                    player.getPitch(),
                    player.getYaw(),
                    0.0f,
                    pull * VELOCITY_MULT,
                    1.0f
            );

            world.spawnEntity(projectile);
        }

        world.playSound(
                null,
                player.getX(), player.getY(), player.getZ(),
                SoundEvents.ENTITY_ARROW_SHOOT,
                SoundCategory.PLAYERS,
                1.0f,
                1.0f / (world.getRandom().nextFloat() * 0.4f + 1.2f) + pull * 0.5f
        );

        player.incrementStat(Stats.USED.getOrCreateStat(this));

        // Consume ammo:
        // - Creative: never consume
        // - Infinity: only prevents consuming normal arrows
        if (!player.getAbilities().creativeMode && !infinityFreeNormalArrow) {
            ammo.decrement(1);
            if (ammo.isEmpty()) {
                player.getInventory().removeOne(ammo);
            }
        }

        return true;
    }

    public static int getFullDrawTicks() {
        return FULL_DRAW_TICKS;
    }
}

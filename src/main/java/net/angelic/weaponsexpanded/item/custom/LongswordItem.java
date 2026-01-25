package net.angelic.weaponsexpanded.item.custom;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;

public class LongswordItem extends Item {

    private final ToolMaterial material;
    private final float twoHandedAttackDamage;
    private final float twoHandedAttackSpeed;

    public LongswordItem(
            ToolMaterial material,
            float attackDamage,
            float attackSpeed,
            float twoHandedAttackDamage,
            float twoHandedAttackSpeed,
            Settings settings
    ) {
        super(settings.sword(material, attackDamage, attackSpeed));
        this.material = material;
        this.twoHandedAttackDamage = twoHandedAttackDamage;
        this.twoHandedAttackSpeed = twoHandedAttackSpeed;
    }

    public float getTwoHandedAttackDamage() {
        return twoHandedAttackDamage;
    }

    public float getTwoHandedAttackSpeed() {
        return twoHandedAttackSpeed;
    }

    /**
     * Matches vanilla tooltip math:
     * - Attack Damage tooltip shows (1.0 base + damage modifier)
     * - Damage modifier for weapons is (material bonus + item damage value)
     */
    public double getTwoHandedDisplayedAttackDamage() {
        return 1.0D + (double) material.attackDamageBonus() + (double) twoHandedAttackDamage;
    }

    /**
     * Matches vanilla tooltip math:
     * - Attack Speed tooltip shows (4.0 base + speed modifier)
     */
    public double getTwoHandedDisplayedAttackSpeed() {
        return 4.0D + (double) twoHandedAttackSpeed;
    }
}

package com.captivator.herbalis;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.damagesource.DamageSource;

public class BleedingEffect extends MobEffect {
    public BleedingEffect() {
        // Category: HARMFUL, Color: Dark Red (0x990000)
        super(MobEffectCategory.HARMFUL, 0x990000);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        // Damage the entity by 1.0F (half a heart)
        // You can use DamageSource.MAGIC or create a custom one
        entity.hurt(DamageSource.MAGIC, 1.0F);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        // This logic controls how OFTEN the damage happens.
        // For Poison, it's usually every 25 ticks.
        // Let's make Bleeding happen every 40 ticks (2 seconds).
        int j = 40 >> amplifier;
        if (j > 0) {
            return duration % j == 0;
        } else {
            return true;
        }
    }
}
package com.captivator.herbalis.items;

import com.captivator.herbalis.registry.ModMobEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Items;

import java.util.Map;
import java.util.function.Supplier;

public class HerbTeaItem extends Item {
    private final Map<Supplier<MobEffect>, Integer> effectsToRemove;

    public HerbTeaItem(Properties props, Map<Supplier<MobEffect>, Integer> effectsToRemove) {
        super(props);
        this.effectsToRemove = effectsToRemove;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide) {
            for (Map.Entry<Supplier<MobEffect>, Integer> entry : effectsToRemove.entrySet()) {
                MobEffect effect = entry.getKey().get();
                int reductionTicks = entry.getValue();
                
                if (entity.hasEffect(effect)) {
                    var instance = entity.getEffect(effect);
                    if (instance != null) {
                        int newDuration = instance.getDuration() - reductionTicks;
                        entity.removeEffect(effect);
                        if (newDuration > 0) {
                            entity.addEffect(new net.minecraft.world.effect.MobEffectInstance(effect, newDuration, instance.getAmplifier()));
                        }
                    }
                }
            }
        }
        return super.finishUsingItem(stack, level, entity);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }
}

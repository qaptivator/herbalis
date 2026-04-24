package com.captivator.herbalis.registry;

import com.captivator.herbalis.effects.BleedingEffect;
import com.captivator.herbalis.HerbalisMod;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, HerbalisMod.MODID);

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }

    public static final RegistryObject<MobEffect> BLEEDING_EFFECT =  MOB_EFFECTS.register("bleeding_effect", BleedingEffect::new);
    public static final RegistryObject<MobEffect> PAIN_EFFECT = MOB_EFFECTS.register("pain", () -> new com.captivator.herbalis.effects.SimpleEffect(MobEffectCategory.HARMFUL, 0x808080));
    public static final RegistryObject<MobEffect> SORENESS_EFFECT = MOB_EFFECTS.register("soreness", () -> new com.captivator.herbalis.effects.SimpleEffect(MobEffectCategory.HARMFUL, 0x8B4513));
    public static final RegistryObject<MobEffect> SHORTNESS_OF_BREATH_EFFECT = MOB_EFFECTS.register("shortness_of_breath", () -> new com.captivator.herbalis.effects.SimpleEffect(MobEffectCategory.HARMFUL, 0xADD8E6));
    public static final RegistryObject<MobEffect> INDIGESTION_EFFECT = MOB_EFFECTS.register("indigestion", () -> new com.captivator.herbalis.effects.SimpleEffect(MobEffectCategory.HARMFUL, 0x00FF00));
    public static final RegistryObject<MobEffect> ANXIETY_EFFECT = MOB_EFFECTS.register("anxiety", () -> new com.captivator.herbalis.effects.SimpleEffect(MobEffectCategory.HARMFUL, 0x4B0082));
}

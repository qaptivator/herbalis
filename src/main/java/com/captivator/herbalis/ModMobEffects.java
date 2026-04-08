package com.captivator.herbalis;

import net.minecraft.world.effect.MobEffect;
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
}

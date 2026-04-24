package com.captivator.herbalis.registry;

import com.captivator.herbalis.HerbalisMod;
import com.captivator.herbalis.items.WaterCraftedItem;
import com.captivator.herbalis.stamina.StaminaProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HerbalisMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            if (!event.getObject().getCapability(StaminaProvider.PLAYER_STAMINA).isPresent()) {
                event.addCapability(new ResourceLocation(HerbalisMod.MODID, "stamina"), new StaminaProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Player player = event.player;
            player.getCapability(StaminaProvider.PLAYER_STAMINA).ifPresent(stamina -> {
                // Stamina Drain
                boolean isSprinting = player.isSprinting();
                boolean isSore = player.hasEffect(ModMobEffects.SORENESS_EFFECT.get());
                boolean isShortOfBreath = player.hasEffect(ModMobEffects.SHORTNESS_OF_BREATH_EFFECT.get());

                float drain = 0;
                if (isSprinting) drain += 0.1f;
                if (isSore) drain *= 1.5f;

                if (drain > 0) {
                    stamina.subStamina(drain);
                } else {
                    // Recovery
                    float recovery = 0.05f;
                    if (isSore) recovery *= 0.5f;
                    if (isShortOfBreath) recovery *= 0.2f;
                    if (player.hasEffect(ModMobEffects.BLEEDING_EFFECT.get())) recovery *= 0.8f;
                    
                    // Nettle tonic effect
                    if (player.hasEffect(MobEffects.REGENERATION)) recovery *= 1.2f; // Placeholder for tonic

                    stamina.addStamina(recovery);
                }

                if (stamina.getStamina() <= 0 && !isShortOfBreath) {
                    player.addEffect(new MobEffectInstance(ModMobEffects.SHORTNESS_OF_BREATH_EFFECT.get(), 100));
                    player.setSprinting(false);
                }
            });
            
            // Indigestion nausea
            if (player.hasEffect(ModMobEffects.INDIGESTION_EFFECT.get()) && player.getRandom().nextFloat() < 0.001f) {
                player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 100));
            }
        }
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();
        if (event.getSource().getDirectEntity() instanceof LivingEntity || event.getSource() == net.minecraft.world.damagesource.DamageSource.FALL) {
            if (entity.getRandom().nextFloat() < 0.3f) {
                entity.addEffect(new MobEffectInstance(ModMobEffects.BLEEDING_EFFECT.get(), 600));
            }
            entity.addEffect(new MobEffectInstance(ModMobEffects.PAIN_EFFECT.get(), 400));
            
            if (event.getSource() == net.minecraft.world.damagesource.DamageSource.FALL && event.getAmount() > 4.0f) {
                entity.addEffect(new MobEffectInstance(ModMobEffects.SORENESS_EFFECT.get(), 1200));
            }
        }
        
        // Bleeding gets worse
        if (entity.hasEffect(ModMobEffects.BLEEDING_EFFECT.get())) {
            MobEffectInstance effect = entity.getEffect(ModMobEffects.BLEEDING_EFFECT.get());
            if (effect != null) {
                int amplifier = effect.getAmplifier();
                if (amplifier < 3 && entity.getRandom().nextFloat() < 0.2f) {
                    entity.addEffect(new MobEffectInstance(ModMobEffects.BLEEDING_EFFECT.get(), effect.getDuration(), amplifier + 1));
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLivingHeal(LivingHealEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.hasEffect(ModMobEffects.BLEEDING_EFFECT.get())) {
            event.setCanceled(true);
        } else if (entity.hasEffect(ModMobEffects.PAIN_EFFECT.get())) {
            event.setAmount(event.getAmount() * 0.5f);
        }
    }

    @Mod.EventBusSubscriber(modid = HerbalisMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModBusEvents {
        @SubscribeEvent
        public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
            event.register(com.captivator.herbalis.stamina.IStamina.class);
        }
    }

    @SubscribeEvent
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        ItemStack stack = event.getItemStack();
        
        if (stack.is(ModItems.PLANTAGO_LEAF_ITEM.get()) && player.hasEffect(ModMobEffects.BLEEDING_EFFECT.get())) {
            if (!player.getLevel().isClientSide) {
                MobEffectInstance effect = player.getEffect(ModMobEffects.BLEEDING_EFFECT.get());
                if (effect != null) {
                    int newDuration = effect.getDuration() - 200;
                    player.removeEffect(ModMobEffects.BLEEDING_EFFECT.get());
                    if (newDuration > 0) {
                        player.addEffect(new MobEffectInstance(ModMobEffects.BLEEDING_EFFECT.get(), newDuration, effect.getAmplifier()));
                    }
                    if (!player.getAbilities().instabuild) {
                        stack.shrink(1);
                    }
                    player.getLevel().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.GRASS_HIT, SoundSource.PLAYERS, 1.0f, 1.0f);
                }
            }
            event.setCancellationResult(InteractionResult.sidedSuccess(player.getLevel().isClientSide));
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);
        ItemStack stack = event.getItemStack();
        Player player = event.getEntity();

        if (stack.getItem() instanceof WaterCraftedItem waterItem) {
            boolean created = false;
            if (state.is(Blocks.WATER) || state.getFluidState().isSource()) {
                created = true;
            } else if (state.getBlock() instanceof LayeredCauldronBlock && state.getValue(LayeredCauldronBlock.LEVEL) > 0) {
                if (!level.isClientSide) {
                    LayeredCauldronBlock.lowerFillLevel(state, level, pos);
                }
                created = true;
            }

            if (created) {
                if (!level.isClientSide) {
                    ItemStack result = new ItemStack(waterItem.getResult());
                    if (!player.getAbilities().instabuild) {
                        stack.shrink(1);
                    }
                    if (!player.getInventory().add(result)) {
                        player.drop(result, false);
                    }
                    level.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                }
                event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide));
                event.setCanceled(true);
            }
        }
    }
}

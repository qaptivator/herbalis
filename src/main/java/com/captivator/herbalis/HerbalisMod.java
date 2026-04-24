package com.captivator.herbalis;

import com.captivator.herbalis.blocks.DryingRackBlockEntityRenderer;
import com.captivator.herbalis.blocks.MortarAndPestleBlockEntityRenderer;
import com.captivator.herbalis.registry.ModBlockEntities;
import com.captivator.herbalis.registry.ModBlocks;
import com.captivator.herbalis.registry.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(HerbalisMod.MODID)
public class HerbalisMod
{
    public static final String MODID = "herbalis";
    private static final Logger LOGGER = LogUtils.getLogger();

    public HerbalisMod(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();
        //modEventBus.addListener(this::commonSetup);

        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        com.captivator.herbalis.registry.ModMobEffects.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void registerRenderers(net.minecraftforge.client.event.EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(ModBlockEntities.DRYING_RACK_BE.get(), DryingRackBlockEntityRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntities.MORTAR_BE.get(), MortarAndPestleBlockEntityRenderer::new);
        }

        /*@SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            //LOGGER.info("HELLO FROM CLIENT SETUP");
        }*/
    }

    /*private void commonSetup(final FMLCommonSetupEvent event)
    {
        //LOGGER.info("HELLO FROM COMMON SETUP");
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        //LOGGER.info("HELLO from server starting");
    }*/
}

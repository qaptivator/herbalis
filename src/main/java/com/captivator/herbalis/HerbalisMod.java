package com.captivator.herbalis;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.LootContext;
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
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(HerbalisMod.MODID)
public class HerbalisMod
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "herbalis";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    // Create a Deferred Register to hold Block Entities
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);

    // Creates a new Block with the id "examplemod:example_block", combining the namespace and path
    //public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    // Creates a new BlockItem with the id "examplemod:example_block", combining the namespace and path
    //public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block", () -> new BlockItem(EXAMPLE_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final CreativeModeTab HERBALIS_TAB = new CreativeModeTab("herbalis") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(PLANTAGO_LEAF_ITEM.get());
        }

        @Override
        public void fillItemList(net.minecraft.core.NonNullList<ItemStack> items) {
            items.add(new ItemStack(PLANTAGO_BLOCK_ITEM.get()));
            items.add(new ItemStack(PLANTAGO_LEAF_ITEM.get()));
            items.add(new ItemStack(DRIED_PLANTAGO_ITEM.get()));
            items.add(new ItemStack(MASHED_PLANTAGO_ITEM.get()));
            items.add(new ItemStack(GROUND_PLANTAGO_ITEM.get()));
            items.add(new ItemStack(CHAMOMILE_BLOCK_ITEM.get()));
            items.add(new ItemStack(CHAMOMILE_FLOWERS_ITEM.get()));
            items.add(new ItemStack(DRIED_CHAMOMILE_ITEM.get()));
            items.add(new ItemStack(MASHED_CHAMOMILE_ITEM.get()));
            items.add(new ItemStack(GROUND_CHAMOMILE_ITEM.get()));
            items.add(new ItemStack(MORTAR_AND_PESTLE_ITEM.get()));
            items.add(new ItemStack(DRYING_RACK_ITEM.get()));
        }
    };

    // ---------------------------------------------------------------------------------
    // PLANTAGO
    public static final RegistryObject<Block> PLANTAGO_BLOCK = BLOCKS.register("plantago", () -> new BushBlock(
            Block.Properties
            .of(Material.PLANT)
            .noCollission()
            .instabreak()
            .sound(SoundType.GRASS)
            .offsetType(BlockBehaviour.OffsetType.XZ)));
    public static final RegistryObject<Item> PLANTAGO_BLOCK_ITEM = ITEMS.register("plantago", () -> new BlockItem(PLANTAGO_BLOCK.get(), new Item.Properties().tab(HERBALIS_TAB)));
    public static final RegistryObject<Item> PLANTAGO_LEAF_ITEM = ITEMS.register("plantago_leaf", () -> new Item(new Item.Properties().tab(HERBALIS_TAB)));
    public static final RegistryObject<Item> DRIED_PLANTAGO_ITEM = ITEMS.register("dried_plantago", () -> new Item(new Item.Properties().tab(HERBALIS_TAB)));
    public static final RegistryObject<Item> MASHED_PLANTAGO_ITEM = ITEMS.register("mashed_plantago", () -> new Item(new Item.Properties().tab(HERBALIS_TAB)));
    public static final RegistryObject<Item> GROUND_PLANTAGO_ITEM = ITEMS.register("ground_plantago", () -> new Item(new Item.Properties().tab(HERBALIS_TAB)));

    // ---------------------------------------------------------------------------------
    // CHAMOMILE
    public static final RegistryObject<Block> CHAMOMILE_BLOCK = BLOCKS.register("chamomile", () -> new BushBlock(
            Block.Properties
                    .of(Material.PLANT)
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .offsetType(BlockBehaviour.OffsetType.XZ)));
    public static final RegistryObject<Item> CHAMOMILE_BLOCK_ITEM = ITEMS.register("chamomile", () -> new BlockItem(CHAMOMILE_BLOCK.get(), new Item.Properties().tab(HERBALIS_TAB)));
    public static final RegistryObject<Item> CHAMOMILE_FLOWERS_ITEM = ITEMS.register("chamomile_flowers", () -> new Item(new Item.Properties().tab(HERBALIS_TAB)));
    public static final RegistryObject<Item> DRIED_CHAMOMILE_ITEM = ITEMS.register("dried_chamomile", () -> new Item(new Item.Properties().tab(HERBALIS_TAB)));
    public static final RegistryObject<Item> MASHED_CHAMOMILE_ITEM = ITEMS.register("mashed_chamomile", () -> new Item(new Item.Properties().tab(HERBALIS_TAB)));
    public static final RegistryObject<Item> GROUND_CHAMOMILE_ITEM = ITEMS.register("ground_chamomile", () -> new Item(new Item.Properties().tab(HERBALIS_TAB)));

    // ---------------------------------------------------------------------------------
    // FUNCTIONAL BLOCKS
    public static final RegistryObject<Block> MORTAR_AND_PESTLE = BLOCKS.register("mortar_and_pestle", () -> new MortarAndPestleBlock(
            Block.Properties
                    .of(Material.STONE)
                    .strength(0.5f)
                    .sound(SoundType.STONE)
                    .noOcclusion()));
    public static final RegistryObject<Item> MORTAR_AND_PESTLE_ITEM = ITEMS.register("mortar_and_pestle", () -> new BlockItem(MORTAR_AND_PESTLE.get(), new Item.Properties().tab(HERBALIS_TAB)));
    public static final RegistryObject<BlockEntityType<MortarAndPestleBlockEntity>> MORTAR_BE = BLOCK_ENTITIES.register("mortar_and_pestle", () -> BlockEntityType.Builder.of(MortarAndPestleBlockEntity::new, MORTAR_AND_PESTLE.get()).build(null));

    public static final RegistryObject<Block> DRYING_RACK = BLOCKS.register("drying_rack", () -> new DryingRackBlock(
            Block.Properties
                    .of(Material.WOOD)
                    .strength(0.5f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));
    public static final RegistryObject<Item> DRYING_RACK_ITEM = ITEMS.register("drying_rack", () -> new BlockItem(DRYING_RACK.get(), new Item.Properties().tab(HERBALIS_TAB)));

    public static final RegistryObject<BlockEntityType<DryingRackBlockEntity>> DRYING_RACK_BE = BLOCK_ENTITIES.register("drying_rack", () -> BlockEntityType.Builder.of(DryingRackBlockEntity::new, DRYING_RACK.get()).build(null));

    public HerbalisMod(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so block entities get registered
        BLOCK_ENTITIES.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            ItemBlockRenderTypes.setRenderLayer(PLANTAGO_BLOCK.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(MORTAR_AND_PESTLE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(CHAMOMILE_BLOCK.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(DRYING_RACK.get(), RenderType.cutout());
        }

        @SubscribeEvent
        public static void registerRenderers(net.minecraftforge.client.event.EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(DRYING_RACK_BE.get(), DryingRackBlockEntityRenderer::new);
            event.registerBlockEntityRenderer(MORTAR_BE.get(), MortarAndPestleBlockEntityRenderer::new);
        }
    }
}

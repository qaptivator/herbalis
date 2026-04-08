package com.captivator.herbalis.registry;

import com.captivator.herbalis.blocks.DryingRackBlockEntity;
import com.captivator.herbalis.HerbalisMod;
import com.captivator.herbalis.blocks.MortarAndPestleBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, HerbalisMod.MODID);

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }

    public static final RegistryObject<BlockEntityType<MortarAndPestleBlockEntity>> MORTAR_BE =
            BLOCK_ENTITIES.register("mortar_and_pestle", () -> BlockEntityType.Builder.of(MortarAndPestleBlockEntity::new, ModBlocks.MORTAR_AND_PESTLE.get()).build(null));

    public static final RegistryObject<BlockEntityType<DryingRackBlockEntity>> DRYING_RACK_BE =
            BLOCK_ENTITIES.register("drying_rack", () -> BlockEntityType.Builder.of(DryingRackBlockEntity::new, ModBlocks.DRYING_RACK.get()).build(null));
}

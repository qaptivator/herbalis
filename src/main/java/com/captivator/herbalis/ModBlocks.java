package com.captivator.herbalis;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, HerbalisMod.MODID);

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    private static BlockBehaviour.Properties herbProps() {
        return BlockBehaviour.Properties
                .of(Material.PLANT)
                .noCollission()
                .instabreak()
                .sound(SoundType.GRASS)
                .offsetType(BlockBehaviour.OffsetType.XZ);
    }

    // ---------------------------------------------------------------------------------
    // HERBS
    public static final RegistryObject<Block> PLANTAGO_BLOCK = BLOCKS.register("plantago", () -> new BushBlock(herbProps()));
    public static final RegistryObject<Block> CHAMOMILE_BLOCK = BLOCKS.register("chamomile", () -> new BushBlock(herbProps()));
    public static final RegistryObject<Block> NETTLE_BLOCK = BLOCKS.register("nettle", () -> new NettleBlock(herbProps()));
    public static final RegistryObject<Block> TALL_NETTLE_BLOCK = BLOCKS.register("tall_nettle",
            () -> new TallNettleBlock(BlockBehaviour.Properties.copy(Blocks.ROSE_BUSH)
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .offsetType(BlockBehaviour.OffsetType.XZ)));
    public static final RegistryObject<Block> VALERIAN_BLOCK = BLOCKS.register("valerian", () -> new NettleBlock(herbProps()));

    // ---------------------------------------------------------------------------------
    // FUNCTIONAL BLOCKS
    public static final RegistryObject<Block> MORTAR_AND_PESTLE = BLOCKS.register("mortar_and_pestle", () -> new MortarAndPestleBlock(
            Block.Properties
                    .of(Material.STONE)
                    .strength(0.5f)
                    .sound(SoundType.STONE)
                    .noOcclusion()));

    public static final RegistryObject<Block> DRYING_RACK = BLOCKS.register("drying_rack", () -> new DryingRackBlock(
            Block.Properties
                    .of(Material.WOOD)
                    .strength(0.5f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));
}

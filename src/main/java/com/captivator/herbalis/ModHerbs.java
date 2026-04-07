package com.captivator.herbalis;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class ModHerbs {
    public static final Map<String, RegistryObject<Block>> BLOCKS = new LinkedHashMap<>();
    public static final Map<String, RegistryObject<Item>> ITEMS = new LinkedHashMap<>();

    public static void register() {
        defineHerb("plantago", "leaf", true);
        defineHerb("chamomile", "flowers", true);
        defineHerb("valerian", "root", true);
        defineHerb("nettle", "leaf", false);
    }

    private static void defineHerb(String name, String part, boolean hasVariants) {
        RegistryObject<Block> block = HerbalisMod.BLOCKS.register(name, () -> new BushBlock(
                BlockBehaviour.Properties
                        .of(Material.PLANT)
                        .noCollission()
                        .instabreak()
                        .sound(SoundType.GRASS)
                        .offsetType(BlockBehaviour.OffsetType.XZ)));

        BLOCKS.put(name, block);

        registerItem(name, new BlockItem(block.get(), props()));
        registerItem(name + "_" + part, new Item(props()));
        if (hasVariants) {
            registerItem("dried_" + name, new Item(props()));
            registerItem("mashed_" + name, new Item(props()));
            registerItem("ground_" + name, new Item(props()));
        }
    }

    private static void registerItem(String id, Item item) {
        ITEMS.put(id, HerbalisMod.ITEMS.register(id, () -> item));
    }

    private static Item.Properties props() {
        return new Item.Properties().tab(HerbalisMod.HERBALIS_TAB);
    }
}

package com.captivator.herbalis;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTabs {
    public static final CreativeModeTab HERBALIS_TAB = new CreativeModeTab("herbalis") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.PLANTAGO_LEAF_ITEM.get());
        }

        @Override
        public void fillItemList(NonNullList<ItemStack> items) {
            items.add(new ItemStack(ModItems.PLANTAGO_BLOCK_ITEM.get()));
            items.add(new ItemStack(ModItems.PLANTAGO_LEAF_ITEM.get()));
            items.add(new ItemStack(ModItems.DRIED_PLANTAGO_ITEM.get()));
            items.add(new ItemStack(ModItems.MASHED_PLANTAGO_ITEM.get()));
            items.add(new ItemStack(ModItems.GROUND_PLANTAGO_ITEM.get()));
            items.add(new ItemStack(ModItems.PLANTAGO_POULTICE.get()));
            
            items.add(new ItemStack(ModItems.CHAMOMILE_BLOCK_ITEM.get()));
            items.add(new ItemStack(ModItems.CHAMOMILE_FLOWERS_ITEM.get()));
            items.add(new ItemStack(ModItems.DRIED_CHAMOMILE_ITEM.get()));
            items.add(new ItemStack(ModItems.MASHED_CHAMOMILE_ITEM.get()));
            items.add(new ItemStack(ModItems.GROUND_CHAMOMILE_ITEM.get()));
            
            items.add(new ItemStack(ModItems.NETTLE_BLOCK_ITEM.get()));
            items.add(new ItemStack(ModItems.TALL_NETTLE_BLOCK_ITEM.get()));
            items.add(new ItemStack(ModItems.NETTLE_LEAF_ITEM.get()));
            
            items.add(new ItemStack(ModItems.VALERIAN_BLOCK_ITEM.get()));
            
            items.add(new ItemStack(ModItems.UNFIRED_CUP_ITEM.get()));
            items.add(new ItemStack(ModItems.CERAMIC_CUP_ITEM.get()));
            items.add(new ItemStack(ModItems.LEATHER_PIECE_ITEM.get()));
            
            items.add(new ItemStack(ModItems.MORTAR_AND_PESTLE_ITEM.get()));
            items.add(new ItemStack(ModItems.DRYING_RACK_ITEM.get()));
        }
    };
}

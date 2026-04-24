package com.captivator.herbalis.registry;

import com.captivator.herbalis.HerbalisMod;
import com.captivator.herbalis.items.WaterCraftedItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, HerbalisMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    private static Item.Properties herbProps() {
        return new Item.Properties().tab(ModCreativeTabs.HERBALIS_TAB);
    }

    // ---------------------------------------------------------------------------------
    // CERAMIC CUP
    public static final RegistryObject<Item> UNFIRED_CUP_ITEM = ITEMS.register("unfired_cup", () -> new Item(herbProps()));
    public static final RegistryObject<Item> CERAMIC_CUP_ITEM = ITEMS.register("ceramic_cup", () -> new Item(herbProps()));

    // ---------------------------------------------------------------------------------
    // PLANTAGO
    public static final RegistryObject<Item> PLANTAGO_BLOCK_ITEM = ITEMS.register("plantago", () -> new BlockItem(ModBlocks.PLANTAGO_BLOCK.get(), herbProps()));
    public static final RegistryObject<Item> PLANTAGO_LEAF_ITEM = ITEMS.register("plantago_leaf", () -> new Item(herbProps()));
    public static final RegistryObject<Item> DRIED_PLANTAGO_ITEM = ITEMS.register("dried_plantago", () -> new Item(herbProps()));
    public static final RegistryObject<Item> MASHED_PLANTAGO_ITEM = ITEMS.register("mashed_plantago", () -> new WaterCraftedItem(herbProps(), ModItems.PLANTAGO_POULTICE));
    public static final RegistryObject<Item> GROUND_PLANTAGO_ITEM = ITEMS.register("ground_plantago", () -> new Item(herbProps()));
    public static final RegistryObject<Item> PLANTAGO_POULTICE = ITEMS.register("plantago_poultice", () -> new Item(herbProps().food(new FoodProperties.Builder().alwaysEat().effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 200, 0), 1.0F).build())) {
        @Override
        public UseAnim getUseAnimation(ItemStack stack) {
            return UseAnim.BOW;
        }
        @Override
        public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int count) {
            if (count % 4 == 0 && level.isClientSide) {
                entity.playSound(SoundEvents.GRASS_HIT, 1F, 0.8F + level.random.nextFloat() * 0.4F);
            }
        }
    });

    // ---------------------------------------------------------------------------------
    // CHAMOMILE
    public static final RegistryObject<Item> CHAMOMILE_BLOCK_ITEM = ITEMS.register("chamomile", () -> new BlockItem(ModBlocks.CHAMOMILE_BLOCK.get(), herbProps()));
    public static final RegistryObject<Item> CHAMOMILE_FLOWERS_ITEM = ITEMS.register("chamomile_flowers", () -> new Item(herbProps()));
    public static final RegistryObject<Item> DRIED_CHAMOMILE_ITEM = ITEMS.register("dried_chamomile", () -> new Item(herbProps()));
    public static final RegistryObject<Item> MASHED_CHAMOMILE_ITEM = ITEMS.register("mashed_chamomile", () -> new Item(herbProps()));
    public static final RegistryObject<Item> GROUND_CHAMOMILE_ITEM = ITEMS.register("ground_chamomile", () -> new Item(herbProps()));
    public static final RegistryObject<Item> CHAMOMILE_TEA = ITEMS.register("chamomile_tea", () -> new com.captivator.herbalis.items.HerbTeaItem(herbProps().stacksTo(1).craftRemainder(ModItems.CERAMIC_CUP_ITEM.get()).food(new FoodProperties.Builder().alwaysEat().build()), 
            java.util.Map.of(ModMobEffects.PAIN_EFFECT, 200, ModMobEffects.SORENESS_EFFECT, 200)));

    // ---------------------------------------------------------------------------------
    // NETTLE
    public static final RegistryObject<Item> NETTLE_BLOCK_ITEM = ITEMS.register("nettle", () -> new BlockItem(ModBlocks.NETTLE_BLOCK.get(), herbProps()));
    public static final RegistryObject<Item> NETTLE_LEAF_ITEM = ITEMS.register("nettle_leaf", () -> new Item(herbProps().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.2f).alwaysEat().effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1.0f).build())));
    public static final RegistryObject<Item> TALL_NETTLE_BLOCK_ITEM = ITEMS.register("tall_nettle", () -> new DoubleHighBlockItem(ModBlocks.TALL_NETTLE_BLOCK.get(), herbProps()));

    // ---------------------------------------------------------------------------------
    // VALERIAN
    public static final RegistryObject<Item> VALERIAN_BLOCK_ITEM = ITEMS.register("valerian", () -> new BlockItem(ModBlocks.VALERIAN_BLOCK.get(), herbProps()));
    public static final RegistryObject<Item> VALERIAN_TEA = ITEMS.register("valerian_tea", () -> new com.captivator.herbalis.items.HerbTeaItem(herbProps().stacksTo(1).craftRemainder(ModItems.CERAMIC_CUP_ITEM.get()).food(new FoodProperties.Builder().alwaysEat().build()), 
            java.util.Map.of(ModMobEffects.ANXIETY_EFFECT, 600, ModMobEffects.PAIN_EFFECT, 100)));

    // ---------------------------------------------------------------------------------
    // MINT
    public static final RegistryObject<Item> MINT_BLOCK_ITEM = ITEMS.register("mint", () -> new BlockItem(ModBlocks.MINT_BLOCK.get(), herbProps()));
    public static final RegistryObject<Item> MINT_LEAF_ITEM = ITEMS.register("mint_leaf", () -> new Item(herbProps()));
    public static final RegistryObject<Item> DRIED_MINT_ITEM = ITEMS.register("dried_mint", () -> new Item(herbProps()));
    public static final RegistryObject<Item> GROUND_MINT_ITEM = ITEMS.register("ground_mint", () -> new Item(herbProps()));
    public static final RegistryObject<Item> MINT_TEA = ITEMS.register("mint_tea", () -> new com.captivator.herbalis.items.HerbTeaItem(herbProps().stacksTo(1).craftRemainder(ModItems.CERAMIC_CUP_ITEM.get()).food(new FoodProperties.Builder().alwaysEat().build()), 
            java.util.Map.of(ModMobEffects.INDIGESTION_EFFECT, 400)));

    // ---------------------------------------------------------------------------------
    // TOOLS & MISC
    public static final RegistryObject<Item> MORTAR_AND_PESTLE_ITEM = ITEMS.register("mortar_and_pestle", () -> new BlockItem(ModBlocks.MORTAR_AND_PESTLE.get(), herbProps()));
    public static final RegistryObject<Item> DRYING_RACK_ITEM = ITEMS.register("drying_rack", () -> new BlockItem(ModBlocks.DRYING_RACK.get(), herbProps()));
    public static final RegistryObject<Item> LEATHER_PIECE_ITEM = ITEMS.register("leather_piece", () -> new Item(herbProps()));
    public static final RegistryObject<Item> WATER_CRAFTED_VISUAL = ITEMS.register("water_crafted_visual", () -> new Item(new Item.Properties()));
   /* public static final RegistryObject<Item> WATER_BLOCK_VISUAL = ITEMS.register("water_block_visual", () -> new TooltipItem(new Item.Properties(), "item.herbalis.water_block_visual.tooltip") {
        @Override
        public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
            return itemStack.copy();
        }
        @Override
        public boolean hasCraftingRemainingItem(ItemStack stack) {
            return true;
        }
    }); */
}

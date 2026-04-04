package com.captivator.herbalis;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class WaterCraftedItem extends Item {
    private final Supplier<? extends Item> resultSupplier;

    public WaterCraftedItem(Properties props, Supplier<? extends Item> resultSupplier) {
        super(props);
        this.resultSupplier = resultSupplier;
    }

    public Item getResult() {
        return resultSupplier.get();
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        if (entity.isInWater() && !entity.level.isClientSide) {
            ItemStack result = new ItemStack(resultSupplier.get(), stack.getCount());
            ItemEntity newEntity = new ItemEntity(entity.level, entity.getX(), entity.getY(), entity.getZ(), result);
            newEntity.setPickUpDelay(10);
            entity.level.addFreshEntity(newEntity);
            
            entity.level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), 
                SoundEvents.MUD_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
            
            entity.discard();
            return true;
        }
        return false;
    }
}

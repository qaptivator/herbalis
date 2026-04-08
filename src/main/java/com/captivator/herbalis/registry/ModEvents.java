package com.captivator.herbalis.registry;

import com.captivator.herbalis.HerbalisMod;
import com.captivator.herbalis.items.WaterCraftedItem;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HerbalisMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {
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

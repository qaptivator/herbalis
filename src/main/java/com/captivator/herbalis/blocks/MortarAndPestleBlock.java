package com.captivator.herbalis.blocks;

import com.captivator.herbalis.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.Containers;
import org.jetbrains.annotations.Nullable;

public class MortarAndPestleBlock extends Block implements EntityBlock {
    protected static final VoxelShape MORTAR = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 5.0D, 13.0D);
    protected static final VoxelShape PESTLE = Block.box(4.5D, 4.0D, 9.5D, 6.5D, 8.0D, 11.5D);
    protected static final VoxelShape SHAPE = Shapes.or(MORTAR, PESTLE);

    public MortarAndPestleBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return MORTAR;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        BlockEntity be = level.getBlockEntity(pos);
        if (!(be instanceof MortarAndPestleBlockEntity mortar)) {
            return InteractionResult.PASS;
        }

        ItemStack stackInHand = player.getItemInHand(hand);
        ItemStack stackInMortar = mortar.getItem();

        if (!stackInMortar.isEmpty() && isGrindable(stackInMortar) && !player.isShiftKeyDown()) {
            if (mortar.getGrindCount() < 2) {
                // Grinding progress
                if (!level.isClientSide) {
                    mortar.incrementGrindCount();
                    level.playSound(null, pos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1.0f, 1.0f);
                    spawnGrindParticles((ServerLevel) level, pos, stackInMortar);
                }
                return InteractionResult.sidedSuccess(level.isClientSide);
            } else if (mortar.getGrindCount() == 2) {
                // Final grind
                if (!level.isClientSide) {
                    mortar.setItem(getGrindResult(stackInMortar));
                    level.playSound(null, pos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1.0f, 1.5f);
                    spawnGrindParticles((ServerLevel) level, pos, stackInMortar);
                }
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        }

        // Standard interaction (swap or remove)
        if (!level.isClientSide) {
            if (stackInHand.isEmpty()) {
                if (!stackInMortar.isEmpty()) {
                    player.setItemInHand(hand, stackInMortar);
                    mortar.setItem(ItemStack.EMPTY);
                }
            } else {
                if (stackInMortar.isEmpty()) {
                    mortar.setItem(stackInHand.split(1));
                } else if (ItemStack.isSameItemSameTags(stackInHand, stackInMortar) && stackInHand.getCount() < stackInHand.getMaxStackSize()) {
                    // If holding same item and has space, grab from mortar
                    int toAdd = Math.min(stackInMortar.getCount(), stackInHand.getMaxStackSize() - stackInHand.getCount());
                    stackInHand.grow(toAdd);
                    stackInMortar.shrink(toAdd);
                    mortar.setItem(stackInMortar.isEmpty() ? ItemStack.EMPTY : stackInMortar);
                } else {
                    // Swap
                    ItemStack temp = stackInMortar.copy();
                    mortar.setItem(stackInHand.split(1));
                    if (player.getItemInHand(hand).isEmpty()) {
                        player.setItemInHand(hand, temp);
                    } else if (!player.getInventory().add(temp)) {
                        player.drop(temp, false);
                    }
                }
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    private boolean isGrindable(ItemStack stack) {
        return stack.is(ModItems.PLANTAGO_LEAF_ITEM.get()) ||
               stack.is(ModItems.DRIED_PLANTAGO_ITEM.get()) ||
               stack.is(ModItems.CHAMOMILE_FLOWERS_ITEM.get()) ||
               stack.is(ModItems.DRIED_CHAMOMILE_ITEM.get()) ||
               stack.is(ModItems.DRIED_MINT_ITEM.get());
    }

    private ItemStack getGrindResult(ItemStack stack) {
        if (stack.is(ModItems.PLANTAGO_LEAF_ITEM.get())) return new ItemStack(ModItems.MASHED_PLANTAGO_ITEM.get(), stack.getCount());
        if (stack.is(ModItems.DRIED_PLANTAGO_ITEM.get())) return new ItemStack(ModItems.GROUND_PLANTAGO_ITEM.get(), stack.getCount());
        if (stack.is(ModItems.CHAMOMILE_FLOWERS_ITEM.get())) return new ItemStack(ModItems.MASHED_CHAMOMILE_ITEM.get(), stack.getCount());
        if (stack.is(ModItems.DRIED_CHAMOMILE_ITEM.get())) return new ItemStack(ModItems.GROUND_CHAMOMILE_ITEM.get(), stack.getCount());
        if (stack.is(ModItems.DRIED_MINT_ITEM.get())) return new ItemStack(ModItems.GROUND_MINT_ITEM.get(), stack.getCount());
        return stack;
    }

    private void spawnGrindParticles(ServerLevel level, BlockPos pos, ItemStack stack) {
        for (int i = 0; i < 8; i++) {
            level.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, stack),
                    pos.getX() + 0.5d, pos.getY() + 0.4d, pos.getZ() + 0.5d,
                    1, 0.1d, 0.1d, 0.1d, 0.05d);
        }
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof MortarAndPestleBlockEntity mortar) {
                Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), mortar.getItem());
            }
            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new MortarAndPestleBlockEntity(pos, state);
    }
}

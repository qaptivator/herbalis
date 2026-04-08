package com.captivator.herbalis;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.Containers;
import org.jetbrains.annotations.Nullable;

public class DryingRackBlock extends Block implements EntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    protected static final VoxelShape NORTH_SHAPE = Block.box(0.0D, 13.0D, 0.0D, 16.0D, 16.0D, 3.0D);
    protected static final VoxelShape SOUTH_SHAPE = Block.box(0.0D, 13.0D, 13.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape WEST_SHAPE = Block.box(0.0D, 13.0D, 0.0D, 3.0D, 16.0D, 16.0D);
    protected static final VoxelShape EAST_SHAPE = Block.box(13.0D, 13.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    public DryingRackBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        switch (state.getValue(FACING)) {
            case SOUTH:
                return SOUTH_SHAPE;
            case WEST:
                return WEST_SHAPE;
            case EAST:
                return EAST_SHAPE;
            case NORTH:
            default:
                return NORTH_SHAPE;
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof DryingRackBlockEntity dryingRack) {
                ItemStack stackInHand = player.getItemInHand(hand);
                ItemStack stackInRack = dryingRack.getItem();

                if (stackInHand.isEmpty()) {
                    if (!stackInRack.isEmpty()) {
                        player.setItemInHand(hand, stackInRack);
                        dryingRack.setItem(ItemStack.EMPTY);
                        return InteractionResult.SUCCESS;
                    }
                } else {
                    if (stackInRack.isEmpty()) {
                        dryingRack.setItem(stackInHand.split(1));
                        return InteractionResult.SUCCESS;
                    } else if (ItemStack.isSameItemSameTags(stackInHand, stackInRack) && stackInHand.getCount() < stackInHand.getMaxStackSize()) {
                        // If holding same item and has space, grab from rack
                        int toAdd = Math.min(stackInRack.getCount(), stackInHand.getMaxStackSize() - stackInHand.getCount());
                        stackInHand.grow(toAdd);
                        stackInRack.shrink(toAdd);
                        dryingRack.setItem(stackInRack.isEmpty() ? ItemStack.EMPTY : stackInRack);
                        return InteractionResult.SUCCESS;
                    } else {
                        // Swap logic
                        ItemStack temp = stackInRack.copy();
                        dryingRack.setItem(stackInHand.split(1));
                        if (player.getItemInHand(hand).isEmpty()) {
                            player.setItemInHand(hand, temp);
                        } else if (!player.getInventory().add(temp)) {
                            player.drop(temp, false);
                        }
                        return InteractionResult.SUCCESS;
                    }
                }
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof DryingRackBlockEntity dryingRack) {
                Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), dryingRack.getItem());
            }
            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DryingRackBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide ? null : createTickerHelper(type, ModBlockEntities.DRYING_RACK_BE.get(), DryingRackBlockEntity::tick);
    }

    @SuppressWarnings("unchecked")
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> actualType, BlockEntityType<E> expectedType, BlockEntityTicker<? super E> ticker) {
        return expectedType == actualType ? (BlockEntityTicker<A>) ticker : null;
    }
}

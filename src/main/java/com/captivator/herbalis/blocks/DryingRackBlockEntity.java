package com.captivator.herbalis.blocks;

import com.captivator.herbalis.registry.ModBlockEntities;
import com.captivator.herbalis.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DryingRackBlockEntity extends BlockEntity {
    private final ItemStackHandler itemHandler = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (level != null && !level.isClientSide) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
            dryingProgress = 0;
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.of(() -> itemHandler);
    private int dryingProgress = 0;
    private static final int DRYING_TIME = 200; // 10 seconds

    public DryingRackBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DRYING_RACK_BE.get(), pos, state);
    }

    public ItemStack getItem() {
        return itemHandler.getStackInSlot(0);
    }

    public void setItem(ItemStack stack) {
        itemHandler.setStackInSlot(0, stack);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        dryingProgress = nbt.getInt("dryingProgress");
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        nbt.put("inventory", itemHandler.serializeNBT());
        nbt.putInt("dryingProgress", dryingProgress);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag nbt = super.getUpdateTag();
        saveAdditional(nbt);
        return nbt;
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, DryingRackBlockEntity blockEntity) {
        if (level.isClientSide) return;

        ItemStack stack = blockEntity.getItem();
        if (!stack.isEmpty()) {
            if (isDryable(stack)) {
                blockEntity.dryingProgress++;
                if (blockEntity.dryingProgress >= DRYING_TIME) {
                    blockEntity.dryItem();
                    blockEntity.dryingProgress = 0;
                }
            } else {
                blockEntity.dryingProgress = 0;
            }
        } else {
            blockEntity.dryingProgress = 0;
        }
    }

    private static boolean isDryable(ItemStack stack) {
        return stack.is(ModItems.PLANTAGO_LEAF_ITEM.get()) || stack.is(ModItems.CHAMOMILE_FLOWERS_ITEM.get()) || stack.is(net.minecraft.world.item.Items.ROTTEN_FLESH);
    }

    private void dryItem() {
        ItemStack stack = getItem();
        int count = stack.getCount();
        if (stack.is(ModItems.PLANTAGO_LEAF_ITEM.get())) {
            setItem(new ItemStack(ModItems.DRIED_PLANTAGO_ITEM.get(), count));
        } else if (stack.is(ModItems.CHAMOMILE_FLOWERS_ITEM.get())) {
            setItem(new ItemStack(ModItems.DRIED_CHAMOMILE_ITEM.get(), count));
        } else if (stack.is(net.minecraft.world.item.Items.ROTTEN_FLESH)) {
            setItem(new ItemStack(ModItems.LEATHER_PIECE_ITEM.get(), count));
        }
    }
}

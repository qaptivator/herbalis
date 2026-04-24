package com.captivator.herbalis.stamina;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StaminaProvider implements ICapabilitySerializable<CompoundTag> {
    public static Capability<IStamina> PLAYER_STAMINA = CapabilityManager.get(new CapabilityToken<IStamina>() { });

    private StaminaImpl stamina = null;
    private final LazyOptional<IStamina> optional = LazyOptional.of(this::createStamina);

    private StaminaImpl createStamina() {
        if (this.stamina == null) {
            this.stamina = new StaminaImpl();
        }
        return this.stamina;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == PLAYER_STAMINA) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createStamina().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createStamina().loadNBTData(nbt);
    }
}

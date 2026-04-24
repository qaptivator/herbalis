package com.captivator.herbalis.stamina;

import net.minecraft.nbt.CompoundTag;

public class StaminaImpl implements IStamina {
    private float stamina = 100.0f;
    private float maxStamina = 100.0f;

    @Override
    public float getStamina() {
        return stamina;
    }

    @Override
    public void setStamina(float stamina) {
        this.stamina = Math.max(0.0f, Math.min(stamina, maxStamina));
    }

    @Override
    public void addStamina(float amount) {
        setStamina(stamina + amount);
    }

    @Override
    public void subStamina(float amount) {
        setStamina(stamina - amount);
    }

    @Override
    public float getMaxStamina() {
        return maxStamina;
    }

    @Override
    public void setMaxStamina(float maxStamina) {
        this.maxStamina = maxStamina;
        this.stamina = Math.min(stamina, maxStamina);
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putFloat("stamina", stamina);
        nbt.putFloat("maxStamina", maxStamina);
    }

    public void loadNBTData(CompoundTag nbt) {
        stamina = nbt.getFloat("stamina");
        maxStamina = nbt.getFloat("maxStamina");
    }
}

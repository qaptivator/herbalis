package com.captivator.herbalis.stamina;

public interface IStamina {
    float getStamina();
    void setStamina(float stamina);
    void addStamina(float amount);
    void subStamina(float amount);
    
    float getMaxStamina();
    void setMaxStamina(float maxStamina);
}

package com.skillw.rpglib.api.fixed;

import com.skillw.rpglib.RPGLib;
import org.bukkit.configuration.ConfigurationSection;

public class FixedData {
    private int integerMin;
    private int integerMax;
    private int decimalMin;
    private int decimalMax;
    private int decimalValue;

    public int getDecimalValue() {
        return decimalValue;
    }

    public void setDecimalValue(int decimalValue) {
        this.decimalValue = decimalValue;
    }

    public static FixedData getDefaultData() {
        return defaultData;
    }

    public static void setDefaultData(FixedData defaultData) {
        FixedData.defaultData = defaultData;
    }

    private static FixedData defaultData;

    public FixedData(int integerMin, int integerMax, int decimalMin, int decimalMax, int decimalValue) {
        this.integerMin = integerMin;
        this.integerMax = integerMax;
        this.decimalMin = decimalMin;
        this.decimalMax = decimalMax;
        this.decimalValue = decimalValue;
    }

    public FixedData(ConfigurationSection section) {
        this.integerMin = section.getInt("integer.min");
        this.integerMax = section.getInt("integer.max");
        this.decimalMin = section.getInt("decimal.min");
        this.decimalMax = section.getInt("decimal.max");
        this.decimalValue = section.getInt("decimal.value");
    }

    public int getIntegerMin() {
        return integerMin;
    }

    public void setIntegerMin(int integerMin) {
        this.integerMin = integerMin;
    }

    public int getIntegerMax() {
        return integerMax;
    }

    public void setIntegerMax(int integerMax) {
        this.integerMax = integerMax;
    }

    public int getDecimalMin() {
        return decimalMin;
    }

    public void setDecimalMin(int decimalMin) {
        this.decimalMin = decimalMin;
    }

    public int getDecimalMax() {
        return decimalMax;
    }

    public void setDecimalMax(int decimalMax) {
        this.decimalMax = decimalMax;
    }

    public static void load(){
        setDefaultData(new FixedData(RPGLib.getConfigManager().getConfig().getConfigurationSection("options.fixed")));
    }
}

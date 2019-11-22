package com.nutriscan.shared.domain;

import androidx.annotation.NonNull;

import com.nutriscan.misc.enums.NutrientType;

/**
 * An immutable nutrient domain object
 */
public class Nutrient {
    private final NutrientType nutrientType;
    private final double amount;
    private final String unit;

    public Nutrient(@NonNull NutrientType nutrientType, double amount, String unit) {
        this.nutrientType = nutrientType;
        this.amount = amount;
        this.unit = unit;
    }

    public NutrientType getNutrientType() {
        return nutrientType;
    }

    public double getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }

    @NonNull
    @Override
    public String toString() {
        return this.nutrientType.toString();
    }
}

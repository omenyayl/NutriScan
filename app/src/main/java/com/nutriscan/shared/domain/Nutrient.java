package com.nutriscan.shared.domain;

import androidx.annotation.NonNull;

import com.nutriscan.misc.enums.NutrientType;
import com.nutriscan.misc.enums.Unit;

/**
 * An immutable nutrient domain object
 */
public class Nutrient {
    private final NutrientType nutrientType;
    private final double amount;
    private final Unit unit;

    public Nutrient(@NonNull NutrientType nutrientType, double amount, Unit unit) {
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

    public Unit getUnit() {
        return unit;
    }

    @NonNull
    @Override
    public String toString() {
        return this.nutrientType.toString();
    }
}

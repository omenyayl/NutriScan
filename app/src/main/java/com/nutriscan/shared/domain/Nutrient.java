package com.nutriscan.shared.domain;

import androidx.annotation.NonNull;

import com.nutriscan.shared.misc.enums.NutrientType;
import com.nutriscan.shared.misc.enums.Unit;

/**
 * An immutable nutrient domain object
 */
public class Nutrient {
    private final NutrientType nutrientType;
    private final double amount;

    public Nutrient(@NonNull NutrientType nutrientType, double amount) {
        this.nutrientType = nutrientType;
        this.amount = amount;
    }

    /**
     * @deprecated - use Nutrient(NutrientType, double)
     */
    public Nutrient(@NonNull NutrientType nutrientType, double amount, Unit unit) {
        this.nutrientType = nutrientType;
        this.amount = amount;
    }

    public NutrientType getNutrientType() {
        return nutrientType;
    }

    public double getAmount() {
        return amount;
    }

    public Unit getUnit() {
        return nutrientType.getUnit();
    }

    @NonNull
    @Override
    public String toString() {
        return this.nutrientType.toString();
    }
}

package com.nutriscan.misc.utils;

import androidx.annotation.NonNull;

import com.nutriscan.misc.enums.NutrientType;
import com.nutriscan.misc.enums.Unit;
import com.nutriscan.shared.domain.Nutrient;

public class NutrientUtils {
    /**
     * Retrieve a Unit from the given string
     * @param unit The unit name
     * @return A Unit corresponding to the given unit name
     * @throws IllegalArgumentException if the given string is not a known unit
     */
    public static Unit parseUnitString(@NonNull String unit) {
        switch (unit.toLowerCase().trim()) {
            // TODO fix
        }
        throw new IllegalArgumentException();
    }

    /**
     * @param nutrient The nutrient of which to get the daily value
     * @return The percent of the daily value given the nutrient
     */
    public static double getPercentDailyValue(Nutrient nutrient) {
        return nutrient.getAmount() / nutrient.getNutrientType().getDailyValue() * 100;
    }

}

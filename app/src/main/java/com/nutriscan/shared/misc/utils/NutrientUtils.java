package com.nutriscan.shared.misc.utils;

import com.nutriscan.shared.domain.Nutrient;

public class NutrientUtils {
    /**
     * @param nutrient The nutrient of which to get the daily value
     * @return The percent of the daily value given the nutrient
     */
    public static double getPercentDailyValue(Nutrient nutrient) {
        return nutrient.getAmount() / nutrient.getNutrientType().getDailyValue() * 100;
    }

}

package com.nutriscan.misc.utils;

import android.support.annotation.NonNull;
import com.nutriscan.misc.enums.Unit;

public class NutrientUtils {
    /**
     * Retrieve a Unit from the given string
     * @param unit The unit name
     * @return A Unit corresponding to the given unit name
     * @throws IllegalArgumentException if the given string is not a known unit
     */
    public static Unit parseUnitString(@NonNull String unit) {
        switch (unit.toLowerCase().trim()) {
            case "kilogram": return Unit.KILOGRAM;
            case "gram": return Unit.GRAM;
            case "pound": return Unit.POUND;
            case "ounce": return Unit.OUNCE;
            case "serving": return Unit.SERVING;
            case "cup": return Unit.CUP;
            case "whole": return Unit.WHOLE;
            case "liter": return Unit.LITER;
            case "milliliter": return Unit.MILLILITER;
            case "cubic inch": return Unit.CUBIC_INCH;
            case "quart": return Unit.QUART;
            case "drop": return Unit.DROP;
            case "gallon": return Unit.GALLON;
            case "dash": return Unit.DASH;
            case "fluid ounce": return Unit.FLUID_OUNCE;
            case "pint": return Unit.PINT;
            case "pinch": return Unit.PINCH;
            case "teaspoon": return Unit.TEASPOON;
            case "tablespoon": return Unit.TABLESPOON;
            case "dessert spoon": return Unit.DESSERT_SPOON;
        }
        throw new IllegalArgumentException();
    }
}

package com.nutriscan.misc.utils;

import androidx.annotation.NonNull;
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
            // TODO fix
        }
        throw new IllegalArgumentException();
    }

}

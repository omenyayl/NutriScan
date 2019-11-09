package com.nutriscan.utils;

import com.nutriscan.misc.enums.Unit;
import com.nutriscan.misc.utils.NutrientUtils;

import org.junit.Test;

import static org.junit.Assert.*;

public class NutrientUtilsTest {

    @Test
    public void parseUnitString_withCapitals() {
        assertEquals(Unit.DESSERT_SPOON, NutrientUtils.parseUnitString("Dessert spoon"));
    }

    @Test
    public void parseUnitString_withExtraSpaces() {
        assertEquals(Unit.DESSERT_SPOON, NutrientUtils.parseUnitString("  Dessert spoon   "));
    }
}
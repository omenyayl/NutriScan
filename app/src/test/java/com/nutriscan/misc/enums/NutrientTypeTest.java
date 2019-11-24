package com.nutriscan.misc.enums;

import com.nutriscan.shared.misc.enums.NutrientType;

import org.junit.Test;

import static org.junit.Assert.*;

public class NutrientTypeTest {

    @Test
    public void fromString_1() {
        assertEquals(NutrientType.VITAMIN_D, NutrientType.fromString("VITAMIN_D"));
    }

    @Test
    public void fromString_2() {
        assertEquals(NutrientType.POLYUNSATURATED, NutrientType.fromString("POLYUNSATURATED"));
    }

    @Test
    public void fromString_3() {
        assertEquals(NutrientType.VITAMIN_B12, NutrientType.fromString("VITAMIN_B12"));
    }

}
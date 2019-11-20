package com.nutriscan.misc.enums;

import androidx.annotation.NonNull;

public enum NutrientType {
    CALCIUM ("Calcium"),
    CARBS ("Carbs"),
    CHOLESTEROL ("Cholesterol"),
    MONOUNSATURATED ("Monounsaturated"),
    POLYUNSATURATED ("Polyunsaturated"),
    SATURATED ("Saturated Fat"),
    FAT ("Fat"),
    TRANS ("Trans Fat"),
    IRON ("Iron"),
    FIBER ("Fiber"),
    FOLATE ("Folate"),
    POTASSIUM ("Potassium"),
    MAGNESIUM ("Magnesium"),
    SODIUM ("Sodium"),
    ENERGY ("Energy"),
    VITAMIN_B3 ("Niacin (B3)"),
    PHOSPHORUS ("Phosphorus"),
    PROTEIN ("Protein"),
    VITAMIN_B2 ("Riboflavin (B2)"),
    SUGARS ("Sugars"),
    VITAMIN_B1 ("Thiamin (B1)"),
    VITAMIN_E ("Vitamin E"),
    VITAMIN_A ("Vitamin A"),
    VITAMIN_B12 ("Vitamin B12"),
    VITAMIN_B6 ("Vitamin B6"),
    VITAMIN_C ("Vitamin C"),
    VITAMIN_D ("Vitamin D"),
    VITAMIN_K ("Vitamin K");
    
    private final String name;
    
    NutrientType(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return this.name;
    }

    public static NutrientType fromString(String name) {
        for (NutrientType nutrientName : NutrientType.values()) {
            if (nutrientName.name().equals(name)) return nutrientName;
        }
        throw new IllegalArgumentException("Unrecognized nutrient name");
    }

}


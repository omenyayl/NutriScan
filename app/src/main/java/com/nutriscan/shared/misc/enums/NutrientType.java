package com.nutriscan.shared.misc.enums;

import androidx.annotation.NonNull;

public enum NutrientType {
    CALCIUM ("Calcium", 1300, Unit.mg),
    CARBS ("Carbs", 300, Unit.g),
    CHOLESTEROL ("Cholesterol", 300, Unit.mg),
    MONOUNSATURATED ("Monounsaturated", 18, Unit.g),
    POLYUNSATURATED ("Polyunsaturated", 18, Unit.g),
    SATURATED ("Saturated Fat", 18, Unit.g),
    FAT ("Fat", 18, Unit.g),
    TRANS ("Trans Fat", 18, Unit.g),
    IRON ("Iron", 18, Unit.mg),
    FIBER ("Fiber", 25, Unit.g),
    FOLATE ("Folate", 400, Unit.aeg),
    POTASSIUM ("Potassium", 4700, Unit.mg),
    MAGNESIUM ("Magnesium", 420, Unit.mg),
    SODIUM ("Sodium", 2300, Unit.mg),
    ENERGY ("Energy", 2000, Unit.kcal),
    VITAMIN_B3 ("Niacin (B3)", 16, Unit.mg),
    PHOSPHORUS ("Phosphorus", 700, Unit.mg),
    PROTEIN ("Protein", 53, Unit.g),
    VITAMIN_B2 ("Riboflavin (B2)", 1.3, Unit.mg),
    SUGARS ("Sugars", 300, Unit.g),
    VITAMIN_B1 ("Thiamin (B1)", 1.2, Unit.mg),
    VITAMIN_E ("Vitamin E", 15, Unit.mg),
    VITAMIN_A ("Vitamin A", 900, Unit.aeg),
    VITAMIN_B12 ("Vitamin B12", 2.4, Unit.aeg),
    VITAMIN_B6 ("Vitamin B6", 1.7, Unit.mg),
    VITAMIN_C ("Vitamin C", 90, Unit.mg),
    VITAMIN_D ("Vitamin D", 20, Unit.aeg),
    VITAMIN_K ("Vitamin K", 120, Unit.aeg);
    
    private final String name;
    private final Unit unit;
    private final double dailyValue;
    
    NutrientType(String name, double dailyValue, Unit unit) {
        this.name = name;
        this.dailyValue = dailyValue;
        this.unit = unit;
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

    public Unit getUnit() {
        return unit;
    }

    public double getDailyValue() {
        return dailyValue;
    }
}


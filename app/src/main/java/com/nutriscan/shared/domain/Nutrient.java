package com.nutriscan.shared.domain;

import android.support.annotation.NonNull;

import com.nutriscan.misc.enums.Unit;

/**
 * An immutable nutrient domain object
 */
public class Nutrient {
    private final String name;
    private final double amount;
    private final Unit unit;

    public Nutrient(@NonNull String name, double amount, Unit unit) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }
}

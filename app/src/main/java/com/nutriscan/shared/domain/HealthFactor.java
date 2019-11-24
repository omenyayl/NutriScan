package com.nutriscan.shared.domain;

import androidx.annotation.NonNull;

/**
 * Immutable HealthFactor domain object
 */
public class HealthFactor {
    private final String name;
    private final double weight;
    private final int magnitude;

    public HealthFactor(@NonNull String name, double weight, int magnitude) {
        this.name = name;

        this.weight = weight;
        this.magnitude = magnitude;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public int getMagnitude() {
        return magnitude;
    }
}

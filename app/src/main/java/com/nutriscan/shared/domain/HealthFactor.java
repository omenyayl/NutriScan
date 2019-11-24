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

//        if (weight < 0 || weight > 1) throw new IllegalArgumentException("Weight must be 0-1");
        this.weight = weight;

        if (magnitude > 5 || magnitude < 0) throw new IllegalArgumentException("Magnitude must be 0-5");
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

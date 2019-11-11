package com.nutriscan.shared.domain;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

public final class Analysis {
    private final double healthRating;
    private final List<HealthFactor> healthFactors;

    /**
     * Calculates the analysis from the given product
     */
    public Analysis(@NonNull Product product) {
        this.healthFactors = calculateHealthFactorsFromProduct(product);
        this.healthRating = calculateHealthRatingFromHealthFactors(this.healthFactors);
    }

    private double calculateHealthRatingFromHealthFactors(@NonNull List<HealthFactor> healthFactors) {
        double total = 0;
        for (HealthFactor factor : healthFactors) {
            total += factor.getWeight() * factor.getMagnitude();
        }
        return total;
    }

    /**
     * @param product The product of which to calculate the health factors
     * @return A list of health factors
     */
    private List<HealthFactor> calculateHealthFactorsFromProduct(@NonNull Product product) {
        /*
         * TODO: calculate the correct health factors for the given product and test
         */
        return Arrays.asList(
                new HealthFactor("Calorie amount", 0.4, 4),
                new HealthFactor("Fat amount", 0.3, 2)
        );
    }

    public double getHealthRating() {
        return healthRating;
    }

    public List<HealthFactor> getHealthFactors() {
        return healthFactors;
    }
}

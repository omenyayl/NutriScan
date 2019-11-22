package com.nutriscan.shared.domain;

import androidx.annotation.NonNull;

import java.util.ArrayList;
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
        List <HealthFactor> healthFactors = new ArrayList<>();
        List <Nutrient> nutrients = product.getNutrients();
//        for(Nutrient nutrient:nutrients){
//            switch (nutrient.getNutrientType()){
//                case FAT:
//                    double amount = nutrient.getAmount();
//
//                    healthFactors.add("Fat amount", .0935, );
//                    break;
//                case PROTEIN:
//                    healthFactors.add("Protein", .0935);
//                    break;
//                case ENERGY:
//                    healthFactors.add("Energy", .213);
//                    break;
//                case SUGARS:
//                    healthFactors.add("Sugars", .213);
//                    break;
//                case CHOLESTEROL:
//                    healthFactors.add("Cholesterol", .2);
//                    break;
//                case SODIUM:
//                    healthFactors.add("Sodium", .0935);
//                    break;
//                case FIBER:
//                    healthFactors.add("Fiber", .0935);
//                    break;
//
//            }
//        }

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

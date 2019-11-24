package com.nutriscan.shared.domain;

import androidx.annotation.NonNull;

import com.nutriscan.shared.misc.enums.NutrientType;

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
        if (total>5.0){
            total = 5.0;
        }

        int ret_total= (int)(total);

        if(total == 1){
            total = 5;
        }
        else if(total == 2){
            total = 4;
        }
        else if (total ==4){
            total = 2;
        }
        else if (total == 5){
            total = 1;
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
        //For one serving!
        //3.065 is score for median range
        //5 is unhealthy
        //1 is healthy

        for(Nutrient nutrient:nutrients){
            double amount = 0;
            double dailyvalue= 0;
            switch (nutrient.getNutrientType()){
                case FAT:
                    amount = nutrient.getAmount();
                    dailyvalue = nutrient.getNutrientType().getDailyValue();
                    if(amount>dailyvalue*.2){ //greater than 20%
                        //double val = .0935 + (amount/dailyvalue)*(amount/dailyvalue);

                        healthFactors.add(new HealthFactor("Fat amount", .0935, 4));
                    }
                    else if(amount<=dailyvalue*.05){  //dv less than 5%
                        //double val = .0935+(dailyvalue*.05+amount)/dailyvalue;
                        healthFactors.add(new HealthFactor("Protein", .0935, 3));
                    }
                    else {
                        healthFactors.add(new HealthFactor("Fat amount", .0935, 2));
                    }
                    break;
                case PROTEIN:
                    amount = nutrient.getAmount();
                    dailyvalue = nutrient.getNutrientType().getDailyValue();
                    if(amount>dailyvalue*.2){ //greater than 20%
                        double val = .0935+(amount/dailyvalue)*(amount/dailyvalue);
                        healthFactors.add(new HealthFactor("Protein", .0935, 4));
                    }
                    else if(amount<=dailyvalue*.05){  //dv less than 5%
                        double val = .0935+(dailyvalue*.05+amount)/dailyvalue;
                        healthFactors.add(new HealthFactor("Protein", .0935, 3));
                    }
                    else{
                        healthFactors.add(new HealthFactor("Protein", .0935, 3));
                    }
                    break;
                case ENERGY:
                    amount = nutrient.getAmount();
                    dailyvalue = nutrient.getNutrientType().getDailyValue();
                    if(amount>dailyvalue*.2){ //greater than 20%                  //dailyvalue || amount/dailyvalue > dailyvalue/2){
                        double val = .213+((amount-dailyvalue*.2)*(amount-dailyvalue*.2))/dailyvalue;
                        healthFactors.add(new HealthFactor("Energy", .0935, 5));
                    }
                    else if(amount<=dailyvalue*.05){  //dv less than 5%
                        double val = .213+(dailyvalue*.05+amount)/dailyvalue;
                        healthFactors.add(new HealthFactor("Energy", .0935, 3));
                    }
                    else {
                        healthFactors.add(new HealthFactor("Energy", .213, 4));
                    }
                    break;
                case SUGARS:
                    amount = nutrient.getAmount();
                    dailyvalue = nutrient.getNutrientType().getDailyValue();
                    if(amount>dailyvalue*.2){ //greater than 20%                  //dailyvalue || amount/dailyvalue > dailyvalue/2){
                        double val = .213+((amount-dailyvalue*.2)*(amount-dailyvalue*.2))/dailyvalue;
                        healthFactors.add(new HealthFactor("Sugars", .0935, 5));
                    }
                    else if(amount<=dailyvalue*.05){
                        double val = .213+(dailyvalue*.05+amount)/dailyvalue;
                        healthFactors.add(new HealthFactor("Sugars", .0935, 2));
                    }
                    else {
                        healthFactors.add(new HealthFactor("Sugars", .213, 4));
                    }
                    break;
                case CHOLESTEROL:
                    amount = nutrient.getAmount();
                    dailyvalue = nutrient.getNutrientType().getDailyValue();
                    if(amount>dailyvalue*.2){ //greater than 20%
                        double val = .2 + (amount/dailyvalue)*(amount/dailyvalue);
                        healthFactors.add(new HealthFactor("Cholesterol", .0935, 4));
                    }
                    else if(amount<=dailyvalue*.05){
                        double val = .2+(dailyvalue*.05+amount)/dailyvalue;
                        healthFactors.add(new HealthFactor("Cholesterol", .0935, 3));
                    }
                    else {
                        healthFactors.add(new HealthFactor("Cholesterol", .2,2));
                    }
                    break;
                case SODIUM:
                    amount = nutrient.getAmount();
                    dailyvalue = nutrient.getNutrientType().getDailyValue();
                    if(amount>dailyvalue*.2){ //greater than 20%
                        double val = .0935 + (amount/dailyvalue)*(amount/dailyvalue);
                        healthFactors.add(new HealthFactor("Sodium", .0935, 4));
                    }
                    else if(amount<=dailyvalue*.05){
                        double val = .0935+(dailyvalue*.05+amount)/dailyvalue;
                        healthFactors.add(new HealthFactor("Sodium", .0935, 1));
                    }
                    else {
                        healthFactors.add(new HealthFactor("Sodium", .0935, 2));
                    }
                    break;
                case FIBER:
                    if(amount>dailyvalue*.2){ //greater than 20%
                        double val = .0935 + (amount/dailyvalue)*(amount/dailyvalue);
                        healthFactors.add(new HealthFactor("Fiber", .0935,4));

                    }
                    else if(amount<=dailyvalue*.05){
                        double val = .0935+(dailyvalue*.05+amount)/dailyvalue;
                        healthFactors.add(new HealthFactor("Fiber", .0935, 1));
                    }
                    else {
                        healthFactors.add(new HealthFactor("Fiber", .0935,2));
                    }
                    break;

            }
        }

        return healthFactors;
    }

    public double getHealthRating() {
        return healthRating;
    }

    public List<HealthFactor> getHealthFactors() {
        return healthFactors;
    }
}

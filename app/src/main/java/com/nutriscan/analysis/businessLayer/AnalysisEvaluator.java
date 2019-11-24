package com.nutriscan.analysis.businessLayer;

import android.content.Context;

import com.nutriscan.shared.domain.Analysis;
import com.nutriscan.shared.domain.Person;
import com.nutriscan.shared.domain.Product;
import com.nutriscan.shared.repositories.FoodRepository;
import com.nutriscan.shared.repositories.PersonRepository;
import com.nutriscan.shared.repositories.ScanHistoryRepository;

import java.util.Objects;

/**
 * Business logic for the analysis functional area
 */
public class AnalysisEvaluator implements IAnalysisEvaluator{

    private Product product;
    private Analysis analysis;

    public AnalysisEvaluator() {
        this.product = FoodRepository.getInstance().getScannedItem().getValue();
        this.analysis = new Analysis(Objects.requireNonNull(this.product));
    }

    @Override
    public Analysis getAnalysis() {
        return this.analysis;
    }

    public Product getProduct() {
        return product;
    }

    /**
     * Save the analyzed product to the database (into the profile's scan history)
     */
    public void saveProduct(Context context) {
        Person person = PersonRepository.getInstance().getPerson().getValue();
        if (person != null && this.product != null) {
            ScanHistoryRepository.getInstance().saveProduct(context, person, this.product);
        }
    }
}

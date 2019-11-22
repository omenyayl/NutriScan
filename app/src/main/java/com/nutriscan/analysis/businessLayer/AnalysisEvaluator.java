package com.nutriscan.analysis.businessLayer;

import com.nutriscan.shared.domain.Analysis;
import com.nutriscan.shared.domain.HealthFactor;
import com.nutriscan.shared.domain.Product;

import java.util.List;

public class AnalysisEvaluator implements IAnalysisEvaluator{

    private Product product;
    private Analysis analysis;

    public AnalysisEvaluator(Product p) {
        this.product = p;
        this.analysis = new Analysis(p);
    }

    @Override
    public Analysis getAnalysis() {
        return this.analysis;
    }

    public Product getProduct() {
        return product;
    }
}

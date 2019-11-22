package com.nutriscan.analysis.businessLayer;

import com.nutriscan.shared.domain.Analysis;
import com.nutriscan.shared.domain.HealthFactor;
import com.nutriscan.shared.domain.Product;

import java.util.List;

public class AnalysisEvaluator implements IAnalysisEvaluator{
    @Override
    public List<HealthFactor> getHealthFactors(Product p) {
        Analysis analysis = new Analysis(p);
        return analysis.getHealthFactors();
    }
}

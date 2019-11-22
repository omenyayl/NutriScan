package com.nutriscan.analysis.businessLayer;

import com.nutriscan.shared.domain.HealthFactor;
import com.nutriscan.shared.domain.Product;

import java.util.List;

public interface IAnalysisEvaluator {

    List<HealthFactor> getHealthFactors(Product p);
}



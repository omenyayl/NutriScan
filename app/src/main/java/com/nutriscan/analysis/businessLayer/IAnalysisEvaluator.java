package com.nutriscan.analysis.businessLayer;

import android.content.Context;

import com.nutriscan.shared.domain.Analysis;
import com.nutriscan.shared.domain.HealthFactor;
import com.nutriscan.shared.domain.Product;

import java.util.List;

public interface IAnalysisEvaluator {
    Analysis getAnalysis();
    Product getProduct();
    void saveProduct(Context context);
}





package com.nutriscan.analysis.presentationLayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.nutriscan.R;
import com.nutriscan.analysis.businessLayer.AnalysisEvaluator;
import com.nutriscan.analysis.businessLayer.IAnalysisEvaluator;
import com.nutriscan.analysis.presentationLayer.listAdapters.HealthFactorsAdapter;
import com.nutriscan.shared.domain.Analysis;
import com.nutriscan.shared.domain.Product;

import java.util.Locale;


/**
 * The view for the analysis functional area
 */
public class AnalysisActivity extends AppCompatActivity {
    private TextView textViewProductName;
    private TextView textViewAnalysis;
    private RecyclerView recyclerViewHealthFactors;
    private IAnalysisEvaluator analysisEvaluator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);
        bindViews();
        this.analysisEvaluator = new AnalysisEvaluator();
        updateAnalysisView(this.analysisEvaluator.getAnalysis(), this.analysisEvaluator.getProduct());
    }

    private void bindViews(){
        this.textViewProductName = findViewById(R.id.textViewProductName);
        this.textViewAnalysis = findViewById(R.id.textViewAnalysis);
        this.recyclerViewHealthFactors = findViewById(R.id.recyclerViewHealthFactors);
        findViewById(R.id.buttonAddToScanHistory).setOnClickListener(v -> onClickButtonAddToScanHistory());
    }

    private void onClickButtonAddToScanHistory() {
        this.analysisEvaluator.saveProduct(this);
    }

    /**
     * Sets the text and list data in this view
     * @param analysis The analysis to use
     * @param product The product to use
     */
    private void updateAnalysisView(Analysis analysis, Product product){
        HealthFactorsAdapter healthFactorsAdapter = new HealthFactorsAdapter(analysis.getHealthFactors());
        this.recyclerViewHealthFactors.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerViewHealthFactors.setAdapter(healthFactorsAdapter);
        this.recyclerViewHealthFactors.setNestedScrollingEnabled(false);

        this.textViewProductName.setText(product.getName());
        this.textViewAnalysis.setText(String.format(Locale.US, "%.1f", analysis.getHealthRating()));
        colorHealthRatingTextView(analysis.getHealthRating());
    }

    private void colorHealthRatingTextView(double healthRating) {
        int color = 0;
        switch ((int) healthRating) {
            case 1:
                color = ContextCompat.getColor(this, R.color.analysis_1);
                break;
            case 2:
                color = ContextCompat.getColor(this, R.color.analysis_2);
                break;
            case 3:
                color = ContextCompat.getColor(this, R.color.analysis_4);
                break;
            case 4:
            case 5:
                color = ContextCompat.getColor(this, R.color.analysis_5);
                break;
        }
        this.textViewAnalysis.setTextColor(color);
    }
}

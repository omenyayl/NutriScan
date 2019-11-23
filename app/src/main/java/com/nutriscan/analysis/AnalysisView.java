

package com.nutriscan.analysis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.nutriscan.R;
import com.nutriscan.analysis.businessLayer.AnalysisEvaluator;
import com.nutriscan.analysis.businessLayer.IAnalysisEvaluator;
import com.nutriscan.analysis.listAdapters.HealthFactorsAdapter;
import com.nutriscan.shared.domain.Analysis;
import com.nutriscan.shared.domain.HealthFactor;
import com.nutriscan.shared.repositories.FoodRepository;

import java.util.List;
import java.util.Locale;

public class AnalysisView extends AppCompatActivity {
    private TextView textViewProductName;
    private TextView textViewAnalysis;
    private RecyclerView recyclerViewHealthFactors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);
        bindViews();

        /*
        TODO: REMOVE COMMUNICATION FROM VIEW DIRECTLY TO REPOSITORY (AS THIS IS NOT LAYERED)
         */
        FoodRepository.getInstance().getScannedItem().observe(this, p -> {
            if (p != null) {
                IAnalysisEvaluator analysisEvaluator = new AnalysisEvaluator(p);
                Analysis analysis = analysisEvaluator.getAnalysis();
                initHealthFactorList(analysis.getHealthFactors());
                textViewProductName.setText(p.getName());
                textViewAnalysis.setText(String.format(Locale.US, "%.1f", analysis.getHealthRating()));
            }
        });
    }

    private void bindViews(){
        this.textViewProductName = findViewById(R.id.textViewProductName);
        this.textViewAnalysis = findViewById(R.id.textViewAnalysis);
        this.recyclerViewHealthFactors = findViewById(R.id.recyclerViewHealthFactors);
        findViewById(R.id.buttonAddToScanHistory).setOnClickListener(v -> onClickButtonAddToScanHistory());
    }

    private void onClickButtonAddToScanHistory() {

    }

    private void initHealthFactorList(List<HealthFactor> healthFactors){
        HealthFactorsAdapter healthFactorsAdapter = new HealthFactorsAdapter(healthFactors);
        this.recyclerViewHealthFactors.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerViewHealthFactors.setAdapter(healthFactorsAdapter);
        this.recyclerViewHealthFactors.setNestedScrollingEnabled(false);
    }
}

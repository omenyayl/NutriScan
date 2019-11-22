

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
import com.nutriscan.shared.domain.HealthFactor;

import java.util.List;

public class AnalysisView extends AppCompatActivity {
    private TextView textViewProductName;
    private TextView textViewAnalysis;
    private RecyclerView recyclerViewHealthFactors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);
        bindViews();
        IAnalysisEvaluator analysisEvaluator = new AnalysisEvaluator();
//        initHealthFactorList(analysisEvaluator.getHealthFactors());
    }

    private void bindViews(){
        this.textViewProductName = findViewById(R.id.textViewProductName);
        this.textViewAnalysis = findViewById(R.id.textViewAnalysis);
        this.recyclerViewHealthFactors = findViewById(R.id.recyclerViewHealthFactors);

    }

    private void initHealthFactorList(List<HealthFactor> healthFactors){
        HealthFactorsAdapter healthFactorsAdapter = new HealthFactorsAdapter(healthFactors);
        this.recyclerViewHealthFactors.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerViewHealthFactors.setAdapter(healthFactorsAdapter);
        this.recyclerViewHealthFactors.setNestedScrollingEnabled(false);

    }
}

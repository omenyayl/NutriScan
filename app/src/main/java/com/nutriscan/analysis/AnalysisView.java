

package com.nutriscan.analysis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.nutriscan.R;

public class AnalysisView extends AppCompatActivity {
    private TextView textViewProductName;
    private TextView textViewAnalysis;
    private RecyclerView recyclerViewHealthFactors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);
    }

    private void bindViews(){
        this.textViewProductName = findViewById(R.id.textViewProductName);
        this.textViewAnalysis = findViewById(R.id.textViewAnalysis);
        this.recyclerViewHealthFactors = findViewById(R.id.recyclerViewHealthFactors);
    }
}

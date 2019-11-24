package com.nutriscan.scan.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.nutriscan.R;
import com.nutriscan.analysis.presentationLayer.AnalysisActivity;
import com.nutriscan.scan.viewModel.IProductDetailsViewModel;
import com.nutriscan.scan.viewModel.ProductDetailsViewModel;
import com.nutriscan.scan.view.listAdapters.NutrientListAdapter;
import com.nutriscan.shared.domain.Nutrient;
import com.nutriscan.shared.domain.Product;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * UI for the scanning functional area
 */
public class ProductDetailsView extends AppCompatActivity {

    public static String INTENT_KEY_UPC = "upc";

    private IProductDetailsViewModel productDetailsViewModel;

    private TextView textViewProductName;
    private TextView textViewUPCValue;
    private RecyclerView recyclerViewNutrients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_loading);

        productDetailsViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())
                .create(ProductDetailsViewModel.class);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        long upc = getUPCFromIntent();

        if (upc != -1) {
            productDetailsViewModel.getProduct(this, upc).observe(this, p -> {
                if (p != null) {
                    setContentView(R.layout.activity_product_details);
                    bindViews();
                    updateProductData(p);
                }
            });
        }

//        launchScanner();
    }

    private long getUPCFromIntent() {
        Intent intent = getIntent();

        if (intent != null && intent.hasExtra(INTENT_KEY_UPC)) {
            return intent.getLongExtra(INTENT_KEY_UPC, -1);
        }
        return -1;
    }

    private void bindViews(){
        this.textViewProductName = findViewById(R.id.textViewProductName);
        this.textViewUPCValue = findViewById(R.id.textViewUPCValue);
        this.recyclerViewNutrients = findViewById(R.id.recyclerViewNutrients);
        findViewById(R.id.analyzeButton).setOnClickListener(v -> onAnalyzeButtonClicked());
    }

    private void initNutrientsList(List<Nutrient> nutrientList) {
        NutrientListAdapter adapter = new NutrientListAdapter(nutrientList);
        recyclerViewNutrients.setNestedScrollingEnabled(false);
        recyclerViewNutrients.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewNutrients.setAdapter(adapter);
    }

    private void updateProductData(Product product) {
        this.textViewProductName.setText(product.getName());
        this.textViewUPCValue.setText(String.format(Locale.US, "%d", product.getUpc()));
        initNutrientsList(product.getNutrients());
    }

    private void onAnalyzeButtonClicked() {
        Intent analysisIntent = new Intent(this, AnalysisActivity.class);
        startActivity(analysisIntent);
    }

    // region <scanning>



    // endregion
    // region <menu>
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
    // endregion
}

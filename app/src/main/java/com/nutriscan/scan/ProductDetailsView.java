package com.nutriscan.scan;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nutriscan.R;
import com.nutriscan.analysis.AnalysisView;
import com.nutriscan.shared.repositories.FoodRepository;
import com.nutriscan.misc.enums.NutrientType;
import com.nutriscan.misc.enums.Unit;
import com.nutriscan.scan.listAdapters.NutrientListAdapter;
import com.nutriscan.shared.domain.Nutrient;
import com.nutriscan.shared.domain.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * UI for the scanning functional area
 */
public class ProductDetailsView extends AppCompatActivity {

    private static final int REQUEST_CODE_SCAN = 102;
    private static final int REQUEST_CAMERA_PERMISSION = 103;

    private TextView textViewProductName;
    private TextView textViewUPCValue;
    private RecyclerView recyclerViewNutrients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        bindViews();

        List<Nutrient> nutrients = new ArrayList<>();
        nutrients.add(new Nutrient(NutrientType.ENERGY, 200.0));
        nutrients.add(new Nutrient(NutrientType.FAT, 20.0));
        nutrients.add(new Nutrient(NutrientType.SUGARS, 40.0));
        Product product = new Product(123456, "Oreo Cookies", nutrients);
        updateProductData(product);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
//        launchScanner();
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
        Intent analysisIntent = new Intent(this, AnalysisView.class);
        startActivity(analysisIntent);
    }

    /**
     * Called when the barcode was scanned
     * @param barcode Scanned barcode
     */
    private void onScanBarcode(String barcode) {
        Toast.makeText(this, barcode, Toast.LENGTH_SHORT).show();
    }

    // region <scanning>
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SCAN &&
                resultCode == RESULT_OK &&
                data != null &&
                data.hasExtra(ScanActivity.INTENT_EXTRA_BARCODE)) {
            onScanBarcode(data.getStringExtra(ScanActivity.INTENT_EXTRA_BARCODE));
        } else if (requestCode == REQUEST_CODE_SCAN && resultCode != RESULT_OK) {
            finish();
        }
    }

    public void launchScanner() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
        } else {
            Intent intent = new Intent(this, ScanActivity.class);
            startActivityForResult(intent, REQUEST_CODE_SCAN);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                launchScanner();
            } else {
                Toast.makeText(this, "Please grant camera permission to use the scanner", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    // endregion
    // region <menu>
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
    // endregion
}

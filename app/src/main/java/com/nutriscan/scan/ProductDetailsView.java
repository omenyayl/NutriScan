package com.nutriscan.scan;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.nutriscan.R;

import java.util.Objects;

/**
 * UI for the scanning functional area
 */
public class ProductDetailsView extends AppCompatActivity {

    private static final int REQUEST_CODE_SCAN = 102;
    private static final int REQUEST_CAMERA_PERMISSION = 103;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        launchScanner();
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
        if (requestCode == REQUEST_CODE_SCAN &&
                resultCode == RESULT_OK &&
                data != null &&
                data.hasExtra(ScanActivity.INTENT_EXTRA_BARCODE)) {
            onScanBarcode(data.getStringExtra(ScanActivity.INTENT_EXTRA_BARCODE));
        }
        else if (requestCode == REQUEST_CODE_SCAN && resultCode != RESULT_OK) {
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

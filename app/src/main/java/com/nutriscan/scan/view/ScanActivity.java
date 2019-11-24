package com.nutriscan.scan.view;

import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Launch this to scan a barcode, it will add the scanned text to its result intent data
 */
public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    public static final String INTENT_EXTRA_BARCODE = "barcode";

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    /**
     * @param rawResult The barcode result
     */
    @Override
    public void handleResult(Result rawResult) {
        long upc;
        try {
            upc = Long.parseLong(rawResult.toString());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid barcode", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        Intent productDetailsIntent = new Intent(this, ProductDetailsView.class);
        productDetailsIntent.putExtra(ProductDetailsView.INTENT_KEY_UPC, upc);
        startActivity(productDetailsIntent);
    }

}

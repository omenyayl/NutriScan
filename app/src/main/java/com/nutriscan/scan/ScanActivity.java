package com.nutriscan.scan;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

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
        Intent finishIntent = new Intent();
        finishIntent.putExtra(INTENT_EXTRA_BARCODE, rawResult.toString());
        setResult(RESULT_OK, finishIntent);
        finish();
    }
}

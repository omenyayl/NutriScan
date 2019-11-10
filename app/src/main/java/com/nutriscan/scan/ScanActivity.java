package com.nutriscan.scan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private static final String TAG = "ScanActivity";
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

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
//        Log.v(TAG, rawResult.getText()); // Prints scan results
//        Log.v(TAG, rawResult.getBarcodeFormat().toString()); // Prints the scan format (QR code, pdf417 etc.)
        Intent finishIntent = new Intent();
        finishIntent.putExtra(INTENT_EXTRA_BARCODE, rawResult.toString());
        setResult(RESULT_OK, finishIntent);
        finish();
        // If you would like to resume scanning, call this method below:
//        mScannerView.resumeCameraPreview(this);
    }
}

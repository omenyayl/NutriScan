package com.nutriscan.scan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.nutriscan.R;

import java.util.Objects;

/**
 * UI for the scanning functional area
 */
public class ScanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}

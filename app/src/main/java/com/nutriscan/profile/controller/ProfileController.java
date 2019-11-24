package com.nutriscan.profile.controller;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.nutriscan.R;
import com.nutriscan.profile.view.listAdapters.ScanHistoryAdapter;
import com.nutriscan.scan.view.ProductDetailsView;
import com.nutriscan.scan.view.ScanActivity;
import com.nutriscan.shared.domain.Nutrient;
import com.nutriscan.shared.domain.Person;
import com.nutriscan.shared.domain.Product;
import com.nutriscan.shared.domain.ScanLog.IScanLog;
import com.nutriscan.shared.repositories.PersonRepository;
import com.nutriscan.shared.repositories.ScanHistoryRepository;

import java.util.Locale;

public class ProfileController extends AppCompatActivity {

    private RecyclerView recyclerViewScanHistory;
    private TextView textViewCaloriesValue;
    private TextView textViewTotalFatValue;
    private TextView textViewCholesterolValue;
    private TextView textViewSodiumValue;
    private TextView textViewCarbohydrateValue;
    private TextView textViewProteinValue;

    private static final int REQUEST_CODE_SCAN = 102;
    private static final int REQUEST_CAMERA_PERMISSION = 103;
    private static final int PERMISSION_REQUEST_PHONE_STATE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        bindViews();
        obtainDeviceID();
        initObservations();
    }


    private void initObservations() {
        // first obtain the Person model then use that person model to obtain that person's
        // scan history
        PersonRepository.getInstance().getPerson().observe(this, person -> {
            if (person != null) {
                Log.d("ProfileController", person.getId());
                Toast.makeText(this, "Person ID: " + person.getId(), Toast.LENGTH_LONG).show();
                ScanHistoryRepository.getInstance()
                        .getProducts(this, person).observe(this, this::onRetrieveScanHistory);
            }
        });
    }

    private void bindViews() {
        recyclerViewScanHistory = findViewById(R.id.recyclerViewScanHistory);
        textViewCaloriesValue = findViewById(R.id.textViewCaloriesValue);
        textViewTotalFatValue =findViewById(R.id.textViewTotalFatValue);
        textViewCholesterolValue = findViewById(R.id.textViewCholesterolValue);
        textViewSodiumValue = findViewById(R.id.textViewSodiumValue);
        textViewCarbohydrateValue = findViewById(R.id.textViewCarbohydrateValue);
        textViewProteinValue = findViewById(R.id.textViewProteinValue);
    }

    // region <Scan History>

    /**
     * Called upon retrieval of the scan log
     * @param scanLog The retrieved scan log (might be null)
     */
    private void onRetrieveScanHistory(@Nullable IScanLog<Product> scanLog) {
        if (scanLog != null) {
            initScanHistory(scanLog);
            for (Product product : scanLog.getItems()) {
                for (Nutrient nutrient : product.getNutrients()) {
                    switch (nutrient.getNutrientType()){
                        case ENERGY:
                            appendNutrientAmountToTextView(nutrient,textViewCaloriesValue);
                            break;
                        case MONOUNSATURATED:
                        case POLYUNSATURATED:
                        case SATURATED:
                        case FAT:
                        case TRANS:
                            appendNutrientAmountToTextView(nutrient,textViewTotalFatValue);
                            break;
                        case CHOLESTEROL:
                            appendNutrientAmountToTextView(nutrient,textViewCholesterolValue);
                            break;
                        case SODIUM:
                            appendNutrientAmountToTextView(nutrient,textViewSodiumValue);
                            break;
                        case CARBS:
                        case SUGARS:
                            appendNutrientAmountToTextView(nutrient,textViewCarbohydrateValue);
                            break;
                        case PROTEIN:
                            appendNutrientAmountToTextView(nutrient,textViewProteinValue);
                            break;
                    }
                    // For debugging purposes bottom logs the nutrient data (shown in Logcat).
                    Log.d(this.getClass().getName(), nutrient.toString() + ": " + nutrient.getAmount() + ' ' + nutrient.getUnit());
                }
            }
        }
    }

    private void appendNutrientAmountToTextView(Nutrient nutrient,TextView textView){
        double currentAmount= Double.parseDouble(textView.getText().toString());
        double totalAmount=currentAmount+nutrient.getAmount();
        textView.setText(String.format(Locale.US, "%.1f",totalAmount));
    }

    private void initScanHistory(IScanLog<Product> scanLog) {
        ScanHistoryAdapter adapter = new ScanHistoryAdapter(scanLog);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewScanHistory.setLayoutManager(layoutManager);
        recyclerViewScanHistory.setAdapter(adapter);
        recyclerViewScanHistory.setNestedScrollingEnabled(false);
        adapter.setOnModelClickListener(model -> launchProductDetailsView(model.getUpc()));
    }

    // endregion

    // region <Menu>
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_scan) {
//            Intent scanIntent = new Intent(this, ProductDetailsView.class);
//            startActivity(scanIntent);
            launchScanner();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // endregion

    // region <Device ID>
    /**
     * Gets the device identifier from the phone and posts its value into this.liveDeviceID
     */
    private void obtainDeviceID() {
        if (PersonRepository.getInstance().getPerson().getValue() == null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                String deviceID;
                deviceID = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getImei();
                if (deviceID == null) deviceID = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getMeid();
                PersonRepository.getInstance().getPerson().postValue(new Person(deviceID));
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_PHONE_STATE},
                        PERMISSION_REQUEST_PHONE_STATE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_PHONE_STATE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                obtainDeviceID();
            }
            else {
                Toast.makeText(this, "Your device ID is required for this app.", Toast.LENGTH_LONG)
                        .show();
                finish();
            }
        }
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                launchScanner();
            } else {
                Toast.makeText(this, "Please grant camera permission to use the scanner", Toast.LENGTH_SHORT).show();
            }
        }
    }
    // endregion

    private void launchProductDetailsView(long upc) {
        Intent productDetailsIntent = new Intent(this, ProductDetailsView.class);
        productDetailsIntent.putExtra(ProductDetailsView.INTENT_KEY_UPC, upc);
        startActivity(productDetailsIntent);
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

}

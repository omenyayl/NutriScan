package com.nutriscan.profile;

import android.Manifest;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.nutriscan.R;
import com.nutriscan.profile.listAdapters.ScanHistoryAdapter;
import com.nutriscan.shared.domain.Product;
import com.nutriscan.shared.domain.ScanLog.IScanLog;
import com.nutriscan.shared.domain.ScanLog.ScanLog;

import java.util.Locale;

public class ProfileController extends AppCompatActivity {

    private RecyclerView recyclerViewScanHistory;
    private static final int PERMISSION_REQUEST_PHONE_STATE = 101;
    private MutableLiveData<String> liveDeviceID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        bindViews();
        initScanHistory();
        liveDeviceID = new MutableLiveData<>();
        obtainDeviceID(liveDeviceID);

        liveDeviceID.observe(this, id -> {
            if (id != null) {
                // TODO: Device identifier obtained here, do something with it
                Toast.makeText(this, "ID: " + id, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Gets the device identifier from the phone and posts its value into this.liveDeviceID
     */
    private void obtainDeviceID(MutableLiveData<String> liveDeviceID) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            String IMEI;
            IMEI = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getImei();
            if (IMEI == null) IMEI = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getMeid();
            liveDeviceID.postValue(IMEI);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_PHONE_STATE},
                    PERMISSION_REQUEST_PHONE_STATE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_PHONE_STATE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                obtainDeviceID(this.liveDeviceID);
            }
            else {
                Toast.makeText(this, "Your device ID is required for this app.", Toast.LENGTH_LONG)
                        .show();
                finish();
            }
        }
    }

    private void bindViews() {
        recyclerViewScanHistory = findViewById(R.id.recyclerViewScanHistory);
    }

    private void initScanHistory() {
        IScanLog<Product> products = new ScanLog();
        /* Sample items */
        for (int i = 0; i < 25; i++) {
            products.addItem(new Product(String.format(Locale.US,"%012d", i), "Product " + i));
        }
        ScanHistoryAdapter adapter = new ScanHistoryAdapter(products);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewScanHistory.setLayoutManager(layoutManager);
        recyclerViewScanHistory.setAdapter(adapter);
        recyclerViewScanHistory.setNestedScrollingEnabled(false);
        adapter.setOnModelClickListener(model ->
                Toast.makeText(this, model.toString(), Toast.LENGTH_SHORT).show());
    }
}

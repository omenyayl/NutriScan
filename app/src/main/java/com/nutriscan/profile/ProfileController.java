package com.nutriscan.profile;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.nutriscan.R;
import com.nutriscan.profile.listAdapters.ScanHistoryAdapter;
import com.nutriscan.scan.ProductDetailsView;
import com.nutriscan.shared.domain.Person;
import com.nutriscan.shared.domain.Product;
import com.nutriscan.shared.domain.ScanLog.IScanLog;
import com.nutriscan.shared.repositories.PersonRepository;
import com.nutriscan.shared.repositories.ScanHistoryRepository;

public class ProfileController extends AppCompatActivity {

    private RecyclerView recyclerViewScanHistory;
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
        PersonRepository.getInstance().getPerson().observe(this, person -> {
            if (person != null) {
                // TODO: Device identifier obtained here, do something with it
                Toast.makeText(this, "ID: " + person, Toast.LENGTH_SHORT).show();
            }
        });

        ScanHistoryRepository.getInstance().getProducts().observe(this, scanLog -> {
            if (scanLog != null) {
                initScanHistory(scanLog);
            }
        });
    }

    private void bindViews() {
        recyclerViewScanHistory = findViewById(R.id.recyclerViewScanHistory);
    }

    // region <Scan History>

    private void initScanHistory(IScanLog<Product> scanLog) {
        ScanHistoryAdapter adapter = new ScanHistoryAdapter(scanLog);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewScanHistory.setLayoutManager(layoutManager);
        recyclerViewScanHistory.setAdapter(adapter);
        recyclerViewScanHistory.setNestedScrollingEnabled(false);
        adapter.setOnModelClickListener(model ->
                Toast.makeText(this, model.toString(), Toast.LENGTH_SHORT).show());
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
            Intent scanIntent = new Intent(this, ProductDetailsView.class);
            startActivity(scanIntent);
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
    }
    // endregion
}

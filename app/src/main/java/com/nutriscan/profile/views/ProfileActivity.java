package com.nutriscan.profile.views;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nutriscan.R;
import com.nutriscan.profile.listAdapters.ScanHistoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    RecyclerView recyclerViewScanHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews(this);
    }

    private void bindViews(final Context context) {
        recyclerViewScanHistory = findViewById(R.id.recyclerViewScanHistory);
        List<String> names = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            names.add("Test");
        }
        ScanHistoryAdapter adapter = new ScanHistoryAdapter(names);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewScanHistory.setLayoutManager(layoutManager);
        recyclerViewScanHistory.setAdapter(adapter);
        recyclerViewScanHistory.setNestedScrollingEnabled(false);
    }
}

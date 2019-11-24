package com.nutriscan.shared.API;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.nutriscan.shared.misc.constants.Endpoints;
import com.nutriscan.shared.misc.enums.NutrientType;
import com.nutriscan.shared.domain.Nutrient;
import com.nutriscan.shared.domain.Person;
import com.nutriscan.shared.domain.Product;
import com.nutriscan.shared.domain.ScanLog.IScanLog;
import com.nutriscan.shared.domain.ScanLog.ScanLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProfileAPI {
    private static final String LOG_TAG = ProfileAPI.class.getName();
    private static ProfileAPI instance;

    private ProfileAPI() { }

    public static ProfileAPI getInstance() {
        if (instance == null) instance = new ProfileAPI();
        return instance;
    }

    /**
     * Send an HTTP request to retrieve the scan history for the given Person
     * @param iScanLogMutableLiveData Which observable to post the result to
     * @param context Android Context
     * @param person The person of which to obtain the scan history
     */
    public void enqueueGetProductsRequest(MutableLiveData<IScanLog<Product>> iScanLogMutableLiveData,
                                           Context context,
                                           Person person) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = Endpoints.getProfileScanHistoryEndpoint(person);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                url,
                response -> ((Runnable) () -> { // running a new thread to not overload the ui thread
                    try {
                        IScanLog<Product> scanLog = getScanLogFromJSONArray(response);
                        iScanLogMutableLiveData.postValue(scanLog);
                    } catch (JSONException e) {
                        Log.e(LOG_TAG, e.toString());
                        Toast.makeText(context, "An error occurred when parsing data from the server", Toast.LENGTH_LONG).show();
                    }
                }).run(),
                error -> {
                    if (error != null) {
                        Log.e(LOG_TAG, error.toString());
                        Toast.makeText(context, "Error obtaining scan history data from the server", Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    /**
     * @param jsonArray The JSON Array to parse
     * @return Scan Log parsed from the given jsonArray
     * @throws JSONException if could not parse the given JSON Array
     */
    private IScanLog<Product> getScanLogFromJSONArray(JSONArray jsonArray) throws JSONException {
        IScanLog<Product> scanLog = new ScanLog();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject product = jsonArray.getJSONObject(i);
            long upc = product.getLong("upc");
            String name = product.getString("name");
            List<Nutrient> nutrientList = new ArrayList<>();
            JSONArray nutrients = product.getJSONArray("nutrients");
            for (int j = 0; j < nutrients.length(); j++) {
                JSONObject nutrient = nutrients.getJSONObject(j);
                String nutrientName = nutrient.getString("name");
                double nutrientAmount = nutrient.getDouble("amount");
                NutrientType nutrientType;
                try {
                    nutrientType = NutrientType.fromString(nutrientName);
                } catch (IllegalArgumentException e) {
                    throw new JSONException(e.toString());
                }
                nutrientList.add(new Nutrient(nutrientType, nutrientAmount));
            }
            scanLog.addItem(new Product(upc, name, nutrientList));
        }

        return scanLog;
    }
}

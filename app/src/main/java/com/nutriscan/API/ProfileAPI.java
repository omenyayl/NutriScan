package com.nutriscan.API;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.nutriscan.misc.constants.APIConstants;
import com.nutriscan.shared.domain.Person;
import com.nutriscan.shared.domain.Product;
import com.nutriscan.shared.domain.ScanLog.IScanLog;
import com.nutriscan.shared.domain.ScanLog.ScanLog;

import org.json.JSONException;
import org.json.JSONObject;

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
        String url = String.format("%s/scans/%s", APIConstants.API_BASE_URL, person.getId());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                url,
                response -> ((Runnable) () -> { // running a new thread to not overload the ui thread
                    try {
                        IScanLog<Product> scanLog = new ScanLog();
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject product = response.getJSONObject(i);
                            String upc = product.getString("upc");
                            String name = product.getString("name");
                            scanLog.addItem(new Product(upc, name));
                        }
                        iScanLogMutableLiveData.postValue(scanLog);
                    } catch (JSONException e) {
                        Log.e(LOG_TAG, e.toString());
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
}

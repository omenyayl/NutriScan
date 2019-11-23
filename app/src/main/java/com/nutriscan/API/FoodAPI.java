package com.nutriscan.API;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.nutriscan.misc.enums.NutrientType;
import com.nutriscan.shared.domain.Nutrient;
import com.nutriscan.shared.domain.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FoodAPI {
    private static FoodAPI instance;

    private FoodAPI() {}

    public static FoodAPI getInstance() {
        if (instance == null) instance = new FoodAPI();
        return instance;
    }

    /**
     * @param productMutableLiveData Product observable of which to post the result to
     * @param context Android Context
     * @param upc The UPC code of the product
     */
    public void enqueueGetProductRequest(MutableLiveData<Product> productMutableLiveData,
                                         Context context,
                                         long upc) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = Endpoints.getProductEndpoint(upc);
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(
                url,
                null,
                response -> {
                    try {
                        Product product = getProductFromJSON(response);
                        productMutableLiveData.postValue(product);
                    } catch (JSONException | IllegalArgumentException e) {
                        Toast.makeText(context, "Unable to process product information.", Toast.LENGTH_LONG).show();
                        Log.e(getClass().getName(), e.toString());
                    }
                },
                error -> {
                    if (error != null) {
                        if (error.networkResponse.statusCode == 404) {
                            Toast.makeText(context, "Could not find the product information.", Toast.LENGTH_LONG).show();
                        } else {
                            Log.e(getClass().getName(), error.toString());
                            Toast.makeText(context, "Error obtaining product data.", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    private Product getProductFromJSON(JSONObject jsonObjectProduct) throws JSONException, IllegalArgumentException {
        JSONArray jsonArrayNutrients = jsonObjectProduct.getJSONArray("nutrients");
        List<Nutrient> nutrientList = new ArrayList<>();
        for (int i = 0; i < jsonArrayNutrients.length(); i++) {
            JSONObject jsonObjectNutrient = jsonArrayNutrients.getJSONObject(i);
            Nutrient nutrient = new Nutrient(
                    NutrientType.fromString(jsonObjectNutrient.getString("name")),
                    jsonObjectNutrient.getDouble("amount")
            );
            nutrientList.add(nutrient);
        }
        return new Product(
                jsonObjectProduct.getLong("upc"),
                jsonObjectProduct.getString("name"),
                nutrientList
        );
    }
}

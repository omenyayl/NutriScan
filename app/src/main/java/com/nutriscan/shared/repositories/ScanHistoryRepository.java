package com.nutriscan.shared.repositories;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.nutriscan.shared.API.ProfileAPI;
import com.nutriscan.shared.misc.enums.NutrientType;
import com.nutriscan.shared.domain.Nutrient;
import com.nutriscan.shared.domain.Person;
import com.nutriscan.shared.domain.Product;
import com.nutriscan.shared.domain.ScanLog.IScanLog;
import com.nutriscan.shared.domain.ScanLog.ScanLog;

import java.util.ArrayList;
import java.util.List;


/**
 * Repository containing the scan history data
 */
public class ScanHistoryRepository implements IScanHistoryRepository{
    private static ScanHistoryRepository instance;
    private MutableLiveData<IScanLog<Product>> scanLog;

    private ScanHistoryRepository() {
        scanLog = new MutableLiveData<>();
    }

    private void mockRequestProducts() {
        IScanLog<Product> products = new ScanLog();
        /* Sample items */
        for (int i = 0; i < 25; i++) {
            List<Nutrient> nutrientList = new ArrayList<>();
            nutrientList.add(new Nutrient(NutrientType.ENERGY, 10*i));
            products.addItem(new Product(i, "Product " + i, nutrientList));
        }
        scanLog.postValue(products);
    }

    public static ScanHistoryRepository getInstance() {
        if (instance == null) instance = new ScanHistoryRepository();
        return instance;
    }

    /**
     * @return An observable containing the scan history of products
     * @param person The person of which to obtain the scan log
     */
    public MutableLiveData<IScanLog<Product>> getProducts(Context context, @NonNull Person person) {
        ProfileAPI.getInstance().enqueueGetProductsRequest(this.scanLog, context, person);
//        mockRequestProducts();
        return scanLog;
    }

    @Override
    public void saveProduct(Context context, @NonNull Person person, @NonNull Product product) {
        ProfileAPI.saveProduct(context, person, product);
        IScanLog<Product> scanHistory = this.scanLog.getValue();
        if (scanHistory != null) {
            scanHistory.addItem(product);
            this.scanLog.postValue(scanHistory);
        }
    }

}

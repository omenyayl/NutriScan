package com.nutriscan.shared.repositories;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.nutriscan.shared.domain.Person;
import com.nutriscan.shared.domain.Product;
import com.nutriscan.shared.domain.ScanLog.IScanLog;
import com.nutriscan.shared.domain.ScanLog.ScanLog;


/**
 * Repository containing the scan history data
 */
public class ScanHistoryRepository {
    private static ScanHistoryRepository instance;
    private MutableLiveData<IScanLog<Product>> scanLog;

    private ScanHistoryRepository() {
        scanLog = new MutableLiveData<>();
    }

    private void mockRequestProducts() {
        IScanLog<Product> products = new ScanLog();
        /* Sample items */
        for (int i = 0; i < 25; i++) {
            products.addItem(new Product(i, "Product " + i));
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
//        ProfileAPI.getInstance().enqueueGetProductsRequest(this.scanLog, context, person);
        mockRequestProducts();
        return scanLog;
    }

}

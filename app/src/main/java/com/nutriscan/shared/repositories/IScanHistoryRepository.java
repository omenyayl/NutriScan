package com.nutriscan.shared.repositories;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.nutriscan.shared.domain.Person;
import com.nutriscan.shared.domain.Product;
import com.nutriscan.shared.domain.ScanLog.IScanLog;

public interface IScanHistoryRepository {
    MutableLiveData<IScanLog<Product>> getProducts(Context context, @NonNull Person person);
    void saveProduct(Context context, @NonNull Person person);
}

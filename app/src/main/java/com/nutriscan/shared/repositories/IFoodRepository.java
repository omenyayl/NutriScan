package com.nutriscan.shared.repositories;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.nutriscan.shared.domain.Product;

/**
 * Defines the contract for the shared food repository
 */
public interface IFoodRepository {
    MutableLiveData<Product> getScannedItem();

    /**
     * Retrieve the product from the backend
     * @param context Android context
     * @param upc UPC of the product
     * @return Observable to the product
     */
    MutableLiveData<Product> getProduct(Context context, long upc);
}

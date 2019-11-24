package com.nutriscan.shared.repositories;

import androidx.lifecycle.MutableLiveData;

import com.nutriscan.shared.domain.Product;

/**
 * Defines the contract for the shared food repository
 */
public interface IFoodRepository {
    MutableLiveData<Product> getScannedItem();
}

package com.nutriscan.scan.viewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.nutriscan.API.FoodAPI;
import com.nutriscan.shared.domain.Product;
import com.nutriscan.shared.repositories.FoodRepository;

public class ProductDetailsViewModel extends ViewModel implements IProductDetailsViewModel{

    @Override
    public LiveData<Product> getScannedProduct() {
        return FoodRepository.getInstance().getScannedItem();
    }

    @Override
    public void onItemScanned(long upc, Context context) {
        FoodAPI.getInstance().enqueueGetProductRequest(
                FoodRepository.getInstance().getScannedItem(),
                context,
                upc
        );
    }
}

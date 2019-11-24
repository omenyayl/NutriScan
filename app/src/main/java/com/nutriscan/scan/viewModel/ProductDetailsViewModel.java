package com.nutriscan.scan.viewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.nutriscan.shared.API.FoodAPI;
import com.nutriscan.shared.domain.Product;
import com.nutriscan.shared.repositories.FoodRepository;

public class ProductDetailsViewModel extends ViewModel implements IProductDetailsViewModel{
    @Override
    public LiveData<Product> getProduct(Context context, long upc) {
        return FoodRepository.getInstance().getProduct(context, upc);
    }

    public void resetProduct() {
        FoodRepository.getInstance().getScannedItem().setValue(null);
    }
}

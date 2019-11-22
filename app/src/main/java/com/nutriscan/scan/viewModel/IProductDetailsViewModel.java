package com.nutriscan.scan.viewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import com.nutriscan.shared.domain.Product;

public interface IProductDetailsViewModel {
    LiveData<Product> getScannedProduct();
    void onItemScanned(long upc, Context context);
}

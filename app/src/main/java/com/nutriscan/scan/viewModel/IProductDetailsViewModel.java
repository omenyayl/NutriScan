package com.nutriscan.scan.viewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import com.nutriscan.shared.domain.Product;

public interface IProductDetailsViewModel {
    /**
     * @return Product observable
     */
    LiveData<Product> getScannedProduct();

    /**
     * Called when the item finishes scanning
     * @param upc The UPC of the scanned item
     * @param context Android context
     */
    void onItemScanned(long upc, Context context);
}

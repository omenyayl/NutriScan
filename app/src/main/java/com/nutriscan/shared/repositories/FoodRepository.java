package com.nutriscan.shared.repositories;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.nutriscan.shared.API.FoodAPI;
import com.nutriscan.shared.misc.enums.NutrientType;
import com.nutriscan.shared.domain.Nutrient;
import com.nutriscan.shared.domain.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository for food
 */
public class FoodRepository implements IFoodRepository{
    private static FoodRepository instance;
    private MutableLiveData <Product> scannedItem;

    private FoodRepository() {
        this.scannedItem = new MutableLiveData<>();
    }

    public static FoodRepository getInstance() {
        if (instance == null) instance = new FoodRepository();
        return instance;
    }

    public MutableLiveData<Product> getScannedItem() {
//        mockScannedItem();
        return scannedItem;
    }

    public MutableLiveData<Product> getProduct(Context context, long upc) {
        FoodAPI.enqueueGetProductRequest(
                this.scannedItem,
                context,
                upc
        );
        return scannedItem;
    }

    private void mockScannedItem() {
        List<Nutrient> nutrients = new ArrayList<>();
        nutrients.add(new Nutrient(NutrientType.ENERGY, 400));
        nutrients.add(new Nutrient(NutrientType.PROTEIN, 20));
        nutrients.add(new Nutrient(NutrientType.FAT, 500));
        nutrients.add(new Nutrient(NutrientType.POTASSIUM, 60));
        Product p = new Product(1234567,"BROCOOLINIECHEDAR", nutrients);
        this.scannedItem.postValue(p);
    }
}

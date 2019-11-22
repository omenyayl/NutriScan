package com.nutriscan.shared.repositories;

import androidx.lifecycle.MutableLiveData;

import com.nutriscan.misc.enums.NutrientType;
import com.nutriscan.misc.enums.Unit;
import com.nutriscan.shared.domain.Nutrient;
import com.nutriscan.shared.domain.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository for food
 */
public class FoodRepository {
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
        mockScannedItem();
        return scannedItem;
    }

    private void mockScannedItem() {
        List<Nutrient> nutrients = new ArrayList<>();
        nutrients.add(new Nutrient(NutrientType.ENERGY, 400, Unit.kcal));
        nutrients.add(new Nutrient(NutrientType.PROTEIN, 20, Unit.g));
        nutrients.add(new Nutrient(NutrientType.FAT, 500, Unit.g));
        nutrients.add(new Nutrient(NutrientType.POTASSIUM, 60, Unit.mg));
        Product p = new Product(1234567,"BROCOOLINIECHEDAR", nutrients);
        this.scannedItem.postValue(p);
    }
}

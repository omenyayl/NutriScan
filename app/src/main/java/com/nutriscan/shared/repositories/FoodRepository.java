package com.nutriscan.shared.repositories;

/**
 * Repository for food
 */
public class FoodRepository {
    private static FoodRepository instance;

    private FoodRepository() {

    }

    public FoodRepository getInstance() {
        if (instance == null) instance = new FoodRepository();
        return instance;
    }



}

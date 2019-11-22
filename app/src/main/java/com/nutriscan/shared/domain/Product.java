package com.nutriscan.shared.domain;

import androidx.annotation.NonNull;

import java.util.List;

/**
 * The immutable product domain object
 */
public final class Product {
    private final long upc;
    private final String name;
    private final List<Nutrient> nutrients;

    public Product(long upc, @NonNull String name, @NonNull List<Nutrient> nutrients) {
        this.upc = upc;
        this.name = name;
        this.nutrients = nutrients;
    }

    public long getUpc() {
        return upc;
    }

    public String getName() {
        return name;
    }

    public List<Nutrient> getNutrients() {
        return nutrients;
    }

    @NonNull
    @Override
    public String toString() {
        return this.name;
    }
}

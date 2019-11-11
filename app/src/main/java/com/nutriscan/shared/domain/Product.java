package com.nutriscan.shared.domain;

import androidx.annotation.NonNull;

/**
 * The immutable product domain object
 */
public final class Product {
    private final String upc;
    private final String name;

    public Product(@NonNull String upc, @NonNull String name) {
        this.upc = upc;
        this.name = name;
    }

    public String getUpc() {
        return upc;
    }

    public String getName() {
        return name;
    }

    @NonNull
    @Override
    public String toString() {
        return this.name;
    }
}

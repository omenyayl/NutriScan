package com.nutriscan.shared.domain;

import androidx.annotation.NonNull;

/**
 * The immutable product domain object
 */
public final class Product {
    private final long upc;
    private final String name;

    public Product(long upc, @NonNull String name) {
        this.upc = upc;
        this.name = name;
    }

    public long getUpc() {
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

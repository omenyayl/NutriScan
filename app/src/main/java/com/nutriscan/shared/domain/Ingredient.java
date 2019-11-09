package com.nutriscan.shared.domain;

import android.support.annotation.NonNull;

/**
 * An immutable ingredient domain object
 */
public class Ingredient {
    private final String name;

    public Ingredient(@NonNull String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

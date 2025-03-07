package com.nutriscan.shared.domain;

import androidx.annotation.NonNull;

/**
 * The immutable Person domain object
 */
public final class Person {
    private final String id;

    public Person(@NonNull String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @NonNull
    @Override
    public String toString() {
        return this.id;
    }
}

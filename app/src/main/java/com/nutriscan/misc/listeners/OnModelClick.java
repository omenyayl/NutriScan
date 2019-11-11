package com.nutriscan.misc.listeners;

import androidx.annotation.NonNull;

/**
 * Simple listener that has an onClick method that passes an object
 * @param <E>
 */
public interface OnModelClick<E> {
    /**
     * @param model The "model" that was clicked
     */
    void onClick(@NonNull E model);
}

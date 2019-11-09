package com.nutriscan.misc.listeners;

import android.support.annotation.NonNull;

public interface OnModelClick<E> {
    void onClick(@NonNull E model);
}

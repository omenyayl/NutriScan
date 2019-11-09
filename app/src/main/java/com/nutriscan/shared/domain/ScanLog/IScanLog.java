package com.nutriscan.shared.domain.ScanLog;

import android.support.annotation.NonNull;

import com.nutriscan.shared.domain.Product;

import java.util.List;

public interface IScanLog<E> {
    void addItem(@NonNull E p);
    List<E> getItems();
}

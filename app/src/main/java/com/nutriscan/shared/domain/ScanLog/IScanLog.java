package com.nutriscan.shared.domain.ScanLog;

import androidx.annotation.NonNull;

import java.util.List;

public interface IScanLog<E> {
    void addItem(@NonNull E p);
    List<E> getItems();
}

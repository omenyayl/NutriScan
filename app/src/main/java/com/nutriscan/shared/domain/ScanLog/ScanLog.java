package com.nutriscan.shared.domain.ScanLog;

import androidx.annotation.NonNull;

import com.nutriscan.shared.domain.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * A ScanLog domain object
 */
public class ScanLog implements IScanLog<Product>{
    private List<Product> productList;

    public ScanLog() {
        productList = new ArrayList<>();
    }

    /**
     * @param p Adds the given product to this
     */
    @Override
    public void addItem(@NonNull Product p) {
        this.productList.add(p);
    }

    /**
     * @return A list of products
     */
    @Override
    public List<Product> getItems() {
        return productList;
    }
}

package com.nutriscan.shared.repositories;

public class ScanListRepository {
    private static ScanListRepository instance;

    private ScanListRepository() {

    }

    public static ScanListRepository getInstance() {
        if (instance == null) instance = new ScanListRepository();
        return instance;
    }


}

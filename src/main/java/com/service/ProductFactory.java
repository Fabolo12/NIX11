package com.service;

import com.model.product.Phone;
import com.model.product.ProductType;
import com.model.product.TV;

public class ProductFactory {
    private static final ProductService<Phone> PHONE_SERVICE = PhoneService.getInstance();
    private static final ProductService<TV> TV_SERVICE = TVService.getInstance();

    private ProductFactory() {
    }

    public static void createAndSave(ProductType type) {
        switch (type) {
            case PHONE -> PHONE_SERVICE.createAndSave(1);
            case TV -> TV_SERVICE.createAndSave(1);
            default -> throw new IllegalArgumentException("Unknown Product type: " + type);
        };
    }

}

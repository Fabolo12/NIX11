package com.service;

import com.model.product.Phone;
import com.model.product.ProductType;

import java.util.Random;

public class ProductFactory {
    private static final Random RANDOM = new Random();

    private static final ProductService<Phone> PHONE_SERVICE = PhoneService.getInstance();

    private ProductFactory() {
    }

    public static void createAndSave(ProductType type) {
        switch (type) {
            case PHONE -> PHONE_SERVICE.createAndSave(1);
            default -> throw new IllegalArgumentException("Unknown Product type: " + type);
        };
    }

}

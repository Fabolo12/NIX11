package com.service;

import com.model.product.*;

import java.util.Random;

public class ProductFactory {
    private static final Random RANDOM = new Random();

    private ProductFactory() {
    }

    public static Product creatProduct(ProductType type) {
        return switch (type) {
            case TV -> new TV(
                    "Title-" + RANDOM.nextInt(1000),
                    RANDOM.nextInt(500),
                    RANDOM.nextDouble(1000.0)
            );
            case PHONE -> new Phone(
                    "Title-" + RANDOM.nextInt(1000),
                    RANDOM.nextInt(500),
                    RANDOM.nextDouble(1000.0),
                    "Model-" + RANDOM.nextInt(10),
                    getRandomManufacturer()
            );
            default -> throw new IllegalArgumentException("Unknown Product type: " + type);
        };
    }

    private static Manufacturer getRandomManufacturer() {
        final Manufacturer[] values = Manufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }

}

package com;

import com.model.Phone;
import com.model.Product;
import com.model.ProductType;
import com.repository.PhoneRepository;
import com.service.PhoneService;
import com.service.ProductFactory;

import java.util.ArrayList;
import java.util.List;


public class Main {
    private static final PhoneService PHONE_SERVICE = new PhoneService(new PhoneRepository());

    public static void main(String[] args) {
        PHONE_SERVICE.createAndSavePhones(10);
        PHONE_SERVICE.printAll();

        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final Phone phone = (Phone) ProductFactory.creatProduct(ProductType.PHONE);
            PHONE_SERVICE.savePhone(phone);
            products.add(phone);
        }
        System.out.println(products);
    }
}

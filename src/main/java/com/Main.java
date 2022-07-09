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

        //optionalExamples();
    }

    private static void optionalExamples() {
        PHONE_SERVICE.createAndSavePhones(1);
        final String id = PHONE_SERVICE.getAll().get(0).getId();

        PHONE_SERVICE.printIfPresent(id);
        PHONE_SERVICE.printOrGetDefault(id);
        PHONE_SERVICE.printOrCreatDefault(id);
        PHONE_SERVICE.mapPhoneToString(id);
        PHONE_SERVICE.printOrPrintDefault(id);
        PHONE_SERVICE.checksPhoneLessThen(id, 1000);
        PHONE_SERVICE.checksPhoneLessThen(id, 10);
        PHONE_SERVICE.checksPhoneLessThen("123", 1000);
        try {
            PHONE_SERVICE.printPhoneOrElseThrowException(id);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        PHONE_SERVICE.printPhone(id);
    }


}
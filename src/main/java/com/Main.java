package com;

import com.model.Order;
import com.model.product.Phone;
import com.model.product.Product;
import com.model.product.TV;
import com.repository.PhoneRepository;
import com.repository.TVRepository;
import com.service.*;

import java.util.List;


public class Main {
    private static final ProductService<Phone> PHONE_SERVICE = new PhoneService(new PhoneRepository());
    private static final ProductService<TV> TV_SERVICE = new TVService(new TVRepository());

    private static final OptionalExamples OPTIONAL_EXAMPLES = new OptionalExamples(new PhoneRepository());

    private static final OrderService ORDER_SERVICE = new OrderService();

    public static void main(String[] args) {
        TV_SERVICE.createAndSave(2);
        PHONE_SERVICE.createAndSave(2);

        final List<TV> tvs = TV_SERVICE.getAll();
        final Order order = ORDER_SERVICE.creatOrder(tvs);
        System.out.println(order);

        System.out.println("~".repeat(5));

        final List<Phone> phones = PHONE_SERVICE.getAll();
        ORDER_SERVICE.addProducts(order, phones);
        System.out.println(order);

        System.out.println("~".repeat(5));

        final Product remove = ORDER_SERVICE.remove(order, 1);
        System.out.println(order);

        System.out.println("~".repeat(5));

        ORDER_SERVICE.setProduct(order, 0, remove);
        System.out.println(order);

        System.out.println("~".repeat(5));

        ORDER_SERVICE.addProduct(order, 0, remove);
        System.out.println(order);
    }

    private static void optionalExamples() {
        PHONE_SERVICE.createAndSave(1);
        final String id = PHONE_SERVICE.getAll().get(0).getId();

        OPTIONAL_EXAMPLES.printIfPresent(id);
        OPTIONAL_EXAMPLES.printOrGetDefault(id);
        OPTIONAL_EXAMPLES.printOrCreatDefault(id);
        OPTIONAL_EXAMPLES.mapPhoneToString(id);
        OPTIONAL_EXAMPLES.printOrPrintDefault(id);
        OPTIONAL_EXAMPLES.checksPhoneLessThen(id, 1000);
        OPTIONAL_EXAMPLES.checksPhoneLessThen(id, 10);
        OPTIONAL_EXAMPLES.checksPhoneLessThen("123", 1000);
        try {
            OPTIONAL_EXAMPLES.printPhoneOrElseThrowException(id);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        OPTIONAL_EXAMPLES.printPhone(id);
    }
}
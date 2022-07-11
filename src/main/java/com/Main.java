package com;

import com.model.Phone;
import com.model.TV;
import com.repository.PhoneRepository;
import com.repository.TVRepository;
import com.service.OptionalExamples;
import com.service.PhoneService;
import com.service.ProductService;
import com.service.TVService;


public class Main {
    private static final ProductService<Phone> PHONE_SERVICE = new PhoneService(new PhoneRepository());
    private static final ProductService<TV> TV_SERVICE = new TVService(new TVRepository());

    private static final OptionalExamples OPTIONAL_EXAMPLES = new OptionalExamples(new PhoneRepository());

    public static void main(String[] args) {
        TV_SERVICE.createAndSave(2);
        TV_SERVICE.printAll();

        System.out.println("~".repeat(5));

        PHONE_SERVICE.createAndSave(2);
        PHONE_SERVICE.printAll();


        optionalExamples();

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
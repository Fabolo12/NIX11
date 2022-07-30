package com.service;

import com.model.product.Manufacturer;
import com.model.product.Phone;
import com.model.product.Product;
import com.repository.PhoneRepository;

import java.util.Optional;

public class OptionalExamples {
    private final PhoneRepository repository;

    public OptionalExamples(PhoneRepository repository) {
        this.repository = repository;
    }

    public void printIfPresent(String id) {
        repository.findById(id).ifPresent(System.out::println);
    }

    public void printOrGetDefault(String id) {
        final Product phone1 = repository.findById(id)
                .orElse(createAndSavePhone());
        System.out.println(phone1);

        System.out.println("~".repeat(5));

        final Product phone2 = repository.findById("123")
                .orElse(createAndSavePhone());
        System.out.println(phone2);
    }

    public void printOrCreatDefault(String id) {
        final Product phone1 = repository.findById(id)
                .orElseGet(this::createAndSavePhone);
        System.out.println(phone1);

        System.out.println("~".repeat(5));

        final Product phone2 = repository.findById("123")
                .orElseGet(() -> {
                    System.out.println("TODO");
                    return createAndSavePhone();
                });
        System.out.println(phone2);
    }

    public void mapPhoneToString(String id) {
        final String phone1 = repository.findById(id)
                .map(Phone::toString)
                .orElse("Phone not found");
        System.out.println(phone1);

        System.out.println("~".repeat(5));

        final String phone2 = repository.findById("112")
                .map(Phone::toString)
                .orElse("Phone not found");
        System.out.println(phone2);
    }

    public void printOrPrintDefault(String id) {
        repository.findById(id).ifPresentOrElse(
                System.out::println,
                () -> System.out.println(createAndSavePhone())
        );

        System.out.println("~".repeat(5));

        repository.findById("112").ifPresentOrElse(
                System.out::println,
                () -> System.out.println(createAndSavePhone())
        );
    }

    public void checksPhoneLessThen(String id, int count) {
        repository.findById(id)
                .filter(phone -> phone.getCount() <= count)
                .ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println("Phone with count " + count + " not found")
                );
    }

    public void printPhoneOrElseThrowException(String id) {
        final Product phone1 = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Phone with id " + id + " not found"));
        System.out.println(phone1);

        System.out.println("~".repeat(5));

        final Product phone2 = repository.findById("123")
                .orElseThrow(() -> new IllegalArgumentException("Phone with id " + id + " not found"));
        System.out.println(phone2);
    }

    public void printPhone(String id) {
        repository.findById(id).or(() -> Optional.of(createAndSavePhone()))
                .ifPresent(System.out::println);

        System.out.println("~".repeat(5));

        repository.findById("123").or(() -> Optional.of(createAndSavePhone()))
                .ifPresent(System.out::println);
    }

    private Phone createAndSavePhone() {
        return new Phone("Title", 0, 0.0, "Model", Manufacturer.APPLE);
    }
}

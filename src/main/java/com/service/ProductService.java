package com.service;

import com.model.product.Product;
import com.repository.CrudRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public abstract class ProductService<T extends Product> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
    protected static final Random RANDOM = new Random();
    private final CrudRepository<T> repository;

    protected ProductService(CrudRepository<T> repository) {
        this.repository = repository;
    }

    public void createAndSave(int count) {
        if (count < 1) {
            throw new IllegalArgumentException("count must been bigger then 0");
        }
        List<T> products = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            final T phone = creatProduct();
            products.add(phone);
            LOGGER.info("Product {} has been saved", phone.getId());
        }
        repository.saveAll(products);
    }

    protected abstract T creatProduct();

    public void save(T phone) {
        if (phone.getCount() == 0) {
            phone.setCount(-1);
        }
        repository.save(phone);
    }

    public List<T> getAll() {
        return repository.getAll();
    }

    public void printAll() {
        repository.getAll().stream()
                .sorted(Comparator.comparing(Product::getTitle))
                .forEach(System.out::println);
    }
}

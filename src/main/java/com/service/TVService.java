package com.service;

import com.model.product.TV;
import com.repository.TVRepository;

import java.util.ArrayList;

public class TVService extends ProductService<TV> {

    private final TVRepository repository;

    private static TVService instance;

    private TVService(final TVRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public static TVService getInstance() {
        if (instance == null) {
            instance = new TVService(TVRepository.getInstance());
        }
        return instance;
    }
    @Override
    protected TV creatProduct() {
        return new TV(
                TV.class.getSimpleName() + "-" + RANDOM.nextInt(1000),
                RANDOM.nextInt(500),
                RANDOM.nextDouble(1000.0),
                new ArrayList<>());
    }

    public long countDetail(final String detail) {
        return repository.getAll().stream()
                .flatMap(tv -> tv.getDetails().stream())
                .filter(d -> d.equals(detail))
                .count();
    }
}

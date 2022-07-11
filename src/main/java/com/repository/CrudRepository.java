package com.repository;

import com.model.Product;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T extends Product> {
    void save(T phone);

    void saveAll(List<T> phones);

    boolean update(T phone);

    boolean delete(String id);

    List<T> getAll();

    Optional<T> findById(String id);
}

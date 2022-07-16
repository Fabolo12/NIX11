package com.repository;

import com.model.product.TV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class TVRepository implements CrudRepository<TV> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TVRepository.class);
    private final List<TV> tvList;

    public TVRepository() {
        tvList = new LinkedList<>();
    }

    @Override
    public void save(TV phone) {
        if (phone == null) {
            final IllegalArgumentException exception = new IllegalArgumentException("Cannot save a null phone");
            LOGGER.error(exception.getMessage(), exception);
            throw exception;
        } else {
            checkDuplicates(phone);
            tvList.add(phone);
        }
    }

    private void checkDuplicates(TV phone) {
        for (TV p : tvList) {
           if (phone.hashCode() == p.hashCode() && phone.equals(p)) {
               final IllegalArgumentException exception = new IllegalArgumentException("Duplicate phone: " +
                       phone.getId());
               LOGGER.error(exception.getMessage(), exception);
               throw exception;
           }
        }
    }

    @Override
    public void saveAll(List<TV> phones) {
        for (TV phone : phones) {
            save(phone);
        }
    }

    @Override
    public boolean update(TV phone) {
        final Optional<TV> result = findById(phone.getId());
        if (result.isEmpty()) {
            return false;
        }
        final TV originPhone = result.get();
        TVCopy.copy(phone, originPhone);
        return true;
    }

    @Override
    public boolean delete(String id) {
        final Iterator<TV> iterator = tvList.iterator();
        while (iterator.hasNext()) {
            final TV phone = iterator.next();
            if (phone.getId().equals(id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public List<TV> getAll() {
        if (tvList.isEmpty()) {
            return Collections.emptyList();
        }
        return tvList;
    }

    @Override
    public Optional<TV> findById(String id) {
        TV result = null;
        for (TV phone : tvList) {
            if (phone.getId().equals(id)) {
                result = phone;
            }
        }
        return Optional.ofNullable(result);
    }

    private static class TVCopy {
        private static void copy(final TV from, final TV to) {
            to.setCount(from.getCount());
            to.setPrice(from.getPrice());
            to.setTitle(from.getTitle());
        }
    }
}

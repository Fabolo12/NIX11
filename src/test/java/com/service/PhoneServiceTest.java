package com.service;

import com.model.product.Manufacturer;
import com.model.product.Phone;
import com.repository.PhoneRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Optional;

class PhoneServiceTest {

    private PhoneService target;
    private PhoneRepository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(PhoneRepository.class);
        target = new PhoneService(repository);
    }

    @Test
    void createAndSavePhones_negativeCount() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->  target.createAndSave(-1));
    }

    @Test
    void createAndSavePhones_zeroCount() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->  target.createAndSave(0));
    }

    @Test
    void createAndSavePhones() {
        target.createAndSave(2);
        Mockito.verify(repository).saveAll(Mockito.anyList());
    }

    @Test
    void getAll() {
        target.getAll();
        Mockito.verify(repository).getAll();
    }

    @Test
    void printAll() {
        target.printAll();
        Mockito.verify(repository).getAll();
    }

    @Test
    void savePhone() {
        final Phone phone = creatSimplePhone();
        target.save(phone);

        ArgumentCaptor<Phone> argument = ArgumentCaptor.forClass(Phone.class);
        Mockito.verify(repository).save(argument.capture());
        Assertions.assertEquals("Title", argument.getValue().getTitle());
    }

    @Test
    void savePhone_zeroCount() {
        final Phone phone = creatSimplePhone();
        phone.setCount(0);
        target.save(phone);

        ArgumentCaptor<Phone> argument = ArgumentCaptor.forClass(Phone.class);
        Mockito.verify(repository).save(argument.capture());
        Assertions.assertEquals("Title", argument.getValue().getTitle());
        Assertions.assertEquals(-1, argument.getValue().getCount());
    }

    @Test
    void getTotalPriceFor_findOne() {
        // configuration
        final Phone phone = creatSimplePhone();
        Mockito.when(repository.findById("")).thenReturn(Optional.of(phone));
        final int expected = (int) (phone.getCount() * phone.getPrice());

        // call test method
        final int actual = target.getTotalPriceFor("");

        // checks
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getTotalPriceFor_notFind() {
        // configuration
        Mockito.when(repository.findById("")).thenReturn(Optional.empty());
        final int expected = -1;

        // call test method
        final int actual = target.getTotalPriceFor("");

        // checks
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void createAndSave_oneTime() {
        // configuration

        // call test method
        target.createAndSave(1);

        // checks
        Mockito.verify(repository).saveAll(Mockito.anyList());
    }

    @Test
    void createAndSave_manyTime() {
        // configuration

        // call test method
        target.createAndSave(5);

        // checks
        Mockito.verify(repository, Mockito.atLeastOnce()).saveAll(Mockito.anyList());
    }

    @Test
    void getOrCreat_findOne() {
        // configuration
        final Phone expected = creatSimplePhone();
        Mockito.when(repository.findById("")).thenReturn(Optional.of(expected));
        Mockito.doThrow(RuntimeException.class)
                .when(repository)
                .save(Mockito.any(Phone.class));

        // call test method
        final Phone actual = target.getOrCreat("");

        // checks
        Assertions.assertEquals(expected.getId(), actual.getId());
    }


    private Phone creatSimplePhone() {
        return new Phone("Title", 10, 1000.0, "Model", Manufacturer.APPLE);
    }
}
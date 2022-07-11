package com.service;

import com.model.Manufacturer;
import com.model.Phone;
import com.repository.CrudRepository;

public class PhoneService extends ProductService<Phone> {

    public PhoneService(CrudRepository<Phone> repository) {
        super(repository);
    }

    @Override
    protected Phone creatProduct() {
        return new Phone(
                "Title-" + RANDOM.nextInt(1000),
                RANDOM.nextInt(500),
                RANDOM.nextDouble(1000.0),
                "Model-" + RANDOM.nextInt(10),
                getRandomManufacturer()
        );
    }

    private Manufacturer getRandomManufacturer() {
        final Manufacturer[] values = Manufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }
}

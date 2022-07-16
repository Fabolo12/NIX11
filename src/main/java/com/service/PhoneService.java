package com.service;

import com.model.product.Manufacturer;
import com.model.product.Phone;
import com.repository.CrudRepository;

import java.util.concurrent.atomic.AtomicInteger;

public class PhoneService extends ProductService<Phone> {

    public PhoneService(CrudRepository<Phone> repository) {
        super(repository);
    }

    @Override
    protected Phone creatProduct() {
        return new Phone(
                Phone.class.getSimpleName() + "-" + RANDOM.nextInt(1000),
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

    public int getTotalPriceFor(final String id) {
        final AtomicInteger totalPrice = new AtomicInteger(0);
        repository.findById(id).ifPresentOrElse(
                phone -> totalPrice.set((int) (phone.getCount() * phone.getPrice())),
                () -> totalPrice.set(-1)
        );
        return totalPrice.get();
    }

    public Phone getOrCreat(final String id) {
        return repository.findById(id).orElseGet(() -> {
            final Phone phone = creatProduct();
            repository.save(phone);
            return phone;
        });
    }
}

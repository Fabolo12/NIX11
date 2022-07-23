package com.service;

import com.model.product.Manufacturer;
import com.model.product.Phone;
import com.model.product.Product;
import com.repository.PhoneRepository;

import java.util.concurrent.atomic.AtomicInteger;

public class PhoneService extends ProductService<Phone> {

    private final PhoneRepository repository;

    private static PhoneService instance;

    private PhoneService(final PhoneRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public static PhoneService getInstance() {
        if (instance == null) {
            instance = new PhoneService(PhoneRepository.getInstance());
        }
        return instance;
    }

    public static PhoneService getInstance(final PhoneRepository repository) {
        if (instance == null) {
            instance = new PhoneService(repository);
        }
        return instance;
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

    public double getTotalPriceForModel(final String model) {
        double totalPrice = 0;
        for (Phone phone : repository.findByModel(model)) {
            totalPrice += phone.getPrice();
        }
        return totalPrice;
    }

    public double getTotalPriceForModelStream(final String model) {
        return repository.getAll().stream()
                .filter(phone -> phone.getModel().equals(model))
                .mapToDouble(Product::getPrice)
                .sum();
    }
}

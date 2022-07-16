package com.model;

import com.model.product.Product;

import java.util.UUID;

public class Order {

    private final String id;
    private Product[] products;

    private int size;

    private double totalPrice;

    private static final String EXCEPTION_MESSAGE = "Index out of range: ";

    public Order() {
        this(10);
    }

    public Order(int capacity) {
        products = new Product[capacity];
        id = UUID.randomUUID().toString();
        size = 0;
        totalPrice = 0.0;
    }

    public void add(Product product) {
        for (int i = 0; i < products.length; i++) {
            if (products[i] == null) {
                tryAddNewValue(i, product);
                return;
            }
        }
    }

    public void add(int index, Product product) {
        if (index < 0) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE + index);
        }
        if (index >= products.length) {
            index = products.length;
            tryAddNewValue(index, product);
        } else {
            if (products[index] == null) {
                products[index] = product;
            } else {
                tryAddNewValue(index, product);
            }
        }
    }

    public void set(int index, Product product) {
        if (index < 0) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE + index);
        }
        if (index >= products.length) {
            index = products.length;
        }
        trySetNewValue(index, product);
    }

    public Product get(final int index) {
        if (index < 0 || index >= products.length) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE + index);
        }
        return products[index];
    }

    public int indexOf(final Product product) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].equals(product)) {
                return i;
            }
        }
        return -1;
    }

    public void addAll(final Product[] newProducts) {
        for (int i = 0; i < products.length; i++) {
            if (products[i] == null) {
                addInnerArray(i, newProducts);
                return;
            }
        }

        addInnerArray(products.length, newProducts);
    }

    private void addInnerArray(int i, Product[] newProducts) {
        for (int j = 0; j < newProducts.length; i++, j++) {
            tryAddNewValue(i, newProducts[j]);
        }
    }

    public void clear() {
        products = new Product[10];
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void trySetNewValue(int index, Product product) {
        if (index < 0) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE + index);
        }

        if (index >= products.length) {
            increaseProductsArray();
        }

        totalPrice -= products[index].getPrice();
        products[index] = product;
        totalPrice += product.getPrice();
    }

    private void tryAddNewValue(int index, Product product) {
        if (index < 0) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE + index);
        }

        if (index + size > products.length) {
            increaseProductsArray();
        }

        if (products[index] != null) {
            final int tail = products.length - index - 1;
            System.arraycopy(products, index, products, index + 1, tail);
        }

        products[index] = product;
        size++;
        totalPrice += product.getPrice();
    }
    private void increaseProductsArray() {
        final int length = products.length;
        final int newLength = length + length / 2 + 1;
        final Product[] newProducts = new Product[newLength];
        System.arraycopy(products, 0, newProducts, 0, length);
        products = newProducts;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Order ").append(id).append("\n");
        for (Product product : products) {
            if (product == null) {
                continue;
            }
            builder.append("Product ").append(product.getTitle()).append(" ");
            builder.append("count ").append(product.getCount()).append(" ");
            builder.append(String.format("price %.2f", product.getPrice())).append("\n");
        }
        builder.append(String.format("\tTotal price %.2f", totalPrice));
        return builder.toString();
    }

    public Product remove(int index) {
        if (index < 0 || index >= products.length) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE + index);
        }
        final int length = products.length;
        final int newLength = length - 1;
        final Product[] newProducts = new Product[newLength];

        final Product product = products[index];
        System.arraycopy(products, 0, newProducts, 0, index);
        if (index < newLength) {
            final int tail = newLength - index;
            System.arraycopy(products, index + 1, newProducts, index, tail);
        }
        products = newProducts;
        totalPrice -= product.getPrice();
        size -= 1;
        return product;
    }
}

package com.service;

import com.model.Order;
import com.model.product.Product;

import java.util.List;

public class OrderService {
    public Order creatOrder(List<? extends Product> products) {
        final Order order = new Order(products.size());
        order.addAll(products.toArray(new Product[0]));
        return order;
    }

    public void addProducts(Order order, List<? extends Product> products) {
        order.addAll(products.toArray(new Product[0]));
    }

    public void setProduct(Order order, int index, Product product) {
        order.set(index, product);
    }

    public Product remove(Order order, int index) {
        return order.remove(index);
    }

    public void addProduct(Order order, int index, Product product) {
        order.add(index, product);
    }
}

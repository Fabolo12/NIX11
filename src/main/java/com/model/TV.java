package com.model;

public class TV extends Product {
    public TV(String title, int count, double price) {
        super(title, count, price, ProductType.TV);
    }
}

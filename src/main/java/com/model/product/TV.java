package com.model.product;

import java.util.Comparator;

public class TV extends Product {
    public TV(String title, int count, double price) {
        super(title, count, price, ProductType.TV);
    }

    public static class TVComparator implements Comparator<TV> {
        @Override
        public int compare(TV o1, TV o2) {
            return o1.getId().compareTo(o2.getId());
        }
    }

    @Override
    public String toString() {
        return "TV{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", type=" + type +
                '}';
    }
}

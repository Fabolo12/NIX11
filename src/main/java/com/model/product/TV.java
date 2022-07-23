package com.model.product;

import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;

@Getter
@Setter
public class TV extends Product {

    private List<String> details;
    public TV(String title, int count, double price, List<String> details) {
        super(title, count, price, ProductType.TV);
        this.details = details;
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

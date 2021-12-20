package com.example.TestProject.model;

import java.util.Objects;

public class Coupon {
    private long id;
    private double price;

    public Coupon(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coupon coupon = (Coupon) o;
        return id == coupon.id &&
                Double.compare(coupon.price, price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price);
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", price=" + price +
                '}';
    }
}

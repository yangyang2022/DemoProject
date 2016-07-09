package com.yangyang.spel;

public class Car {
    private String brand;
    private double price;
    private double tyrePrimeter;

    public double getTyrePrimeter() {
        return tyrePrimeter;
    }

    public void setTyrePrimeter(double tyrePrimeter) {
        this.tyrePrimeter = tyrePrimeter;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", price=" + price +
                ", tyrePrimeter=" + tyrePrimeter +
                '}';
    }
}

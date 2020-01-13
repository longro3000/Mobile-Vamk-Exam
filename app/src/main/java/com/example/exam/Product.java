package com.example.exam;

import androidx.annotation.NonNull;

public class Product {
    String name, unit, imageSource = "N/A";
    double price;
    int amount=0;
    double totalPrice;

    public Product(String name, String unit, String imageSource, double price) {
        this.name = name;
        this.unit = unit;
        this.imageSource = imageSource;
        this.price = price;
    }

    public void addAmount(int amount) {
        this.amount = amount;
    }

    @NonNull
    @Override
    public String toString() {
        return "name: " + this.name + ", image: " + this.imageSource + ", price: " + this.price + ", unit: " + this.unit + ", amount: " + this.amount + ", total price: " + this.totalPrice;
    }

    public String toStringWithoutAmount() {
        return "name: " + this.name + ", image: " + this.imageSource + ", price: " + this.price + ", unit: " + this.unit;
    }


}

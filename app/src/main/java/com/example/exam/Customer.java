package com.example.exam;

import androidx.annotation.NonNull;

public class Customer {
    String firstName, lastName, email, phoneNumber, address;

    public Customer(String firstName, String lastName, String email, String phoneNumber, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    @NonNull
    @Override
    public String toString() {
        return "first name: " + this.firstName + ", last name: " + this.lastName + ", email: " + this.email + ", phone number: " + this.phoneNumber + ", address: " + this.address;
    }
}

package com.pluralsight.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public class Transaction {

    // Properties (fields) of the Transaction class
    private String vendor;
    private double amount;
    private String description;
    private LocalDateTime createdDatetime;

    // Constructor to initialize Transaction object
    public Transaction() {
    }

    public Transaction(String vendor, double amount, String description) {
        this.vendor = vendor;
        this.amount = amount;
        this.createdDatetime = LocalDateTime.now();
        this.description = description;

    }

    // Getter and setter methods for each property

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedDatetime() {
        return createdDatetime;
    }

    @Override
    public String toString() {
        return String.format(
                "Transaction {%n" +
                        "    vendor: '%s',%n" +
                        "    amount: %.2f,%n" +
                        "    description: '%s',%n" +
                        "    createdDateTime: %s%n" +
                        "}",
                vendor, amount, description, createdDatetime
        );
    }
}

package com.pluralsight.model;

import com.pluralsight.utils.InputUtil;

import java.time.LocalDateTime;


public class Transaction {



    // Properties (fields) of the Transaction class
    private LocalDateTime createdDateTime;
    private String description;
    private String vendor;
    private double amount;


    // Constructor to initialize Transaction object
    public Transaction() {
    }

    public Transaction(LocalDateTime createdDateTime,String description, String vendor, double amount) {
        this.createdDateTime = createdDateTime;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;

    }

    // Getter and setter methods for each property


    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    //  Override the toString method to display the Transaction
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Transaction {")
                .append("\n    createdDateTime: ").append(createdDateTime.format(InputUtil.dateTimeFormatter))
                .append("\n    vendor: '").append(vendor).append('\'')
                .append("\n    amount: ").append(String.format("%.2f", amount))
                .append("\n    description: '").append(description).append('\'')
                .append("\n}");
        return sb.toString();
    }

}

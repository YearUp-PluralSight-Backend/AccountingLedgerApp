package com.pluralsight.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class Transaction {

    // Properties (fields) of the Transaction class
    private UUID id;
    private String vendor;
    private double amount;
    private String description;
    private LocalDate createDate;
    private LocalTime createTime;
    private LocalDate updatedDate;
    private LocalTime updatedTime;
    private static int numberOfTransactions;

    // Constructor to initialize Transaction object
    public Transaction() {
    }

    public Transaction(String vendor, double amount, String description, LocalDate updatedDate, LocalTime updatedTime) {
        this.id = UUID.randomUUID();
        this.vendor = vendor;
        this.amount = amount;
        this.description = description;
        this.createDate = LocalDate.now();
        this.createTime = LocalTime.now();
        this.updatedDate = updatedDate;
        this.updatedTime = updatedTime;
        numberOfTransactions++;

    }

    // Getter and setter methods for each property
    public UUID getId() {
        return id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfTransactions() {
        return numberOfTransactions;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public LocalTime getCreateTime() {
        return createTime;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public LocalTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Transaction {")
                .append("\n    id: ").append(id)
                .append("\n    vendor: '").append(vendor).append('\'')
                .append("\n    amount: ").append(amount)
                .append("\n    description: '").append(description).append('\'')
                .append("\n    createDate: ").append(createDate)
                .append("\n    createTime: ").append(createTime)
                .append("\n    updatedDate: ").append(updatedDate)
                .append("\n    updatedTime: ").append(updatedTime)
                .append("\n}");
        return sb.toString();
    }
}

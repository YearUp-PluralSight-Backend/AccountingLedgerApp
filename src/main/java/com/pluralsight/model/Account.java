package com.pluralsight.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Account {

    // Properties (fields) of the Account class
    private UUID id;
    private double balance;
    private String accountType;
    private boolean isActive;
    private List<Transaction> transactionList;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    // Constructor to initialize Account object
    public Account() {
    }

    public Account(String accountType, double balance, List<Transaction> transactionList,  boolean isActive, LocalDateTime createdTime, LocalDateTime updatedTime) {
        this.id = UUID.randomUUID();
        this.accountType = accountType;
        this.balance = balance;
        this.transactionList = transactionList;
        this.isActive = isActive;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    // Getter and setter methods for each property
    public UUID getId() {
        return id;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }


    // Override the toString method to display the account information

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Account {")
                .append("\n    id: ").append(id)
                .append("\n    balance: ").append(balance)
                .append("\n    accountType: '").append(accountType).append('\'')
                .append("\n    isActive: ").append(isActive)
                .append("\n    createdTime: ").append(createdTime)
                .append("\n    updatedTime: ").append(updatedTime)
                .append("\n    transactionList: \s\s").append(new ArrayList<>(transactionList))
                .append("\n}");
        return sb.toString();
    }

}

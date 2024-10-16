package com.pluralsight.model;

import com.pluralsight.utils.InputUtil;

import java.time.LocalDateTime;

/**
 * The Transaction class represents a financial transaction with details such as
 * creation date, description, vendor, and amount.
 */
public class Transaction {

    // Properties (fields) of the Transaction class
    private LocalDateTime createdDateTime;
    private String description;
    private String vendor;
    private double amount;

    /**
     * Default constructor to initialize a Transaction object with default values.
     */
    public Transaction() {
    }

    /**
     * Constructor to initialize a Transaction object with specified values.
     *
     * @param createdDateTime The date and time when the transaction was created
     * @param description A brief description of the transaction
     * @param vendor The vendor associated with the transaction
     * @param amount The amount of the transaction
     */
    public Transaction(LocalDateTime createdDateTime, String description, String vendor, double amount) {
        this.createdDateTime = createdDateTime;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    /**
     * Gets the creation date and time of the transaction.
     *
     * @return The creation date and time
     */
    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    /**
     * Gets the description of the transaction.
     *
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the transaction.
     *
     * @param description The new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the vendor associated with the transaction.
     *
     * @return The vendor
     */
    public String getVendor() {
        return vendor;
    }

    /**
     * Sets the vendor associated with the transaction.
     *
     * @param vendor The new vendor
     */
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    /**
     * Gets the amount of the transaction.
     *
     * @return The amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the amount of the transaction.
     *
     * @param amount The new amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Returns a string representation of the Transaction object.
     *
     * @return A string representation of the Transaction
     */
    @Override
    public String toString() {
        return """
               |%s|%s|%s|%.2f|
               """.formatted(createdDateTime.format(InputUtil.dateTimeFormatter), description, vendor, amount);

//        StringBuilder sb = new StringBuilder();
//        sb.append("")
//                .append("\s\s\s|\s\s\s").append(createdDateTime.format(InputUtil.dateTimeFormatter))
//                .append("\s\s\s|\s\s\s").append(vendor).append('\'')
//                .append("\s\s\s|\s\s\s").append(String.format("%.2f", amount))
//                .append("\s\s\s|\s\s\s").append(description).append('\'')
//                .append("\s\s\s|\s\s\s");
//        return sb.toString();
    }
}
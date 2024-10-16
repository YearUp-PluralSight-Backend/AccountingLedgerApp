package com.pluralsight.Repository;

import com.pluralsight.model.Transaction;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface for generating and handling transaction reports.
 */
public interface ReportOperations {



    /**
     * Sets up the transaction data.
     *
     * @param transactionList the list of all transactions
     * @param transactionListWithDeposit the list of deposit transactions
     * @param transactionListWithPayment the list of payment transactions
     */
    void setUpData(List<Transaction> transactionList, List<Transaction> transactionListWithDeposit, List<Transaction> transactionListWithPayment);

    /**
     * Displays the report screen menu.
     */
    void reportScreenMenu();

    /**
     * Displays the report screen and handles user input for different report options.
     */
    void reportScreen();

    /**
     * Generates a report for the month-to-date transactions.
     */
    void generateMonthToDateReport();

    /**
     * Generates a report for the previous month's transactions.
     */
    void generatePreviousMonthReport();

    /**
     * Generates a report for the year-to-date transactions.
     */
    void generateYearToDateReport();

    /**
     * Generates a report for the previous year's transactions.
     */
    void generatePreviousYearReport();

    /**
     * Searches and prints transactions by the vendor name.
     *
     * @param vendorName the name of the vendor
     */
    void searchByVendorName(String vendorName);

    /**
     * Performs a custom search on transactions based on various properties.
     *
     * @param startDate the start date of the search range
     * @param endDate the end date of the search range
     * @param description the transaction description
     * @param vendorName the name of the vendor
     * @param amount the transaction amount
     * @throws InterruptedException if the search is interrupted
     */
    void customSearch(LocalDate startDate, LocalDate endDate, String description, String vendorName, double amount) throws InterruptedException;
}


package com.pluralsight.Repository;

import com.pluralsight.model.Transaction;
import com.pluralsight.utils.InputUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Interface for {@code Ledger} class to implement the functions, such as makeDeposit, makePayment, DisplayEntries and more.
 */
public abstract class LedgerOperations {

    // Current balance of the ledger
    protected double balance;
    // Date and time when the ledger was created
    protected LocalDateTime createdDateTime;
    // The Database or CSV file name
    protected static final String FILENAME = "inventory.csv";
    // For logging
    protected static Logger logger = LoggerFactory.getLogger(LedgerOperations.class);
    // List of all transactions
    protected List<Transaction> transactionList = LedgerOperations.getInventory();
    // List of deposit transactions
    protected List<Transaction> transactionListWithDeposit = new ArrayList<>();
    // List of payment transactions
    protected List<Transaction> transactionListWithPayment = new ArrayList<>();

    /**
     * Displays the ledger screen menu options to the user.
     */
    public abstract void ledgerScreenMenu();

    /**
     * Displays the ledger screen and processes user commands.
     * The loop continues until the user chooses to go back to the home screen.
     */
    public abstract void ledgerScreen();

    /**
     * Adds a deposit transaction to the ledger.
     *
     * @throws InterruptedException If the thread is interrupted
     */
    public abstract void addDeposit() throws InterruptedException;

    /**
     * Makes a payment transaction and adds it to the ledger.
     *
     * @throws InterruptedException If the thread is interrupted
     */
    public abstract void makePayment() throws InterruptedException;

    /**
     * Displays all payment transactions.
     *
     * @throws InterruptedException If the thread is interrupted
     */
    public abstract void displayPaymentEntries() throws InterruptedException;

    /**
     * Displays all deposit transactions.
     *
     * @throws InterruptedException If the thread is interrupted
     */
    public abstract void displayDepositEntries() throws InterruptedException;

    /**
     * Displays all transactions.
     *
     * @throws InterruptedException If the thread is interrupted
     */
    public abstract void displayAllEntries() throws InterruptedException;

    /**
     * Initializes the data by categorizing transactions into deposits and payments.
     *
     * @param transactions The list of transactions to initialize
     */
    protected abstract void initializingData(List<Transaction> transactions);

    /**
     * Checks if a number is negative.
     *
     * @param number The number to check
     * @return True if the number is negative, false otherwise
     */
    public abstract boolean isNegative(double number);

    /**
     * Reads the file `inventory.csv`, gets the content and splits them by `|`.
     * Creates a {@code Transaction} to store the data and adds the {@code Transaction} to the inventory list.
     *
     * @return The list of transactions read from the file
     */
    protected static List<Transaction> getInventory() {
        List<Transaction> inventory = new ArrayList<>(); // create a list to store the transactions

        // read the file and add the content to the inventory list. (try-catch resource release)
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            logger.info("Reading the csv file {}", FILENAME);
            bufferedReader.readLine(); // skip the first line
            while ((line = bufferedReader.readLine()) != null) {
                if (line.isBlank() || line.isEmpty()) continue;
                String[] lineContent = line.split("\\|");

                // Parse the line content and create a Transaction object
                LocalDateTime createdTime = LocalDateTime.parse((lineContent[0] + " " + lineContent[1]), InputUtil.dateTimeFormatter);
                inventory.add(new Transaction(createdTime, lineContent[2], lineContent[3], Double.parseDouble(lineContent[4])));
            }
        } catch (IOException e) {
            logger.error("Failed to read from file " + FILENAME, e);
            e.printStackTrace();
        }
        logger.info("Finished reading the file {}", FILENAME);
        logger.info("Loaded {} transactions from {}", inventory.size(), FILENAME);
        return inventory;
    }

    /**
     * Reads the list and overwrites the CSV file.
     *
     * @param transactionList The list of transactions to write to the file
     */
    protected static void updateCSVFile(List<Transaction> transactionList) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("inventory.csv"))) {
            logger.info("Updating the file {}", FILENAME);

            // Write the header of the content
            StringBuilder header = new StringBuilder();
            bufferedWriter.write(header
                    .append("|")
                    .append("Date")
                    .append("|")
                    .append("Time")
                    .append("|")
                    .append("Description")
                    .append("|")
                    .append("VendorName")
                    .append("|")
                    .append("Amount")
                    .toString()
            );
            for (Transaction transaction : transactionList) {
                if (transaction == null) continue;
                // Write the contents
                StringBuilder content = new StringBuilder();
                String line = content
                        .append("\n")
                        .append(transaction.getCreatedDateTime().toLocalDate())
                        .append("|")
                        .append(transaction.getCreatedDateTime().toLocalTime())
                        .append("|")
                        .append(transaction.getDescription())
                        .append("|")
                        .append(transaction.getVendor())
                        .append("|")
                        .append(transaction.getAmount())
                        .toString();
                bufferedWriter.write(line);
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            logger.error("Failed to update the file {}", FILENAME, e);
            e.printStackTrace();
        }
        logger.info("Successfully updated the file {}", FILENAME);
    }
}
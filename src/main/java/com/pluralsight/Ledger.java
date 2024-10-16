package com.pluralsight;

import com.pluralsight.model.Transaction;
import com.pluralsight.Repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.pluralsight.utils.InputUtil.*;

/**
 * The Ledger class represents the accounting ledger which maintains a list of transactions,
 * balance, and provides methods to manipulate and display these transactions.
 */
public class Ledger implements LedgerOperations {

    // Logger instance for logging information
    private static final Logger logger = LoggerFactory.getLogger(Ledger.class);
    // Singleton instance of the Report class
    private static final Report report = Report.getInstance();
    // Singleton instance of the Ledger class
    private static Ledger ledger;
    // Unique identifier for the ledger
    private UUID id;
    // Current balance of the ledger
    private double balance;
    // List of all transactions
    private List<Transaction> transactionList = LedgerOperations.getInventory();
    // List of deposit transactions
    private List<Transaction> transactionListWithDeposit = new ArrayList<>();
    // List of payment transactions
    private List<Transaction> transactionListWithPayment = new ArrayList<>();
    // Date and time when the ledger was created
    private LocalDateTime createdDateTime;

    /**
     * Default constructor to initialize the Ledger object and populate initial data.
     */
    public Ledger() {
        initializingData(transactionList);
    }

    /**
     * Returns the singleton instance of the Ledger class.
     * If the instance is null, it initializes it.
     *
     * @return The singleton instance of Ledger
     */
    public static Ledger getInstance() {
        if (ledger == null) {
            ledger = new Ledger();
        }
        return ledger;
    }

    /**
     * Constructor to initialize a Ledger object with specified balance and transaction list.
     *
     * @param balance The initial balance of the ledger
     * @param transactionList The list of transactions
     */
    public Ledger(double balance, List<Transaction> transactionList) {
        this.id = UUID.randomUUID();
        this.balance = balance;
        this.createdDateTime = LocalDateTime.now();
    }

    // Getter and setter methods for each property

    /**
     * Gets the unique identifier of the ledger.
     *
     * @return The unique identifier
     */
    public UUID getId() {
        return id;
    }

    /**
     * Gets the current balance of the ledger.
     *
     * @return The current balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the current balance of the ledger.
     *
     * @param balance The new balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Gets the list of all transactions.
     *
     * @return The list of all transactions
     */
    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    /**
     * Sets the list of all transactions.
     *
     * @param transactionList The new list of transactions
     */
    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    /**
     * Gets the list of deposit transactions.
     *
     * @return The list of deposit transactions
     */
    public List<Transaction> getTransactionListWithDeposit() {
        return transactionListWithDeposit;
    }

    /**
     * Sets the list of deposit transactions.
     *
     * @param transactionListWithDeposit The new list of deposit transactions
     */
    public void setTransactionListWithDeposit(List<Transaction> transactionListWithDeposit) {
        this.transactionListWithDeposit = transactionListWithDeposit;
    }

    /**
     * Gets the list of payment transactions.
     *
     * @return The list of payment transactions
     */
    public List<Transaction> getTransactionListWithPayment() {
        return transactionListWithPayment;
    }

    /**
     * Sets the list of payment transactions.
     *
     * @param transactionListWithPayment The new list of payment transactions
     */
    public void setTransactionListWithPayment(List<Transaction> transactionListWithPayment) {
        this.transactionListWithPayment = transactionListWithPayment;
    }

    /**
     * Gets the creation date and time of the ledger.
     *
     * @return The creation date and time
     */
    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    /**
     * Returns a string representation of the Ledger object.
     *
     * @return A string representation of the Ledger
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ledger {")
                .append("\n    id: ").append(id)
                .append("\n    balance: ").append(balance)
                .append("\n    transactionList: ").append(transactionList)
                .append("\n    transactionListWithDeposit: ").append(transactionListWithDeposit)
                .append("\n    transactionListWithPayment: ").append(transactionListWithPayment)
                .append("\n}");
        return sb.toString();
    }

    /**
     * Displays the ledger screen menu options to the user.
     */
    @Override
    public void ledgerScreenMenu() {
        String options =
                """
                =========================================================================
                =====          Welcome To Accounting Ledger Application             =====
                =====          Option: (All)  Display All Entries                   =====
                =====          Option  (D)    Display Only Deposit Entries          =====
                =====          Option  (P)    Display Only Payment Entries          =====
                =====          Option  (R)    Report Screen                         =====
                =====          Option  (H)    Home   Screen                         =====
                =========================================================================
                """;
        System.out.println(options);
    }

    /**
     * Displays the ledger screen and processes user commands.
     * The loop continues until the user chooses to go back to the home screen.
     */
    @Override
    public void ledgerScreen() {
        while (true) {
            try {
                ledgerScreenMenu();
                String command = promptForString(" (LedgerScreen) Enter your Option: ").toUpperCase();
                switch (command) {
                    case "ALL" -> ledger.displayAllEntries();                                                       // Display all entries
                    case "D" -> ledger.displayDepositEntries();                                                     // Display deposit entries
                    case "P" -> ledger.displayPaymentEntries();                                                     // Display payment entries
                    case "R" -> {
                        report.setUpData(transactionList, transactionListWithDeposit, transactionListWithPayment);  // Setup the data for report screen
                        report.reportScreen();                                                                      // Display report screen
                    }
                    case "H" -> { return; }                                                                         // Go back to the home screen
                    default -> System.out.println("Invalid Option. Please choose between (ALL, D, P, R, H)\n");
                }
            } catch (Exception e) {
                logger.info("Wrong command or Option!");
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Adds a deposit transaction to the ledger.
     *
     * @throws InterruptedException If the thread is interrupted
     */
    @Override
    public void addDeposit() throws InterruptedException {
        Transaction transaction = userInput();
        if (transaction == null) {
            Thread.sleep(1000);
            return;
        }
        transaction.setAmount(transaction.getAmount() * -1);
        transactionList.add(transaction);
        transactionListWithDeposit.add(transaction);
        LedgerOperations.updateCSVFile(transactionList);
        Thread.sleep(1000);
    }

    /**
     * Makes a payment transaction and adds it to the ledger.
     *
     * @throws InterruptedException If the thread is interrupted
     */
    @Override
    public void makePayment() throws InterruptedException {
        Transaction transaction = userInput();
        if (transaction == null) {
            Thread.sleep(1000);
            return;
        }
        transactionList.add(transaction);
        transactionListWithPayment.add(transaction);
        LedgerOperations.updateCSVFile(transactionList);
        Thread.sleep(1000);
    }

    /**
     * Displays all payment transactions.
     *
     * @throws InterruptedException If the thread is interrupted
     */
    @Override
    public void displayPaymentEntries() throws InterruptedException {
        transactionListWithPayment.stream().forEach(System.out::println);
        Thread.sleep(2000);
    }

    /**
     * Displays all deposit transactions.
     *
     * @throws InterruptedException If the thread is interrupted
     */
    @Override
    public void displayDepositEntries() throws InterruptedException {
        transactionListWithDeposit.stream().forEach(System.out::println);
        Thread.sleep(2000);
    }

    /**
     * Displays all transactions.
     *
     * @throws InterruptedException If the thread is interrupted
     */
    @Override
    public void displayAllEntries() throws InterruptedException {
        transactionList.stream().forEach(System.out::println);
        Thread.sleep(2000);
    }

    /**
     * Initializes the data by categorizing transactions into deposits and payments.
     *
     * @param transactions The list of transactions to initialize
     */
    @Override
    public void initializingData(List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            if (isNegative(transaction.getAmount())) {
                transactionListWithDeposit.add(transaction);
            } else {
                transactionListWithPayment.add(transaction);
            }
        }
    }

    /**
     * Checks if a number is negative.
     *
     * @param number The number to check
     * @return True if the number is negative, false otherwise
     */
    @Override
    public boolean isNegative(double number) {
        return number < 0;
    }
}
package com.pluralsight;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.pluralsight.utils.InputUtil.*;

public class Ledger implements Repository{

    // Properties (fields) of the Account class
    private static final Logger logger = LoggerFactory.getLogger(Ledger.class);
    private static final Report report = Report.getInstance();
    private static Ledger ledger;
    private UUID id;
    private double balance;
    private List<Transaction> transactionList = getInventory();
    private List<Transaction> transactionListWithDeposit = new ArrayList<>();
    private List<Transaction> transactionListWithPayment = new ArrayList<>();
    private LocalDateTime createdDateTime;


    // Constructor to initialize Account object
    public Ledger() {
        initializingData(transactionList);
    }

    public static Ledger getInstance() {
        if (ledger == null) {
           ledger = new Ledger();
        }
        return ledger;
    }

    public Ledger(double balance, List<Transaction> transactionList) {
        this.id = UUID.randomUUID();
        this.balance = balance;
        this.createdDateTime = LocalDateTime.now();
    }

    // Getter and setter methods for each property


    public UUID getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public List<Transaction> getTransactionListWithDeposit() {
        return transactionListWithDeposit;
    }

    public void setTransactionListWithDeposit(List<Transaction> transactionListWithDeposit) {
        this.transactionListWithDeposit = transactionListWithDeposit;
    }

    public List<Transaction> getTransactionListWithPayment() {
        return transactionListWithPayment;
    }

    public void setTransactionListWithPayment(List<Transaction> transactionListWithPayment) {
        this.transactionListWithPayment = transactionListWithPayment;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    // Override the toString method to display the account information
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ledger {")
                .append("\n    id: ").append(id)
                .append("\n    balance: ").append(balance)
                .append("\n    transactionList: \s\s").append(transactionList)
                .append("\n    transactionList: \s\s").append(transactionListWithDeposit)
                .append("\n    transactionList: \s\s").append(transactionListWithPayment)
                .append("\n}");
        return sb.toString();
    }

    public static void ledgerScreenMenu() {
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

    public void ledgerScreen() {
        while(true) {
            try {
                ledgerScreenMenu();
                String command = promptForString(" (LedgerScreen) Enter your Option: ").toUpperCase();
                switch (command) {
                    case "ALL" -> ledger.displayAllEntries();                                                       // Display all entries
                    case "D"   ->   ledger.displayDepositEntries();                                                 // Display deposit entries
                    case "P"   ->   ledger.displayPaymentEntries();                                                 // Display payment entries
                    case "R"   -> {
                        report.setUpData(transactionList, transactionListWithDeposit, transactionListWithPayment);  // Setup the data for report screen
                        report.reportScreen();                                                                      // Display report screen
                    }
                    case "H"   -> {return;}                                                                         // Go back to the home screen
                    default -> System.out.println("Invalid Option. Please choose between (ALL, D, P, R, H)\n");
                }
            } catch (Exception e) {
                logger.info("Wrong command or Option!");
                throw new RuntimeException(e);
            }
        }

    }

    // add deposit
    public void addDeposit() throws InterruptedException {

        // 2023-04-15 10:13:25
        Transaction transaction = userInput();
        if (transaction == null)  {
            Thread.sleep(1000);
        };
        transaction.setAmount(transaction.getAmount() * -1);
        transactionList.add(transaction);
        transactionListWithDeposit.add(transaction);
        updateCSVFile(transactionList);
        Thread.sleep(1000);

    }

    public void makePayment() throws InterruptedException {

        // 2023-04-15 10:13:25
        Transaction transaction = userInput();
        if (transaction == null)  {
            Thread.sleep(1000);
        };
        transactionList.add(transaction);
        transactionListWithPayment.add(transaction);
        updateCSVFile(transactionList);
        Thread.sleep(1000);

    }

    private void displayPaymentEntries() throws InterruptedException {

        for (Transaction transaction: transactionListWithPayment) {
            System.out.println(transaction);
        }
        Thread.sleep(2000);

    }

    private void displayDepositEntries() throws InterruptedException {

        for (Transaction transaction: transactionListWithDeposit) {
            System.out.println(transaction);
        }
        Thread.sleep(2000);

    }

    private void displayAllEntries() throws InterruptedException {

        for (Transaction transaction: transactionList) {
            System.out.println(transaction);
        }
        Thread.sleep(2000);
    }

    private void initializingData(List<Transaction> transactions) {
        for (Transaction transaction: transactions) {
            if (isNegative(transaction.getAmount())) {
                transactionListWithDeposit.add(transaction);
            } else {
                transactionListWithPayment.add(transaction);

            }
        }
    }

    private static boolean isNegative(double number) {
        return number < 0;
    }

}

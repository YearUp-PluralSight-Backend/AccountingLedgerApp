package com.pluralsight.service;

import com.pluralsight.Repository.ReportOperations;
import com.pluralsight.model.Transaction;
import com.pluralsight.utils.InputUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.TemporalAdjusters.*;

/**
 * The Report class provides methods to generate various financial reports
 * based on transactions, such as month-to-date, previous month, year-to-date,
 * and previous year reports. It also allows searching transactions by vendor name
 * and custom search by properties.
 */
public class Report implements ReportOperations {

    // Logger instance for logging information
    private static final Logger logger = LoggerFactory.getLogger(Report.class);

    // List of all transactions
    private List<Transaction> transactionList;
    // List of deposit transactions
    private List<Transaction> transactionListWithDeposit;
    // List of payment transactions
    private List<Transaction> transactionListWithPayment;
    // Singleton instance of the Report class
    private static Report report;

    /**
     * Private constructor to prevent instantiation.
     */
    private Report() {
    }

    /**
     * Returns the singleton instance of the Report class.
     * If the instance is null, it initializes it.
     *
     * @return The singleton instance of Report
     */
    public static Report getInstance() {
        if (report == null) {
            report = new Report();
        }
        return report;
    }

    /**
     * Sets up the data for the report by initializing the transaction lists.
     *
     * @param transactionList The list of all transactions
     * @param transactionListWithDeposit The list of deposit transactions
     * @param transactionListWithPayment The list of payment transactions
     */
    public void setUpData(List<Transaction> transactionList, List<Transaction> transactionListWithDeposit, List<Transaction> transactionListWithPayment) {
        this.transactionList = transactionList;
        this.transactionListWithDeposit = transactionListWithDeposit;
        this.transactionListWithPayment = transactionListWithPayment;
    }

    /**
     * Displays the report screen menu options to the user.
     */
    public void reportScreenMenu() {
        String options =
                """
                        =========================================================================
                        =====          Welcome To Report Screen                             =====
                        =====          Option: (1)    Month To Date                         =====
                        =====          Option  (2)    Previous Month                        =====
                        =====          Option  (3)    Year To Date                          =====
                        =====          Option  (4)    Previous Year                         =====
                        =====          Option  (5)    Search By Vendor Name                 =====
                        =====          Option  (6)    Custom Search By Properties           =====
                        =====          Option  (0)    Back to Ledger Screen                 =====
                        =========================================================================
                        """;
        System.out.println(options);
    }

    /**
     * Displays the report screen and processes user commands.
     * The loop continues until the user chooses to go back to the ledger screen.
     */
    public void reportScreen() {
        logger.info("Showing Report Screen!");
        while (true) {
            try {
                reportScreenMenu();
                String command = InputUtil.promptForString(" (ReportScreen) Enter your Option: ").toUpperCase();
                switch (command) {
                    case "1" -> report.generateMonthToDateReport();
                    case "2" -> report.generatePreviousMonthReport();
                    case "3" -> report.generateYearToDateReport();
                    case "4" -> report.generatePreviousYearReport();
                    case "5" -> {
                        String vendorName = InputUtil.promptForString("Enter Vendor Name: ");
                        report.searchByVendorName(vendorName);
                    }
                    case "6" -> {
                        String start = InputUtil.promptForString("Enter start Date Time (yyyy-MM-dd): ");
                        String end = InputUtil.promptForString("Enter End Date Time (yyyy-MM-dd): ");
                        String description = InputUtil.promptForString("Enter the description: ");
                        String vendorName = InputUtil.promptForString("Enter Vendor Name: ");
                        double Amount = InputUtil.promptForDouble("Enter the Amount: ");
                        LocalDate startDate;
                        LocalDate endDate;
                        if (!start.isBlank() && !start.isEmpty() && !end.isBlank() && !end.isEmpty()) {
                            startDate = LocalDate.parse(start, InputUtil.dateFormatter);
                            endDate = LocalDate.parse(end, InputUtil.dateFormatter);
                        }
                        System.out.println("Date Time Format issues!");
                        startDate = LocalDate.now().minusYears(20);
                        endDate = LocalDate.now();
                        report.customSearch(startDate, endDate, description, vendorName, Amount);
                    }
                    case "0" -> {
                        return;
                    }
                    default -> System.out.println("Invalid Option. Please choose between (1----6 and (0: Exit))\n");
                }
            } catch (Exception e) {
                logger.error("Error processing the option: ", e);
                e.printStackTrace();
            }
        }
    }

    /**
     * Generates a month-to-date report by filtering transactions from the first day of the current month to today.
     */
    @Override
    public void generateMonthToDateReport() throws InterruptedException {
        logger.info("Generating report from this month to now!");
        LocalDate today = LocalDate.now().plusDays(1);
        LocalDate firstDayOfTheMonth = today.with(firstDayOfMonth()).minusDays(1);

        InputUtil.header();
        transactionList.parallelStream().filter(t -> {
                    if (t.getCreatedDateTime().toLocalDate().isAfter(firstDayOfTheMonth) && t.getCreatedDateTime().toLocalDate().isBefore(today) || t.getCreatedDateTime().toLocalDate().isEqual(firstDayOfTheMonth))
                        return true;
                    return false;
                }).sorted(Comparator.comparing(Transaction::getCreatedDateTime).reversed())
                .forEachOrdered(System.out::println);
        InputUtil.footer();

        Thread.sleep(2000);
    }

    /**
     * Generates a report for the previous month by filtering transactions from the first to the last day of the previous month.
     */
    @Override
    public void generatePreviousMonthReport() throws InterruptedException {
        logger.info("Generating report from previous whole month!");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime firstDayOfMonth = now.minusMonths(1).with(firstDayOfMonth());
        LocalDateTime lastDayOfMonth = now.minusMonths(1).with(lastDayOfMonth());

        InputUtil.header();
        List<Transaction> sortedTransactions = transactionList.parallelStream()
                .filter(t -> t.getCreatedDateTime().isAfter(firstDayOfMonth) && (t.getCreatedDateTime().isBefore(lastDayOfMonth) || t.getCreatedDateTime().isEqual(lastDayOfMonth)))
                .sorted(Comparator.comparing(Transaction::getCreatedDateTime).reversed())
                .collect(Collectors.toList());
        if (sortedTransactions.isEmpty()) {
            System.out.println("No transactions found in the previous month.");
        } else {
            sortedTransactions.stream().forEach(System.out::println);
        }
        InputUtil.footer();
        Thread.sleep(2000);

    }

    /**
     * Generates a year-to-date report by filtering transactions from the first day of the current year to today.
     */
    @Override
    public void generateYearToDateReport() throws InterruptedException {
        logger.info("Generating report from this year to now!");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startYear = now.with(firstDayOfYear());

        InputUtil.header();
        List<Transaction> sortedTransactions = transactionList.parallelStream()
                .filter(t -> t.getCreatedDateTime().isAfter(startYear) && t.getCreatedDateTime().isBefore(now) || t.getCreatedDateTime().isEqual(now))
                .sorted(Comparator.comparing(Transaction::getCreatedDateTime).reversed())
                .collect(Collectors.toList());
        if (sortedTransactions.isEmpty()) {
            System.out.println("No transactions found in the previous month.");
        } else {
            sortedTransactions.stream().forEach(System.out::println);
        }
        InputUtil.footer();
        Thread.sleep(2000);

    }

    /**
     * Generates a report for the previous year by filtering transactions from the first to the last day of the previous year.
     */
    @Override
    public void generatePreviousYearReport() throws InterruptedException {
        logger.info("Generating report from last whole year !");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lastyearOfFirstDay = now.minusYears(1).with(firstDayOfYear());
        LocalDateTime lastyearOfLastDay = now.minusYears(1).with(lastDayOfYear());

        InputUtil.header();
        List<Transaction> sortedTransactions = transactionList.parallelStream()
                .filter(t -> t.getCreatedDateTime().isAfter(lastyearOfFirstDay) && t.getCreatedDateTime().isBefore(lastyearOfLastDay) || t.getCreatedDateTime().isEqual(lastyearOfFirstDay))
                .sorted(Comparator.comparing(Transaction::getCreatedDateTime).reversed())
                .collect(Collectors.toList());
        if (sortedTransactions.isEmpty()) {
            System.out.println("No transactions found in the previous month.");
        } else {
            sortedTransactions.stream().forEach(System.out::println);
        }
        InputUtil.footer();
        Thread.sleep(2000);

    }

    /**
     * Searches transactions by vendor name and displays the results.
     *
     * @param vendorName The name of the vendor to search for
     */
    @Override
    public void searchByVendorName(String vendorName) throws InterruptedException {
        logger.info("Performing search transaction function by look up vendor name!");
        InputUtil.header();
        transactionList.parallelStream().filter(t -> t.getVendor().equalsIgnoreCase(vendorName)).forEach(System.out::println);
        InputUtil.footer();
        Thread.sleep(2000);

    }

    /**
     * Performs a custom search on transactions based on the provided properties.
     *
     * @param startDate The start date of the search range
     * @param endDate The end date of the search range
     * @param description The description to search for
     * @param vendorName The vendor name to search for
     * @param amount The amount to search for
     * @throws InterruptedException If the thread is interrupted
     */
    @Override
    public void customSearch(LocalDate startDate, LocalDate endDate, String description, String vendorName, double amount) throws InterruptedException {
        logger.info("Performing custom search function!");
        InputUtil.header();

        transactionList.stream().filter(transaction -> {
            if (transaction.getCreatedDateTime().toLocalDate().isAfter(startDate) || transaction.getCreatedDateTime().toLocalDate().isBefore(endDate) ||
                    isEqualWithString(transaction.getVendor(), vendorName) || isEqualWithString(transaction.getDescription(), description) ||
                    isEqualWithDouble(transaction.getAmount(), amount)) {
                return true;
            } else {
                return false;
            }

        }).sorted(Comparator.comparing(Transaction::getCreatedDateTime).reversed()).forEachOrdered(System.out::println);

        InputUtil.footer();
        Thread.sleep(2000);
    }

    /**
     * Checks if two strings are equal, ignoring case.
     *
     * @param s1 The first string
     * @param s2 The second string
     * @return True if the strings are equal, false otherwise
     */
    private boolean isEqualWithString(String s1, String s2) {
        return s1.equalsIgnoreCase(s2);
    }

    /**
     * Checks if two double values are equal.
     *
     * @param d1 The first double value
     * @param d2 The second double value
     * @return True if the double values are equal, false otherwise
     */
    private boolean isEqualWithDouble(double d1, double d2) {
        return d1 == d2;
    }

}
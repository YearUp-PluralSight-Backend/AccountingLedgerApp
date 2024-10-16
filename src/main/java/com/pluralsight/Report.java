package com.pluralsight;

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


public class Report {

    private static final Logger logger = LoggerFactory.getLogger(Report.class);

    private List<Transaction> transactionList;
    private List<Transaction> transactionListWithDeposit;
    private List<Transaction> transactionListWithPayment;
    private static Report report;

    private Report() {
    }

    public static Report getInstance() {
        // Singleton
        if (report == null) {
            report = new Report();
        }

        return report;

    }

    public void setUpData(List<Transaction> transactionList, List<Transaction> transactionListWithDeposit, List<Transaction> transactionListWithPayment) {
        this.transactionList = transactionList;
        this.transactionListWithDeposit = transactionListWithDeposit;
        this.transactionListWithPayment = transactionListWithPayment;
    }

    public void reportScreenMenu() {
        String options =
                """
                        =========================================================================
                        =====          (%s) Welcome To Report Screen                        =====
                        =====          Option: (1)    Month To Date                         =====
                        =====          Option  (2)    Previous Month                        =====
                        =====          Option  (3)    Year To Date                          =====
                        =====          Option  (4)    Previous Year                         =====
                        =====          Option  (5)    Search By Vendor Name                 =====
                        =====          Option  (5)    Custom Search By Properties           =====
                        =====          Option  (0)    Back to Ledger Screen                 =====
                        =========================================================================
                        """.formatted("\\u263A");
        System.out.println(options);
    }

    public void reportScreen() {
        System.out.println(report);
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
                        LocalDate startDate = LocalDate.parse(start, InputUtil.dateFormatter);
                        LocalDate endDate = LocalDate.parse(end, InputUtil.dateFormatter);
                        report.customSearch(startDate, endDate, description, vendorName, Amount);
                    }
                    case "0" -> {
                        return;
                    }
                    default -> System.out.println("Invalid Option. Please choose between (1-9, ALL, D, P, R, H)\n");
                }

            } catch (Exception e) {
                logger.error("Error processing the option: ", e);
                throw new RuntimeException(e);
            }
        }
    }

    private void generateMonthToDateReport() {


        // https://stackoverflow.com/questions/9382897/how-to-get-start-and-end-date-of-a-year

        LocalDate today = LocalDate.now();
        transactionList.parallelStream().filter(t -> {
            LocalDate firstDayOfTheMonth = today.with(firstDayOfMonth()); // Get the first day of the current month
            if (t.getCreatedDateTime().toLocalDate().isAfter(firstDayOfTheMonth) && t.getCreatedDateTime().toLocalDate().isBefore(t.getCreatedDateTime().toLocalDate()))
                return true;
            return false;
        }).forEach(System.out::println);


    }

    private void generatePreviousMonthReport() {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime beforeMonth = now.minusMonths(1);

        List<Transaction> sortedTransactions = transactionList.stream()
                .filter(t -> t.getCreatedDateTime().isAfter(beforeMonth) && t.getCreatedDateTime().isBefore(now))
                .sorted(Comparator.comparing(Transaction::getCreatedDateTime))
                .collect(Collectors.toList());

        sortedTransactions.stream().forEach(System.out::println);

    }

    private void generateYearToDateReport() {

        // https://stackoverflow.com/questions/9382897/how-to-get-start-and-end-date-of-a-year
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startYear = now.with(firstDayOfYear());

        List<Transaction> sortedTransactions = transactionList.stream()
                .filter(t -> t.getCreatedDateTime().isAfter(startYear) && t.getCreatedDateTime().isBefore(now))
                .sorted(Comparator.comparing(Transaction::getCreatedDateTime))
                .collect(Collectors.toList());

    }

    private void generatePreviousYearReport() {

        // https://stackoverflow.com/questions/9382897/how-to-get-start-and-end-date-of-a-year
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lastyearOfFirstDay = now.minusYears(1).with(firstDayOfYear());
        LocalDateTime lastyearOfLastDay = now.minusYears(1).with(lastDayOfYear());


    }

    private void searchByVendorName(String vendorName) {
        transactionList.stream().filter(t -> t.getVendor().equalsIgnoreCase(vendorName)).forEach(System.out::println);
    }

    private void customSearch(LocalDate startDate, LocalDate endDate, String description, String vendorName, double amount) {

        for (Transaction transaction : transactionList) {
            if (transaction.getCreatedDateTime().toLocalDate().isAfter(startDate) && transaction.getCreatedDateTime().toLocalDate().isBefore(endDate) &&
                    isEqualWithString(transaction.getVendor(), vendorName) && isEqualWithString(transaction.getDescription(), description) &&
                    isEqualWithDouble(transaction.getAmount(), amount)) {
                System.out.println(transaction);
            }
        }
    }

    private boolean isEqualWithString(String s1, String s2) {
        return s1.equalsIgnoreCase(s2);
    }

    private boolean isEqualWithDouble(double d1, double d2) {
        return d1 == d2;
    }

}

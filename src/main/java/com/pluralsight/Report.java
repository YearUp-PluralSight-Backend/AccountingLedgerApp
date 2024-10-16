package com.pluralsight;

import com.pluralsight.model.Transaction;
import com.pluralsight.utils.InputUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.*;
import java.util.List;

public class Report {

    private static final Logger logger = LoggerFactory.getLogger(Report.class);

    private List<Transaction> transactionList;
    private List<Transaction> transactionListWithDeposit;
    private List<Transaction> transactionListWithPayment;
    private static Report report;

    private Report() {}

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
                =====          Welcome To Accounting Ledger Application             =====
                =====          Option: (1)    Month To Date                         =====
                =====          Option  (2)    Previous Month                        =====
                =====          Option  (3)    Year To Date                          =====
                =====          Option  (4)    Previous Year                         =====
                =====          Option  (5)    Search By Vendor Name                 =====
                =====          Option  (6)    Search By Start Date                  =====
                =====          Option  (7)    Search By End Date                    =====
                =====          Option  (8)    Search By Description                 =====
                =====          Option  (9)    Search By Amount                      =====
                =====          Option  (0)    Back to Ledger Screen                 =====
                =========================================================================
                """;
        System.out.println(options);
    }

    public void reportScreen() {
        System.out.println(report);
        while(true) {
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
                        String startDate = InputUtil.promptForString("Enter End Date Time (yyyy-MM-dd): ");
                        report.searchByStartDate(LocalDate.parse(startDate));
                    }
                    case "7" -> {
                        String endDate = InputUtil.promptForString("Enter End Date Time (yyyy-MM-dd): ");
                        report.searchByEndDate(LocalDate.parse(endDate, InputUtil.dateTimeFormatter));
                    }
                    case "8" -> {
                        String description = InputUtil.promptForString("Enter Description: ");
                        report.searchByDescription(description);
                    }
                    case "9" -> {
                        double amount = InputUtil.promptForDouble("Enter the Amount");
                        report.searchByAmount(amount);
                    }
                    case "0" -> {return;}
                    default -> System.out.println("Invalid Option. Please choose between (1-9, ALL, D, P, R, H)\n");
                }

            } catch (Exception e) {
                logger.error("Error processing the option: ", e);
                throw new RuntimeException(e);
            }
        }
    }

    private void generateMonthToDateReport() {

        LocalDate today = LocalDate.now();
        transactionList.parallelStream().filter(t -> {
            Month month = t.getCreatedDateTime().getMonth();
            int year = t.getCreatedDateTime().getYear();
            LocalDate firstDayOfTheMonth = today.withDayOfMonth(1); // Get the first day of the current month
            if (t.getCreatedDateTime().toLocalDate().isAfter(firstDayOfTheMonth) && t.getCreatedDateTime().toLocalDate().isBefore(t.getCreatedDateTime().toLocalDate())) return  true;
            return false;
        }).forEach(System.out::println);




    }

    private void generatePreviousMonthReport() {

    }

    private void generateYearToDateReport() {
    }

    private void generatePreviousYearReport() {
    }

    private void searchByVendorName(String vendorName) {
        transactionList.stream().filter(t -> t.getVendor().equalsIgnoreCase(vendorName)).forEach(System.out::println);
    }
    private void searchByStartDate(LocalDate startDate) {
        transactionList.stream().filter(t -> t.getCreatedDateTime().toLocalDate().isAfter(startDate)).forEach(System.out::println);
    }

    private void searchByEndDate(LocalDate endDate) {
        transactionList.stream().filter(t -> t.getCreatedDateTime().toLocalDate().isBefore(endDate)).forEach(System.out::println);
    }

    private void searchByDescription(String description) {
        transactionList.stream().filter(t -> t.getDescription().equalsIgnoreCase(description)).forEach(System.out::println);
    }

    private void searchByAmount(double amount) {
        transactionList.stream().filter(t -> t.getAmount() == amount).forEach(System.out::println);
    }
}

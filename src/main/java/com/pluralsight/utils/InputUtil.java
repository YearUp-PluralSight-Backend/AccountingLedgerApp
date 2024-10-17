package com.pluralsight.utils;

import com.pluralsight.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class InputUtil {


    // For
    private static final Logger logger = LoggerFactory.getLogger(InputUtil.class);
    public final static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.US);
    public final static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Prompt for a string value from the user.
     *
     * @param prompt Message to show the user
     * @return The input value
     */
    public static String promptForString(String prompt) {
        System.out.println(prompt);
        String value = "";
        try {
            value = scanner.nextLine().trim();
            logger.info("User input string: {}", value);
        } catch (Exception e) {
            logger.error("Error while reading string input: ", e);
            e.printStackTrace();
        }
        return value;
    }

    public static String promptForString() {
        String value = "";
        try {
            value = scanner.nextLine().trim();
            logger.info("User input string: {}", value);
        } catch (Exception e) {
            logger.error("Error while reading string input: ", e);
            e.printStackTrace();
        }
        return value;
    }

    public static int promptForInteger(String prompt) {
        System.out.println(prompt);
        int value = 0;
        try {
            value = Integer.parseInt(scanner.nextLine().trim());
            logger.info("User input integer: {}", value);
        } catch (Exception e) {
            logger.error("Error while reading integer input: ", e);
            e.printStackTrace();
        }
        return value;
    }

    public static int promptForInteger() {
        int value = 0;
        try {
            value = Integer.parseInt(scanner.nextLine().trim());
            logger.info("User input integer: {}", value);
        } catch (Exception e) {
            logger.error("Error while reading integer input: ", e);
            e.printStackTrace();
        }
        return value;
    }

    public static Double promptForDouble(String prompt) {
        System.out.println(prompt);
        double value = 0;
        try {
            value = Double.parseDouble(scanner.nextLine().trim());
            logger.info("User input double: {}", value);
        } catch (Exception e) {
            logger.error("Error while reading double input: ", e);
            System.out.println("Number Format issue! Please enter the right format!");
            e.printStackTrace();
        }
        return value;
    }

    public static Double promptForDouble() {
        double value = 0;
        try {
            value = Double.parseDouble(scanner.nextLine().trim());
            logger.info("User input double: {}", value);
        } catch (Exception e) {
            logger.error("Error while reading double input: ", e);
            e.printStackTrace();
        }
        return value;
    }

    public static short promptForShort(String prompt) {
        System.out.println(prompt);
        short value = 0;
        try {
            value = Short.parseShort(scanner.nextLine().trim());
            logger.info("User input short: {}", value);
        } catch (Exception e) {
            logger.error("Error while reading short input: ", e);
            e.printStackTrace();
        }
        return value;
    }

    public static void formatTransaction(Transaction transaction) {
        String content =
                """
                %s|%s|%s|%s|%.02f|
                """.formatted(
                        transaction.getCreatedDateTime().toLocalDate(),
                        transaction.getCreatedDateTime().toLocalTime(),
                        transaction.getDescription(),
                        transaction.getVendor(),
                        transaction.getAmount());
        logger.info("Formatted transaction: {}", content);
        System.out.println(content);
    }

    public static Transaction userInput() {
        try {
            String dateTime = promptForString("Enter the Date time (yyyy-MM-dd HH:mm:ss) Or default Today's Now: ");
            logger.info("User entered dateTime: {}", dateTime);

            String vendorName = promptForString("Enter the Vendor Name: ");
            logger.info("User entered vendorName: {}", vendorName);

            String description = promptForString("Enter the Description: ");
            logger.info("User entered description: {}", description);

            Double amount = promptForDouble("Enter the Amount: ");
            logger.info("User entered amount: {}", amount);

            LocalDateTime createdDateTime;

            if (dateTime.isBlank() || dateTime.isEmpty()) {
                createdDateTime = LocalDateTime.now();

            } else {
                createdDateTime = LocalDateTime.parse(dateTime, InputUtil.dateTimeFormatter);
            }
            Transaction transaction = new Transaction(createdDateTime, description, vendorName, amount);
            logger.info("Created transaction: {}", transaction);

            return transaction;
        } catch (Exception e) {
            logger.error("Error while processing user input: ", e);
            System.out.println("Date Time Format issues! Please try it again!");
            e.printStackTrace();
        }

        return null;
    }

    public static void displayDataWithFormat(List<Transaction> transactionList) {
        header();
        transactionList.stream()
                .sorted(Comparator.comparing(Transaction::getCreatedDateTime))
                .forEach(System.out::println);
        footer();
    }

    public static void header() {
        String header = """
                | %-20s | %-40s | %-20s | $%-20s |
                ------------------------------------------------------------------------------------------------------------------
                """.formatted("Date Time", "Description", "Vendor Name", "Amount");
        System.out.print(header);
    }

    public static void footer() {
        String footer = """
                ------------------------------------------------------------------------------------------------------------------
                ------------------------        You have successfully displayed all of the data!          ------------------------
                ------------------------------------------------------------------------------------------------------------------
                """;
        System.out.println(footer);
    }

    public static void formatOuput(double d) {
        String value = """
                ------------------------------------------------------------------------------------------------------------------
                ------------------------        Your Balance is %.2f                                      ------------------------
                ------------------------------------------------------------------------------------------------------------------
                """.formatted(d);
        System.out.println(value);
    }
}

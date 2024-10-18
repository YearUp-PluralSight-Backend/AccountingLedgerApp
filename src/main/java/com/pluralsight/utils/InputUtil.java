package com.pluralsight.utils;

import com.pluralsight.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Utility class for handling user input and formatting transactions.
 */
public class InputUtil {

    // Logger instance for logging information
    private static final Logger logger = LoggerFactory.getLogger(InputUtil.class);
    // DateTimeFormatter for formatting date and time
    public final static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.US);
    // DateTimeFormatter for formatting date
    public final static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);

    // Scanner instance for reading user input
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

    /**
     * Prompt for a string value from the user without a prompt message.
     *
     * @return The input value
     */
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

    /**
     * Prompt for an integer value from the user.
     *
     * @param prompt Message to show the user
     * @return The input value
     */
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

    /**
     * Prompt for an integer value from the user without a prompt message.
     *
     * @return The input value
     */
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

    /**
     * Prompt for a double value from the user.
     *
     * @param prompt Message to show the user
     * @return The input value
     */
    public static Double promptForDouble(String prompt) {
        System.out.println(prompt);
        double value = 0;
        try {
            String stringValue = scanner.nextLine().trim();
            if (stringValue.isBlank() && stringValue.isEmpty()) {
                value = 0;
            } else {
                value = Double.parseDouble(stringValue);
            }
            logger.info("User input double: {}", value);
        } catch (Exception e) {
            logger.error("Error while reading double input: ", e);
            System.out.println("Number Format issue! Please enter the right format!");
            e.printStackTrace();
        }
        return value;
    }

    /**
     * Prompt for a double value from the user with a default value.
     *
     * @param prompt Message to show the user
     * @param defaultValue The default value if input is empty
     * @return The input value
     */
    public static Double promptForDouble(String prompt, double defaultValue) {
        System.out.println(prompt);
        double value = 0;
        try {
            String stringValue = scanner.nextLine().trim();
            if (stringValue.isBlank() && stringValue.isEmpty()) {
                value = defaultValue;
            }
            logger.info("User input double: {}", value);
        } catch (Exception e) {
            logger.error("Error while reading double input: ", e);
            System.out.println("Number Format issue! Please enter the right format!");
            e.printStackTrace();
        }
        return value;
    }

    /**
     * Prompt for a double value from the user without a prompt message.
     *
     * @return The input value
     */
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

    /**
     * Prompt for a short value from the user.
     *
     * @param prompt Message to show the user
     * @return The input value
     */
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

    /**
     * Format a transaction and print it.
     *
     * @param transaction The transaction to format
     */
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

    /**
     * Prompt the user for transaction details and create a Transaction object.
     *
     * @return The created Transaction object
     */
    public static Transaction userInput() {
        try {
            String dateTime = promptForString("Enter the Date time (yyyy-MM-dd HH:mm:ss) Or default Today's Now: ");

            LocalDateTime createdDateTime;

            if (dateTime.isBlank() || dateTime.isEmpty()) {
                createdDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
                System.out.println("You have used the default time: Now " + createdDateTime);

            } else {
                createdDateTime = LocalDateTime.parse(dateTime, InputUtil.dateTimeFormatter);
            }

            String vendorName = promptForString("Enter the Vendor Name: ");
            String description = promptForString("Enter the Description: ");
            Double amount = promptForDouble("Enter the Amount: ");

            System.out.println();
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

    /**
     * Display a list of transactions with formatted output.
     *
     * @param transactionList The list of transactions to display
     */
    public static void displayDataWithFormat(List<Transaction> transactionList) {
        header();
        transactionList.stream()
                .sorted(Comparator.comparing(Transaction::getCreatedDateTime).reversed())
                .forEach(System.out::println);

        Comparator<Transaction> comparator = Comparator.comparing(Transaction::getCreatedDateTime).reversed();
        Collections.sort(transactionList, comparator);

        footer();
    }

    /**
     * Print the header for the transaction display.
     */
    public static void header() {
        String header = """
                | %-20s | %-40s | %-20s | $%-20s |
                ------------------------------------------------------------------------------------------------------------------
                """.formatted("Date Time", "Description", "Vendor Name", "Amount");
        System.out.print(header);
    }

    /**
     * Print the footer for the transaction display.
     */
    public static void footer() {
        String footer = """
                ------------------------------------------------------------------------------------------------------------------
                ------------------------        You have successfully displayed all of the data!          ------------------------
                ------------------------------------------------------------------------------------------------------------------
                """;
        System.out.println(footer);
    }

    /**
     * Format and print the output for a double value.
     *
     * @param d The double value to format
     */
    public static void formatOuput(double d) {
        String value = """
                ------------------------------------------------------------------------------------------------------------------
                ------------------------        Your Balance is %.2f                                ------------------------
                ------------------------------------------------------------------------------------------------------------------
                """.formatted(d);
        System.out.println(value);
    }

}
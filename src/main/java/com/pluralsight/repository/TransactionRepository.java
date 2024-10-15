package com.pluralsight.repository;

import com.pluralsight.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository {

    Logger logger = LoggerFactory.getLogger(TransactionRepository.class);

    String FILENAME = "inventory.csv";

    Optional<Transaction> saveTransaction(Transaction transaction);

    Optional<Boolean> updateTransaction(UUID id, Transaction transaction);

    Optional<Boolean> deleteTransaction(UUID id);

    Optional<Transaction> getTransactionById(UUID id);

    Optional<List<Transaction>> getTransactions();

    Optional<ArrayList<Transaction>> getTransactionBetweenDates(LocalDate startDate, LocalDate endDate);

    Optional<ArrayList<Transaction>> getTransactionBetweenTimes(LocalTime startTime, LocalTime endTime);

    Optional<Transaction> getTransactionByVendor(String vendorName);

    void printTransaction();

    /**
     * read the file inventory.csv
     * get the content and split them by |.
     * create a {@code Product} to store the data
     * add the {@code Product} to the inventory list
     *
     * @return inventory
     */
    default List<Transaction> getInventory() {
        List<Transaction> inventory = new ArrayList<>(); // create a list to store the products

        // read the file and add the content to the inventory list.  (try-catch resource release)
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            logger.info("Reading the csv file {}", FILENAME);

            while ((line = bufferedReader.readLine()) != null) {

                String[] lineContent = line.split("\\|");
//                2023-04-15|10:13:25|ergonomic keyboard|Amazon|-89.50
//     public Transaction(String vendor, double amount, String description, int numberOfTransactions, LocalDate updatedDate, LocalTime updatedTime)
                inventory.add(new Transaction(lineContent[3], Double.parseDouble(lineContent[4]), lineContent[2], LocalDate.now(), LocalTime.now()));

            }
        } catch (IOException e) {
            logger.error("Failed to read from file " + FILENAME, e);
            throw new RuntimeException(e);
        }
        logger.info("Finished reading the file {}", FILENAME);
        logger.info("Loaded {} transactions from {}", inventory.size(), FILENAME);
        return inventory;
    }

    /**
     *  read the list and overwrite the csv file.
     * @param transactionList
     */
    default void updateCSVFile(List<Transaction> transactionList) {

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("inventory.csv", true))) {
            logger.info("Updating the file {}", FILENAME);
            for (Transaction transaction : transactionList) {
                StringBuilder stringBuilder = new StringBuilder();
                String line = stringBuilder.append("\n")
                        .append(transaction.getCreateDate())
                        .append("|")
                        .append(transaction.getCreateTime())
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
            throw new RuntimeException(e);
        }
        logger.info("Successfully updated the file {}", FILENAME);
    }
}

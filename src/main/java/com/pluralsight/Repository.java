package com.pluralsight;

import com.pluralsight.utils.InputUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface Repository {

    Logger logger = LoggerFactory.getLogger(Repository.class);

    String FILENAME = "inventory.csv";

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
            int skip = 0;
            while ((line = bufferedReader.readLine()) != null) {

                if (skip == 0) { // skip the first line
                    skip++;
                    continue;
                }

                if (line.isBlank() || line.isEmpty()) continue;
                String[] lineContent = line.split("\\|");

//                2023-04-15|10:13:25|ergonomic keyboard|Amazon|-89.50
//     public Transaction(LocalDateTime, String vendor, double amount, String description)
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
     *  read the list and overwrite the csv file.
     * @param transactionList
     */
    default void updateCSVFile(List<Transaction> transactionList) {
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

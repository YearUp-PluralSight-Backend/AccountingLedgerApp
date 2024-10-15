package pluralsight.service;

import com.pluralsight.model.Transaction;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public interface TransationService {

    String FILENAME = "inventory.csv";

    /**
     * read the file inventory.csv
     * get the content and split them by |.
     * create a {@code Product} to store the data
     * add the {@code Product} to the inventory list
     *
     * @return
     */
    default List<Transaction> getInventory() {
        List<Transaction> inventory = new ArrayList<>(); // create a list to store the products

        // read the file and add the content to the inventory list.  (try-catch resource release)
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                String[] lineContent = line.split("\\|");
//                2023-04-15|10:13:25|ergonomic keyboard|Amazon|-89.50
//     public Transaction(String vendor, double amount, String description, int numberOfTransactions, LocalDate createDate, LocalTime createTime, LocalDate updatedDate, LocalTime updatedTime)
                inventory.add(new Transaction(lineContent[3], Double.parseDouble(lineContent[4]), lineContent[2], LocalDate.now(), LocalTime.now(), LocalDate.now(), LocalTime.now()));

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return inventory;
    }

    default void UpdatetheCSVFile(List<Transaction> transactionList) {

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("inventory.csv", true))) {

            for (Transaction transaction: transactionList) {
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
            throw new RuntimeException(e);
        }
    }
}

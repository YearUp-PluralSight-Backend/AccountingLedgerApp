package com.pluralsight.repository.RepositoryImple;

import com.pluralsight.model.Transaction;
import com.pluralsight.repository.TransactionRepository;
import com.pluralsight.utils.UtilHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TranstionrRepositoryImple implements TransactionRepository {


    // using for logging.
    private static final Logger logger = LoggerFactory.getLogger(TranstionrRepositoryImple.class);

    //    database for temp storing transaction data;
    private final List<Transaction> transactionList = getInventory();


    /**
     * Adds a transaction to the database (or a CSV file).
     *
     * @param transaction to be added
     * @return transaction if it saved successfully.
     */
    @Override
    public Optional<Transaction> saveTransaction(Transaction transaction) {

        transactionList.add(transaction);  // add transaction
        updateCSVFile(transactionList); // rewrite the transaction to csv file. or update the database.
        logger.info("Added a new transaction {}", transaction);
        return Optional.of(transaction);
    }

    /**
     * Updates the transaction based on the id.
     *
     * @param id          based on id to find the transaction in the database or csv file.
     * @param transaction replace the transaction that found by id.
     * @return Optional<Boolean>  if it found equal id, else return false.
     */
    @Override
    public Optional<Boolean> updateTransaction(UUID id, Transaction transaction) {

        for (Transaction oldTransaction : transactionList) {

            if (oldTransaction.getId().equals(id)) {

                oldTransaction.setAmount(transaction.getAmount());
                oldTransaction.setVendor(transaction.getVendor());
                oldTransaction.setDescription(transaction.getDescription());
                oldTransaction.setUpdatedDate(transaction.getUpdatedDate());
                oldTransaction.setUpdatedTime(transaction.getUpdatedTime());
                logger.info("Updated {} transaction  to {}", oldTransaction, transaction);
                return Optional.of(true);
            }
        }

        return Optional.of(false);
    }

    /**
     * Finds the transaction by id and then delete it
     *
     * @param id : based on id to find the transaction in the database or csv file.
     * @return Optional<Boolean>   if it found it, return true, else return false; Optional
     */
    @Override
    public Optional<Boolean> deleteTransaction(UUID id) {

        for (Transaction oldTransaction : transactionList) {
            if (oldTransaction.getId().equals(id)) {

                transactionList.remove(oldTransaction);
                logger.info("Deleted a transaction based on id : {}", id);
                return Optional.of(true);
            }
        }

        return Optional.of(false);
    }

    /**
     * Finds a transaction based on id
     *
     * @param id
     * @return transaction
     */
    @Override
    public Optional<Transaction> getTransactionById(UUID id) {

        for (Transaction oldTransaction : transactionList) {
            if (oldTransaction.getId().equals(id)) {
                logger.info("Get a transaction by id: {}", id);
                return Optional.of(oldTransaction);
            }
        }

        return Optional.empty();
    }

    /**
     * If the size is less than 1, it will return Optional empty and means no data in the array.
     *
     * @return transactionList
     */
    @Override
    public Optional<List<Transaction>> getTransactions() {
        logger.info("Get all transactions.  Total of them are: {}!", transactionList.size());
        return transactionList.size() > 1 ? Optional.of(transactionList) : Optional.empty();
    }

    /**
     * Finds the transactions that occurred between the specified start and end dates.
     *
     * @param startDate the beginning of the date range (exclusive)
     * @param endDate   the end of the date range (exclusive)
     * @return
     */
    @Override
    public Optional<ArrayList<Transaction>> getTransactionBetweenDates(LocalDate startDate, LocalDate endDate) {

        ArrayList<Transaction> transactionsBetweenDates = new ArrayList<>();

        for (Transaction transaction : transactionList) {

            if (transaction.getCreateDate().isAfter(startDate) && transaction.getCreateDate().isBefore(endDate)) {
                transactionsBetweenDates.add(transaction);
            }
        }
        logger.info("Number of the Transaction between date {} --- {} : {}!", startDate, endDate, transactionsBetweenDates.size());

        return Optional.of(transactionsBetweenDates);
    }

    /**
     * Finds the transactions that occurred between the specified start and end times.
     *
     * @param startTime the beginning of the time range (exclusive)
     * @param endTime   the end of the time range (exclusive)
     * @return
     */
    @Override
    public Optional<ArrayList<Transaction>> getTransactionBetweenTimes(LocalTime startTime, LocalTime endTime) {

        ArrayList<Transaction> transactionsBetweenTimes = new ArrayList<>();

        for (Transaction transaction : transactionList) {

            if (transaction.getCreateTime().isAfter(startTime) && transaction.getCreateTime().isBefore(endTime)) {
                transactionsBetweenTimes.add(transaction);
            }
        }
        logger.info("Number of the Transaction between time {} --- {} : {}!", startTime, endTime, transactionsBetweenTimes.size());
        return Optional.of(transactionsBetweenTimes);
    }

    /**
     * Finds a transaction by the specified vendor name.
     *
     * @param vendorName the name of the vendor to search for
     * @return transaction
     */
    @Override
    public Optional<Transaction> getTransactionByVendor(String vendorName) {
        for (Transaction transaction : transactionList) {

            if (transaction.getVendor().equalsIgnoreCase(vendorName)) {
                logger.info("Transaction: {}", transaction);
                return Optional.of(transaction);
            }
        }
        return Optional.empty();
    }

    /**
     * printout all transactions
     */
    @Override
    public void printTransaction() {

        logger.info("print out the transaction!  total of transactions are {}", transactionList.size());
        String header =
                """
                 \s\s\sdate\s\s\s|\s\s\stime\s\s\s|\s\s\sdescription\s\s\s|\s\s\svendor\s\s\s|\s\s\samount\s\s\s|
                 """;
        transactionList.stream().forEach(UtilHelper::format);

    }
}

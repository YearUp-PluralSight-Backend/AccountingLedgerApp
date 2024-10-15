package com.pluralsight.repository.RepositoryImple;

import com.pluralsight.model.Transaction;
import com.pluralsight.repository.TransactionRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TranstionrRepositoryImple implements TransactionRepository {


//    database for temp storing transaction data;
    private final List<Transaction> transactionList = getInventory();


    /**
     *  add the transaction to database
     * @param transaction
     * @return transaction
     */
    @Override
    public Optional<Transaction> saveTransaction(Transaction transaction) {

        transactionList.add(transaction);  // add transaction
        updateCSVFile(transactionList); // rewrite the transaction to csv file. or update the database.



        return Optional.of(transaction);
    }

    /**
     * @param id
     * @param transaction
     * @return
     */
    @Override
    public Optional<Boolean> updateTransaction(UUID id, Transaction transaction) {

        for (Transaction oldTransaction: transactionList) {

            if (oldTransaction.getId().equals(id)) {

                oldTransaction.setAmount(transaction.getAmount());
                oldTransaction.setVendor(transaction.getVendor());
                oldTransaction.setDescription(transaction.getDescription());
                oldTransaction.setUpdatedDate(transaction.getUpdatedDate());
                oldTransaction.setUpdatedTime(transaction.getUpdatedTime());

                return Optional.of(true);
            }
        }

        return Optional.of(false);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<Boolean> deleteTransaction(UUID id) {

        for (Transaction oldTransaction: transactionList) {
            if (oldTransaction.getId().equals(id)) {

                transactionList.remove(oldTransaction);

                return Optional.of(true);
            }
        }

        return Optional.of(false);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<Transaction> getTransactionById(UUID id) {

        for (Transaction oldTransaction: transactionList) {
            if (oldTransaction.getId().equals(id)) {
                return Optional.of(oldTransaction);
            }
        }

        return Optional.empty();
    }

    /**
     *  if the size is less than 1, it will return Optional empty and means no data in the array.
     * @return transactionList
     */
    @Override
    public Optional<List<Transaction>> getTransactions() {
        return transactionList.size() > 1 ? Optional.of(transactionList) : Optional.empty();
    }

    /**
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public Optional<ArrayList<Transaction>> getTransactionBetweenDates(LocalDate startDate, LocalDate endDate) {
        return Optional.empty();
    }

    /**
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public Optional<ArrayList<Transaction>> getTransactionBetweenTimes(LocalTime startDate, LocalTime endDate) {
        return Optional.empty();
    }

    /**
     * @param vendorName
     * @return
     */
    @Override
    public Optional<Transaction> getTransactionByVendor(String vendorName) {
        return Optional.empty();
    }

    /**
     *
     */
    @Override
    public void printTransaction() {

    }
}

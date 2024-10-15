package com.pluralsight.repository.RepositoryImple;

import com.pluralsight.model.Account;
import com.pluralsight.repository.AccountRepository;

public class AccountRepositoryImple implements AccountRepository {


    /**
     * @param account
     */
    @Override
    public void saveAccount(Account account) {

    }

    /**
     * @param account
     */
    @Override
    public void updateAccount(Account account) {

    }

    /**
     * @param account
     */
    @Override
    public void deleteAccount(Account account) {

    }

    /**
     * @param accountNumber
     * @return
     */
    @Override
    public Account getAccount(String accountNumber) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public Account[] getAccounts() {
        return new Account[0];
    }

    /**
     * @param accountNumber
     */
    @Override
    public void printAccount(String accountNumber) {

    }
}

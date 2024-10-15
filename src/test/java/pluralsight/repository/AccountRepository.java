package pluralsight.repository;

import com.pluralsight.model.Account;


public interface AccountRepository {


    void saveAccount(Account account);

    void updateAccount(Account account);

    void deleteAccount(Account account);

    Account getAccount(String accountNumber);

    Account[] getAccounts();

    void printAccount(String accountNumber);


}

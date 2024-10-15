package pluralsight.model;


import com.pluralsight.model.Account;
import com.pluralsight.model.Address;
import com.pluralsight.model.Enums.Role;
import com.pluralsight.model.Person;
import com.pluralsight.model.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PersonTest {

    // Class-level variable for accounts
    private List<Account> accounts;

    @Before
    public void initializingData() {
        accounts = new ArrayList<>(); // Initialize the accounts list here

        List<Transaction> savingsTransactionList = new ArrayList<>();

        Transaction transaction1 = new Transaction("Amazon", 2000, "Personal Computer",
                LocalDate.now(), LocalTime.now());

        Transaction transaction2 = new Transaction("Best Buy", 1500, "Laptop",
                LocalDate.now(), LocalTime.now());

        Transaction transaction3 = new Transaction("Walmart", 500, "Groceries",
                LocalDate.now(), LocalTime.now());

        Transaction transaction4 = new Transaction("Apple Store", 1200, "iPhone",
                LocalDate.now(), LocalTime.now());

        Transaction transaction5 = new Transaction("Target", 300, "Clothing",
                LocalDate.now(), LocalTime.now());

        Transaction transaction6 = new Transaction("Amazon", 2000, "Personal Computer",
                LocalDate.now(), LocalTime.now());

        savingsTransactionList.add(transaction1);
        savingsTransactionList.add(transaction2);
        savingsTransactionList.add(transaction3);
        savingsTransactionList.add(transaction4);
        savingsTransactionList.add(transaction5);
        savingsTransactionList.add(transaction6);

        // Create an Account and add it to the accounts list
        Account account = new Account("Savings", 5000, savingsTransactionList, true, LocalDateTime.now(), LocalDateTime.now());
        accounts.add(account);
    }

    @Test
    public void CreatePerson() {

        /**
         *     private UUID id;
         *     private String name;
         *     private int age;
         *     private String email;
         *     private String password;
         *     private String phoneNumber;
         *     private LocalDate dateOfBirth;
         *     private Address address;
         *     private List<Account> accounts;
         */

        Address address = new Address("242 W 41st St", "New York", "NY", "10036");
        LocalDate dateOfBirth = LocalDate.parse("2000-09-07");

        Person person = new Person("Yiming", 24, "Yiming.Gao@gmail.com", "123456789", "917-907-3991", dateOfBirth, address, accounts, Role.USER);

        assertNotNull(person);
        assertEquals("Yiming", person.getName());
        assertEquals(24, person.getAge());
        assertEquals("Yiming.Gao@gmail.com", person.getEmail());
        assertEquals("123456789", person.getPassword());
        assertEquals("917-907-3991", person.getPhoneNumber());
        assertEquals(dateOfBirth, person.getDateOfBirth());
        assertEquals(address, person.getAddress());
        assertEquals(1, person.getAccounts().size());
        assertEquals("Savings", person.getAccounts().get(0).getAccountType());
        assertEquals(Role.USER, person.getRole());
    }
}

package pluralsight.model;


import com.pluralsight.model.Account;
import com.pluralsight.model.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AccountTest {


   List<Transaction> transactionList;

   @Before
   public void initializingTransaction() {
      transactionList = new ArrayList<>();
      Transaction transaction1 = new Transaction("Amazon", 2000, "Personal Computer",
              LocalDate.now(), LocalTime.now(),
              LocalDate.now(), LocalTime.now());

      Transaction transaction2 = new Transaction("Best Buy", 1500, "Laptop",
              LocalDate.now(), LocalTime.now(),
              LocalDate.now(), LocalTime.now());

      Transaction transaction3 = new Transaction("Walmart", 500, "Groceries",
              LocalDate.now(), LocalTime.now(),
              LocalDate.now(), LocalTime.now());

      Transaction transaction4 = new Transaction("Apple Store", 1200, "iPhone",
              LocalDate.now(), LocalTime.now(),
              LocalDate.now(), LocalTime.now());

      Transaction transaction5 = new Transaction("Target", 300, "Clothing",
              LocalDate.now(), LocalTime.now(),
              LocalDate.now(), LocalTime.now());
      Transaction transaction6 = new Transaction("Amazon", 2000, "Personal Computer",
              LocalDate.now(), LocalTime.now(),
              LocalDate.now(), LocalTime.now());

      transactionList.add(transaction1);
      transactionList.add(transaction2);
      transactionList.add(transaction3);
      transactionList.add(transaction4);
      transactionList.add(transaction5);
      transactionList.add(transaction6);
   }

   @Test
   public void creatAccount() {

      /**
       private UUID id;
       private double balance;
       private String accountType;
       private boolean isActive;
       private List<Transaction> account;
       private LocalDateTime createdTime;
       private LocalDateTime updatedTime;
       */
      Account account = new Account("Savings", 5000,  transactionList, true, LocalDateTime.now(), LocalDateTime.now());

      System.out.println(account);
      assertNotNull(account);
      assertEquals("Savings", account.getAccountType());
      assertEquals(5000, account.getBalance(), 0.01);
      assertTrue(account.isActive());
      assertEquals(6, account.getTransactionList().size());
   }
}

package pluralsight.model;

import com.pluralsight.model.Transaction;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TransactionTest {



    @Test
    public void createTransaction() {

        /**
            private UUID id;
            private String vendor;
            private double amount;
            private String description;
            private LocalDate createDate;
            private LocalTime createTime;
            private LocalDate updatedDate;
            private LocalTime updatedTime;
            private static int numberOfTransactions;

         */
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

        assertNotNull(transaction6);
        assertEquals("Amazon", transaction6.getVendor());
        assertEquals(2000, transaction6.getAmount(), 0.01);
        assertEquals("Personal Computer", transaction6.getDescription());
        assertEquals(LocalDate.now(), transaction6.getCreateDate());
        assertEquals(LocalTime.now().getHour(), transaction6.getCreateTime().getHour());
        System.out.println(transaction6);
    }
}

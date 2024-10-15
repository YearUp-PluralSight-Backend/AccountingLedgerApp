package pluralsight.model;

import com.pluralsight.model.Address;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Testing the Address
 */
public class AddressTest {


    @Test
    public void createAddress() {

        /*
            private String street;
            private String city;
            private String state;
            private String zipCode;
         */

        Address address = new Address("242 W 41st St", "New York", "NY", "10036");
        assertNotNull(address);
        assertEquals("242 W 41st St", address.getStreet());
        assertEquals("New York", address.getCity());
        assertEquals("NY", address.getState());
        assertEquals("10036", address.getZipCode());
        System.out.println(address);

    }


}

package se.kth.iv1350.integration;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

public class CustomerRegistryTest {
    private CustomerRegistry registry;
    private String phoneNumber;
    private String name;
    private String nonExistingPhoneNumber;

    @Before
    public void setUp() {
        registry = new CustomerRegistry();

        phoneNumber = "1234";
        name = "Gustaf";
        nonExistingPhoneNumber = "0000";
    }

    @After
    public void tearDown() {
        registry = null;

        phoneNumber = null;
        name = null;
        nonExistingPhoneNumber = null;
    }

    @Test
    public void testFindCustomerReturnsMatchingCustomer(){
        CustomerDTO foundCustomer = registry.findCustomer(phoneNumber);

        assertNotNull("Customer should be found", foundCustomer);
        assertEquals("The phone number does not match the expected phone number.", phoneNumber, foundCustomer.getPhoneNumber());
        assertEquals("The name does not match the expected name.", name, foundCustomer.getName());
    }

    @Test
    public void testFIndCustomerReturnsNullWhenCustomerDoesNotExist() {
        CustomerDTO foundCustomer = registry.findCustomer(nonExistingPhoneNumber);

        assertNull("Method should return null for a phone number not in the system.", foundCustomer);
    }
}

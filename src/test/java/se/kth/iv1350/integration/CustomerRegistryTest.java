package se.kth.iv1350.integration;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import se.kth.iv1350.integration.exceptions.DatabaseFailureException;
import se.kth.iv1350.integration.exceptions.UserNotFoundException;

public class CustomerRegistryTest {
    private CustomerRegistry registry;
    private String phoneNumber;
    private String name;
    private String nonExistingPhoneNumber;
    private String phoneNumberTriggersDatabaseFailureException;

    @Before
    public void setUp() {
        registry = new CustomerRegistry();
        phoneNumber = "1234";
        name = "Gustaf";
        nonExistingPhoneNumber = "1223";
        phoneNumberTriggersDatabaseFailureException = "0000";

    }
    @After
    public void tearDown() {
        registry = null;
        phoneNumber = null;
        name = null;
        nonExistingPhoneNumber = null;
        phoneNumberTriggersDatabaseFailureException = null;
    }

    @Test
    public void testFindCustomerReturnsMatchingCustomer() throws UserNotFoundException {
        CustomerDTO foundCustomer = registry.findCustomer(phoneNumber);

        assertNotNull("Customer should be found", foundCustomer);
        assertEquals("The phone number does not match the expected phone number.", phoneNumber, foundCustomer.getPhoneNumber());
        assertEquals("The name does not match the expected name.", name, foundCustomer.getName());
    }
    @Test
    public void testFindCustomerWhenUserNotFound() {
        try {
            registry.findCustomer(nonExistingPhoneNumber);
            fail("Should have thrown UserNotFoundException, but didn't.");
        } catch (UserNotFoundException e) {
            assertTrue("Wrong exception message, does not contain specified phonenumber.", e.getMessage().contains(nonExistingPhoneNumber));
        }
    }

    @Test
    public void testFindCustomerThrowsDatabaseFailure() {
        try {
            registry.findCustomer(phoneNumberTriggersDatabaseFailureException);
            fail("Should have thrown DatabaseFailureException, but didn't.");
        } catch (DatabaseFailureException e) {
            String expectedMessage = "Could not reach database.";
            assertEquals("Error message in DatabaseFailureException does not match.", 
                     expectedMessage, e.getMessage());
        } catch (UserNotFoundException e) {
            fail("Threw UserNotFoundException instead of DatabaseFailureException.");
        }
    }
}
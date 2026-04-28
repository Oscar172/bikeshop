package se.kth.iv1350.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class CustomerRegistryTest {

    @Test
    public void testFindCustomerReturnsMatchingCustomer(){
        CustomerRegistry customerRegistry = new CustomerRegistry();

        CustomerDTO firstCustomer = customerRegistry.findCustomer("1234");
        CustomerDTO secondCustomer = customerRegistry.findCustomer("5678");

        assertNotNull(firstCustomer);
        assertEquals("1234", firstCustomer.getPhoneNumber());
        assertEquals("Gustaf", firstCustomer.getName());

        assertNotNull(secondCustomer);
        assertEquals("5678", secondCustomer.getPhoneNumber());
        assertEquals("Tova", secondCustomer.getName());
    }

    @Test
    public void testFindCustomerReturnsNullWhenCusomterDoesNotExist(){
        CustomerRegistry customerRegistry = new CustomerRegistry();

        CustomerDTO foundCustomer = customerRegistry.findCustomer("1111");

        assertNull(foundCustomer);
    }
}

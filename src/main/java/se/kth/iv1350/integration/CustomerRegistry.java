package se.kth.iv1350.integration;

import java.util.ArrayList;
import java.util.List;


/**
 * Represents a customer registry and conatains logic for searching for customers.
 */
public class CustomerRegistry {
    private List<CustomerDTO> customers = new ArrayList<>();

    /**
     * Creates a new CustomerReigstry and initializes it with existing customers.
     */
    public CustomerRegistry() {
        customers.add(new CustomerDTO("1234", "Gustaf"));
        customers.add(new CustomerDTO("5678", "Tova"));
    }
  
    /**
     * Retrieves information about an existing customer based on their phonenumber.
     * 
     * @param phoneNumber The customer's phone number.
     * @return The matching customer, or null if no customer is found.
     */
    public CustomerDTO findCustomer(String phoneNumber) {
        for (CustomerDTO customer : customers) {
            if(customer.getPhoneNumber().equals(phoneNumber)) {
                return customer;
            }
        }
        return null;
    }

}


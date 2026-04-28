package se.kth.iv1350.integration;

import java.util.ArrayList;
import java.util.List;


/**
 * Represents a register of customers and contains the logic
 * to search for customers in the system.
 */
public class CustomerRegistry {
    private List<CustomerDTO> customers = new ArrayList<>();

    /**
     * Initiates the class
     */
    public CustomerRegistry() {
        // Example, add a customer here to be able to run fake execution in view
        // Add code that run when the object is created
        // For example, initiate a list of customers or create a DTO.
        customers.add(new CustomerDTO("1234", "Gustaf"));
        customers.add(new CustomerDTO("5678", "Tova"));
    }
  
    /**
     * Retrieves information about an existing customer based on their phonenumber.
     * @param phoneNumber The customers phonenumber.
     * @return The matching customer found in the registry.
     */
    public CustomerDTO findCustomer(String phoneNumber) {
        for (CustomerDTO customer : customers) {
            if(customer.getPhoneNumber().equals(phoneNumber)) {
                return customer;
            }
        }
        return null; // Alternativ flow?
    }

}


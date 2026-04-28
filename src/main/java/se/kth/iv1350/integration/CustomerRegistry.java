package se.kth.iv1350.integration;

import java.util.ArrayList;
import java.util.List;


/**
 * Represents a register of customers and contains the logic
 * to search for customers in the system.
 */
public class CustomerRegistry {
    private List<CustomerDTO> customers = new ArrayList<>();

    public CustomerRegistry(){
        customers.add(new CustomerDTO("0763252275", "Oscar"));
        customers.add(new CustomerDTO("0701111111", "Gustav"));
        customers.add(new CustomerDTO("0702222222", "Tova"));
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

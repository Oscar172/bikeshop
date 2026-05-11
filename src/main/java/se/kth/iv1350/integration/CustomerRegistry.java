package se.kth.iv1350.integration;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.integration.exceptions.UserNotFoundException;
import se.kth.iv1350.integration.exceptions.DatabaseFailureException;
import se.kth.iv1350.util.Logger;

/**
 * Represents a customer registry and contains logic for searching for customers.
 */
public class CustomerRegistry{
    private List<CustomerDTO> customers = new ArrayList<>();
    private final Logger logger;

    /**
     * Creates a new CustomerReigstry and initializes it with existing customers.
     */
    public CustomerRegistry(Logger logger) {
        this.logger = logger;
        customers.add(new CustomerDTO("1234", "Gustaf"));
        customers.add(new CustomerDTO("5678", "Tova"));
    }

    /**
     * Retrieves information about an existing customer based on their phonenumber.
     * @param phoneNumber The customer's phone number.
     * @return Information about the customer in the form of a CustomerDTO.
     * @throws UserNotFoundException if no customer with the specified phone
     * number can be found in the customer registry.
     * @throws DatabaseFailureException if the database can not be reached.
     */
    public CustomerDTO findCustomer(String phoneNumber) 
                throws UserNotFoundException, DatabaseFailureException {
        
        if (phoneNumber.equals("0000")) {
            logger.log("DatabaseFailureException: Could not reach database.");
            throw new DatabaseFailureException("Could not reach database.");
        }
        for (CustomerDTO customer : customers) {
            if(customer.getPhoneNumber().equals(phoneNumber)) {
                return customer;
            }
        }
        throw new UserNotFoundException("No customer was found under this phonenumber: " + phoneNumber);
    }

}


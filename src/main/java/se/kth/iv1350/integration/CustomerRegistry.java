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

    private static final CustomerRegistry INSTANCE = new CustomerRegistry();

    private Logger logger;

    private List<CustomerDTO> customers = new ArrayList<>();

    /**
     * Creates a new CustomerRegistry and initializes it with existing customers.
     */
    private CustomerRegistry() {
        customers.add(new CustomerDTO("1234", "Gustaf"));
        customers.add(new CustomerDTO("5678", "Tova"));
    }

    /**
     * Method for access to CustomerRegistry due to Singleton GoF.
     * @return INSTANCE of CustomerRegistry.
     */
    public static CustomerRegistry getCustomerRegistry(){
        return INSTANCE;
    }

    public void setLogger (Logger logger){
        this.logger = logger;
    }

    /**
     * Retrieves information about an existing customer based on their phonenumber.
     * To avoid NPE an if-statement is used for the logger call.
     * @param phoneNumber The customer's phone number.
     * @return Information about the customer in the form of a CustomerDTO.
     * @throws UserNotFoundException if no customer with the specified phone
     * number can be found in the customer registry.
     * @throws DatabaseFailureException if the database can not be reached.
     */
    public CustomerDTO findCustomer(String phoneNumber) 
                throws UserNotFoundException, DatabaseFailureException {
        
        if (phoneNumber.equals("0000")) {
            if (logger != null) {
                logger.log("Database not accessable occured when customer searched for" + phoneNumber);
            }
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


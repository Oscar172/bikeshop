package se.kth.iv1350.integration;

import se.kth.iv1350.integration.CustomerDTO;

public class CustomerRegistry {

    //<<create>> CustomerRegistry():CustomerRegistry
    public static CustomerRegistry customerRegistry(){
        return new CustomerRegistry();
    }

    CustomerDTO customerDTO = CustomerDTO.createCustomerDTO();
   
    /**
     * Searches for a specific customer in the customer registry
     * @param phoneNumber The customers phone number
     * @return Returns the searched for customers CustomerDTO
     */
    public CustomerDTO searchCustomer(String phoneNumber){
        return new CustomerDTO(phoneNumber, "Unknown customer");
    }

   

     public void getCustomerDTO(CustomerDTO customer){

     }
}

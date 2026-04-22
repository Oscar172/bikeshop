package se.kth.iv1350.integration;

import se.kth.iv1350.dto.CustomerDTO;

public class CustomerRegistry {

    //<<create>> CustomerRegistry():CustomerRegistry
    public static CustomerRegistry customerRegistry(){
        return new CustomerRegistry();
    }

    //findCustomer(phoneNumber : String) : CustomerDTO
    public CustomerDTO findCustomer(String phoneNumber){
        return new CustomerDTO(phoneNumber);
    }





}

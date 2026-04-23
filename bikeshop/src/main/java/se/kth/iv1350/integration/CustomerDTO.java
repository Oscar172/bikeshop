package se.kth.iv1350.integration;
import se.kth.iv1350.integration.CustomerRegistry;

public class CustomerDTO {
    //skapar CustomerDTO-class
    public static CustomerDTO createCustomerDTO(){
        return new CustomerDTO();
    }

    //nollställer referensen? typ
    public CustomerDTO() {}

    private String phoneNumber;
    private String name;

        
    public CustomerDTO (String phoneNumber, String name){
        this.phoneNumber = phoneNumber;
        this.name = name;
    }

    //getters
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public String getName(){
        return name;
    }

    public String customerEx(String phoneNumber){
        return phoneNumber;
    }

    public String customerEx2(String phoneNumber){
        return phoneNumber;
    }

    public String customerEx3(String phoneNumber){
        return phoneNumber;
    }
}

package se.kth.iv1350.dto;

public class CustomerDTO {

    private String phoneNumber;
    private String name;

    public CustomerDTO(String phoneNumber, String name){
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

    public String customerBajs(String phoneNumber){
        return phoneNumber;
    }
    

    // detta är ett test att pusha.


}

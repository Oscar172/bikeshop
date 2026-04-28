package se.kth.iv1350.integration;

public class CustomerDTO {
    
    private String phoneNumber;
    private String name;

    
    /**
     *  Structure of the object.
     * @param phoneNumber the customers phonenumber.
     * @param name  the name of the customer.
     */
    public CustomerDTO (String phoneNumber, String name){
        this.phoneNumber = phoneNumber;
        this.name = name;
    }

    /**
     * Getters below
     */
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public String getName(){
        return name;
    }
}

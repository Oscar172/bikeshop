package se.kth.iv1350.integration;

public class CustomerDTO {
    
     /**
     * Constructs CustomerDTO class
     * @return reference to the class
     */
    public static CustomerDTO createCustomerDTO(){
        return new CustomerDTO();
    }

    //nollställer referensen? typ
    public CustomerDTO() {}

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

    /**
     * 
     * Hardcoded examples of CustomerDTOs
     */
    public String customerEx(String phoneNumber){
        return phoneNumber;
    }

    public String customerEx2(String phoneNumber){
        return phoneNumber;
    }

    public String customerEx3(String phoneNumber){
        return phoneNumber;
    }

    public String customerEx4(String phoneNumber){
        return phoneNumber;
    }
}

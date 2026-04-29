package se.kth.iv1350.integration;

public class CustomerDTO {
    private final String phoneNumber;
    private final String name;

    /**
     * Creates a new CustomerDTO containing customer information.
     * 
     * @param phoneNumber The customer's phone number.
     * @param name The name of the customer.
     */
    public CustomerDTO (String phoneNumber, String name){
        this.phoneNumber = phoneNumber;
        this.name = name;
    }

    /**
     * Returns the customer's phone number.
     * 
     * @return The customer's phone number.
     */
    public String getPhoneNumber(){
        return phoneNumber;
    }

    /** Returns the cusomter's name.
     * 
     * @return The customer's name.
     */
    public String getName(){
        return name;
    }
}

package se.kth.iv1350.integration;

public class CustomerDTO {

    private String phoneNumber;
    private String name;

    public CustomerDTO(String phoneNumber, String name){
        this.phoneNumber = phoneNumber;
        this.name = name;
    }

    //getters

    /**
     * A getter that returns a customers phonenumber.
     * @return The customers phonenumber.
     */
    public String getPhoneNumber(){
        return phoneNumber;
    }

    /**
     * A getter that returns a customers name.
     * @return The customers name.
     */
    public String getName(){
        return name;
    }
}

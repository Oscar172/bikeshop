package se.kth.iv1350.view;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.dto.CustomerDTO; 

public class View {

    private Controller contr;
    public View(Controller contr) {
        this.contr = contr;
    }
    
    String phoneNumber = "0000";
    CustomerDTO foundCustomer = contr.findCustomer(phoneNumber);

    
}

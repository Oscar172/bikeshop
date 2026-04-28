package se.kth.iv1350.startup;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.integration.CustomerRegistry;
import se.kth.iv1350.integration.Printer;
import se.kth.iv1350.integration.RepairOrderRegistry;
import se.kth.iv1350.view.View;

public class Main 
{
    /**
     * main starts all the classes below.
     * @param args allows inputs.
     */
    public static void main( String[] args )
    {
        CustomerRegistry customerRegistry = new CustomerRegistry();
        RepairOrderRegistry repairOrderRegistry =  new RepairOrderRegistry();
        Printer printer = new Printer();

        Controller contr = new Controller(customerRegistry, repairOrderRegistry, printer);
        View view = new View(contr);
    }
}

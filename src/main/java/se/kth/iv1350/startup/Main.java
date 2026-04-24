package se.kth.iv1350.startup;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.integration.CustomerRegistry;
import se.kth.iv1350.integration.Printer;
import se.kth.iv1350.integration.RepairOrderRegistry;
import se.kth.iv1350.view.View;
import se.kth.iv1350.integration.CustomerDTO;


public class Main 
{
    /**
     * main starts all the classes below.
     * @param args allows inputs.
     */
    public static void main( String[] args )
    {
        CustomerRegistry customerRegistry = CustomerRegistry.customerRegistry();
            
        RepairOrderRegistry repairOrderRegistry = RepairOrderRegistry.repairOrderRegistry();
        Printer printer = Printer.createPrinter();

        Controller contr = new Controller(customerRegistry, repairOrderRegistry, printer);
        View view = new View(contr); //view-objekt ej klart
    }
}

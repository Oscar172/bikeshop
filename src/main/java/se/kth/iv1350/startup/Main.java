package se.kth.iv1350.startup;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.integration.CustomerRegistry;
import se.kth.iv1350.integration.RepairOrderRegistry;
import se.kth.iv1350.integration.Printer;
import se.kth.iv1350.view.View;

/**
 * Starts the application.
 */
public class Main 
{
    /**
     * Starts the entire application
     * 
     * @param args Command line arguments.
     */
    public static void main(String[] args )
    {
        CustomerRegistry customerRegistry = new CustomerRegistry();
        Printer printer = new Printer();
        RepairOrderRegistry repairOrderRegistry = new RepairOrderRegistry();

        Controller contr = new Controller(customerRegistry, repairOrderRegistry, printer);
        View view = new View(contr);
        view.runFakeExecution();
    }
}

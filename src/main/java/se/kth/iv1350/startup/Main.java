package se.kth.iv1350.startup;

import se.kth.iv1350.controller.Controller;

import se.kth.iv1350.integration.CustomerRegistry;
import se.kth.iv1350.integration.RepairOrderRegistry;
import se.kth.iv1350.integration.Printer;
import se.kth.iv1350.view.View;
import se.kth.iv1350.view.RepairOrderLogger;
import se.kth.iv1350.view.RepairOrderView;
import se.kth.iv1350.util.Logger;
import se.kth.iv1350.util.FileLogger;

/**
 * Starts the application.
 */
public class Main 
{
    /**
     * Starts the entire program.
     * 
     * @param args Command line arguments, not used.
     */
    public static void main(String[] args ){   
        Logger repairOrderFileLogger = new FileLogger("logs/repair-order-log.txt");
        Logger dataBaseFileLogger = new FileLogger("logs/database-log.txt");

        CustomerRegistry customerRegistry = new CustomerRegistry(dataBaseFileLogger);
        Printer printer = new Printer();
        RepairOrderRegistry repairOrderRegistry = new RepairOrderRegistry();

        Controller contr = new Controller(customerRegistry, repairOrderRegistry, printer);

        repairOrderRegistry.addRepairOrderObserver(new RepairOrderView());
        repairOrderRegistry.addRepairOrderObserver(new RepairOrderLogger(repairOrderFileLogger));

        View view = new View(contr, dataBaseFileLogger);
        view.runFakeExecution();
    }
}

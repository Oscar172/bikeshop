package se.kth.iv1350.view;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.integration.CustomerRegistry;
import se.kth.iv1350.integration.CustomerDTO;
import se.kth.iv1350.integration.RepairOrderDTO;
import se.kth.iv1350.integration.Printer;
import se.kth.iv1350.integration.RepairOrderRegistry;
import se.kth.iv1350.view.View;

/**
 * Simulates user interaction with the system.
 */
public class View {
    private final Controller contr;

    /**
     * Creates a new View.
     * 
     * @param contr The controller used for all system operations.
     */
    public View(Controller contr) {
        this.contr = contr;
    }
    
    /**
     * Simulates a user input that generates calls to system operations.
     */
    public void runFakeExecution() {
        String phoneNumber = "1234";
        String repairOrderId = "RO-1";

        System.out.println("----- Sample Execution -----");
        
        CustomerDTO foundCustomer = contr.searchForCustomer(phoneNumber);

        if (foundCustomer != null) {
            System.out.println("Customer found: " + foundCustomer.getName());
            System.out.println("");
        } else {
            System.out.println("No customer found on this number: " + phoneNumber);
        }

        contr.createRepairOrder("Flat tire and a broken front-light", phoneNumber, "BIKE-001");
        contr.addDiagnosticReport(repairOrderId, "Fix flat tire and other stuff");
        contr.addRepairTask(repairOrderId, "Change all brake wires", 249.90);
        contr.acceptRepairOrder(repairOrderId);
    }

}

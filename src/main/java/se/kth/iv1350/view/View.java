package se.kth.iv1350.view;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.integration.CustomerDTO;
import se.kth.iv1350.integration.RepairOrderDTO;
import se.kth.iv1350.integration.exceptions.UserNotFoundException;
import se.kth.iv1350.integration.exceptions.DatabaseFailureException;

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
        String phoneNumber = "0000";
        CustomerDTO foundCustomer = null;
        try {
            foundCustomer = contr.searchForCustomer(phoneNumber);

            System.out.println("----- Sample Execution -----");
            System.out.println("Customer found: " + foundCustomer.getName());

            contr.createRepairOrder("Flat tire and a broken front-light", phoneNumber, "BIKE-001");

            String repairOrderId = "RO-1";
            contr.addDiagnosticReport(repairOrderId, "Fix flat tire and other stuff");
            System.out.println("Diagnostic report added to: " + repairOrderId);

            contr.addRepairTask(repairOrderId, "Change all brake wires", 249.90);
            System.out.println("Repair task added to: " + repairOrderId);

            RepairOrderDTO repairOrder = contr.findRepairOrder(phoneNumber);
            if(repairOrder != null){
                System.out.println("Latest repair order: " + repairOrder.getRepairOrderId());
                System.out.println("Status: " + repairOrder.getState());
                System.out.println("Total cost: " + repairOrder.getTotalCost());
            }
            contr.acceptRepairOrder(repairOrderId);

        } catch (UserNotFoundException e) {
            System.out.println("ERROR: " + e.getMessage());
        } catch (DatabaseFailureException e) {
            System.out.println("ERROR: Something went wrong when trying to reach server. Please try again later.");
        }
    }
        
       

}

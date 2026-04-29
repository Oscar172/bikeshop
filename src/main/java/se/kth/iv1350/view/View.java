package se.kth.iv1350.view;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.integration.CustomerDTO;
import se.kth.iv1350.integration.RepairOrderDTO;

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
        String inputPhoneNumber = "0763252275";
        CustomerDTO foundCustomer = contr.searchForCustomer(inputPhoneNumber);

        if (foundCustomer != null) {
            System.out.println("Customer found: " + foundCustomer.getName());
        } else {
            System.out.println("No customer found on this number: " + inputPhoneNumber);
        }

        String repairOrderId = "123";
        String diagTaskResult = "Adjust front wheel";
        contr.addDiagnosticReport(repairOrderId, diagTaskResult);
        System.out.println("Diagnostic results has been sent to the registry for order: " + repairOrderId);

        String repairTaskDescription = "Change all brake wires";
        double cost = 249.90;
        contr.addRepairTask(repairOrderId, repairTaskDescription, cost);

        RepairOrderDTO repairOrder = contr.findRepairOrder(phoneNumber);
        RepairOrderDTO[] repairOrders = contr.findAllRepairOrders(phoneNumber); 

        // Lägg till repairORderStatus(accept/reject)
    }
}

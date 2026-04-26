package se.kth.iv1350.view;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.integration.CustomerDTO;
import se.kth.iv1350.integration.RepairOrderDTO;

public class View {

    private Controller contr;

    public View(Controller contr) {
        this.contr = contr;
    }
    

    /**
     * Sample execution of the View in order to make calls to other parts of the program.
     */
    public void sampleExecution() {
        String phoneNumber = "0000";
        String repairOrderId = "28981829";
        String repairTaskDescription = "Change all brake wires";
        double cost = 249.90;

        CustomerDTO foundCustomer = contr.findCustomer(phoneNumber);
        RepairOrderDTO repairOrder = contr.findRepairOrder(phoneNumber);
        RepairOrderDTO[] repairOrders = contr.findAllRepairOrders(phoneNumber);
        contr.addRepairTask(repairOrderId, repairTaskDescription, cost);
    }

   
}

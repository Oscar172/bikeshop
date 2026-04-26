package se.kth.iv1350.controller;

import se.kth.iv1350.integration.CustomerDTO;
import se.kth.iv1350.integration.CustomerRegistry;
import se.kth.iv1350.integration.Printer;
import se.kth.iv1350.integration.RepairOrderDTO;
import se.kth.iv1350.integration.RepairOrderRegistry;
import se.kth.iv1350.model.RepairOrder;
import se.kth.iv1350.model.RepairTask;

public class Controller {

    private CustomerRegistry customerRegistry;
    private RepairOrderRegistry repairOrderRegistry;
    private Printer printer;

    public Controller(CustomerRegistry customerRegistry,
                        RepairOrderRegistry repairOrderRegistry,
                        Printer printer){
        this.customerRegistry = customerRegistry;
        this.repairOrderRegistry = repairOrderRegistry;
        this.printer = printer;
    }

    
    
    /**
     * Searches for a specific customer in the customer registry
     * @param phoneNumber The customers phone number
     * @return Returns the searched for customers CustomerDTO
     */
    public CustomerDTO findCustomer(String phoneNumber){
        return customerRegistry.searchCustomer(phoneNumber);
    }

    public RepairOrderDTO createRepairOrder(String problemDescr, String phoneNumber, String bikeSerialNumber){
        RepairOrder repairOrder = RepairOrder.createRepairOrder(problemDescr, phoneNumber, bikeSerialNumber, repairOrderRegistry);
        return repairOrder.createRepairOrderDTO();
    }

    public RepairOrderDTO[] findAllRepairOrders(String phoneNumber){
        return repairOrderRegistry.findAllRepairOrders(phoneNumber);
    }

    public void addDiagnosticReport(String repairOrderId, String diagTaskResult){
        repairOrderRegistry.addDiagnosticReport(repairOrderId, diagTaskResult);
    }
    

    /**
     * Creates a RepairTask object from the View inputs and adds it to an existing RepairOrder.
     * @param repairOrderId The identifer of the RepairOrder to modify.
     * @param taskDescription The description of the proposed repair task.
     * @param cost  The cost of the proposed repair task.
     */
    public void addRepairTask(String repairOrderId, String repairTaskDescription, double cost){
        RepairTask newTask = RepairTask.createRepairTask(repairTaskDescription, cost);
        
        //RepairOrderDTO dto = repairOrderRegistry.getRepairOrderByRepairOrderId(repairOrderId);

        RepairOrder.addRepairTaskToRepairOrder(repairOrderId, newTask, repairOrderRegistry); //anropa repairORder class för att lägga till
    }

    public RepairOrderDTO findRepairOrder(String phoneNumber){
        return repairOrderRegistry.findRepairOrder(phoneNumber);
    }

    public void acceptRepairOrder(String repairOrderId){
        repairOrderRegistry.acceptRepairOrder(repairOrderId);
    }

    public void rejectRepairOrder(String repairOrderId){
        repairOrderRegistry.rejectRepairOrder(repairOrderId);
    }
}

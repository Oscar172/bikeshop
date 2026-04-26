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

    public Controller(CustomerRegistry customerRegistry, RepairOrderRegistry repairOrderRegistry, Printer printer){
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

    /**
     * Adds a diagnostic result to a specified report.
     * @param repairOrderId Identifier of the repairOrder.
     * @param diagTaskResult Result of the diagnostic.
     */
    public void addDiagnosticReport(String repairOrderId, String diagTaskResult){
        
        RepairOrderDTO dto = repairOrderRegistry.getRepairOrderByRepairOrderId(repairOrderId); //gets the DTO from registry
        RepairOrder repairOrder = new RepairOrder(dto, repairOrderRegistry); //anropa repairORder class för att lägga till
        repairOrder.addDiagnosticReport(diagTaskResult);
    }   
    

    /**
     * Creates a RepairTask object from the View inputs and adds it to an existing RepairOrder.
     * @param repairOrderId The identifer of the RepairOrder to modify.
     * @param repairTaskDescription The description of the proposed repair task.
     * @param cost  The cost of the proposed repair task.
     */
    public void addRepairTask(String repairOrderId, String repairTaskDescription, double cost){
        RepairOrderDTO dto = repairOrderRegistry.getRepairOrderByRepairOrderId(repairOrderId); //gets the DTO from registry
        
        RepairOrder repairOrder = new RepairOrder(dto, repairOrderRegistry); //anropa repairORder class för att lägga till
        RepairTask newTask = RepairTask.createRepairTask(repairTaskDescription, cost);
        repairOrder.addRepairTask(newTask); //adds the task to teh report
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

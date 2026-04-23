package se.kth.iv1350.controller;

import se.kth.iv1350.integration.CustomerDTO;
import se.kth.iv1350.integration.RepairOrderDTO;
import se.kth.iv1350.integration.CustomerRegistry;
import se.kth.iv1350.integration.Printer;
import se.kth.iv1350.integration.RepairOrderRegistry;

//skapa getters till RepairDTO och CustomerDTO här.

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
        return repairOrderRegistry.createRepairOrder(problemDescr, phoneNumber, bikeSerialNumber);
    }

    public RepairOrderDTO[] findAllRepairOrders(String phoneNumber){
        RepairOrderDTO[] repairOrders = repairOrderRegistry.findAllRepairOrders(phoneNumber);
        return repairOrders;
    }

    public void addDiagnosticReport(String repairOrderId, String diagTaskResult){
        repairOrderRegistry.addDiagnosticReport(repairOrderId, diagTaskResult);
    }

    public void addRepairTask(String repairOrderId, String repairTask, double cost){
        repairOrderRegistry.addRepairTask(repairOrderId, repairTask, cost);
    }

    public RepairOrderDTO findRepairOrder(String phoneNumber){
        RepairOrderDTO repairOrder = repairOrderRegistry.findRepairOrder(phoneNumber);
        return repairOrder;
    }

    public void acceptRepairOrder(String repairOrderId){
        repairOrderRegistry.acceptRepairOrder(repairOrderId);
    }

    public void rejectRepairOrder(String repairOrderId){
        repairOrderRegistry.rejectRepairOrder(repairOrderId);
    }







}

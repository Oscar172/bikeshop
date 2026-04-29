package se.kth.iv1350.controller;

import se.kth.iv1350.integration.CustomerDTO;
import se.kth.iv1350.integration.CustomerRegistry;
import se.kth.iv1350.integration.Printer;
import se.kth.iv1350.integration.RepairOrderDTO;
import se.kth.iv1350.integration.RepairOrderRegistry;
import se.kth.iv1350.model.RepairOrder;
import se.kth.iv1350.model.RepairTask;

/**
 * The applications controller. All calls from view pass through here
 */
public class Controller {

    private final CustomerRegistry customerRegistry;
    private final RepairOrderRegistry repairOrderRegistry;
    private final Printer printer;

    /**
    * Creates a new instance of Controller.
    * 
    * @param customerReigstry Reference to the customer Registry in the integration layer.
    * @param repairOrderRegistry Reference to the repair order registry in the integration layer.
    * @param printer Refernce to the printer in the integration layer. 
    */
    public Controller(CustomerRegistry customerRegistry,
                        RepairOrderRegistry repairOrderRegistry,
                        Printer printer){
        this.customerRegistry = customerRegistry;
        this.repairOrderRegistry = repairOrderRegistry;
        this.printer = printer;
    }

    /**
     * Searchers for a specific customer by phone number.
     * @param phoneNumber The phone number used to search for the cusomter.
     * @return Information about the customer in the form of a CustomerDTO.
     */
    public CustomerDTO searchForCustomer(String phoneNumber){
        return customerRegistry.findCustomer(phoneNumber);
    }

    /**
     * Creates a new repair order.
     * 
     * @param problemDescr Description of the bike's problem.
     * @param phoneNumber The customer's phone number.
     * @param bikeSerialNumber The bike's serial number.
     */
    public void createRepairOrder(String problemDescr, String phoneNumber, String bikeSerialNumber){
        RepairOrder.createRepairOrder(problemDescr, phoneNumber, bikeSerialNumber, repairOrderRegistry);
    }

    /**
     * Finds all repair orders connected to a specific phone number.
     * 
     * @param phoneNumber The customer's phone number.
     * @return All matching repair orders as an array of RepairOrderDTO.
     */
    public RepairOrderDTO[] findAllRepairOrders(String phoneNumber){
        return repairOrderRegistry.findAllRepairOrders(phoneNumber);
    }

    /**
     * Adds a diagnostic report to an existing repair order.
     * 
     * @param repairOrderId  The id of the repair order to update
     * @param diagTaskResult The result of the diagnostic task.
     */
    public void addDiagnosticReport(String repairOrderId, String diagTaskResult){
        repairOrderRegistry.addDiagnosticReport(repairOrderId, diagTaskResult);
    }

    /**
     * Creates a repair task object from the View inputs and adds it to an existing repair order.
     *  
     * @param repairOrderId The identifer of the RepairOrder to update.
     * @param repairTaskDescription The description of the repair task.
     * @param cost  The cost of the proposed repair task.
     */
    public void addRepairTask(String repairOrderId, String repairTaskDescription, double cost){
        RepairTask newTask = RepairTask.createRepairTask(repairTaskDescription, cost);
        repairOrderRegistry.addRepairTask(repairOrderId, newTask);
    }

    /**
     * Finds the latest reapir order for a specific customer. 
     * 
     * @param phoneNumber The cusomter's phone number.
     * @return The latest matching repair order as a RepairOrderDTO.
     */
    public RepairOrderDTO findRepairOrder(String phoneNumber){
        return repairOrderRegistry.findRepairOrder(phoneNumber);
    }

    /**
     * Accepts a repair order and prints it.
     * 
     * @param repairOrderId The id of the repair order to accept.
     */
    public void acceptRepairOrder(String repairOrderId){
        RepairOrder repairOrder = repairOrderRegistry.acceptRepairOrder(repairOrderId);
        if(repairOrder != null){
            printer.printRepairOrder(repairOrder);
        }
    }

    /**
     * Rejects a repair order.
     * 
     * @param repairOrderId The id of the reapir order to reject.
     */
    public void rejectRepairOrder(String repairOrderId){
        repairOrderRegistry.rejectRepairOrder(repairOrderId);
    }
}

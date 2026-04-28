package se.kth.iv1350.controller;

import se.kth.iv1350.integration.CustomerDTO;
import se.kth.iv1350.integration.CustomerRegistry;
import se.kth.iv1350.integration.Printer;
import se.kth.iv1350.integration.RepairOrderDTO;
import se.kth.iv1350.integration.RepairOrderRegistry;
import se.kth.iv1350.model.RepairOrder;
import se.kth.iv1350.model.RepairTask;



/**
 * The applications controller. All calls from view pass through here.
 */
public class Controller {

    private CustomerRegistry customerRegistry;
    private RepairOrderRegistry repairOrderRegistry;
    private Printer printer;

    /**
     * Creates a new instanse of Controller. (Constructor)
     * @param customerRegistry Reference to the cutsumer registry in the
     * integration layer.
     * @param repairOrderRegistry Reference to the order registry in the
     * integration layer.
     * @param printer Reference to the printer in the integrations layer.
     */
    public Controller(CustomerRegistry customerRegistry,
                        RepairOrderRegistry repairOrderRegistry,
                        Printer printer){
        this.customerRegistry = customerRegistry;
        this.repairOrderRegistry = repairOrderRegistry;
        this.printer = printer;

    }

    /**
     * Searchers for a specific customer by their phonenumber.
     * @param phoneNumber The phonenumber the search is based on.
     * @return Information about the customer in the form of a CustomerDTO.
     */
    public CustomerDTO searchForCustomer(String phoneNumber){
        return customerRegistry.findCustomer(phoneNumber);
    }

    public void createRepairOrder(String problemDescr, String phoneNumber, String bikeSerialNumber,  RepairOrderRegistry repairOrderRegistry){
        RepairOrder.createRepairOrder(problemDescr, phoneNumber, bikeSerialNumber, repairOrderRegistry);
    }

    public RepairOrderDTO[] findAllRepairOrders(String phoneNumber){
        return repairOrderRegistry.findAllRepairOrders(phoneNumber);
    }

    /**
     * Adds a diagnostic report to an existing repair order.
     * @param repairOrderId  The id of the order to add a diagnostic report to.
     * @param diagTaskResult The result of the diagnose.
     */
    public void addDiagnosticReport(String repairOrderId, String diagTaskResult){
        repairOrderRegistry.addDiagnosticReport(repairOrderId, diagTaskResult);
    }

    /**
     * Creates a RepairTask object from the View inputs and adds it to an existing RepairOrder.
     * @param repairOrderId The identifer of the RepairOrder to modify.
     * @param repairTaskDescription The description of the proposed repair task.
     * @param cost  The cost of the proposed repair task.
     */
    public void addRepairTask(String repairOrderId, String repairTaskDescription, double cost){
        
        RepairTask newTask = RepairTask.createRepairTask(repairTaskDescription, cost);

        repairOrderRegistry.addRepairTask(repairOrderId, newTask);
    }

    public RepairOrderDTO findRepairOrder(String phoneNumber){
        return repairOrderRegistry.findRepairOrder(phoneNumber);
    }

    public void acceptRepairOrder(String repairOrderId){
        RepairOrder repairOrder = repairOrderRegistry.acceptRepairOrder(repairOrderId);
        printer.printRepairOrder(repairOrder);
    }

    public void rejectRepairOrder(String repairOrderId){
        repairOrderRegistry.rejectRepairOrder(repairOrderId);
    }
}

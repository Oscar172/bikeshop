package se.kth.iv1350.controller;

import se.kth.iv1350.integration.CustomerDTO;
import se.kth.iv1350.integration.CustomerRegistry;
import se.kth.iv1350.integration.Printer;
import se.kth.iv1350.integration.RepairOrderDTO;
import se.kth.iv1350.integration.RepairOrderRegistry;
import se.kth.iv1350.integration.exceptions.DatabaseFailureException;
import se.kth.iv1350.integration.exceptions.UserNotFoundException;
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
     * @throws UserNotFoundException if no customer with the specified phone
     * number can be found in the customer registry.
     */
    public CustomerDTO searchForCustomer(String phoneNumber) throws UserNotFoundException, DatabaseFailureException {
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
        RepairOrder newOrder = RepairOrder.createRepairOrder(problemDescr, phoneNumber, bikeSerialNumber, repairOrderRegistry);
        this.updateRepairOrder(newOrder);
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
     * @param repairOrderId The id of the reapir order to update.
     * @param diagTaskResult The result of the diagnostic task.
     */
    public void addDiagnosticReport(String repairOrderId, String diagTaskResult){
        RepairOrder repairOrder = repairOrderRegistry.findRepairOrderById(repairOrderId);
        if(repairOrder != null){
            repairOrder.addDiagnosticReport(diagTaskResult);
            updateRepairOrder(repairOrder);
        }
    }

    /**
     * Adds a repair task to an existing repair order.
     * 
     * @param repairOrderId The id of the repair order to update.
     * @param repairTask The repair task to add.
     */
    public void addRepairTask(String repairOrderId, String repairTaskDescription, double cost){
        RepairOrder repairOrder = repairOrderRegistry.findRepairOrderById(repairOrderId);
        RepairTask newTask = RepairTask.createRepairTask(repairTaskDescription, cost);
        if(repairOrder != null){
            repairOrder.addRepairTask(newTask);
            updateRepairOrder(repairOrder);
        }
    }

    /**
     * Saves a new repair order or updates an exisitng one.
     * 
     * @param repairOrder The specific order to save.
     */
    public void updateRepairOrder(RepairOrder updatedOrder){
        RepairOrder[] orders = repairOrderRegistry.getRepairOrders();
        int countRepairOrders = repairOrderRegistry.getNrOfRepairOrders();
        String idOfUpdatedOrder = updatedOrder.getRepairOrderId();

        for (int i = 0; i < countRepairOrders; i++) {
            if (orders[i].getRepairOrderId().equals(idOfUpdatedOrder)) {
                orders[i] = updatedOrder;
                repairOrderRegistry.notifyObservers(updatedOrder);
                return;
            }
        }
        orders[countRepairOrders] = updatedOrder;
        repairOrderRegistry.incrementNrOfRepairOrders();
        repairOrderRegistry.notifyObservers(updatedOrder);
    }

    /**
     * Accepts a repair order.
     * 
     * @param repairOrderId The id of the repair order.
     * @return The accepted repair order, or null if no matching order is found.
     */
    public RepairOrder acceptRepairOrder(String repairOrderId) {
        RepairOrder repairOrder = repairOrderRegistry.findRepairOrderById(repairOrderId);
        if(repairOrder != null){
            repairOrder.accept();
            this.updateRepairOrder(repairOrder);
            return repairOrder;
        }
        return null;
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
    /* 
    public void acceptRepairOrder(String repairOrderId){
        RepairOrder repairOrder = repairOrderRegistry.acceptRepairOrder(repairOrderId);
        if(repairOrder != null){
            printer.printRepairOrder(repairOrder);
        }
    }*/

    /**
     * Rejects a repair order.
     * 
     * @param repairOrderId The id of the reapir order to reject.
     */
    public void rejectRepairOrder(String repairOrderId){
        repairOrderRegistry.rejectRepairOrder(repairOrderId);
    }
}

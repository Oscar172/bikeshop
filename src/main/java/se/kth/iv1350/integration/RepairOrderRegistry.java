package se.kth.iv1350.integration;

import se.kth.iv1350.model.RepairOrder;
import se.kth.iv1350.model.RepairTask;

/**
 * Represents a registry of repair orders and contains logic for storing
 * and retrieving repair orders.
 */
public class RepairOrderRegistry {

    private final RepairOrder[] repairOrders;
    private int nrOfRepairOrders;
    
    /**
     * Creates a new RepairOrderRegistry.
     */
    public RepairOrderRegistry(){
        this.repairOrders = new RepairOrder[100];
        this.nrOfRepairOrders = 0;
    }

    /**
     * Generates a new repair order id.
     * 
     * @return A unique repair order id.
     */
    public String generateRepairOrderId(){
        return "Repair Order: " + (nrOfRepairOrders + 1);
    }

    /**
     * Finds all repair orders connected to a specific phone number.
     * 
     * @param phoneNumber The customer's phone number.
     * @return An array containing all matching repair orders.
     */
    public RepairOrderDTO[] findAllRepairOrders(String phoneNumber){
        RepairOrderDTO[] foundRepairOrders = new RepairOrderDTO[nrOfRepairOrders];
        int nrOfFoundRepairOrders = 0;

        for(int i = 0; i < nrOfRepairOrders; i++){
            if(repairOrders[i].getPhoneNumber().equals(phoneNumber)){
                foundRepairOrders[nrOfFoundRepairOrders] = repairOrders[i].createRepairOrderDTO();
                nrOfFoundRepairOrders++;
            }
        }
        RepairOrderDTO[] repairOrders = new RepairOrderDTO[nrOfFoundRepairOrders];
        for(int i = 0; i < nrOfFoundRepairOrders; i++){
            repairOrders[i] = foundRepairOrders[i];
        }

        return repairOrders;
    }

    /**
     * Adds a diagnostic report to an existing repair order.
     * 
     * @param repairOrderId The id of the reapir order to update.
     * @param diagTaskResult The result of the diagnostic task.
     */
    public void addDiagnosticReport(String repairOrderId, String diagTaskResult){
        RepairOrder repairOrder = findRepairOrderById(repairOrderId);
        if(repairOrder != null){
            repairOrder.addDiagnosticReport(diagTaskResult);
        }
    }

    /**
     * Adds a repair task to an existing repair order.
     * 
     * @param repairOrderId The id of the repair order to update.
     * @param repairTask The repair task to add.
     */
    public void addRepairTask(String repairOrderId, RepairTask repairTask){
        RepairOrder repairOrder = findRepairOrderById(repairOrderId);
        if(repairOrder != null){
            repairOrder.addRepairTask(repairTask);
        }
    }

    /**
     * Finds the latest repair order for a specific phone number.
     * 
     * @param phoneNumber The customer's phone number.
     * @return The latest matching repair order, or null if none is found.
     */
    public RepairOrderDTO findRepairOrder(String phoneNumber){
        for(int i = nrOfRepairOrders - 1; i >= 0; i--){
            if(repairOrders[i].getPhoneNumber().equals(phoneNumber)){
                return repairOrders[i].createRepairOrderDTO();
            }
        }
        return null;
    }

    /**
     * Saves a new repair order or updates an exisitng one.
     * 
     * @param repairOrder The specific order to save.
     */
    public void updateRepairOrder(RepairOrder repairOrder){
        for(int i = 0; i < nrOfRepairOrders; i++){
            if(repairOrders[i].getRepairOrderId().equals(repairOrder.getRepairOrderId())){
                repairOrders[i] = repairOrder;
                return;
            }
        }

        repairOrders[nrOfRepairOrders] = repairOrder;
        nrOfRepairOrders++;
    }

    /**
     * Returns a repair order as a DTO based on its id.
     * 
     * @param repairOrderId The id of the repair order.
     * @return The matching repair order as a DTO, or null if none is found.
     */
    public RepairOrderDTO returnRepairOrderDTO(String repairOrderId){
        RepairOrder repairOrder = findRepairOrderById(repairOrderId);
        if(repairOrder != null){
            return repairOrder.createRepairOrderDTO();
        }
        return null;
    }
    
    /**
     * Accepts a repair order.
     * 
     * @param repairOrderId The id of the repair order.
     * @return The accepted repair order, or null if no matching order is found.
     */
    public RepairOrder acceptRepairOrder(String repairOrderId){
        RepairOrder repairOrder = findRepairOrderById(repairOrderId);
        if(repairOrder != null){
            repairOrder.updateRepairOrderStatus("ACCEPTED");
            return repairOrder;
        }
        return null;
    }

    /**
     * Rejects a repair order.
     * 
     * @param repairOrderId The id of the repair order.
     */
    public void rejectRepairOrder(String repairOrderId){
        RepairOrder repairOrder = findRepairOrderById(repairOrderId);
        if(repairOrder != null){
            repairOrder.updateRepairOrderStatus("REJECTED");
        }
    }

    /**
     * Finds a repair order by its id.
     * 
     * @param repairOrderId The id of the repair order.
     * @return The matching repair order, or null if none is found.
     */
    private RepairOrder findRepairOrderById(String repairOrderId){
        for (int i = 0; i < nrOfRepairOrders; i++) {
            if (repairOrders[i].getRepairOrderId().equals(repairOrderId)) {
                return repairOrders[i];
            }
        }
        return null;
    }
}

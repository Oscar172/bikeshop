package se.kth.iv1350.integration;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.model.RepairOrder;

/**
 * Represents a register of all repair orders.
 */
public class RepairOrderRegistry {
    private List<RepairOrderDTO> repairOrders = new ArrayList<>();

    /**
     * Creates a new instace of RepairOrderRegistry. (Constructor)
     */
    public RepairOrderRegistry() {
        // Example order so this is not empty for now
        repairOrders.add(new RepairOrderDTO(null, null, null, null, null, 0, null, null));
    }

    //createRepairOrder
    public RepairOrder createRepairOrder(String problemDescr, String phoneNumber, String bikeSerialNumber){
        return RepairOrder.createRepairOrder(problemDescr, phoneNumber, bikeSerialNumber, this);
    }

    //findAllRepairOrders
    public RepairOrderDTO[] findAllRepairOrders(String phoneNumber){
        return new RepairOrderDTO[0];
    }

    public void addRepairTask(String repairOrderId, String repairTask, double cost){

    }

    public RepairOrderDTO findRepairOrder(String phoneString){
        return null;
    }

    /**
     * Recieves an object (repairOrder) that has been updated with new information
     * and saves it in the list.
     * @param repairOrder The repair order object that has been changed.
     */
    public void updateRepairOrder(RepairOrderDTO repairOrder) {
        for (int i = 0; i < repairOrders.size(); i++) {
            if (repairOrders.get(i).getRepairOrderId().equals(repairOrder.getRepairOrderId())) {
                repairOrders.set(i, repairOrder);
                //Alt.
                System.out.println("Order: " + repairOrder.getRepairOrderId() + "has been updated");
                return;
            }
        }
    }

    /**
     * Retrieves a specific repairorder based on its id.
     * @param repairOrderId The id of the order to fetch.
     * @return The found order returns as a DTO, or null if not found.
     */
    public RepairOrderDTO returnRepairOrderDTO(String repairOrderId) {
        for (RepairOrderDTO order : repairOrders) {
            if (order.getRepairOrderId().equals(repairOrderId)) {
                return order;
            }
        }
        return null;
    }

    public void acceptRepairOrder(String repairOrderId){

    }

    public void rejectRepairOrder(String repairOrderId){
        
    }





}


package se.kth.iv1350.integration;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.model.RepairOrder;
import se.kth.iv1350.view.RepairOrderObserver;

/**
 * Represents a registry of repair orders and contains logic for storing
 * and retrieving repair orders.
 */
public class RepairOrderRegistry {

    private final RepairOrder[] repairOrders;
    private int nrOfRepairOrders;
    private final List<RepairOrderObserver> repairOrderObservers;
    
    /**
     * Creates a new RepairOrderRegistry.
     */
    public RepairOrderRegistry(){
        this.repairOrders = new RepairOrder[100];
        this.nrOfRepairOrders = 0;
        this.repairOrderObservers = new ArrayList<>();
    }

    /**
     * Generates a new repair order id.
     * 
     * @return A unique repair order id.
     */
    public String generateRepairOrderId(){
        return "RO-" + (nrOfRepairOrders + 1);
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
     * Rejects a repair order.
     * 
     * @param repairOrderId The id of the repair order.
     */
    public void rejectRepairOrder(String repairOrderId){
        RepairOrder repairOrder = findRepairOrderById(repairOrderId);
        if(repairOrder != null){
            repairOrder.reject();
            notifyObservers(repairOrder);
        }
    }

    /**
     * Finds a repair order by its id.
     * 
     * @param repairOrderId The id of the repair order.
     * @return The matching repair order, or null if none is found.
     */
    public RepairOrder findRepairOrderById(String repairOrderId){
        for (int i = 0; i < nrOfRepairOrders; i++) {
            if (repairOrders[i].getRepairOrderId().equals(repairOrderId)) {
                return repairOrders[i];
            }
        }
        return null;
    }

    /**
     * A methos that is called when a new Repair Order is created to increment the total
     * number of Repair Orders. 
     */
    public void incrementNrOfRepairOrders() {
        this.nrOfRepairOrders++;
    }
    public int getNrOfRepairOrders() {
        return this.nrOfRepairOrders;
    }

    public RepairOrder[] getRepairOrders() {
        return this.repairOrders;
    }

    //nytt
    /**
     * Adds an observer that will be notified when a repair order is updated.
     * @param observer The observer to add.
     */
    public void addRepairOrderObserver(RepairOrderObserver observer){
        repairOrderObservers.add(observer);
    }

    /**
     * Notifies all observers that a repair order has been updated.
     * @param repairOrder The updated repair order.
     */
    public void notifyObservers(RepairOrder repairOrder){

        RepairOrderDTO repairOrderDTO = repairOrder.createRepairOrderDTO();

        //Gå igenom varje observer i listan, för varje observer anropa repairOrderUpdated(..), skicka in repairOrderDTO
        for(RepairOrderObserver observer : repairOrderObservers){
            observer.repairOrderUpdated(repairOrderDTO);
        }
    }
}

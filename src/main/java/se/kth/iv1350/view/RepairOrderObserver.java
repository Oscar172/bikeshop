package se.kth.iv1350.view;

import se.kth.iv1350.integration.RepairOrderDTO;


/**
 * Implemented by classes that want to be notified when a repair order is updated.
 */
public interface RepairOrderObserver{

    /**
     * Called when a repair order has been updated.
     * 
     * @param repairOrderDTO The updated repair order.
     */
    void repairOrderUpdated(RepairOrderDTO repairOrderDTO);
}

package se.kth.iv1350.view;

import se.kth.iv1350.integration.RepairOrderDTO;

/**
 * Prints a message when a repair order has been updated.
 */
public class RepairOrderView implements RepairOrderObserver {

    /**
     * Called when a repair order has been updated.
     *
     * @param repairOrderDTO The updated repair order.
     */
    @Override
    public void repairOrderUpdated(RepairOrderDTO repairOrderDTO) {
        System.out.println("Repair order updated: " + repairOrderDTO.getRepairOrderId());
    }
}
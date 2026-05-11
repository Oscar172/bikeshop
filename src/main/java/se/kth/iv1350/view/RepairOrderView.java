package se.kth.iv1350.view;

import se.kth.iv1350.integration.RepairOrderDTO;

/**
 * Prints updated repair order information to the user.
 */
public class RepairOrderView implements RepairOrderObserver {

    /**
     * Prints the updated repair order.
     * 
     * @param repairOrderDTO The updated repair order.
     */
    @Override
    public void repairOrderUpdated(RepairOrderDTO repairOrderDTO) {
        System.out.println("-----Updated Repair Order-----");
        System.out.println("Repair order ID: " + repairOrderDTO.getRepairOrderId());
        System.out.println("Phone number: " + repairOrderDTO.getPhoneNumber());
        System.out.println("Problem Description: " + repairOrderDTO.getProblemDescr());
        System.out.println("Serial Number: " + repairOrderDTO.getBikeSerialNumber());
        System.out.println("Status: " + repairOrderDTO.getState());
        System.out.println("Total cost: " + repairOrderDTO.getTotalCost());
        System.out.println("Diagnostic report: " + repairOrderDTO.getDiagnosticReport());
        System.out.println("ETA: " + repairOrderDTO.getEstimatedCompletionDate());
        System.out.println("");
    }
}

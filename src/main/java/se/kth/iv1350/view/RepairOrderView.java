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
        System.out.println("Repair Order ID: " + repairOrderDTO.getRepairOrderId());
        System.out.println("Phone Number: " + repairOrderDTO.getPhoneNumber());
        System.out.println("Problem Description: " + repairOrderDTO.getProblemDescr());
        System.out.println("Serial Number: " + repairOrderDTO.getBikeSerialNumber());
        System.out.println("Status: " + repairOrderDTO.getState());
        System.out.println("Total Cost: " + repairOrderDTO.getTotalCost());
        System.out.println("Diagnostic Report: " + repairOrderDTO.getDiagnosticReport());
        System.out.println("Estimated Repair Time: " + repairOrderDTO.getEstimatedCompletionDate());
        System.out.println("");
    }
}

package se.kth.iv1350.view;

import se.kth.iv1350.integration.RepairOrderDTO;

import se.kth.iv1350.util.Logger;

 /**
 * Writes updated repair orders to a log file.
 */
public class RepairOrderLogger implements RepairOrderObserver {
    private final Logger logger;

    public RepairOrderLogger(Logger logger){
        this.logger = logger;
    }

    /**
     * Writes the upddated repair Order to the log file.
     * @param repairOrderDTO The updated repair order.
     */
    @Override
    public void repairOrderUpdated(RepairOrderDTO repairOrderDTO){
        String message = 
            "-----Repair Order Log-----\n" +
              "Repair order ID: " + repairOrderDTO.getRepairOrderId() + "\n" +
              "Phone number: " + repairOrderDTO.getPhoneNumber() + "\n" +
              "Problem Description: " + repairOrderDTO.getProblemDescr() + "\n" +
              "Serial Number: " + repairOrderDTO.getBikeSerialNumber() + "\n" +
              "Status: " + repairOrderDTO.getState() + "\n" +
              "Total cost: " + repairOrderDTO.getTotalCost() + "\n" +
              "Diagnostic report: " + repairOrderDTO.getDiagnosticReport() + "\n" +
              "ETA: " + repairOrderDTO.getEstimatedCompletionDate() + "\n";
            
        logger.log(message);
    }
}

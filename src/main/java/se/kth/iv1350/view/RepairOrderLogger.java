package se.kth.iv1350.view;

import se.kth.iv1350.integration.RepairOrderDTO;
import se.kth.iv1350.util.Logger;

 /**
 * Logs repair order updates to a file.
 */
public class RepairOrderLogger implements RepairOrderObserver{
    private final Logger logger;

    /**
     * Creates a new instance that logs repair order updates.
     * 
     * @param logger The logger used to write repair order updates.
     */
    public RepairOrderLogger(Logger logger){
        this.logger = logger;
    }

    /**
     * Writes the upddated repair order to the log file.
     * 
     * @param repairOrderDTO The updated repair order.
     */
    @Override
    public void repairOrderUpdated(RepairOrderDTO repairOrderDTO){
        String message = 
            "-----Repair Order Log-----\n" +
              "Repair Order ID: " + repairOrderDTO.getRepairOrderId() + "\n" +
              "Phone Number: " + repairOrderDTO.getPhoneNumber() + "\n" +
              "Problem Description: " + repairOrderDTO.getProblemDescr() + "\n" +
              "Serial Number: " + repairOrderDTO.getBikeSerialNumber() + "\n" +
              "Status: " + repairOrderDTO.getState() + "\n" +
              "Total Cost: " + repairOrderDTO.getTotalCost() + "\n" +
              "Diagnostic Report: " + repairOrderDTO.getDiagnosticReport() + "\n" +
              "Etimated Repair Time: " + repairOrderDTO.getEstimatedCompletionDate() + "\n";
            
        logger.log(message);
    }
}


